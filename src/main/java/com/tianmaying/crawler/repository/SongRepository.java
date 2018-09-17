package com.tianmaying.crawler.repository;

import com.tianmaying.crawler.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 14:22
 **/
public interface SongRepository extends JpaRepository<Song, String> {
}