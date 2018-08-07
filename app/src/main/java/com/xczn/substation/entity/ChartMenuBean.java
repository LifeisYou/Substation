package com.xczn.substation.entity;

import java.util.List;

/**
 * @Author zhangxiao
 * @Date 2018/8/7 0007
 * @Comment
 */
public class ChartMenuBean {

    /**
     * id : 123
     * name : 报表1
     * path :
     * measuretype : ["Ia","Ib","Ic"]
     */

    private int id;
    private String name;
    private String path;
    private List<String> measuretype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getMeasuretype() {
        return measuretype;
    }

    public void setMeasuretype(List<String> measuretype) {
        this.measuretype = measuretype;
    }
}
