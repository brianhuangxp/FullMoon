package com.ringcentral.fullmoon.domain.vo.system;

public class ClientUserVo {
    private String userName;
    private String nickName;

    public ClientUserVo(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public ClientUserVo() {

    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
