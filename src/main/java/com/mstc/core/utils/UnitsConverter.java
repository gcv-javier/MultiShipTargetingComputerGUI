package com.mstc.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnitsConverter {

    // Based unit used in the game for distance is meter, for time is second, for speed is m/s
    // The user can choose another one, but the amounts would be converted from input and from output
    // And the UI will round the results to one decimal, as the U-Boat interface

    // Distance
    public static double fromMetersToNauticalMiles(double meters) {
        return meters / 1852;
    }

    public static double fromNauticalMilesToMeters(double nauticalMiles) {
        return nauticalMiles * 1852;
    }

    public static double fromKiloMetersToNauticalMiles(double kiloMeters) {
        return fromMetersToNauticalMiles(kiloMeters/1000);
    }

    public static double fromNauticalMilesToKiloMeters(double nauticalMiles) {
        return fromNauticalMilesToMeters(nauticalMiles) * 1000;
    }

    public static double fromKiloMetersToMeters(Double kilometers) {
        return kilometers * 1000;
    }

    public static double fromMetersToKiloMeters(Double meters) {
        return meters / 1000;
    }

    // Speed
    public static double fromMetersBySecondsToKnots(double metersBySeconds) {
        return metersBySeconds * 1.94384;
    }

    public static double fromKnotsToMetersBySecond(double knots) {
        return knots / 1.94384;
    }

    public static double fromKiloMetersByHourToKnots(double kiloMetersByHour) {
        return kiloMetersByHour / 1.852;
    }

    public static double fromKnotsToKilometersByHour(double knots) {
        return knots * 1.852;
    }

    public static double fromKiloMetersByHourToMetersBySecond(Double kilometersByHour) {
        return kilometersByHour / 3.6;
    }

    public static double fromMetersBySecondToKiloMetersByHour(Double metersBySecond) {
        return metersBySecond * 3.6;
    }

    // Time
    // seconds to minutes + seconds (123 -> 02:03)
    public static String fromSecondsToMinutesAndSeconds(double seconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss.SSS", Locale.getDefault());
        return formatter.format(new Date((long) (seconds * 1000)));
    }

}
