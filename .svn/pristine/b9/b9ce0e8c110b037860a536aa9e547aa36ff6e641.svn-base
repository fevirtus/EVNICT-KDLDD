/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_User;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Datalist;
import main.entity.shared.report.S_Report_Form;
import main.entity.shared.system.S_Attribute;
import main.entity.shared.system.S_Attribute_Group;
import main.entity.shared.system.S_Organization;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.ConvertExcelToHtml;
import main.web.shared.system.bean.ListBaseDataInfoAttGroupDataModel_;
import main.web.shared.system.bean.ListBaseDataInfoDataModel;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseDataInfo;
import evnit.util.setting.DBSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

@ManagedBean
@ViewScoped
public class S_ReportResultBean extends BasePageCommon implements Serializable {

    private String objId;
    private List<S_Report_Form> lstReportForm;
    private IReportConfigRemote m_IReportConfigRemote;
    private String reportFormSelect;
//    private S_ReportResultBean iattGroupReport;
    private HashMap<String, Object> pramReport;
    private HashMap<String, Object> pramTypeReport;
    private HashMap<String, Object> resultReport;
    private List<S_Report_Datalist> lstReportDataList;
    private StreamedContent fileReport;
    private String txtLog;
    private String reportHtmlView;
    private S_Report report;
    public ResourcesFactory m_RfAttProp = null;
//    public IAssetRemote m_IAssetRemote = null;
    public ISystemCommonRemote m_ISystemCommonRemote = null;
    public boolean m_disableButton = false;
    public boolean m_disableButtonAdd = false;
    public boolean m_disableButtonSave = false;
    //public List<BaseDataInfo> m_lstAttGroup; //ThảoDD thêm để xử lý khi thêm mới nhóm
    public ListBaseDataInfoAttGroupDataModel_ m_dmAttGroup; //ThảoDD thêm để xử lý khi thêm mới nhóm
    public List<BaseDataInfo> ListListQuery;
    public BaseDataInfo[] SelectionListQuery;
    public BaseDataInfo SelectionOneListQuery;
    public String m_ListQueryID;
    public String m_ListQueryDesc;
    public String sAttrID;
    public String sAttrDataID;
    public String sQueryList;
    public String sQueryOne;
    public Integer iValue;
    public Integer iDesc;
    private String m_objRightVal;
    public String sAttTypeId;
    public List<BaseDataInfo> lstAttGroup;
    public String sObjIDInfo;
    public String sObjTypeInfo;
    public boolean bShowReport = false;

