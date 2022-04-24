package com.mstc.core.calculator;

import com.mstc.core.dto.TargetShip;
import com.mstc.core.dto.Torpedo;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorTest {

    TrigonometricCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TrigonometricCalculator();
    }

    // TODO complete tests and do more tests
    @Test
    public void calculateDistanceToImpactTest() {
        Torpedo torpedo = new Torpedo.TorpedoBuilder().setSpeed(20).createTorpedo();
        TargetShip targetShip = new TargetShip.TargetShipBuilder().setSpeed(15d).setAngleOfBow(30d).setTorpedo(torpedo)
                .createTargetShip();

//        double distanceToImpact = calculator.calculateDistanceToImpact(targetShip);
//        System.out.println(distanceToImpact);

        double angleToImpact = calculator.calculateTorpedoAngle(targetShip);
        System.out.println(angleToImpact);

        angleToImpact = Math.toDegrees(Math.asin((15/20) * Math.sin(Math.toRadians(30))));
        System.out.println(angleToImpact);

        angleToImpact = Math.toDegrees(Math.asin(0.375d));
        System.out.println(angleToImpact);

        float a = 15, b = 20;
        System.out.println(a/b);

        // Apache commons math
        double rounded0 = Precision.round(0.55d, 1);
        System.out.println(rounded0);
        double rounded1 = Precision.round(0.53d, 1);
        System.out.println(rounded1);
        double rounded2 = Precision.round(0.53d, 1, BigDecimal.ROUND_HALF_UP);
        System.out.println(rounded2);
        double rounded3 = Precision.round(0.53d, 1,  RoundingMode.HALF_UP.ordinal() );
        System.out.println(rounded3);

        double rounded4 = Precision.round(0.55d, 1, BigDecimal.ROUND_HALF_UP);
        System.out.println(rounded4);
        double rounded5 = Precision.round(0.55d, 1,  RoundingMode.HALF_UP.ordinal() );
        System.out.println(rounded5);

    }
}
