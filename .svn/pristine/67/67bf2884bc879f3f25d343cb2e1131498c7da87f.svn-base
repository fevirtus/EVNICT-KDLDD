/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author TUYEN EVN
 */
@Entity
public class ADiemdoMix implements Serializable {

    @Id
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;

    @Column(name = "TEN_DIEMDO")
    private String tenDiemdo;

    @Column(name = "MA_LOAI_DD")
    private String maLoaiDd;

    @Column(name = "MA_TC_DD")
    private String maTcDd;

    @Column(name = "MO_TA")
    private String moTa;

    @Column(name = "NGAY_VH")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date ngayVh;

    @Column(name = "NGAY_KT")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date ngayKt;

    @Column(name = "IT_VH")
    private BigInteger itVh;

    @Column(name = "ORGID_REF")
    private String orgidRef;

    @Column(name = "MA_DIEMDO_REF")
    private String maDiemdoRef;

    @Column(name = "USER_CR_ID")
    private String userCrId;

    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date userCrDtime;

    @Column(name = "USER_MDF_ID")
    private String userMdfId;

    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date userMdfDtime;

    @Column(name = "ORGID")
    private String orgid;

    @Column(name = "ULEVELID")
    private String ulevelid;

    @Column(name = "TRANG_THAI")
    private BigInteger trangThai;

    @Column(name = "STT")
    private BigInteger stt;

    @Column(name = "TEN_NHOM_DD")
    private String tenNhomDD;

    @Column(name = "TEN_LOAI_DD")
    private String tenLoaiDD;
    
    @Column(name = "TEN_TC_DD")
    private String tenTcDD;
    
    @Column(name = "ULEVELDESC")
    private String ulevelDesc;

    @Column(name = "ORGDESC")
    private String orgDesc;
    
    @JoinColumn(name = "MA_CTO", referencedColumnName = "MA_CTO")
    @ManyToOne
    private ACongto maCto;

    @JoinColumn(name = "MA_NHOM_DD", referencedColumnName = "MA_NHOM_DD")
    @ManyToOne(optional = false)
    private ADiemdoNhom maNhomDd;

    private boolean trangThaiToBoolean;

    private boolean itVhToBoolean;
    
    private boolean isAx;

    public ADiemdoMix() {
    }

    public ADiemdoMix(String maDiemdo) {
        this.maDiemdo = maDiemdo;
    }

    public ADiemdoMix(String maDiemdo, String maLoaiDd, java.util.Date ngayVh, String userCrId, java.util.Date userCrDtime, String orgid) {
        this.maDiemdo = maDiemdo;
        this.maLoaiDd = maLoaiDd;
        this.ngayVh = ngayVh;
        this.userCrId = userCrId;
        this.userCrDtime = userCrDtime;
        this.orgid = orgid;
    }

    public boolean isIsAx() {
        return orgidRef != null;
    }

    public void setIsAx(boolean isAx) {
        this.isAx = isAx;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getUlevelDesc() {
        return ulevelDesc;
    }

    public void setUlevelDesc(String ulevelDesc) {
        this.ulevelDesc = ulevelDesc;
    }

    public String getTenTcDD() {
        return tenTcDD;
    }

    public void setTenTcDD(String tenTcDD) {
        this.tenTcDD = tenTcDD;
    }

    public String getTenLoaiDD() {
        return tenLoaiDD;
    }

    public void setTenLoaiDD(String tenLoaiDD) {
        this.tenLoaiDD = tenLoaiDD;
    }

    public String getTenNhomDD() {
        return tenNhomDD;
    }

    public void setTenNhomDD(String tenNhomDD) {
        this.tenNhomDD = tenNhomDD;
    }

    public String getMaDiemdo() {
        return maDiemdo;
    }

    public void setMaDiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public java.util.Date getNgayVh() {
        return ngayVh;
    }

    public void setNgayVh(java.util.Date ngayVh) {
        this.ngayVh = ngayVh;
    }

    public java.util.Date getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(java.util.Date ngayKt) {
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

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public java.util.Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(java.util.Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public java.util.Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(java.util.Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
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

    public BigInteger getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(BigInteger trangThai) {
        this.trangThai = trangThai;
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

    public boolean isTrangThaiToBoolean() {
        return trangThai.intValue() == 1;
    }

    public void setTrangThaiToBoolean(boolean trangThaiToBoolean) {
        this.trangThaiToBoolean = trangThaiToBoolean;
    }

    public boolean isItVhToBoolean() {
        if (itVh == null) {
            return false;
        }
        return itVh.intValue() == 1;
    }

    public void setItVhToBoolean(boolean itVhToBoolean) {
        this.itVhToBoolean = itVhToBoolean;
    }

}
