package com.student.shop.controller;

import com.student.shop.model.News;
import com.student.shop.util.AdminUtil;
import com.student.shop.common.web.JsonResult;
import com.student.shop.model.Admin;
import com.student.shop.service.AdminService;
import com.student.shop.service.NewsService;
import com.student.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author student
 * @date 2023-3-8
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService;

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg() {
        return "admin/adminReg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String doReg(Admin admin) {
        adminService.save(admin);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/adminLogin";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testIndex() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(Admin admin, HttpSession session) {
        if (adminService.checkLogin(admin)) {
            AdminUtil.saveAdminToSession(session, adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword()));
            log.debug("管理员[{}]登陆成功",admin.getUsername());
            return "redirect:/admin/product";
        }
        return "redirect:/admin/login?errorPwd=true";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        AdminUtil.deleteAdminFromSession(session);
        return "redirect:/";
    }

    @RequestMapping(value = "/news/delete/{id}")
    @ResponseBody
    public JsonResult newsDelete(@PathVariable("id") Integer id) {
        newsService.delNews(id);
        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }

    @RequestMapping(value = "/news/{id}")
    public ModelAndView newsView(@PathVariable("id") Integer id,ModelAndView model) {
        News news = newsService.findById(id);
        model.addObject("news", news);
        model.setViewName("admin/news/newsDetail");
        return model;
    }

    @RequestMapping(value = "/news/new", method = RequestMethod.GET)
    public String newsAdd(HttpSession session) {
        if (AdminUtil.getAdminFromSession(session) == null) {
            return "redirect:/admin/login?error=true";
        }
        return "admin/news/newsAdd";
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public String newsAdd(News news,HttpSession session) {
        news.setInputUser(AdminUtil.getAdminFromSession(session));
        news.setCreateTime(new Date());
        newsService.addNews(news);
        return "redirect:/admin/news";
    }


}
