package kim.aries.controller;

import com.alibaba.fastjson.JSON;
import kim.aries.modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    RestTemplate restTemplate;
    private static final String questionBankProviderService = "http://question-bank-provider";
    private static final String judgeProviderService = "http://judge-provider";

    @PostMapping(value = "/doJudge")
    public JudgeResult doJudge(@RequestBody Map map, HttpServletRequest request) {
        String topicId = (String) map.get("topic_id");
        String code = (String) map.get("code");
        //从题库微服务查询用例
        CommonResult<Sample> result = restTemplate.getForObject(questionBankProviderService + "/topic_sub/getSamples?topic_id=" + topicId, CommonResult.class);
        List<Sample> samples = JSON.parseArray(JSON.toJSONString(result.getData()), Sample.class);
        //从评测微服务获取执行结果
        JudgeRequest judgeRequest = new JudgeRequest("java", code, samples);
        CommonResult<JudgeResult> judgeResultCommonResult = restTemplate.postForObject(judgeProviderService + "/judge/judgeCode", judgeRequest, CommonResult.class);
        JudgeResult judgeResult = JSON.parseObject(JSON.toJSONString(judgeResultCommonResult.getData()), JudgeResult.class);
        return judgeResult;
    }

}