    public S_ReportResultBean() {
        try {
            objId = WorkingContext.getRequestQueryString("rId");
            sObjIDInfo = objId;
            sObjTypeInfo = "RPT";

            if (objId != null) {
                String sRight = BaseConstant.getObjRightFull();
                WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, sRight);
                lstReportForm = getIReportConfigRemote().getListReportForm(objId);
                lstReportDataList = getIReportConfigRemote().getListReportDataList(objId);
                report = (S_Report) getIReportConfigRemote().findById(objId, S_Report.class);
            }
            loadAttGroup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<BaseDataInfo> getLstQuery(String sAttrID) {

        List<BaseDataInfo> lstResult = new ArrayList<BaseDataInfo>();
        S_Attribute tempAtt = (S_Attribute) getISystemCommonRemote().findById(sAttrID, S_Attribute.class
        );
        String sQueryListTemp = tempAtt.getDataquerylst();
        Integer iValueTemp = tempAtt.getDatafieldvalue();
        Integer iDescTemp = tempAtt.getDatafielddesc();
        if (sQueryListTemp != null && iValueTemp != null && iDescTemp
                != null) {
            lstResult = getISystemCommonRemote().getListFromQuery(sQueryListTemp, iValueTemp, iDescTemp, null, (List<BaseDataInfo>) m_dmAttGroup.getWrappedData(), null);
        }
        return lstResult;
    }

    public final void loadAttGroup() {
        CheckObjIDInfo();
        CheckDtAttGroup();
        CheckObjRight();
    }
    //}}</editor-fold>

    public boolean isbShowReport() {
        return bShowReport;
    }

    //{{<editor-fold defaultstate="collapsed" desc="Get Set">
    public void setbShowReport(boolean bShowReport) {
        this.bShowReport = bShowReport;
    }

    public BaseDataInfo getSelectionOneListQuery() {
        return SelectionOneListQuery;
    }

    public void setSelectionOneListQuery(BaseDataInfo SelectionOneListQuery) {
        this.SelectionOneListQuery = SelectionOneListQuery;
    }

    public String getM_ListQueryDesc() {
        return m_ListQueryDesc;
    }

    public void setM_ListQueryDesc(String m_ListQueryDesc) {
        this.m_ListQueryDesc = m_ListQueryDesc;
    }

    public String getM_ListQueryID() {
        return m_ListQueryID;
    }

    public void setM_ListQueryID(String m_ListQueryID) {
        this.m_ListQueryID = m_ListQueryID;
    }

    public BaseDataInfo[] getSelectionListQuery() {
        return SelectionListQuery;
    }

    public void setSelectionListQuery(BaseDataInfo[] SelectionListQuery) {
        this.SelectionListQuery = SelectionListQuery;
    }

    public boolean isM_disableButton() {
        return m_disableButton;
    }

    public void setM_disableButton(boolean m_disableButton) {
        this.m_disableButton = m_disableButton;
    }

    public boolean isM_disableButtonAdd() {
        return m_disableButtonAdd;
    }

    public void setM_disableButtonAdd(boolean m_disableButtonAdd) {
        this.m_disableButtonAdd = m_disableButtonAdd;
    }

    public boolean isM_disableButtonSave() {
        return m_disableButtonSave;
    }

    public void setM_disableButtonSave(boolean m_disableButtonSave) {
        this.m_disableButtonSave = m_disableButtonSave;
    }
    //}}</editor-fold>

    public void CheckObjIDInfo() {
        if (sObjIDInfo == null || sObjIDInfo.equals("")) {
            //m_disableButton = true;
            m_disableButtonAdd = true;
            m_disableButtonSave = true;
        } else {
            LoadAttGroupForObject(sObjIDInfo, sObjTypeInfo);
        }
    }

    public void CheckDtAttGroup() {
        //ThảoDD: bỏ dòng lệnh bên dưới vì gây lặp, không thể lấy được input từ grid
        //if (getDtAttGroupMinh() != null && getDtAttGroupMinh().getValue() != null) {
        ListBaseDataInfoAttGroupDataModel_ l1 = m_dmAttGroup;// (ListBaseDataInfoAttGroupDataModel_) getDtAttGroupMinh().getValue();
        if (l1 != null && l1.getRowCount() > 0) {
            //m_disableButton = false;
            m_disableButtonAdd = false;
            m_disableButtonSave = false;
        } else {
            //m_disableButton = false;
            m_disableButtonAdd = false;
            m_disableButtonSave = false;
        }
//        } else {
//            //m_disableButton = true;
//            m_disableButtonAdd = true;
//            m_disableButtonSave = true;
//        }
    }

    public void CheckObjRight() {
        m_objRightVal = (String) WorkingContext.getSessionValueAndRemove(WorkingContext.OBJ_RIGHT);
        if (m_objRightVal == null || m_objRightVal.isEmpty() || !m_objRightVal.equals(BaseConstant.getObjRightFull())) {
            //m_disableButton = true;
            m_disableButtonAdd = true;
            m_disableButtonSave = true;
        }
    }

    public DataTable getDtAttGroupMinh() {
        DataTable dt = (DataTable) WorkingContext.findJSFComponent("dtAttGroup", true);
        return dt;
    }

    public List<BaseDataInfo> getListAttGroupMinh() {
        if (m_dmAttGroup == null) {
            return null;
        }
        return (List<BaseDataInfo>) m_dmAttGroup.getWrappedData();
        /*
         * DataTable dt = getDtAttGroupMinh(); ListBaseDataInfoAttGroupDataModel_
         * lstDataModel= (ListBaseDataInfoAttGroupDataModel_)dt.getValue();
         * List<BaseDataInfo> lstDataInfo = (List<BaseDataInfo>)
         * lstDataModel.getWrappedData();
         *
         * return lstDataInfo;
         */
    }

    public String getObjTypeIDInfo() {
        String sObjTypeId = "";
        UIInput hf;
        hf = (UIInput) WorkingContext.findJSFComponent("hfObjTypeId", true);
        if (hf != null && hf.getValue() != null) {
            sObjTypeId = hf.getValue().toString();
        } else {
            sObjTypeId = WorkingContext.getRequestQueryString("ObjTypeID");
            if (hf != null) {
                hf.setValue(sObjTypeId);
            }
        }
        return sObjTypeId;
    }

    public String getObjIDInfo() {
        String sObjId = "";
        UIInput hf;
        hf = (UIInput) WorkingContext.findJSFComponent("hfObjId", true);
        if (hf != null && hf.getValue() != null) {
            sObjId = hf.getValue().toString();
        } else {
            sObjId = WorkingContext.getRequestQueryString("ObjID");
            if (hf != null) {
                hf.setValue(sObjId);
            }
        }

        return sObjId;
    }

//    public IAssetRemote getIAssetRemote() {
//        try {
//            if (m_IAssetRemote == null) {
//                m_IAssetRemote = (IAssetRemote) EjbContext.getLocalEJBRemote(IAssetRemote.class.getSimpleName());
//            } else {
//                EjbContext.LoginEJB();
//            }
//        } catch (Exception ex) {
//        }
//        return m_IAssetRemote;
//    }
    public ISystemCommonRemote getISystemCommonRemote() {
        try {
            if (m_ISystemCommonRemote == null) {
                m_ISystemCommonRemote = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception ex) {
        }
        return m_ISystemCommonRemote;
    }

    public boolean checkExistsGroupInDatatable(List<BaseDataInfo> lst, String attrgroupid) {
        for (BaseDataInfo baseDataInfo : lst) {
            if (baseDataInfo.getColStr9() != null) {
                if (baseDataInfo.getColStr9().equals(attrgroupid)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List getDsDataInfoForAttributeNew(String ObjID, String ObjTypeID) {
        List<BaseDataInfo> listBean = null;
        try {
            S_Organization tempOrg = (S_Organization) getISystemCommonRemote().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
            if (ObjID != null && ObjTypeID != null) {
                listBean = getISystemCommonRemote().getAttr_Asso_AssetNew(ObjID, ObjTypeID, tempOrg.getOrgid(), tempOrg.getOrgidParent());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, S_ReportResultBean.class.getName() + ".getDsDataInfoForAttributeNew()");
        }
        return listBean;
    }

    public void LoadAttGroupDefaultForCategory(String categoryid) {
        try {
            //Thêm nhóm thuộc tính mặc định
            DataTable dt = getDtAttGroupMinh();
            lstAttGroup = new ArrayList<BaseDataInfo>();
            if (dt.getValue() != null) {
                lstAttGroup = (List<BaseDataInfo>) getListAttGroupMinh();// dt.getValue();
            }
            List<BaseDataInfo> lstAdd = new ArrayList<BaseDataInfo>();
            //Lấy nhóm thuộc tính mặc đinh
            //List<S_Attribute_Group> lst = getIAssetRemote().getAttributeGroupDefaulAddForCategory(categoryid);
            List<S_Attribute_Group> lst = getISystemCommonRemote().getAttributeGroupDefaulAddForReport(categoryid);
            S_Organization tempOrg = (S_Organization) getISystemCommonRemote().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
            if (lst != null) {
                for (S_Attribute_Group group : lst) {
                    if (!checkExistsGroupInDatatable(lstAttGroup, group.getAttrgroupid())) {
                        if (getObjIDInfo().isEmpty()) {
                            lstAdd.addAll(getISystemCommonRemote().
                                    getAttr_GroupByAttrgroupid(group.getAttrgroupid(), tempOrg.getOrgid(), tempOrg.getOrgidParent()));
                        } else {
                            lstAdd.addAll(getISystemCommonRemote().
                                    getAttr_GroupByAssetIDandAttrgroupid(getObjIDInfo(), group.getAttrgroupid(), tempOrg.getOrgid(), tempOrg.getOrgidParent()));
                        }
                    }
                }
                lstAttGroup.addAll(lstAdd);
                CreateIDList(lstAdd);
                ListBaseDataInfoAttGroupDataModel_ listminh = new ListBaseDataInfoAttGroupDataModel_(lstAttGroup);
                m_dmAttGroup = listminh; //ThảoDD sửa lại, dùng biến ViewScope
                //dt.setValue(listminh);

            }
        } catch (Exception ex) {
            showErrorFromException(ex, S_ReportResultBean.class.getName() + ".LoadAttGroupDefaultForCategory()");
        }
    }

    public void LoadAttGroupDefaultForNewAssetByOrgID() {
        try {
            DataTable dt = getDtAttGroupMinh();
            S_Organization tempOrg = (S_Organization) getISystemCommonRemote().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
            if (dt != null) {
                lstAttGroup = new ArrayList<BaseDataInfo>();
                lstAttGroup = getISystemCommonRemote().
                        getAttributeGroupDefaulAddForAddNewAssetByOrgID(WorkingContext.getWorkingInfo(), tempOrg.getOrgid(), tempOrg.getOrgidParent());
                CreateIDList(lstAttGroup);
                ListBaseDataInfoAttGroupDataModel_ listminh = new ListBaseDataInfoAttGroupDataModel_(lstAttGroup);
                m_dmAttGroup = listminh; //ThảoDD sửa lại, dùng biến ViewScope
                //dt.setValue(listminh);
            }
        } catch (Exception ex) {

        }
    }

    public void LoadAttGroupForObject(String ObjID, String ObjTypeID) {
        DataTable dt = getDtAttGroupMinh();
        if (dt != null) {
            try {
                //Load các thuộc tính mở rộng theo đối tượng
                lstAttGroup = new ArrayList<BaseDataInfo>();
                lstAttGroup = getDsDataInfoForAttributeNew(ObjID, ObjTypeID);
                //Chinh lai gia tri mac dinh
                S_Organization tempOrganization = (S_Organization) getIAdmin().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
                SimpleDateFormat tempFormatDay = new SimpleDateFormat("dd");
                SimpleDateFormat tempFormatMonth = new SimpleDateFormat("M");
                SimpleDateFormat tempFormatYear = new SimpleDateFormat("yyyy");

                if (lstAttGroup != null) {
                    for (BaseDataInfo obj : lstAttGroup) {
                        if (obj.getColStr6() != null && obj.getColStr6().equals("STR")) {
                            if (obj.getColStr4() != null && obj.getColStr12() != null && (obj.getColStr12().equals("CBBOX") || obj.getColStr12().equals("CBLST"))) {
                                switch (obj.getColStr4().trim().toUpperCase()) {
                                    case "ORGID":
                                        obj.setColStr4(tempOrganization.getOrgid() + " - " + tempOrganization.getOrgdesc());
                                        break;
                                    case "ORGID_PARENT":
                                        obj.setColStr4(tempOrganization.getsOrgidParent().getOrgid() + " - " + tempOrganization.getsOrgidParent().getOrgdesc());
                                        break;
                                    case "DAY":
                                        obj.setColStr4(tempFormatDay.format(new Date()) + " - Ngày " + tempFormatDay.format(new Date()));
                                        break;
                                    case "QUARTER":
                                        Integer curMonth = Integer.parseInt(tempFormatMonth.format(new Date()));
                                        Integer curQuarter = 1;
                                        if (curMonth <= 3) {
                                            curQuarter = 1;
                                        } else if (curMonth > 3 && curMonth <= 6) {
                                            curQuarter = 2;
                                        } else if (curMonth > 6 && curMonth <= 9) {
                                            curQuarter = 3;
                                        } else if (curMonth > 9 && curMonth <= 12) {
                                            curQuarter = 4;
                                        }
                                        obj.setColStr4(curQuarter.toString() + " - Quý " + curQuarter.toString());
                                        break;
                                    case "MONTH":
                                        obj.setColStr4(tempFormatMonth.format(new Date()) + " - Tháng " + tempFormatMonth.format(new Date()));
                                        break;
                                    case "YEAR":
                                        obj.setColStr4(tempFormatYear.format(new Date()) + " - " + tempFormatYear.format(new Date()));
                                        break;
                                }
                            } else if (obj.getColStr3() != null) {
                                switch (obj.getColStr3().trim().toUpperCase()) {
                                    case "ORGID":
                                        obj.setColStr3(tempOrganization.getOrgid());
                                        break;
                                    case "ORGID_PARENT":
                                        obj.setColStr3(tempOrganization.getsOrgidParent().getOrgid());
                                        break;
                                    case "DAY":
                                        obj.setColStr3(tempFormatDay.format(new Date()));
                                        break;
                                    case "MONTH":
                                        obj.setColStr3(tempFormatMonth.format(new Date()));
                                        break;
                                    case "QUARTER":
                                        Integer curMonth = Integer.parseInt(tempFormatMonth.format(new Date()));
                                        Integer curQuarter = 1;
                                        if (curMonth <= 3) {
                                            curQuarter = 1;
                                        } else if (curMonth > 3 && curMonth <= 6) {
                                            curQuarter = 2;
                                        } else if (curMonth > 6 && curMonth <= 9) {
                                            curQuarter = 3;
                                        } else if (curMonth > 9 && curMonth <= 12) {
                                            curQuarter = 4;
                                        }
                                        obj.setColStr3(curQuarter.toString());
                                        break;
                                    case "YEAR":
                                        obj.setColStr3(tempFormatYear.format(new Date()));
                                        break;
                                }
                            }
                        }
                    }
                }
                CreateIDList(lstAttGroup);
                ListBaseDataInfoAttGroupDataModel_ listminh = new ListBaseDataInfoAttGroupDataModel_(lstAttGroup);
                m_dmAttGroup = listminh; //ThảoDD thêm vào để xử lý các thao tác thêm, sửa, xóa
//            CheckDtAttGroup();
//            CheckObjRight();
                //dt.setValue(listminh);
            } catch (Exception ex) {
                Logger.getLogger(S_ReportResultBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ClearAttGroup() {
        m_dmAttGroup = null;
//        DataTable dt = getDtAttGroupMinh();
//        if (dt != null) {
//            //Load các thuộc tính mở rộng theo đối tượng
//            dt.setValue(null);
//        }
    }

    protected void loadListFromQuery() {
        try {
            String query = null;
            if (sQueryList != null && iValue != null && iDesc != null) {
                if (sQueryList.toUpperCase().contains("TOP")) {
                    String[] arr = sQueryList.split(" ");
                    for (String str : arr) {
                        str.trim();
                    }
                    query = "";
                    for (String str : arr) {
                        if (!"".equals(str)) {
                            query += str + " ";
                        }
                    }
                    arr = query.split(" ");
                    query = "";
                    for (int i = 0; i < arr.length; i++) {
                        if (i != 1 && i != 2) {
                            query += arr[i] + " ";
                        }
                    }
                }
                if (query == null || "".equals(query)) {
                    query = sQueryList;
                }
                ListListQuery = getISystemCommonRemote().getListFromQuery(query, iValue, iDesc);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, S_ReportResultBean.class.getName() + ".getListFromQuery()");
        }
    }

    public ListBaseDataInfoDataModel getListFromQuery() {
//        if (ListListQuery == null){
//            try {
//                if (sQueryList != null && iValue != null && iDesc != null) {
//                    ListListQuery = getISystemCommonRemote().getListFromQuery(sQueryList, iValue, iDesc);
//                } else {
//                    return null;
//                }
//            }
//            catch (Exception ex)
//            {
//                showErrorFromException(ex, AttGroupBean.class.getName() + ".getListFromQuery()");
//            }
//        }
        if (ListListQuery == null) {
            return null;
        }

        return new ListBaseDataInfoDataModel(ListListQuery);
    }

    public void onChooseListQuery(ActionEvent event) {
        BaseDataInfo bdi = SelectionOneListQuery; //SelectionListQuery[0]
        if (bdi != null) {
            List<BaseDataInfo> List1 = (List<BaseDataInfo>) getListAttGroupMinh();// getDtAttGroupMinh().getValue();
            for (int i = 0; i < List1.size(); i++) {
                if (List1.get(i).getColStr13() != null) {
                    if (List1.get(i).getColStr7().equals(sAttrID)) {
                        List1.get(i).setColStr3(bdi.getColStr1());
                        List1.get(i).setColStr4(bdi.getColStr1() + " - " + bdi.getColStr2());
                    }
                }
                if (List1.get(i).getColStr13() != null) {
                    if (List1.get(i).getColStr13().contains("@" + sAttrID)) {
                        List1.get(i).setColStr3(null);
                        List1.get(i).setColStr4(null);
                    }
                }
                if (List1.get(i).getColStr14() != null) {
                    if (List1.get(i).getColStr14().contains("@" + sAttrID)) {
                        List1.get(i).setColStr3(null);
                        List1.get(i).setColStr4(null);
                    }
                }
            }
        }
    }

    public void onLoadListQuery(ActionEvent event) {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            if (externalContext.getRequestParameterMap().containsKey("AttrID")) {
                if (externalContext.getRequestParameterMap().get("AttrID") != null) {
                    sAttrID = (String) externalContext.getRequestParameterMap().get("AttrID");
                    S_Attribute tempAtt = (S_Attribute) getISystemCommonRemote().findById(sAttrID, S_Attribute.class);
                    sQueryList = tempAtt.getDataquerylst();
                    List<BaseDataInfo> List1 = (List<BaseDataInfo>) getListAttGroupMinh();// getDtAttGroupMinh().getValue();
                    sQueryList = getISystemCommonRemote().CorrectQueryFromListAttrData(sQueryList, List1, getObjIDInfo());
                    sQueryOne = tempAtt.getDataqueryone();
                    iValue = tempAtt.getDatafieldvalue();
                    iDesc = tempAtt.getDatafielddesc();
                }
            }
            ListListQuery = null;
            loadListFromQuery(); //ThảoDD thêm luôn đoạn này để điều khiển query lỗi
            if (ListListQuery == null) {
                return;
            }

            //Trả về client để điều khiển dialog
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "LoadListQuery");
            PrimeFaces.current().ajax().addCallbackParam("Method", "LoadListQuery");
        } catch (Exception e) {
            showErrorFromException(e, S_ReportResultBean.class.getName() + ".onLoadListQuery()");
        }
    }

    public void onLoadAttrHisVal(ActionEvent event) {
        WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, m_objRightVal);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (externalContext.getRequestParameterMap().containsKey("AttrID")) {
            if (externalContext.getRequestParameterMap().get("AttrID") != null) {
                sAttrID = (String) externalContext.getRequestParameterMap().get("AttrID");
                S_Attribute tempAtt = (S_Attribute) getISystemCommonRemote().findById(sAttrID, S_Attribute.class);
                sQueryList = tempAtt.getDataquerylst();
                List<BaseDataInfo> List1 = (List<BaseDataInfo>) getListAttGroupMinh();// getDtAttGroupMinh().getValue();
                sQueryList = getISystemCommonRemote().CorrectQueryFromListAttrData(sQueryList, List1, getObjIDInfo());
                sQueryOne = tempAtt.getDataqueryone();
                iValue = tempAtt.getDatafieldvalue();
                iDesc = tempAtt.getDatafielddesc();
                sAttTypeId = tempAtt.getAttTypeid();
            }
        }
        if (externalContext.getRequestParameterMap().containsKey("AttrDataID")) {
            if (externalContext.getRequestParameterMap().get("AttrDataID") != null) {
                sAttrDataID = (String) externalContext.getRequestParameterMap().get("AttrDataID");
            }
        }
        //Trả về client để điều khiển dialog
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "LoadAttrHisVal");
//        context.addCallbackParam("AttrDataID", sAttrDataID);
//        context.addCallbackParam("AttrID", sAttrID);

        PrimeFaces.current().ajax().addCallbackParam("Method", "LoadAttrHisVal");
        PrimeFaces.current().ajax().addCallbackParam("AttrDataID", sAttrDataID);
        PrimeFaces.current().ajax().addCallbackParam("AttrID", sAttrID);

        WorkingContext.setSessionValue("DataQueryList", sQueryList);
        WorkingContext.setSessionValue("DataQueryOne", sQueryOne);
        WorkingContext.setSessionValue("DataFieldValue", iValue);
        WorkingContext.setSessionValue("DataFieldDesc", iDesc);
        WorkingContext.setSessionValue("AttTypeId", sAttTypeId);
    }

    public static void CreateIDList(List<BaseDataInfo> data) {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setColInt8(i);
            }
        }
    }

    public boolean getCloseBtnRendered() {
        String s = "";
        UIInput hf;
        hf = (UIInput) WorkingContext.findJSFComponent("hfDlgAttGroup", true);
        if (hf != null && hf.getValue() != null) {
            s = hf.getValue().toString();
        }

        if (s.equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

    public boolean getRenderBtnUpDown() {
        if (m_dmAttGroup != null) {
            Integer CountAttrGrp = 0;
            for (Object object : m_dmAttGroup) {
                if (((BaseDataInfo) object).getColInt1() == 0) {
                    CountAttrGrp++;
                }
            }
            if (CountAttrGrp <= 1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public List<String> complete(String query) {
        //LoadAttGroupForObject(sObjIDInfo, sObjTypeInfo); 
        if (UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("AttrID") != null) {
            sAttrID = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("AttrID");

            S_Attribute tempAtt = (S_Attribute) getISystemCommonRemote().findById(sAttrID, S_Attribute.class);
            sQueryList = tempAtt.getDataquerylst();
            iValue = tempAtt.getDatafieldvalue();
            iDesc = tempAtt.getDatafielddesc();
        }
        //List<String> results = new ArrayList<String>();
//
//        for (int i = 0; i < 10; i++) {
//            results.add(query + i);
//        }
        ListListQuery = null;
        if (sQueryList != null && iValue != null && iDesc != null) {
            ListListQuery = getISystemCommonRemote().getListFromQuery(sQueryList, iValue, iDesc, query, (List<BaseDataInfo>) m_dmAttGroup.getWrappedData(), null);
        }
//        if (ListListQuery != null) {
//            for (BaseDataInfo b : ListListQuery) {
//                results.add(b.getColStr1() + " - " + b.getColStr2());
//            }
//        }
//        return getISystemCommonRemote().GetLstObjByQuery(sQueryList, query);

        return getMatchingObj(ListListQuery, query, 100);
    }

    List<String> getMatchingObj(List<BaseDataInfo> list, String regex, int maxResult) {
        ArrayList<String> matches = new ArrayList<String>();
        int iCount = 0;
        for (BaseDataInfo s : list) {
            if (s.getColStr2().toUpperCase().contains(regex.toUpperCase())) {
                matches.add(s.getColStr1() + " - " + s.getColStr2());
                iCount++;
                if (iCount >= maxResult) {
                    break;
                }
            }
        }
        return matches;
    }

    public void exportExcel(ActionEvent event) throws Exception {
        //Kiem tra du lieu dau vao   
        try {
            txtLog = "1.Bắt đầu tổng hợp dữ liệu\r\n";
//            FacesContext context = FacesContext.getCurrentInstance();
//            Application app = context.getApplication();
//            iattGroupReport = (S_ReportResultBean) app.evaluateExpressionGet(context, "#{attGroupReportBean}", S_ReportResultBean.class);
            
            pramTypeReport = new HashMap<>();
            pramReport = new HashMap<>();
            resultReport = new HashMap<>();
            //Bo xung them cac tham so chung
            S_Organization tempOrg = (S_Organization) getIReportConfigRemote().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
            pramReport.put("MaDVQL", tempOrg.getOrgid());
            pramReport.put("TenDVQL", tempOrg.getOrgdesc());
            String g_TenDViCapTren = "";
            if (tempOrg.getOrgidParent() != null) {
                S_Organization g_so = (S_Organization) getIReportConfigRemote().findById(tempOrg.getOrgidParent(), S_Organization.class);
                if (g_so != null) {
                    g_TenDViCapTren = g_so.getOrgdesc();
                }
            }

            pramReport.put("TenDVQLCapTren", g_TenDViCapTren);
            pramReport.put("UserName", WorkingContext.getUserName());
            pramReport.put("UserDesc", ((Q_User) getIReportConfigRemote().findById(WorkingContext.getUserName(), Q_User.class)).getUsername());
            pramReport.put("SysDate", new Date());
            txtLog += "2.Lấy giá trị tham số hệ thống: OK\r\n";
            txtLog += "3.Lấy giá trị tham số người dùng:\r\n";
            for (int i = 0; i < lstAttGroup.size(); i++) {
                try {

                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("STR")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        if (lstAttGroup.get(i).getColStr12().equals("CBLST")) {
                            try {
                                pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr4().split("-")[0].trim());
                                pramReport.put(lstAttGroup.get(i).getColStr5() + "_DESC", lstAttGroup.get(i).getColStr4().split("-")[1].trim());
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr4().split("-")[0].trim() + "\r\n";
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + "_DESC" + ": " + lstAttGroup.get(i).getColStr4().split("-")[1].trim() + "\r\n";
                            } catch (Exception e) {
                                pramReport.put(lstAttGroup.get(i).getColStr5(), null);
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": null\r\n";
                            }
                        } else if (lstAttGroup.get(i).getColStr12().equals("CBLSTS")) {
                            if (lstAttGroup.get(i).getColStr3s() != null) {
                                String strTemp = "";
                                for (String obj : lstAttGroup.get(i).getColStr3s()) {
                                    strTemp += obj + "#";
                                }
                                if (strTemp.length() > 0) {
                                    strTemp = strTemp.substring(0, strTemp.length() - 1);
                                }
                                pramReport.put(lstAttGroup.get(i).getColStr5(), strTemp);
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + strTemp + "\r\n";
                            }

                        } else if (lstAttGroup.get(i).getColStr12().equals("CBBOX")) {
                            pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr4());
                            txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr4() + "\r\n";
                        } else {
                            pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr3());
                            txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr3() + "\r\n";
                        }

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("DATE")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColDate1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColDate1() + "\r\n";

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("NUM")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColDou1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColDou1() + "\r\n";

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("BOOL")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColBol1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColBol1() + "\r\n";

                    }
                } catch (Exception e) {
                    txtLog += "-" + e.getMessage();
                }

            }

            //Lay data list
            String strSQLTemp;
            String sQuery;
            String sQueryStand = "";
            Boolean bFlag;
            String tempParam;
            int iIndex;
            txtLog += "4. Chuẩn hóa câu lệnh và tổng hợp dữ liệu" + "\r\n";
            HashMap<Integer, String> pramTemp = new HashMap<>();
            List<Object[]> tempDatalist;
            List<DynaBean> tempDatalistBean = new ArrayList<DynaBean>();
            for (int i = 0; i < lstReportDataList.size(); i++) {
                try {

                    bFlag = false;
                    tempParam = "";
                    sQueryStand = "";
                    iIndex = 1;
                    sQuery = lstReportDataList.get(i).getQuery();
                    sQuery = sQuery.replaceAll("(\r\n|\n|\t)", " ");
                    sQuery = sQuery.replaceAll("\\s+", " ");
                    for (int k = 0; k < sQuery.length(); k++) {
                        if (sQuery.substring(k, k + 1).equals("?")) {
                            bFlag = true;
                        }
                        if (bFlag && (sQuery.substring(k, k + 1).equals(";")
                                || sQuery.substring(k, k + 1).equals(" ")
                                || sQuery.substring(k, k + 1).equals(",")
                                || sQuery.substring(k, k + 1).equals(")")
                                || sQuery.substring(k, k + 1).equals("(")
                                || k == sQuery.length() - 1)) {
                            sQueryStand += "?" + iIndex;
                            if (k == sQuery.length() - 1) {
                                pramTemp.put(iIndex, tempParam);
                                sQueryStand += sQuery.substring(k, k + 1);
                                break;
                            } else {
                                pramTemp.put(iIndex, tempParam);
                            }
                            bFlag = false;
                            tempParam = "";
                            iIndex += 1;

                        }
                        if (bFlag && !sQuery.substring(k, k + 1).equals("?")) {
                            tempParam += sQuery.substring(k, k + 1);
                        }
                        if (!bFlag) {
                            sQueryStand += sQuery.substring(k, k + 1);
                        }
                    }
                } catch (Exception e) {
                    txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
                }
                try {

                    if (lstReportDataList.get(i).getCommandtypeid().equals("TEXT")) {
                        tempDatalistBean = new ArrayList<DynaBean>();
                        if (lstReportDataList.get(i).getLoadtype()) {
                            tempDatalist = getIReportConfigRemote().getListResult(sQueryStand, pramReport, pramTemp);
                            if (tempDatalist.size() > 0) {
                                DynaProperty proTy[] = new DynaProperty[tempDatalist.get(0).length];
                                for (int m = 0; m < tempDatalist.get(0).length; m++) {
                                    proTy[m] = new DynaProperty("s" + m, Object.class);
                                }
                                DynaClass dBeanClass = new BasicDynaClass("DBean", null, proTy);

                                DynaBean dBean;
                                Object tempValue;
                                for (int n = 0; n < tempDatalist.size(); n++) {
                                    dBean = dBeanClass.newInstance();

                                    for (int p = 0; p < tempDatalist.get(n).length; p++) {
                                        if (tempDatalist.get(n)[p] == null) {
                                            tempValue = "";
                                        } else {
                                            tempValue = tempDatalist.get(n)[p];
                                        }
                                        dBean.set("s" + p, tempValue);
                                    }
                                    tempDatalistBean.add(dBean);
                                }
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
                                tempDatalist = null;
                                //tempDatalistBean = null;
                            } else {
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
                            }
                            //Day du lieu vao DBEAN                            

                        } else {
                            getIReportConfigRemote().execResult(sQueryStand, pramReport, pramTemp);
                        }
                    }
                    if (lstReportDataList.get(i).getCommandtypeid().equals("PROC")) {
                        tempDatalistBean = new ArrayList<DynaBean>();
                        if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                            tempDatalist = getIReportConfigRemote().getListResultProc(sQueryStand, pramReport, pramTemp);
                        } else {
                            tempDatalist = getIReportConfigRemote().getListResultProc(sQueryStand, pramReport, pramTemp, pramTypeReport);
                        }

                        if (lstReportDataList.get(i).getLoadtype()) {
                            if (tempDatalist.size() > 0) {
                                DynaProperty properties[] = new DynaProperty[tempDatalist.get(0).length];
                                for (int m = 0; m < tempDatalist.get(0).length; m++) {
                                    properties[m] = new DynaProperty("s" + m, Object.class);
                                }
                                DynaClass dBeanClass = new BasicDynaClass("DBean", null, properties);
                                DynaBean dBean;
                                Object tempValue;
                                for (int n = 0; n < tempDatalist.size(); n++) {
                                    dBean = dBeanClass.newInstance();
                                    for (int p = 0; p < tempDatalist.get(n).length; p++) {
                                        if (tempDatalist.get(n)[p] == null) {
                                            tempValue = "";
                                        } else {
                                            tempValue = tempDatalist.get(n)[p];
                                        }
                                        dBean.set("s" + p, tempValue);
                                    }
                                    tempDatalistBean.add(dBean);
                                }
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
                                tempDatalist = null;
                                //tempDatalistBean = null;
                            } else {
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
                            }
                        } else {
                            getIReportConfigRemote().execResultProc(sQueryStand, pramReport, pramTemp);
                        }
                    }
                } catch (Exception e) {
                    txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
                    WorkingContext.showMessages(enumMessageMode.eError, "Tổng hợp báo cáo", e.getMessage());
                }
            }

            txtLog += "-:OK" + "\r\n";
            for (String key : pramReport.keySet()) {
                try {
                    resultReport.put(key, pramReport.get(key));
                } catch (Exception e) {
                }

            }
            //Truyen connecttion
            txtLog += "5. Tạo mẫu dữ liệu:" + "\r\n";
            //ReportManager cninfo = new ReportManagerImpl(getIReportConfigRemote().getConnection(), resultReport);
            //resultReport.put("cninfo", cninfo);
//            RequestContext rContext = RequestContext.getCurrentInstance();
//            rContext.addCallbackParam("Method", "Report");
//            rContext.addCallbackParam("status", true);

            PrimeFaces.current().ajax().addCallbackParam("Method", "Report");
            PrimeFaces.current().ajax().addCallbackParam("status", true);

            S_Report_Form tempFile = (S_Report_Form) getIReportConfigRemote().findById(reportFormSelect, S_Report_Form.class);
            XLSTransformer transformer = new XLSTransformer();
            File tempFileOutput = File.createTempFile("tempFile", ".temp1");
            File tempFileInput = File.createTempFile("tempFile", ".temp2");
            FileOutputStream fos = new FileOutputStream(tempFileInput);
            fos.write(tempFile.getFiledata());

            transformer.transformXLS(tempFileInput.getAbsolutePath(), resultReport, tempFileOutput.getAbsolutePath());
            
            //edit by thinhnd
            //doan nay o dau the nhi
            InputStream stream = new FileInputStream(tempFileOutput);
            SerializableSupplier<InputStream> temp_stream = new SerializableSupplier<InputStream>() {
                @Override
                public InputStream get() {
                    return stream;
                }
            };

            fileReport = DefaultStreamedContent.builder().name(tempFile.getRptformdesc() + ".xls").contentType("application/vnd.ms-excel").stream(temp_stream).build();
            
            fos.close();
            tempFileInput.delete();
            tempFileOutput.deleteOnExit();
            WorkingContext.showMessages(enumMessageMode.eInfo, "Tổng hợp báo cáo", "Tổng hợp báo cáo thành công");
            txtLog += "-:OK" + "\r\n";
        } catch (Exception e) {
            txtLog += "-" + e.getMessage() + "\r\n";
            WorkingContext.showMessages(enumMessageMode.eError, "Tổng hợp báo cáo", e.getMessage());
        }
    }

    public void exportHtml(ActionEvent event) throws Exception {
        //Kiem tra du lieu dau vao   
        try {
            txtLog = "1.Bắt đầu tổng hợp dữ liệu\r\n";
//            FacesContext context = FacesContext.getCurrentInstance();
//            Application app = context.getApplication();
//            iattGroupReport = (S_ReportResultBean) app.evaluateExpressionGet(context, "#{attGroupReportBean}", S_ReportResultBean.class);
            pramTypeReport = new HashMap<>();
            pramReport = new HashMap<>();
            resultReport = new HashMap<>();
            //Bo xung them cac tham so chung
            S_Organization tempOrg = (S_Organization) getIReportConfigRemote().findById(WorkingContext.getOrganizationCurrent(), S_Organization.class);
            pramReport.put("MaDVQL", tempOrg.getOrgid());
            pramReport.put("TenDVQL", tempOrg.getOrgdesc());

            String g_TenDViCapTren = "";
            if (tempOrg.getOrgidParent() != null) {
                S_Organization g_so = (S_Organization) getIReportConfigRemote().findById(tempOrg.getOrgidParent(), S_Organization.class);
                if (g_so != null) {
                    g_TenDViCapTren = g_so.getOrgdesc();
                }
            }

            pramReport.put("TenDVQLCapTren", g_TenDViCapTren);
            pramReport.put("UserName", WorkingContext.getUserName());
            pramReport.put("UserDesc", ((Q_User) getIReportConfigRemote().findById(WorkingContext.getUserName(), Q_User.class)).getUsername());
            pramReport.put("SysDate", new Date());
            txtLog += "2.Lấy giá trị tham số hệ thống: OK\r\n";
            txtLog += "3.Lấy giá trị tham số người dùng:\r\n";
            for (int i = 0; i < lstAttGroup.size(); i++) {
                try {

                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("STR")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        if (lstAttGroup.get(i).getColStr12().equals("CBLST")) {
                            try {
                                pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr4().split("-")[0].trim());
                                pramReport.put(lstAttGroup.get(i).getColStr5() + "_DESC", lstAttGroup.get(i).getColStr4().split("-")[1].trim());
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr4().split("-")[0].trim() + "\r\n";
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + "_DESC" + ": " + lstAttGroup.get(i).getColStr4().split("-")[1].trim() + "\r\n";
                            } catch (Exception e) {
                                pramReport.put(lstAttGroup.get(i).getColStr5(), null);
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": null\r\n";
                            }
                        } else if (lstAttGroup.get(i).getColStr12().equals("CBLSTS")) {
                            if (lstAttGroup.get(i).getColStr3s() != null) {
                                String strTemp = "";
                                for (String obj : lstAttGroup.get(i).getColStr3s()) {
                                    strTemp += obj + "#";
                                }
                                if (strTemp.length() > 0) {
                                    strTemp = strTemp.substring(0, strTemp.length() - 1);
                                }
                                pramReport.put(lstAttGroup.get(i).getColStr5(), strTemp);
                                txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + strTemp + "\r\n";
                            }

                        } else if (lstAttGroup.get(i).getColStr12().equals("CBBOX")) {
                            pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr4());
                            txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr4() + "\r\n";
                        } else {
                            pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr3());
                            txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColStr3() + "\r\n";
                        }

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("DATE")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColDate1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColDate1() + "\r\n";

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("NUM")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColDou1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColDou1() + "\r\n";

                    }
                    if (lstAttGroup.get(i).getColStr6() != null && lstAttGroup.get(i).getColStr6().equals("BOOL")) {
                        pramTypeReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColStr6());
                        pramReport.put(lstAttGroup.get(i).getColStr5(), lstAttGroup.get(i).getColBol1());
                        txtLog += "-" + lstAttGroup.get(i).getColStr5() + ": " + lstAttGroup.get(i).getColBol1() + "\r\n";

                    }
                } catch (Exception e) {
                    txtLog += "-" + e.getMessage();
                }

            }

            //Lay data list
            String strSQLTemp;
            String sQuery;
            String sQueryStand = "";
            Boolean bFlag;
            String tempParam;
            int iIndex;
            txtLog += "4. Chuẩn hóa câu lệnh và tổng hợp dữ liệu" + "\r\n";
            HashMap<Integer, String> pramTemp = new HashMap<>();
            List<Object[]> tempDatalist;
            List<DynaBean> tempDatalistBean = new ArrayList<DynaBean>();
            for (int i = 0; i < lstReportDataList.size(); i++) {
                try {

                    bFlag = false;
                    tempParam = "";
                    sQueryStand = "";
                    iIndex = 1;
                    sQuery = lstReportDataList.get(i).getQuery();
                    sQuery = sQuery.replaceAll("(\r\n|\n|\t)", " ");
                    sQuery = sQuery.replaceAll("\\s+", " ");
                    for (int k = 0; k < sQuery.length(); k++) {
                        if (sQuery.substring(k, k + 1).equals("?")) {
                            bFlag = true;
                        }
                        if (bFlag && (sQuery.substring(k, k + 1).equals(";")
                                || sQuery.substring(k, k + 1).equals(" ")
                                || sQuery.substring(k, k + 1).equals(",")
                                || sQuery.substring(k, k + 1).equals(")")
                                || sQuery.substring(k, k + 1).equals("(")
                                || k == sQuery.length() - 1)) {
                            sQueryStand += "?" + iIndex;
                            if (k == sQuery.length() - 1) {
                                pramTemp.put(iIndex, tempParam);
                                sQueryStand += sQuery.substring(k, k + 1);
                                break;
                            } else {
                                pramTemp.put(iIndex, tempParam);
                            }
                            bFlag = false;
                            tempParam = "";
                            iIndex += 1;

                        }
                        if (bFlag && !sQuery.substring(k, k + 1).equals("?")) {
                            tempParam += sQuery.substring(k, k + 1);
                        }
                        if (!bFlag) {
                            sQueryStand += sQuery.substring(k, k + 1);
                        }
                    }
                } catch (Exception e) {
                    txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
                }
                try {

                    if (lstReportDataList.get(i).getCommandtypeid().equals("TEXT")) {
                        tempDatalistBean = new ArrayList<DynaBean>();
                        if (lstReportDataList.get(i).getLoadtype()) {
                            tempDatalist = getIReportConfigRemote().getListResult(sQueryStand, pramReport, pramTemp);

                            if (tempDatalist.size() > 0) {
                                DynaProperty proTy[] = new DynaProperty[tempDatalist.get(0).length];
                                for (int m = 0; m < tempDatalist.get(0).length; m++) {
                                    proTy[m] = new DynaProperty("s" + m, Object.class);
                                }
                                DynaClass dBeanClass = new BasicDynaClass("DBean", null, proTy);

                                DynaBean dBean;
                                Object tempValue;
                                for (int n = 0; n < tempDatalist.size(); n++) {
                                    dBean = dBeanClass.newInstance();

                                    for (int p = 0; p < tempDatalist.get(n).length; p++) {
                                        if (tempDatalist.get(n)[p] == null) {
                                            tempValue = "";
                                        } else {
                                            tempValue = tempDatalist.get(n)[p];
                                        }
                                        dBean.set("s" + p, tempValue);
                                    }
                                    tempDatalistBean.add(dBean);
                                }
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
                                tempDatalist = null;
                                //tempDatalistBean = null;
                            } else {
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
                            }
                            //Day du lieu vao DBEAN                            

                        } else {
                            getIReportConfigRemote().execResult(sQueryStand, pramReport, pramTemp);
                        }
                    }
                    if (lstReportDataList.get(i).getCommandtypeid().equals("PROC")) {
                        tempDatalistBean = new ArrayList<DynaBean>();

                        if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                            tempDatalist = getIReportConfigRemote().getListResultProc(sQueryStand, pramReport, pramTemp);
                        } else {
                            tempDatalist = getIReportConfigRemote().getListResultProc(sQueryStand, pramReport, pramTemp, pramTypeReport);
                        }

                        if (lstReportDataList.get(i).getLoadtype()) {
                            if (tempDatalist.size() > 0) {
                                DynaProperty properties[] = new DynaProperty[tempDatalist.get(0).length];
                                for (int m = 0; m < tempDatalist.get(0).length; m++) {
                                    properties[m] = new DynaProperty("s" + m, Object.class);
                                }
                                DynaClass dBeanClass = new BasicDynaClass("DBean", null, properties);
                                DynaBean dBean;
                                Object tempValue;
                                for (int n = 0; n < tempDatalist.size(); n++) {
                                    dBean = dBeanClass.newInstance();
                                    for (int p = 0; p < tempDatalist.get(n).length; p++) {
                                        if (tempDatalist.get(n)[p] == null) {
                                            tempValue = "";
                                        } else {
                                            tempValue = tempDatalist.get(n)[p];
                                        }
                                        dBean.set("s" + p, tempValue);
                                    }
                                    tempDatalistBean.add(dBean);
                                }
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
                                tempDatalist = null;
                                //tempDatalistBean = null;
                            } else {
                                resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
                            }
                        } else {
                            getIReportConfigRemote().execResultProc(sQueryStand, pramReport, pramTemp);
                        }
                    }
                } catch (Exception e) {
                    txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
                    //WorkingContext.showMessages(enumMessageMode.eError, "Tổng hợp báo cáo", e.getMessage());
                }
            }

