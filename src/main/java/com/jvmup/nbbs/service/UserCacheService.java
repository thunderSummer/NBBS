package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-17 14:49
 **/
@Service
public class UserCacheService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
//    @Cacheable(value = "nickname2id")
    public int getIdByNickname(String nickname){
        return userDao.getIdByNickname(nickname);
    }


//    @Cacheable(value = "email2id")
    public int getIdByEmail(String email){
        return userDao.getIdByEmail(email);
    }

//    @Cacheable(value = "email2nickname")
    public String getNicknameByEmail(String email){
        return userDao.getNicknameByEmail(email);
    }
}
