package com.tianmaying.crawler.impl;

/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 14:28
 **/

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianmaying.crawler.Crawler;
import com.tianmaying.crawler.model.Song;
import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;
import com.tianmaying.crawler.model.WebPage.Status;
import com.tianmaying.crawler.repository.SongRepository;
import com.tianmaying.crawler.repository.WebPageRepository;

@Component
public class MultiCrawlerWithJpa implements Crawler {

    public static final Integer MAX_THREADS = 1;

    @Autowired
    private WebPageRepository webPageRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public void initCrawlerList() {
//        for(int i = 0; i < 43; i++) {
//            webPageRepository.saveAndFlush(new WebPage("http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset="  + (i * 35), PageType.playlists));
//        }
        WebPage webPage=new WebPage("http://music.163.com/playlist?id=2180709071", PageType.playlist);
        webPageRepository.save(webPage);
    }

    @Override
    public synchronized WebPage getUnCrawlPage() {
        WebPage webPage = webPageRepository.findTopByStatus(Status.uncrawl);
        if(webPage == null) {
            return null;
        }
        // 将页面标记为已爬，并返回页面
        webPage.setStatus(Status.crawled);
        webPageRepository.saveAndFlush(webPage);
        return webPage;
    }

    @Override
    public List<WebPage> addToCrawlList(List<WebPage> webPages) {
        // your code here
        for (WebPage w:webPages
             ) {webPageRepository.saveAndFlush(w);
        }
        return null;
    }

    @Override
    public Song saveSong(Song song) {
        // your code here
        songRepository.save(song);
        return null;
    }

    @Override
    public void doRun(){
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        for(int i = 0; i < MAX_THREADS; i++) {
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
        // your code here

        return songRepository.findAll();
    }

}