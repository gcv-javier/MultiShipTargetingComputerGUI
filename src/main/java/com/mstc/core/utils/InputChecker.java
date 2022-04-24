package com.mstc.core.utils;

import com.mstc.core.calculator.TrigonometricCalculator;
import com.mstc.core.dto.Settings;

/**
 * Class that checks the rightness of the values inserted by the user.
 */
public class InputChecker {

    public static boolean areInputsNullOrEmptyStrings(Object... inputs) {
        for (Object input : inputs) {
            if (input == null || (input instanceof String && ((String) input).isBlank())) {
                return true;
            }
        }
        return false;
    }

    public static String generateOutput(Settings settings) {
        if (settings.getTargetShips() == 1) {
            return "You need 2 ships at least to use the countdown";
        } else {
            return "Number of target ships is " + settings.getTargetShips() + " and the calculation used was " +
                    ((settings.getCalculator() instanceof TrigonometricCalculator) ? "Trigonometric calculation" :
                            "Basic calculation");
        }
    }
}
