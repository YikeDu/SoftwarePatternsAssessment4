package com.student.shop.service;

import com.student.shop.repository.PictureRepository;
import com.student.shop.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author student
 * @version 2023/3/31
 */
@Service
@Transactional
public class PictureService {

    @Autowired
    PictureRepository pictureDao;

    public void save(Picture picture) {
        pictureDao.save(picture);
    }

    public List<Picture> findAll() {
        return pictureDao.findAll();
    }

}
