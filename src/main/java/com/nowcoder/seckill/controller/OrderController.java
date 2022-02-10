package com.nowcoder.seckill.controller;

import com.nowcoder.seckill.common.ErrorCode;
import com.nowcoder.seckill.common.ResponseModel;
import com.nowcoder.seckill.entity.User;
import com.nowcoder.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
@CrossOrigin(origins = "${nowcoder.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class OrderController implements ErrorCode {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel create(/*HttpSession session, */
            int itemId, int amount, Integer promotionId, String token) {
//        User user = (User) session.getAttribute("loginUser");
        User user = (User) redisTemplate.opsForValue().get(token);
        orderService.createOrder(user.getId(), itemId, amount, promotionId);
        return new ResponseModel();
    }

}
