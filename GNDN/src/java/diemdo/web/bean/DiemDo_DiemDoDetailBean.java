/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoMix;
import diemdo.ADiemdoNhom;
import diemdo.ALstLoaiDd;
import diemdo.ALstTcDd;
import diemdo.ALstUlevel;
import diemdo.remote.IDiemDoRemote;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Assets;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author TUYEN EVN
 */
@ManagedBean
@ViewScoped
public class DiemDo_DiemDoDetailBean extends BasePageCommon implements Serializable {

    private ADiemdoMix diemDo;
    private IDiemDoRemote iDiemDoRemote;
    private ADiemdoNhom nhomDDSelected;
    private ALstLoaiDd loaiDDSelected;
    private ALstTcDd TcDDSelected;
    private ALstUlevel UlevelSelected;
    private String orgId;
    private boolean disableInput;
    private boolean lockNhom;
    private List<ADiemdoNhom> ListTenAndMaDD = new ArrayList<>();
    private List<ALstLoaiDd> listLoaiDD = new ArrayList<>();
    private List<ALstTcDd> listTcDD = new ArrayList<>();
    private List<ALstUlevel> listUlevel = new ArrayList<>();
    private List<S_Assets> listOrganization = new ArrayList<>();
    private String userId;
    private String orgidSelected;
    //false = edit mode
    private boolean isAdd = true;

