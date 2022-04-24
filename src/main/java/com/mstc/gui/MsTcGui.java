package com.mstc.gui;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.mstc.core.calculator.BasicCalculator;
import com.mstc.core.calculator.TrigonometricCalculator;
import com.mstc.core.dto.CalculationResult;
import com.mstc.core.utils.InputChecker;
import com.mstc.core.dto.Settings;
import com.mstc.core.dto.TargetShip;
import com.mstc.core.utils.InputOutputParser;

import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 * The GUI class that works with MSTCGUI.form
 */
public class MsTcGui extends JFrame {

    private TrigonometricCalculator trigonometricCalculator;
    private BasicCalculator basicCalculator;
    private Settings settings;
    private List<TorpedoDisplayed> targetShip1TorpedoesToBeDisplayed = new ArrayList<TorpedoDisplayed>();
    private List<TorpedoDisplayed> targetShip2TorpedoesToBeDisplayed = new ArrayList<TorpedoDisplayed>();
    private List<TorpedoDisplayed> targetShip3TorpedoesToBeDisplayed = new ArrayList<TorpedoDisplayed>();
    private List<TorpedoDisplayed> targetShip4TorpedoesToBeDisplayed = new ArrayList<TorpedoDisplayed>();
    private List<TorpedoDisplayed> targetShip5TorpedoesToBeDisplayed = new ArrayList<TorpedoDisplayed>();

    // Group for radio buttons
    private ButtonGroup groupUnits;

    // the first target to wait for is the second one
    private Timer timer;
    private long startTime = -1;
    private long duration = 0;
    boolean reproducingClipAboutTime = false;

    private JPanel mainPanel;
    private JPanel settingsPanel;
    private JPanel targetInfoPanel;
    private JPanel calculationPanel;
    private JPanel resultsPanel;
    private JRadioButton metricRadioButton;
    private JRadioButton mixedRadioButton;
    private JRadioButton nauticalRadioButton;
    private JSpinner spinnerTargetShips;
    private JTabbedPane tabbedPanel;
    private JPanel panelShip1;
    private JPanel panelInfo;
    private JPanel panelInfoFlow;
    private JTextField targetShip1Name;
    private JTextField targetShip1Speed;
    private JTextField targetShip1AngleOfBow;
    private JTextField targetShip1Distance;
    private JComboBox targetShip1ComboBoxTorpedoType;
    private JComboBox targetShip1ComboBoxTorpedoSpeed;
    private JButton calculateButton;
    private JScrollPane panelInfoScroll;
    private JTextArea angleOfBowTitleTextArea;
    private JTextArea angleOfBowLink;
    private JLabel targetShip1TimeToImpactLabel;
    private JLabel outputLabel;
    private JLabel targetShip1DistanceToImpactLabel;
    private JLabel targetShip1TorpedoAngleLabel;
    private JButton uboatFireButton;
    private JLabel secondTargetCountDownLabel;
    private JRadioButton basicCalculationRadioButton;
    private JRadioButton trigonometricCalculationRadioButton;
    private JLabel targetShip1SpeedLabel;
    private JLabel targetShip1DistanceLabel;
    private JLabel firstTargetDescription;
    private JLabel firstTargetTorpedoDescription;
    private JLabel secondTargetDescription;
    private JLabel secondTargetTorpedoDescription;
    private JLabel secondTargetFireMessage;
    private JLabel thirdTargetCountDownLabel;
    private JLabel thirdTargetDescription;
    private JLabel thirdTargetTorpedoDescription;
    private JLabel targetShip1TorpedoRangeLabel;
    private JLabel targetShip1TorpedoSpeedLabel;
    private JPanel panelShip2;
    private JPanel panelShip3;
    private JPanel panelShip4;
    private JPanel panelShip5;
    private JLabel targetShip2SpeedLabel;
    private JLabel targetShip3SpeedLabel;
    private JLabel targetShip4SpeedLabel;
    private JLabel targetShip5SpeedLabel;
    private JTextField targetShip2Speed;
    private JTextField targetShip3Speed;
    private JTextField targetShip4Speed;
    private JTextField targetShip5Speed;
    private JTextField targetShip2AngleOfBow;
    private JTextField targetShip3AngleOfBow;
    private JTextField targetShip4AngleOfBow;
    private JTextField targetShip5AngleOfBow;
    private JLabel targetShip2DistanceLabel;
    private JLabel targetShip3DistanceLabel;
    private JLabel targetShip4DistanceLabel;
    private JLabel targetShip5DistanceLabel;
    private JTextField targetShip2Distance;
    private JTextField targetShip3Distance;
    private JTextField targetShip4Distance;
    private JTextField targetShip5Distance;
    private JTextField targetShip2Name;
    private JTextField targetShip3Name;
    private JTextField targetShip4Name;
    private JTextField targetShip5Name;
    private JComboBox targetShip2ComboBoxTorpedoType;
    private JComboBox targetShip3ComboBoxTorpedoType;
    private JComboBox targetShip4ComboBoxTorpedoType;
    private JComboBox targetShip5ComboBoxTorpedoType;
    private JComboBox targetShip2ComboBoxTorpedoSpeed;
    private JComboBox targetShip3ComboBoxTorpedoSpeed;
    private JComboBox targetShip4ComboBoxTorpedoSpeed;
    private JComboBox targetShip5ComboBoxTorpedoSpeed;
    private JLabel targetShip2TorpedoRangeLabel;
    private JLabel targetShip3TorpedoRangeLabel;
    private JLabel targetShip4TorpedoRangeLabel;
    private JLabel targetShip5TorpedoRangeLabel;
    private JLabel targetShip2TimeToImpactLabel;
    private JLabel targetShip3TimeToImpactLabel;
    private JLabel targetShip4TimeToImpactLabel;
    private JLabel targetShip5TimeToImpactLabel;
    private JLabel targetShip2TorpedoAngleLabel;
    private JLabel targetShip3TorpedoAngleLabel;
    private JLabel targetShip4TorpedoAngleLabel;
    private JLabel targetShip5TorpedoAngleLabel;
    private JLabel targetShip2DistanceToImpactLabel;
    private JLabel targetShip3DistanceToImpactLabel;
    private JLabel targetShip4DistanceToImpactLabel;
    private JLabel targetShip5DistanceToImpactLabel;
    private JLabel thirdTargetFireMessage;
    private JLabel firstTargetTitle;
    private JLabel secondTargetTitle;
    private JLabel thirdTargetTitle;
    private JLabel fourthTargetTitle;
    private JLabel fifthTargetTitle;
    private JLabel secondTargetWait;
    private JLabel thirdTargetWait;
    private JLabel fourthTargetWait;
    private JLabel fifthTargetWait;
    private JLabel fourthTargetDescription;
    private JLabel fifthTargetDescription;
    private JLabel fourthTargetTorpedoDescription;
    private JLabel fifthTargetTorpedoDescription;
    private JLabel fourthTargetCountDownLabel;
    private JLabel fifthTargetCountDownLabel;
    private JLabel targetShip2TorpedoSpeedLabel;
    private JLabel targetShip3TorpedoSpeedLabel;
    private JLabel targetShip4TorpedoSpeedLabel;
    private JLabel targetShip5TorpedoSpeedLabel;
    private JLabel targetShip5DistanceToImpactText;
    private JLabel targetShip4DistanceToImpactText;
    private JLabel targetShip3DistanceToImpactText;
    private JLabel targetShip2DistanceToImpactText;
    private JLabel targetShip1DistanceToImpactText;
    private JLabel fourthTargetFireMessage;
    private JLabel fifthTargetFireMessage;
    private JTextArea angleOfBowContent1TextArea;
    private JLabel redditLink;
    private JTextArea angleOfBowContent2TextArea;
    private JTextArea angleOfBowContent3TextArea;
    private JLabel firstImageLabel;
    private JLabel secondLabelImage;
    private JLabel thirdLabelImage;
    private JPanel creditsPanel;
    private JPanel calculationMethodPanel;
    private JPanel calculationOutputPanel;


    /**
     * Creates new form MSTCGUI
     */
    public MsTcGui() {
        trigonometricCalculator = new TrigonometricCalculator();
        basicCalculator = new BasicCalculator();
        // by default the unit system is metric
        // by default there is one ship selected
        // by default we use the trigonometric calculator
        settings = new Settings(Settings.UNITS_SYSTEM.METRIC, 1, trigonometricCalculator);

        // init the GUI components
        initComponents();
        // init the action listeners of the components of the GUI
        initListeners();

    }

