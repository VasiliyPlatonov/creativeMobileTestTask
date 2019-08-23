import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackupServiceImplTest {
    private BackupGenerator generator = new BackupRandomGenerator();
    private BackupService service = new BackupServiceImpl(generator);

    private String path = "src/test/resources/data";


    @Before
    public void cleanPath() throws IOException {
        FileUtils.deleteDirectory(new File(path));
        System.out.println("Directory on path '" + path + "' has ben removed.");
    }

    @Test
    public void writeAsFileTest() {
        String path = this.path;
        String backupName = "backup.dat";
        int minFSize = 12 * 1024; // 12kB
        int maxFSize = 20 * 1024; // 20kB
        byte[] backup = service.generate(minFSize, maxFSize);

        File backupFile = new File(path + File.separator + backupName);

        assertThat(backupFile.exists()).isFalse(); // file hasn`t created yet
        service.writeAsFile(path, backupName, backup);
        assertThat(backupFile.exists()).isTrue(); // file has been created

        // Check file size
        assertThat(FileUtils.sizeOf(backupFile)).isBetween((long) (minFSize), (long) maxFSize);
    }
}

