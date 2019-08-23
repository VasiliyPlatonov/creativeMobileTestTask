import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipArchiverImplTest {
    private ZipArchiverImpl zipArchiver = new ZipArchiverImpl();

    private String path = "src/test/resources/data";
    private String dataSetName = "test_set";

    @Before
    public void makeData() {
        BackupRandomGenerator generator = new BackupRandomGenerator();
        BackupService backupService = new BackupServiceImpl(generator);
        DataMaker dataMaker = new DataMaker(backupService);

        generator.setRange(3, 8);

        int countOfFiles = 40;
        int minFSize = 12 * 1024; // 12kB
        int maxFSize = 20 * 1024; // 20kB
        dataMaker.makeDataSet(path, dataSetName, dataSetName, countOfFiles, minFSize, maxFSize);
        File dataSet = new File(path + File.separator + dataSetName);
        assertThat(dataSet.exists()).isTrue();
    }

    @After
    public void cleanPath() throws IOException {
        FileUtils.deleteDirectory(new File(path));
        System.out.println("Directory on path '" + path + "' has ben removed.");
    }

    @Test
    public void zipItTest() {
        String source = path + File.separator + dataSetName;
        String target = path + File.separator + "test_archive";
        zipArchiver.zipIt(source, target, 1);
    }
}
