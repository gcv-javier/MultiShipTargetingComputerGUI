package com.mstc.core.dto;

import java.util.Objects;

/**
 * The Target Ship, that contains all the information to make the calculation.
 */
public class TargetShip {

    private int id;
    private String name;
    private Double speed; // it can be null (when using basic calculation, for example)
    private Double angleOfBow; // it can be null (when using basic calculation, for example)
    private Double distance; // meters per second, by default
    private Torpedo torpedo; // torpedo selected to hit the target ship

    private TargetShip(int id, String name, Double speed, Double angleOfBow, Double distance, Torpedo torpedo) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.angleOfBow = angleOfBow;
        this.distance = distance;
        this.torpedo = torpedo;
    }

    public int getId() {
        return id;
    }

    public TargetShip setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TargetShip setName(String name) {
        this.name = name;
        return this;
    }

    public Double getSpeed() {
        return speed;
    }

    public TargetShip setSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

    public Double getAngleOfBow() {
        return angleOfBow;
    }

    public TargetShip setAngleOfBow(Double angleOfBow) {
        this.angleOfBow = angleOfBow;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public TargetShip setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Torpedo getTorpedo() {
        return torpedo;
    }

    public TargetShip setTorpedo(Torpedo torpedo) {
        this.torpedo = torpedo;
        return this;
    }

    @Override
    public String toString() {
        return "TargetShip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", angleOfBow=" + angleOfBow +
                ", distance=" + distance +
                ", torpedo=" + torpedo +
                '}';
    }

    // Ships are different because they got different IDs when they are created (it's a sequence 1-5).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetShip that = (TargetShip) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class TargetShipBuilder {

        private int id;
        private String name;
        private Double speed;
        private Double angleOfBow;
        private Double distance;
        private Torpedo torpedo;

        public TargetShipBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TargetShipBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TargetShipBuilder setSpeed(Double speed) {
            this.speed = speed;
            return this;
        }

        public TargetShipBuilder setAngleOfBow(Double angleOfBow) {
            this.angleOfBow = angleOfBow;
            return this;
        }

        public TargetShipBuilder setDistance(Double distance) {
            this.distance = distance;
            return this;
        }

        public TargetShipBuilder setTorpedo(Torpedo torpedo) {
            this.torpedo = torpedo;
            return this;
        }

        public TargetShip createTargetShip() {
            return new TargetShip(id, name, speed, angleOfBow, distance, torpedo);
        }
    }
}
