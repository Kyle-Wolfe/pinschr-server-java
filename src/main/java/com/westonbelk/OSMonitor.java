package com.westonbelk;

import com.google.gson.annotations.Expose;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class OSMonitor implements Monitorable {

    @Expose private String platform;
    @Expose private String version;
    @Expose private String user;
    @Expose private String home;
    @Expose private String hostname;

    public OSMonitor() {
        Properties properties = System.getProperties();
        this.platform = getPlatform(properties);
        this.version = properties.getProperty("os.version");
        this.user = properties.getProperty("user.name");
        this.home = properties.getProperty("user.home")
                .replace(File.separator,"/");

        this.hostname = "";
        try {
            this.hostname = getHostname();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPlatform(Properties properties) {
        final String WINDOWS_PREFIX = "windows";
        final String MAC_PREFIX = "mac";
        final String LINUX_PREFIX = "linux";

        String name = properties.getProperty("os.name");
        name = name.toLowerCase();

        if(name.startsWith(WINDOWS_PREFIX)) {
            return "Windows";
        }
        else if (name.startsWith(MAC_PREFIX)) {
            return "Mac";
        }
        else if (name.startsWith(LINUX_PREFIX)) {
            return "Linux";
        }
        else {
            return "-1";
        }
    }

    private String getHostname() throws IOException {
        String output;

        Process p = Runtime.getRuntime().exec("hostname");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        output = input.readLine();

        input.close();
        return output;
    }

    @Override
    public void update() {
        // Nothing needs to be updated since it shouldn't change
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
