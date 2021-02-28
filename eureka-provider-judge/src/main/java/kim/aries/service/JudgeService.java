package kim.aries.service;

import kim.aries.modal.JudgeRequest;
import kim.aries.modal.JudgeResult;

import java.io.IOException;

/**
 * @Author aries
 * @Data 2021-02-28
 */
public interface JudgeService {
    JudgeResult doJudge(JudgeRequest judgeRequest) throws IOException, InterruptedException;
}
