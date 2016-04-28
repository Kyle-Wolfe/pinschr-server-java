package com.westonbelk;

import com.google.gson.annotations.Expose;
import oshi.hardware.PowerSource;

public class PowerWrapper {

    @Expose private String name;
    @Expose private double remainingCapacity;
    @Expose private double timeRemaining;

    public PowerWrapper(PowerSource p) {
        this.name = p.getName();
        this.remainingCapacity = p.getRemainingCapacity();
        this.timeRemaining = p.getTimeRemaining();
    }
}
