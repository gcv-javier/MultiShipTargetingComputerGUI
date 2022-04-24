package com.mstc.core.dto;

import com.mstc.core.data.TorpedoType;

/**
 * The torpedo that contains all the information about the torpedo chosen to be shot.
 */
public class Torpedo {

    private TorpedoType type;
    private double speed; // meters per second, by default
    private double maxDistance; // the range

    private Torpedo(TorpedoType type, double speed, double maxDistance) {
        this.type = type;
        this.speed = speed;
        this.maxDistance = maxDistance;
    }

    public TorpedoType getType() {
        return type;
    }

    public void setType(TorpedoType type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public String toString() {
        return "Torpedo{" +
                "type=" + type +
                ", speed=" + speed +
                ", maxDistance=" + maxDistance +
                '}';
    }

    public static class TorpedoBuilder {
        private TorpedoType type;
        private double speed;
        private double maxDistance;

        public TorpedoBuilder setType(TorpedoType type) {
            this.type = type;
            return this;
        }

        public TorpedoBuilder setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public TorpedoBuilder setMaxDistance(double maxDistance) {
            this.maxDistance = maxDistance;
            return this;
        }

        public Torpedo createTorpedo() {
            return new Torpedo(type, speed, maxDistance);
        }
    }
}
