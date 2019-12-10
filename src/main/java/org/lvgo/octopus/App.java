package org.lvgo.octopus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        JSONObject jsonObject = JSONObject.parseObject("{\"ns\":\"pl.content.homeFeed.index\",\"domid\":\"Pl_Official_MyProfileFeed__22\"}");
        System.out.println(jsonObject.get("domid"));
    }
}
