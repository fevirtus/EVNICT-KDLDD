/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;


import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_Role;
import main.entity.shared.admin.Q_User;
import main.entity.shared.admin.Q_User_Ext;
import main.entity.shared.system.Bb_Message;
import main.entity.shared.system.Bb_Message_Ext;
import main.entity.shared.system.Bb_Message_Rcv;
import main.entity.shared.system.Bb_Message_Rcv_Ext;
import main.entity.shared.system.S_Dept;
import main.remote.shared.admin.IAdmin;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.shared.dialog.bean.DeptDialog;
import main.web.shared.dialog.bean.RoleDialog;
import main.web.shared.dialog.bean.UserDialog;
import evnit.util.common.BaseConstant;
import evnit.util.common.S_Key_ControlInfo;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import main.remote.shared.attach.IShareAttach;
import main.remote.shared.system.ISM_BB_BLRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CloseEvent;

/**
 * Bean quản lý các thông báo do người dùng gửi vào hệ thống cho các các người dùng khác
 * @author thaodd
 */
@ManagedBean
@ViewScoped
public class PltBBMng extends BasePageCommon implements Serializable {
    private String m_sDlgHeader; //Lưu tên thao tác để truyền vào Dialog
    private ResourcesFactory m_rf;
    private Bb_Message_Ext[] m_lstBBChoose;
    private boolean m_bCheckAll, m_bCheckOne;
    private String m_sOptValid;
    private String m_UserID;

    //Các hằng số: khóa đưa vào list option lựa chọn thời hạn thông báo
    private static final String mcValid = "BBValid", mcInvalid="BBInvalid", mcFuture="BBFuture", mcAll="BBAll";
    private List<SelectItem> m_lstUser; //mỗi phần tử là một SelectItem đưa vào combo
    private List<Bb_Message_Ext> m_lstBB;
    private List<SelectItem> m_lstRcvType;
    private int m_currentRowIndex; //Vi trí hiện tại của bản ghi đang hiển thị
    private Bb_Message m_currentBBNew; //Bản ghi đang làm việc

    //Các biến cho dialog
    private String m_DlgVisible_Set="true";
    private Date m_DlgEffDay_dEFrom, m_DlgEffDay_dETo;
    private Boolean m_DlgEffDay_bEFrom=true, m_DlgEffDay_bETo=true;

    //Các biến cho form chi tiết
    private String m_currentRcvTypeID;
    private String m_sDeleteMsg;
    private boolean m_bShowLongDetail;
    private List<Bb_Message_Rcv_Ext> m_lstRcvObj_User, m_lstRcvObj_Dept, m_lstRcvObj_Role;
    private List<Bb_Message_Rcv> m_lstRcvObj_Add, m_lstRcvObj_Remove;
    private Bb_Message_Rcv_Ext[] m_arrRcvObjChooose; //Mảng các đối tượng chọn trong danh sách
    //public static final String mcBBLstID = "BBLstID"; //Key đặt list ID của BB vào session truyền từ form cha vào form chi tiết để thiết lập di chuyển
    //Các biến phục vụ cho di chuyển trong form chi tiết
    private List<String> m_nvgLstID;
    private int m_nvgCurrentIndex;

    private enum enumAction
    {
        eNone,
        eBBAdd,
        eBBEdit,
        eBBDelete
    }

    private enumAction m_eActionCurrent=enumAction.eNone; //Biến lưu thao tác hiện tại

    //{{<editor-fold defaultstate="collapsed" desc="Các get/set">
    public Boolean getM_DlgEffDay_bEFrom() {
        return m_DlgEffDay_bEFrom;
    }

    public void setM_DlgEffDay_bEFrom(Boolean m_DlgEffDay_bEFrom) {
        this.m_DlgEffDay_bEFrom = m_DlgEffDay_bEFrom;
    }

    public Boolean getM_DlgEffDay_bETo() {
        return m_DlgEffDay_bETo;
    }

    public void setM_DlgEffDay_bETo(Boolean m_DlgEffDay_bETo) {
        this.m_DlgEffDay_bETo = m_DlgEffDay_bETo;
    }

   public Date getM_DlgEffDay_dEFrom() {
        return m_DlgEffDay_dEFrom;
    }

    public void setM_DlgEffDay_dEFrom(Date m_DlgEffDay_dEFrom) {
        this.m_DlgEffDay_dEFrom = m_DlgEffDay_dEFrom;
    }

    public Date getM_DlgEffDay_dETo() {
        return m_DlgEffDay_dETo;
    }

    public void setM_DlgEffDay_dETo(Date m_DlgEffDay_dETo) {
        this.m_DlgEffDay_dETo = m_DlgEffDay_dETo;
    }

    public String getM_DlgVisible_Set() {
        return m_DlgVisible_Set;
    }

    public void setM_DlgVisible_Set(String m_DlgVisible_bSet) {
        this.m_DlgVisible_Set = m_DlgVisible_bSet;
    }

    public Bb_Message_Rcv_Ext[] getM_arrRcvObjChooose() {
        return m_arrRcvObjChooose;
    }

    public void setM_arrRcvObjChooose(Bb_Message_Rcv_Ext[] m_arrRcvObjChooose) {
        this.m_arrRcvObjChooose = m_arrRcvObjChooose;

    }

    public boolean getM_bShowLongDetail() {
        return m_bShowLongDetail;
    }

    public void setM_bShowLongDetail(boolean m_bShowLongDetail) {
        this.m_bShowLongDetail = m_bShowLongDetail;
    }
    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    public String getM_currentRcvTypeID() {
        return m_currentRcvTypeID;
    }

    public void setM_currentRcvTypeID(String m_currentRcvTypeID) {
        this.m_currentRcvTypeID = m_currentRcvTypeID;
    }
    
    public Bb_Message getM_currentBBNew() {
        if (m_currentBBNew==null)
            m_currentBBNew=new Bb_Message(); //Tránh bị lỗi ban đầu null

        return m_currentBBNew;
    }

    public void setM_currentBBNew(Bb_Message m_currentBBNew) {
        this.m_currentBBNew = m_currentBBNew;
    }
    public String getM_UserID() {
        return m_UserID;
    }

    public void setM_UserID(String m_UserID) {
        this.m_UserID = m_UserID;
    }

    public String getM_sOptValid() {
        return m_sOptValid;
    }

    public void setM_sOptValid(String m_sOptValid) {
        this.m_sOptValid = m_sOptValid;
    }

    public boolean isM_bCheckAll() {
        return m_bCheckAll;
    }

    public void setM_bCheckAll(boolean m_bCheckAll) {
        this.m_bCheckAll = m_bCheckAll;
    }

