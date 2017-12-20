package pw.artwhite.FileCopy;

/**
 * Created by artwhite on 24/11/2017.
 */
public class TestCopy {
    public static void main(String[] args) {
        Copy cop = new Copy("/Applications/MAMP/htdocs/cashbb/php/asf", "/Applications/MAMP/htdocs/cashbb/php/keywords");
        cop.Run();
    }
}
