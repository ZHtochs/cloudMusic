package com.tianmaying.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class HtmlFetcher {

    public String fetch(String url) {
        if(url==null)
            return null;
        Connection.Response response = null;

        response = Jsoup.connect(url).timeout(3000).execute();


        System.out.println(response.body());
        return null;
    }

    public static <T> void main(String[] args) throws Exception {
        HtmlFetcher htmlFetcher = new HtmlFetcher();
        System.out.println(htmlFetcher.fetch("http://music.163.com/#/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0"));

    }

}