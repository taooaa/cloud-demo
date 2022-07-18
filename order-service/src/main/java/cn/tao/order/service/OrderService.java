package cn.tao.order.service;

import cn.tao.clients.UserClient;
import cn.tao.order.mapper.OrderMapper;
import cn.tao.order.pojo.Order;
import cn.tao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.利用feign调用
        User user = userClient.findById(order.getUserId());
        //3.封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
