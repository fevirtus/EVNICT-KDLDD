/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.report;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author DuongNM
 */
@Entity
public class S_ReportTTVHNgay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STT")
    private Integer stt;
    @Column(name = "GIO")
    private String gio;
    @Column(name = "SL_TONG")
    private Double sltong;
    @Column(name = "SL_DU")
    private Double sldu;
    @Column(name = "SL_ON")
    private Double slon;
    @Column(name = "SL_OFF")
    private Double sloff;
    @Column(name = "SL_FAIL")
    private Double slfail;
    @Column(name = "0")
    private Double sl0;
    @Column(name = "0_1")
    private Double sl01;
    @Column(name = "TEN_DONVI")
    private String ten_donvi;
    @Column(name = "SL_DIEMDO")
    private Double sl_diemdo;

    @Transient
    private Double thuthapThieu_T;
    @Transient
    private Double khongthuthap;
    @Transient
    private Double tile;

    public S_ReportTTVHNgay() {
    }

    public S_ReportTTVHNgay(Integer rptid) {
        this.stt = rptid;
    }

    public Double getSl_diemdo() {
        return sl_diemdo;
    }

    public void setSl_diemdo(Double sl_diemdo) {
        this.sl_diemdo = sl_diemdo;
    }

    public String getTen_donvi() {
        return ten_donvi;
    }

    public void setTen_donvi(String ten_donvi) {
        this.ten_donvi = ten_donvi;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public Double getSltong() {
        return sltong;
    }

    public void setSltong(Double sltong) {
        this.sltong = sltong;
    }

    public Double getSldu() {
        return sldu;
    }

    public void setSldu(Double sldu) {
        this.sldu = sldu;
    }

    public Double getSlon() {
        return slon;
    }

    public void setSlon(Double slon) {
        this.slon = slon;
    }

    public Double getSloff() {
        return sloff;
    }

    public void setSloff(Double sloff) {
        this.sloff = sloff;
    }

    public Double getSlfail() {
        return slfail;
    }

    public void setSlfail(Double slfail) {
        this.slfail = slfail;
    }

    public Double getSl0() {
        return sl0;
    }

    public void setSl0(Double sl0) {
        this.sl0 = sl0;
    }

    public Double getSl01() {
        return sl01;
    }

    public void setSl01(Double sl01) {
        this.sl01 = sl01;
    }

    public Double getThuthapThieu_T() {
        thuthapThieu_T = (sloff == null ? 0.0 : sloff) + (slfail == null ? 0.0 : slfail);
        return thuthapThieu_T;
    }

    public void setThuthapThieu_T(Double thuthapThieu_T) {
        this.thuthapThieu_T = thuthapThieu_T;
    }

    public Double getKhongthuthap() {
        khongthuthap = (sltong == null ? 0.0 : sltong) - (thuthapThieu_T == null ? 0.0 : thuthapThieu_T) - (sldu == null ? 0.0 : sldu);
        return khongthuthap;
    }

    public void setKhongthuthap(Double khongthuthap) {
        this.khongthuthap = khongthuthap;
    }

    public Double getTile() {
        Double ms = (sltong == null ? 0.0 : sltong) - (sloff == null ? 0.0 : sloff) - (khongthuthap == null ? 0.0 : khongthuthap);
        Double ts = sldu * 100;
        if (sldu == null || sldu == 0.0 || ms == 0.0) {
            tile = 0.0;
        } else {
            tile = ts / (ms);
        }
        return tile;
    }

    public void setTile(Double tile) {
        this.tile = tile;
    }

}
