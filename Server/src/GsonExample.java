import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {
    public static void main(String[] args) {

        NvidiaGraphicsMonitor gpu1 = new NvidiaGraphicsMonitor();
        NvidiaGraphicsMonitor gpu2 = new NvidiaGraphicsMonitor();

        Map<String, Jsonable> myMap = new HashMap<String, Jsonable>();
        myMap.put("first", gpu1);
        myMap.put("second", gpu2);

        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(myMap);

        System.out.println(json);

    }
}

