package com.tianmaying.crawler.impl;

import com.tianmaying.crawler.Crawler;
import com.tianmaying.crawler.HtmlParser;
import com.tianmaying.crawler.model.Song;
import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-16 15:56
 **/
public class BasicCrawler implements Crawler {

    private final HtmlParser htmlParser = new HtmlParser();
    public List<WebPage> crawlerList;
    public List<Song> songs = new ArrayList<>();

    @Override
    public void initCrawlerList() {
        crawlerList = new ArrayList<WebPage>();
//        for(int i = 0; i < 43; i++) {
//            crawlerList.add(new WebPage("http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset="  + (i * 35), PageType.playlists));
//        }
        crawlerList.add(new WebPage("http://music.163.com/playlist?id=454016843", PageType.playlist));
    }

    @Override
    public WebPage getUnCrawlPage() {
        // your code here
        if(crawlerList.isEmpty())
            return null;
        WebPage webPage=crawlerList.remove(0);

        return webPage;

    }

    @Override
    public List<WebPage> addToCrawlList(List<WebPage> webPages) {
        // your code here
        for (WebPage w:webPages
             ) {
            this.crawlerList.add(w);
        }
        return null;
    }

    @Override
    public Song saveSong(Song song) {
        // your code here
//        System.out.println(song);
        this.songs.add(song);
        return null;
    }

    @Override
    public void doRun() {
        // your code here
        WebPage webPage;
        while ((webPage = getUnCrawlPage()) != null) {
            String url=webPage.getUrl();
            if(webPage.getType()==PageType.playlists) {
                addToCrawlList(htmlParser.parsePlaylists(url));
            }
            if (webPage.getType()==PageType.playlist){
                addToCrawlList(htmlParser.parsePlaylist(url));
            }
            if (webPage.getType()==PageType.song){
                Song song=new Song();
                song.setCommentCount(htmlParser.parseSong(url));
                song.setUrl(url);
                song.setTitle(webPage.getTitle());
                saveSong(song);
            }
        }
    }

    @Override
    public List<Song> getSongs() {
        // your code here

        return songs;
    }

    public static <T> void main(String[] args) throws Exception {
        Date startTime = new Date();
        Crawler crawler = new BasicCrawler();
        crawler.run();
        for (Song song : crawler.getSongs()) {
            System.out.println(song);
        }
        System.out.println("花费时间：" + (new Date().getTime() - startTime.getTime()));
    }

}