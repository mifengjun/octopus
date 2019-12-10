package org.lvgo.octopus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void fetchcw() throws IOException {
        Document document = Jsoup.connect("http://emweb.eastmoney.com/pc_usf10/FinancialAnalysis/index?color=web&code=BABA.N").get();
        Elements elements = document.getElementsByClass("contract-introduce");

        for (Element element : elements) {
            Elements table = element.getElementsByTag("table");
            System.out.println("table = " + table);
        }
    }
}
