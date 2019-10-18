package com.tsvico.util;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/18 10:43
 * 功能
 */
public class JsonBean {

    /**
     * code : 200
     * mes : http://www.bejson.com
     * data : 科技园路.
     */

    private int code=200; //默认值
    private String mes="";
    private Integer Intdata;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getData() {
        return data;
    }

    public Integer getIntdata() {
        return Intdata;
    }

    public void setIntdata(Integer intdata) {
        Intdata = intdata;
    }

    public void setData(String data) {
        this.data = data;
    }
}
