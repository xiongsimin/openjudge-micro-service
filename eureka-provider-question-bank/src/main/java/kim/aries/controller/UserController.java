package kim.aries.controller;

import kim.aries.modal.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kim.aries.modal.User;
import org.springframework.web.client.RestTemplate;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@RestController
@RequestMapping(value = "/test1")
public class UserController {

    @RequestMapping(value = "/user")
    public CommonResult<User> getUser() {
        User user = new User("xiongsm");
        CommonResult<User> result = new CommonResult<>();
        result.setStatus("200");
        result.setMsg("成功");
        result.setData(user);
        System.out.println("这是test1");
        return result;
    }
}
