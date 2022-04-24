package com.mstc.core.calculator;

import com.mstc.core.dto.TargetShip;

/**
 * Calculator implementation of the basic approach where only the distance to the target ship and the torpedo speed is used.
 */
public class BasicCalculator extends Calculator {

    /**
     * It calculates the time to impact using the equation Time=Distance/Speed.
     * @param targetShip
     * @return
     */
    @Override
    public double calculateTimeToImpact(TargetShip targetShip) {
        return calculateDistanceToImpact(targetShip) / targetShip.getTorpedo().getSpeed();
    }

    @Override
    public double calculateDistanceToImpact(TargetShip targetShip) {
        return targetShip.getDistance();
    }
}
