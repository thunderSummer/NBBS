package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Partition;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PartitionDao {


    void UpdatePartiTitle(@Param("title") String title);//更新分区标题

    void removePartiTitle(@Param("parid") int parid);//删除分区标题

    void updatePartition(Partition partition);//更新分区

    void removePartition(@Param("parid") int parid);//删除分区

    void addPartion(Partition Partition);//添加分区

    Partition getPartitionInfo(int parid);//获取分区信息

    List<Partition> getAllPartition();//获取所有分区信息

}
