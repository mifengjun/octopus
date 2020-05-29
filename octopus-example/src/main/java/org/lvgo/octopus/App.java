package org.lvgo.octopus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {

        Octopus.init("https://movie.douban.com/subject/27186348/reviews").parser(DouBanParser.class).start();
    }
}
