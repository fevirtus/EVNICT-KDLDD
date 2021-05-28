package main.web.shared.dialog.bean;


import main.ContextResources.EjbContext;
import main.remote.shared.common.IQueryShareRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumMessageMode;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import main.entity.shared.common.InputTextLongData;

/**
 *
 * @author khiemvk
 */
@ManagedBean
@ViewScoped
public class InputTextLongDialog extends BasePageCommon implements Serializable {
    //Các cột giá trị khóa phân tách bởi dấu chấm ;

    private InputTextLongData textLongData;
    private IQueryShareRemote m_IQueryRemote;
    protected ResourcesFactory m_rfCommon;
    private String m_objRightVal;
    //private String orgid = "";
    //private String siteid = "";

//    public String getOrgid() {
//        return orgid;
//    }
//
//    public void setOrgid(String orgid) {
//        this.orgid = orgid;
//    }
//
//    public String getSiteid() {
//        return siteid;
//    }
//
//    public void setSiteid(String siteid) {
//        this.siteid = siteid;
//    }

    @PostConstruct
    public void init() {
        try {
            m_objRightVal=(String) WorkingContext.getSessionValueAndRemove(WorkingContext.OBJ_RIGHT);
            //Reset
            if (textLongData != null) {
                textLongData.error = null;
            }
            if (!isPostback()) {
                remove();
                textLongData = new InputTextLongData();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//                if (externalContext.getRequestParameterMap().containsKey("orgid")) {
//                    orgid = (String) externalContext.getRequestParameterMap().get("orgid");
//                }
//                if (externalContext.getRequestParameterMap().containsKey("siteid")) {
//                    siteid = (String) externalContext.getRequestParameterMap().get("siteid");
//                }
                if (externalContext.getRequestParameterMap().containsKey("tableName")) {
                    textLongData.tableName = (String) externalContext.getRequestParameterMap().get("tableName");
                }
                if (externalContext.getRequestParameterMap().containsKey("keyColums")) {
                    String temp = (String) externalContext.getRequestParameterMap().get("keyColums");
                    if (temp != null) {
                        if (!temp.isEmpty()) {
                            String[] temps = temp.split("\\;");
                            textLongData.keyColums = Arrays.asList(temps);
                        }
                    }
                }
                if (externalContext.getRequestParameterMap().containsKey("keyValues")) {
                    String temp = (String) externalContext.getRequestParameterMap().get("keyValues");
                    if (temp != null) {
                        if (!temp.isEmpty()) {
                            String[] temps = temp.split("\\;");
                            textLongData.keyValues = Arrays.asList(temps);
                        }
                    }
                }
                if (externalContext.getRequestParameterMap().containsKey("colText")) {
                    textLongData.colText = (String) externalContext.getRequestParameterMap().get("colText");
                }
                if (externalContext.getRequestParameterMap().containsKey("colUserIDUpdate")) {
                    textLongData.colUserIDUpdate = (String) externalContext.getRequestParameterMap().get("colUserIDUpdate");
                    if (textLongData.colUserIDUpdate != null) {
                        if (textLongData.colUserIDUpdate.isEmpty()) {
                            textLongData.colUserIDUpdate = null;
                        }
                    }
                }
                if (externalContext.getRequestParameterMap().containsKey("colDateUpdate")) {
                    textLongData.colDateUpdate = (String) externalContext.getRequestParameterMap().get("colDateUpdate");
                    if (textLongData.colDateUpdate != null) {
                        if (textLongData.colDateUpdate.isEmpty()) {
                            textLongData.colDateUpdate = null;
                        }
                    }
                }
                //Load dữ liệu
                textLongData.userid = WorkingContext.getUserName();
                if (getIQueryRemote() != null) {
                    textLongData = getIQueryRemote().loadTextLong(textLongData);
                }
                if (textLongData.error != null) {
                    showError(textLongData.error, "InputTextLongDialog.init()");
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, "InputTextLongDialog.init()");
        }
    }

    public boolean isPostback() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getRenderKit().getResponseStateManager().isPostback(facesContext);
    }

