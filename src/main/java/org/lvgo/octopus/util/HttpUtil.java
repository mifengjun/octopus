package org.lvgo.octopus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * //TODO 描述此类作用
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/16 17:51
 */
public class HttpUtil {


    public static String get(String netAddr) {

        URL url;
        try {
            url = new URL(netAddr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //[请求连接超时时间]
            urlConnection.setConnectTimeout(2000);
            //[读取超时时间]
            urlConnection.setReadTimeout(2000);
            //[请求方式--默认就是get]
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder text = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    text.append(temp);
                }
                return text.toString();

            } else {
                //请求失败
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
