public interface ZipArchiver {
    /**
     * Zip it
     *
     * @param source data source location
     * @param target output ZIP location
     * @param level  the compression level (0-9)
     * @throws IllegalArgumentException if the compression level is invalid
     * @see java.util.zip.Deflater
     */
    void zipIt(String source, String target, int level);

}
