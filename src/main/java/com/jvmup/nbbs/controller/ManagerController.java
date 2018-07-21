package com.jvmup.nbbs.controller;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import com.jvmup.nbbs.dao.PostDao;
import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.po.Partition;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.Section;
import com.jvmup.nbbs.service.PartitionService;
import com.jvmup.nbbs.service.PostService;
import com.jvmup.nbbs.service.SectionService;
import com.jvmup.nbbs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:32
 **/
@RestController
public class ManagerController {

    private UserService userService;

    private PartitionService partitionService;

    private SectionService sectionService;

    private UserDao userDao;

    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private PostService postService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPartitionService(PartitionService partitionService) {
        this.partitionService = partitionService;
    }
    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/admin/allUser")
    public ResponseStyle getUserInfo(HttpServletRequest request){
        return new ResponseStyle().success(userService.getAllUserId());
    }
    @RequestMapping(value = "/admin/partitionOwner",method = RequestMethod.DELETE)
    public ResponseStyle removePartitionOwner(HttpServletRequest request,@RequestBody Param param){
        partitionService.removePartitionOwner(param.getUserId(),param.getId());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/partitionOwner",method = RequestMethod.POST)
    public ResponseStyle addPartitionOwner(HttpServletRequest request,@RequestBody Param param){
        partitionService.addPartitionOwner(param.getUserId(),param.getId());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/admin/sectionOwner",method = RequestMethod.POST)
    public ResponseStyle addSectionOwner(HttpServletRequest request,@RequestBody Param param){
        sectionService.addSectionOwner(param.getUserId(),param.getId());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/sectionOwner",method = RequestMethod.DELETE)
    public ResponseStyle deleteSectionOwner(HttpServletRequest request,@RequestBody Param param){
        sectionService.removeSectionOwner(param.getUserId(),param.getId());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/section",method = RequestMethod.POST)
    public ResponseStyle addSection(HttpServletRequest request, @RequestBody Section section){
        sectionService.addSection(section);
        return new ResponseStyle().success(section.getId());
    }
    @RequestMapping(value = "/admin/section",method = RequestMethod.PUT)
    public ResponseStyle updateSection(HttpServletRequest request,@RequestBody Section section){
        sectionService.updateSection(section);
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/admin/partition",method = RequestMethod.PUT)
    public ResponseStyle updatePartition(HttpServletRequest request,@RequestBody Partition partition){
        partitionService.updatePartition(partition);
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/partition",method = RequestMethod.POST)
    public ResponseStyle addPartition(HttpServletRequest request,@RequestBody Partition partition){
        partitionService.addPartition(partition);
        return new ResponseStyle().success(partition.getId());
    }
    @RequestMapping(value = "/admin/isSection",method = RequestMethod.POST)
    public ResponseStyle isAs(HttpServletRequest request,@RequestBody Param param)
    {
        int a =userDao.isAs(param.getUserId(),param.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("id",param.userId);
        map.put("result",a==1);
        return new ResponseStyle().success(map);
    }
    @RequestMapping(value = "/admin/isPartition",method = RequestMethod.POST)
    public ResponseStyle isAp(HttpServletRequest request,@RequestBody Param param)
    {
        int a =userDao.isAp(param.getUserId(),param.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("id",param.userId);
        map.put("result",a==1);
        return new ResponseStyle().success(map);
    }
    @RequestMapping(value = "/admin/section/partition",method = RequestMethod.PUT)
    public ResponseStyle changeSectionPartition(HttpServletRequest request,@RequestBody Section section){
        sectionService.changeSectionPartition(section);
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/allPost",method = RequestMethod.GET)
    public ResponseStyle getAllPost(HttpServletRequest request){
        return new ResponseStyle().success(postService.getAllPost());
    }
    @RequestMapping(value = "/admin/post/type",method = RequestMethod.PUT)
    public ResponseStyle changeType(HttpServletRequest request,@RequestBody Param1 param1){
        postDao.changePostType(param1.postId,param1.type);
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/post/top",method = RequestMethod.PUT)
    public ResponseStyle changeTopping(HttpServletRequest request,@RequestBody Param1 param1){
        postDao.toppingPost(param1.postId,param1.topping);
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/admin/post",method = RequestMethod.DELETE)
    public ResponseStyle deletePost(HttpServletRequest request,@RequestBody Param1 param1){
        postDao.deletePost(param1.postId);
        return new ResponseStyle().success();
    }
    static class Param{
        int userId;
        int id;

        public Param() {
        }

        public int getUserId() {
            return userId;
        }

        public int getId() {
            return id;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    static class Param1{
        int topping;
        int type;
        int postId;

        public Param1() {
        }

        public int getTopping() {
            return topping;
        }

        public void setTopping(int topping) {
            this.topping = topping;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }
    }

}
