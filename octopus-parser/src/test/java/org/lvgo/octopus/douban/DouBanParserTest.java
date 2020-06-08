package org.lvgo.octopus.douban;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;

class DouBanParserTest {

    @Test
    void parse() {
        String url = "https://movie.douban.com/subject/27186348/reviews";
        Octopus.init(url).interval(5).parser(DouBanParser.class).start();
    }
}