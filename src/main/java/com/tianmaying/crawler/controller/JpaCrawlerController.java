package com.tianmaying.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tianmaying.crawler.impl.MultiCrawlerWithJpa;
import com.tianmaying.crawler.model.Song;
import com.tianmaying.crawler.repository.SongRepository;
/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 14:27
 **/
@RestController
@RequestMapping("/jpa")
public class JpaCrawlerController {

    @Autowired
    private MultiCrawlerWithJpa multiCrawler;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/start")
    public String start() throws InterruptedException {
        multiCrawler.run();
        return "爬取完毕";
    }

    @GetMapping("/songs")
    public Page<Song> songs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

}