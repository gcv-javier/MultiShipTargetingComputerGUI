package com.mstc.core.utils;

import com.mstc.core.calculator.Calculator;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitsConverterTest {

    UnitsConverter unitsConverter;

    @BeforeEach
    void setUp() {
        unitsConverter = new UnitsConverter();
    }

    // TODO test all the conversion methods

    @Test
    public void fromMetersToNauticalMilesTest() {
        double nauticalMiles = unitsConverter.fromMetersToNauticalMiles(1);
        System.out.println(nauticalMiles);
        assertEquals(Precision.round(0.000539957, 1), Precision.round(nauticalMiles, 1));
    }

    @Test
    public void fromNauticalMilesToMetersTest() {
        double meters = unitsConverter.fromNauticalMilesToMeters(1);
        System.out.println(meters);
        assertEquals(1852, Precision.round(meters, 1));
    }
}
