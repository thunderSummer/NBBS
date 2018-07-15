package com.jvmup.nbbs.dao;

public interface UserDao {
    /**
     *nickName 唯一确定，通过缓存技术，将nickName和用户的id一一映射，提高访问效率
     * @param nickName
     * @return 返回用户的id
     */
    int getIdByNickname(String nickName);
}
