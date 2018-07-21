package com.jvmup.nbbs.service;

import com.jvmup.nbbs.po.Partition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@ContextConfiguration("classpath*:/applicationContext/applicationContext.xml")
public class PartitionServiceTest extends AbstractTestNGSpringContextTests {
    private PartitionService partitionService;
    private Logger logger = LogManager.getLogger();

    @Autowired
    public void setPartitionService(PartitionService partitionService) {
        this.partitionService = partitionService;
    }

    @Test
    public void testGetAllPartition() {
        logger.info(partitionService.getAllPartition());
    }

    @Test
    public void testGetAllBriefPartition() {
        logger.info(partitionService.getAllBriefPartition());
    }

    @Test
    public void testGetPartition() {
        logger.info(partitionService.getPartition(1));
    }

    @Test
    public void testUpdatePartition() {
        Partition partition = new Partition();
        partition.setId(1);
        partition.setTitle("风采本部");
        partitionService.updatePartition(partition);
    }

    @Test
    public void testAddPartition() {
        Partition partition = new Partition();
        partition.setTitle("本部风采啊");
        partitionService.updatePartition(partition);
    }

    @Test
    public void testAddPartitionOwner() {
        partitionService.addPartitionOwner(2,2);
    }
}