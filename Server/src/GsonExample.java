import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;
import org.hyperic.sigar.Sigar;

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

