package com.example.chaes.projectlittlecare.LCClass;

/**
 * Created by user on 2017-05-28.
 */

public class ListViewItem2 {
    private String descNameStr ;
    private String descDateStr ;
    private String descTimeStr;
    private String descDurationStr;
    private String descPhoneStr;
    private String descDetailStr;

    public void setDescNameStr(String descName) {
        descNameStr = descName ;
    }
    public void setDescDateStr(String descDate) { descDateStr = descDate ; }
    public void setDescTimeStr(String descTime) { descTimeStr = descTime ; }
    public void setDescDurationStr(String descDuration) { descDurationStr = descDuration ; }
    public void setDescPhoneStr(String descPhone) { descPhoneStr = descPhone; }
    public void setDescDetailStr(String descDetail) { descDetailStr = descDetail; }


    public String getDescNameStr() {
        return this.descNameStr ;
    }
    public String getDescDateStr() { return this.descDateStr ; }
    public String getDescTimeStr() { return  this.descTimeStr ; }
    public String getDescDurationStr() { return this.descDurationStr; }
    public String getDescPhoneStr() { return this.descPhoneStr; }
    public String getDescDetailStr() { return this.descDetailStr; }
}
