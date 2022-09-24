package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class Map {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private String preview;
    private String detailInfo;
    private String state;
    private String type;
    private String userGroup;
    private String tag;
//    public Integer getId() {return id;}
//    public void setId(Integer id) {this.id =id;}
//    public String getName() {return name;}
//    public void setName(String name){this.name=name;}
//    public Date getCreateDate() {return createDate;}
//    public void setCreateDate(Date createDate) {this.createDate = createDate;}
//    public String getPreview() {return preview;}
//    public void setPreview(String preview) {this.preview= preview;}
//    public String getDetailInfo() {return detailInfo;}
//    public void setDetailInfo(String detailInfo) {this.detailInfo=detailInfo;}
//    public String getState() {return state;}
//    public void setState(String state) {this.state=state;}
//    public String getType() {return type;}
//    public void setType(String type) {this.type=type;}
//    public String getUserGroup() {return userGroup;}
//    public void setUserGroup(String userGroup) {this.userGroup=userGroup;}
//    public String getTag() {return tag;}
//    public void setTag(String tag) { this.tag= tag;}
}
