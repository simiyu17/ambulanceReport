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
public class ProfileDocuments {
    
    private String dNo;
    
    private String name;

    public ProfileDocuments() {
    }

    public ProfileDocuments(String dNo, String name) {
        this.dNo = dNo;
        this.name = name;
    }
    

    /**
     * @return the dNo
     */
    public String getdNo() {
        return dNo;
    }

    /**
     * @param dNo the dNo to set
     */
    public void setdNo(String dNo) {
        this.dNo = dNo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
