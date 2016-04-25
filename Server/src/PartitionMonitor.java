import com.google.gson.annotations.Expose;
import org.hyperic.sigar.*;

import java.util.ArrayList;
import java.util.List;


public class PartitionMonitor implements Monitorable {

    Sigar sigar;

    @Expose List<Partition> partitions;

    public PartitionMonitor(Sigar sigar) {
        this.sigar = sigar;
        if(isAvailable()) {
            update();
        }
    }

    public void init() {

    }

    @Override
    public void update() {
        try{
            FileSystem[] fsList = sigar.getFileSystemList();
            this.partitions = new ArrayList<Partition>();

            for(int i = 0; i < fsList.length; i++) {
                if(fsList[i].getType() == FileSystem.TYPE_LOCAL_DISK) {
                    Partition p = new Partition();
                    p.setMountPoint(fsList[i].getDirName());
                    p.setDeviceName(fsList[i].getDevName());

                    FileSystemUsage fsUsage = sigar.getFileSystemUsage(fsList[i].getDirName());
                    p.setBytesFree(fsUsage.getFree());
                    p.setBytesUsed(fsUsage.getUsed());
                    p.setReadBytes(fsUsage.getDiskReadBytes());
                    p.setWriteBytes(fsUsage.getDiskWriteBytes());

                    this.partitions.add(p);
                }
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
