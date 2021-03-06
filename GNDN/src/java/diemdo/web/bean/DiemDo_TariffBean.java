/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoTariff;
import diemdo.ADiemdoTariffMix;
import diemdo.ADiemdoTariffMix2;
import diemdo.remote.IDiemDoRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.ContextResources.EjbContext;
import main.web.common.bean.BasePageCommon;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class DiemDo_TariffBean extends BasePageCommon implements Serializable {

    private List<ADiemdoTariffMix2> listDDTariff = new ArrayList<>();           //list all DD have tariff
    private List<ADiemdoTariff> listTariff = new ArrayList<>();             //list all tariff
    private List<ADiemdoTariffMix> listTariffMix = new ArrayList<>();
    private List<ADiemdoTariffMix2> listDDTariffSelected = new ArrayList<>();
    private ADiemdo diemDoSelected = new ADiemdo();
    private IDiemDoRemote iDiemDoRemote;
    private Integer monthLoad = 0;
    private Integer yearLoad = 0;

    public DiemDo_TariffBean() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddTariff() {
        try {
            if (!listDDTariffSelected.isEmpty() && listDDTariffSelected.size() == 1) {
                String insertTariff = "true";
                PrimeFaces.current().ajax().addCallbackParam("Method", "openEditDialogTariff");
                PrimeFaces.current().ajax().addCallbackParam("maDD", listDDTariffSelected.get(0).getADiemdoTariffPK().getMaDiemdo());
                PrimeFaces.current().ajax().addCallbackParam("nam", listDDTariffSelected.get(0).getADiemdoTariffPK().getNam());
                PrimeFaces.current().ajax().addCallbackParam("thang", listDDTariffSelected.get(0).getADiemdoTariffPK().getThang());
                PrimeFaces.current().ajax().addCallbackParam("dateId", listDDTariffSelected.get(0).getADiemdoTariffPK().getDateid());
                PrimeFaces.current().ajax().addCallbackParam("insertTariff", insertTariff);
            } else {
                PrimeFaces.current().ajax().addCallbackParam("Method", "openDialogTariff");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onEditTariff() {
        try {
            if (!listDDTariffSelected.isEmpty() && listDDTariffSelected.size() == 1) {
                if (true) {

                }
                PrimeFaces.current().ajax().addCallbackParam("Method", "openEditDialogTariff");
                PrimeFaces.current().ajax().addCallbackParam("maDD", listDDTariffSelected.get(0).getADiemdoTariffPK().getMaDiemdo());
                PrimeFaces.current().ajax().addCallbackParam("nam", listDDTariffSelected.get(0).getADiemdoTariffPK().getNam());
                PrimeFaces.current().ajax().addCallbackParam("thang", listDDTariffSelected.get(0).getADiemdoTariffPK().getThang());
                PrimeFaces.current().ajax().addCallbackParam("dateId", listDDTariffSelected.get(0).getADiemdoTariffPK().getDateid());
                PrimeFaces.current().ajax().addCallbackParam("insertTariff", "false");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Th??ng b??o", "Ch???n m???t ????? s???a"));
                PrimeFaces.current().ajax().update("formTariff:messages");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onLoadData() {
        try {
            if (monthLoad == null || yearLoad == null) {
                DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formTariff:@id(listDD)");
                dataTable.resetValue();
            } else {
                //Load data for tariff history datatable
                listTariffMix = getIDiemDoRemote().getAllTariffMix(monthLoad, yearLoad);

                //Load data for diemdo had been config datatable
                listDDTariff = getIDiemDoRemote().getAllTariffMix2(monthLoad, yearLoad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String title, String content) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, content));
        PrimeFaces.current().ajax().update("tariffForm:messages");
    }

    public Integer getMonthLoad() {
        return monthLoad;
    }

    public void setMonthLoad(Integer monthLoad) {
        this.monthLoad = monthLoad;
    }

    public Integer getYearLoad() {
        return yearLoad;
    }

    public void setYearLoad(Integer yearLoad) {
        this.yearLoad = yearLoad;
    }

    public List<ADiemdoTariffMix> getListTariffMix() {
        return listTariffMix;
    }

    public void setListTariffMix(List<ADiemdoTariffMix> listTariffMix) {
        this.listTariffMix = listTariffMix;
    }

    public List<ADiemdoTariff> getListTariff() {
        return listTariff;
    }

    public void setListTariff(List<ADiemdoTariff> listTariff) {
        this.listTariff = listTariff;
    }

    public List<ADiemdoTariffMix2> getListDDTariff() {
        return listDDTariff;
    }

    public void setListDDTariff(List<ADiemdoTariffMix2> listDDTariff) {
        this.listDDTariff = listDDTariff;
    }

    public List<ADiemdoTariffMix2> getListDDTariffSelected() {
        return listDDTariffSelected;
    }

    public void setListDDTariffSelected(List<ADiemdoTariffMix2> listDDTariffSelected) {
        this.listDDTariffSelected = listDDTariffSelected;
    }

    public ADiemdo getDiemDoSelected() {
        return diemDoSelected;
    }

    public void setDiemDoSelected(ADiemdo diemDoSelected) {
        this.diemDoSelected = diemDoSelected;
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
