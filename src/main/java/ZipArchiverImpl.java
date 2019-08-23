import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiverImpl implements ZipArchiver {
    /**
     * Zip it
     *
     * @param source data source location
     * @param target output ZIP location
     * @param level  the compression level (0-9)
     * @throws IllegalArgumentException if the compression level is invalid
     * @see java.util.zip.Deflater
     */
    public void zipIt(String source, String target, int level) {
        List<File> fileList =
                this.fillFileList(new File(source), new ArrayList<>());
        try (
                FileOutputStream fos = new FileOutputStream(target);
                ZipOutputStream zos = new ZipOutputStream(fos)
        ) {
            zos.setLevel(level);
            byte[] buffer = new byte[1024];

            System.out.println("Output to Zip : " + target);
            for (File file : fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);

                try (FileInputStream in = new FileInputStream(file)) {
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
            }
            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Traverse a directory and get all files,
     * and add the file into fileList
     *
     * @param node file or directory
     */
    private List<File> fillFileList(File node, List<File> fileList) {
        //add file only
        if (node.isFile()) {
            fileList.add(node.getAbsoluteFile());
        }
        if (node.isDirectory()) {
            String[] subNote = node.list();
            if (subNote != null) {
                for (String filename : subNote) {
                    fillFileList(new File(node, filename), fileList);
                }
            }
        }
        return fileList;
    }
}
