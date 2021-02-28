package kim.aries.modal;


public class Topic {
    private Long id;//编号
    private int time_limit;//时间限制(秒)
    private int memory_limit;//内存限制(MB)
    private String title;//标题
    private String content;//题干
    private String input_intro;//输入说明
    private String output_intro;//输出说明
    private String input_sample;//样例（输入）
    private String output_sample;//样例（输出）
    private String info;//说明（数据范围、提示等）


    public Long getId() {
        return id;
    }


    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public int getMemory_limit() {
        return memory_limit;
    }

    public void setMemory_limit(int memory_limit) {
        this.memory_limit = memory_limit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInput_intro() {
        return input_intro;
    }

    public void setInput_intro(String input_intro) {
        this.input_intro = input_intro;
    }

    public String getOutput_intro() {
        return output_intro;
    }

    public void setOutput_intro(String output_intro) {
        this.output_intro = output_intro;
    }

    public String getInput_sample() {
        return input_sample;
    }

    public void setInput_sample(String input_sample) {
        this.input_sample = input_sample;
    }

    public String getOutput_sample() {
        return output_sample;
    }

    public void setOutput_sample(String output_sample) {
        this.output_sample = output_sample;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", time_limit=" + time_limit +
                ", memory_limit=" + memory_limit +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", input_intro='" + input_intro + '\'' +
                ", output_intro='" + output_intro + '\'' +
                ", input_sample='" + input_sample + '\'' +
                ", output_sample='" + output_sample + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
