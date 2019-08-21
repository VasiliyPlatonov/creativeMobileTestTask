package domain;

public interface BackupService {
    void writeAsFile(String path, String fName, byte[] backup);
}
