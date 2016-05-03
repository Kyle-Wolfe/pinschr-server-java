package com.westonbelk;

import com.google.gson.annotations.Expose;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;

import java.util.ArrayList;
import java.util.List;


public class PartitionMonitor implements Monitorable {

    @Expose private List<Partition> partitions = new ArrayList<>();

    private HardwareAbstractionLayer hardware;

    public PartitionMonitor(HardwareAbstractionLayer hardware) {
        this.hardware = hardware;
        if(isAvailable()) {
            update();
        }
    }

    @Override
    public void update() {
        partitions.clear();
        for(OSFileStore f : hardware.getFileStores()) {
            partitions.add(new Partition(f));
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
