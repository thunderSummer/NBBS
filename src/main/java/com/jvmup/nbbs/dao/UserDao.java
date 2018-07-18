package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.annotation.Permission;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.User;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Email;
import java.util.List;

public interface UserDao {
    /**
     *nickName 唯一确定，通过缓存技术，将nickName和用户的id一一映射，提高访问效率
     * @param nickname
     * @return 返回用户的id
     */
    int getIdByEmail(String nickname);
    int getIdByNickname(String nickname);

    void updateUserInfo(User user);

    void updateMail(@Param("id") int id,@Param("email") String mail);

    void updatePassword(@Param("id")int id,@Param("password") String password);

    void updateEx(@Param("id") int id, @Param("num") int num);

    void updateStatus(@Param("id") int id,@Param("status") int status);

    User getUserInfo(int id);

    List<User> getFans(int id);

    List<User> getFollows(int id);


    List<String> getMaskWord(int id);

    List<User> getBlack(int id);

    void addFans(@Param("reqId")int reqId,@Param("desId") int desId);

    void addBlack(@Param("reqId") int reqId,@Param("desId") int desId);

    void addWord(@Param("userId") int id,@Param("word") String word);

    void addFollow(@Param("reqId") int reqId,@Param("desId") int desId);

    void addUser(User user);

    void removeFans(@Param("reqId")int reqId,@Param("desId") int desId);

    void removeBlack(@Param("reqId") int reqId,@Param("desId") int desId);

    void removeWord(@Param("userId") int userId,@Param("word") String word);

    String findUserPwd(User user);

    String getNicknameByEmail(String email);






}
