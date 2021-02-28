package kim.aries.controller;

import kim.aries.modal.CommonResult;
import kim.aries.modal.Sample;
import kim.aries.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@RestController
@RequestMapping(value = "/topic_sub")
public class TopicController {
    @Value("${server.port}")
    int port;

    @Autowired
    SampleService sampleService;

    @GetMapping("/getSamples")
    @ResponseBody
    public CommonResult<List<Sample>> getSamples(HttpServletRequest request) throws IOException {
        System.out.println(port);
        //根据题目Id查询题目实例
        Long topicId = Long.parseLong(request.getParameter("topic_id"));
        // 集成Redis缓存
        List<Sample> sampleList = sampleService.findSampleByTopicId(topicId);
        CommonResult<List<Sample>> result = new CommonResult<>();
        result.setStatus("200");
        result.setMsg("成功");
        result.setData(sampleList);
        return result;
    }
}
