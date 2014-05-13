package com.ringcentral.fullmoon.domain.ap;

import java.util.List;

/**
 * Created by huangking on 14-1-10.
 */
public class ServerJason extends JsonBean {
    private List<Integer> providers;
    private List<Integer> servers;

    public List<Integer> getProviders() {
        return providers;
    }

    public void setProviders(List<Integer> providers) {
        this.providers = providers;
    }

    public List<Integer> getServers() {
        return servers;
    }

    public void setServers(List<Integer> servers) {
        this.servers = servers;
    }
}
