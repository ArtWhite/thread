package pw.artwhite.downloadmusic;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by artwhite on 12/12/2017.
 */
public class MusicDownloader extends Thread{

    private static final String OUT_FILE_TXT = "src/pw/artwhite/downloadmusic/outFile.txt";
    private static final String PATH_TO_MUSIC = "src/pw/artwhite/downloadmusic/music/";
    private Pattern name_pattern = Pattern.compile("name:\\s?(.*?),");
    private Pattern song_pattern = Pattern.compile("url:\\s?(.*?);");

    public void run(){
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music, name = null;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    Matcher matcherSong = song_pattern.matcher(music);
                    Matcher matcherName = name_pattern.matcher(music);
                    while (matcherSong.find() && matcherName.find()){
                        music = matcherSong.group(1);
                        name = matcherName.group(1);
                    }

                    name = addSlashes(name);

                    downloadUsingNIO(music, PATH_TO_MUSIC + name + ".mp3");
                    System.out.println(count + " - Музыка скачана");
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String addSlashes(String s) {
        s = s.replaceAll("\\\\", "");

        s = s.replaceAll("\\n", "\\\\n");
        s = s.replaceAll("\\r", "\\\\r");
        s = s.replaceAll("\\00", "\\\\0");
        s = s.replaceAll("'", "\\\\'");
        s = s.replaceAll("/$", "");
        s = s.replaceAll("/\\z", "");
        if (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        s = s.substring(0, s.length() - (s.endsWith("/") ? 1 : 0));
        s = s.replaceAll("/", "|");
        s = s.replaceAll("\\/", "|");
        return s;
    }

    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}