            txtLog += "-:OK" + "\r\n";
            for (String key : pramReport.keySet()) {
                try {
                    resultReport.put(key, pramReport.get(key));
                } catch (Exception e) {
                }

            }
            //Truyen connecttion
            txtLog += "5. Tạo mẫu dữ liệu:" + "\r\n";
            //ReportManager cninfo = new ReportManagerImpl(getIReportConfigRemote().getConnection(), resultReport);
            //resultReport.put("cninfo", cninfo);
//            RequestContext rContext = RequestContext.getCurrentInstance();
//            rContext.addCallbackParam("Method", "Report");
//            rContext.addCallbackParam("status", true);

            PrimeFaces.current().ajax().addCallbackParam("Method", "Report");
            PrimeFaces.current().ajax().addCallbackParam("status", true);

            S_Report_Form tempFile = (S_Report_Form) getIReportConfigRemote().findById(reportFormSelect, S_Report_Form.class);
            XLSTransformer transformer = new XLSTransformer();
            File tempFileOutput = File.createTempFile("tempFile", ".temp1");
            File tempFileInput = File.createTempFile("tempFile", ".temp2");
            FileOutputStream fos = new FileOutputStream(tempFileInput);
            fos.write(tempFile.getFiledata());
            transformer.transformXLS(tempFileInput.getAbsolutePath(), resultReport, tempFileOutput.getAbsolutePath());
            //fileReport = new DefaultStreamedContent(new FileInputStream(tempFileOutput), "application/vnd.ms-excel", tempFile.getRptformdesc() + ".xls");
            ConvertExcelToHtml iConvertExcelToHtml = new ConvertExcelToHtml();
            reportHtmlView = iConvertExcelToHtml.ConvertExcelToHtml(new FileInputStream(tempFileOutput), false).toString();
            WorkingContext.setSessionValue("reportHtmlView01", reportHtmlView);

