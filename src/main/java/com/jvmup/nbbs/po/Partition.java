package com.jvmup.nbbs.po;

import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 13:51
 **/
@Alias("partition")
public class Partition extends BasePo {
    private int id;
    private String title;

    private List<User> partitionOwner;
    private List<Section> sections;

    public List<User> getPartionOwner() {
        return partitionOwner;
    }

    public void setPartionOwner(List<User> partionOwner) {
        this.partitionOwner = partionOwner;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Partition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
