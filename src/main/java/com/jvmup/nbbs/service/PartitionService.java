package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.PartitionDao;
import com.jvmup.nbbs.po.Partition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectName: NBBS
 * 删除partition 就不提供了，太麻烦了。
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:43
 **/
@Service
public class PartitionService {
    private PartitionDao partitionDao;

    @Autowired
    public void setPartitionDao(PartitionDao partitionDao) {
        this.partitionDao = partitionDao;
    }

    public List<Partition> getAllPartition(){
       return partitionDao.getAllPartition();
    }
    public List<Partition> getAllBriefPartition(){
        return partitionDao.getAllBriefPartition();
    }

    public Partition getPartition(int id){

        return partitionDao.getPartitionInfo(id);
    }

    public void  updatePartition(Partition partition){
        partitionDao.updatePartition(partition);
    }

    public void addPartition(Partition partition){
        partitionDao.addPartition(partition);
    }
}
