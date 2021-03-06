/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanhanh.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoNhom;
import diemdo.remote.IDiemDoRemote;
import dulieuthuthap.MHistoryBddd;
import dulieuthuthap.MHistoryBdddMix;
import java.io.Serializable;
import java.util.ArrayList;
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
import vanhanh.remote.IVanHanhRemote;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class QuanLyBienDongDDThangBean extends BasePageCommon implements Serializable {

    private IDiemDoRemote iDiemDoRemote;
    private IVanHanhRemote iVanHanhRemote;
    private String userId;
    private int monthLoad = 0;
    private int yearLoad = 0;
    private boolean isShowData = false;
    private S_Assets orgIdSelected;
    private ADiemdoNhom nhomDDSelected;
    private ADiemdo diemDoSelected;
    private List<MHistoryBdddMix> listBDDDSelected = new ArrayList<>();
    private List<ADiemdo> listDiemDoSorted = new ArrayList<>();
    private List<ADiemdoNhom> listDiemDoNhomSorted = new ArrayList<>();
    private List<S_Assets> listOrg = new ArrayList<>();
    private List<MHistoryBdddMix> listBDDD = new ArrayList<>();

    public QuanLyBienDongDDThangBean() {
        try {
            isShowData = false;
            userId = WorkingContext.getUserName();
            listOrg = getiVanHanhRemote().getAllOrgTreetable(userId);
            orgIdSelected = listOrg.get(0);
            listDiemDoNhomSorted = getIDiemDoRemote().getAllDiemDoNhomByOrgid(orgIdSelected.getOrgid());
            nhomDDSelected = listDiemDoNhomSorted.get(0);
            listDiemDoSorted = getIDiemDoRemote().getListDiemDoByMaNhomDD(nhomDDSelected.getMaNhomDd());
            diemDoSelected = listDiemDoSorted.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLoadData() {
        try {
            if (monthLoad == 0 || yearLoad == 0) {
                sendMessage("Th??ng b??o", "M???i nh???p th??ng v?? n??m!");
            } else {
                listBDDD = getiVanHanhRemote().getListBDDDByMaDDTimeMix(diemDoSelected.getMaDiemdo(), monthLoad, yearLoad);
                isShowData = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAdd() {
        try {
            if (monthLoad == 0 || yearLoad == 0) {
                sendMessage("Th??ng b??o", "M???i nh???p th??ng v?? n??m!");
            } else {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDialogAdd");
                PrimeFaces.current().ajax().addCallbackParam("maDiemDo", diemDoSelected.getMaDiemdo());
                PrimeFaces.current().ajax().addCallbackParam("thang", monthLoad);
                PrimeFaces.current().ajax().addCallbackParam("nam", yearLoad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEdit() {
        try {
            if (listBDDDSelected.size() != 1) {
                sendMessage("Th??ng b??o", "Ch???n m???t bi???n ?????ng ??o ?????m ????? s???a");
            } else if (monthLoad != 0 && yearLoad != 0) {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDialogEdit");
                PrimeFaces.current().ajax().addCallbackParam("maDiemDo", diemDoSelected.getMaDiemdo());
                PrimeFaces.current().ajax().addCallbackParam("thang", monthLoad);
                PrimeFaces.current().ajax().addCallbackParam("nam", yearLoad);
                PrimeFaces.current().ajax().addCallbackParam("dateid", listBDDDSelected.get(0).getMHistoryBdddPK().getDateid());
                PrimeFaces.current().ajax().addCallbackParam("maloai", listBDDDSelected.get(0).getMHistoryBdddPK().getLoai());
            } else {
                sendMessage("Th??ng b??o", "M???i nh???p th??ng v?? n??m!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDelete() {
        try {
            if (!listBDDDSelected.isEmpty()) {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDeleteDialog");
            } else {
                sendMessage("Th??ng b??o", "Ch???n m???t ????? x??a");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doDeleteDiemDo() {
        try {
            listBDDDSelected.stream().forEach((item) -> {
                getiVanHanhRemote().delete(item.getMHistoryBdddPK(), MHistoryBddd.class);
            });
            //update main page
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDelClose() {
        try {
            if (monthLoad != 0 && yearLoad != 0 && isShowData == true) {
                listBDDD = getiVanHanhRemote().getListBDDDByMaDDTimeMix(diemDoSelected.getMaDiemdo(), monthLoad, yearLoad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCalculate() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClose() {
        try {
            if (monthLoad != 0 && yearLoad != 0 && isShowData == true) {
                listBDDD = getiVanHanhRemote().getListBDDDByMaDDTimeMix(diemDoSelected.getMaDiemdo(), monthLoad, yearLoad);
                listBDDDSelected.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onOrgidChange() {
        try {
            if (orgIdSelected.getOrgid().equals("NPT")) {
                listDiemDoNhomSorted = getIDiemDoRemote().getAllDiemDoNhom(userId);
                if (!listDiemDoNhomSorted.isEmpty()) {
                    nhomDDSelected = listDiemDoNhomSorted.get(0);
                    listDiemDoSorted = getIDiemDoRemote().getListDiemDoByMaNhomDD(nhomDDSelected.getMaNhomDd());
                    if (!listDiemDoSorted.isEmpty()) {
                        diemDoSelected = listDiemDoSorted.get(0);
                    } else {
                        diemDoSelected.setMaDiemdo("");
                        diemDoSelected.setTenDiemdo("");
                    }
                } else {
                    nhomDDSelected.setTenNhomDd("");
                    diemDoSelected.setMaDiemdo("");
                    diemDoSelected.setTenDiemdo("");
                }
            } else {
                listDiemDoNhomSorted = getIDiemDoRemote().getAllDiemDoNhomByOrgid(orgIdSelected.getOrgid());
                if (!listDiemDoNhomSorted.isEmpty()) {
                    nhomDDSelected = listDiemDoNhomSorted.get(0);
                    listDiemDoSorted = getIDiemDoRemote().getListDiemDoByMaNhomDD(nhomDDSelected.getMaNhomDd());
                    if (!listDiemDoSorted.isEmpty()) {
                        diemDoSelected = listDiemDoSorted.get(0);
                    } else {
                        diemDoSelected.setMaDiemdo("");
                        diemDoSelected.setTenDiemdo("");
                    }
                } else {
                    nhomDDSelected.setTenNhomDd("");
                    diemDoSelected.setMaDiemdo("");
                    diemDoSelected.setTenDiemdo("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMaNhomDDChange() {
        try {
            listDiemDoSorted = getIDiemDoRemote().getListDiemDoByMaNhomDD(nhomDDSelected.getMaNhomDd());
            diemDoSelected = listDiemDoSorted.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String title, String content) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, content));
        PrimeFaces.current().ajax().update("formBienDong:messages");
    }

    public IVanHanhRemote
            getiVanHanhRemote() {
        try {
            if (iVanHanhRemote == null) {
                iVanHanhRemote = (IVanHanhRemote) EjbContext.getLocalEJBRemote(IVanHanhRemote.class
                        .getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login l???i khi g???i ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iVanHanhRemote;
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

    public List<MHistoryBdddMix> getListBDDDSelected() {
        return listBDDDSelected;
    }

    public void setListBDDDSelected(List<MHistoryBdddMix> listBDDDSelected) {
        this.listBDDDSelected = listBDDDSelected;
    }

    public List<MHistoryBdddMix> getListBDDD() {
        return listBDDD;
    }

    public void setListBDDD(List<MHistoryBdddMix> listBDDD) {
        this.listBDDD = listBDDD;
    }

    public ADiemdo getDiemDoSelected() {
        return diemDoSelected;
    }

    public void setDiemDoSelected(ADiemdo diemDoSelected) {
        this.diemDoSelected = diemDoSelected;
    }

    public ADiemdoNhom getNhomDDSelected() {
        return nhomDDSelected;
    }

    public void setNhomDDSelected(ADiemdoNhom nhomDDSelected) {
        this.nhomDDSelected = nhomDDSelected;
    }

    public List<ADiemdoNhom> getListDiemDoNhomSorted() {
        return listDiemDoNhomSorted;
    }

    public void setListDiemDoNhomSorted(List<ADiemdoNhom> listDiemDoNhomSorted) {
        this.listDiemDoNhomSorted = listDiemDoNhomSorted;
    }

    public List<ADiemdo> getListDiemDoSorted() {
        return listDiemDoSorted;
    }

    public void setListDiemDoSorted(List<ADiemdo> listDiemDoSorted) {
        this.listDiemDoSorted = listDiemDoSorted;
    }

    public S_Assets getOrgIdSelected() {
        return orgIdSelected;
    }

    public void setOrgIdSelected(S_Assets orgIdSelected) {
        this.orgIdSelected = orgIdSelected;
    }

    public List<S_Assets> getListOrg() {
        return listOrg;
    }

    public void setListOrg(List<S_Assets> listOrg) {
        this.listOrg = listOrg;
    }

    public int getMonthLoad() {
        return monthLoad;
    }

    public void setMonthLoad(int monthLoad) {
        this.monthLoad = monthLoad;
    }

    public int getYearLoad() {
        return yearLoad;
    }

    public void setYearLoad(int yearLoad) {
        this.yearLoad = yearLoad;
    }
}
