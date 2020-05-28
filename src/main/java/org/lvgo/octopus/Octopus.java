package org.lvgo.octopus;

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


    public Octopus start() {
        Response response = simulator.downLoad();
        return this;
    }


    public Octopus(Simulator simulator) {
        this.simulator = simulator;
    }

    /**
     * 在初始化的时候放入模拟器
     *
     * @param simulator 模拟器
     * @return 章鱼
     */
    public static Octopus init(Simulator simulator) {
        return new Octopus(simulator);
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

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
