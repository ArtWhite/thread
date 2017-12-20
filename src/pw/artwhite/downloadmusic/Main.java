package pw.artwhite.downloadmusic;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by artwhite on 05/12/2017.
 */
public class Main {
    private static final String IN_FILE_TXT = "src/pw/artwhite/downloadmusic/inFile.txt";
    private static final String OUT_FILE_TXT = "src/pw/artwhite/downloadmusic/outFile.txt";

    public static void main(String[] args) {
        String Url;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);

                String result;
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
                Matcher matcher = email_pattern.matcher(result);
                while (matcher.find()) {
                    outFile.write(matcher.group() + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MusicDownloader downloader = new MusicDownloader();
        downloader.run();
    }
}