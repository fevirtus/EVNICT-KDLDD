/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.report;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author DuongNM
 */
@Entity
public class S_ReportTTPhutaiThang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STT")
    private Integer stt;
    @Column(name = "NGAY")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngay;
    @Column(name = "SL_DD")
    private Double sldd;
    @Column(name = "SL_DU")
    private Double sldu;
    @Column(name = "SL_THIEU")
    private Double slthieu;
    @Column(name = "SL_IN_EVENT")
    private Double slinevent;

    @Transient
    private Double slnotinevent;
    @Transient
    private Double khongthuthap;
    @Transient
    private Double tile;

    public S_ReportTTPhutaiThang() {
    }

    public S_ReportTTPhutaiThang(Integer rptid) {
        this.stt = rptid;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Double getSldu() {
        return sldu;
    }

    public void setSldu(Double sldu) {
        this.sldu = sldu;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Double getSldd() {
        return sldd;
    }

    public void setSldd(Double sldd) {
        this.sldd = sldd;
    }

    public Double getSlthieu() {
        return slthieu;
    }

    public void setSlthieu(Double slthieu) {
        this.slthieu = slthieu;
    }

    public Double getSlinevent() {
        return slinevent;
    }

    public void setSlinevent(Double slinevent) {
        this.slinevent = slinevent;
    }

    public Double getSlnotinevent() {
        slnotinevent = (slthieu == null ? 0.0 : slthieu) - (slinevent == null ? 0.0 : slinevent);
        return slnotinevent;
    }

    public void setSlnotinevent(Double slnotinevent) {
        this.slnotinevent = slnotinevent;
    }

    public Double getKhongthuthap() {
        khongthuthap = (sldd == null ? 0.0 : sldd) - (slthieu == null ? 0.0 : slthieu) - (sldu == null ? 0.0 : sldu);
        return khongthuthap;
    }

    public void setKhongthuthap(Double khongthuthap) {
        this.khongthuthap = khongthuthap;
    }

    public Double getTile() {
        Double ms = (sldd == null ? 0.0 : sldd) - (slinevent == null ? 0.0 : slinevent) - (khongthuthap == null ? 0.0 : khongthuthap);
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
