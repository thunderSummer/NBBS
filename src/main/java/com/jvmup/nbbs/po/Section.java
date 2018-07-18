package com.jvmup.nbbs.po;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 13:50
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Section {
    private int id;
    private String title;
    private String announcement;
    private Partition partition;
    private List<User> sectionOwner;

    public List<User> getSectionOwner() {
        return sectionOwner;
    }

    public void setSectionOwner(List<User> sectionOwner) {
        this.sectionOwner = sectionOwner;
    }

    public Section() {
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

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Partition getPartition() {
        return partition;
    }

    public void setPartition(Partition partition) {
        this.partition = partition;
    }
}
