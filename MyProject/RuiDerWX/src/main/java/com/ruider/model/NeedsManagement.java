package com.ruider.model;

import java.util.Date;

/**
 * Created by mahede on 2018/11/27.
 */
public class NeedsManagement {

    int id;

    int categoryId;

    int userId;

    String title;

    String content;

    Date startTime;

    Date deadline;

    String qq;

    String weChat;

    String phoneNo;

    int limitNo;

    Date createTime;

    Date updateTime;

    private String extendField1;

    private String extendField2;

    private String extendField3;

    private String extendField4;

    private String extendField5;

    private String extendField6;

    private String extendField7;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getLimitNo() {
        return limitNo;
    }

    public void setLimitNo(int limitNo) {
        this.limitNo = limitNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtendField1() {
        return extendField1;
    }

    public void setExtendField1(String extendField1) {
        this.extendField1 = extendField1;
    }

    public String getExtendField2() {
        return extendField2;
    }

    public void setExtendField2(String extendField2) {
        this.extendField2 = extendField2;
    }

    public String getExtendField3() {
        return extendField3;
    }

    public void setExtendField3(String extendField3) {
        this.extendField3 = extendField3;
    }

    public String getExtendField4() {
        return extendField4;
    }

    public void setExtendField4(String extendField4) {
        this.extendField4 = extendField4;
    }

    public String getExtendField5() {
        return extendField5;
    }

    public void setExtendField5(String extendField5) {
        this.extendField5 = extendField5;
    }

    public String getExtendField6() {
        return extendField6;
    }

    public void setExtendField6(String extendField6) {
        this.extendField6 = extendField6;
    }

    public String getExtendField7() {
        return extendField7;
    }

    public void setExtendField7(String extendField7) {
        this.extendField7 = extendField7;
    }

    @Override
    public String toString() {
        return "Carpooling{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", deadline=" + deadline +
                ", qq='" + qq + '\'' +
                ", weChat='" + weChat + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", limitNo=" + limitNo +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extendField1='" + extendField1 + '\'' +
                ", extendField2='" + extendField2 + '\'' +
                ", extendField3='" + extendField3 + '\'' +
                ", extendField4='" + extendField4 + '\'' +
                ", extendField5='" + extendField5 + '\'' +
                ", extendField6='" + extendField6 + '\'' +
                ", extendField7='" + extendField7 + '\'' +
                '}';
    }
}
