package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.report.S_Report_Commandtype;
import main.entity.shared.report.S_Report_Datalist;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author khiemvk
 */
@ManagedBean
@ViewScoped
public class ReportDataListBean extends BasePageBean implements Serializable {
    private IReportConfigRemote m_IReportConfigRemote;
    private S_Report_Datalist m_S_Report_Datalist;
    private ResourcesFactory m_RfAssetProp;
    //Check nhập
    String m_Style_TxtReportID;
    String m_Style_TxtReportGroup;
    String m_Style_TxtUomInput;
    String m_Style_TxtColumn;
    String m_MsgCheck;
    String g_rptid;
    String g_rptformid;
    private ResourcesFactory m_Rf;
//    @ManagedProperty(name = "assetReportBean", value = "#{assetReportBean}")
//    private AssetReportBean assetReportBean;
    List<String> m_LstReportID = null;

    private String m_sDeleteMsg;
    List<S_Report_Commandtype> lstS_Report_Commandtype;
   S_Report_Commandtype m_Commandtype;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public ReportDataListBean() {
        if (m_RfAssetProp == null) {
            m_RfAssetProp = new ResourcesFactory("main/web/eam/asset/prop/AssetProp");
        }
        if (!isPostback()) {
            String reportid = WorkingContext.getRequestQueryString("rptid");
            String datalistid = WorkingContext.getRequestQueryString("datalistid");
            lstS_Report_Commandtype = getIReportConfigRemote().getListCommandType();
            
            g_rptid = reportid;
            g_rptformid = datalistid;
            if (datalistid != null) {
                LoadDetail(datalistid);
                g_rptid = reportid;
            } 
            else {
                AddNewDetail();
            }            
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    
    private void loadReportDetail() {
        try {
            
            if (getIReportConfigRemote() == null) {
                return;
            }
            String reportid = WorkingContext.getRequestQueryString("rptid");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void CleanScreen() {
        m_S_Report_Datalist = new S_Report_Datalist();
        m_S_Report_Datalist.setEnable(true);
        //m_LstReportID = null;
        resetInputRequire();
    }

    /**
     * Load
     * @param optabid
     */
    public void LoadDetail(String reportid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Report_Datalist = (S_Report_Datalist) getIReportConfigRemote().findById(reportid, S_Report_Datalist.class);
            if (m_S_Report_Datalist != null) {
                //Load nhom
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, ReportDataListBean.class.getName() + "LoadDetail()");
        }
    }

    
    public void AddNewDetail() {
        CleanScreen();
        m_S_Report_Datalist.setRptid(g_rptid);
        m_S_Report_Datalist.setLoadtype(true);
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Report_Datalist == null) {
                return false;
            }
            boolean bCheck = true;
            S_Report_Datalist tg = m_S_Report_Datalist;
            //Check nhap
            if (!checkID_Standard(tg.getDatalistid())){
                bCheck = false;
                m_Style_TxtReportID = "error";
            }
            if (tg.getDatalistdesc()== null || tg.getDatalistdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtReportID = "error";
            }        
                      
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }
            /*
             * Valid du lieu nhap
             */

            //Kiểm tra ID
            if (getFormMode()== enumFormMode.eAddNew){
                if (tg.getDatalistid()!= null && !tg.getDatalistdesc().trim().isEmpty()) {
                    S_Report_Datalist m_S_Report_Datalist1 = (S_Report_Datalist)getIReportConfigRemote().findById(m_S_Report_Datalist.getDatalistid(), S_Report_Datalist.class);
                    if(m_S_Report_Datalist1!=null){
                        m_MsgCheck = m_RfAssetProp.getMessage("msgReportExists");
                        return false;
                    }
                }
            }
           
          
            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {
                m_S_Report_Datalist.setUserCrId(WorkingContext.getUserName());
                m_S_Report_Datalist.setUserCrDtime(new Date());

                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Report_Datalist.getDatalistid(), S_Report_Datalist.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, ReportDataListBean.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Report_Datalist.setDatalistid(kc.getOutputValue());
            } else {
                m_S_Report_Datalist.setUserMdfId(WorkingContext.getUserName());
                m_S_Report_Datalist.setUserMdfDtime(new Date());
            }
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, ReportDataListBean.class.getName() + ".CheckInput()");
            return false;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    /**
     * Cập nhật mảng khi thêm mới, xóa
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListDetails(int type,String id){
        //getLstReportID();
        m_LstReportID = (List<String>) WorkingContext.getSessionValue("LIST_METER");
        if(m_LstReportID==null)
            return;
        switch(type){
            case 0://Them moi
                m_LstReportID.add(id);
                updateNavigator(m_LstReportID.size() - 1, m_LstReportID);
                break;
            case 1://Delete
                if(m_LstReportID.contains(id)){
                    int idx = m_LstReportID.indexOf(id);
                    m_LstReportID.remove(id);
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < m_LstReportID.size()-1)
                        idxNew = idx +1;
                    else if(idx >= m_LstReportID.size()-1)
                        idxNew = m_LstReportID.size()-1;
                    else if(idx>0)
                        idxNew = idx -1;
                    if (idxNew >= 0 && idxNew < m_LstReportID.size()){
                        LoadDetail(m_LstReportID.get(idxNew));
                        updateNavigator(idxNew, m_LstReportID);
                    } else{
                        setFormMode(enumFormMode.eInit);
                    }
                }
                break;
            default:
                break;
        }
        WorkingContext.setSessionValue("LIST_METER",m_LstReportID);
    }
    /**
     * Lấy mảng phần tử để duyệt
     * @return
     */
//    public List<String> getLstReportID(){
//        if(m_LstReportID!=null)
//            return m_LstReportID;
//        //Lay lai list
//        if(assetReportBean!=null){
//            List<S_Report> lstTabs = assetReportBean.getM_LstReports();
//            if(lstTabs!=null){
//                m_LstReportID = new ArrayList<String>();
//                for(S_Report tab:lstTabs)
//                    m_LstReportID.add(tab.getRptid());
//            }
//        }
//        return m_LstReportID;
//    }

    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            //getLstReportID();
            m_LstReportID = (List<String>) WorkingContext.getSessionValue("LIST_METER");
            setDisableNavigator(true);
            if (m_LstReportID != null && m_S_Report_Datalist != null) {
                if (!m_LstReportID.isEmpty() && m_S_Report_Datalist.getRptid() != null) {
                    //Tìm vị trí hiện tại
                    String id = m_S_Report_Datalist.getRptid();
                    int idx = m_LstReportID.indexOf(id);
                    if (idx >= 0 && idx < m_LstReportID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadDetail(m_LstReportID.get(0));
                            updateNavigator(0, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadDetail(m_LstReportID.get(idx));
                            }
                            updateNavigator(idx, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < m_LstReportID.size()) {
                                LoadDetail(m_LstReportID.get(idx));
                            }
                            updateNavigator(idx, m_LstReportID);
                        } else {//last
                            LoadDetail(m_LstReportID.get(m_LstReportID.size() - 1));
                            updateNavigator(m_LstReportID.size() - 1, m_LstReportID);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void resetInputRequire() {
        m_Style_TxtReportID = "";
        m_Style_TxtReportGroup = "";
        m_Style_TxtUomInput = "";
        m_Style_TxtColumn = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAddNewDetail(ActionEvent event) {
        try {
            AddNewDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDataListBean.class.getName() + ".onAddNewDetail()");
        }
    }

    public void onSaveDetail(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if(!m_MsgCheck.isEmpty())
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_MsgCheck);
                return;
            }
           

            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIReportConfigRemote() != null) {
                    if (m_IReportConfigRemote.insert(m_S_Report_Datalist)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListDetails(0,m_S_Report_Datalist.getDatalistid());
                        g_rptformid = m_S_Report_Datalist.getDatalistid();
                    }

                }
            }else{
                if (getIReportConfigRemote() != null) {
                    if (m_IReportConfigRemote.update(m_S_Report_Datalist)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        g_rptformid = m_S_Report_Datalist.getDatalistid();
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDataListBean.class.getName() + ".onSaveDetail()");
        }
    }

    public void onDeleteDetail(ActionEvent event) {
        try {
            if (getIReportConfigRemote()!=null) {
                String id = m_S_Report_Datalist.getDatalistid();
                boolean bDel = getIReportConfigRemote().delete(id,S_Report_Datalist.class);
                if (bDel) {
                    WorkingContext.showMessages(enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListDetails(1,id);
                } else {
                    showError(m_rfCommon.getMessage("msgDeleteError") + BaseConstant.CRLF()+ getIReportConfigRemote().getLastActionInfo(),
                            ReportDataListBean.class.getName() + ".onDeleteDetail()");
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDataListBean.class.getName() + ".onDeleteDetail()");
        }
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public S_Report_Commandtype getM_Commandtype() {
        return m_Commandtype;
    }

    public void setM_Commandtype(S_Report_Commandtype m_Commandtype) {
        this.m_Commandtype = m_Commandtype;
    }
    
    
    public List<S_Report_Commandtype> getLstS_Report_Commandtype() {
        return lstS_Report_Commandtype;
    }

    public void setLstS_Report_Commandtype(List<S_Report_Commandtype> lstS_Report_Commandtype) {
        this.lstS_Report_Commandtype = lstS_Report_Commandtype;
    }
    
    
   
    
    
    public IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class.getSimpleName());
            } else{
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public S_Report_Datalist getM_S_Report_Datalist() {
        return m_S_Report_Datalist;
    }

    public void setM_S_Report_Datalist(S_Report_Datalist m_S_Report_Datalist) {
        this.m_S_Report_Datalist = m_S_Report_Datalist;
    }

   

    

    
    public Boolean getTxtIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

        public Boolean getDisableBtnAddNewReport() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return true;
        }
        return false;
    }

    public String getStyle_TxtReportID() {
        return m_Style_TxtReportID;
    }

    public String getStyle_TxtReportGroup() {
        return m_Style_TxtReportGroup;
    }

    public String getStyle_TxtUomInput() {
        return m_Style_TxtUomInput;
    }

    public String getStyle_TxtColumn() {
        return m_Style_TxtColumn;
    }

//    public AssetReportBean getAssetReportBean() {
//        return assetReportBean;
//    }
//
//    public void setAssetReportBean(AssetReportBean assetReportBean) {
//        this.assetReportBean = assetReportBean;
//        if (getFormMode() != enumFormMode.eAddNew)
//            setPageDetailInfo(enumResultSetPosition.eInit);
//    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }
    //}}</editor-fold>

    public boolean getDisabledSaveData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
       
        return false;
    }

    public boolean getDisabledDeleteData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return true;
        }        
        return false;
    }
    
    
}


