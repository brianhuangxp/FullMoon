package com.ringcentral.fullmoon.domain.ap;

import java.util.List;

/**
 * Created by huangking on 14-1-10.
 */
public class CommonSearchAp extends BaseAp{
    private String startDate;
    private String endDate;
    private Integer providerId;
    private Integer serverId;
    private List<Integer> providerIds;
    private List<Integer> serverIds;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public List<Integer> getProviderIds() {
        return providerIds;
    }

    public void setProviderIds(List<Integer> providerIds) {
        this.providerIds = providerIds;
    }

    public List<Integer> getServerIds() {
        return serverIds;
    }

    public void setServerIds(List<Integer> serverIds) {
        this.serverIds = serverIds;
    }
}
