package club.magicfun.aquila;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		String jsonpStr = page.getHtml();
		//System.out.println(jsonpStr);
		
		String jsonStr = jsonpStr.substring(12, jsonpStr.length() - 1);
		System.out.println((unicodeToString(jsonStr).replace("\t", "").replace(" ", "")));
		//System.out.println(convert(jsonStr).replace("\t", ""));
		
		//JSONObject json = new JSONObject(jsonStr);
		//System.out.println(json);
		
		return null; 
	}
	
	private String convert(String utfString){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		
		while((i=utfString.indexOf("\\u", pos)) != -1){
			sb.append(utfString.substring(pos, i));
			if(i+5 < utfString.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
			}
		}
		
		return sb.toString();
	}
	

	private static String unicodeToString(String str) {
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
	    Matcher matcher = pattern.matcher(str);
	    char ch;
	    while (matcher.find()) {
	        ch = (char) Integer.parseInt(matcher.group(2), 16);
	        str = str.replace(matcher.group(1), ch + "");    
	    }
	    return str;
	}

	public static void main(String[] args) throws Exception {

		//String crawlPath = "/Users/apple/data/";
		String crawlPath = "C:\\temp\\";
		System.out.println("crawlPath: " + crawlPath);
		
		DemoJsonCrawler crawler = new DemoJsonCrawler(crawlPath);
		crawler.addSeed("http://zhi.taobao.com/seller/json/getSigninDetail.htm?day=20150619&appId=1%2C7%2C9&_ksTS=1433824780590_75&callback=jsonp76");

		crawler.start(1);
	}

}
