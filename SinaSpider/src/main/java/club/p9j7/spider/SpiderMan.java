package club.p9j7.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class SpiderMan {
    public SinaSpider sinaSpider;

    @Autowired
    public SpiderMan(SinaSpider sinaSpider) {
        this.sinaSpider = sinaSpider;
    }

    public void crawlNews() {
        Spider newsSpider = Spider.create(sinaSpider);
        for (int i = 109; i <=399; i++) {
            newsSpider.addUrl("https://feed.mix.sina.com.cn/api/roll/get?pageid=153&lid=2509&k=&num=50&page=" + i + "&r=0.05788510884168385&callback=jQuery1112048951531526572656_1556626211539&_=1556626211540");
        }
        newsSpider.addPipeline(sinaSpider.sinaPipeline).thread(5).run();
    }

}
