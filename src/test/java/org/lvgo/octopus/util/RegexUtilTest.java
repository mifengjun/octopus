package org.lvgo.octopus.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegexUtilTest {

    private String content = "Ab@C#$$F^2%&g3*h*i5g%#K66l7M7n8o8p9Q9rst0U0v0W1x4yz";

    @Test
    void equals() {
        Assertions.assertFalse(RegexUtil.equals("", content));
    }

    @Test
    void contain() {
        Assertions.assertTrue(RegexUtil.contain("\\^2%", content));
    }

    @Test
    void beginWith() {
        Assertions.assertTrue(RegexUtil.beginWith("A", content));
    }

    @Test
    void endWith() {
        Assertions.assertTrue(RegexUtil.endWith("z", content));
    }

}