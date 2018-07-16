package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 17:44
 **/
@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Cacheable(value = "nickname2id")
    public int getIdByNickname(String nickname){
        return userDao.getIdByNickname(nickname);
    }

}