    private void initComponents() {
        // Set main panel for the Frame window
        setContentPane(mainPanel);
        // Group for the units radio buttons
        groupUnits = new ButtonGroup();
        groupUnits.add(metricRadioButton);
        groupUnits.add(mixedRadioButton);
        groupUnits.add(nauticalRadioButton);

        // Maximum values for the spinner (target ships)
        // default value, lower bound, upper bound, increment by
        spinnerTargetShips.setModel(new SpinnerNumberModel(1.0, 1.0, 5.0, 1.0));
        // manual insertion is not allowed
        ((JSpinner.DefaultEditor) spinnerTargetShips.getEditor()).getTextField().setEditable(false);

        // Fields that can only be decimals
        targetShip1Speed.setDocument(new FixedSizeDecimalDocument(targetShip1Distance, 10));
        targetShip1AngleOfBow.setDocument(new FixedSizeDecimalDocument(targetShip1Distance, 10));
        targetShip1Distance.setDocument(new FixedSizeDecimalDocument(targetShip1Distance, 10));

        // Torpedo Type
        // in case change of the value manually
        targetShip1ComboBoxTorpedoType.setEditable(false);

        // Torpedo Speed
        // in case change of the value manually
        targetShip1ComboBoxTorpedoSpeed.setEditable(false);

        // Group for the calculation methods
        ButtonGroup groupCalculators = new ButtonGroup();
        groupCalculators.add(basicCalculationRadioButton);
        groupCalculators.add(trigonometricCalculationRadioButton);

        // tab panel
        tabbedPanel.setEnabledAt(0, true);
        tabbedPanel.setEnabledAt(1, false);
        tabbedPanel.setEnabledAt(2, false);
        tabbedPanel.setEnabledAt(3, false);
        tabbedPanel.setEnabledAt(4, false);
        tabbedPanel.setEnabledAt(5, true);

        // help panel
        angleOfBowLink.setFont(new Font("Consolas", Font.PLAIN, 16));
        angleOfBowLink.setForeground(Color.BLUE.darker());
        angleOfBowLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelInfoScroll.getVerticalScrollBar().setUnitIncrement(14);
        panelInfoScroll.getVerticalScrollBar().setValues(0, 0, 0, 0);

        ImageIcon image1 = new ImageIcon(getClass().getResource("/help/AOB_1.PNG"));
        ImageIcon image2 = new ImageIcon(getClass().getResource("/help/AOB_2.PNG"));
        ImageIcon image3 = new ImageIcon(getClass().getResource("/help/AOB_3.PNG"));

        angleOfBowContent1TextArea.setText("Select protactor tool in the map:");
        firstImageLabel.setIcon(image1);

        angleOfBowContent2TextArea.setText("Zoom in and click right in the middle \n of the uboat>");
        secondLabelImage.setIcon(image2);

        angleOfBowContent3TextArea.setText("Release the mouse button into \n the middle of the target ship \n and move the mouse until the \n center of the bow of the ship>");
        thirdLabelImage.setIcon(image3);

        // TODO include more documentation to help the user

        // reddit link in the credits
        redditLink.setFont(new Font("Consolas", Font.PLAIN, 16));
        redditLink.setForeground(Color.BLUE.darker());
        redditLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    private void initListeners() {

        // Units Radio buttons action listeners
        metricRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setUnitSystem(Settings.UNITS_SYSTEM.METRIC);
                updateFieldLabelsAllTabs(Settings.UNITS_SYSTEM.METRIC);
            }
        });
        mixedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setUnitSystem(Settings.UNITS_SYSTEM.MIXED);
                updateFieldLabelsAllTabs(Settings.UNITS_SYSTEM.MIXED);
            }
        });
        nauticalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setUnitSystem(Settings.UNITS_SYSTEM.NAUTICAL);
                updateFieldLabelsAllTabs(Settings.UNITS_SYSTEM.NAUTICAL);
            }
        });

        // Spinner listener
        JComponent comp = spinnerTargetShips.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);

        spinnerTargetShips.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // System.out.println("value changed: " + spinnerTargetShips.getValue());
                // System.out.println(tabbedPanel.getComponents().length);
                // System.out.println(tabbedPanel.getComponent(0).getName());
                switch ((int) ((double) spinnerTargetShips.getValue())) {
                    case 1:
                        tabbedPanel.setEnabledAt(0, true);
                        tabbedPanel.setEnabledAt(1, false);
                        tabbedPanel.setEnabledAt(2, false);
                        tabbedPanel.setEnabledAt(3, false);
                        tabbedPanel.setEnabledAt(4, false);
                        settings.setTargetShips(1);
                        break;
                    case 2:
                        tabbedPanel.setEnabledAt(0, true);
                        tabbedPanel.setEnabledAt(1, true);
                        tabbedPanel.setEnabledAt(2, false);
                        tabbedPanel.setEnabledAt(3, false);
                        tabbedPanel.setEnabledAt(4, false);
                        settings.setTargetShips(2);
                        break;
                    case 3:
                        tabbedPanel.setEnabledAt(0, true);
                        tabbedPanel.setEnabledAt(1, true);
                        tabbedPanel.setEnabledAt(2, true);
                        tabbedPanel.setEnabledAt(3, false);
                        tabbedPanel.setEnabledAt(4, false);
                        settings.setTargetShips(3);
                        break;
                    case 4:
                        tabbedPanel.setEnabledAt(0, true);
                        tabbedPanel.setEnabledAt(1, true);
                        tabbedPanel.setEnabledAt(2, true);
                        tabbedPanel.setEnabledAt(3, true);
                        tabbedPanel.setEnabledAt(4, false);
                        settings.setTargetShips(4);
                        break;
                    case 5:
                        tabbedPanel.setEnabledAt(0, true);
                        tabbedPanel.setEnabledAt(1, true);
                        tabbedPanel.setEnabledAt(2, true);
                        tabbedPanel.setEnabledAt(3, true);
                        tabbedPanel.setEnabledAt(4, true);
                        settings.setTargetShips(5);
                        JOptionPane.showMessageDialog(tabbedPanel,
                                "The stern torpedo might get out of range, make sure you can reach the desired target.",
                                "Warning", WARNING_MESSAGE);
                        break;
                }
            }
        });

        // Target Ship Distance Key Listener, to activate and show the right values of the Torpedo type and its speed
        // when the distance to ship is too far we need to remove the speeds and the torpedo types that don't cover that
        targetShip1Distance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Strings.isNullOrEmpty(targetShip1Distance.getText())) {
                    targetShip1TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip1Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip1TorpedoesToBeDisplayed,
                            targetShip1ComboBoxTorpedoType, targetShip1ComboBoxTorpedoSpeed,
                            targetShip1TorpedoRangeLabel, settings);
                }
            }
        });
        targetShip2Distance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Strings.isNullOrEmpty(targetShip2Distance.getText())) {
                    targetShip2TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip2Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip2TorpedoesToBeDisplayed,
                            targetShip2ComboBoxTorpedoType, targetShip2ComboBoxTorpedoSpeed,
                            targetShip2TorpedoRangeLabel, settings);
                }
            }
        });
        targetShip3Distance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Strings.isNullOrEmpty(targetShip3Distance.getText())) {
                    targetShip3TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip3Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip3TorpedoesToBeDisplayed,
                            targetShip3ComboBoxTorpedoType, targetShip3ComboBoxTorpedoSpeed,
                            targetShip3TorpedoRangeLabel, settings);
                }
            }
        });
        targetShip4Distance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Strings.isNullOrEmpty(targetShip4Distance.getText())) {
                    targetShip4TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip4Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip4TorpedoesToBeDisplayed,
                            targetShip4ComboBoxTorpedoType, targetShip4ComboBoxTorpedoSpeed,
                            targetShip4TorpedoRangeLabel, settings);
                }
            }
        });
        targetShip5Distance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Strings.isNullOrEmpty(targetShip5Distance.getText())) {
                    targetShip5TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip5Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip5TorpedoesToBeDisplayed,
                            targetShip5ComboBoxTorpedoType, targetShip5ComboBoxTorpedoSpeed,
                            targetShip5TorpedoRangeLabel, settings);
                }
            }
        });

        // Torpedo Type ComboBox
        // when torpedo type changes we change the torpedo speed(s) available
        targetShip1ComboBoxTorpedoType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    // if the mouse made the selection -> the comboBox has focus
                    if (targetShip1ComboBoxTorpedoType.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoSpeedAndRange(targetShip1ComboBoxTorpedoType,
                                targetShip1ComboBoxTorpedoSpeed, targetShip1TorpedoesToBeDisplayed,
                                targetShip1TorpedoRangeLabel, settings);
                    }
                }
            }
        });
        targetShip2ComboBoxTorpedoType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    // if the mouse made the selection -> the comboBox has focus
                    if (targetShip2ComboBoxTorpedoType.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoSpeedAndRange(targetShip2ComboBoxTorpedoType,
                                targetShip2ComboBoxTorpedoSpeed, targetShip2TorpedoesToBeDisplayed,
                                targetShip2TorpedoRangeLabel, settings);
                    }
                }
            }
        });
        targetShip3ComboBoxTorpedoType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    // if the mouse made the selection -> the comboBox has focus
                    if (targetShip3ComboBoxTorpedoType.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoSpeedAndRange(targetShip3ComboBoxTorpedoType,
                                targetShip3ComboBoxTorpedoSpeed, targetShip3TorpedoesToBeDisplayed,
                                targetShip3TorpedoRangeLabel, settings);
                    }
                }
            }
        });
        targetShip4ComboBoxTorpedoType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    // if the mouse made the selection -> the comboBox has focus
                    if (targetShip4ComboBoxTorpedoType.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoSpeedAndRange(targetShip4ComboBoxTorpedoType,
                                targetShip4ComboBoxTorpedoSpeed, targetShip4TorpedoesToBeDisplayed,
                                targetShip4TorpedoRangeLabel, settings);
                    }
                }
            }
        });
        targetShip5ComboBoxTorpedoType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    // if the mouse made the selection -> the comboBox has focus
                    if (targetShip5ComboBoxTorpedoType.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoSpeedAndRange(targetShip5ComboBoxTorpedoType,
                                targetShip5ComboBoxTorpedoSpeed, targetShip5TorpedoesToBeDisplayed,
                                targetShip5TorpedoRangeLabel, settings);
                    }
                }
            }
        });

        // Torpedo Speed ComboBox
        targetShip1ComboBoxTorpedoSpeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    if (targetShip1ComboBoxTorpedoSpeed.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoRange(targetShip1ComboBoxTorpedoType,
                                targetShip1ComboBoxTorpedoSpeed, targetShip1TorpedoRangeLabel,
                                targetShip1TorpedoesToBeDisplayed, settings);
                    }
                }
            }
        });
        targetShip2ComboBoxTorpedoSpeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    if (targetShip2ComboBoxTorpedoSpeed.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoRange(targetShip2ComboBoxTorpedoType,
                                targetShip2ComboBoxTorpedoSpeed, targetShip2TorpedoRangeLabel,
                                targetShip2TorpedoesToBeDisplayed, settings);
                    }
                }
            }
        });
        targetShip3ComboBoxTorpedoSpeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    if (targetShip3ComboBoxTorpedoSpeed.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoRange(targetShip3ComboBoxTorpedoType,
                                targetShip3ComboBoxTorpedoSpeed, targetShip3TorpedoRangeLabel,
                                targetShip3TorpedoesToBeDisplayed, settings);
                    }
                }
            }
        });
        targetShip4ComboBoxTorpedoSpeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    if (targetShip4ComboBoxTorpedoSpeed.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoRange(targetShip4ComboBoxTorpedoType,
                                targetShip4ComboBoxTorpedoSpeed, targetShip4TorpedoRangeLabel,
                                targetShip4TorpedoesToBeDisplayed, settings);
                    }
                }
            }
        });
        targetShip5ComboBoxTorpedoSpeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Object item = event.getItem();
                    if (targetShip5ComboBoxTorpedoSpeed.hasFocus()) {
                        GuiUtils.updateTargetShipTorpedoRange(targetShip5ComboBoxTorpedoType,
                                targetShip5ComboBoxTorpedoSpeed, targetShip5TorpedoRangeLabel,
                                targetShip5TorpedoesToBeDisplayed, settings);
                    }
                }
            }
        });

        // Calculation Radio Buttons
        basicCalculationRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setCalculator(basicCalculator);
            }
        });
        trigonometricCalculationRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setCalculator(trigonometricCalculator);
            }
        });

        // Calculation button listener
        ActionListener calculateAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO SPLIT IN SEVERAL METHODS

                // Control input errors and create targets, torpedoes and set the result of the calculation
                List<TargetShip> targetShipList = new ArrayList<>();
                TargetShip targetShip1, targetShip2, targetShip3, targetShip4, targetShip5 = null;

                // ---------1
                if (settings.getTargetShips() >= 1) {
                    targetShip1 = GuiUtils.controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(0,
                            targetShip1ComboBoxTorpedoType, targetShip1ComboBoxTorpedoSpeed, targetShip1Speed, targetShip1Name,
                            targetShip1AngleOfBow, targetShip1Distance, targetShip1TorpedoAngleLabel, targetShip1DistanceToImpactLabel,
                            targetShip1TimeToImpactLabel, settings, outputLabel, trigonometricCalculator, basicCalculator);
                    if (targetShip1 == null) {
                        return;
                    }
                    targetShipList.add(targetShip1);
                }
                //----------2
                if (settings.getTargetShips() >= 2) {
                    targetShip2 = GuiUtils.controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(1,
                            targetShip2ComboBoxTorpedoType, targetShip2ComboBoxTorpedoSpeed, targetShip2Speed, targetShip2Name,
                            targetShip2AngleOfBow, targetShip2Distance, targetShip2TorpedoAngleLabel, targetShip2DistanceToImpactLabel,
                            targetShip2TimeToImpactLabel, settings, outputLabel, trigonometricCalculator, basicCalculator);
                    if (targetShip2 == null) {
                        return;
                    }
                    targetShipList.add(targetShip2);
                }
                //----------3
                if (settings.getTargetShips() >= 3) {
                    targetShip3 = GuiUtils.controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(2,
                            targetShip3ComboBoxTorpedoType, targetShip3ComboBoxTorpedoSpeed, targetShip3Speed, targetShip3Name,
                            targetShip3AngleOfBow, targetShip3Distance, targetShip3TorpedoAngleLabel, targetShip3DistanceToImpactLabel,
                            targetShip3TimeToImpactLabel, settings, outputLabel, trigonometricCalculator, basicCalculator);
                    if (targetShip3 == null) {
                        return;
                    }
                    targetShipList.add(targetShip3);
                }
                //----------4
                if (settings.getTargetShips() >= 4) {
                    targetShip4 = GuiUtils.controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(3,
                            targetShip4ComboBoxTorpedoType, targetShip4ComboBoxTorpedoSpeed, targetShip4Speed, targetShip4Name,
                            targetShip4AngleOfBow, targetShip4Distance, targetShip4TorpedoAngleLabel, targetShip4DistanceToImpactLabel,
                            targetShip4TimeToImpactLabel, settings, outputLabel, trigonometricCalculator, basicCalculator);
                    if (targetShip4 == null) {
                        return;
                    }
                    targetShipList.add(targetShip4);
                }
                //----------5
                if (settings.getTargetShips() >= 5) {
                    targetShip5 = GuiUtils.controlInputAndCreateTargetShipAndTorpedoAndSetCalculationResult(4,
                            targetShip5ComboBoxTorpedoType, targetShip5ComboBoxTorpedoSpeed, targetShip5Speed, targetShip5Name,
                            targetShip5AngleOfBow, targetShip5Distance, targetShip5TorpedoAngleLabel, targetShip5DistanceToImpactLabel,
                            targetShip5TimeToImpactLabel, settings, outputLabel, trigonometricCalculator, basicCalculator);
                    if (targetShip5 == null) {
                        return;
                    }
                    targetShipList.add(targetShip5);
                }

                // Get the calculator defined in the settings
                List<CalculationResult> shootingInOrder = settings.getCalculator()
                        .getShootingInOrder(targetShipList);
                // todo if two ships get the same time only one is shown

                System.out.println("Ships in order: " + shootingInOrder.stream().map(t -> t.toString())
                        .collect(Collectors.joining(",")));

                // todo control shootingInOrder size

                outputLabel.setText(InputChecker.generateOutput(settings));

                // disable descriptions from previous clicks on calculation button
                disableShootingDescriptionsAndTitles();

                // Set the shooting sorting
                for (int i = 0; i < shootingInOrder.size(); i++) {
                    switch (i) {
                        case 0: {
                            GuiUtils.setShootingOrderLabels(i, shootingInOrder, firstTargetDescription,
                                    firstTargetTorpedoDescription, firstTargetTitle, null, null, settings);
                            break;
                        }
                        case 1: {
                            GuiUtils.setShootingOrderLabels(i, shootingInOrder, secondTargetDescription,
                                    secondTargetTorpedoDescription, secondTargetTitle, secondTargetWait,
                                    secondTargetCountDownLabel, settings);
                            break;
                        }
                        case 2: {
                            GuiUtils.setShootingOrderLabels(i, shootingInOrder, thirdTargetDescription,
                                    thirdTargetTorpedoDescription, thirdTargetTitle, thirdTargetWait,
                                    thirdTargetCountDownLabel, settings);
                            break;
                        }
                        case 3: {
                            GuiUtils.setShootingOrderLabels(i, shootingInOrder, fourthTargetDescription,
                                    fourthTargetTorpedoDescription, fourthTargetTitle, fourthTargetWait,
                                    fourthTargetCountDownLabel, settings);
                            break;
                        }
                        case 4: {
                            GuiUtils.setShootingOrderLabels(i, shootingInOrder, fifthTargetDescription,
                                    fifthTargetTorpedoDescription, fifthTargetTitle, fifthTargetWait,
                                    fifthTargetCountDownLabel, settings);
                            break;
                        }
                    }
                }

                if (settings.getTargetShips() > 1) {
                    // Set the timer action listener for waiting for the second target ship
                    setTimer(shootingInOrder, 0, secondTargetCountDownLabel, secondTargetFireMessage);

                    // after calculation is done the user can click on the fire button, if the timer is not running
                    if (!timer.isRunning()) {
                        uboatFireButton.setEnabled(true);
                    }
                }
            }

        };
        // Add the same action to Calculate button and pressed intro key in Distance field
        calculateButton.addActionListener(calculateAction);
        //distance
        targetShip1Distance.addActionListener(calculateAction);
        targetShip2Distance.addActionListener(calculateAction);
        targetShip3Distance.addActionListener(calculateAction);
        targetShip4Distance.addActionListener(calculateAction);
        targetShip5Distance.addActionListener(calculateAction);
        // speed
        targetShip1Speed.addActionListener(calculateAction);
        targetShip2Speed.addActionListener(calculateAction);
        targetShip3Speed.addActionListener(calculateAction);
        targetShip4Speed.addActionListener(calculateAction);
        targetShip5Speed.addActionListener(calculateAction);
        // angle of bow
        targetShip1AngleOfBow.addActionListener(calculateAction);
        targetShip2AngleOfBow.addActionListener(calculateAction);
        targetShip3AngleOfBow.addActionListener(calculateAction);
        targetShip4AngleOfBow.addActionListener(calculateAction);
        targetShip5AngleOfBow.addActionListener(calculateAction);

        // Set action listener for the first torpedo button. The timer for the second torpedo will start.
        setUboatFireButtonAction();

        // Hyperlinks
        angleOfBowLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=kqVO-ESAm8s"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                angleOfBowLink.setFont(new Font("Consolas", Font.PLAIN, 16));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                angleOfBowLink.setFont(new Font("Consolas", Font.PLAIN, 18));
            }
        });
        redditLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.reddit.com/r/uboatgame/comments/tukmrx/i_present_to_you_my_multi_ship_targeting_computer/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                redditLink.setText("Reddit");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                redditLink.setText("<html><a href=''>" + "Reddit" + "</a></html>");
            }
        });
    }

    private void setTimer(List<CalculationResult> shootingInOrder, final int index, JLabel targetCountDownLabel, JLabel targetFireMessage) {
        // Set timers
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 0 is the one with a longer time
                double secondsToWaitForTarget =
                        shootingInOrder.get(index).getTimeToImpact() - shootingInOrder.get(index + 1).getTimeToImpact();
                duration = (long) secondsToWaitForTarget * 1000; // duration in milliseconds
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                    targetFireMessage.setText("FIRE!!!");
                    // System.out.println("Stop Index: " + index);
                }
                // System.out.println("Index: " + index);
                // System.out.println("Clock time: " + clockTime);
                // System.out.println("Duration: " + duration);
                if (duration - clockTime <= 2000 && !reproducingClipAboutTime) {
                    GuiUtils.reproduceTwoSecondsClip(getClass());
                    reproducingClipAboutTime = true;
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
                targetCountDownLabel.setText(df.format(duration - clockTime));

                // start the time with the launch of the current torpedo to wait for the next one.
                if (clockTime >= duration && index + 2 < shootingInOrder.size()) {
                    // System.out.println("Call Next launch");
                    setNextLaunch(shootingInOrder, index + 1);
                }
            }
        });
        timer.setInitialDelay(0);

    }

    private void setNextLaunch(List<CalculationResult> shootingInOrder, int index) {
        // initialize things
        reproducingClipAboutTime = false; // initialize reminder clip boolean

        // System.out.println("Next launch, index " + index);

        // 0, timer was set when the calculate button was pressed
        switch (index) {
            case 1: {
                setTimer(shootingInOrder, 1, thirdTargetCountDownLabel, thirdTargetFireMessage);
                break;
            }
            case 2: {
                setTimer(shootingInOrder, 2, fourthTargetCountDownLabel, fourthTargetFireMessage);
                break;
            }
            case 3: {
                setTimer(shootingInOrder, 3, fifthTargetCountDownLabel, fifthTargetFireMessage);
                break;
            }
            case 4: {
                setTimer(shootingInOrder, 4, fifthTargetCountDownLabel, fifthTargetFireMessage);
                break;
            }
        }

        // Start count down (after the new timer has been set)
        if (!timer.isRunning()) {
            // System.out.println("Next launch, is not running");
            startTime = -1;
            timer.start();
        }

    }

    private void setUboatFireButtonAction() {
        // Fire button
        uboatFireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // disable the button, it will be enabled when calculation is re-done
                uboatFireButton.setEnabled(false);

                // initialize things
                reproducingClipAboutTime = false; // initialize reminder clip boolean
                secondTargetFireMessage.setText("");
                thirdTargetFireMessage.setText("");
                fourthTargetFireMessage.setText("");
                fifthTargetFireMessage.setText("");

                // reproduce clip
                GuiUtils.reproduceTwoSecondsClip(getClass());

                // sleep 2 seconds, the clip duration
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Start count down
                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }
            }
        });
    }

    private void disableShootingDescriptionsAndTitles() {
        firstTargetTitle.setEnabled(false);
        secondTargetWait.setEnabled(false);
        secondTargetCountDownLabel.setEnabled(false);
        firstTargetDescription.setEnabled(false);
        firstTargetTorpedoDescription.setEnabled(false);

        secondTargetTitle.setEnabled(false);
        thirdTargetWait.setEnabled(false);
        thirdTargetCountDownLabel.setEnabled(false);
        secondTargetDescription.setEnabled(false);
        secondTargetTorpedoDescription.setEnabled(false);
        secondTargetFireMessage.setText("");

        thirdTargetTitle.setEnabled(false);
        fourthTargetWait.setEnabled(false);
        fourthTargetCountDownLabel.setEnabled(false);
        thirdTargetDescription.setEnabled(false);
        thirdTargetTorpedoDescription.setEnabled(false);
        thirdTargetFireMessage.setText("");


        fourthTargetTitle.setEnabled(false);
        fifthTargetWait.setEnabled(false);
        fifthTargetCountDownLabel.setEnabled(false);
        fourthTargetDescription.setEnabled(false);
        fourthTargetTorpedoDescription.setEnabled(false);
        fourthTargetFireMessage.setText("");


        fifthTargetTitle.setEnabled(false);
        fifthTargetDescription.setEnabled(false);
        fifthTargetTorpedoDescription.setEnabled(false);
        fifthTargetFireMessage.setText("");

    }

    private void updateFieldLabelsAllTabs(Settings.UNITS_SYSTEM metric) {
        switch (metric) {
            case METRIC -> {
                //1
                targetShip1SpeedLabel.setText("Speed (km/h)");
                targetShip1DistanceLabel.setText("Distance (km)");
                targetShip1TorpedoSpeedLabel.setText("Speed (km/h)");
                targetShip1TorpedoRangeLabel.setText("Distance (km)");
                targetShip1DistanceToImpactText.setText("Distance to impact (km)");
                if (!Strings.isNullOrEmpty(targetShip1Distance.getText())) {
                    targetShip1TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip1Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip1TorpedoesToBeDisplayed,
                            targetShip1ComboBoxTorpedoType, targetShip1ComboBoxTorpedoSpeed,
                            targetShip1TorpedoRangeLabel, settings);
                }
                // 2
                targetShip2SpeedLabel.setText("Speed (km/h)");
                targetShip2DistanceLabel.setText("Distance (km)");
                targetShip2TorpedoSpeedLabel.setText("Speed (km/h)");
                targetShip2TorpedoRangeLabel.setText("Distance (km)");
                targetShip2DistanceToImpactText.setText("Distance to impact (km)");
                if (!Strings.isNullOrEmpty(targetShip2Distance.getText())) {
                    targetShip2TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip2Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip2TorpedoesToBeDisplayed,
                            targetShip2ComboBoxTorpedoType, targetShip2ComboBoxTorpedoSpeed,
                            targetShip2TorpedoRangeLabel, settings);
                }
                // 3
                targetShip3SpeedLabel.setText("Speed (km/h)");
                targetShip3DistanceLabel.setText("Distance (km)");
                targetShip3TorpedoSpeedLabel.setText("Speed (km/h)");
                targetShip3TorpedoRangeLabel.setText("Distance (km)");
                targetShip3DistanceToImpactText.setText("Distance to impact (km)");
                if (!Strings.isNullOrEmpty(targetShip3Distance.getText())) {
                    targetShip3TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip3Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip3TorpedoesToBeDisplayed,
                            targetShip3ComboBoxTorpedoType,targetShip3ComboBoxTorpedoSpeed,
                            targetShip3TorpedoRangeLabel, settings);
                }
                // 4
                targetShip4SpeedLabel.setText("Speed (km/h)");
                targetShip4DistanceLabel.setText("Distance (km)");
                targetShip4TorpedoSpeedLabel.setText("Speed (km/h)");
                targetShip4TorpedoRangeLabel.setText("Distance (km)");
                targetShip4DistanceToImpactText.setText("Distance to impact (km)");
                if (!Strings.isNullOrEmpty(targetShip4Distance.getText())) {
                    targetShip4TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip4Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip4TorpedoesToBeDisplayed,
                            targetShip4ComboBoxTorpedoType, targetShip4ComboBoxTorpedoSpeed,
                            targetShip4TorpedoRangeLabel, settings);
                }
                // 5
                targetShip5SpeedLabel.setText("Speed (km/h)");
                targetShip5DistanceLabel.setText("Distance (km)");
                targetShip5TorpedoSpeedLabel.setText("Speed (km/h)");
                targetShip5TorpedoRangeLabel.setText("Distance (km)");
                targetShip5DistanceToImpactText.setText("Distance to impact (km)");
                if (!Strings.isNullOrEmpty(targetShip5Distance.getText())) {
                    targetShip5TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip5Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip5TorpedoesToBeDisplayed,
                            targetShip5ComboBoxTorpedoType,targetShip5ComboBoxTorpedoSpeed,
                            targetShip5TorpedoRangeLabel, settings);
                }
                break;
            }
            case MIXED -> {
                // 1
                targetShip1SpeedLabel.setText("Speed (knots)");
                targetShip1DistanceLabel.setText("Distance (km)");
                targetShip1TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip1TorpedoRangeLabel.setText("Distance (km)");
                targetShip1DistanceToImpactText.setText("Distance to impact (km)");

                if (!Strings.isNullOrEmpty(targetShip1Distance.getText())) {
                    targetShip1TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip1Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip1TorpedoesToBeDisplayed,
                            targetShip1ComboBoxTorpedoType, targetShip1ComboBoxTorpedoSpeed,
                            targetShip1TorpedoRangeLabel, settings);
                }
                // 2
                targetShip2SpeedLabel.setText("Speed (knots)");
                targetShip2DistanceLabel.setText("Distance (km)");
                targetShip2TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip2TorpedoRangeLabel.setText("Distance (km)");
                targetShip2DistanceToImpactText.setText("Distance to impact (km)");

                if (!Strings.isNullOrEmpty(targetShip2Distance.getText())) {
                    targetShip2TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip2Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip2TorpedoesToBeDisplayed,
                            targetShip2ComboBoxTorpedoType, targetShip2ComboBoxTorpedoSpeed,
                            targetShip2TorpedoRangeLabel, settings);
                }

                // 3
                targetShip3SpeedLabel.setText("Speed (knots)");
                targetShip3DistanceLabel.setText("Distance (km)");
                targetShip3TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip3TorpedoRangeLabel.setText("Distance (km)");
                targetShip3DistanceToImpactText.setText("Distance to impact (km)");

                if (!Strings.isNullOrEmpty(targetShip3Distance.getText())) {
                    targetShip3TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip3Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip3TorpedoesToBeDisplayed,
                            targetShip3ComboBoxTorpedoType, targetShip3ComboBoxTorpedoSpeed,
                            targetShip3TorpedoRangeLabel, settings);
                }

                // 4
                targetShip4SpeedLabel.setText("Speed (knots)");
                targetShip4DistanceLabel.setText("Distance (km)");
                targetShip4TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip4TorpedoRangeLabel.setText("Distance (km)");
                targetShip4DistanceToImpactText.setText("Distance to impact (km)");

                if (!Strings.isNullOrEmpty(targetShip4Distance.getText())) {
                    targetShip4TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip4Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip4TorpedoesToBeDisplayed,
                            targetShip4ComboBoxTorpedoType, targetShip4ComboBoxTorpedoSpeed,
                            targetShip4TorpedoRangeLabel, settings);
                }

                // 5
                targetShip5SpeedLabel.setText("Speed (knots)");
                targetShip5DistanceLabel.setText("Distance (km)");
                targetShip5TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip5TorpedoRangeLabel.setText("Distance (km)");
                targetShip5DistanceToImpactText.setText("Distance to impact (km)");

                if (!Strings.isNullOrEmpty(targetShip5Distance.getText())) {
                    targetShip5TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip5Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip5TorpedoesToBeDisplayed,
                            targetShip5ComboBoxTorpedoType, targetShip5ComboBoxTorpedoSpeed,
                            targetShip5TorpedoRangeLabel, settings);
                }
                break;
            }
            case NAUTICAL -> {
                // 1
                targetShip1SpeedLabel.setText("Speed (knots)");
                targetShip1DistanceLabel.setText("Distance (nmi)");
                targetShip1TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip1TorpedoRangeLabel.setText("Distance (nmi)");
                targetShip1DistanceToImpactText.setText("Distance to impact (nmi)");

                if (!Strings.isNullOrEmpty(targetShip1Distance.getText())) {
                    targetShip1TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip1Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip1TorpedoesToBeDisplayed,
                            targetShip1ComboBoxTorpedoType, targetShip1ComboBoxTorpedoSpeed,
                            targetShip1TorpedoRangeLabel, settings);
                }

                // 2
                targetShip2SpeedLabel.setText("Speed (knots)");
                targetShip2DistanceLabel.setText("Distance (nmi)");
                targetShip2TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip2TorpedoRangeLabel.setText("Distance (nmi)");
                targetShip2DistanceToImpactText.setText("Distance to impact (nmi)");
                if (!Strings.isNullOrEmpty(targetShip2Distance.getText())) {
                    targetShip2TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip2Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip2TorpedoesToBeDisplayed,
                            targetShip2ComboBoxTorpedoType, targetShip2ComboBoxTorpedoSpeed,
                            targetShip2TorpedoRangeLabel, settings);
                }

                // 3
                targetShip3SpeedLabel.setText("Speed (knots)");
                targetShip3DistanceLabel.setText("Distance (nmi)");
                targetShip3TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip3TorpedoRangeLabel.setText("Distance (nmi)");
                targetShip3DistanceToImpactText.setText("Distance to impact (nmi)");
                if (!Strings.isNullOrEmpty(targetShip3Distance.getText())) {
                    targetShip3TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip3Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip3TorpedoesToBeDisplayed,
                            targetShip3ComboBoxTorpedoType, targetShip3ComboBoxTorpedoSpeed,
                            targetShip3TorpedoRangeLabel, settings);
                }

                // 4
                targetShip4SpeedLabel.setText("Speed (knots)");
                targetShip4DistanceLabel.setText("Distance (nmi)");
                targetShip4TorpedoSpeedLabel.setText("Speed (knots)");
                targetShip4TorpedoRangeLabel.setText("Distance (nmi)");
                targetShip4DistanceToImpactText.setText("Distance to impact (nmi)");
                if (!Strings.isNullOrEmpty(targetShip4Distance.getText())) {
                    targetShip4TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                            InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                    Double.parseDouble(targetShip4Distance.getText())));

                    GuiUtils.enableTorpedoesDependingOnRange(targetShip4TorpedoesToBeDisplayed,
                            targetShip4ComboBoxTorpedoType, targetShip4ComboBoxTorpedoSpeed,
                            targetShip4TorpedoRangeLabel, settings);

                    // 5
                    targetShip5SpeedLabel.setText("Speed (knots)");
                    targetShip5DistanceLabel.setText("Distance (nmi)");
                    targetShip5TorpedoSpeedLabel.setText("Speed (knots)");
                    targetShip5TorpedoRangeLabel.setText("Distance (nmi)");
                    targetShip5DistanceToImpactText.setText("Distance to impact (nmi)");
                    if (!Strings.isNullOrEmpty(targetShip5Distance.getText())) {
                        targetShip5TorpedoesToBeDisplayed = GuiUtils.calculateTorpedoesToBeDisplayed(
                                InputOutputParser.parseInputDistance(settings.getUnitSystem(),
                                        Double.parseDouble(targetShip5Distance.getText())));

                        GuiUtils.enableTorpedoesDependingOnRange(targetShip5TorpedoesToBeDisplayed,
                                targetShip5ComboBoxTorpedoType, targetShip5ComboBoxTorpedoSpeed,
                                targetShip5TorpedoRangeLabel, settings);
                    }
                }
                break;
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setInheritsPopupMenu(false);
        mainPanel.setMaximumSize(new Dimension(-1, -1));
        mainPanel.setMinimumSize(new Dimension(-1, -1));
        mainPanel.setOpaque(true);
        mainPanel.setPreferredSize(new Dimension(620, 590));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayoutManager(1, 6, new Insets(10, 15, 10, 15), -1, -1));
        mainPanel.add(settingsPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        settingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        metricRadioButton = new JRadioButton();
        metricRadioButton.setSelected(true);
        metricRadioButton.setText("Metric (km, km/h)");
        settingsPanel.add(metricRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerTargetShips = new JSpinner();
        settingsPanel.add(spinnerTargetShips, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mixedRadioButton = new JRadioButton();
        mixedRadioButton.setText("Mixed (km, knots)");
        settingsPanel.add(mixedRadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Target Ships");
        settingsPanel.add(label1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        settingsPanel.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        nauticalRadioButton = new JRadioButton();
        nauticalRadioButton.setText("Nautical (nmi, knots)");
        settingsPanel.add(nauticalRadioButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetInfoPanel = new JPanel();
        targetInfoPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(targetInfoPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tabbedPanel = new JTabbedPane();
        tabbedPanel.setVisible(true);
        targetInfoPanel.add(tabbedPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 220), null, 0, false));
        panelShip1 = new JPanel();
        panelShip1.setLayout(new GridLayoutManager(8, 10, new Insets(10, 15, 10, 15), -1, -1));
        panelShip1.setMaximumSize(new Dimension(-1, -1));
        panelShip1.setMinimumSize(new Dimension(-1, -1));
        panelShip1.setPreferredSize(new Dimension(-1, -1));
        tabbedPanel.addTab("Target Ship 1", panelShip1);
        final JLabel label2 = new JLabel();
        label2.setText("Name (optional)");
        panelShip1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Angle of Bow (degrees)");
        panelShip1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1DistanceLabel = new JLabel();
        targetShip1DistanceLabel.setText("Distance (km)");
        panelShip1.add(targetShip1DistanceLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Torpedo(es)");
        panelShip1.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Time to impact");
        panelShip1.add(label5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1Name = new JTextField();
        targetShip1Name.setToolTipText("i.e Empire Bell Class");
        panelShip1.add(targetShip1Name, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip1Speed = new JTextField();
        panelShip1.add(targetShip1Speed, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip1AngleOfBow = new JTextField();
        panelShip1.add(targetShip1AngleOfBow, new GridConstraints(2, 1, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip1Distance = new JTextField();
        panelShip1.add(targetShip1Distance, new GridConstraints(3, 1, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip1ComboBoxTorpedoType = new JComboBox();
        targetShip1ComboBoxTorpedoType.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        targetShip1ComboBoxTorpedoType.setModel(defaultComboBoxModel1);
        panelShip1.add(targetShip1ComboBoxTorpedoType, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 26), null, null, 0, false));
        targetShip1SpeedLabel = new JLabel();
        targetShip1SpeedLabel.setText("Speed (km/h)");
        panelShip1.add(targetShip1SpeedLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1TimeToImpactLabel = new JLabel();
        targetShip1TimeToImpactLabel.setText("");
        panelShip1.add(targetShip1TimeToImpactLabel, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1DistanceToImpactText = new JLabel();
        targetShip1DistanceToImpactText.setText("Distance to impact (km)");
        panelShip1.add(targetShip1DistanceToImpactText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1DistanceToImpactLabel = new JLabel();
        targetShip1DistanceToImpactLabel.setText("");
        panelShip1.add(targetShip1DistanceToImpactLabel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Torpedo Angle (degrees)");
        panelShip1.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1TorpedoAngleLabel = new JLabel();
        targetShip1TorpedoAngleLabel.setText("");
        panelShip1.add(targetShip1TorpedoAngleLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelShip1.add(spacer2, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        targetShip1TorpedoSpeedLabel = new JLabel();
        targetShip1TorpedoSpeedLabel.setText("Speed (km/h)");
        panelShip1.add(targetShip1TorpedoSpeedLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 16), null, 0, false));
        targetShip1ComboBoxTorpedoSpeed = new JComboBox();
        targetShip1ComboBoxTorpedoSpeed.setEnabled(false);
        panelShip1.add(targetShip1ComboBoxTorpedoSpeed, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(55, 25), null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("");
        panelShip1.add(label7, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelShip1.add(spacer3, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Range");
        panelShip1.add(label8, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip1TorpedoRangeLabel = new JLabel();
        targetShip1TorpedoRangeLabel.setText("");
        panelShip1.add(targetShip1TorpedoRangeLabel, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelShip2 = new JPanel();
        panelShip2.setLayout(new GridLayoutManager(8, 10, new Insets(10, 15, 10, 15), -1, -1));
        panelShip2.setMaximumSize(new Dimension(-1, -1));
        panelShip2.setMinimumSize(new Dimension(-1, -1));
        panelShip2.setPreferredSize(new Dimension(-1, -1));
        tabbedPanel.addTab("Target Ship 2", panelShip2);
        final JLabel label9 = new JLabel();
        label9.setText("Name (optional)");
        panelShip2.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Angle of Bow (degrees)");
        panelShip2.add(label10, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2DistanceLabel = new JLabel();
        targetShip2DistanceLabel.setText("Distance (km)");
        panelShip2.add(targetShip2DistanceLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Torpedo(es)");
        panelShip2.add(label11, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Time to impact");
        panelShip2.add(label12, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2Name = new JTextField();
        targetShip2Name.setToolTipText("i.e Empire Bell Class");
        panelShip2.add(targetShip2Name, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip2Speed = new JTextField();
        panelShip2.add(targetShip2Speed, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip2AngleOfBow = new JTextField();
        panelShip2.add(targetShip2AngleOfBow, new GridConstraints(2, 1, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip2Distance = new JTextField();
        panelShip2.add(targetShip2Distance, new GridConstraints(3, 1, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip2ComboBoxTorpedoType = new JComboBox();
        targetShip2ComboBoxTorpedoType.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        targetShip2ComboBoxTorpedoType.setModel(defaultComboBoxModel2);
        panelShip2.add(targetShip2ComboBoxTorpedoType, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 26), null, null, 0, false));
        targetShip2SpeedLabel = new JLabel();
        targetShip2SpeedLabel.setText("Speed (km/h)");
        panelShip2.add(targetShip2SpeedLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2TimeToImpactLabel = new JLabel();
        targetShip2TimeToImpactLabel.setText("");
        panelShip2.add(targetShip2TimeToImpactLabel, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2DistanceToImpactText = new JLabel();
        targetShip2DistanceToImpactText.setText("Distance to impact (km)");
        panelShip2.add(targetShip2DistanceToImpactText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2DistanceToImpactLabel = new JLabel();
        targetShip2DistanceToImpactLabel.setText("");
        panelShip2.add(targetShip2DistanceToImpactLabel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Torpedo Angle (degrees)");
        panelShip2.add(label13, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2TorpedoAngleLabel = new JLabel();
        targetShip2TorpedoAngleLabel.setText("");
        panelShip2.add(targetShip2TorpedoAngleLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelShip2.add(spacer4, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        targetShip2TorpedoSpeedLabel = new JLabel();
        targetShip2TorpedoSpeedLabel.setText("Speed (km/h)");
        panelShip2.add(targetShip2TorpedoSpeedLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 16), null, 0, false));
        targetShip2ComboBoxTorpedoSpeed = new JComboBox();
        targetShip2ComboBoxTorpedoSpeed.setEnabled(false);
        panelShip2.add(targetShip2ComboBoxTorpedoSpeed, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(55, 25), null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("");
        panelShip2.add(label14, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelShip2.add(spacer5, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Range");
        panelShip2.add(label15, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip2TorpedoRangeLabel = new JLabel();
        targetShip2TorpedoRangeLabel.setText("");
        panelShip2.add(targetShip2TorpedoRangeLabel, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelShip3 = new JPanel();
        panelShip3.setLayout(new GridLayoutManager(8, 10, new Insets(10, 15, 10, 15), -1, -1));
        panelShip3.setMaximumSize(new Dimension(-1, -1));
        panelShip3.setMinimumSize(new Dimension(-1, -1));
        panelShip3.setPreferredSize(new Dimension(-1, -1));
        tabbedPanel.addTab("Target Ship 3", panelShip3);
        final JLabel label16 = new JLabel();
        label16.setText("Name (optional)");
        panelShip3.add(label16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Angle of Bow (degrees)");
        panelShip3.add(label17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3DistanceLabel = new JLabel();
        targetShip3DistanceLabel.setText("Distance (km)");
        panelShip3.add(targetShip3DistanceLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Torpedo(es)");
        panelShip3.add(label18, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Time to impact");
        panelShip3.add(label19, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3Name = new JTextField();
        targetShip3Name.setText("");
        targetShip3Name.setToolTipText("i.e Empire Bell Class");
        panelShip3.add(targetShip3Name, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip3Speed = new JTextField();
        panelShip3.add(targetShip3Speed, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip3AngleOfBow = new JTextField();
        panelShip3.add(targetShip3AngleOfBow, new GridConstraints(2, 1, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip3Distance = new JTextField();
        panelShip3.add(targetShip3Distance, new GridConstraints(3, 1, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip3ComboBoxTorpedoType = new JComboBox();
        targetShip3ComboBoxTorpedoType.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        targetShip3ComboBoxTorpedoType.setModel(defaultComboBoxModel3);
        panelShip3.add(targetShip3ComboBoxTorpedoType, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 26), null, null, 0, false));
        targetShip3SpeedLabel = new JLabel();
        targetShip3SpeedLabel.setText("Speed (km/h)");
        panelShip3.add(targetShip3SpeedLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3TimeToImpactLabel = new JLabel();
        targetShip3TimeToImpactLabel.setText("");
        panelShip3.add(targetShip3TimeToImpactLabel, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3DistanceToImpactText = new JLabel();
        targetShip3DistanceToImpactText.setText("Distance to impact (km)");
        panelShip3.add(targetShip3DistanceToImpactText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3DistanceToImpactLabel = new JLabel();
        targetShip3DistanceToImpactLabel.setText("");
        panelShip3.add(targetShip3DistanceToImpactLabel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Torpedo Angle (degrees)");
        panelShip3.add(label20, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3TorpedoAngleLabel = new JLabel();
        targetShip3TorpedoAngleLabel.setText("");
        panelShip3.add(targetShip3TorpedoAngleLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelShip3.add(spacer6, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        targetShip3TorpedoSpeedLabel = new JLabel();
        targetShip3TorpedoSpeedLabel.setText("Speed (km/h)");
        panelShip3.add(targetShip3TorpedoSpeedLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 16), null, 0, false));
        targetShip3ComboBoxTorpedoSpeed = new JComboBox();
        targetShip3ComboBoxTorpedoSpeed.setEnabled(false);
        panelShip3.add(targetShip3ComboBoxTorpedoSpeed, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(55, 25), null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("");
        panelShip3.add(label21, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panelShip3.add(spacer7, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Range");
        panelShip3.add(label22, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip3TorpedoRangeLabel = new JLabel();
        targetShip3TorpedoRangeLabel.setText("");
        panelShip3.add(targetShip3TorpedoRangeLabel, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelShip4 = new JPanel();
        panelShip4.setLayout(new GridLayoutManager(8, 10, new Insets(10, 15, 10, 15), -1, -1));
        panelShip4.setMaximumSize(new Dimension(-1, -1));
        panelShip4.setMinimumSize(new Dimension(-1, -1));
        panelShip4.setPreferredSize(new Dimension(-1, -1));
        tabbedPanel.addTab("Targe Ship 4", panelShip4);
        final JLabel label23 = new JLabel();
        label23.setText("Name (optional)");
        panelShip4.add(label23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        label24.setText("Angle of Bow (degrees)");
        panelShip4.add(label24, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4DistanceLabel = new JLabel();
        targetShip4DistanceLabel.setText("Distance (km)");
        panelShip4.add(targetShip4DistanceLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        label25.setText("Torpedo(es)");
        panelShip4.add(label25, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        label26.setText("Time to impact");
        panelShip4.add(label26, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4Name = new JTextField();
        targetShip4Name.setToolTipText("i.e Empire Bell Class");
        panelShip4.add(targetShip4Name, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip4Speed = new JTextField();
        targetShip4Speed.setText("");
        panelShip4.add(targetShip4Speed, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip4AngleOfBow = new JTextField();
        panelShip4.add(targetShip4AngleOfBow, new GridConstraints(2, 1, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip4Distance = new JTextField();
        panelShip4.add(targetShip4Distance, new GridConstraints(3, 1, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip4ComboBoxTorpedoType = new JComboBox();
        targetShip4ComboBoxTorpedoType.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        targetShip4ComboBoxTorpedoType.setModel(defaultComboBoxModel4);
        panelShip4.add(targetShip4ComboBoxTorpedoType, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 26), null, null, 0, false));
        targetShip4SpeedLabel = new JLabel();
        targetShip4SpeedLabel.setText("Speed (km/h)");
        panelShip4.add(targetShip4SpeedLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4TimeToImpactLabel = new JLabel();
        targetShip4TimeToImpactLabel.setText("");
        panelShip4.add(targetShip4TimeToImpactLabel, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4DistanceToImpactText = new JLabel();
        targetShip4DistanceToImpactText.setText("Distance to impact (km)");
        panelShip4.add(targetShip4DistanceToImpactText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4DistanceToImpactLabel = new JLabel();
        targetShip4DistanceToImpactLabel.setText("");
        panelShip4.add(targetShip4DistanceToImpactLabel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        label27.setText("Torpedo Angle (degrees)");
        panelShip4.add(label27, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4TorpedoAngleLabel = new JLabel();
        targetShip4TorpedoAngleLabel.setText("");
        panelShip4.add(targetShip4TorpedoAngleLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panelShip4.add(spacer8, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        targetShip4TorpedoSpeedLabel = new JLabel();
        targetShip4TorpedoSpeedLabel.setText("Speed (km/h)");
        panelShip4.add(targetShip4TorpedoSpeedLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 16), null, 0, false));
        targetShip4ComboBoxTorpedoSpeed = new JComboBox();
        targetShip4ComboBoxTorpedoSpeed.setEnabled(false);
        panelShip4.add(targetShip4ComboBoxTorpedoSpeed, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(55, 25), null, null, 0, false));
        final JLabel label28 = new JLabel();
        label28.setText("");
        panelShip4.add(label28, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panelShip4.add(spacer9, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label29 = new JLabel();
        label29.setText("Range");
        panelShip4.add(label29, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip4TorpedoRangeLabel = new JLabel();
        targetShip4TorpedoRangeLabel.setText("");
        panelShip4.add(targetShip4TorpedoRangeLabel, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelShip5 = new JPanel();
        panelShip5.setLayout(new GridLayoutManager(8, 10, new Insets(10, 15, 10, 15), -1, -1));
        panelShip5.setMaximumSize(new Dimension(-1, -1));
        panelShip5.setMinimumSize(new Dimension(-1, -1));
        panelShip5.setPreferredSize(new Dimension(-1, -1));
        tabbedPanel.addTab("Target Ship 5", panelShip5);
        final JLabel label30 = new JLabel();
        label30.setText("Name (optional)");
        panelShip5.add(label30, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label31 = new JLabel();
        label31.setText("Angle of Bow (degrees)");
        panelShip5.add(label31, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5DistanceLabel = new JLabel();
        targetShip5DistanceLabel.setText("Distance (km)");
        panelShip5.add(targetShip5DistanceLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label32 = new JLabel();
        label32.setText("Torpedo(es)");
        panelShip5.add(label32, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label33 = new JLabel();
        label33.setText("Time to impact:");
        panelShip5.add(label33, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5Name = new JTextField();
        targetShip5Name.setToolTipText("i.e Empire Bell Class");
        panelShip5.add(targetShip5Name, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip5Speed = new JTextField();
        panelShip5.add(targetShip5Speed, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip5AngleOfBow = new JTextField();
        panelShip5.add(targetShip5AngleOfBow, new GridConstraints(2, 1, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip5Distance = new JTextField();
        panelShip5.add(targetShip5Distance, new GridConstraints(3, 1, 1, 7, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetShip5ComboBoxTorpedoType = new JComboBox();
        targetShip5ComboBoxTorpedoType.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        targetShip5ComboBoxTorpedoType.setModel(defaultComboBoxModel5);
        panelShip5.add(targetShip5ComboBoxTorpedoType, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 26), null, null, 0, false));
        targetShip5SpeedLabel = new JLabel();
        targetShip5SpeedLabel.setText("Speed (km/h)");
        panelShip5.add(targetShip5SpeedLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5TimeToImpactLabel = new JLabel();
        targetShip5TimeToImpactLabel.setText("");
        panelShip5.add(targetShip5TimeToImpactLabel, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5DistanceToImpactText = new JLabel();
        targetShip5DistanceToImpactText.setText("Distance to impact (km)");
        panelShip5.add(targetShip5DistanceToImpactText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5DistanceToImpactLabel = new JLabel();
        targetShip5DistanceToImpactLabel.setText("");
        panelShip5.add(targetShip5DistanceToImpactLabel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label34 = new JLabel();
        label34.setText("Torpedo Angle (degrees)");
        panelShip5.add(label34, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5TorpedoAngleLabel = new JLabel();
        targetShip5TorpedoAngleLabel.setText("");
        panelShip5.add(targetShip5TorpedoAngleLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panelShip5.add(spacer10, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        targetShip5TorpedoSpeedLabel = new JLabel();
        targetShip5TorpedoSpeedLabel.setText("Speed (km/h)");
        panelShip5.add(targetShip5TorpedoSpeedLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 16), null, 0, false));
        targetShip5ComboBoxTorpedoSpeed = new JComboBox();
        targetShip5ComboBoxTorpedoSpeed.setEnabled(false);
        panelShip5.add(targetShip5ComboBoxTorpedoSpeed, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(55, 25), null, null, 0, false));
        final JLabel label35 = new JLabel();
        label35.setText("");
        panelShip5.add(label35, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panelShip5.add(spacer11, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label36 = new JLabel();
        label36.setText("Range");
        panelShip5.add(label36, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetShip5TorpedoRangeLabel = new JLabel();
        targetShip5TorpedoRangeLabel.setText("");
        panelShip5.add(targetShip5TorpedoRangeLabel, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelInfo.setVisible(false);
        tabbedPanel.addTab("Help", panelInfo);
        panelInfoScroll = new JScrollPane();
        panelInfo.add(panelInfoScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 1, false));
        panelInfoFlow = new JPanel();
        panelInfoFlow.setLayout(new GridBagLayout());
        panelInfoScroll.setViewportView(panelInfoFlow);
        angleOfBowTitleTextArea = new JTextArea();
        angleOfBowTitleTextArea.setEditable(false);
        Font angleOfBowTitleTextAreaFont = this.$$$getFont$$$(null, Font.BOLD, 16, angleOfBowTitleTextArea.getFont());
        if (angleOfBowTitleTextAreaFont != null) angleOfBowTitleTextArea.setFont(angleOfBowTitleTextAreaFont);
        angleOfBowTitleTextArea.setText("How to calculate Angle of Bow");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelInfoFlow.add(angleOfBowTitleTextArea, gbc);
        angleOfBowContent1TextArea = new JTextArea();
        angleOfBowContent1TextArea.setEditable(false);
        angleOfBowContent1TextArea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelInfoFlow.add(angleOfBowContent1TextArea, gbc);
        angleOfBowContent2TextArea = new JTextArea();
        angleOfBowContent2TextArea.setEditable(false);
        angleOfBowContent2TextArea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelInfoFlow.add(angleOfBowContent2TextArea, gbc);
        angleOfBowContent3TextArea = new JTextArea();
        angleOfBowContent3TextArea.setEditable(false);
        angleOfBowContent3TextArea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelInfoFlow.add(angleOfBowContent3TextArea, gbc);
        firstImageLabel = new JLabel();
        firstImageLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelInfoFlow.add(firstImageLabel, gbc);
        secondLabelImage = new JLabel();
        secondLabelImage.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelInfoFlow.add(secondLabelImage, gbc);
        thirdLabelImage = new JLabel();
        thirdLabelImage.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelInfoFlow.add(thirdLabelImage, gbc);
        angleOfBowLink = new JTextArea();
        angleOfBowLink.setEditable(false);
        Font angleOfBowLinkFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, angleOfBowLink.getFont());
        if (angleOfBowLinkFont != null) angleOfBowLink.setFont(angleOfBowLinkFont);
        angleOfBowLink.setText("Angle of Bow");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelInfoFlow.add(angleOfBowLink, gbc);
        calculationPanel = new JPanel();
        calculationPanel.setLayout(new GridLayoutManager(2, 1, new Insets(10, 15, 10, 15), -1, -1));
        mainPanel.add(calculationPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        calculationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-4473925)));
        calculationMethodPanel = new JPanel();
        calculationMethodPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        calculationPanel.add(calculationMethodPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        basicCalculationRadioButton = new JRadioButton();
        basicCalculationRadioButton.setText("Basic calculation");
        basicCalculationRadioButton.setToolTipText("Basic calculation only uses the distance from the sub to the ship");
        calculationMethodPanel.add(basicCalculationRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        trigonometricCalculationRadioButton = new JRadioButton();
        trigonometricCalculationRadioButton.setSelected(true);
        trigonometricCalculationRadioButton.setText("Trigonometric calculation");
        trigonometricCalculationRadioButton.setToolTipText("Trig calculation calculates distance to impact");
        calculationMethodPanel.add(trigonometricCalculationRadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        calculateButton = new JButton();
        calculateButton.setText("CALCULATE");
        calculationMethodPanel.add(calculateButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        calculationMethodPanel.add(spacer12, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        calculationMethodPanel.add(spacer13, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        calculationOutputPanel = new JPanel();
        calculationOutputPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        calculationPanel.add(calculationOutputPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label37 = new JLabel();
        label37.setText("Output:");
        calculationOutputPanel.add(label37, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputLabel = new JLabel();
        outputLabel.setText("");
        calculationOutputPanel.add(outputLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayoutManager(9, 5, new Insets(10, 15, 10, 15), -1, -1));
        mainPanel.add(resultsPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label38 = new JLabel();
        label38.setText("Shooting Order");
        resultsPanel.add(label38, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        resultsPanel.add(spacer14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        firstTargetTitle = new JLabel();
        firstTargetTitle.setEnabled(false);
        Font firstTargetTitleFont = this.$$$getFont$$$(null, Font.BOLD, -1, firstTargetTitle.getFont());
        if (firstTargetTitleFont != null) firstTargetTitle.setFont(firstTargetTitleFont);
        firstTargetTitle.setText("First target");
        resultsPanel.add(firstTargetTitle, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstTargetDescription = new JLabel();
        firstTargetDescription.setText("");
        resultsPanel.add(firstTargetDescription, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uboatFireButton = new JButton();
        uboatFireButton.setEnabled(false);
        uboatFireButton.setText("Uboat fire in 2 seconds");
        resultsPanel.add(uboatFireButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetWait = new JLabel();
        secondTargetWait.setEnabled(false);
        secondTargetWait.setText("Wait");
        resultsPanel.add(secondTargetWait, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetCountDownLabel = new JLabel();
        secondTargetCountDownLabel.setEnabled(false);
        secondTargetCountDownLabel.setText("00:00.000");
        resultsPanel.add(secondTargetCountDownLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetTitle = new JLabel();
        secondTargetTitle.setEnabled(false);
        Font secondTargetTitleFont = this.$$$getFont$$$(null, Font.BOLD, -1, secondTargetTitle.getFont());
        if (secondTargetTitleFont != null) secondTargetTitle.setFont(secondTargetTitleFont);
        secondTargetTitle.setText("Second target");
        resultsPanel.add(secondTargetTitle, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstTargetTorpedoDescription = new JLabel();
        firstTargetTorpedoDescription.setText("");
        resultsPanel.add(firstTargetTorpedoDescription, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetDescription = new JLabel();
        secondTargetDescription.setText("");
        resultsPanel.add(secondTargetDescription, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetTorpedoDescription = new JLabel();
        secondTargetTorpedoDescription.setText("");
        resultsPanel.add(secondTargetTorpedoDescription, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondTargetFireMessage = new JLabel();
        Font secondTargetFireMessageFont = this.$$$getFont$$$(null, Font.BOLD, -1, secondTargetFireMessage.getFont());
        if (secondTargetFireMessageFont != null) secondTargetFireMessage.setFont(secondTargetFireMessageFont);
        secondTargetFireMessage.setText("");
        resultsPanel.add(secondTargetFireMessage, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetWait = new JLabel();
        thirdTargetWait.setEnabled(false);
        thirdTargetWait.setText("Wait");
        resultsPanel.add(thirdTargetWait, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetCountDownLabel = new JLabel();
        thirdTargetCountDownLabel.setEnabled(false);
        thirdTargetCountDownLabel.setText("00:00.000");
        resultsPanel.add(thirdTargetCountDownLabel, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetTitle = new JLabel();
        thirdTargetTitle.setEnabled(false);
        Font thirdTargetTitleFont = this.$$$getFont$$$(null, Font.BOLD, -1, thirdTargetTitle.getFont());
        if (thirdTargetTitleFont != null) thirdTargetTitle.setFont(thirdTargetTitleFont);
        thirdTargetTitle.setText("Third target");
        resultsPanel.add(thirdTargetTitle, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetDescription = new JLabel();
        thirdTargetDescription.setText("");
        resultsPanel.add(thirdTargetDescription, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetTorpedoDescription = new JLabel();
        thirdTargetTorpedoDescription.setText("");
        resultsPanel.add(thirdTargetTorpedoDescription, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdTargetFireMessage = new JLabel();
        Font thirdTargetFireMessageFont = this.$$$getFont$$$(null, Font.BOLD, -1, thirdTargetFireMessage.getFont());
        if (thirdTargetFireMessageFont != null) thirdTargetFireMessage.setFont(thirdTargetFireMessageFont);
        thirdTargetFireMessage.setText("");
        resultsPanel.add(thirdTargetFireMessage, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        resultsPanel.add(spacer15, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        resultsPanel.add(spacer16, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        resultsPanel.add(spacer17, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fourthTargetWait = new JLabel();
        fourthTargetWait.setEnabled(false);
        fourthTargetWait.setText("Wait");
        resultsPanel.add(fourthTargetWait, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        resultsPanel.add(spacer18, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fourthTargetTitle = new JLabel();
        fourthTargetTitle.setEnabled(false);
        Font fourthTargetTitleFont = this.$$$getFont$$$(null, Font.BOLD, -1, fourthTargetTitle.getFont());
        if (fourthTargetTitleFont != null) fourthTargetTitle.setFont(fourthTargetTitleFont);
        fourthTargetTitle.setText("Fourth target");
        resultsPanel.add(fourthTargetTitle, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        resultsPanel.add(spacer19, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fifthTargetWait = new JLabel();
        fifthTargetWait.setText("Wait");
        resultsPanel.add(fifthTargetWait, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        resultsPanel.add(spacer20, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fifthTargetTitle = new JLabel();
        fifthTargetTitle.setEnabled(false);
        Font fifthTargetTitleFont = this.$$$getFont$$$(null, Font.BOLD, -1, fifthTargetTitle.getFont());
        if (fifthTargetTitleFont != null) fifthTargetTitle.setFont(fifthTargetTitleFont);
        fifthTargetTitle.setText("Fifth target");
        resultsPanel.add(fifthTargetTitle, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer21 = new Spacer();
        resultsPanel.add(spacer21, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fourthTargetCountDownLabel = new JLabel();
        fourthTargetCountDownLabel.setEnabled(false);
        fourthTargetCountDownLabel.setText("00:00.000");
        resultsPanel.add(fourthTargetCountDownLabel, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fifthTargetCountDownLabel = new JLabel();
        fifthTargetCountDownLabel.setEnabled(false);
        fifthTargetCountDownLabel.setText("00:00.000");
        resultsPanel.add(fifthTargetCountDownLabel, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fourthTargetDescription = new JLabel();
        fourthTargetDescription.setText("");
        resultsPanel.add(fourthTargetDescription, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fourthTargetTorpedoDescription = new JLabel();
        fourthTargetTorpedoDescription.setText("");
        resultsPanel.add(fourthTargetTorpedoDescription, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fifthTargetDescription = new JLabel();
        fifthTargetDescription.setText("");
        resultsPanel.add(fifthTargetDescription, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fifthTargetTorpedoDescription = new JLabel();
        fifthTargetTorpedoDescription.setText("");
        resultsPanel.add(fifthTargetTorpedoDescription, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fourthTargetFireMessage = new JLabel();
        Font fourthTargetFireMessageFont = this.$$$getFont$$$(null, Font.BOLD, -1, fourthTargetFireMessage.getFont());
        if (fourthTargetFireMessageFont != null) fourthTargetFireMessage.setFont(fourthTargetFireMessageFont);
        fourthTargetFireMessage.setText("");
        resultsPanel.add(fourthTargetFireMessage, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fifthTargetFireMessage = new JLabel();
        Font fifthTargetFireMessageFont = this.$$$getFont$$$(null, Font.BOLD, -1, fifthTargetFireMessage.getFont());
        if (fifthTargetFireMessageFont != null) fifthTargetFireMessage.setFont(fifthTargetFireMessageFont);
        fifthTargetFireMessage.setText("");
        resultsPanel.add(fifthTargetFireMessage, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creditsPanel = new JPanel();
        creditsPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(creditsPanel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creditsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label39 = new JLabel();
        label39.setText("Credits:   -PringlesMan- and sinchan607");
        creditsPanel.add(label39, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer22 = new Spacer();
        creditsPanel.add(spacer22, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        redditLink = new JLabel();
        redditLink.setText("Reddit");
        creditsPanel.add(redditLink, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
