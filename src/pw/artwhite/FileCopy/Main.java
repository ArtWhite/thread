package pw.artwhite.FileCopy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Created by artwhite on 24/11/2017.
 */
public class Main {
    private static final String ADDRES = "src/pw/artwhite/FileCopy/License.txt";

    public static void main(String[] args) throws InterruptedException {

        CopyFiles copyFiles = new CopyFiles(ADDRES, "src/pw/artwhite/FileCopy/index.txt");
        CopyFiles copyFiles1 = new CopyFiles(ADDRES, "src/pw/artwhite/FileCopy/index_copy.txt");

        start(copyFiles);
        copyJoin(copyFiles);

        System.out.println("Задание первое -" + copyFiles.getTimeRun());

        copyFiles = new CopyFiles(ADDRES, "src/pw/artwhite/FileCopy/index.txt");

        start(copyFiles);
        copyJoin(copyFiles);

        start(copyFiles1);
        copyJoin(copyFiles1);

        System.out.println("Задание второе -" + (copyFiles.getTimeRun() + copyFiles1.getTimeRun()));

        copyFiles = new CopyFiles(ADDRES, "src/pw/artwhite/FileCopy/index.txt");
        copyFiles1 = new CopyFiles(ADDRES, "src/pw/artwhite/FileCopy/index_copy.txt");

        start(copyFiles, copyFiles1);
        copyJoin(copyFiles, copyFiles1);

        System.out.println("Задание третье: ");
        System.out.println(copyFiles.getTimeRun());
        System.out.println(copyFiles1.getTimeRun());


    }

    /**
     * Метод запускает 2 потока.
     */
    private static void start(CopyFiles copyFiles, CopyFiles copyFiles1) {
        copyFiles.start();
        copyFiles1.start();

    }

    /**
     * Метод запускает один поток
     */
    private static void start(CopyFiles copyFiles) {
        copyFiles.start();
    }

    /**
     * Метод вызывает метод join() для потоков, который ждет пока выполниться поток.
     */
    private static void copyJoin(CopyFiles copyFiles, CopyFiles copyFiles1) throws InterruptedException {
        copyFiles.join();
        copyFiles1.join();
    }

    /**
     * метод вызывает метод join() для потока, который ждет пока поток выпониться.
     */
    private static void copyJoin(CopyFiles copyFiles) throws InterruptedException {
        copyFiles.join();
    }
}
