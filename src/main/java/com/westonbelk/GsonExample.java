package com.westonbelk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GsonExample {
    public static void main(String[] args) throws Exception {

        int count = 0;
        SystemMonitor monitor = new SystemMonitor();
        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press enter to get json output");

        while(true) {
            String in = sysIn.readLine();
            if(in.equals("exit")) {
                System.out.println(in);
                break;
            }

            System.out.println("==========");
            count++;
            System.out.println(count);
            System.out.println("==========");
            monitor.update();
            monitor.printPretty();

        }
    }
}

