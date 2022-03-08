package elias.vwa.utility;

import elias.vwa.data.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Utilities {

    /**
     * @return token
     * die Erste Zeile aus der in \data\Constants angegebenen .txt - Datei
     */
    public static String getTokenFromFile() {
        try {
            File f = new File(System.getProperty("user.dir") + Constants.TOKEN_PATH);
            BufferedReader r = new BufferedReader(new FileReader(f));
            return r.readLine();
        } catch (Exception e) {
            System.err.println("Es gab ein Problem den Token aus " + System.getProperty("user.dir") +  Constants.TOKEN_PATH + " zu lesen.");
            e.printStackTrace();
        }
        return null;
    }
}
