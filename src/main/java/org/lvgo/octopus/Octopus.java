package org.lvgo.octopus;

import java.io.IOException;
import java.io.Serializable;

/**
 * 爬虫核心
 * <p>
 * 包含整体流程 :
 * <p>
 * 0. 创建一个 Octopus 初始化一系列参数
 * 　　start
 * 　　　↓
 * 1. 放入一个url
 * 　　　↓
 * 　　　↓
 * 2. 经过 {@link Simulator} 中的 jsoup 处理 输出 document 给 parser
 * 　　　↓
 * 　　　↓
 * 3. @{@link Parser} 处理过后返回结果为 data
 * 　　　↓
 * 　　　↓
 * 4. 将{@link Data} 交给 {@link Handler} 在处理
 * 　　　↓
 * 　　end
 * 完成整个数据抓取的开始到结束过程
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 14:51
 */
public class Octopus implements Serializable {

    private static final long serialVersionUID = 9143660675316418503L;
    /**
     * 浏览器模拟器
     * <p>
     * 输入url 输出 网页
     */
    private Simulator simulator;
    /**
     * 网页解析器
     * <p>
     * 输入网页 输出 自定义数据信息
     */
    private Parser parser;
    /**
     * 数据处理器
     * <p>
     * 输入数据信息 输出结果
     */
    private Handler handler;

    /**
     * 请求载体
     */
    private Request request = new Request();


    public Octopus() {
    }

    public Octopus(String url) {
        this.request.putUrl(url);
        this.simulator = new Simulator();
    }

    /**
     * 初始化
     *
     * @return 章鱼
     */
    public static Octopus init(String url) {
        return new Octopus(url);
    }

    public Octopus simulator(Simulator simulator) {
        this.simulator = simulator;
        return this;
    }

    public Octopus parser(Class clazz) {
        try {
            this.parser = (Parser) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return this;
    }

    public void start() throws IOException {
        String url = request.getUrl();
        if (url == null) {
            return;
        }
        // 下载页面, 结果存储在 #simulator.response 中
        simulator.downLoad(url);

        // 解析页面, 输入 simulator.response 输出 data
        parser.parse(request, simulator.getResponse());
        // 如果地址不为空, 继续爬取
        start();
    }

    /**
     * 设置数据处理
     *
     * @param handler 数据处理
     * @return 章鱼
     */
    public Octopus handler(Handler handler) {
        this.handler = handler;
        return this;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public Parser getParser() {
        return parser;
    }

    public Handler getHandler() {
        return handler;
    }

}
