package com.student.shop.controller;

import com.student.shop.model.News;
import com.student.shop.common.Page;
import com.student.shop.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author student
 * @date 2023-3-8
 */
@Controller
@RequestMapping("/admin/news")
public class NewsAdminController {
    private static final Logger logger = LoggerFactory.getLogger(NewsAdminController.class);
    @Autowired
    NewsService newsService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model, HttpServletRequest request) {
        Page<News> page = new Page<News>(request);
        newsService.findNews(page);
        model.addObject("page", page);
        model.setViewName("admin/news/newsAdmin");
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewNews(@PathVariable Integer id,ModelAndView model, HttpServletRequest request) {
        News news = newsService.findById(id);
        model.addObject("news", news);
        model.setViewName("news/newsDetail");
        return model;
    }

}
