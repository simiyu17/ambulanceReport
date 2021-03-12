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
public class CurrentMedicineList {
    
    private String sn;
    private String medicineList;
    private String medicineUse;
    private String unit;

    public CurrentMedicineList() {
    }

    public CurrentMedicineList(String sn, String medicineList, String medicineUse, String unit) {
        this.sn = sn;
        this.medicineList = medicineList;
        this.medicineUse = medicineUse;
        this.unit = unit;
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
     * @return the medicineList
     */
    public String getMedicineList() {
        return medicineList;
    }

    /**
     * @param medicineList the medicineList to set
     */
    public void setMedicineList(String medicineList) {
        this.medicineList = medicineList;
    }

    /**
     * @return the medicineUse
     */
    public String getMedicineUse() {
        return medicineUse;
    }

    /**
     * @param medicineUse the medicineUse to set
     */
    public void setMedicineUse(String medicineUse) {
        this.medicineUse = medicineUse;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
