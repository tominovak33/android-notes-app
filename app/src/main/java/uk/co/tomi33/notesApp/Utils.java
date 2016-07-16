package uk.co.tomi33.notesApp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static String readStringFromInputStream(FileInputStream fis) {
        String content = "";
        String tempString;

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);

        try {
            while (null != (tempString = bufferedReader.readLine())) {
                content += tempString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
