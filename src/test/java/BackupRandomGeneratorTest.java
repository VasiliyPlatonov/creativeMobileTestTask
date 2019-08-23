import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BackupRandomGeneratorTest {
    private BackupRandomGenerator generator = new BackupRandomGenerator();

    @Test
    public void generateTest() {
        int min = 12 * 1024; // 12 kB
        int max = 20 * 1024; // 20kB

        List<byte[]> backups = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            backups.add(generator.generate(min, max));
        }

        /* Check size between min and max */
        backups.forEach((b) -> assertThat(b.length).isBetween(min, max));

        generator.setRange(3, 8);

        assertThat(generator.generate(min, max)).containsOnly(3, 4, 5, 6, 7, 8);

    }
}
