package kim.aries.controller;

import kim.aries.modal.CommonResult;
import kim.aries.modal.JudgeRequest;
import kim.aries.modal.JudgeResult;
import kim.aries.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@RestController
@RequestMapping("/judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;

    @PostMapping(value = "/judgeCode")
    public CommonResult<JudgeResult> judgeCode(@RequestBody JudgeRequest judgeRequest) {
        CommonResult<JudgeResult> resultCommonResult = new CommonResult<>();
        JudgeResult result = null;
        try {
            result = judgeService.doJudge(judgeRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultCommonResult.setStatus("200");
        resultCommonResult.setMsg("成功");
        resultCommonResult.setData(result);
        return resultCommonResult;
    }
}
