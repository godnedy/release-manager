package com.edytagodniak.releasemanager.api;

public class SystemVersionNotFoundException extends RuntimeException {

    public SystemVersionNotFoundException() {
        super("SystemVersion does not exist!");
    }
}
