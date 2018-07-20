package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.Partition;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.PartitionService;
import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:32
 **/
@RestController
public class PartitionController extends BaseController{
    private PartitionService partitionService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPartitionService(PartitionService partitionService) {
        this.partitionService = partitionService;
    }

    @RequestMapping(value = "/allPartition",method = RequestMethod.GET)
    public ResponseStyle getAllPartition(){
        return new ResponseStyle().success(partitionService.getAllPartition());
    }


    @RequestMapping(value = "/partition",method = RequestMethod.PUT)
    public ResponseStyle  updatePartition(@RequestBody  Partition partition, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        if (userService.isAdmin(user.getId()))
        {
            partitionService.updatePartition(partition);
            return new ResponseStyle().success();

        }
        else
            return new ResponseStyle().failure("权限不够");
    }

    @RequestMapping(value = "/partition",method = RequestMethod.POST)
    public ResponseStyle addPartition(@RequestBody Partition partition,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        if (userService.isAdmin(user.getId()))
        {
            partitionService.addPartition(partition);
            return new ResponseStyle().success();

        }
        else
            return new ResponseStyle().failure("权限不够");
    }

}
