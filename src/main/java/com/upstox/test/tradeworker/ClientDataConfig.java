package com.upstox.test.tradeworker;

import com.upstox.test.model.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class ClientDataConfig {

    private Set<Subscriber> clientList;

    private static ClientDataConfig clientDataConfig_Instance = null;

    public Set<Subscriber> getClientList() {
        return clientList;
    }

    private ClientDataConfig() {
        clientList = new HashSet<>();
    }

    public static ClientDataConfig getClientDataConfig_Instance(){
        if (clientDataConfig_Instance == null)
            clientDataConfig_Instance = new ClientDataConfig();
        return clientDataConfig_Instance;
    }
}
