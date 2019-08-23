import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DataMakerTest {
    private BackupGenerator bGenerator = new BackupRandomGenerator();
    private BackupService bService = new BackupServiceImpl(bGenerator);
    private DataMaker dataMaker = new DataMaker(bService);

    private String path = "src/test/resources/data";


    @Before
    @After
    public void cleanPath() throws IOException {
        FileUtils.deleteDirectory(new File(path));
        System.out.println("Directory on path '" + path + "' has ben removed.");
    }

    @Test
    public void makeDataSetTest() {
        String path = this.path;
        String setName = "test_set";
        String fName = setName;
        int countOfFiles = 4;
        int minFSize = 12 * 1024; // 12kB
        int maxFSize = 20 * 1024; // 20kB

        File dataSet = new File(path + File.separator + setName);

        assertThat(dataSet.exists()).isFalse(); // data set hasn`t created yet
        dataMaker.makeDataSet(path, setName, fName, countOfFiles, minFSize, maxFSize);
        assertThat(dataSet.exists()).isTrue(); // data set has been created

        assertThat(FileUtils.listFiles(dataSet, null, true)
                .size())
                .isEqualTo(countOfFiles);
    }
}
