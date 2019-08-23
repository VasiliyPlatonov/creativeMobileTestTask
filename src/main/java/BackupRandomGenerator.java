import java.util.concurrent.ThreadLocalRandom;

public class BackupRandomGenerator implements BackupGenerator {
    private Range range;

    public Range getRange() {
        return range;
    }

    public void setRange(int from, int to) {
        this.range = new Range(from, to);
    }


    /**
     * Generate a randomly filled array with randomly generated length from minLen to maxLen.
     */
    @Override
    public byte[] generate(int minLen, int maxLen) {
        if (minLen >= maxLen) {
            throw new IllegalArgumentException("min length of resulting byte array must be less than max length");
        }
        byte[] result = new byte[ThreadLocalRandom.current().nextInt(minLen, maxLen + 1)];

        if (this.range == null) {
            // just random
            ThreadLocalRandom.current().nextBytes(result);
        } else {
            // range random
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) ThreadLocalRandom.current().nextInt(range.from, range.to + 1);

            }

        }
        return result;
    }

    private class Range {
        int from;
        int to;

        Range(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }


}
