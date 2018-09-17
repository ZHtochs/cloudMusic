package com.tianmaying.crawler.repository;

import com.tianmaying.crawler.model.WebPage;
import com.tianmaying.crawler.model.WebPage.PageType;
import com.tianmaying.crawler.model.WebPage.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 14:22
 **/
public interface WebPageRepository extends JpaRepository<WebPage, String> {
    WebPage findTopByStatus(Status status);
    long countByStatus(Status status);
    List<WebPage> findByType(PageType pageType);
}