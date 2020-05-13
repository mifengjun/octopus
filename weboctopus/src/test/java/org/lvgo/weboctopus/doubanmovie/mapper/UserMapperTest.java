package org.lvgo.weboctopus.doubanmovie.mapper;

import org.junit.jupiter.api.Test;
import org.lvgo.weboctopus.WebOctopusApplicationTests;
import org.lvgo.weboctopus.doubanmovie.bean.User;

import javax.annotation.Resource;
import java.util.List;

class UserMapperTest extends WebOctopusApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void getUserList() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }
}