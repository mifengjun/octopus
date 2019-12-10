package org.lvgo.octopus.core;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicatorTest {

    @Test
    public void copy() {
        Duplicate duplicate = Duplicator.url("http://www.baidu.com").copy();
        Assert.assertTrue(duplicate.isSuccess());
    }

    @Test
    public void url() {
        Duplicator.url("http://www.baidu.com");
    }

    @Test
    public void getUrl() {
    }
}