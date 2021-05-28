/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evnit.util.common;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author hangntd
 */
public class Complex  implements Serializable{
    private String strValue;
    private Integer intValue;
    private Double dbtValue;
    private Boolean booleanValue;
    private HashMap<String,Double> mapDouble;
    private HashMap<String,String> mapString;
    private HashMap<String,Integer> mapInteger;

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Double getDbtValue() {
        return dbtValue;
    }

    public void setDbtValue(Double dbtValue) {
        this.dbtValue = dbtValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public HashMap<String, Double> getMapDouble() {
        return mapDouble;
    }

    public void setMapDouble(HashMap<String, Double> mapDouble) {
        this.mapDouble = mapDouble;
    }

    public HashMap<String, Integer> getMapInteger() {
        return mapInteger;
    }

    public void setMapInteger(HashMap<String, Integer> mapInteger) {
        this.mapInteger = mapInteger;
    }

    public HashMap<String, String> getMapString() {
        return mapString;
    }

    public void setMapString(HashMap<String, String> mapString) {
        this.mapString = mapString;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }
    
}
