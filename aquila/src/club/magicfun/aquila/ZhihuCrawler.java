package club.magicfun.aquila;

import java.util.regex.Pattern;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class ZhihuCrawler extends BreadthCrawler {

	public ZhihuCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
	}

	@Override
	public void visit(Page page, Links links) {
        String question_regex="^http://www.zhihu.com/question/[0-9]+";
        if(Pattern.matches(question_regex, page.getUrl())){
            System.out.println("正在抽取"+page.getUrl());
            /*抽取标题*/
            String title=page.getDoc().title();
            System.out.println(title);
            /*抽取提问内容*/
            String question=page.getDoc().select("div[id=zh-question-detail]").text();
            System.out.println(question);
        }
	}

	public static void main(String[] args) throws Exception {

		ZhihuCrawler crawler=new ZhihuCrawler("/Users/apple/leo", false);  
        crawler.addSeed("http://www.zhihu.com/question/21962447");  
        crawler.addRegex("http://www.zhihu.com/.*");  
        crawler.start(5);     
	}

}
