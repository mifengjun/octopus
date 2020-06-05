package org.lvgo.octopus;

import org.lvgo.octopus.util.OctopusUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调度
 * <p>
 * 深度优先搜索用一个数组存放产生的所有状态。
 * （1） 把初始状态放入数组中，设为当前状态；
 * （2） 扩展当前的状态，产生一个新的状态放入数组中，同时把新产生的状态设为当前状态；
 * （3） 判断当前状态是否和前面的重复，如果重复则回到上一个状态，产生它的另一状态；
 * （4） 判断当前状态是否为目标状态，如果是目标，则找到一个解答，结束算法。
 * （5） 如果数组为空，说明无解。
 * <p>
 * <p>
 * 设计思路即 单链结构
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/6/3 10:54
 */
public class UrlScheduler {

    /**
     * 用来统计调度器中的url个数
     */
    private AtomicInteger size = new AtomicInteger(0);
    /**
     * 用来统计调度器中的标记的url个数
     */
    private AtomicInteger markerSize = new AtomicInteger(0);
    /**
     * 根节点, #Head 节点
     */
    private Node root;

    /**
     * 当前节点, 通过调用parent|next时改变
     */
    private Node currentNode;

    private UrlScheduler(Node node) {
        root = node;
    }

    public static UrlScheduler getInstance() {
        if (SingletonUrlScheduler.urlScheduler == null) {
            OctopusUtils.sleep(1);
            getInstance();
        }
        return SingletonUrlScheduler.urlScheduler;
    }

    public static UrlScheduler getInstance(String url) {
        if (SingletonUrlScheduler.urlScheduler == null) {
            Node node = new Node();
            SingletonUrlScheduler.urlScheduler = new UrlScheduler(node);
            node.addUrl(url);
        }
        return SingletonUrlScheduler.urlScheduler;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public AtomicInteger getSize() {
        return size;
    }

    public void setSize(AtomicInteger size) {
        this.size = size;
    }

    public AtomicInteger getMarkerSize() {
        return markerSize;
    }

    public void setMarkerSize(AtomicInteger markerSize) {
        this.markerSize = markerSize;
    }

    public Integer size() {
        return getSize().get();
    }

    public Integer markerSize() {
        return getMarkerSize().get();
    }

    public Integer unMarkerSize() {
        return getSize().get() - getMarkerSize().get();
    }

    public Node root() {
        return root;
    }

    private static class SingletonUrlScheduler {
        private static UrlScheduler urlScheduler;
    }
}

/**
 * 负责提供 url调度结构中的节点对象, 包括当前节点中保存的url 和一些基本属性.
 * <p>
 * 目前使用的为对象结构, 即 UrlScheduler 对象包含一个根节点和 一个用来保存
 * <p>
 * 地址的节点对象
 *
 * @author lvgorice@gmail.com
 * @since 1.0
 */
class Node {
    /**
     * 是否为根节点, 该字段会根据树的深度来进行判断 true or false
     */
    private boolean root = true;
    /**
     * 树结构深度, 每增加一级child节点, 深度+1
     */
    private int depth;
    /**
     * 节点保存的url信息, url类为 #Node类的内部类
     */
    private List<Url> urls = new ArrayList<>(20);
    /**
     * 当前节点的父节点对象 HashCode 值, 用来在 #UrlScheduler.schedulerNodes 获取节点
     * <p>
     * 根节点该值为 null;
     */
    private Node parent;
    /**
     * 当前节点的子节点对象 HashCode 值, 用来在 #UrlScheduler.schedulerNodes 获取节点
     * <p>
     * 叶节点该值为 null;
     */
    private Node child;
    /**
     * 节点状态, 用来表示节点中的 url 全部标记或未全部标记
     * <p>
     * -需要手动标记-
     */
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer size() {
        return UrlScheduler.getInstance().getSize().get();
    }

    public Integer markerSize() {
        return UrlScheduler.getInstance().getMarkerSize().get();
    }

    public Integer unMarkerSize() {
        return UrlScheduler.getInstance().getSize().get() - UrlScheduler.getInstance().getMarkerSize().get();
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void addUrl(String url) {
        UrlScheduler.getInstance().getSize().getAndIncrement();
        // 新的地址加入, 该节点存在新的地址
        this.setState(false);
        urls.add(new Url(url));
    }

    public Node addNode(Node n) {
        n.parent = this;
        n.setDepth(this.getDepth() + 1);
        this.child = n;
        return this;
    }

    public Node parent() {
        UrlScheduler.getInstance().setCurrentNode(this.parent);
        return this.parent;
    }

    public Node child() {
        UrlScheduler.getInstance().setCurrentNode(this.child);
        return this.child;
    }

    public boolean isRoot() {
        return root;
    }

    public int getDepth() {
        return depth;
    }

    private void setDepth(Integer depth) {
        this.depth = depth;
        this.root = depth == 0;
    }

    class Url {
        private String url;
        private boolean marker;

        public Url(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public boolean isMarker() {
            return marker;
        }

        public void marker() {
            UrlScheduler.getInstance().getMarkerSize().getAndIncrement();
            this.marker = true;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Url.class.getSimpleName() + "[", "]")
                    .add("url='" + url + "'")
                    .add("marker=" + marker)
                    .toString();
        }
    }
}
