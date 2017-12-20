package pw.artwhite.FileCopy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Created by artwhite on 24/11/2017.
 */
public class Copy {
    private String from;
    private String to;

    public Copy(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void Run(){
        String text = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(from), Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            text += line;
        } catch (IOException e) {
            // log error
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // log warning
                }
            }
        }

        try(FileWriter writer = new FileWriter(to, false))
        {
            // запись всей строки
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

}
