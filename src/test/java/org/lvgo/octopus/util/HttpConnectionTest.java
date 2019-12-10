package org.lvgo.octopus.util;

import org.junit.Test;
import org.lvgo.octopus.core.Duplicate;


/**
 * {@link org.lvgo.octopus.util.HttpConnection}
 */
public class HttpConnectionTest {

    private String url = "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9725233622329927174%22%7D&n_type=0&p_from=1";


    @Test
    public void building() {
        HttpConnection building = HttpConnection.building(url);
        System.out.println("building = " + building);
    }

    @Test
    public void header() {
        HttpConnection header = HttpConnection.building(url).header("", "");
    }

    @Test
    public void connect() {
        Duplicate connect = HttpConnection.building(url).connect();
        System.out.println("connect = " + connect);
    }
}