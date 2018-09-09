package com.tianmaying.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlFetcher {

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

//    public List<WebPage> parsePlaylists(String url) throws IOException{
//        HtmlFetcher HTML_FETCHER=new HtmlFetcher();
//        Document document = Jsoup.parse(HTML_FETCHER.fetch(url));
//        Elements playlists = document.select(".tit.f-thide.s-fc0");
//        return playlists.stream().map(e -> new WebPage(BASE_URL + e.attr("href"), PageType.playlist)).collect(Collectors.toList());
//    }

    public static <T> void main(String[] args) throws Exception {
        HtmlFetcher htmlFetcher = new HtmlFetcher();
        System.out.println(htmlFetcher.fetch("http://music.163.com/#/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0"));

    }

}