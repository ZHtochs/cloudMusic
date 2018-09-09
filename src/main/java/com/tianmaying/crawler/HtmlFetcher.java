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
        return null;
    }

    public static <T> void main(String[] args) throws Exception {
        HtmlFetcher htmlFetcher = new HtmlFetcher();

    }

}