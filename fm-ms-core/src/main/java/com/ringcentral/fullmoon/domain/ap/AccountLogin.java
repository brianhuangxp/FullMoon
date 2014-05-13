package com.ringcentral.fullmoon.domain.ap;

/**
 * Created by huangking on 14-1-2.
 */
public class AccountLogin extends BaseAp{
    private String userName;
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
