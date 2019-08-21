package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BackupServiceImpl implements BackupService {

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

}
