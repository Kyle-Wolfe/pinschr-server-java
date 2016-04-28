package com.westonbelk;

import com.google.gson.annotations.Expose;

public class Partition {

    @Expose private String mountPoint;
    @Expose private String deviceName;
    @Expose private long bytesFree;
    @Expose private long bytesUsed;
    @Expose private long readBytes;
    @Expose private long writeBytes;

    public Partition() {
        this.mountPoint = "";
        this.deviceName = "";
        this.bytesFree = 0;
        this.bytesUsed = 0;
        this.readBytes = 0;
        this.writeBytes = 0;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setBytesFree(long bytesFree) {
        this.bytesFree = bytesFree;
    }

    public void setBytesUsed(long bytesUsed) {
        this.bytesUsed = bytesUsed;
    }

    public void setReadBytes(long readBytes) {
        this.readBytes = readBytes;
    }

    public void setWriteBytes(long writeBytes) {
        this.writeBytes = writeBytes;
    }
}
