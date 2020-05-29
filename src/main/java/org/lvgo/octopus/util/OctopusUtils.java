package org.lvgo.octopus.util;

/**
 * 工具杂项包
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/29 14:20
 */
public class OctopusUtils {


    /**
     * 间隔时间
     *
     * @param seconds 单位:秒
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