    public boolean isM_bCheckOne() {
        return m_bCheckOne;
    }

    public void setM_bCheckOne(boolean m_bCheckOne) {
        this.m_bCheckOne = m_bCheckOne;
    }

    public String getDlgHeader()
    {
        return m_sDlgHeader;
    }

    public Bb_Message_Ext[] getM_lstBBChoose() {
        return m_lstBBChoose;
    }

    public void setM_lstBBChoose(Bb_Message_Ext[] lst) {
        this.m_lstBBChoose = lst;
    }
    //}}</editor-fold>

    /** Creates a new instance of PltBBMng */
    public PltBBMng() {
       m_rf=new ResourcesFactory("main/web/shared/system/prop/SM_BB_Prop");
       m_sOptValid=mcValid;
       m_UserID=WorkingContext.getUserName();

       String s;
       s=WorkingContext.getRequestQueryString("bbid");
       if (s!=null && !s.isEmpty())
       {
            //Lấy bản ghi dữ liệu
            m_currentBBNew = (Bb_Message) getISM_BB_BLRemote().findById(s, Bb_Message.class);
            if (m_currentBBNew == null)
            {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgRecordNotExist"));
                return;
            }
            else
            {
                //Lấy danh sách id phục vụ di chuyển
                m_nvgLstID=(List<String>)WorkingContext.getSessionValueAndRemove(WorkingContext.KEY_LSTID_FORNAV);
                if (m_nvgLstID != null)
                {
                    m_nvgCurrentIndex=Integer.parseInt(m_nvgLstID.get(0));
                    m_nvgLstID.remove(0);
                }
            }
            s=WorkingContext.getRequestQueryString("method");
            if (s.equals("BBEdit"))
                m_eActionCurrent=enumAction.eBBEdit;
        }
       else
       {
           s=WorkingContext.getRequestQueryString("method");
           if (s!=null && s.equals("BBAddNew"))
               m_eActionCurrent=enumAction.eBBAdd;
       }
     
       if (m_currentBBNew == null)
        m_currentBBNew=new Bb_Message(); //Tránh bị lỗi ban đầu null
    }

//{{<editor-fold defaultstate="collapsed" desc="Các hàm cho trang quản lý">
    protected final ISystemCommonRemote getISystemCommonRemote()
    {
       try
       {
           ISystemCommonRemote ism = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
           return ism;
       }
       catch (Exception ex)
       {
           showErrorFromException(ex, PltShortcutMng.class.getSimpleName());
       }
       return null;
    }

    protected final IShareAttach getIShareAttach()
    {
       try
       {
           IShareAttach ism = (IShareAttach) EjbContext.getLocalEJBRemote(IShareAttach.class.getSimpleName());
           return ism;
       }
       catch (Exception ex)
       {
           
       }
       return null;
    }
    protected final ISM_BB_BLRemote getISM_BB_BLRemote()
    {
       try
       {
           ISM_BB_BLRemote ism = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
           return ism;
       }
       catch (Exception ex)
       {
           showErrorFromException(ex, PltShortcutMng.class.getName() + ".getISM_BB_BLRemote()");
       }
       return null;
    }

