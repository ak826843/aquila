package club.magicfun.aquila;

import org.json.JSONObject;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

public class DemoJsonCrawler extends DeepCrawler {

	public DemoJsonCrawler(String crawlPath) {
		super(crawlPath);

		HttpRequesterImpl requester = (HttpRequesterImpl) this.getHttpRequester();

		requester.setMethod("POST");
		requester.setCookie("cookie");
		requester.addHeader("xxx", "xxxxxxxx");
	}

	@Override
	public Links visitAndGetNextLinks(Page page) {

		String jsonStr = page.getHtml();
		System.out.println(jsonStr);
		
		return null; 
		
		/*
		JSONObject json = new JSONObject(jsonStr);
		String ip = json.getString("ip");
		int port = json.getInt("port");
		System.out.println("JSON:" + jsonStr.trim() + "\n" + "JSON ip=" + ip + "  port=" + port);
		return null;
		*/
	}

	public static void main(String[] args) throws Exception {

		DemoJsonCrawler crawler = new DemoJsonCrawler("/home/hu/data/wb");
		crawler.addSeed("http://tui.taobao.com/recommend?sellerid=109725928&itemid=27563020249&buyerid=%208GNDI0hNQ4CAYxf6wNfkHF6&catid=50009529&cna=%208GNDI0hNQ4CAYxf6wNfkHF6&areaid=tad_first_area&_ksTS=1433488349382_761&callback=jsonp762&appid=152");

		crawler.start(1);

	}

}
