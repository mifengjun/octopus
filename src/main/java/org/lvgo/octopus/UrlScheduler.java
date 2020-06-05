package org.lvgo.octopus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
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
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/6/3 10:54
 */
public class UrlScheduler {

    static ConcurrentHashMap<Integer, Node> targetUrlNode = new ConcurrentHashMap<>();
    static AtomicInteger size = new AtomicInteger(0);
    static AtomicInteger markerSize = new AtomicInteger(0);
    private Node root;

    private UrlScheduler(Node node) {
        root = node;
    }

    public static UrlScheduler getInstance(String url) {
        if (SingletonUrlScheduler.urlScheduler == null) {
            Node node = new Node();
            node.addUrl(url);
            SingletonUrlScheduler.urlScheduler = new UrlScheduler(node);
            UrlScheduler.targetUrlNode.put(node.hashCode(), node);
        }
        return SingletonUrlScheduler.urlScheduler;
    }

    public Integer size() {
        return UrlScheduler.size.get();
    }

    public Integer markerSize() {
        return UrlScheduler.markerSize.get();
    }

    public Integer unMarkerSize() {
        return UrlScheduler.size.get() - UrlScheduler.markerSize.get();
    }

    public Node root() {
        return root;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UrlScheduler.class.getSimpleName() + "[", "]")
                .add("targetUrlNode=" + targetUrlNode)
                .toString();
    }

    private static class SingletonUrlScheduler {
        private static UrlScheduler urlScheduler;
    }
}

class Node {
    private boolean root = true;
    private int depth;
    private List<Url> urls = new ArrayList<>(20);
    private Integer parentCode;
    private Integer childCode;

    public Integer size() {
        return UrlScheduler.size.get();
    }

    public Integer markerSize() {
        return UrlScheduler.markerSize.get();
    }

    public Integer unMarkerSize() {
        return UrlScheduler.size.get() - UrlScheduler.markerSize.get();
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void addUrl(String url) {
        UrlScheduler.size.getAndIncrement();
        urls.add(new Url(url));
    }

    public Node addNode(Node n) {
        n.parentCode = this.hashCode();
        n.setDepth(this.getDepth() + 1);
        UrlScheduler.targetUrlNode.put(n.hashCode(), n);
        this.childCode = n.hashCode();
        return this;
    }

    public Node parent() {
        return UrlScheduler.targetUrlNode.get(this.parentCode());
    }

    public Node child() {
        return UrlScheduler.targetUrlNode.get(this.childCode());
    }

    public boolean isRoot() {
        return depth == 0;
    }

    public int getDepth() {
        return depth;
    }

    private void setDepth(Integer depth) {
        this.depth = depth;
        this.root = depth == 0;
    }

    public Integer parentCode() {
        return parentCode;
    }

    public Integer childCode() {
        return childCode;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("root=" + root)
                .add("depth=" + depth)
                .add("urls=" + urls)
                .add("parentCode=" + parentCode)
                .add("childCode=" + childCode)
                .toString();
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
            UrlScheduler.markerSize.getAndIncrement();
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
