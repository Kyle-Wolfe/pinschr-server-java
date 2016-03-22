import com.google.gson.annotations.Expose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class NvidiaGraphicsMonitor implements Monitorable {
    @Expose private int count;
    @Expose private String name;
    @Expose private double fanSpeed;
    @Expose private int used;
    @Expose private int free;
    @Expose private int temperature;
    @Expose private double powerDraw;

    public NvidiaGraphicsMonitor() {
        update();
    }

    public void update() {
        String output = null;
        Path path = FileSystems.getDefault().getPath("C:/Program Files/NVIDIA Corporation/NVSMI", "nvidia-smi.exe");
        String options = " --format=csv,nounits,noheader --query-gpu=count,gpu_name,fan.speed,memory.used,memory.free,temperature.gpu,power.draw";
        try {
            output = runProcess(path, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] data = parse(output);

        // Assign the data to the object's fields
        this.count = Integer.parseInt(data[0]);
        this.name = data[1];
        this.fanSpeed = Double.parseDouble(data[2]);
        this.used = Integer.parseInt(data[3]);
        this.free = Integer.parseInt(data[4]);
        this.temperature = Integer.parseInt(data[5]);
        this.powerDraw = Double.parseDouble(data[6]);
    }

    private static String[] parse(String raw) {
        // Split into keys and values.
        String[] values = raw.split(",");

        for (int i = 0; i < values.length; i++) {
            // Strip away unnecessary spaces.
            values[i] = values[i].trim();

            // If the value is not supported then give it the value -1.
            // We give it -1 because this will convert to a string and a number.
            if(values[i].equals("[Not Supported]")) {
                values[i] = "-1";
            }
        }
        return values;
    }

    private static String runProcess(Path path, String args) throws IOException {
        StringBuilder output = new StringBuilder();
        String line;

        Process p = Runtime.getRuntime().exec(path + args);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while ((line = input.readLine()) != null) {
            output.append(line);
            output.append(System.getProperty("line.separator"));
        }
        input.close();

        return output.toString();
    }
}