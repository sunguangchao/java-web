import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by 11981 on 2017/4/16.
 */
public class GithubRepoPageProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    public void process(Page page){
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null){
            page.setSkip(true);
        }
        page.putField("readme",page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }

    public Site getSite(){
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
     //           .addPipeline(new JsonFilePipeline("F:\\wait_to_realise\\spider"))
                .thread(5)
                .run();
    }
}
