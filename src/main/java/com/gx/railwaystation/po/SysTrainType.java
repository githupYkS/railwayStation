package com.gx.railwaystation.po;

import java.io.Serializable;

public class SysTrainType implements Serializable {

    /**
     * 列车状态ID
     */
    private Integer trainTypeId;

    /**
     * 状态名称
     */
    private String trainTypeName;

    public Integer getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(Integer trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    @Override
    public String toString() {
        return "SysTrainType{" +
                "trainTypeId=" + trainTypeId +
                ", trainTypeName='" + trainTypeName + '\'' +
                '}';
    }
}
