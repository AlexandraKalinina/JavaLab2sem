package ru.parser.simbirSoft.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parser.simbirSoft.services.WordService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    private WordService wordService;

    public Parser(WordService wordService) {
        this.wordService = wordService;
    }

    public void reader() throws IOException {
        /*String html = "<div class=\"tabs__title\">Выстраиваем процессы там, где их нет; подстраиваемся там, где они есть</div>            <div class=\"tabs__text\">Вы получаете нужное IT-решение, а мы заботимся о технических деталях</div>          </div>                                <div class=\"tabs__item\" id=\"bx_651765591_4\">  ";
        Document doc = Jsoup.parse(html);*/
        /*File file = new File("save.html");
        Document doc = Jsoup.parse(file, "UTF-8");
        System.out.println(doc.text());*/
        FileReader reader = new FileReader
                ("C:/Users/Александра/IdeaProjects/SimbirSoft/save.html");
        extractText(reader);
        addWord();
    }
    Map<String, Integer> words = new HashMap<>();

    public void addWord() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("text.txt"));
        String[] strings;
        while (scan.hasNext()) {
            if (words.size()>=500) {
                saveInBd();
                words = new HashMap<>();
            } else saveWordInMap(scan.next());
        }
        saveInBd();
    }

    private void saveWordInMap(String next) {
        if (!words.containsKey(next)) {
            words.put(next, 1);
        } else {
            words.put(next, words.get(next) + 1);
        }
    }

    private void saveInBd() {
        wordService.saveMap(words);
    }

    public static void extractText(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ( (line=br.readLine()) != null) {
            sb.append(line);
        }
        File file = new File("text.txt");
        /*Document doc = Jsoup.parse(sb.toString());*/
        /*String textOnly = Jsoup.parse(sb.toString()).text();*/
        BufferedWriter  writer = null;
        try
        {
            Document doc = Jsoup.parse(sb.toString());
            writer = new BufferedWriter(new FileWriter(file));
            //to do: убрать лишние знаки, оставить только слова

            writer.write(doc.text().replaceAll("[^A-Za-zА-Яа-яёЁ0-9]", "\n"));
            /*System.out.println(doc.text().replaceAll("[^A-Za-zА-Яа-яёЁ0-9]", "\n"));*/
            writer.close();
        }
        catch ( IOException e)
        {
            System.out.println(e);
        }
    }
}