    public void onBBAddNew(ActionEvent ev)
    {
        m_sDlgHeader=m_rf.getMessage("BBAddNew");
        m_eActionCurrent=enumAction.eBBAdd;
        m_currentBBNew=new Bb_Message();

        //Trả về client để điều khiển dialog
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "BBAddNew");
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "BBAddNew");

    }

    public void onBBDeleteStart(ActionEvent ev)
    {
        if (!checkChooseBB())
            return;

        m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
        m_sDeleteMsg=m_sDeleteMsg.replaceAll("@count",String.valueOf(m_lstBBChoose.length));
        if (m_lstBBChoose.length==1)
            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc"," ('" + m_lstBBChoose[0].getBbmsum() + "')");
        else
            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc","");

        m_eActionCurrent=enumAction.eBBDelete;
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "DelBB");
        PrimeFaces.current().ajax().addCallbackParam("Method", "DelBB");
    }
    public void onBBDeleteEnd(ActionEvent ev)
    {
       try
        {
            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                Bb_Message_Ext oInfo=null;
                if (m_lstBBChoose !=null)
                {
                    for(int i=0;i<m_lstBBChoose.length;i++)
                    {
                        oInfo=m_lstBBChoose[i];
                        if (!ism.delete(oInfo.getBbmid(),Bb_Message.class))
                        {
                            showError(ism.getLastActionInfo(), PltBBMng.class.getName() + ".onDeleteEnd()");
                            return;
                        }
                        //WebTools.deleteFileOtherAttach("BBMSG", oInfo.getBbmid());
                        
                        PrimeFaces.current().ajax().addCallbackParam("BBMSG", oInfo.getBbmid());
                    }
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    refreshGrdBB();
                }
            }
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".onBBDeleteEnd()");
        }
    }

    protected void refreshGrdBB()
    {
        m_lstBB=null;
        m_lstBBChoose=null;
        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
    }

    public void onCloseDlgFunc(CloseEvent evt)
    {
        if (m_eActionCurrent==enumAction.eBBAdd || m_eActionCurrent==enumAction.eBBEdit)
            refreshGrdBB();
    }

    public List<Bb_Message_Ext> getDsBB()
    {
        if (m_lstBB != null)
            return m_lstBB;
        try
        {
            ISM_BB_BLRemote ibb=getISM_BB_BLRemote();
            if (ibb==null)
                return null;
            m_lstBB=ibb.getLstBB(m_UserID, m_sOptValid);
            return m_lstBB;
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".getDsBB()");
        }
        return null;
   }

    public void onCheckAll(AjaxBehaviorEvent event) {
//        if(m_lstShortcut!=null){
//            for (Sm_Shortcut s_Data : m_lstShortcut) {
//                s_Data.setbChecked(m_bCheckAllShortcut);
//            }
//        }

    }

    public void onCheckOne(AjaxBehaviorEvent event) {
//        try {
//            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
//            //dtData = (DataTable)selectBoolean.getParent().getParent();
//            //Lấy vị trí
//            String temps[] = selectBoolean.getClientId().split("\\:");
//            if (temps.length > 2) {
//                int rowIndex = Integer.valueOf(temps[2]);
//                //dtData.setRowIndex(rowIndex);
//                //System.out.println("rowIndex=" + rowIndex);
//                //if (m_bIsLazy)
//                //    rowIndex=rowIndex % getPageSize(); //Khi load lazy tung trang thi bien lstData chi chua du lieu cua mot trang con rowIndex la chi so tong
//
//                Sm_Shortcut s_Data = m_lstShortcut.get(rowIndex); // (A_Asset) dtData.getRowData();
//                s_Data.setbChecked(!s_Data.getbChecked());
//
//                //System.out.println("Set check " + s_Data.getbChecked() + " for " + s_Data.getAssetid());
//                //Gán giá trị chọn vào biến session
//                //WorkingContext.setDialogObjSelect(s_Data.getAssetid(), s_Data.getbChecked());
//            }
//        } catch (Exception ex) {
//            //ex.printStackTrace();
//            showErrorFromException(ex, PltShortcutMng.class.getName() + ".onCheckShortcut()");
//        }
    }

    /**
     * Khởi tạo danh sách các option hiệu lực thông báo
     * @return
     */
    public List<SelectItem> getDsValidOpt()
    {
        List<SelectItem> lst=new ArrayList<SelectItem>();
        SelectItem si;
        si=new SelectItem(mcValid,m_rf.getMessage(mcValid));
        lst.add(si);
        si=new SelectItem(mcInvalid,m_rf.getMessage(mcInvalid));
        lst.add(si);
        si=new SelectItem(mcFuture,m_rf.getMessage(mcFuture));
        lst.add(si);
        si=new SelectItem(mcAll,m_rf.getMessage(mcAll));
        lst.add(si);

        return lst;
    }

   /**
     * Hàm lấy ra danh sách các user. Nếu user hiện tại có quyền admin thì lấy tất cả, không thì chỉ có user hiện tại
     * @return List of SelectItem đưa vào combo
     */
    public List getDsUserWithCheckAdmin()
    {
        if (m_lstUser==null)
            m_lstUser=initDsUserWithCheckAdmin();
        return m_lstUser;
    }
    protected List<SelectItem> initDsUserWithCheckAdmin()
    {
       try
       {
           String s;
           boolean b;
           IAdmin ad= (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());// SharedCaller.getIAdmin();
           s=WorkingContext.getUserName();
           if (s==null || s.isEmpty())
               return null;
           b=ad.checkUserIsAdmin(WorkingContext.getUserName());
           List<Q_User> lst=null;
           List<SelectItem> lstResult;
           Q_User q;
           if (b) //is admin
           {
                lst=ad.getAllUserWithOrder("userid");
                if (lst==null)
                {
                    s=ad.getLastActionInfo();
                    showError(s, PltBBMng.class.getName()+ ".initDsUserWithCheckAdmin()" );
                    return null;
                }
                q=new Q_User();
                q.setUserid(BaseConstant.getAllKey());
                q.setUsername(m_rfCommon.getMessage("lbAll"));
                lst.add(0, q); //Thêm bản ghi đầu tiên: tất cả người dùng
           }
           else //khong phai admin
           {
                 q=ad.findUserByUserID(s);
                 lst=new ArrayList<Q_User>();
                 if (q != null)
                    lst.add(q);
           }
           lstResult=new ArrayList();
           for(int i=0; i < lst.size();i++)
           {
                q=(Q_User) lst.get(i);
                s=q.getUsername();
                if (!q.getUserid().equals(BaseConstant.getAllKey()))
                    s= q.getUserid() + " - " + s;
                lstResult.add(new SelectItem(q.getUserid(), s));
           }
           return lstResult;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, PltShortcutMng.class.getName() + ".initDsUserWithCheckAdmin()");
       }
       return null;
    }

    protected DataTable getGrdBB()
    {
        return (DataTable) WorkingContext.findJSFComponent("formDlg:grdBB", false);
    }
    public void onChooseClick(ActionEvent ev)
    {
        m_lstBB=null; //Clear biến này để nạp lại grid
        m_lstBB=getDsBB();
        DataTable grd=getGrdBB();
        if (grd != null) //reset chi so trang
            grd.reset();
        m_lstBBChoose=null;
    }

    public String getRecordNum()
    {
        String s;
        s=m_rfCommon.getMessage("msgRecordNum") + ": ";
        if (m_lstBB==null)
            s += "0";
        else
            s += m_lstBB.size();

        return s;
    }

    public void onBBEdit(ActionEvent ev)
    {
        try
        {
            //Định vị bản ghi
            String temps[] = ev.getComponent().getClientId().split("\\:");
            if (temps.length <= 2) {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgGenError"));
                return;
            }

            m_currentRowIndex = Integer.valueOf(temps[2]);
            //Lấy bản ghi dữ liệu
//            m_currentBBNew = (Bb_Message) getISM_BB_BLRemote().findById(m_lstBB.get(m_currentRowIndex).getBbmid(), Bb_Message.class);
//            if (m_currentBBNew == null)
//            {
//                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgRecordNotExist"));
//                return;
//            }

            m_sDlgHeader=m_rf.getMessage("BBEdit");
            m_eActionCurrent=enumAction.eBBEdit;

            //Trả về client để điều khiển dialog
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "BBEdit");
//            context.addCallbackParam("bbid", m_lstBB.get(m_currentRowIndex).getBbmid());
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "BBEdit");
            PrimeFaces.current().ajax().addCallbackParam("bbid", m_lstBB.get(m_currentRowIndex).getBbmid());
            
            //Lưu mảng ID vào danh sách để cung cấp cho chức năng duyệt:
            //Phần tử đầu tiên là chỉ số đang chọn trong danh sách, các phần tử sau là các id của thông báo
            List<String> lstID=new ArrayList<String>();
            lstID.add(String.valueOf(m_currentRowIndex));
            for(Bb_Message_Ext b : m_lstBB)
            {
                lstID.add(b.getBbmid());
            }
            WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV,lstID);
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".onBBEdit()");
        }
    }

    /**
     * Điều khiển edit trên toolbar, lấy phần tử đầu tiên trong danh sách chọn làm phần tử khởi đầu
     * @param ev
     */
    public void onBBEditToolbar(ActionEvent ev)
    {
        try
        {
            if (!checkChooseBB())
                return;

            m_sDlgHeader=m_rf.getMessage("BBEdit");
            m_eActionCurrent=enumAction.eBBEdit;

            //Trả về client để điều khiển dialog
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "BBEdit");
//            context.addCallbackParam("bbid", m_lstBBChoose[0].getBbmid());
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "BBEdit");
            PrimeFaces.current().ajax().addCallbackParam("bbid", m_lstBBChoose[0].getBbmid());
            
            //Lưu mảng ID vào danh sách để cung cấp cho chức năng duyệt:
            //Phần tử đầu tiên là chỉ số đang chọn trong danh sách, các phần tử sau là các id của thông báo
            List<String> lstID=new ArrayList<String>();
            //lstID.add(String.valueOf(0));
            for(int i=0;i < m_lstBB.size();i++)
            {
                Bb_Message_Ext b=m_lstBB.get(i);
                if (b.getBbmid().equals(m_lstBBChoose[0].getBbmid())) //Gán chỉ số đang chọn
                    lstID.add(0,String.valueOf(i));
                lstID.add(b.getBbmid());
            }
            WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV,lstID);
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".onBBEdit()");
        }
    }

    protected boolean checkChooseBB()
    {
        if (m_lstBBChoose==null || m_lstBBChoose.length==0)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgNotCheckRecord"));
            return false;
        }
        return true;
    }
    /**
     * Action đặt hiện/ẩn thông báo
     * @param ev
     */
    public void onShowDlgVisibleBB(ActionEvent ev)
    {
        if (!checkChooseBB())
            return;
        m_sDlgHeader=m_rf.getMessage("BBDlgVisibleHeader");

//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "ShowDlgVisibleBB");
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "ShowDlgVisibleBB");
    }

    public void onAcceptDlgVisibleBB(ActionEvent ev)
    {
       if (!checkChooseBB())
            return;
       try
        {
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
            {
                for(int i=0;i < m_lstBBChoose.length;i++)
                {
                    if (!iBL.setVisibleBB(m_lstBBChoose[i].getBbmid(), Boolean.valueOf(m_DlgVisible_Set)))
                    {
                        showError(iBL.getLastActionInfo(), PltBBMng.class.getName() + ".onAcceptDlgVisibleBB()");
                        return;
                    }
                }
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                refreshGrdBB();
            }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".onAcceptDlgVisibleBB()");
        }
    }

    /**
     * Action đặt ngạy hiệu lực thông báo
     * @param ev
     */
    public void onShowDlgEffDay(ActionEvent ev)
    {
        if (!checkChooseBB())
            return;
        m_sDlgHeader=m_rf.getMessage("BBDlgEffDayHeader");
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "ShowDlgEffDay");
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "ShowDlgEffDay");
    }

    public void onAcceptDlgEffDay(ActionEvent ev)
    {
       if (!checkChooseBB())
            return;
        if (m_DlgEffDay_bEFrom && m_DlgEffDay_dEFrom != null && m_DlgEffDay_bETo && m_DlgEffDay_dETo != null)
            if (m_DlgEffDay_dEFrom.compareTo(m_DlgEffDay_dETo) > 0)
            {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn,null, m_rf.getMessage("BBDTDayValid_Check"));
                return;
            }
       if ((!m_DlgEffDay_bEFrom && !m_DlgEffDay_bETo) || (m_DlgEffDay_bEFrom && m_DlgEffDay_dEFrom==null) || (m_DlgEffDay_bETo && m_DlgEffDay_dETo==null))
       {
           WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn,null, m_rf.getMessage("BBDTDayValid_Required"));
           return;
        }
       try
        {
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
            {
                for(int i=0;i < m_lstBBChoose.length;i++)
                {
                    if (!iBL.setEffDayBB(m_lstBBChoose[i].getBbmid(),m_DlgEffDay_bEFrom, m_DlgEffDay_dEFrom,m_DlgEffDay_bETo,m_DlgEffDay_dETo))
                    {
                        showError(iBL.getLastActionInfo(), PltBBMng.class.getName() + ".onAcceptDlgEffDay()");
                        return;
                    }
                }
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                refreshGrdBB();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "CloseDlgEffDay");
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "CloseDlgEffDay");
            }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".onAcceptDlgVisibleBB()");
        }

    }

    public String getMsgBBCount()
    {
        if (m_lstBBChoose==null || m_lstBBChoose.length==0)
            return "";

        String s=m_rf.getMessage("BBBBCount");
        s=s.replaceAll("@count",String.valueOf(m_lstBBChoose.length));
        return s;
    }

    public void onShowDlgReadLog(ActionEvent ev)
    {
       if (!checkChooseBB())
            return;
        //Lưu mảng ID vào danh sách để cung cấp cho chức năng duyệt:
        //Phần tử đầu tiên là chỉ số đang chọn trong danh sách, các phần tử sau là các id của thông báo
        List<String> lstID=new ArrayList<String>();
        lstID.add(String.valueOf(0));
        for(int i=0; i< m_lstBBChoose.length;i++)
        {
            lstID.add(m_lstBBChoose[i].getBbmid());
        }
        WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV,lstID);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("bbid", m_lstBBChoose[0].getBbmid());
