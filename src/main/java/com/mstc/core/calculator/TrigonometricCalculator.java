package com.mstc.core.calculator;

import com.mstc.core.dto.TargetShip;

/**
 * Calculator implementation of the trigonometric approach where we calculate time of impact based on law of the sines
 * getting firstly the torpedo angle to impact the ship (also calculated by the submarine TDC).
 * The problem of knowing the time of impact is a triangle where we know one angle (angle of bow) and one distance
 * (the distance to the target).
 * For more details check:
 * https://drive.google.com/file/d/1H_RYhZf-xX3YBnIS-3oeUDl9bj7PxiE_/view
 * http://www.tvre.org/en/torpedo-calculator-t-vh-re-s3
 * http://www.simhq.com/_naval/PDF/naval_009print.pdf
 */
public class TrigonometricCalculator extends Calculator{

    /**
     * Implementation of the time to impact calculation.
     * Calculate time that the torpedo needs to reach the distance to impact based on the torpedo speed.
     * We could have also calculated the time to impact based on the target speed and the distance that the ship will travel,
     * the other side of the triangle.
     * @param targetShip
     * @return time in seconds, by default.
     */
    @Override
    public double calculateTimeToImpact(TargetShip targetShip) {
        // time = distance/speed
        double timeToImpact = calculateDistanceToImpact(targetShip) / targetShip.getTorpedo().getSpeed();
        return timeToImpact;
    }

    /**
     * Implementation of the distance to impact calculation.
     * Calculate the distance where the torpedo will hit using the law of sines applied to a no rectangular triangle
     * where the angle is the angle of bow, and we can get the other two angles.
     *
     * calculateDistanceToShipImpactPoint could have been used, but with one side of the triangle is enough,
     * although we compared both and the time is the same.
     * @param targetShip
     * @return Distance in meters, by default.
     */
    @Override
    public double calculateDistanceToImpact(TargetShip targetShip) {
        // it's the distance that the torpedo makes
        double distanceToImpact = calculateDistanceToTorpedoImpactPoint(targetShip);
        return distanceToImpact;
    }

    /**
     * Calculate the distance where the torpedo will hit using the law of sines applied to a no rectangular triangle
     * where the angle is the angle of bow, and we can get the other two angles.
     * @param targetShip
     * @return Distance in meters, by default.
     */
    private double calculateDistanceToTorpedoImpactPoint(TargetShip targetShip) {
        // sin method by default uses radians instead of degrees
        // 𝛽 = 180° - 𝜃 - 𝛼; 𝜃 = angle of bow, 𝛼 = torpedo angle (calculated by the submarine TDC)
        // c = (sin𝜃 / sin𝛽) a; a = distance to ship, c = distance to impact done by the torpedo
        double angleOfImpact = 180 - targetShip.getAngleOfBow() - calculateTorpedoAngle(targetShip);
        double distanceToImpact = (Math.sin(Math.toRadians(targetShip.getAngleOfBow())) /
                                        Math.sin(Math.toRadians(angleOfImpact)))
                                    * targetShip.getDistance();
        return distanceToImpact;
    }

    /**
     * Calculate the distance where the target will be hit using the law of sines applied to a no rectangular triangle
     * where the angle is the angle of bow, and we can get the other two angles.     *
     * @param targetShip
     * @return Distance in meters, by default.
     */
    private double calculateDistanceToShipImpactPoint(TargetShip targetShip) {
        // sin method by default uses radians instead of degrees
        // b = (sin𝛼 / sin𝛽) a; b = distance made by the ship until it gets hit by the torpedo
        double angleOfImpact = 180 - targetShip.getAngleOfBow() - calculateTorpedoAngle(targetShip);
        double distanceToImpact = (Math.sin(Math.toRadians(calculateTorpedoAngle(targetShip))) /
                Math.sin(Math.toRadians(angleOfImpact))) * targetShip.getDistance();
        return distanceToImpact;
    }


    /**
     * Calculate the angle of the torpedo to be shot using the law of sines applied to the triangle
     * where the angle is the angle of bow and the hypotenuse is the distance to target.
     * Similar to this equation is used by the sub TDC.
     * @param targetShip
     * @return angle in degrees
     */
    public double calculateTorpedoAngle(TargetShip targetShip) {
        // arcsin method returns radians instead of degrees
        // 𝛼 = 𝑎𝑟𝑐𝑠𝑖𝑛((𝑇𝑎𝑟𝑔𝑒𝑡 𝑆𝑝𝑒𝑒𝑑/𝑇𝑜𝑟𝑝𝑒𝑑𝑜 𝑆𝑝𝑒𝑒𝑑) sin𝜃) = sin−1((𝑇𝑎𝑟𝑔𝑒𝑡 𝑆𝑝𝑒𝑒𝑑/𝑇𝑜𝑟𝑝𝑒𝑑𝑜 𝑆𝑝𝑒𝑒𝑑) sin𝜃)
        double u = targetShip.getSpeed() / targetShip.getTorpedo().getSpeed();
        double angleToImpact = Math.toDegrees(Math.asin(u * Math.sin(Math.toRadians(targetShip.getAngleOfBow()))));
        return angleToImpact;
    }
}
