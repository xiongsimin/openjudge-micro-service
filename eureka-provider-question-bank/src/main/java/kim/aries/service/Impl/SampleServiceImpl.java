package kim.aries.service.Impl;

import kim.aries.modal.Sample;
import kim.aries.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import kim.aries.mapper.SampleMapper;

import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@Service("sampleService")
public class SampleServiceImpl implements SampleService {
    @Autowired
    SampleMapper sampleMapper;

    //value指定缓存位置名称
    @Override
//    @Cacheable(value = "SamplesCache", key = "'findSampleByTopicId['+#topicId+']'")
    public List<Sample> findSampleByTopicId(Long topicId){
        return sampleMapper.findSampleByTopicId(topicId);
    }
}
