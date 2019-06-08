package club.p9j7.spider;

import club.p9j7.mapper.SinaNewsMapper;
import club.p9j7.model.SinaNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class SinaPipeline implements Pipeline {
    @Autowired
    private SinaNewsMapper sinaNewsMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        SinaNews sinaNews = resultItems.get("sinaNews");
        if (sinaNews != null) {
            sinaNewsMapper.insertNews(resultItems.get("sinaNews"));
        }
    }
}
