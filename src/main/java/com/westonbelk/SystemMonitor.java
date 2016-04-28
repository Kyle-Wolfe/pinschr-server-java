package com.westonbelk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class SystemMonitor {

    private HashMap<String, Monitorable> monitors = new LinkedHashMap<String, Monitorable>();
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public SystemMonitor() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

        monitors.put("memory", new MemoryMonitor(hardware.getMemory()));
        monitors.put("cpu", new CPUMonitor(hardware.getProcessor(), hardware.getSensors()));
        monitors.put("gpu", new NvidiaGraphicsMonitor());
        monitors.put("os", new OSMonitor());
        monitors.put("partitions", new PartitionMonitor());
        monitors.put("power", new PowerMonitor());

        Iterator<Monitorable> it = monitors.values().iterator();
        while(it.hasNext()) {
            if(!it.next().isAvailable()) {
                it.remove();
            }
        }
    }

    public void update() {
        for (Monitorable value : monitors.values()) {
            value.update();
        }
    }

    public void printPretty() {
        Gson prettyGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        System.out.println(prettyGson.toJson(this.monitors));
    }
    
    public String toString() {
        return gson.toJson(this.monitors);
    }
}
