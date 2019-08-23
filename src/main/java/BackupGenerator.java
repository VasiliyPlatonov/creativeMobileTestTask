public interface BackupGenerator {
    /**
     * Generate byte array.
     *
     * @param minLen minimum array length
     * @param maxLen maximum array length
     */
    byte[] generate(int minLen, int maxLen);
}
