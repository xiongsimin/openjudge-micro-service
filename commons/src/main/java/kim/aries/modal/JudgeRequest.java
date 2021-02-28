package kim.aries.modal;

import java.io.Serializable;
import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-15
 * @Description
 */
public class JudgeRequest implements Serializable {
    //编程语言
    private String language;
    //源代码内容
    private String code;
    private List<Sample> samples;

    public JudgeRequest() {
    }


    public JudgeRequest(String language, String code, List<Sample> samples) {
        this.language = language;
        this.code = code;
        this.samples = samples;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    @Override
    public String toString() {
        return "JudgeRequest{" +
                "language='" + language + '\'' +
                ", code='" + code + '\'' +
                ", samples=" + samples +
                '}';
    }
}
