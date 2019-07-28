package pl.wojtek120.warsztaty1.zadanie5;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        getAllWords();
    }

    public static void getAllWords(){
        Connection connection = Jsoup.connect("http://www.onet.pl/");
        List<String> list = new ArrayList<>();
        Path path = Paths.get("popular_words.txt");

        try {
            Document document = connection.get();
            Elements links = document.select("span.title");
            for (Element elem : links){
                //System.out.println(elem.text());
                String[] tmpList = elem.text()
                        .replaceAll("\\.", "").replaceAll("'", "")
                        .replaceAll("!", "").replaceAll(",", "")
                        .replaceAll(":", "").replaceAll("\\?", "")
                        .replaceAll("\"", "").replaceAll("„", "")
                        .toLowerCase().split(" ");

                for (String str: tmpList){
                    if(str.length() >= 3){
                        list.add(str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getFilteredWords();
    }

    public static void getFilteredWords(){
        Path pathLoad = Paths.get("popular_words.txt");
        Path pathSave = Paths.get("filtered_popular_words.txt");

        String []filter = new String[]{"ponieważ", "ale", "lub", "oraz", "nie", "się", "jak", "gdy"};
        List<String> filteredWords = new ArrayList<>();

        try {
            for(String str : Files.readAllLines(pathLoad)){
                filteredWords.add(str);

                for(String filterStr : filter){
                    if(filterStr.equals(str)){
                        filteredWords.remove(filteredWords.size() - 1);
                        break;
                    }
                }
//                System.out.println(str);
            }

            Files.write(pathSave, filteredWords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
