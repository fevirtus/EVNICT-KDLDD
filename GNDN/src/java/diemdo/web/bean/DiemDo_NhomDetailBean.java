/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.web.bean;

import diemdo.ADiemdoNhom;
import diemdo.ALstCategory;
import diemdo.ALstType;
import diemdo.ejb.DiemDoBL;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import main.ContextResources.EjbContext;
import main.web.common.bean.BasePageCommon;
import org.primefaces.PrimeFaces;
import diemdo.remote.IDiemDoRemote;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import main.web.common.bean.WorkingContext;

/**
 *
 * @author TUYEN EVN
 */
@ManagedBean
@ViewScoped
public class DiemDo_NhomDetailBean extends BasePageCommon implements Serializable {

    private ADiemdoNhom diemDoNhom;
    private IDiemDoRemote iDiemDoRemote;
    private List<ADiemdoNhom> listDiemDoNhom = new ArrayList<>();
    private List<ALstCategory> listCategory = new ArrayList<>();
    private List<ALstType> listType = new ArrayList<>();
    //true = add mode
    //false = edit mode
    private boolean isAdd = true;
    private String userId;

    public DiemDo_NhomDetailBean() {
        try {
            userId = WorkingContext.getUserName();
            listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhom(userId);
            listCategory = getIDiemDoRemote().getAllCategoryId(userId);
            listType = getIDiemDoRemote().getAllTypeId();
            diemDoNhom = new ADiemdoNhom();
            String maNhomDD = WorkingContext.getRequestQueryString("maNhomDD");
            if (maNhomDD != null) {
                isAdd = false;
                diemDoNhom = getIDiemDoRemote().getDiemDoNhomByID(maNhomDD);
                listCategory = getIDiemDoRemote().getAllCategoryByTypeId(diemDoNhom.getTypeid());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void onKieuChange(){
        listCategory = getIDiemDoRemote().getAllCategoryByTypeId(diemDoNhom.getTypeid());
    }

    //event when click button save
    public void onSaveNhom() {
        try {
            if (isAdd) {
                AddNewNhomDD();
            } else {
                //set modifiry time
                diemDoNhom.setUserMdfDtime(new Date(System.currentTimeMillis()));
                diemDoNhom.setUserMdfId(WorkingContext.getUserName());
                //update to db
                getIDiemDoRemote().update(diemDoNhom);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???nh s???a th??nh c??ng"));
                PrimeFaces.current().ajax().update("CreateNhomForm:messages");
            }
        } catch (Exception e) {
        } finally {
            //PrimeFaces.current().executeScript("location.reload()");
//            clear();
        }
    }

    //Add new nhom diem do
    public void AddNewNhomDD() {
        try {
            if (checkInput()) {
                diemDoNhom.setOrgid(WorkingContext.getOrganizationCurrent());
                diemDoNhom.setUserCrId(WorkingContext.getUserName());
                diemDoNhom.setUserCrDtime(new Date(System.currentTimeMillis()));
                diemDoNhom.setUserMdfDtime(new Date(System.currentTimeMillis()));
                getIDiemDoRemote().insert(diemDoNhom);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Th??m m???i th??nh c??ng"));
                PrimeFaces.current().ajax().update("CreateNhomForm:messages");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Check input before save change to db
    public boolean checkInput() {
        if (diemDoNhom.getMaNhomDd() == null || diemDoNhom.getMaNhomDd().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "M?? ??i???m ??o kh??ng ???????c ????? tr???ng!"));
            PrimeFaces.current().ajax().update("CreateNhomForm:messages");
            return false;
        }
        if (IsMaDiemDoIsExits()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "M?? ??i???m ??o ???? t???n t???i!"));
            PrimeFaces.current().ajax().update("CreateNhomForm:messages");
            return false;
        }
        return true;
    }

    //check duplicate ma nhom diem do
    public boolean IsMaDiemDoIsExits() {
        List<ADiemdoNhom> list = getIDiemDoRemote().getAllDiemDoNhom(userId);
        return list.stream().anyMatch((item) -> (diemDoNhom.getMaNhomDd().equals(item.getMaNhomDd())));
    }

    //auto complete for category
    public List<String> completeTextCategory(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> list = new ArrayList<>();
        try {
            for (ALstCategory item : listCategory) {
                if (item.getCategoryid() != null) {
                    list.add(item.getCategoryid());
                }
            }
        } catch (Exception e) {
        }
        return list.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    //auto complete for type
    public List<String> completeTextType(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> list = new ArrayList<>();
        try {
            for (ALstType item : listType) {
                if (item.getTypeid() != null) {
                    list.add(item.getTypeid());
                }
            }
        } catch (Exception e) {
        }
        return list.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public List<ALstCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<ALstCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<ALstType> getListType() {
        return listType;
    }

    public void setListType(List<ALstType> listType) {
        this.listType = listType;
    }

    public List<ADiemdoNhom> getListDiemDoNhom() {
        return listDiemDoNhom;
    }

    public void setListDiemDoNhom(List<ADiemdoNhom> listDiemDoNhom) {
        this.listDiemDoNhom = listDiemDoNhom;
    }

    public boolean isIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public ADiemdoNhom getDiemDoNhom() {
        return diemDoNhom;
    }

    public void setDiemDoNhom(ADiemdoNhom diemDoNhom) {
        this.diemDoNhom = diemDoNhom;
    }

    public void clear() {
        diemDoNhom = new ADiemdoNhom();
        iDiemDoRemote = new DiemDoBL();
        isAdd = true;
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
