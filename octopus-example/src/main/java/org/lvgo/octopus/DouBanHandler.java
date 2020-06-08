package org.lvgo.octopus;

import org.lvgo.octopus.assist.OctopusData;
import org.lvgo.octopus.core.Handler;
import org.lvgo.octopus.douban.Comment;

/**
 * //TODO 描述此类作用
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/6/8 10:38
 */
public class DouBanHandler implements Handler {
    /**
     * 处理解析出来的数据
     *
     * @param octopusData 解析出来的数据对象
     */
    @Override
    public void handler(OctopusData octopusData) {
        Comment comment = (Comment) octopusData;
        if (comment.getType() == 0) {

        }
    }
}
