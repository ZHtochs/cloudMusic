package com.tianmaying.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;
import java.util.List;

public class HtmlFetcher {
    private static String url = "https://music.163.com/playlist?id=486766852";

    public String fetch(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).timeout(3000)
                   .header("Host", "http://music.163.com")
                    .header("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")
                    .header("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Language", "zh-cn,zh;q=0.5")
                    .header("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7")
                    .header("Connection", "keep-alive")
                    .execute();
            return response.statusCode() / 100 == 2 ? response.body() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> void main(String[] args) throws Exception {


    }

}