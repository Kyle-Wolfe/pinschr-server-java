package com.westonbelk;

import com.google.gson.annotations.Expose;
import oshi.hardware.GlobalMemory;

public class MemoryMonitor implements Monitorable {

    private GlobalMemory memory;

    @Expose private long free;
    @Expose private long used;

    public MemoryMonitor(GlobalMemory memory) {
        this.memory = memory;
        if(isAvailable()) {
            update();
        }
    }

    @Override
    public void update() {
        long total = memory.getTotal();
        this.free = memory.getAvailable();
        this.used = total - free;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
