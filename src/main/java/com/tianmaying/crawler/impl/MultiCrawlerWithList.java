package com.tianmaying.crawler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.tianmaying.crawler.Crawler;
import com.tianmaying.crawler.model.Song;
import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;

public class MultiCrawlerWithList implements Crawler {

    public List<WebPage> crawlerList;
    public List<Song> songs = new ArrayList<>();
    public static final Integer MAX_THREADS = 20;

    @Override
    public void initCrawlerList() {
      //your code here
        crawlerList = new ArrayList<WebPage>();
        crawlerList.add(new WebPage("http://music.163.com/playlist?id=454016843", PageType.playlist));
    }

    @Override
    public synchronized WebPage getUnCrawlPage() {
        //your code here
        if(crawlerList.isEmpty())
            return null;
        WebPage webPage=crawlerList.remove(0);

        return webPage;
    }

    @Override
    public List<WebPage> addToCrawlList(List<WebPage> webPages) {
      //your code here
        for (WebPage w:webPages
                ) {
            this.crawlerList.add(w);
        }
        return null;
    }

    @Override
    public Song saveSong(Song song) {
      //your code here
        this.songs.add(song);
        return null;
    }

    @Override
    public void doRun() {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < MAX_THREADS; i++) {
            executorService.execute(new MultiCrawlerThread(this));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Song> getSongs() {
      //your code here
        return songs;
    }
    
    public static <T> void main(String[] args) throws Exception {
        Date startTime = new Date();
        Crawler crawler = new MultiCrawlerWithList();
        crawler.run();
        for(Song song : crawler.getSongs()) {
            System.out.println(song);
        }
        System.out.println("花费时间：" + (new Date().getTime() - startTime.getTime()));
    }

}
