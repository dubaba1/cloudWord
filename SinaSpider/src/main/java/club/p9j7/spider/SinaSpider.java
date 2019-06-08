package club.p9j7.spider;

import club.p9j7.model.SinaNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class SinaSpider implements PageProcessor {
    public Pipeline sinaPipeline;
    private Site site = Site.me().setUserAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5").setRetryTimes(3).setSleepTime(100).setTimeOut(10000);
    private static Logger logger = LoggerFactory.getLogger(SinaSpider.class);
    //https://feed.mix.sina.com.cn/api/roll/get?pageid=153&lid=2509&k=&num=50&page=1&r=0.05788510884168385&callback=jQuery1112048951531526572656_1556626211539&_=1556626211540
    private static String urlBase = "https://feed.mix.sina.com.cn/api/roll/get\\S+";
    //https://finance.sina.com.cn/stock/usstock/c/2019-04-07/doc-ihvhiqax0622211.shtml
    private static String urlDetail = "https://[a-z]+\\.sina\\.com\\.cn/\\S+.shtml";

    @Autowired
    public SinaSpider(Pipeline sinaPipeline) {
        this.sinaPipeline = sinaPipeline;
    }


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(urlBase).match()) {
            List<String> pgList = new ArrayList<>();
            String content = page.getHtml().get();
            Pattern pattern = Pattern.compile("\"url\":\"(https:\\\\/\\\\/[a-z]+\\.sina\\.com\\.cn\\\\/\\S{1,70}\\.shtml)");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                pgList.add(matcher.group(1).replaceAll("\\\\", ""));
            }
            page.addTargetRequests(pgList);
        }
        if (page.getUrl().regex(urlDetail).match()) {
            SinaNews sinaNews = new SinaNews();
            sinaNews.setTitle(page.getHtml().xpath("//h1/text(0)").toString());
            sinaNews.setNewsTime(LocalDateTime.parse(page.getHtml().xpath("//div[@class='date-source']/span[@class='date']/text(0)").toString(), DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")));
            sinaNews.setContent(page.getHtml().xpath("//div[@class='article']/p/text(0)").all().toString());
            sinaNews.setKeyword(page.getHtml().xpath("//div[@class='keywords']/a/text(0)").all().toString());
            System.out.println(sinaNews.toString());
            page.putField("sinaNews", sinaNews);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
