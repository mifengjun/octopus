package org.lvgo.octopus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UrlSchedulerTest {

    @Test
    void add() {
        UrlScheduler urlScheduler = UrlScheduler.getInstance("123123");

        Node node = new Node();
        node.addUrl("1111");
        node.addUrl("2222");
        node.addUrl("3333");
        Node root = urlScheduler.root().addNode(node);

        Assertions.assertEquals(root, node.parent());

        Assertions.assertEquals(urlScheduler.size(), node.size());
        Assertions.assertEquals(root.size(), 4);

        node.getUrls().forEach(Node.Url::marker);

        Assertions.assertEquals(node.markerSize(), 3);
        Assertions.assertEquals(node.unMarkerSize(), 1);

        Assertions.assertTrue(root.isRoot());
        Assertions.assertFalse(node.isRoot());

        Assertions.assertEquals(root.getDepth(), 0);
        Assertions.assertEquals(node.getDepth(), 1);
    }
}