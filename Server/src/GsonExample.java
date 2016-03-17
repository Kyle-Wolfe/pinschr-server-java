import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.Sigar;

public class GsonExample {
    public static void main(String[] args) {
        SystemMonitor sys = new SystemMonitor();
        System.out.println(sys);
        try {
            Thread.sleep(10000);
        } catch(Exception e) {}
        sys.update();
        System.out.println(sys);

    }
}

