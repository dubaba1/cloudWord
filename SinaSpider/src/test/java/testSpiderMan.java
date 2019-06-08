import club.p9j7.Application;
import club.p9j7.spider.SpiderMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class testSpiderMan {
    @Autowired
    SpiderMan spiderMan;

    @Test
    public void testNews(){
        spiderMan.crawlNews();
    }
}
