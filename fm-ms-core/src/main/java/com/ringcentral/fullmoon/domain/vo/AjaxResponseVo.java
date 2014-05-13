package com.ringcentral.fullmoon.domain.vo;

/**
 * Created by huangking on 14-1-7.
 */
public class AjaxResponseVo {
    private Status status;
    private Object result;
    private long spendTime;

    public AjaxResponseVo(){

    }

    public AjaxResponseVo(Status status, Object result, long spendTime) {
        this.status = status;
        this.result = result;
        this.spendTime = spendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }
}
