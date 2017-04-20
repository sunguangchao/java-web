import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by 11981 on 2017/4/20.
 */
public class CSDNBlogProcessor implements PageProcessor{

    private static final String username = "sun_blaze";

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(3000);

    public void process(Page page){
        if (!page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/\\d+").match()){
            page.addTargetRequests(page.getHtml().xpath("//div[@class='skin_list']").links()
                    .regex("/" + username + "/article/details/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/").all());
            //        page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
        }else{
            page.putField("title", page.getHtml().xpath("//div[@class='skin_list']//h3[@class='list_c_t']/a/text()"));
            page.putField("content", page.getHtml().xpath("//div[@class='skin_detail']//div[@class='markdown_views']"));
        }

    }

    public Site getSite(){
        return site;
    }

    public static void main(String[] args){
        Spider.create(new CSDNBlogProcessor())
                .addUrl("http://blog.csdn.net/sun_blaze")
                .addPipeline(new JsonFilePipeline("F:\\wait_to_realise\\spider"))
                .run();
    }

}
