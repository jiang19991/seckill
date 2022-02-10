package com.nowcoder.seckill;

import com.nowcoder.seckill.entity.User;
import com.nowcoder.seckill.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.concurrent.TimeUnit;

//@SpringBootTest
class SeckillApplicationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testRedisTemplate() {
        redisTemplate.opsForValue().set("name:1", "Tom");
        System.out.println(redisTemplate.opsForValue().get("name:1"));
        redisTemplate.opsForValue().set("user:15", userService.findUserById(15));
        User user = (User) redisTemplate.opsForValue().get("user:15");
        System.out.println(user);

        redisTemplate.opsForHash().put("user:1","name","jby");
        redisTemplate.expire("user:1",60, TimeUnit.SECONDS);
    }

    @Test
    public void testTransactional() {
        Object result = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";

                operations.multi();
                operations.opsForSet().add(redisKey, "zhangsan");
                operations.opsForSet().add(redisKey, "lisi");
                operations.opsForSet().add(redisKey, "wangwu");

                System.out.println(operations.opsForSet().members(redisKey));

                return operations.exec();
            }
        });
        System.out.println(result);
    }

}
