package diemdo.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoMix;
import diemdo.ADiemdoNhom;
import diemdo.remote.IDiemDoRemote;
import main.web.common.bean.BasePageCommon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.ContextResources.EjbContext;
import main.web.common.bean.WorkingContext;
import main.entity.shared.system.S_Assets;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class DiemDoBean extends BasePageCommon implements Serializable {

    private List<ADiemdoNhom> listDiemDoNhom = new ArrayList<>();
    private List<ADiemdoMix> listDiemdoMix = new ArrayList<>();
    private List<S_Assets> listOrgIdByUserId = new ArrayList<>();

    private List<ADiemdoMix> listDiemDoMixSelected = new ArrayList<>();
    private List<ADiemdoNhom> listDiemDoNhomSelected = new ArrayList<>();
    private IDiemDoRemote iDiemDoRemote;
    private String ngayVHToString;
    private String userId;
    private String orgidSelected;

    public DiemDoBean() {
        try {
            userId = WorkingContext.getUserName();
            listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhom(userId);
            listDiemdoMix = getIDiemDoRemote().getAllDiemDoMix(userId);
            listOrgIdByUserId = getIDiemDoRemote().getListOrgIdByUserId(userId);
            orgidSelected = listOrgIdByUserId.get(0).getOrgid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onOrgidChange() {
        try {
            List<S_Assets> list = getIDiemDoRemote().getListChildOrg(orgidSelected);
            if (list.stream().anyMatch(item -> item.getOrlevel() == 1)) {
                listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhom(userId);
                listDiemdoMix = getIDiemDoRemote().getAllDiemDoMix(userId);
                listDiemDoNhomSelected.clear();
            } else {
                listDiemDoNhom = getIDiemDoRemote().getAllDiemDoNhomByOrgid(orgidSelected);
                listDiemdoMix = getIDiemDoRemote().getAllDiemDoMixByOrgid(orgidSelected);
                listDiemDoNhomSelected.clear();
            }
        } catch (Exception e) {

        }

    }

    public void onClose() {
        if (!listDiemDoNhomSelected.isEmpty()) {
            listDiemdoMix = getIDiemDoRemote().getAllDiemDoMixByMaNhomDD(orgidSelected, listDiemDoNhomSelected.get(0).getMaNhomDd());
        } else {
            listDiemdoMix = getIDiemDoRemote().getAllDiemDoMix(userId);
        }
    }

    //event of 3 button in NhomDD datatable
    //open dialog when click on Add Button
    public void onAddNhom() {
        try {
            //display dialog create
            PrimeFaces.current().ajax().addCallbackParam("Method", "openDialogNhom");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //open dialog when click on Edit Button
    public void onEditNhom() {
        try {
            if (!listDiemDoNhomSelected.isEmpty()) {
                if (listDiemDoNhomSelected.size() == 1) {
                    PrimeFaces.current().ajax().addCallbackParam("Method", "openEditDialogNhom");
                    PrimeFaces.current().ajax().addCallbackParam("maNhomDD", listDiemDoNhomSelected.get(0).getMaNhomDd());
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? s???a"));
                    PrimeFaces.current().ajax().update("formNhom:messages");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? s???a"));
                PrimeFaces.current().ajax().update("formNhom:messages");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //open dialog when click on Delete Button
    public void onDeleteNhom() {
        try {
            if (!listDiemDoNhomSelected.isEmpty()) {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDeleteDialogNhom");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? x??a"));
                PrimeFaces.current().ajax().update("formNhom:messages");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete item from db when user confirm in DelDialog
    public void doDeleteNhom() {
        try {
            listDiemDoNhomSelected.stream().forEach((item) -> {
                getIDiemDoRemote().delete(item.getMaNhomDd(), ADiemdoNhom.class);
            });

            //update main page
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "???? x??a th??nh c??ng"));
            PrimeFaces.current().ajax().update("formNhom:messages");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //event of 3 button in Diemdo datatable
    //Open dialog when click on Add button
    public void onAddDiemDo() {
        try {
            if (!listDiemDoNhomSelected.isEmpty()) {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDialogDiemDo");
                PrimeFaces.current().ajax().addCallbackParam("orgidNhom", listDiemDoNhomSelected.get(0).getOrgid());
                PrimeFaces.current().ajax().addCallbackParam("maNhomDD", listDiemDoNhomSelected.get(0).getMaNhomDd());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n ????n v??? qu???n l?? tr?????c khi t???o ??i???m ??o m???i."));
                PrimeFaces.current().ajax().update("formNhom:messages");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Open dialog when click on Edit button
    public void onEdiDiemDo(String maDiemdo) {
        try {
            PrimeFaces.current().ajax().addCallbackParam("Method", "openEditDialogDiemDo");
            PrimeFaces.current().ajax().addCallbackParam("maDD", maDiemdo);
            PrimeFaces.current().ajax().addCallbackParam("orgidSelected", orgidSelected);
//            if (!listDiemDoMixSelected.isEmpty()) {
//                if (listDiemDoMixSelected.size() == 1) {
//                    PrimeFaces.current().ajax().addCallbackParam("Method", "openEditDialogDiemDo");
//                    PrimeFaces.current().ajax().addCallbackParam("maDD", listDiemDoMixSelected.get(0).getMaDiemdo());
//                } else {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? s???a"));
//                    PrimeFaces.current().ajax().update("formNhom:messages");
//                }
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? s???a"));
//                PrimeFaces.current().ajax().update("formNhom:messages");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Open dialog when click on Delete button
    public void onDeleteDiemDo() {
        try {
            if (!listDiemDoMixSelected.isEmpty()) {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDeleteDialogDiemDo");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? x??a"));
                PrimeFaces.current().ajax().update("formNhom:messages");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete item from db when user confirm in DelDialog
    public void doDeleteDiemDo() {
        try {
            listDiemDoMixSelected.stream().forEach((item) -> {
                getIDiemDoRemote().delete(item.getMaDiemdo(), ADiemdo.class);
            });
            //update main page
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //transfer maDD to AXDialog and show it up
    public void onAX() {
        try {
            if (!listDiemDoMixSelected.isEmpty()) {
                if (listDiemDoMixSelected.size() == 1) {
                    PrimeFaces.current().ajax().addCallbackParam("Method", "openAXDialog");
                    PrimeFaces.current().ajax().addCallbackParam("maDD", listDiemDoMixSelected.get(0).getMaDiemdo());
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? ??nh x???"));
                    PrimeFaces.current().ajax().update("formNhom:messages");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? ??nh x???"));
                PrimeFaces.current().ajax().update("formNhom:messages");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRowSelected() {
        try {
            if (!listDiemDoNhomSelected.isEmpty()) {
                listDiemdoMix = getIDiemDoRemote().getAllDiemDoMixByMaNhomDD(orgidSelected, listDiemDoNhomSelected.get(0).getMaNhomDd());
            } else {
                listDiemdoMix = getIDiemDoRemote().getAllDiemDoMix(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String displayTitle() {
        String str = "";
        try {
            if (listDiemDoNhomSelected.isEmpty()) {
                str = "";
            } else {
                str = listDiemDoNhomSelected.get(0).getTenNhomDd();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getOrgidSelected() {
        return orgidSelected;
    }

    public void setOrgidSelected(String orgidSelected) {
        this.orgidSelected = orgidSelected;
    }

    public List<S_Assets> getListOrgIdByUserId() {
        return listOrgIdByUserId;
    }

    public void setListOrgIdByUserId(List<S_Assets> listOrgIdByUserId) {
        this.listOrgIdByUserId = listOrgIdByUserId;
    }

    public List<ADiemdoMix> getListDiemDoMixSelected() {
        return listDiemDoMixSelected;
    }

    public void setListDiemDoMixSelected(List<ADiemdoMix> listDiemDoMixSelected) {
        this.listDiemDoMixSelected = listDiemDoMixSelected;
    }

    public List<ADiemdoMix> getListDiemdoMix() {
        return listDiemdoMix;
    }

    public void setListDiemdoMix(List<ADiemdoMix> listDiemdoMix) {
        this.listDiemdoMix = listDiemdoMix;
    }

    public List<ADiemdoNhom> getListDiemDoNhomSelected() {
        return listDiemDoNhomSelected;
    }

    public void setListDiemDoNhomSelected(List<ADiemdoNhom> listDiemDoNhomSelected) {
        this.listDiemDoNhomSelected = listDiemDoNhomSelected;
    }

    public String getNgayVHToString() {
        return ngayVHToString;
    }

    public void setNgayVHToString(String ngayVHToString) {
        this.ngayVHToString = ngayVHToString;
    }

    public List<ADiemdoNhom> getListDiemDoNhom() {
        return listDiemDoNhom;
    }

    public void setListDiemDoNhom(List<ADiemdoNhom> listDiemDoNhom) {
        this.listDiemDoNhom = listDiemDoNhom;
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
