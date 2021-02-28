package kim.aries.service;

import kim.aries.modal.Sample;

import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-28
 */
public interface SampleService {

    List<Sample> findSampleByTopicId(Long topicId);
}
