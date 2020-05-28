package org.lvgo.octopus;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 16:15
 */
public class Request {

    /**
     * 请求头
     */
    private Map<String, String> headers = new HashMap<>(5);


    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * 请求头, 提供单个请求头设置, key value 格式
     */
    public void header(String key, String value) {
        headers.put(key, value);
    }
}
