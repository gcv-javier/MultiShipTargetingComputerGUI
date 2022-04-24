package com.mstc.gui;

import com.mstc.core.data.TorpedoType;

/**
 * The content of the ComboBox of the TorpedoType
 */
public class TorpedoComboItem {

    private String key;
    private TorpedoType value;

    public TorpedoComboItem(String key, TorpedoType value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public TorpedoType getValue() {
        return value;
    }
}
