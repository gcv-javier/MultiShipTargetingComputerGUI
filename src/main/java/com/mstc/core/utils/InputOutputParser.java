package com.mstc.core.utils;

import com.mstc.core.dto.Settings;
import org.apache.commons.math3.util.Precision;

/**
 * The app core uses meters and meters by second.
 * The input/output might be different.
 * This class translates the input values to meters and m/s
 * and translates the output values from meters and m/s to the ones set by the user.
 * Also, the output will get here a lower precision (rounded).
 */
public class InputOutputParser {

    // Input speed
    public static double parseInputSpeed(Settings.UNITS_SYSTEM unitSystem, Double speed) {
        switch (unitSystem) {
            case METRIC -> {return UnitsConverter.fromKiloMetersByHourToMetersBySecond(speed);}
            case MIXED, NAUTICAL -> {return UnitsConverter.fromKnotsToMetersBySecond(speed);}
        }
        return 0.0;
    }

    // Input distance
    public static double parseInputDistance(Settings.UNITS_SYSTEM unitSystem, Double distance) {
        switch (unitSystem) {
            case METRIC, MIXED -> {return UnitsConverter.fromKiloMetersToMeters(distance);}
            case NAUTICAL -> {return UnitsConverter.fromNauticalMilesToMeters(distance);}
        }
        return 0.0;
    }

    // Output speed
    public static double parseOutputSpeed(Settings.UNITS_SYSTEM unitSystem, Double speed) {
        switch (unitSystem) {
            case METRIC -> {return Precision.round(UnitsConverter.fromMetersBySecondToKiloMetersByHour(speed), 1);}
            case NAUTICAL, MIXED -> {return Precision.round(UnitsConverter.fromMetersBySecondsToKnots(speed), 1);}
        }
        return 0.0;
    }

    // Output distance
    // 1.256 km -> 1256m
    public static double parseOutputDistance(Settings.UNITS_SYSTEM unitSystem, Double distance) {
        switch (unitSystem) {
            case METRIC, MIXED -> {return Precision.round(UnitsConverter.fromMetersToKiloMeters(distance), 3);}
            case NAUTICAL -> {return Precision.round(UnitsConverter.fromMetersToNauticalMiles(distance), 3);}
        }
        return 0.0;
    }
}
