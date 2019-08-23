import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.zip.Deflater;

public class App {
    private static String PATH = "src/main/resources/data/";

    public static void main(String[] args) {
        App app = new App();

        // Make data sets
        app.makeIncompressibleDataSet(PATH);
        app.makeMiddleCompressibleDataSet(PATH);
        app.makeSuperCompressibleDataSet(PATH);

        // Make archive from data sets
        ZipArchiver zipArchiver = new ZipArchiverImpl();
        zipArchiver.zipIt(
                PATH + "incompressible",
                PATH + "incompressible_arch",
                Deflater.BEST_COMPRESSION);

        zipArchiver.zipIt(
                PATH + "superCompressible",
                PATH + "superCompressible_arch",
                Deflater.BEST_COMPRESSION);

        zipArchiver.zipIt(
                PATH + "middleCompressible",
                PATH + "middleCompressible_arch",
                Deflater.BEST_COMPRESSION);

        System.out.println("\n\n----------------------------------------------------------------");
        System.out.println("Size without compression:");
        System.out.println("Incompressible data set:\t\t" + FileUtils.sizeOf(new File(PATH + "incompressible")) + " bytes");
        System.out.println("Middle compressible data set:\t" + FileUtils.sizeOf(new File(PATH + "middleCompressible")) + " bytes");
        System.out.println("Super compressible data set:\t" + FileUtils.sizeOf(new File(PATH + "superCompressible")) + " bytes");

        System.out.println("\nSize after compression:");
        System.out.println("Incompressible data set:\t\t" + FileUtils.sizeOf(new File(PATH + "incompressible_arch")) + " bytes");
        System.out.println("Middle compressible data set:\t" + FileUtils.sizeOf(new File(PATH + "middleCompressible_arch")) + " bytes");
        System.out.println("Super compressible data set:\t" + FileUtils.sizeOf(new File(PATH + "superCompressible_arch")) + " bytes");
    }

    private void makeIncompressibleDataSet(String path) {
        BackupRandomGenerator generator = new BackupRandomGenerator();
        BackupService backupService = new BackupServiceImpl(generator);
        DataMaker dataMaker = new DataMaker(backupService);

        dataMaker.makeDataSet(
                path,
                "incompressible", "incompressible",
                70,
                12 * 1024,
                20 * 1024);
    }

    private void makeSuperCompressibleDataSet(String path) {
        BackupRandomGenerator generator = new BackupRandomGenerator();
        generator.setRange(3, 3);
        DataMaker dataMaker = new DataMaker(new BackupServiceImpl(generator));

        dataMaker.makeDataSet(
                path,
                "superCompressible", "superCompressible",
                70,
                12 * 1024,
                20 * 1024);
    }

    private void makeMiddleCompressibleDataSet(String path) {
        BackupRandomGenerator generator = new BackupRandomGenerator();
        generator.setRange(3, 6);
        DataMaker dataMaker = new DataMaker(new BackupServiceImpl(generator));

        dataMaker.makeDataSet(
                path,
                "middleCompressible", "middleCompressible",
                70,
                12 * 1024,
                20 * 1024);
    }
}
