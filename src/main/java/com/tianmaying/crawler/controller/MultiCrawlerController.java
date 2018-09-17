package com.tianmaying.crawler.controller;

import com.tianmaying.crawler.Crawler;
import com.tianmaying.crawler.impl.MultiCrawlerWithList;
import com.tianmaying.crawler.model.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 10:43
 **/
@RestController
@RequestMapping("/multi")
public class MultiCrawlerController {

    private final Crawler crawler = new MultiCrawlerWithList();

    @GetMapping("/start")
    public String start() throws InterruptedException {
        crawler.run();
        return "爬取完毕";
    }
    @GetMapping("/songs")
    public List<Song> listSongs(){
        return crawler.getSongs();
    }

}