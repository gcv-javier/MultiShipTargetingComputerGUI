package com.mstc.core.calculator;

import com.mstc.core.dto.CalculationResult;
import com.mstc.core.dto.Settings;
import com.mstc.core.dto.TargetShip;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract class where every implementation should predict the time/distance of impact of the torpedo.
 */
public abstract class Calculator {

    /**
     * The shooting order. From the bigger time amount to the smallest one.
     * @param targetShips
     * @return
     */
    public List<CalculationResult> getShootingInOrder(List<TargetShip> targetShips) {
        List<CalculationResult> shootingInAscendingOrder = calculateTimeAmountsToImpact(targetShips).stream()
                .sorted(Comparator.comparingDouble(CalculationResult::getTimeToImpact))
                .collect(Collectors.toList());
        Collections.reverse(shootingInAscendingOrder);
        return shootingInAscendingOrder;
    }

    /**
     * Calculate the time to impact of the torpedo to the target ship.
     * It should be implemented in every concrete Calculator.
     * @param targetShip
     * @return
     */
    public abstract double calculateTimeToImpact(TargetShip targetShip);

    /**
     * Calculate the distance to impact of the torpedo to the target ship.
     * It should be implemented in every concrete Calculator.
     * @param targetShip
     * @return
     */
    public abstract double calculateDistanceToImpact(TargetShip targetShip);

    /**
     * Calculate the time to impact for every target ship based on the implementation of every calculator.
     * @param targetShips
     * @return
     */
    private Set<CalculationResult> calculateTimeAmountsToImpact(List<TargetShip> targetShips) {
        Set<CalculationResult> calculationResults = new HashSet<>();
        targetShips.forEach(t -> calculationResults.add(new CalculationResult(t, calculateTimeToImpact(t))));
        return calculationResults;
    }
}
