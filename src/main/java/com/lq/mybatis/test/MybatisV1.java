package com.lq.mybatis.test;

import com.lq.mybatis.po.User;
import org.junit.Test;

import java.util.List;

public class MybatisV1 {

    @Test
    public void test() {
        List<User> uints = selectList();
    }

    private List<User> selectList() {
        return null;
    }
}
