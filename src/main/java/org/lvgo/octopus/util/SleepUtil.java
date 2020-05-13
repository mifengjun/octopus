package org.lvgo.octopus.util;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞工具
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/26 17:39
 */
public class SleepUtil {

    /**
     * 阻塞n秒
     *
     * @param seconds 秒数
     */
    public static void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }
    }
}
