package com.tianmaying.crawler;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.tianmaying.crawler.model.Song;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.ImmutableMap;
import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;

public class HtmlParser {

    private static final HtmlFetcher HTML_FETCHER = new HtmlFetcher();
    private static final String BASE_URL = "http://music.163.com/";
    private static final String text = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";

    public List<WebPage> parsePlaylists(String url) {
        Document document = Jsoup.parse(HTML_FETCHER.fetch(url));
//        System.out.println(document);
        Elements playlists = document.select("p.dec a");
        List<WebPage> list = new ArrayList<>();
        for (Element element : playlists) {
            WebPage webPage = new WebPage();
            //System.out.println(BASE_URL+ element.attr("href"));
            webPage.setUrl(BASE_URL + element.attr("href"));
            webPage.setType(PageType.playlist);
            webPage.setStatus(WebPage.Status.uncrawl);
            list.add(webPage);
        }
        return list;
    }

    public List<WebPage> parsePlaylist(String url) {
        Document document = Jsoup.parse(HTML_FETCHER.fetch(url));
//        System.out.println(document);
        Elements playlists = document.select("ul.f-hide a");
        List<WebPage> songs = new ArrayList<>();

        for (Element element : playlists) {
            WebPage webPage = new WebPage();
//            System.out.println(BASE_URL+ element.attr("href"));
            webPage.setUrl(BASE_URL + element.attr("href"));
            webPage.setType(PageType.song);
            webPage.setTitle(element.text());
            webPage.setStatus(WebPage.Status.uncrawl);
            songs.add(webPage);
        }
        return songs;

    }


    public Long parseSong(String url) {
        String urlAPI = "http://music.163.com/api/v1/resource/comments/R_SO_4_" + url.split("=")[1];
        Document document = Jsoup.parse(HTML_FETCHER.fetch(urlAPI));
        String content =document.text();

        String comment=content.substring(content.length()-30,content.length()-1);
//        System.out.println(comment);
        String s1=comment.replaceAll("[^0-9]", "");
        return Long.parseLong(s1);
    }


    public static <T> void main(String[] args) throws Exception {

        String playLists = "http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0";
        String songs = "http://music.163.com/playlist?id=454016843";
        String singleSong = "http://music.163.com/song?id=29999506";
        HtmlParser htmlParser = new HtmlParser();
//        htmlParser.parsePlaylists(playLists).forEach(playlist -> System.out.println(playlist));
//        htmlParser.parsePlaylist(singleSong);
//        System.out.println(htmlParser.parseSong(singleSong));
        System.out.println(htmlParser.parseSong(singleSong));

    }

}