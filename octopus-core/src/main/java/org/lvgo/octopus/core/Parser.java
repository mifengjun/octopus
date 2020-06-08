package org.lvgo.octopus.core;

import org.lvgo.octopus.assist.OctopusData;
import org.lvgo.octopus.assist.Request;
import org.lvgo.octopus.assist.Response;

/**
 * octopus 分析器, 将模拟器的返回值作为输入, 网页内容作为输出
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 14:22
 */
public interface Parser {

    /**
     * 解析下载的返回值
     *
     * @param request  请求载体
     * @param response simulator 下载到的 response
     * @return 解析的结果数据
     */
    OctopusData parse(Request request, Response response);
}
