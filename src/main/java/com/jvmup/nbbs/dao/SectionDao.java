package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Section;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SectionDao {


    void updateSection(Section section);//更新板块信息

    Section getSectionById(int id);

    void addSection(Section Section);//添加板块


    List<Section> getAllSection();//获取所有板块

    List<Section> getNestingSection();
    void changeSectionPartition(@Param("partitionId") int partitionId, @Param("id") int id);

    List<Section> getSectionByPartitionId(int partitionId);
}
