package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.SectionDao;
import com.jvmup.nbbs.po.Section;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:44
 **/
@Service
public class SectionService {
    private SectionDao sectionDao;

    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }
    public void updateSection(Section section){
        sectionDao.updateSection(section);
    }//更新板块信息

    public void addSection(Section section){
        sectionDao.addSection(section);
    }

    /**
     *
     * @return 获取所有版块的简要信息
     */
    public List<Section> getAllSection(){
        return sectionDao.getAllSection();
    }

    /**
     *
     * @return 获取所有版块的详细信息，包括版块版主，所属的分区
     */

    public List<Section> getNestingSection(){
        return sectionDao.getNestingSection();
    }

    /**
     * 改变板块的分区
     */
    public void changeSectionPartition(Section section){
        sectionDao.changeSectionPartition(section.getPartition().getId(),section.getId());
    }

    /**
     * 根据sectionId 获取section
     */
    public Section getSectionById(int id){
        return sectionDao.getSectionById(id);
    }
    /**
     * 根据partitionId 获取全部的section
     */
    public List<Section> getSectionByPartitionId(int partitionId){
        return sectionDao.getSectionByPartitionId(partitionId);
    }

    public void addSectionOwner(int userId,int sectionId){
        sectionDao.addSectionOwner(userId,sectionId);
    }

    public void removeSectionOwner(int userId,int sectionId){
        sectionDao.removeSectionOwner(userId,sectionId);
    }


}

