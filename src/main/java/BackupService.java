public interface BackupService {
    /**
     * Write backup (byte array) as file
     *
     * @param path   output location
     * @param fName  files name
     * @param backup backup that will be write as file on path/fName
     */
    void writeAsFile(String path, String fName, byte[] backup);

    /**
     * Generate byte array.
     *
     * @param minLen minimum array length
     * @param maxLen maximum array length
     */
    byte[] generate(int minLen, int maxLen);
}
