package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Partition;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PartitionDao {


    void updatePartition(Partition partition);//更新分区标题

    void addPartition(Partition Partition);//添加分区

    Partition getPartitionInfo(int id);//获取分区信息

    List<Partition> getAllPartition();//获取所有分区信息
    List<Partition> getAllBriefPartition();

    void deletePartition(Partition partition);

}
