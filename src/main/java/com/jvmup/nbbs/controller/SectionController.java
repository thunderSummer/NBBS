package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.Section;
import com.jvmup.nbbs.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:33
 **/
@RestController
public class SectionController {
    private SectionService sectionService;

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "/section",method = RequestMethod.PUT)
    public ResponseStyle updateSection(Section section){
        sectionService.updateSection(section);
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/section",method = RequestMethod.POST)
    public ResponseStyle addSection(Section section){
        sectionService.addSection(section);
        return new ResponseStyle().success();
    }

    /**
     *
     * @return 获取所有版块的简要信息
     */
    @RequestMapping(value = "/allSection",method = RequestMethod.GET)
    public ResponseStyle getAllSection(){
        return new ResponseStyle().success( sectionService.getAllSection());
    }

    /**
     *
     * @return 获取所有版块的详细信息，包括版块版主，所属的分区
     */
    @RequestMapping(value = "/allSection/detail",method = RequestMethod.GET)
    public ResponseStyle getNestingSection(){
        return new ResponseStyle().success(sectionService.getNestingSection());
    }

    /**
     * 改变板块的分区
     */
    @RequestMapping(value = "/changeSectionPartition",method = RequestMethod.PUT)
    public ResponseStyle changeSectionPartition(@RequestBody Section section){
        sectionService.changeSectionPartition(section);
       return new ResponseStyle().success();
    }

    /**
     * 根据sectionId 获取section
     */
    @RequestMapping(value = "/section/sectionId/{id}",method = RequestMethod.GET)
    public ResponseStyle getSectionById(@PathVariable int id){
        return new ResponseStyle().success(sectionService.getSectionById(id));
    }


    @RequestMapping(value = "/section/partitionId/{partitionId}",method = RequestMethod.GET)
    public ResponseStyle getSectionByPartitionId(@PathVariable int partitionId){
        return new ResponseStyle().success(sectionService.getSectionByPartitionId(partitionId));
    }
    
}
