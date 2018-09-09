package com.tianmaying.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class HtmlFetcher {

    public String fetch(String url) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).timeout(3000).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        return response.body();
    }

    public static <T> void main(String[] args) throws Exception {
        HtmlFetcher htmlFetcher = new HtmlFetcher();
//        System.out.println(htmlFetcher.fetch(
//                "http://music.163.com/#/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0"));
    }

}