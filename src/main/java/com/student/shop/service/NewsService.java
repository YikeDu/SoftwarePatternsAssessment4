/**
 * 
 */
package com.student.shop.service;

import com.student.shop.model.News;
import com.student.shop.repository.NewsRepository;
import com.student.shop.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新闻模块Service
 * @author Student
 * @email student@test.com
 * @date 2023-3-17
 * 
 */
@Service
@Transactional
public class NewsService extends BaseService {

	@Autowired
    NewsRepository newsDao;

    public void addNews(News news){
        newsDao.save(news);
    }

    public void delNews(Integer newsId){
        newsDao.deleteById(newsId);
    }

    public List<News> findNews(Page<News> page) {
            page.setResult(newsDao.findAll(page.getPageable()).getContent());
            page.setTotalCount(newsDao.count());
            return page.getResult();
    }

    public News findById(Integer id) {
        return newsDao.getOne(id);
    }


}