//        context.addCallbackParam("Method", "ShowDlgReadLog");
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "ShowDlgReadLog");
        PrimeFaces.current().ajax().addCallbackParam("bbid", m_lstBBChoose[0].getBbmid());
        
        m_sDlgHeader=m_rf.getMessage("BBDlgReadLogHeader");
        
    }
//}}</editor-fold> Các hàm cho form quản lý

//{{<editor-fold defaultstate="collapsed" desc="Các hàm cho trang chi tiết thông báo">
    /**
     * Hàm dùng trong Dialog
     */
    public void onDtBBAddNew(ActionEvent ev)
    {
        m_eActionCurrent=enumAction.eBBAdd;
        m_currentBBNew=new Bb_Message();
        m_nvgCurrentIndex=-1; //Disable vùng chuyển
        //reset lại các danh sách
        resetDT();
    }

    protected void resetDT()
    {
        m_lstRcvType=null;
        m_lstRcvObj_Dept=null;
        m_lstRcvObj_Role=null;
        m_lstRcvObj_User=null;
        lstRcvObj_reset();
        m_arrRcvObjChooose=null;
        m_currentRcvTypeID=null;
        m_currentRowIndex=-1;
        //Reset cho cả trang log
        m_BbLogLstNoReadUser=null;
        m_BbLogLstReadUser=null;
    }


    public void onDtBBSave(ActionEvent ev)
    {
        try
        {
            boolean b=false;
            switch (m_eActionCurrent)
            {
                case eBBAdd:
                    b=saveBBAdd();
                    break;
                case eBBEdit:
                    b=saveBBEdit();
                    break;
            }
            m_lstRcvType=null; //Refresh
            if (b) //Refresh lai cha
                WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".onBBEdit()");
        }
    }
    protected boolean checkInput_BB()
    {
        if (m_currentBBNew.getBbmsum()== null || m_currentBBNew.getBbmsum().isEmpty())
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn,null, m_rf.getMessage("BBSum_Required"));
            return false;
        }
        if (m_currentBBNew.getEdfrom() != null && m_currentBBNew.getEdto() != null)
            if (m_currentBBNew.getEdfrom().compareTo(m_currentBBNew.getEdto()) > 0)
            {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn,null, m_rf.getMessage("BBDTDayValid_Check"));
                return false;
            }
        return true;
    }

    protected boolean saveObjRcvList()
    {
       try
        {
           if (m_eActionCurrent==enumAction.eBBAdd && m_lstRcvObj_Add != null)
           {
               //update lai truong bmid cho bản ghi thêm mới
               for(Bb_Message_Rcv b:m_lstRcvObj_Add)
                   b.getId().setBbmid(m_currentBBNew.getBbmid());
           }
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (!iBL.saveObjRcvList(m_lstRcvObj_Add,m_lstRcvObj_Remove))
                {
                    showError(iBL.getLastActionInfo(),PltBBMng.class.getName() + ".saveBBAdd()");
                }
                else //Ghi thanh cong
                {
                    lstRcvObj_reset();
                    return true;
                }

        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".saveBBAdd()");
        }
        return false;
    }
    protected boolean saveBBAdd()
    {
        try
        {
            if (!checkInput_BB())
                return false;
            //Sinh key
            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            S_Key_ControlInfo kc=new S_Key_ControlInfo(null,Bb_Message.class.getSimpleName());
            kc=iscr.getGenKeyID(kc);
            if (kc==null || kc.getGenStatus()!=0) //Không thành công
            {
                showErrorFromGenKey(kc, PltBBMng.class.getName() + ".saveBBAdd()");
                return false;
            }

            m_currentBBNew.setBbmid(kc.getOutputValue());
            m_currentBBNew.setUserCrId(WorkingContext.getUserName());
            m_currentBBNew.setUserCrDtime(new Date());
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (iBL.insert(m_currentBBNew))
                {
                    if (saveObjRcvList())
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                    //Chuyen method lai
                    m_eActionCurrent=enumAction.eBBEdit;
                    //Cập nhật navigator
                    if (m_nvgLstID==null) //Khởi tạo
                        m_nvgLstID=new ArrayList<String>();
                    m_nvgLstID.add(m_currentBBNew.getBbmid()); //Thêm vào cuối danh sách
                    m_nvgCurrentIndex=m_nvgLstID.size()-1; //Bản ghi hiện tại là bản ghi cuối cùng của list
                }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".saveBBAdd()");
            return false;
        }
        return true;
    }

    protected boolean saveBBEdit()
    {
        try
        {
            if (!checkInput_BB())
                return false;
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            m_currentBBNew.setUserMdfId(WorkingContext.getUserName());
            m_currentBBNew.setUserMdfDtime(new Date());

            if (iBL != null)
                if (iBL.update(m_currentBBNew))
                {
                    if (saveObjRcvList())
                       WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                    //Update lai nhat ky doc
                    ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                    iscr.fLogObj_Update(m_currentBBNew.getBbmid(), "BBMSG");
                }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".saveBBEdit()");
            return false;
        }
        return true;
    }

    public void onDtBBDelete(ActionEvent ev)
    {
       try
        {
            if (m_currentBBNew==null || m_currentBBNew.getBbmid()==null)
            {
               WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgNotChooseRecord"));
               return;
            }
            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                if (!ism.delete(m_currentBBNew.getBbmid(),Bb_Message.class))
                {
                    showError(ism.getLastActionInfo(), PltBBMng.class.getName() + ".onDtBBDelete()");
                    return;
                }
                //Xóa đính kèm
                //WebTools.deleteFileOtherAttach("BBMSG", m_currentBBNew.getBbmid());
                PrimeFaces.current().ajax().addCallbackParam("BBMSG", m_currentBBNew.getBbmid());

                WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
            }
            //Cập nhật chỉ số navigator sau khi xóa
            if (m_nvgLstID != null && !m_nvgLstID.isEmpty() && m_nvgCurrentIndex >=0)
            {
                m_nvgLstID.remove(m_nvgCurrentIndex);
                if (m_nvgCurrentIndex==m_nvgLstID.size());
                    m_nvgCurrentIndex--;
            }
            if (m_nvgLstID == null || m_nvgLstID.isEmpty())
            {
                m_nvgCurrentIndex=-1;
                onDtBBAddNew(null);
            }
            else if (m_nvgCurrentIndex >= 0)
                showBBDetailForIndex(m_nvgCurrentIndex);
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".onDtBBDelete()");
        }

    }

    public String getUserCRInfo()
    {
        String sVal, s;
        if (m_currentBBNew == null)
            return "";

        sVal=m_rfCommon.getMessage("lblUserCreate");
        s=m_currentBBNew.getUserCrId() != null?m_currentBBNew.getUserCrId():"";
        sVal += ": " + s + ", " + m_rfCommon.getMessage("lblDate") + ": ";
        s="";
        DateFormat dateFormat = new SimpleDateFormat(m_rfCommon.getDateTimeMask());
        if (m_currentBBNew.getUserCrDtime() != null)
            s=dateFormat.format(m_currentBBNew.getUserCrDtime());
        sVal +=s;
        return sVal;
    }

    public String getUserMDFInfo()
    {
        String sVal, s;
        if (m_currentBBNew == null)
            return "";

        sVal=m_rfCommon.getMessage("lblUserUpdate");
        s=m_currentBBNew.getUserMdfId() != null?m_currentBBNew.getUserMdfId():"";
        sVal += ": " + s + ", " + m_rfCommon.getMessage("lblDate") + ": ";
        s="";
        DateFormat dateFormat = new SimpleDateFormat(m_rfCommon.getDateTimeMask());
        if (m_currentBBNew.getUserMdfDtime() != null)
            s=dateFormat.format(m_currentBBNew.getUserMdfDtime());
        sVal +=s;
        return sVal;
    }

    public List<SelectItem> getDsRcvType()
    {
        try
        {
            if (m_lstRcvType != null)
                return m_lstRcvType;

            ISM_BB_BLRemote ibb=getISM_BB_BLRemote();
            if (ibb==null)
                return null;
            List lst=ibb.getLstReceiverTypeBB(m_currentBBNew.getBbmid());
            String s;
            if (lst==null)
            {
                s=ibb.getLastActionInfo();
                showError(s, PltBBMng.class.getName()+ ".getDsRcvType()" );
            }
            Object[] arr;
            List<SelectItem> lstResult=new ArrayList<SelectItem>();
            SelectItem si;
            for(int i=0;i<lst.size();i++)
            {
                arr=(Object[]) lst.get(i);
                si = new SelectItem(arr[0],arr[1] + " (" + arr[2] + ")");
                lstResult.add(si);
            }
            m_lstRcvType=lstResult;
            return m_lstRcvType;
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".getDsRcvType()");
        }
        return null;
    }

    public void onDtShowLongDetail(ActionEvent ev)
    {
        m_bShowLongDetail=!m_bShowLongDetail;
    }
    public void onDtRcvObjAdd(ActionEvent ev)
    {
        if (m_currentBBNew==null || m_currentRcvTypeID==null)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null,"Bạn chưa chọn đối tượng nhận");
            return;
        }
        m_sDlgHeader=m_rf.getMessage("BBDTRcvAddNew");
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.addCallbackParam("Method", "BBDTRcvAddNew_" + m_currentRcvTypeID);
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "BBDTRcvAddNew_" + m_currentRcvTypeID);

    }

    public void onDtRcvObjRemove(ActionEvent ev)
    {
        if (m_currentBBNew==null || m_currentRcvTypeID==null || m_arrRcvObjChooose==null || m_arrRcvObjChooose.length==0)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("BBMsgNoChooseRcv"));
            return;
        }
        if (m_arrRcvObjChooose==null || m_arrRcvObjChooose.length==0)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("BBMsgNoCheckRcv"));
            return;
        }
        for(int i=0;i<m_arrRcvObjChooose.length;i++)
        {
            lstRcvObj_Remove(m_arrRcvObjChooose[i]); //Cập nhật danh sách thao tác với DB
            if (m_currentRcvTypeID.equals("BYUSER"))
               m_lstRcvObj_User.remove(m_arrRcvObjChooose[i]);
            else if(m_currentRcvTypeID.equals("BYDEPT"))
               m_lstRcvObj_Dept.remove(m_arrRcvObjChooose[i]); //Gỡ khỏi mảng hiển thị
            else if(m_currentRcvTypeID.equals("BYROLE"))
               m_lstRcvObj_Role.remove(m_arrRcvObjChooose[i]);
        }

        m_lstRcvType =null; //refresh list


