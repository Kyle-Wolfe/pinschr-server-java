package com.westonbelk;

import com.google.gson.annotations.Expose;
import oshi.software.os.OSFileStore;

public class Partition {

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
