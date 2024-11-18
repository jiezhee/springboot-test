package com.eris.springboottest.redis;

import java.util.Set;

public class RedisTest {

    public void test() {
        RedisUtils.hasKey("kk");
        RedisUtils.set("kk", "vv");
        RedisUtils.set("kk", "vv", 1000);
        RedisUtils.hSet("myhash", "k1", "v1");
        RedisUtils.hGet("myhash", "k1");
        RedisUtils.setAdd("myset", "k1", "k2", "k3");
        RedisUtils.setContains("myset", "k4");
        RedisUtils.setRemove("myset", "k3");
        Set<String> myset = RedisUtils.setGetAll("myset");
        RedisUtils.lPop("mylist");
        RedisUtils.rPush("mylist", "k1");
        RedisUtils.lLen("mylist");
        RedisUtils.zadd("myzset", "m1", 88);
        RedisUtils.zrange("myzset", 0, 100);
    }
}