//        m_eRcvAction=enumRcvAction.eRcvObjRemove;
//        m_sDeleteMsg=m_rf.getMessage("BBDTRcvRemoveConfirm");
//        m_sDeleteMsg=m_sDeleteMsg.replaceAll("@count",String.valueOf(m_arrRcvObjChooose.length));
//        if (m_arrRcvObjChooose.length==1)
//            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc", " (" + m_arrRcvObjChooose[0].getId().getRcvid() + " - " + m_arrRcvObjChooose[0].getObjdesc() + ")");
//        else
//            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc","");
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "BBDTRcvRemove");
    }
  
    public void onDtCloseDlgFunc(CloseEvent evt)
    {
        WorkingContext.resetDialogObjSelect();
    }

    public void onDtConfirmEnd(ActionEvent ev)
    {

    }

    public void onDtDlgChooseClick(ActionEvent ev)
    {
        boolean bAdd, bChanged;
        bChanged=false;
        if (m_currentRcvTypeID.equals("BYUSER"))
        {
            List<Q_User> lstUser = UserDialog.getSelected();
            if (lstUser==null || lstUser.isEmpty() || m_lstRcvObj_User==null) //Có chọn đối tượng không, dừng luôn nếu không chọn hoặc chưa khởi tạo được danh sách source
                return;
            //Kiểm tra bản ghi mới để thêm vào danh sách
            for(Q_User d:lstUser)
            {
                bAdd=true;
                for(Bb_Message_Rcv_Ext b:m_lstRcvObj_User)
                {
                    if (b.getId().getRcvid().equals(d.getUserid()))
                    {
                        bAdd=false;
                        break;
                    }
                }
                //Bản ghi mới, thêm vào danh sách hiển thị
                if (bAdd)
                {
                    Bb_Message_Rcv_Ext bb=new Bb_Message_Rcv_Ext(m_currentBBNew.getBbmid(),m_currentRcvTypeID,d.getUserid());
                    m_lstRcvObj_User.add(bb);
                    lstRcvObj_Add(bb);
                    bChanged=true;
                }
            }
        }
        else if (m_currentRcvTypeID.equals("BYDEPT"))
        {
            List<S_Dept> lstDept = DeptDialog.getSelected();
            if (lstDept==null || lstDept.isEmpty() || m_lstRcvObj_Dept==null) //Có chọn đối tượng không, dừng luôn nếu không chọn hoặc chưa khởi tạo được danh sách source
                return;
            //Kiểm tra bản ghi mới để thêm vào
            for(S_Dept d:lstDept)
            {
                bAdd=true;
                for(Bb_Message_Rcv_Ext b:m_lstRcvObj_Dept)
                {
                    if (b.getId().getRcvid().equals(d.getDeptid()))
                    {
                        bAdd=false;
                        break;
                    }
                }
                //Bản ghi mới, thêm vào danh sách hiển thị
                if (bAdd)
                {
                    Bb_Message_Rcv_Ext bb=new Bb_Message_Rcv_Ext(m_currentBBNew.getBbmid(),m_currentRcvTypeID,d.getDeptid());
                    m_lstRcvObj_Dept.add(bb);
                    lstRcvObj_Add(bb);
                    bChanged=true;
                }
            }
        }
        else if (m_currentRcvTypeID.equals("BYROLE"))
        {
            List<Q_Role> lstRole = RoleDialog.getSelected();
            if (lstRole==null || lstRole.isEmpty() || m_lstRcvObj_Role==null) //Có chọn đối tượng không, dừng luôn nếu không chọn hoặc chưa khởi tạo được danh sách source
                return;
            //Kiểm tra bản ghi mới để thêm vào danh sách
            for(Q_Role d:lstRole)
            {
                bAdd=true;
                for(Bb_Message_Rcv_Ext b:m_lstRcvObj_Role)
                {
                    if (b.getId().getRcvid().equals(d.getRoleid()))
                    {
                        bAdd=false;
                        break;
                    }
                }
                //Bản ghi mới, thêm vào danh sách hiển thị
                if (bAdd)
                {
                    Bb_Message_Rcv_Ext bb=new Bb_Message_Rcv_Ext(m_currentBBNew.getBbmid(),m_currentRcvTypeID,d.getRoleid());
                    m_lstRcvObj_Role.add(bb);
                    lstRcvObj_Add(bb);
                    bChanged=true;
                }
            }
        }
        if (bChanged) //update lại list đối tượng nhận
            m_lstRcvType=null;
    }

    protected void lstRcvObj_reset()
    {
        m_lstRcvObj_Add=null;
        m_lstRcvObj_Remove=null;
    }

    protected void lstRcvObj_Add(Bb_Message_Rcv_Ext bb)
    {
        //Kiểm tra danh sách xóa DB, gỡ nếu có
        if (m_lstRcvObj_Remove != null)
        {
            for(Bb_Message_Rcv b:m_lstRcvObj_Remove)
            {
                if (b.getId().equals(bb.getId()))
                {
                    m_lstRcvObj_Remove.remove(b);
                    return;
                }
            }
        }
        //Không có trong danh sách xóa DB, thêm vào danh sách insert
        if (m_lstRcvObj_Add==null)
            m_lstRcvObj_Add=new ArrayList<Bb_Message_Rcv>();
        Bb_Message_Rcv bNew = new Bb_Message_Rcv(bb.getId());
        m_lstRcvObj_Add.add(bNew);
    }

    protected void lstRcvObj_Remove(Bb_Message_Rcv_Ext bb)
    {
        //Kiểm tra danh sách add, gỡ nếu có
        if (m_lstRcvObj_Add != null)
        {
            for(Bb_Message_Rcv b:m_lstRcvObj_Add)
            {
                if (b.getId().equals(bb.getId()))
                {
                    m_lstRcvObj_Add.remove(b);
                    return;
                }
            }
        }
        //Không có trong danh sách add, thêm vào danh sách delete DB
        if (m_lstRcvObj_Remove==null)
            m_lstRcvObj_Remove=new ArrayList<Bb_Message_Rcv>();
        Bb_Message_Rcv bNew = new Bb_Message_Rcv(bb.getId());
        m_lstRcvObj_Remove.add(bNew);
    }

    public void onChangeRcvObjType(AjaxBehaviorEvent ev)
    {
        //reset lai chi so trang
        DataTable dt=(DataTable) WorkingContext.findJSFComponent("formDlg:dtRcvObj", false);
        if (dt !=  null)
            dt.reset();
        
        getDsRcvObj();
        
    }

    public List<Bb_Message_Rcv_Ext> getDsRcvObj()
    {
        if (m_currentRcvTypeID==null || m_currentRcvTypeID.equals("BYALL"))
            return null;
        try
        {
            if (m_currentRcvTypeID.equals("BYUSER"))
            {
                if (m_lstRcvObj_User==null)
                    m_lstRcvObj_User=getNewLstRcvObj(m_currentBBNew.getBbmid(),m_currentRcvTypeID);
                return m_lstRcvObj_User;
            }
            else if (m_currentRcvTypeID.equals("BYDEPT"))
            {
                if (m_lstRcvObj_Dept==null)
                    m_lstRcvObj_Dept=getNewLstRcvObj(m_currentBBNew.getBbmid(),m_currentRcvTypeID);
                return m_lstRcvObj_Dept;

            }
            else if (m_currentRcvTypeID.equals("BYROLE"))
            {
                if (m_lstRcvObj_Role==null)
                    m_lstRcvObj_Role=getNewLstRcvObj(m_currentBBNew.getBbmid(),m_currentRcvTypeID);
                return m_lstRcvObj_Role;
            }
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".getDsRcvObj()");
        }
        return null;
    }
    protected List<Bb_Message_Rcv_Ext> getNewLstRcvObj(String sBBID, String RcvTypeID)
    {
        try
        {
            ISM_BB_BLRemote ibb=getISM_BB_BLRemote();
            if (ibb==null)
                return null;
            List lst = ibb.getLstRcvObj(sBBID,RcvTypeID);
            if (lst==null)
            {
                showError(ibb.getLastActionInfo(),PltBBMng.class.getName() + ".getNewLstRcvObj()" );
            }
            return lst;
        }
        catch (Exception ex)
        {
            showErrorFromException(ex, PltBBMng.class.getName() + ".getNewLstRcvObj()");
        }
        return null;
    }

    public void onDtNvgFirst(ActionEvent ev)
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex==0)
            return;
        m_nvgCurrentIndex=0;
        showBBDetailForIndex(m_nvgCurrentIndex);
    }
    public void onDtNvgPrev(ActionEvent ev)
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex ==0 )
            return;
        m_nvgCurrentIndex--;
        showBBDetailForIndex(m_nvgCurrentIndex);
    }
    public void onDtNvgNext(ActionEvent ev)
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex == m_nvgLstID.size() - 1 )
            return;
        m_nvgCurrentIndex++;
        showBBDetailForIndex(m_nvgCurrentIndex);
    }
    public void onDtNvgLast(ActionEvent ev)
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex == m_nvgLstID.size() - 1 )
            return;
        m_nvgCurrentIndex=m_nvgLstID.size() - 1;
        showBBDetailForIndex(m_nvgCurrentIndex);
    }

    public String getDtNvgDisabledPrev()
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex <=0 || m_nvgCurrentIndex >= m_nvgLstID.size())
            return "true";
        return "false";
    }
    public String getDtNvgDisabledNext()
    {
        if (m_nvgLstID==null || m_nvgCurrentIndex >= m_nvgLstID.size() - 1 || m_nvgCurrentIndex < 0)
            return "true";
        return "false";
    }

    //Hàm hiển thị chi tiết
    protected void showBBDetailForIndex(int nvgIndex)
    {
        String sBBID;
        sBBID=m_nvgLstID.get(nvgIndex);
        m_bShowLongDetail=false;
        //Lấy bản ghi dữ liệu
        m_currentBBNew = (Bb_Message) getISM_BB_BLRemote().findById(sBBID, Bb_Message.class);
        if (m_currentBBNew == null)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgRecordNotExist"));
            return;
        }
        resetDT();
    }

    public void setDtAttachCount(Integer value)
    {
        if (m_currentBBNew==null || m_currentBBNew.getBbmid()==null)
            return;
        m_currentBBNew.setAttachcount(value);
    }
    
    public Integer getDtAttachCount()
    {
        if (m_currentBBNew==null || m_currentBBNew.getBbmid()==null)
            return 0;
        if (m_currentBBNew.getAttachcount() != null)
            return m_currentBBNew.getAttachcount();
        try
        {
            //ILibRemote ir=(ILibRemote) EjbContext.getLocalEJBRemote(ILibRemote.class.getSimpleName());
            //m_currentBBNew.setAttachcount(ir.getAllAf_OtherCount("BBMSG", m_currentBBNew.getBbmid()));
            m_currentBBNew.setAttachcount(getIShareAttach().getAllAf_OtherCount("BBMSG", m_currentBBNew.getBbmid()));
            return m_currentBBNew.getAttachcount();
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltBBMng.class.getName() + ".getDtAttachCount()");
        }
        return 0;
    }

    public void onDtDlgAfOtherClose(CloseEvent ev)
    {
        m_currentBBNew.setAttachcount(null); //reset
    }


    public void onDtAttachOpen(ActionEvent ev)
    {
        if (m_eActionCurrent==enumAction.eBBAdd || m_currentBBNew.getBbmid()==null)
            return;

//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "BBDTAttachOpen");
//        context.addCallbackParam("bbmid", m_currentBBNew.getBbmid());
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "BBDTAttachOpen");
        PrimeFaces.current().ajax().addCallbackParam("bbmid", m_currentBBNew.getBbmid());
        
        if (renderedFuncEdit("02.31"))
            WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, BaseConstant.getObjRightFull());
        else
            WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, BaseConstant.getObjRightReadonly());
    }