            tempFileInput.delete();
            //tempFileOutput.deleteOnExit();
            //WorkingContext.showMessages(enumMessageMode.eInfo, "Tổng hợp báo cáo", "Tổng hợp báo cáo thành công");
            txtLog += "-:OK" + "\r\n";
            bShowReport = true;
        } catch (Exception e) {
            txtLog += "-" + e.getMessage() + "\r\n";
            WorkingContext.showMessages(enumMessageMode.eError, "Tổng hợp báo cáo", e.getMessage());
        }
    }

//    public void setCombinedModel(CartesianChartModel combinedModel) {
//        this.combinedModel = combinedModel;
//    }
//
//    public CartesianChartModel getCombinedModel() {
//        return combinedModel;
//    }
    public String getTxtLog() {
        return txtLog;
    }

    public void setTxtLog(String txtLog) {
        this.txtLog = txtLog;
    }

    public StreamedContent getFileReport() {
        return fileReport;
    }

    public void setFileReport(StreamedContent fileReport) {
        this.fileReport = fileReport;
    }

    public S_Report getReport() {
        return report;
    }

    public void setReport(S_Report report) {
        this.report = report;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public List<S_Report_Form> getLstReportForm() {
        return lstReportForm;
    }

    public void setLstReportForm(List<S_Report_Form> lstReportForm) {
        this.lstReportForm = lstReportForm;
    }

    public String getReportFormSelect() {
        return reportFormSelect;
    }

    public void setReportFormSelect(String reportFormSelect) {
        this.reportFormSelect = reportFormSelect;
    }

    public String getReportHtmlView() {
        return WorkingContext.getSessionValueAndRemove("reportHtmlView01").toString();
    }

    public void setReportHtmlView(String reportHtmlView) {
        this.reportHtmlView = reportHtmlView;
    }

    private IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public ListBaseDataInfoAttGroupDataModel_ getM_dmAttGroup() {
        return m_dmAttGroup;
    }

    public void setM_dmAttGroup(ListBaseDataInfoAttGroupDataModel_ m_dmAttGroup) {
        this.m_dmAttGroup = m_dmAttGroup;
    }
}

class ListBaseDataInfoAttGroupDataModel extends ListDataModel<BaseDataInfo> implements SelectableDataModel<BaseDataInfo> {

    public ListBaseDataInfoAttGroupDataModel() {
    }

    public ListBaseDataInfoAttGroupDataModel(List<BaseDataInfo> data) {
        super(data);
    }

    //Edit by thinhnd
    @Override
    public String getRowKey(BaseDataInfo t) {
        return t.getColInt8().toString();
    }

    @Override
    public BaseDataInfo getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<BaseDataInfo> list = (List<BaseDataInfo>) getWrappedData();

        for (BaseDataInfo lst : list) {
            if (lst.getColInt8().toString().equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
