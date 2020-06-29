package ru.parser.simbirSoft.utils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperSave {

    public static void downloadPage(String name1, String link) throws IOException {
        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        PrintWriter printWriter = new PrintWriter(name1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        while (s != null) {
            printWriter.print(s);
            s = bufferedReader.readLine();
        }
        printWriter.close();
        inputStream.close();
    }

    public static void savePage(String link) throws IOException {
        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        File file = new File("Save1.html");
        Pattern p = Pattern.compile("\"http[^\"]*/[^\"]*\\.{2,3}");
        BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
        String str = buff.readLine();
        Matcher m = p.matcher(str);
        while (str != null) {
            if (m.find()) {
                String result = m.group();
                downloadFileToHtml(result, file);
            }
            str = buff.readLine();
        }
    }
    public static void downloadFileToHtml(String link, File file) throws IOException {
        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        FileOutputStream fop = new FileOutputStream(file);
        int m = inputStream.read();
        while (m != -1) {
            fop.write(m);
            m = inputStream.read();
        }
        inputStream.close();
        fop.close();
    }
}
