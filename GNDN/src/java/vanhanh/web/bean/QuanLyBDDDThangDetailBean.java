/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanhanh.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoNhom;
import diemdo.remote.IDiemDoRemote;
import dulieuthuthap.ALstLoaiCso;
import dulieuthuthap.MHistoryBddd;
import dulieuthuthap.MHistoryBdddPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import main.ContextResources.EjbContext;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import org.primefaces.PrimeFaces;
import vanhanh.remote.IVanHanhRemote;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class QuanLyBDDDThangDetailBean extends BasePageCommon implements Serializable {

    private IDiemDoRemote iDiemDoRemote;
    private IVanHanhRemote iVanHanhRemote;
    private boolean disableNgay = false;
    private boolean disableLoai = false;
    //1: AddNew         2: Edit
    int mode;
    String maDiemDo;
    String maloai;
    BigInteger thang;
    BigInteger nam;
    BigInteger dateid;
    private ADiemdo diemdo;
    private ADiemdoNhom nhomDD;
    private MHistoryBddd bddd;
    private List<ALstLoaiCso> listLoaiCso = new ArrayList<>();

    public QuanLyBDDDThangDetailBean() {
        try {
            listLoaiCso = getiVanHanhRemote().getListLoaiCso();
            maDiemDo = WorkingContext.getRequestQueryString("maDiemDo");
            maloai = WorkingContext.getRequestQueryString("maloai");
            thang = BigInteger.valueOf(Integer.parseInt(WorkingContext.getRequestQueryString("thang")));
            nam = BigInteger.valueOf(Integer.parseInt(WorkingContext.getRequestQueryString("nam")));
            if (maDiemDo != null) {
                if (maloai != null) {
                    dateid = new BigInteger(WorkingContext.getRequestQueryString("dateid"));
                    mode = 2;
                } else {
                    mode = 1;
                }
                prepareForMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareForMode() {
        try {
            switch (mode) {
                case 1:
                    initNewBDDD();
                    break;
                case 2:
                    initExistBDDD();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initExistBDDD() {
        try {
            diemdo = getIDiemDoRemote().getDiemDoByID(maDiemDo);
            nhomDD = getIDiemDoRemote().getDiemDoNhomByID(diemdo.getMaNhomDd().getMaNhomDd());
            bddd = getiVanHanhRemote().getBDDDByKey(maDiemDo, dateid, maloai);
            disableLoai = disableNgay = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initNewBDDD() {
        try {
            diemdo = getIDiemDoRemote().getDiemDoByID(maDiemDo);
            nhomDD = getIDiemDoRemote().getDiemDoNhomByID(diemdo.getMaNhomDd().getMaNhomDd());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Long longdateid = Long.parseLong(formatter.format(new Date(System.currentTimeMillis())));

            bddd = new MHistoryBddd(new MHistoryBdddPK(maDiemDo, BigInteger.valueOf(longdateid), listLoaiCso.get(0).getMaLoaiCso()));
            bddd.setNgay(new Date(System.currentTimeMillis()));
            bddd.setThang(thang);
            bddd.setNam(nam);
            bddd.setNote("");
            bddd.setIsLock(BigInteger.ZERO);
            bddd.setB1g(BigDecimal.ZERO);
            bddd.setB1n(BigDecimal.ZERO);
            bddd.setB2g(BigDecimal.ZERO);
            bddd.setB2n(BigDecimal.ZERO);
            bddd.setB3g(BigDecimal.ZERO);
            bddd.setB3n(BigDecimal.ZERO);
            bddd.setHsn(BigDecimal.ONE);
            bddd.setSgg(BigDecimal.ZERO);
            bddd.setSgn(BigDecimal.ZERO);
            bddd.setVcg(BigDecimal.ZERO);
            bddd.setVcn(BigDecimal.ZERO);
            bddd.setSlKddB1g(BigDecimal.ZERO);
            bddd.setSlKddB1n(BigDecimal.ZERO);
            bddd.setSlKddB2g(BigDecimal.ZERO);
            bddd.setSlKddB2n(BigDecimal.ZERO);
            bddd.setSlKddB3g(BigDecimal.ZERO);
            bddd.setSlKddB3n(BigDecimal.ZERO);
            bddd.setSlKddSgg(BigDecimal.ZERO);
            bddd.setSlKddSgn(BigDecimal.ZERO);
            bddd.setSlKddVcg(BigDecimal.ZERO);
            bddd.setSlKddVcn(BigDecimal.ZERO);

            disableNgay = false;
            disableLoai = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onAdd(ActionEvent event) {
        try {
            switch (mode) {
                case 1:
                    mode = 2;
                    bddd = getiVanHanhRemote().getNewestBDDD(maDiemDo, thang.intValue(), nam.intValue());
                    disableLoai = disableNgay = true;
                    sendMessage("Th??ng b??o", "Ch??? ????? s???a th??ng tin");
                    break;
                case 2:
                    mode = 1;
                    prepareForMode();
                    sendMessage("Th??ng b??o", "Ch??? ????? th??m m???i");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSave(ActionEvent event) {
        try {
            switch (mode) {
                case 1:
                    addNewBDDD();
                    break;
                case 2:
                    editExistBDDD();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editExistBDDD() {
        try {
            bddd.setUserMdfId(WorkingContext.getUserName());
            bddd.setUserMdfDtime(new Date(System.currentTimeMillis()));
            getiVanHanhRemote().update(bddd);
            sendMessage("Th??ng b??o", "Ch???nh s???a th??nh c??ng!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewBDDD() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Long longdateid = Long.parseLong(formatter.format(bddd.getNgay()));
            bddd.setMHistoryBdddPK(new MHistoryBdddPK(maDiemDo, BigInteger.valueOf(longdateid), bddd.getMHistoryBdddPK().getLoai()));
            bddd.setUserCrId(WorkingContext.getUserName());
            bddd.setUserCrDtime(new Date(System.currentTimeMillis()));
            getiVanHanhRemote().insert(bddd);
            sendMessage("Th??ng b??o", "Th??m m???i th??nh c??ng!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String title, String content) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, content));
        PrimeFaces.current().ajax().update("BDDDForm:messages");
    }

    private IVanHanhRemote getiVanHanhRemote() {
        try {
            if (iVanHanhRemote == null) {
                iVanHanhRemote = (IVanHanhRemote) EjbContext.getLocalEJBRemote(IVanHanhRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login l???i khi g???i ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iVanHanhRemote;
    }

    private IDiemDoRemote getIDiemDoRemote() {
        try {
            if (iDiemDoRemote == null) {
                iDiemDoRemote = (IDiemDoRemote) EjbContext.getLocalEJBRemote(IDiemDoRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login l???i khi g???i ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iDiemDoRemote;
    }

    public boolean isDisableLoai() {
        return disableLoai;
    }

    public void setDisableLoai(boolean disableLoai) {
        this.disableLoai = disableLoai;
    }

    public boolean isDisableNgay() {
        return disableNgay;
    }

    public void setDisableNgay(boolean disableNgay) {
        this.disableNgay = disableNgay;
    }

    public MHistoryBddd getBddd() {
        return bddd;
    }

    public void setBddd(MHistoryBddd bddd) {
        this.bddd = bddd;
    }

    public List<ALstLoaiCso> getListLoaiCso() {
        return listLoaiCso;
    }

    public void setListLoaiCso(List<ALstLoaiCso> listLoaiCso) {
        this.listLoaiCso = listLoaiCso;
    }

    public ADiemdo getDiemdo() {
        return diemdo;
    }

    public void setDiemdo(ADiemdo diemdo) {
        this.diemdo = diemdo;
    }

    public ADiemdoNhom getNhomDD() {
        return nhomDD;
    }

    public void setNhomDD(ADiemdoNhom nhomDD) {
        this.nhomDD = nhomDD;
    }

}
