package com.mstc.core.dto;

import com.mstc.core.calculator.Calculator;

/**
 * Settings of the app. They will be set from the UI.
 */
public class Settings {

    // Definition of the Units System
    public enum UNITS_SYSTEM {METRIC, MIXED, NAUTICAL};

    private UNITS_SYSTEM unitSystem;
    private int targetShips; // the number of ships to shoot
    private Calculator calculator; // the implementation of the calculation method

    public Settings(UNITS_SYSTEM unitSystem, int targetShips, Calculator calculator) {
        this.unitSystem = unitSystem;
        this.targetShips = targetShips;
        this.calculator = calculator;
    }

    public UNITS_SYSTEM getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(UNITS_SYSTEM unitSystem) {
        this.unitSystem = unitSystem;
    }

    public int getTargetShips() {
        return targetShips;
    }

    public void setTargetShips(int targetShips) {
        this.targetShips = targetShips;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
}
