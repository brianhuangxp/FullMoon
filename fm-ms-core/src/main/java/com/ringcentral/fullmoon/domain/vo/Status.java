package com.ringcentral.fullmoon.domain.vo;

/**
 * Created by huangking on 14-1-7.
 */
public class Status {
    private String statusCode;
    private String ex;
    private String alertMsg;

    public Status() {

    }

    public Status(String statusCode, String alertMsg) {
        this.statusCode = statusCode;
        this.alertMsg = alertMsg;
    }

    public Status(String statusCode, String alertMsg, Exception ex) {
        this.statusCode = statusCode;
        this.alertMsg = alertMsg;
        this.ex = ex.getLocalizedMessage();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }
}
