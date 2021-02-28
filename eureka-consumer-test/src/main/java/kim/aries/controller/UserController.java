package kim.aries.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import kim.aries.modal.CommonResult;
import kim.aries.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@RestController
@RequestMapping("/test2")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

        private static final String serviceOneName = "http://question-bank-provider";
//    private static final String serviceOneName = "http://localhost:8002";
    //    private static final String serviceTwoName = "http://judge-provider";
    private static final String serviceTwoName = "http://localhost:8001";

    @RequestMapping(value = "/user")
    public User getUser() {
        CommonResult<User> result = restTemplate.getForObject(serviceOneName + "/test1/user", CommonResult.class);
        User user = JSON.parseObject(JSON.toJSONString(result.getData()), User.class);
        return user;
    }
}
