package com.westonbelk;


import com.google.gson.annotations.Expose;
import oshi.hardware.CentralProcessor;
import oshi.hardware.Sensors;

public class CPUMonitor implements Monitorable {

    private CentralProcessor cpu;
    private Sensors sensors;

    @Expose private String vendor;
    @Expose private String name;
    @Expose private double freq;
    @Expose private long uptime;
    @Expose private double temperature;
    @Expose private double voltage;
    @Expose private int[] fanSpeed;

    public CPUMonitor(CentralProcessor cpu, Sensors sensors) {
        this.cpu = cpu;
        this.sensors = sensors;
        if(isAvailable()) {
            update();
        }
    }

    public void update() {
        this.vendor = cpu.getVendor();
        this.name = cpu.getName();
        this.freq = cpu.getVendorFreq()/1000000000.0;
        this.uptime = cpu.getSystemUptime();

        this.temperature = sensors.getCpuTemperature();
        if(this.temperature == 0) this.temperature = -1;

        this.voltage = sensors.getCpuVoltage();
        if(this.voltage == 0) this.voltage = -1;

        this.fanSpeed = sensors.getFanSpeeds();
        if(this.fanSpeed.length == 1 && fanSpeed[0] == 0) this.fanSpeed = new int[] { -1 };
    }

    public boolean isAvailable() {
        return true;
    }
}
