package com.xczn.substation.entity;

/**
 * @Author zhangxiao
 * @Date 2018/8/7 0007
 * @Comment
 */
public class HisAlarmBean {

    /**
     * equip : 智能设备IED_001
     * message : 前置机1与AH01进线柜PMF720L的A网断开
     * type : normal
     * time : 1533604745
     */

    private String equip;
    private String message;
    private String type;
    private String time;

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
