package com.mstc.core.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Representation of the result of the calculation of the time to impact of one ship.
 */
public class CalculationResult {

    private TargetShip targetShip;
    private double timeToImpact; // in seconds, by default the app's core uses seconds for time

    public CalculationResult(TargetShip targetShip, double timeToImpact) {
        this.targetShip = targetShip;
        this.timeToImpact = timeToImpact;
    }

    public TargetShip getTargetShip() {
        return targetShip;
    }

    public void setTargetShip(TargetShip targetShip) {
        this.targetShip = targetShip;
    }

    public double getTimeToImpact() {
        return timeToImpact;
    }

    public void setTimeToImpact(double timeToImpact) {
        this.timeToImpact = timeToImpact;
    }

    @Override
    public String toString() {
        return "CalculationResult{" +
                "targetShip=" + targetShip +
                ", timeToImpact=" + timeToImpact +
                '}';
    }

    // TODO Two calculation results are compared with the time to impact. This might lead to having just one result if two gets the same time to impact.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResult that = (CalculationResult) o;
        return Double.compare(that.timeToImpact, timeToImpact) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeToImpact);
    }
}