    public DiemDo_DiemDoDetailBean() {
        try {
            userId = WorkingContext.getUserName();
            //prepare data for dialog
            diemDo = new ADiemdoMix();
            nhomDDSelected = new ADiemdoNhom();
            loaiDDSelected = new ALstLoaiDd();
            TcDDSelected = new ALstTcDd();
            UlevelSelected = new ALstUlevel();
            listLoaiDD = getIDiemDoRemote().getAllLoaiDD();
            listTcDD = getIDiemDoRemote().getAllTcDD();
            listUlevel = getIDiemDoRemote().getAllUlevel();
            listOrganization = getIDiemDoRemote().getAllOganization();

            //set default for combobox
            loaiDDSelected = listLoaiDD.get(0);
            TcDDSelected = listTcDD.get(0);
            UlevelSelected = listUlevel.get(0);
            diemDo.setItVh(BigInteger.valueOf(0));
            diemDo.setTrangThai(BigInteger.valueOf(1));
            diemDo.setItVhToBoolean(false);
            diemDo.setTrangThaiToBoolean(true);
            disableInput = false;

            //get maDD of diemdoSelected from diemdo.xhtml            
            String maDD = WorkingContext.getRequestQueryString("maDD");
            orgId = WorkingContext.getRequestQueryString("orgidNhom");
            String maNhomDD = WorkingContext.getRequestQueryString("maNhomDD");
            orgidSelected = WorkingContext.getRequestQueryString("orgidSelected");
            getAllListTenAndMaDD();
            if (orgId != null || maNhomDD != null) {
                isAdd = true;
                disableInput = false;
                nhomDDSelected.setMaNhomDd(maNhomDD);
                lockNhom = true;
            }
            if (maDD != null) {
                //switch to edit mode
                isAdd = false;
                disableInput = true;
                diemDo = getIDiemDoRemote().getDiemDoMixByID(maDD);
                nhomDDSelected = new ADiemdoNhom(diemDo.getMaNhomDd().getMaNhomDd(),
                        diemDo.getMaNhomDd().getTenNhomDd());
                lockNhom = false;
            } else {
                disableInput = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigInteger booleanToInt(boolean b) {
        int x = 0;
        if (b) {
            x = 1;
        } else {
            x = 0;
        }
        return BigInteger.valueOf(x);
    }

    //event when click button Save
    public void onSaveDiemdo() {
        try {
            if (isAdd) {
                AddNewDD();
            } else {
                ADiemdo diemDoSave = new ADiemdo(diemDo.getMaDiemdo(), diemDo.getMaLoaiDd(), diemDo.getNgayVh(), diemDo.getUserCrId(), diemDo.getUserCrDtime(), diemDo.getOrgid());
                //Set attribute for edit
                diemDoSave.setTenDiemdo(diemDo.getTenDiemdo());
                diemDoSave.setMaCto(diemDo.getMaCto());
                diemDoSave.setMoTa(diemDo.getMoTa());
                diemDoSave.setNgayKt(diemDo.getNgayKt());
                diemDoSave.setItVh(booleanToInt(diemDo.isItVhToBoolean()));
                diemDoSave.setOrgidRef(diemDo.getOrgidRef());
                diemDoSave.setMaDiemdoRef(diemDo.getMaDiemdoRef());
                diemDoSave.setUlevelid(UlevelSelected.getUlevelid());
                diemDoSave.setMaTcDd(TcDDSelected.getMaTcDd());
                diemDoSave.setMaLoaiDd(loaiDDSelected.getMaLoaiDd());
                diemDoSave.setMaNhomDd(new ADiemdoNhom(nhomDDSelected.getMaNhomDd(), nhomDDSelected.getTenNhomDd()));
                diemDoSave.setTrangThai(booleanToInt(diemDo.isTrangThaiToBoolean()));
                diemDoSave.setStt(diemDo.getStt());
                diemDoSave.setUserMdfDtime(new Date(System.currentTimeMillis()));
                diemDoSave.setUserMdfId(WorkingContext.getUserName());
                //update to db
                getIDiemDoRemote().update(diemDoSave);
                //alert
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???nh s???a th??nh c??ng"));
                PrimeFaces.current().ajax().update("CreateForm:messages");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set attribute for new object DiemDo then update to database
    public void AddNewDD() {
        try {
            ADiemdo diemDoSave = new ADiemdo(diemDo.getMaDiemdo(), loaiDDSelected.getMaLoaiDd(), diemDo.getNgayVh(), WorkingContext.getUserName(), new Date(System.currentTimeMillis()), orgId);
            //set attributes in combobox
            diemDoSave.setUlevelid(UlevelSelected.getUlevelid());
            diemDoSave.setMaTcDd(TcDDSelected.getMaTcDd());
            if (checkInput()) {
                //set other attributes
                diemDoSave.setTenDiemdo(diemDo.getTenDiemdo());
                diemDoSave.setMaCto(diemDo.getMaCto());
                diemDoSave.setMoTa(diemDo.getMoTa());
                diemDoSave.setNgayKt(diemDo.getNgayKt());
                diemDoSave.setItVh(booleanToInt(diemDo.isItVhToBoolean()));
                diemDoSave.setMaDiemdoRef(diemDo.getMaDiemdoRef());
                diemDoSave.setUlevelid(UlevelSelected.getUlevelid());
                diemDoSave.setMaTcDd(TcDDSelected.getMaTcDd());
                diemDoSave.setMaNhomDd(new ADiemdoNhom(nhomDDSelected.getMaNhomDd(), nhomDDSelected.getTenNhomDd()));
                diemDoSave.setTrangThai(booleanToInt(diemDo.isTrangThaiToBoolean()));
                diemDoSave.setStt(diemDo.getStt());
                diemDoSave.setUserMdfDtime(new Date(System.currentTimeMillis()));
                diemDoSave.setUserMdfId(WorkingContext.getUserName());
                //update to DB
                getIDiemDoRemote().insert(diemDoSave);
                //alert
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Th??m moiws th??nh c??ng"));
                PrimeFaces.current().ajax().update("CreateForm:messages");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Check input before save changing to db
    public boolean checkInput() {
        if (diemDo.getMaDiemdo() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "M?? ??i???m ??o kh??ng ???????c ????? tr???ng!"));
            PrimeFaces.current().ajax().update("CreateForm:messages");
            return false;
        }
        if (loaiDDSelected.getMaLoaiDd() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Lo???i ??i???m ??o kh??ng ???????c ????? tr???ng!"));
            PrimeFaces.current().ajax().update("CreateForm:messages");
            return false;
        }
        if (diemDo.getNgayVh() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ng??y hi???u l???c kh??ng ???????c ????? tr???ng!"));
            PrimeFaces.current().ajax().update("CreateForm:messages");
            return false;
        }
        if (IsMaDiemDoIsExits()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "M?? ??i???m ??o ???? t???n t???i!"));
            PrimeFaces.current().ajax().update("CreateForm:messages");
            return false;
        }
        return true;
    }

    //check duplicate ma nhom diem do
    public boolean IsMaDiemDoIsExits() {
        try {
            List<ADiemdo> list = getIDiemDoRemote().getAllDiemDo(userId);
            return list.stream().anyMatch((item) -> (diemDo.getMaDiemdo().equals(item.getMaDiemdo())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //get name and maDD for combobox
    public void getAllListTenAndMaDD() {
        try {
            if (orgId == null) {
                List<ADiemdoNhom> listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhomByOrgid(orgidSelected);
                for (ADiemdoNhom item : listDiemDoNhom) {
                    String tenNhomDD = item.getTenNhomDd();
                    String maNhomDD = item.getMaNhomDd();
                    ListTenAndMaDD.add(new ADiemdoNhom(maNhomDD, tenNhomDD));
                }
            } else {
                List<ADiemdoNhom> listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhomByOrgid(orgId);
                for (ADiemdoNhom item : listDiemDoNhom) {
                    String tenNhomDD = item.getTenNhomDd();
                    String maNhomDD = item.getMaNhomDd();
                    ListTenAndMaDD.add(new ADiemdoNhom(maNhomDD, tenNhomDD));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLockNhom() {
        return lockNhom;
    }

    public void setLockNhom(boolean lockNhom) {
        this.lockNhom = lockNhom;
    }

    public boolean isDisableInput() {
        return disableInput;
    }

    public void setDisableInput(boolean disableInput) {
        this.disableInput = disableInput;
    }

    public List<S_Assets> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<S_Assets> listOrganization) {
        this.listOrganization = listOrganization;
    }

    public ADiemdoMix getDiemDo() {
        return diemDo;
    }

    public void setDiemDo(ADiemdoMix diemDo) {
        this.diemDo = diemDo;
    }

    public ALstUlevel getUlevelSelected() {
        return UlevelSelected;
    }

    public void setUlevelSelected(ALstUlevel UlevelSelected) {
        this.UlevelSelected = UlevelSelected;
    }

    public List<ALstUlevel> getListUlevel() {
        return listUlevel;
    }

    public void setListUlevel(List<ALstUlevel> listUlevel) {
        this.listUlevel = listUlevel;
    }

    public ALstTcDd getTcDDSelected() {
        return TcDDSelected;
    }

    public void setTcDDSelected(ALstTcDd TcDDSelected) {
        this.TcDDSelected = TcDDSelected;
    }

    public List<ALstTcDd> getListTcDD() {
        return listTcDD;
    }

    public void setListTcDD(List<ALstTcDd> listTcDD) {
        this.listTcDD = listTcDD;
    }

    public ALstLoaiDd getLoaiDDSelected() {
        return loaiDDSelected;
    }

    public void setLoaiDDSelected(ALstLoaiDd loaiDDSelected) {
        this.loaiDDSelected = loaiDDSelected;
    }

    public List<ALstLoaiDd> getListLoaiDD() {
        return listLoaiDD;
    }

    public void setListLoaiDD(List<ALstLoaiDd> listLoaiDD) {
        this.listLoaiDD = listLoaiDD;
    }

    public List<ADiemdoNhom> getListTenAndMaDD() {
        return ListTenAndMaDD;
    }

    public void setListTenAndMaDD(List<ADiemdoNhom> ListTenAndMaDD) {
        this.ListTenAndMaDD = ListTenAndMaDD;
    }

    public ADiemdoNhom getNhomDDSelected() {
        return nhomDDSelected;
    }

    public void setNhomDDSelected(ADiemdoNhom nhomDDSelected) {
        this.nhomDDSelected = nhomDDSelected;
    }

    public boolean isIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public IDiemDoRemote getIDiemDoRemote() {
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
}
