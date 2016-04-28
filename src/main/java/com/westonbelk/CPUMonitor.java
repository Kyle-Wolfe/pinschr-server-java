package com.westonbelk;


import oshi.hardware.CentralProcessor;

public class CPUMonitor implements Monitorable {

    private CentralProcessor cpu;

    public CPUMonitor(CentralProcessor cpu) {
        this.cpu = cpu;
        if(isAvailable()) {
            update();
        }
    }

    public void update() {

    }

    public boolean isAvailable() {
        return false;
    }
}
