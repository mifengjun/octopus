package org.lvgo.octopus;

import org.lvgo.octopus.core.Octopus;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        String url = "https://movie.douban.com/subject/27186348/reviews";
        Octopus.init(url).interval(5).parser(DouBanParser.class).start();
    }
}
