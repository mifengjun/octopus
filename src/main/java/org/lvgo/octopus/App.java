package org.lvgo.octopus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.58pic.com/").get();
        Elements img = document.select("img[src]");
        img.forEach(element -> {
            String href = element.attributes().get("src");
            System.out.println(href);
        });
    }
}
