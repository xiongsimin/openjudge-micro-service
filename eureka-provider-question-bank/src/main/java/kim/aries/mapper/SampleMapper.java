package kim.aries.mapper;

import kim.aries.modal.Sample;

import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-10
 */
public interface SampleMapper {
    /**
     * 根据题目查询测试用例
     *
     * @param topicId
     * @return
     */
    List<Sample> findSampleByTopicId(Long topicId);
}