    public void onSaveTextInputLong(ActionEvent event) {
        try {
            if (getIQueryRemote() != null && textLongData != null) {
                boolean b = getIQueryRemote().updateTextLong(textLongData);
                if (b) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null,
                            getM_rfCommon().getMessage("msgSaveSuccess"));
                    setUpdatedForm();
                } else {
                    WorkingContext.showMessages(enumMessageMode.eError, null,
                            getM_rfCommon().getMessage("msgSaveError"));
                    showError(getIQueryRemote().getLastActionInfo(), InputTextLongDialog.class.getName() + ".onSaveTextInputLong()");
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, InputTextLongDialog.class.getName() + ".onSaveTextInputLong()");
        }
    }

    private IQueryShareRemote getIQueryRemote() {
         try {
            if (m_IQueryRemote == null) {
                try {
                    m_IQueryRemote = (IQueryShareRemote) EjbContext.getLocalEJBRemote(IQueryShareRemote.class.getSimpleName());
                } catch (Exception ex) {
                    Logger.getLogger(InputTextLongDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                EjbContext.LoginEJB();
            }
            return m_IQueryRemote;
        } catch (Exception ex) {
            Logger.getLogger(InputTextLongDialog.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResourcesFactory getM_rfCommon() {
        if (m_rfCommon == null) {
            m_rfCommon = new ResourcesFactory("main/web/common/prop/commonMessages");
        }
        return m_rfCommon;
    }

    public String getLongText() {
        return textLongData.longText;
    }

    public void setLongText(String longText) {
        this.textLongData.longText = longText;
    }

    public void setUpdatedForm() {
        WorkingContext.setSessionValue("InputTextLongDialog_Update", true);
    }

    public static void remove() {
        WorkingContext.setSessionValue("InputTextLongDialog_Update", null);
    }

    public static boolean isUpdate() {
        try {
            String str = (String) WorkingContext.getSessionValue("InputTextLongDialog_Update");
            if (str != null) {
                remove();
                return Boolean.valueOf(str);
            }
        } catch (Exception ex) {
        }
        return false;
    }

//    public boolean getIsDisabledButtonSave() {
//        boolean a = false, b = false;
//        if (orgid != null && !orgid.equals("")) {
//            a = checkRightEditOrg(orgid);
//        }
//        if (siteid != null && !siteid.equals("")) {
//            b = checkRightEditSite(siteid);
//        }
//        if((orgid != null && !orgid.equals(""))&&(siteid == null || siteid.equals("")))
//            return a;
//        else if((siteid != null && !siteid.equals(""))&&(orgid == null || orgid.equals("")))
//            return  b;
//        else if((siteid != null && !siteid.equals(""))&&(orgid != null || !orgid.equals("")))
//            return  a&&b;
//        else
//            return true;
//    }
    
    public boolean  getIsDisabledButtonSave(){
        if (m_objRightVal==null || m_objRightVal.isEmpty() || !m_objRightVal.equals(BaseConstant.getObjRightFull()))
           return false;
        return  true;
    }
    
    //cau hinh show editor
//    public Config getConfigCk(){
//        Config config=new Config();
//        ToolbarButtonGroup toolbarDocument=new ToolbarButtonGroup("document")
//            .item(ToolbarItem.SOURCE);
//        ToolbarButtonGroup toolbarInsert = new ToolbarButtonGroup("insert")
//            .item(ToolbarItem.TABLE)
//            .item(ToolbarItem.PREVIEW)
//            .item(ToolbarItem.HORIZONTAL_RULE)
//            .item(ToolbarItem.SPECIAL_CHAR)
//            .item(ToolbarItem.PAGE_BREAK)
//            .item(ToolbarItem.PASTE_FROM_WORD)
//            .item(ToolbarItem.PASTE_TEXT)
//            .item(ToolbarItem.UNDO)
//            .item(ToolbarItem.REDO);
//        ToolbarButtonGroup toolbarEditing = new ToolbarButtonGroup("editing")
//            .item(ToolbarItem.FIND)
//            .item(ToolbarItem.REPLACE);
//        Toolbar toolbar=new Toolbar(toolbarDocument, toolbarInsert, toolbarEditing, ToolbarButtonGroup.STYLES,  
//                ToolbarButtonGroup.BASIC_STYLES, ToolbarButtonGroup.PARAGRAPH, ToolbarButtonGroup.COLORS);
//        config.toolbar(toolbar);
//        return config;
//    }
}
