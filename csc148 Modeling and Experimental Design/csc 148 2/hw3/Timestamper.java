import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Timestamper{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //String myString = timestamp.toString();        
        String myString2 = sdf.format(timestamp).toString();
        StringSelection stringSelection = new StringSelection(myString2);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);


        System.out.println(myString2);
        System.exit(0);
    }
}