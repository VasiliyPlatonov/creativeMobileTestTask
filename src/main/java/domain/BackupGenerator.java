package domain;


public interface BackupGenerator {
    byte[] generate(int minSize, int maxSize);
}
