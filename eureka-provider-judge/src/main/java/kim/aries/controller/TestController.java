package kim.aries.controller;

import kim.aries.modal.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author aries
 * @Data 2021-02-27
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Value("${server.port}")
    int port;

    @RequestMapping(value = "/printHello")
    public String printHello() {
        User user = new User("xiongsm");
        return "hello" + "server port:" + port + " 用户：" + user.getName();
    }

    @RequestMapping(value = "/user")
    public User getUser() {
        return new User("xiongsm");
    }
}
