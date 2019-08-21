package domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataMaker {
    private BackupGenerator bGenerator;
    private BackupService bService;
    private int countOfFiles;
    private int minFSize;
    private int maxFSize;
    private String pathToData;


    public static void main(String[] args) {
        DataMaker dataMaker = new DataMaker(new BackupRandomGenerator(), new BackupServiceImpl());
        dataMaker.makeData();
    }


    public DataMaker(BackupGenerator bGenerator, BackupService bService) {
        Properties props = new Properties();
        try (InputStream inStream = this.getClass().getResourceAsStream(File.separator + "config.properties")) {
            props.load(inStream);
            if (isWindows()) {
                pathToData = props.getProperty("data.windows.path");
            } else {
                pathToData = props.getProperty("data.unix.path");
            }
            countOfFiles = Integer.parseInt(props.getProperty("data.countOfFiles"));
            minFSize = Integer.parseInt(props.getProperty("data.backup.min"));
            maxFSize = Integer.parseInt(props.getProperty("data.backup.max"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bGenerator = bGenerator;
        this.bService = bService;
    }


    public void makeData() {
        for (int i = 1; i <= countOfFiles; i++) {
            bService.writeAsFile(pathToData, "backup_" + i + ".dat",
                    bGenerator.generate(minFSize, maxFSize));
        }
        System.out.println("Created " + countOfFiles + " files on a path: " + pathToData);
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private String fileSeparator() {
        return System.getProperty("file.separator");
    }
}
