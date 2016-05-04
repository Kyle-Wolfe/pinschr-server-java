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

    private class Partition {

        @Expose private String description;
        @Expose private String name;
        @Expose private long free;
        @Expose private long used;
        @Expose private long reads;
        @Expose private long writes;

        public Partition(OSFileStore f) {
            this.description = f.getDescription();
            this.name = f.getName();
            this.free = f.getUsableSpace();
            this.used = f.getTotalSpace()-f.getUsableSpace();
            this.reads = -1;
            this.writes = -1;
        }
    }

}
