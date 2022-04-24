package com.mstc.core.data;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Representation of the torpedo types, where they could have more than one speed and one range, that depends on the speed.
 * https://uboat.fandom.com/wiki/Items
 */
public enum TorpedoType {
    // 1 knot = 0,514444 meters per second

    // T1
    // 44/40/30 knots -> 22.6, 20.6, 15.4 m/s
    // 5/7.5/12 km -> 5000, 7500, 12000 m

    // T2
    // 30 kt -> 15.4 m/s
    // 5 km -> 5000 m

    // T3
    // 30 kt -> 15.4 m/s
    // 5 km -> 5000 m

    // T5
    // 20 kt -> 10.3 m/s
    // 5.7 km -> 5700

    T1("T1", ImmutableMap.of(22.6d, 5000, 20.6d, 7500, 15.4d, 12000)),
    T2("T2", ImmutableMap.of(15.4d, 5000)),
    T3("T3", ImmutableMap.of(15.4d, 5000)),
    T5("T5", ImmutableMap.of(10.3d, 5700));

    private String name;
    private Map<Double, Integer> speedRangeMap;

    TorpedoType(String name, ImmutableMap<Double, Integer> speedRangeMap) {
        this.name = name;
        this.speedRangeMap = speedRangeMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Double, Integer> getSpeedRangeMap() {
        return speedRangeMap;
    }

    public void setSpeedRangeMap(Map<Double, Integer> speedRangeMap) {
        this.speedRangeMap = speedRangeMap;
    }
}
