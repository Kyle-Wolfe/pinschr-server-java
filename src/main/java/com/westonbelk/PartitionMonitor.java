package com.westonbelk;

import com.google.gson.annotations.Expose;
import java.util.List;


public class PartitionMonitor implements Monitorable {

    @Expose private List<Partition> partitions;

    public PartitionMonitor() {
        if(isAvailable()) {
            update();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
