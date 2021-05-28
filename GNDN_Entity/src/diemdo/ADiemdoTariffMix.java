/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
public class ADiemdoTariffMix implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ADiemdoTariffPK aDiemdoTariffPK;

    @Column(name = "TU_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tuNgay;

    @Column(name = "DEN_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date denNgay;

    @Column(name = "F_CD")
    private String fCd;

    @Column(name = "F_TD")
    private String fTd;
    
    @Column(name = "F_BT")
    private String fBt;
    
    @Column(name = "MO_TA")
    private String moTa;
    
    @Column(name = "TRANG_THAI")
    private BigInteger trangThai;
    
    @Column(name = "USER_CR_ID")
    private String userCrId;
    
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    
    @Column(name = "F_CD_SUNDAY")
    private String fCdSunday;
    
    @Column(name = "F_TD_SUNDAY")
    private String fTdSunday;
    
    @Column(name = "F_BT_SUNDAY")
    private String fBtSunday;

    @Column(name = "TEN_DIEMDO")
    private String tenDiemdo;

    public ADiemdoTariffMix() {
    }

    public ADiemdoTariffMix(ADiemdoTariffPK aDiemdoTariffPK) {
        this.aDiemdoTariffPK = aDiemdoTariffPK;
    }

    public ADiemdoTariffMix(String maDiemdo, BigInteger thang, BigInteger nam, BigInteger dateid) {
        this.aDiemdoTariffPK = new ADiemdoTariffPK(maDiemdo, thang, nam, dateid);
    }

    public ADiemdoTariffPK getADiemdoTariffPK() {
        return aDiemdoTariffPK;
    }

    public void setADiemdoTariffPK(ADiemdoTariffPK aDiemdoTariffPK) {
        this.aDiemdoTariffPK = aDiemdoTariffPK;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getFCd() {
        return fCd;
    }

    public void setFCd(String fCd) {
        this.fCd = fCd;
    }

    public String getFTd() {
        return fTd;
    }

    public void setFTd(String fTd) {
        this.fTd = fTd;
    }

    public String getFBt() {
        return fBt;
    }

    public void setFBt(String fBt) {
        this.fBt = fBt;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigInteger getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(BigInteger trangThai) {
        this.trangThai = trangThai;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
    }

    public String getFCdSunday() {
        return fCdSunday;
    }

    public void setFCdSunday(String fCdSunday) {
        this.fCdSunday = fCdSunday;
    }

    public String getFTdSunday() {
        return fTdSunday;
    }

    public void setFTdSunday(String fTdSunday) {
        this.fTdSunday = fTdSunday;
    }

    public String getFBtSunday() {
        return fBtSunday;
    }

    public void setFBtSunday(String fBtSunday) {
        this.fBtSunday = fBtSunday;
    }

    public String getTenDiemdo() {
        return tenDiemdo;
    }

    public void setTenDiemdo(String tenDiemdo) {
        this.tenDiemdo = tenDiemdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aDiemdoTariffPK != null ? aDiemdoTariffPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdoTariff)) {
            return false;
        }
        ADiemdoTariff other = (ADiemdoTariff) object;
        if ((this.aDiemdoTariffPK == null && other.aDiemdoTariffPK != null) || (this.aDiemdoTariffPK != null && !this.aDiemdoTariffPK.equals(other.aDiemdoTariffPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdoTariff[ aDiemdoTariffPK=" + aDiemdoTariffPK + " ]";
    }

}