//}}</editor-fold>

//{{<editor-fold defaultstate="collapsed" desc="Các hàm cho trang nhật ký đọc">
    private List<Q_User_Ext> m_BbLogLstReadUser;
    private List<Q_User> m_BbLogLstNoReadUser;

    public List<Q_User_Ext> getBbLogDsReadUser()
    {
        if (m_BbLogLstReadUser == null)
            initBbLogLstReadUser();
        return m_BbLogLstReadUser;
    }
    protected void initBbLogLstReadUser()
    {
        try
        {
            if (m_currentBBNew == null)
                return;
            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            if (iscr == null)
                return;
            m_BbLogLstReadUser = iscr.fLogObj_LstUserRead(m_currentBBNew.getBbmid(), "BBMSG", null);
            if (m_BbLogLstReadUser == null)
            {
                showError(iscr.getLastActionInfo(),PltBBMng.class.getName() + ".initBbLogLstReadUser()");
                return;
            }
        }
        catch (Exception ex)
        {
            showErrorFromException(ex,PltBBMng.class.getName() + ".initBbLogLstReadUser()");
        }
    }

    public List<Q_User> getBbLogDsNoReadUser()
    {
        if (m_BbLogLstNoReadUser==null)
            initBbLogLstNoReadUser();
        return m_BbLogLstNoReadUser;
    }
    protected void initBbLogLstNoReadUser()
    {
        try
        {
            if (m_currentBBNew == null)
                return;
            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            if (iscr == null)
                return;
            m_BbLogLstNoReadUser = iscr.fLogObj_LstUserNoReadBB(m_currentBBNew.getBbmid(), null);
            if (m_BbLogLstNoReadUser == null)
            {
                showError(iscr.getLastActionInfo(),PltBBMng.class.getName() + ".initBbLogLstNoReadUser()");
                return;
            }
        }
        catch (Exception ex)
        {
            showErrorFromException(ex,PltBBMng.class.getName() + ".initBbLogLstNoReadUser()");
        }
    }

    public String getBbLogMsgUserRcv()
    {
        String s="";
        try
        {
            s=m_rf.getMessage("BBLogMsgRcv");
            if (m_currentBBNew==null)
                return s;
            ISM_BB_BLRemote ibb=(ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            if (ibb == null)
                return s;
            Integer iCount = ibb.getRcvUserCount(m_currentBBNew.getBbmid());
            if (iCount == null)
            {
                showError(ibb.getLastActionInfo(),PltBBMng.class.getName() + ".getBbLogMsgUserSum()");
                return "";
            }
            s += ": " + iCount;
            return s;
        }
        catch (Exception ex)
        {
            showErrorFromException(ex,PltBBMng.class.getName() + ".getBbLogMsgUserSum()");
        }
        return s;
    }

    public String getBbLogMsgReadUpdate()
    {
        String s=m_rf.getMessage("BBLogMsgNeedUpdate");
        List<Q_User_Ext> lst=getBbLogDsReadUser();
        int iCount=0;
        if (lst!=null)
        {
            //Kiểm tra trường isupdate
            for(Q_User_Ext u:lst)
            {
                if (u.getBblogisupdate() != null && u.getBblogisupdate())
                    iCount++;
            }
        }
        s += ": " + iCount;
        return s;
    }

    public String getBbLogMsgTabReadUser()
    {
        String s=m_rf.getMessage("BBLogTabRead");
        List<Q_User_Ext> lst=getBbLogDsReadUser();
        if (lst != null)
            s += " (" + lst.size() + ")";
        return s;
    }

    public String getBbLogMsgTabNoReadUser()
    {
        String s=m_rf.getMessage("BBLogTabNoRead");
        List<Q_User> lst=getBbLogDsNoReadUser();
        if (lst != null)
            s += " (" + lst.size() + ")";
        return s;
    }

//}}</editor-fold>
    private PltBBMngDataModel pltBBMngDataModel;
    public PltBBMngDataModel getPltBBMngDataModel() {
         getDsBB();
         pltBBMngDataModel=new PltBBMngDataModel(m_lstBB);
         return pltBBMngDataModel;
    }
    private PltBBMngRcvDataModel pltBBMngRcvDataModel;
    public PltBBMngRcvDataModel getPltBBMngRcvDataModel() {
        if(getDsRcvObj()!=null)
            pltBBMngRcvDataModel=new PltBBMngRcvDataModel(getDsRcvObj());
        else
            pltBBMngRcvDataModel=null;
        return pltBBMngRcvDataModel;
    }
    
    //cau hinh show editor
//    public Config getConfigCk(){
//        Config config=new Config();
//        ToolbarButtonGroup toolbarDocument=new ToolbarButtonGroup("document")
//            .item(ToolbarItem.SOURCE);
//        ToolbarButtonGroup toolbarInsert = new ToolbarButtonGroup("insert")
//            .item(ToolbarItem.TABLE)
//            .item(ToolbarItem.HORIZONTAL_RULE)
//            .item(ToolbarItem.SPECIAL_CHAR)
//            .item(ToolbarItem.PAGE_BREAK)
//            .item(ToolbarItem.PASTE_FROM_WORD);
//        ToolbarButtonGroup toolbarEditing = new ToolbarButtonGroup("editing")
//            .item(ToolbarItem.FIND)
//            .item(ToolbarItem.REPLACE)
//            .item(ToolbarItem.SEPARATOR)
//            .item(ToolbarItem.SELECT_ALL);
//        Toolbar toolbar=new Toolbar(toolbarDocument, toolbarInsert, toolbarEditing, 
//                ToolbarButtonGroup.PARAGRAPH,ToolbarButtonGroup.STYLES, ToolbarButtonGroup.COLORS, 
//                ToolbarButtonGroup.BASIC_STYLES);
//        config.toolbar(toolbar);
//        return config;
//    }
}
