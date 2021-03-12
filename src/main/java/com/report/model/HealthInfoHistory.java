/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.report.model;

/**
 *
 * @author simiyu
 */
public class HealthInfoHistory {
    
    private String sn;
    private String condition;

    public HealthInfoHistory() {
    }

    public HealthInfoHistory(String sn, String condition) {
        this.sn = sn;
        this.condition = condition;
    }
    

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
}
