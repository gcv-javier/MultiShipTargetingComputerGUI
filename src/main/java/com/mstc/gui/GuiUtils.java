package com.mstc.gui;

import com.mstc.core.calculator.BasicCalculator;
import com.mstc.core.calculator.TrigonometricCalculator;
import com.mstc.core.data.TorpedoType;
import com.mstc.core.dto.CalculationResult;
import com.mstc.core.dto.Settings;
import com.mstc.core.dto.TargetShip;
import com.mstc.core.dto.Torpedo;
import com.mstc.core.utils.InputChecker;
import com.mstc.core.utils.InputOutputParser;
import com.mstc.core.utils.UnitsConverter;
import org.apache.commons.math3.util.Precision;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class used to implement UI logic instead of using the main UI class (MsTcGui) and making it too big.
 */
public class GuiUtils {

    // Input distance in meters
    public static void enableTorpedoesDependingOnRange(List<TorpedoDisplayed> targetShipTorpedoesToBeDisplayed,
                                                 JComboBox targetShipComboBoxTorpedoType,
                                                 JComboBox targetShipComboBoxTorpedoSpeed,
                                                 JLabel targetShipTorpedoRangeLabel,
                                                 Settings settings) {

        targetShipComboBoxTorpedoType.removeAllItems();
        targetShipComboBoxTorpedoSpeed.removeAllItems();

        targetShipTorpedoesToBeDisplayed.forEach(t -> targetShipComboBoxTorpedoType.addItem(
                new TorpedoComboItem(t.getTorpedoType().getName(), t.getTorpedoType())));

        if (targetShipComboBoxTorpedoType.getItemCount() > 0) {
            // by default it's disabled until the user inserts a distance that covers at least one torpedo range
            targetShipComboBoxTorpedoType.setEnabled(true);
            // the first item will be the selected by default
            targetShipComboBoxTorpedoType.setSelectedIndex(0);

            TorpedoComboItem selectedTorpedoItem = (TorpedoComboItem) targetShipComboBoxTorpedoType.getSelectedItem();

            targetShipTorpedoesToBeDisplayed.get(0).getSpeedRangeMap().entrySet()
                    .forEach(s -> {
                        targetShipComboBoxTorpedoSpeed.setEnabled(true);
                        targetShipComboBoxTorpedoSpeed.addItem(InputOutputParser.parseOutputSpeed(settings.getUnitSystem(), s.getKey()));
                    });

            // there will be one at least, because calculateTorpedoesToBeDisplayed only adds a torpedo when there is at least one right range
            if (targetShipComboBoxTorpedoSpeed.getItemCount() > 0) {
                // the first item will be the selected by default
                targetShipComboBoxTorpedoSpeed.setSelectedIndex(0);
                // set torpedo range
                updateTargetShipTorpedoRange(targetShipComboBoxTorpedoType, targetShipComboBoxTorpedoSpeed,
                        targetShipTorpedoRangeLabel, targetShipTorpedoesToBeDisplayed, settings);
            }
        } else {
            targetShipComboBoxTorpedoType.setEnabled(false);
            targetShipComboBoxTorpedoSpeed.setEnabled(false);
            targetShipTorpedoRangeLabel.setText("");
        }
    }

    public static List<TorpedoDisplayed> calculateTorpedoesToBeDisplayed(double inputDistance) {

        List<TorpedoDisplayed> targetShipTorpedoesToBeDisplayed = new ArrayList<>();

        List<TorpedoType> torpedoTypeList = Arrays.asList(TorpedoType.T1, TorpedoType.T2,
                TorpedoType.T3, TorpedoType.T5);

        // Calculate the torpedoes and its speeds to be displayed
        for (TorpedoType torpedoType : torpedoTypeList) {
            TorpedoDisplayed torpedoDisplayed = new TorpedoDisplayed(torpedoType);
            boolean torpedoToBeDisplayed = false;
            for (Map.Entry<Double, Integer> speedRangeEntry : torpedoType.getSpeedRangeMap().entrySet()) {
                if (speedRangeEntry.getValue() >= inputDistance) {
                    torpedoDisplayed.getSpeedRangeMap().put(speedRangeEntry.getKey(), speedRangeEntry.getValue());
                    torpedoToBeDisplayed = true;
                }
            }
            if (torpedoToBeDisplayed) {
                targetShipTorpedoesToBeDisplayed.add(torpedoDisplayed);
            }
        }
        return targetShipTorpedoesToBeDisplayed;
    }

