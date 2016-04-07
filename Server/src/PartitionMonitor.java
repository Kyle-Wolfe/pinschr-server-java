import com.google.gson.annotations.Expose;
import org.hyperic.sigar.*;


public class PartitionMonitor implements Monitorable {
    Sigar sigar;
    FileSystem[] fsList;
    @Expose String[] mountPoints;
    @Expose String[] deviceNames;
    @Expose long[] bytesFree;
    @Expose long[] bytesUsed;

    public PartitionMonitor(Sigar sigar) {
        this.sigar = sigar;
        update();
    }

    public void init() {

    }

    @Override
    public void update() {
        try{
            this.fsList = sigar.getFileSystemList();
            int numFS = this.fsList.length;

            this.mountPoints = new String[numFS];
            this.deviceNames = new String[numFS];
            this.bytesFree = new long[numFS];
            this.bytesUsed = new long[numFS];

            for(int i = 0; i < numFS; i++) {
                FileSystemUsage fsUsage = sigar.getFileSystemUsage(fsList[i].getDirName());

                this.bytesFree[i] = fsUsage.getFree();
                this.bytesUsed[i] = fsUsage.getUsed();
                this.mountPoints[i] = fsList[i].getDirName();
                this.deviceNames[i] = fsList[i].getDevName();
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
