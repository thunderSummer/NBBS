package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.service.PartitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setPartitionService(PartitionService partitionService) {
        this.partitionService = partitionService;
    }

    @RequestMapping(value = "/allPartition",method = RequestMethod.GET)
    public ResponseStyle getAllPartition(){
        return new ResponseStyle().success(partitionService.getAllPartition());
    }
}