    public static void updateTargetShipTorpedoSpeedAndRange(JComboBox targetShipComboBoxTorpedoType,
                                                      JComboBox targetShipComboBoxTorpedoSpeed,
                                                      List<TorpedoDisplayed> targetShipTorpedoesToBeDisplayed,
                                                      JLabel targetShipTorpedoRangeLabel,
                                                      Settings settings) {

        TorpedoComboItem selectedTorpedoItem = (TorpedoComboItem) targetShipComboBoxTorpedoType.getSelectedItem();
        // this launches the listener that calls updateTargetShipTorpedoRange() although we filter the call in case it's not focused by the user
        targetShipComboBoxTorpedoSpeed.removeAllItems();

        // get the selected torpedo displayed
        Optional<TorpedoDisplayed> selectedTorpedoDisplayed = targetShipTorpedoesToBeDisplayed.stream()
                .filter(t -> t.getTorpedoType().equals(selectedTorpedoItem.getValue())).findFirst();

        if (!selectedTorpedoDisplayed.isPresent()) {
            return;
        }

        // show the available speed depending on the distance
        selectedTorpedoDisplayed.get().getSpeedRangeMap().entrySet()
                .forEach(s -> targetShipComboBoxTorpedoSpeed.addItem(InputOutputParser.parseOutputSpeed(settings.getUnitSystem(), s.getKey())));

        if (targetShipComboBoxTorpedoSpeed.getItemCount() > 0) {
            // at least one speed would be for the torpedo type
            // select by default the first one
            // this launches the listener that calls updateTargetShipTorpedoRange() although we filter the call in case it's not focused by the user
            targetShipComboBoxTorpedoSpeed.setSelectedIndex(0);

            // update the range label
            updateTargetShipTorpedoRange(targetShipComboBoxTorpedoType, targetShipComboBoxTorpedoSpeed,
                    targetShipTorpedoRangeLabel, targetShipTorpedoesToBeDisplayed, settings);
        }
    }

    public static void updateTargetShipTorpedoRange(JComboBox targetShipComboBoxTorpedoType,
                                              JComboBox targetShipComboBoxTorpedoSpeed,
                                              JLabel targetShipTorpedoRangeLabel,
                                              List<TorpedoDisplayed> targetShipTorpedoesToBeDisplayed,
                                              Settings settings) {
        // this method is called when the speeds are deleted, but at this moment we don't need the range
        if (targetShipComboBoxTorpedoSpeed.getItemCount() > 0) {
            TorpedoComboItem selectedTorpedoItem = (TorpedoComboItem) targetShipComboBoxTorpedoType.getSelectedItem();

            // get the selected torpedo displayed
            Optional<TorpedoDisplayed> selectedTorpedoDisplayed = targetShipTorpedoesToBeDisplayed.stream()
                    .filter(t -> t.getTorpedoType().equals(selectedTorpedoItem.getValue())).findFirst();

            if (!selectedTorpedoDisplayed.isPresent()) {
                return;
            }

            Double torpedoSpeed = (Double) targetShipComboBoxTorpedoSpeed.getSelectedItem();
            double speedSelected = Precision.round(InputOutputParser.parseInputSpeed(settings.getUnitSystem(), torpedoSpeed), 1);

            // System.out.println("Torpedo Speed: " + torpedoSpeed);
            // System.out.println("Speed Selected: " + speedSelected);
            // System.out.println(selectedTorpedoDisplayed.get().getSpeedRangeMap().entrySet());

            // we need to find the range in the map of speeds of the selected distance
            targetShipTorpedoRangeLabel.setText(String.valueOf(
                    InputOutputParser.parseOutputDistance(settings.getUnitSystem(),
                            Double.valueOf(selectedTorpedoDisplayed.get().getSpeedRangeMap().get(speedSelected)))));
        }
    }

