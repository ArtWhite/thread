package pw.artwhite.downloadmusic;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Главный класс
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
                Pattern name_pattern = Pattern.compile("<span class=\"artist\">(.*?)<\\/span>");
                Pattern song_pattern = Pattern.compile("<span class=\"track\">(.*?)<\\/span>");
                Matcher matcher = email_pattern.matcher(result);
                Matcher matcherName = name_pattern.matcher(result);
                Matcher matcherSong = song_pattern.matcher(result);

                while (matcher.find() && matcherName.find() && matcherSong.find()) {
                    outFile.write("name: " + matcherName.group(1) + " - " + matcherSong.group(1) + ", url: " + matcher.group() + ";\r\n");
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MusicDownloader downloader = new MusicDownloader();
        downloader.run();
    }
}