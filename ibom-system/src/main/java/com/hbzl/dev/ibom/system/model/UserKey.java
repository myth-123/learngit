package com.hbzl.dev.ibom.system.model;

import com.hbzl.dev.ibom.common.util.Guid;

public class UserKey {
    private String id=Guid.getUUID();

    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}