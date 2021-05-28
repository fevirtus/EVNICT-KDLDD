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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
public class ADiemdoTariffMix2 implements Serializable {

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
    @Column(name = "MA_LOAI_DD")
    private String maLoaiDd;
    @Column(name = "MA_TC_DD")
    private String maTcDd;
    @Column(name = "NGAY_VH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayVh;
    @Column(name = "NGAY_KT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKt;
    @Column(name = "IT_VH")
    private BigInteger itVh;
    @Column(name = "ORGID_REF")
    private String orgidRef;
    @Column(name = "MA_DIEMDO_REF")
    private String maDiemdoRef;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "ULEVELID")
    private String ulevelid;
    @Column(name = "STT")
    private BigInteger stt;
    @JoinColumn(name = "MA_CTO", referencedColumnName = "MA_CTO")
    @ManyToOne
    private ACongto maCto;
    @JoinColumn(name = "MA_NHOM_DD", referencedColumnName = "MA_NHOM_DD")
    @ManyToOne(optional = false)
    private ADiemdoNhom maNhomDd;

    public ADiemdoTariffMix2() {
    }

    public ADiemdoTariffMix2(ADiemdoTariffPK aDiemdoTariffPK) {
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

    public String getfCd() {
        return fCd;
    }

    public void setfCd(String fCd) {
        this.fCd = fCd;
    }

    public String getfTd() {
        return fTd;
    }

    public void setfTd(String fTd) {
        this.fTd = fTd;
    }

    public String getfBt() {
        return fBt;
    }

    public void setfBt(String fBt) {
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

    public String getfCdSunday() {
        return fCdSunday;
    }

    public void setfCdSunday(String fCdSunday) {
        this.fCdSunday = fCdSunday;
    }

    public String getfTdSunday() {
        return fTdSunday;
    }

    public void setfTdSunday(String fTdSunday) {
        this.fTdSunday = fTdSunday;
    }

    public String getfBtSunday() {
        return fBtSunday;
    }

    public void setfBtSunday(String fBtSunday) {
        this.fBtSunday = fBtSunday;
    }

    public ADiemdoTariffPK getADiemdoTariffPK() {
        return aDiemdoTariffPK;
    }

    public void setADiemdoTariffPK(ADiemdoTariffPK aDiemdoTariffPK) {
        this.aDiemdoTariffPK = aDiemdoTariffPK;
    }

    public String getTenDiemdo() {
        return tenDiemdo;
    }

    public void setTenDiemdo(String tenDiemdo) {
        this.tenDiemdo = tenDiemdo;
    }

    public String getMaLoaiDd() {
        return maLoaiDd;
    }

    public void setMaLoaiDd(String maLoaiDd) {
        this.maLoaiDd = maLoaiDd;
    }

    public String getMaTcDd() {
        return maTcDd;
    }

    public void setMaTcDd(String maTcDd) {
        this.maTcDd = maTcDd;
    }

    public Date getNgayVh() {
        return ngayVh;
    }

    public void setNgayVh(Date ngayVh) {
        this.ngayVh = ngayVh;
    }

    public Date getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(Date ngayKt) {
        this.ngayKt = ngayKt;
    }

    public BigInteger getItVh() {
        return itVh;
    }

    public void setItVh(BigInteger itVh) {
        this.itVh = itVh;
    }

    public String getOrgidRef() {
        return orgidRef;
    }

    public void setOrgidRef(String orgidRef) {
        this.orgidRef = orgidRef;
    }

    public String getMaDiemdoRef() {
        return maDiemdoRef;
    }

    public void setMaDiemdoRef(String maDiemdoRef) {
        this.maDiemdoRef = maDiemdoRef;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getUlevelid() {
        return ulevelid;
    }

    public void setUlevelid(String ulevelid) {
        this.ulevelid = ulevelid;
    }

    public BigInteger getStt() {
        return stt;
    }

    public void setStt(BigInteger stt) {
        this.stt = stt;
    }

    public ACongto getMaCto() {
        return maCto;
    }

    public void setMaCto(ACongto maCto) {
        this.maCto = maCto;
    }

    public ADiemdoNhom getMaNhomDd() {
        return maNhomDd;
    }

    public void setMaNhomDd(ADiemdoNhom maNhomDd) {
        this.maNhomDd = maNhomDd;
    }

}
