package org.lvgo.octopus;

import java.util.ArrayList;
import java.util.List;

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
public class UrlScheduler<N> {

    private TargetUrlNode<N> targetUrlNode;

    private UrlScheduler() {
        targetUrlNode = new TargetUrlNode<>();
    }

    public static UrlScheduler getInstance() {
        return SingleUrlScheduler.urlScheduler;
    }

    private static class TargetUrlNode<N> {
        private int depth;
        private N n;
        private List<TargetUrlNode<N>> targetUrlNodes = new ArrayList<>(20);
        private TargetUrlNode<N> parent;

        public TargetUrlNode() {
        }

        public TargetUrlNode(N n) {
            this.n = n;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public N getN() {
            return n;
        }

        public void setN(N n) {
            this.n = n;
        }

        public List<TargetUrlNode<N>> getTargetUrlNodes() {
            return targetUrlNodes;
        }

        public void setTargetUrlNodes(List<TargetUrlNode<N>> targetUrlNodes) {
            this.targetUrlNodes = targetUrlNodes;
        }

        public TargetUrlNode<N> getParent() {
            return parent;
        }

        public void setParent(TargetUrlNode<N> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "TargetUrlNode{" +
                    "depth=" + depth +
                    ", n=" + n +
                    ", targetUrlNodes=" + targetUrlNodes +
                    ", parent=" + parent +
                    '}';
        }
    }

    private static class SingleUrlScheduler {
        private static UrlScheduler urlScheduler = new UrlScheduler();
    }

    @Override
    public String toString() {
        return "UrlScheduler{" +
                "targetUrlNode=" + targetUrlNode +
                '}';
    }
}
