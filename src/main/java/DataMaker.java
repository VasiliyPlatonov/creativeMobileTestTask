import java.io.File;

public class DataMaker {
    private BackupService bService;

    public DataMaker(BackupService bService) {
        this.bService = bService;
    }

    public BackupService getBackupService() {
        return bService;
    }

    public void setBackupService(BackupService bService) {
        this.bService = bService;
    }

    /**
     * Make a set of binary files that will be filled randomly.
     *
     * @param path         output location
     * @param setName      directory where files will be putted
     * @param fName        files name - files wil be named like 'fName_1.dat'
     * @param countOfFiles number of files to be done in the output folder
     * @param minFSize     minimum file size in bytes
     * @param maxFSize     maximum file size in bytes
     */
    public void makeDataSet(String path, String setName, String fName, int countOfFiles, int minFSize, int maxFSize) {
        String fullPath = path + File.separator + setName;

        for (int i = 1; i <= countOfFiles; i++) {
            bService.writeAsFile(
                    fullPath, fName + "_" + i + ".dat",
                    bService.generate(minFSize, maxFSize));
        }
        System.out.println("Created " + countOfFiles + " files on a path: " + path);
    }
}
