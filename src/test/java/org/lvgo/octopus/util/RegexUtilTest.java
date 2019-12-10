package org.lvgo.octopus.util;

import org.junit.Assert;
import org.junit.Test;

public class RegexUtilTest {

    private String content = "Ab@C#$$F^2%&g3*h*i5g%#K66l7M7n8o8p9Q9rst0U0v0W1x4yz";

    @Test
    public void equals() {
        Assert.assertFalse(RegexUtil.equals("", content));
    }

    @Test
    public void contain() {
        Assert.assertTrue(RegexUtil.contain("\\^2%", content));
    }

    @Test
    public void beginWith() {
        Assert.assertTrue(RegexUtil.beginWith("A", content));
    }

    @Test
    public void endWith() {
        Assert.assertTrue(RegexUtil.endWith("z", content));
    }

}