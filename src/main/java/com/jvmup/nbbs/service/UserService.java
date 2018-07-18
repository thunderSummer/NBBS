package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.exception.UserNotExistException;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserCacheService userCacheService;


    @Autowired
    public void setUserCacheService(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getIdByNickname(String nickname){
        return userCacheService.getIdByNickname(nickname);
    }


    public int getIdByEmail(String email){
        return userCacheService.getIdByEmail(email);
    }

    public User getUserInfo(int userId){
        return userDao.getUserInfo(userId);
    }

    public void register(User user){
        userDao.addUser(user);
    }

    public boolean userLogin(User user) throws UserNotExistException {
        String password = userDao.findUserPwd(user);
        if (StringUtil.isBlank(password)){
            throw new UserNotExistException("登录失败，账号信息不存在");
        }
        return password.equals(user.getPassword());
    }

    public String getNicknameByEmail(String email){
        return userCacheService.getNicknameByEmail(email);
    }


    public void updateUserInfo(User user){
        userDao.updateUserInfo(user);
    }
    @CacheEvict(value = "email2id",key ="#user.email")
    public void updateMail(User user){
        userDao.updateMail(getIdByNickname(user.getNickname()),user.getEmail());
    }
    public void updatePassword(User user){
        userDao.updatePassword(getIdByNickname(user.getNickname()),user.getPassword());
    }
    public void updateEx(User user,int num){
        userDao.updateEx(getIdByNickname(user.getNickname()),num);
    }

    public void updateStatus(User user){
        userDao.updateStatus(getIdByNickname(user.getNickname()),user.getStatus());
    }

    public List<User> getFans(String nickname){
        return userDao.getFans(getIdByNickname(nickname));
    }

    public List<User> getFollows(String nickname){
        return userDao.getFollows(getIdByNickname(nickname));
    }

    public List<String> getMaskWord(String nickname){
        return userDao.getMaskWord(getIdByNickname(nickname));
    }

    public List<User> getBlack(String nickname){
        return userDao.getBlack(getIdByNickname(nickname));
    }

    public void addFans(String reqNickname,String desNickname){
        userDao.addFans(getIdByNickname(reqNickname),getIdByNickname(desNickname));
    }

    public void addBlack(String reqNickname,String desNickname){
        userDao.addBlack(getIdByNickname(reqNickname),getIdByNickname(desNickname));
    }

    public void addFollow(String reqNickname,String desNickname){
        userDao.addFollow(getIdByNickname(reqNickname),getIdByNickname(desNickname));
    }
    public void removeFollow(String reqNickname,String desNickname){
        userDao.removeFans(getIdByNickname(desNickname),getIdByNickname(reqNickname));
    }
    public void addWord(String reqNickname,String word){
        userDao.addWord(getIdByNickname(reqNickname),word);
    }
    public void removeFans(String reqNickname,String desNickname){
        userDao.removeFans(getIdByNickname(reqNickname),getIdByNickname(desNickname));
    }
    public void removeBlack(String reqNickname,String desNickname){
        userDao.removeBlack(getIdByNickname(reqNickname),getIdByNickname(desNickname));
    }
    public void removeWord(String reqNickname,String word){
        userDao.removeWord(getIdByNickname(reqNickname),word);
    }

    public void getFllow(String nickname){
        userDao.getFollows(getIdByNickname(nickname));
    }


}
