package com.jvmup.nbbs.po;

import org.apache.ibatis.type.Alias;

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
