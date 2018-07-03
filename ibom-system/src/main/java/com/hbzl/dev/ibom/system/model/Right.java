package com.hbzl.dev.ibom.system.model;

import com.hbzl.dev.ibom.common.util.Guid;

public class Right {
    private String id=Guid.getUUID();

    private String rightCode;

    private String rightName;

    private String rightLevel;

    private String parentRightId;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode == null ? null : rightCode.trim();
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName == null ? null : rightName.trim();
    }

    public String getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(String rightLevel) {
        this.rightLevel = rightLevel == null ? null : rightLevel.trim();
    }

    public String getParentRightId() {
        return parentRightId;
    }

    public void setParentRightId(String parentRightId) {
        this.parentRightId = parentRightId == null ? null : parentRightId.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}