import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BackupServiceImpl implements BackupService {
    private BackupGenerator generator;


    public BackupServiceImpl(BackupGenerator generator) {
        this.generator = generator;
    }

    public void writeAsFile(String path, String fName, byte[] backup) {
        File file = new File(path, fName);
        file.getParentFile().mkdirs();

        try {
            if (file.createNewFile()) {
                try (OutputStream out = new FileOutputStream(file)) {
                    out.write(backup);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] generate(int minLen, int maxLen) {
        return generator.generate(minLen, maxLen);
    }
}
