package com.unifacisa.utils;

import java.util.Comparator;

import com.unifacisa.entities.Client;

public class ClientComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        Client cl1 = (Client) c1;
        Client cl2 = (Client) c2;

        if (cl1.getPriority() > cl2.getPriority())
            return -1;
        else if (cl1.getPriority() < cl2.getPriority())
            return 1;
        else {
            if (cl1.getTokenOrder() < cl2.getTokenOrder()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
