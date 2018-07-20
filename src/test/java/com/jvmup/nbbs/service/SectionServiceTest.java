package com.jvmup.nbbs.service;

import com.jvmup.nbbs.po.Partition;
import com.jvmup.nbbs.po.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
@ContextConfiguration("classpath*:/applicationContext/applicationContext.xml")
public class SectionServiceTest extends AbstractTestNGSpringContextTests {
    private SectionService sectionService;

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Test
    public void testUpdateSection1() {
        Section section = new Section();
        section.setAnnouncement("这是更新后的公告");
        section.setId(1);
        section.setTitle("这是更新后的题目");
        sectionService.updateSection(section);
    }

    @Test
    public void testAddSection1() {
        Section section = new Section();
        section.setTitle("这是添加后的题目");
        section.setAnnouncement("这是添加后的公告");
        Partition partition = new Partition();
        partition.setId(1);
        section.setPartition(partition);
        sectionService.addSection(section);
        logger.info(section.getId());
    }

    @Test
    public void testGetAllSection1() {
        logger.info(sectionService.getAllSection());
    }

    @Test
    public void testGetNestingSection1() {
        logger.info(sectionService.getNestingSection());
    }

    @Test
    public void testChangeSectionPartition1() {
//        sectionService.changeSectionPartition(1,5);
    }

    @Test
    public void testGetSectionById() {
        logger.info(sectionService.getSectionById(1));
    }

    @Test
    public void testGetSectionByPartitionId() {
        logger.info(sectionService.getSectionByPartitionId(2));
    }
}