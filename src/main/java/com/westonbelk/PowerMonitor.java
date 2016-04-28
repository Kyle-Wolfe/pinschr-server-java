package com.westonbelk;

import com.google.gson.annotations.Expose;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;

import java.util.ArrayList;
import java.util.List;

public class PowerMonitor implements Monitorable{

    @Expose private List<PowerWrapper> powerWrapperList;

    private HardwareAbstractionLayer hardware;

    public PowerMonitor() {
        if(isAvailable()) {
            update();
        }
    }

    public void update() {
        this.powerWrapperList = new ArrayList<PowerWrapper>();
        PowerSource[] powerSources = new SystemInfo().getHardware().getPowerSources();
        for (PowerSource p : powerSources) {
            powerWrapperList.add(new PowerWrapper(p));
        }
    }

    public boolean isAvailable() {
        return true;
    }
}
