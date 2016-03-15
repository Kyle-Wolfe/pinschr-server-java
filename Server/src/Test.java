import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.ProcInfo;

import java.io.IOException;
import java.nio.file.Path;


public class Test {

    public static void main(String[] args) throws IOException {
        String path = "C:/Program Files/NVIDIA Corporation/NVSMI/nvidia-smi.exe";

        // try path type
        Process p = Runtime.getRuntime().exec(path + " -q");
    }
}
