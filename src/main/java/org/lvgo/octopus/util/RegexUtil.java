package org.lvgo.octopus.util;

import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 14:59
 */
public class RegexUtil {

    public static boolean equals(String comparison, String content) {
        return content.equals(comparison);
    }

    public static boolean contain(String comparison, String content) {
        return Pattern.matches(".*" + comparison + ".*", content);
    }

    public static boolean beginWith(String comparison, String content) {
        return Pattern.matches("^" + comparison + ".*", content);
    }

    public static boolean endWith(String comparison, String content) {
        return Pattern.matches("^.*" + comparison + "$", content);
    }

}
