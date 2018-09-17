package com.tianmaying.crawler.repository;

import com.tianmaying.crawler.model.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 14:22
 **/
public interface WebPageRepository extends JpaRepository<WebPage, String> {
    WebPage findTopByStatus(com.tianmaying.crawler.model.WebPage.Status status);
}