package domain;

import java.util.concurrent.ThreadLocalRandom;

public class BackupRandomGenerator implements BackupGenerator {
    @Override
    public byte[] generate(int min, int max) {
        byte[] result = new byte[ThreadLocalRandom.current().nextInt(min, max + 1)];
        ThreadLocalRandom.current().nextBytes(result);
        return result;
    }

}