    public static TargetShip controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(int index,
                                                                                              JComboBox targetShipComboBoxTorpedoType,
                                                                                              JComboBox targetShipComboBoxTorpedoSpeed,
                                                                                              JTextField targetShipSpeed,
                                                                                              JTextField targetShipName,
                                                                                              JTextField targetShipAngleOfBow,
                                                                                              JTextField targetShipDistance,
                                                                                              JLabel targetShipTorpedoAngleLabel,
                                                                                              JLabel targetShipDistanceToImpactLabel,
                                                                                              JLabel targetShipTimeToImpactLabel,
                                                                                              Settings settings,
                                                                                              JLabel outputLabel,
                                                                                              TrigonometricCalculator trigonometricCalculator,
                                                                                              BasicCalculator basicCalculator) {

        // Input control
        if (settings.getCalculator() instanceof TrigonometricCalculator) {
            if (InputChecker.areInputsNullOrEmptyStrings(targetShipSpeed.getText(), targetShipAngleOfBow.getText(), targetShipDistance.getText())) {
                outputLabel.setText(String.format("Speed, AOB or Distance are empty, ship %d", index + 1));
                return null;
            }
        }

        if (InputChecker.areInputsNullOrEmptyStrings(targetShipComboBoxTorpedoType.getSelectedItem(),
                targetShipComboBoxTorpedoSpeed.getSelectedItem())) {
            outputLabel.setText(String.format("Torpedo is missing, review the distance is in range, ship %d", index + 1));
            return null;
        }

        // For basic calculation we only need the distance to the ship and the torpedo speed
        if (settings.getCalculator() instanceof BasicCalculator
                && InputChecker.areInputsNullOrEmptyStrings(targetShipDistance.getText())) {
            outputLabel.setText(String.format("Distance not available and it's necessary for Basic calculation, ship %d", index + 1));
            return null;
        }

        // Create Torpedo and Target Ship
        TorpedoType torpedoType = ((TorpedoComboItem) targetShipComboBoxTorpedoType.getSelectedItem()).getValue();
        Double torpedoSpeed = InputOutputParser.parseInputSpeed(settings.getUnitSystem(),
                    (Double) targetShipComboBoxTorpedoSpeed.getSelectedItem());

        Torpedo torpedo = new Torpedo.TorpedoBuilder().setType(torpedoType).setSpeed(torpedoSpeed).createTorpedo();

        TargetShip targetShip = new TargetShip.TargetShipBuilder()
                .setId(index + 1)
                .setName(targetShipName.getText())
                .setSpeed(settings.getCalculator() instanceof TrigonometricCalculator ?
                        InputOutputParser.parseInputSpeed(settings.getUnitSystem(), Double.parseDouble(targetShipSpeed.getText())) : null)
                .setAngleOfBow(settings.getCalculator() instanceof TrigonometricCalculator ?
                        Double.parseDouble(targetShipAngleOfBow.getText()) : null)
                .setDistance(InputOutputParser.parseInputDistance(settings.getUnitSystem(), Double.parseDouble(targetShipDistance.getText())))
                .setTorpedo(torpedo).createTargetShip();

        // Show result of calculation for the current target (we use Apache commons math to round the amounts)
        double torpedoAngle = Precision.round(settings.getCalculator() instanceof TrigonometricCalculator ?
                trigonometricCalculator.calculateTorpedoAngle(targetShip) : 0, 2);
        double distanceToImpact = InputOutputParser.parseOutputDistance(settings.getUnitSystem(),
                settings.getCalculator() instanceof TrigonometricCalculator ?
                        trigonometricCalculator.calculateDistanceToImpact(targetShip) :
                        basicCalculator.calculateDistanceToImpact(targetShip));
        double timeToImpact = Precision.round(settings.getCalculator() instanceof TrigonometricCalculator ?
                trigonometricCalculator.calculateTimeToImpact(targetShip) :
                basicCalculator.calculateTimeToImpact(targetShip), 2);

        targetShipTorpedoAngleLabel.setText(settings.getCalculator() instanceof TrigonometricCalculator ?
                String.valueOf(torpedoAngle) : "N/A");
        targetShipDistanceToImpactLabel.setText(String.valueOf(distanceToImpact));
        targetShipTimeToImpactLabel.setText(UnitsConverter.fromSecondsToMinutesAndSeconds(timeToImpact));

        return targetShip;
    }

    public static void setShootingOrderLabels(int index, List<CalculationResult> shootingInOrder,
                                        JLabel targetDescription, JLabel targetTorpedoDescription,
                                        JLabel targetTitle, JLabel targetWait, JLabel targetCountDownLabel,
                                        Settings settings) {
        targetTitle.setEnabled(true);
        if(targetWait != null ) {
            targetWait.setEnabled(true);
            targetCountDownLabel.setEnabled(true);

            double secondsToWaitForTarget =
                    shootingInOrder.get(index -1 ).getTimeToImpact() - shootingInOrder.get(index).getTimeToImpact();
            long durationTime = (long) secondsToWaitForTarget * 1000; // duration in milliseconds
            SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
            targetCountDownLabel.setText(df.format(durationTime));
        }
        targetDescription.setEnabled(true);
        targetTorpedoDescription.setEnabled(true);
        targetDescription.setText(String.format("Target Ship %s (%s)",
                shootingInOrder.get(index).getTargetShip().getId(), shootingInOrder.get(index).getTargetShip().getName()));
        targetTorpedoDescription.setText(String.format("Torpedo %s at %s %s",
                shootingInOrder.get(index).getTargetShip().getTorpedo().getType().getName(),
                InputOutputParser.parseOutputSpeed(settings.getUnitSystem(),
                        shootingInOrder.get(index).getTargetShip().getTorpedo().getSpeed()),
                settings.getUnitSystem().equals(Settings.UNITS_SYSTEM.MIXED) ||
                        settings.getUnitSystem().equals(Settings.UNITS_SYSTEM.NAUTICAL)
                        ? "knots" : "km/h"));

    }

    public static void reproduceTwoSecondsClip(Class guiClass) {
        URL url = guiClass.getResource("/clips/mixkit-start-match-countdown-1954.wav");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

}
