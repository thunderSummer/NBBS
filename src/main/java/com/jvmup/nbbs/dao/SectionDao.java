package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Section;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;


public interface SectionDao {


    void updateSection(Section section);//更新板块信息

    void updateTitle(@Param("int") int sectionid, @Param("title") String announment);//更新板块标题

    String getTitle(int sectionid);

    void updateAnnouncement(@Param("announcement") String announcement);//更新板块公告

    String getAnnouncement(int sectionid);//获取板块公告

    void updatePartitionid(@Param("Partitionid") int partitionid);//更新板块所属分区

    int getPartitionid(int sectionid);//获取板块所属分区id

    void addSection(Section Section);//添加板块

    void removeAnnouncement(int sectionid);//移除公告

    void removeSection(@Param("sectionid") int sectionid);//删除一个分区

    Section getSectionInfo(int sectionid);//获取id为sectionid的板块信息

    List<Section> getAllSection();//获取所有板块
}
