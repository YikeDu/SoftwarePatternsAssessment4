package com.student.shop.util;

import com.student.shop.model.User;
import com.student.shop.common.Constants;

import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 * 
 * @author Yike Du
 *
 */
public class UserUtil {

    public static final String USER = Constants.LOGIN_USER;

    /**
     * 设置用户到session
     *
     * @param session
     * @param user
     */
    public static void saveUserToSession(HttpSession session, User user) {
        AdminUtil.deleteAdminFromSession(session);
        session.setAttribute(USER, user);
    }

    /**
     * 从Session获取当前用户信息
     *
     * @param session
     * @return
     */
    public static User getUserFromSession(HttpSession session) {
        Object attribute = session.getAttribute(USER);
        return attribute == null ? null : (User) attribute;
    }

    /**
     * 从Session中删除登陆用户的个人信息
     *
     * @param session
     */
    public static void deleteUserFromSession(HttpSession session) {
        session.removeAttribute(USER);
    }
}
