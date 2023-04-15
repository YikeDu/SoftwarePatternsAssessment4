/**
 *
 */
package com.student.shop.service;

import com.student.shop.repository.AdminRepository;
import com.student.shop.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 */
@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminDao;

    public boolean checkLogin(Admin admin) {
        return findByUsernameAndPassword(admin.getUsername(), admin.getPassword()) == null ? false : true;
    }

    public Admin findByUsernameAndPassword(String username, String password) {
        return adminDao.findByUsernameAndPassword(username, password);
    }

    public void save(Admin admin) {
        adminDao.save(admin);
    }

    public Admin findOne(Integer id) {
        return adminDao.getOne(id);
    }

}
