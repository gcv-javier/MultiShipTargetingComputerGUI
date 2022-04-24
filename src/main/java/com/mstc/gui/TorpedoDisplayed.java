package com.mstc.gui;

import com.mstc.core.data.TorpedoType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class gets the info of the torpedo that we show depending on the distance written.
 */
public class TorpedoDisplayed {

    private TorpedoType torpedoType;
    private Map<Double, Integer> speedRangeMap;

    public TorpedoDisplayed(TorpedoType torpedoType) {
        this.torpedoType = torpedoType;
        this.speedRangeMap = new LinkedHashMap<>(); // LinkedHashMap keeps the keys in the order they were inserted
    }

    public TorpedoType getTorpedoType() {
        return torpedoType;
    }

    public void setTorpedoType(TorpedoType torpedoType) {
        this.torpedoType = torpedoType;
    }

    public Map<Double, Integer> getSpeedRangeMap() {
        return speedRangeMap;
    }

    public void setSpeedRangeMap(Map<Double, Integer> speedRangeMap) {
        this.speedRangeMap = speedRangeMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorpedoDisplayed that = (TorpedoDisplayed) o;
        return torpedoType == that.torpedoType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(torpedoType);
    }
}
