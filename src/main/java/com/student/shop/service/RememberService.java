package com.student.shop.service;

import com.student.shop.repository.RememberRepository;
import com.student.shop.model.Remember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author student
 * @version 2023/3/13
 */
@Service
public class RememberService {

    @Autowired
    RememberRepository rememberRepository;

    public void add(Remember remember) {
        rememberRepository.save(remember);
    }

    public void delete(String uuid){
        rememberRepository.deleteById(uuid);
    }

    public Remember findById(String uuid){
        return rememberRepository.getOne(uuid);
    }
}
