/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_Function;
import main.entity.shared.admin.Q_User;
import main.entity.shared.system.Sm_Shortcut;
import main.entity.shared.system.Sm_Shortcutgroup;
import main.remote.shared.admin.IAdmin;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectBoolean;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import main.remote.shared.system.ISM_BB_BLRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author thaodd
 */
@ManagedBean(name="PltShortcutMng")
@ViewScoped
public class PltShortcutMng extends BasePageCommon implements Serializable {
    private String m_AppKeyCurrent;
    private String m_UserID;
    private List m_lstApp; //mỗi phần tử là một SelectItem đưa vào combo
    private List m_lstUser; //mỗi phần tử là một SelectItem đưa vào combo
    private List m_lstShortcutGrp; ; //mỗi phần tử là một SelectItem đưa vào combo
    private List<Sm_Shortcutgroup> m_lstShortcutGrpE; ; //mỗi phần tử là một Entity
    private List<Sm_Shortcut> m_lstShortcut; //mỗi phần tử là một entity
    private Sm_Shortcut m_shortcutCurrent; //Lưu lại biến đang chọn trên grid
    private Sm_Shortcut m_shortcutNew; //Lưu lại biến shortcut tạo mới hoặc đang edit
    private int m_shortcutGrpIndexCurrent; //Lưu lại chỉ số biến nhóm đang chọn trong danh sách Entity m_lstShortcutGrpE
    private Sm_Shortcutgroup m_shortcutGrpNew; //Lưu lại biến nhóm tạo mới: lúc nào cũng sẵn sàng
    private String m_shortcutGrpIDCurrent; //Mã nhóm shortcut đang chon
    private String m_sDlgHeader; //Lưu tên thao tác để truyền vào Dialog
    private ResourcesFactory m_rf;
    private String m_scGrpMoveUp_Disabled, m_scGrpMoveDown_Disabled;

    private List<SelectItem> m_lstFunc; //List SelectItem các function thuộc AppKeyCurrent đưa vào combo
    private boolean m_bCheckAllShortcut;
    private String m_sDeleteMsg; //Thông điệp khi xóa

    private enum enumAction
    {
        eNone,
        eGroupAdd,
        eGroupEdit,
        eGroupDelete,
        eShortcutAdd,
        eShortcutEdit,
        eShortcutDelete
    }
    private enumAction m_eActionCurrent=enumAction.eNone; //Biến lưu thao tác hiện tại

    /** Creates a new instance of SM_BB_Bean */
    public PltShortcutMng() {
        //Khởi tạo biến m_AppKeyCurrent
        //WorkingContext w=new WorkingContext();
        m_AppKeyCurrent = WorkingContext.getAppKeyCurrent();// w.getCookieValue(WorkingContext.APP_KEY_CURRENT);
        m_UserID=WorkingContext.getUserName();
        m_rf=new ResourcesFactory("main/web/shared/system/prop/SM_BB_Prop");
        //Đọc tham số
        String s;
        s=WorkingContext.getRequestQueryString("sm");
        if (s!=null) //Có tham số dòng lệnh cho shortcut
        {
            if (s.equals("edit"))
            {
                s=WorkingContext.getRequestQueryString("smid");
                ISM_BB_BLRemote rm=getISM_BB_BLRemote();
                if (rm != null)
                    m_shortcutNew=(Sm_Shortcut) rm.findById(s, Sm_Shortcut.class);
            }
            if (m_shortcutNew != null)
                m_eActionCurrent=enumAction.eShortcutEdit;
            else //Thêm mới shortcut
            {
                m_shortcutNew=new Sm_Shortcut();
                m_eActionCurrent=enumAction.eShortcutAdd;
                //Khởi tạo appkey
                s=WorkingContext.getRequestQueryString("Appfunctionid");
                if (s != null && !s.isEmpty() && !s.equals("undefined"))
                    m_shortcutNew.setAppfunctionid(s);

                s=WorkingContext.getRequestQueryString("functionid");
                if (s != null && !s.isEmpty() && !s.equals("undefined"))
                    m_shortcutNew.setFunctionid(s);
                else
                {
                    s=WorkingContext.getRequestQueryString("urlname");
                    if (s != null && !s.isEmpty() && !s.equals("undefined")) {
                        m_shortcutNew.setShortcutdesc(s);
                        m_shortcutNew.setUrl((String)WorkingContext.getSessionValueAndRemove(WorkingContext.URL_SESSION_KEY));
                    }
                }
                //Khởi tạo userkey
                s=WorkingContext.getRequestQueryString("Userid");
                if (s != null && !s.isEmpty() && !s.equals("undefined")) //Từ JavaScript truyền vào null sẽ thành && "undefined"
                    m_shortcutNew.setUserid(s);
                else
                    m_shortcutNew.setUserid(WorkingContext.getUserName());

                //Khởi tạo mã nhóm
                s=WorkingContext.getRequestQueryString("Groupid");
                if (s != null && !s.isEmpty() && !s.equals("undefined"))
                    m_shortcutNew.setShortcutgrpid(s);

                if (m_shortcutNew.getFunctionid() !=null) //Gọi các hàm để cập nhật lại mô tả shortcut nếu có
                {
                    getDsFunc();
                    onFuncChangeAjax();
                }
            }
        }
        else //Ngầm định giao diện chính
            m_shortcutGrpNew=new Sm_Shortcutgroup();

        m_scGrpMoveUp_Disabled="true"; m_scGrpMoveDown_Disabled="true";
        
    }

    //{{<editor-fold defaultstate="collapsed" desc="Các get/set">

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }
    
    public boolean getM_bCheckAllShortcut() {
        return m_bCheckAllShortcut;
    }

    public void setM_bCheckAllShortcut(boolean m_bCheckAllShortcut) {
        this.m_bCheckAllShortcut = m_bCheckAllShortcut;
    }

    public String getScGrpMoveUp_Disabled()
    {
        updateSCGroupDisabled(); //Gọi luôn tại đây để lấy giá trị mới nhất
        return m_scGrpMoveUp_Disabled;
    }
    public String getScGrpMoveDown_Disabled()
    {
        updateSCGroupDisabled(); //Gọi luôn tại đây để lấy giá trị mới nhất
        return m_scGrpMoveDown_Disabled;
    }

    /**
     * Hàm lấy ra đối tượng shortcut đưa vào dialog khi thêm mới, sửa
     * @return
     */
    public Sm_Shortcut getDlgShortcut()
    {
        return m_shortcutNew;
    }

    /**
     * Hàm lấy ra đối tượng group đưa vào dialog khi thêm mới, sửa
     * @return
     */
    public Sm_Shortcutgroup getDlgShortcutGroup(){       
        return m_shortcutGrpNew;
    }


    public String getDlgHeader()
    {
        return m_sDlgHeader;
    }
    public String getM_shortcutGrpIDCurrent() {
        return m_shortcutGrpIDCurrent;
    }

    public void setM_shortcutGrpIDCurrent(String m_shortcutGrpIDCurrent) {
        this.m_shortcutGrpIDCurrent = m_shortcutGrpIDCurrent;
    }

    public Sm_Shortcut getM_shortcutCurrent() {
        return m_shortcutCurrent;
    }

    public void setM_shortcutCurrent(Sm_Shortcut m_shortcutCurrent) {
        this.m_shortcutCurrent = m_shortcutCurrent;
    }

    public String getM_UserID() {
        return m_UserID;
    }

    public void setM_UserID(String m_UserID) {
        this.m_UserID = m_UserID;
    }

    /**
     * Hàm lấy ra mã của phân hệ đang chọn
     */
    public String getAppKeyCurrent()
    {
        return m_AppKeyCurrent;
    }

    /**
     * Hàm ghi lại mã của phân hệ đang chọn
     */
    public void setAppKeyCurrent(String sKey)
    {
        m_AppKeyCurrent=sKey;
    }
    //}}</editor-fold>

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
    protected List initDsUserWithCheckAdmin()
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
           List lstResult=new ArrayList();

           Q_User q;
           if (b) //is admin
           {
                List<Q_User> lst=ad.getAllUserWithOrder("userid");
                if (lst==null)
                {
                    s=ad.getLastActionInfo();
                    showError(s, PltShortcutMng.class.getName()+ ".initDsUserWithCheckAdmin()" );
                }
                for(int i=0; i < lst.size();i++)
                {
                    q=(Q_User) lst.get(i);
                    lstResult.add(new SelectItem(q.getUserid(), q.getUserid() + " - " + q.getUsername()));
                }
           }
           else //khong phai admin
           {
                 q=ad.findUserByUserID(s);
                 if (q != null)
                    lstResult.add(new SelectItem(q.getUserid(), q.getUserid() + " - " + q.getUsername()));
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

    /**
     * Hàm lấy ra danh sách các phân hệ đã được cài đặt, lấy từ setting, chưa kết nối DB
     * @return List of SelectItem đưa vào combo
     */
//    public List getDsInstallApp()
//    {
//        if (m_lstApp==null)
//            m_lstApp=new LoginBean().getDsInstallApp();
//        return m_lstApp;
//    }

    public List getDsShortcutGroup()
    {
        if (m_lstShortcutGrp==null)
            m_lstShortcutGrp = initDsShortcutGroup();
        return m_lstShortcutGrp;
    }

    protected ISM_BB_BLRemote getISM_BB_BLRemote()
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

    public List initDsShortcutGroup()
    {
       try
       {
           ISM_BB_BLRemote ism = getISM_BB_BLRemote();
           if (ism==null)
               return null;
           List<Sm_Shortcutgroup> lst=ism.getLstShortcutGroup(getM_UserID(), getAppKeyCurrent());
           m_lstShortcutGrpE=lst; //Lưu lại trong biến class để xử lý tiếp trong các hàm khác
           List lstResult = new ArrayList();
           if (lst==null) //Có lỗi xẩy ra
           {
               showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".initDsShortcutGroup()");
               return null;
           }
           lstResult.add(new SelectItem(null,m_rf.getMessage("SMNoGroup")));
           for(int i=0; i< lst.size(); i++)
           {
               Sm_Shortcutgroup oInfo=lst.get(i);
               lstResult.add(new SelectItem(oInfo.getShortcutgrpid(),oInfo.getShortcutgrpdesc()));
           }

           //Khởi tạo tiếp danh sách shortcut: tạm bỏ đi vì cho điều khiển ajax hết qua nút chọn
           //initDsShortcut();
           return lstResult;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, PltShortcutMng.class.getName() + ".initDsShortcutGroup()");
       }
       return null;
    }

    //Thủ tục dùng cho sự kiện f:ajax
    public void onShortcutGrpChangeAjax(AjaxBehaviorEvent event) {
        findShortcutgroupCurrent(null); //Update lai chi so chon trong danh sach
        //updateSCGroupDisabled(); //Đã thay trực tiếp vào lời gọi property
        m_lstShortcut=initDsShortcut();
    }

    //Thủ tục dùng cho sự kiện p:ajax
    public void onShortcutGrpChangeAjax() {
        findShortcutgroupCurrent(null); //Update lai chi so chon trong danh sach
        //updateSCGroupDisabled(); //Đã thay trực tiếp vào lời gọi property
        m_lstShortcut=initDsShortcut();
    }

    //public List<Sm_Shortcut> getDsShortcut()
    public Sm_ShortcutDataModel getDsShortcut()
    {
        if (m_lstShortcut==null)
            m_lstShortcut=initDsShortcut();
        return new Sm_ShortcutDataModel(m_lstShortcut);
    }

    public List<Sm_Shortcut> initDsShortcut()
    {
//       if (m_shortcutGrpIDCurrent==null)
//            return null;
       try
       {
           ISM_BB_BLRemote ism = getISM_BB_BLRemote();
           if (ism==null)
               return null;
           List<Sm_Shortcut> lst=ism.getLstShortcutByGroup(m_shortcutGrpIDCurrent,getM_UserID(), getAppKeyCurrent());
           if (lst==null) //Có lỗi xẩy ra
           {
               showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".initDsShortcut()");
               return null;
           }
                  
           return lst;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, PltShortcutMng.class.getName() + ".initDsShortcutGroup()");
       }
       return null;
    }

    protected void updateSCGroupDisabled()
    {
        //reset cac bien disable move group
        Integer iPos=moveShortcutGroup_CurrentIndex(false);//m_shortcutGrpIndexCurrent;
        m_scGrpMoveUp_Disabled="true";m_scGrpMoveDown_Disabled="true";

        if (m_lstShortcutGrpE.size() > 1 && iPos >=0)
        {
            if (iPos==0)
            {
                m_scGrpMoveDown_Disabled="false";
            }
            else if(iPos == m_lstShortcutGrpE.size() - 1)
            {
                m_scGrpMoveUp_Disabled="false";
            }
            else
            {
                m_scGrpMoveUp_Disabled="false";
                m_scGrpMoveDown_Disabled="false";
            }
        }

    }
    /**
     * Hàm lấy ra thông tin nhóm đang được chọn
     * @return
     */
    public String getShortcutGrpDescCurrent()
    {
        String sMsg=m_rf.getMessage("SMShortcut");
        Sm_Shortcutgroup oGrp=null;
//        List lstResult = new ArrayList<Object>();

        if (m_shortcutGrpIDCurrent==null)
            sMsg += " " + m_rf.getMessage("SMNoGroup");
//Đã gọi hàm find trong sự kiện chọn List rồi nên không gọi lại nữa
//        if (findShortcutgroupCurrent(lstResult))
//        {
//            oGrp=(Sm_Shortcutgroup)lstResult.get(0);
//            sMsg += " " + oGrp.getShortcutgrpdesc();
//        }
        else if(m_shortcutGrpIndexCurrent >= 0)
        {
            oGrp=(Sm_Shortcutgroup)m_lstShortcutGrpE.get(m_shortcutGrpIndexCurrent);
            sMsg += " " + oGrp.getShortcutgrpdesc();

        }

        if (m_lstShortcut==null)
            m_lstShortcut=initDsShortcut();
        
        if (m_lstShortcut != null)
            sMsg += " (" + m_lstShortcut.size() + " " + m_rfCommon.getMessage("msgRecord") + ")";

        return sMsg;
    }

    /**
     * Hàm tìm kiếm và lấy ra object ShortcutGroup đang được chọn và vị trí trong mảng Entity
     * @param lst: trả ra mảng gồm 2 phần tử
     *  Sm_Shortcutgroup oGrp Biến đối tượng thông tin
     *  int iPos Biến vị trí trong list
     * @return true nếu tìm thấy, <>: false
     */
    protected boolean findShortcutgroupCurrent(List lst)
    {
        Sm_Shortcutgroup oGrp; int iPos;
        m_shortcutGrpIndexCurrent=-1;

        if (m_lstShortcutGrpE != null)
        {
            for(int i=0;i < m_lstShortcutGrpE.size();i++)
            {
                oGrp=(Sm_Shortcutgroup) m_lstShortcutGrpE.get(i);
                if (oGrp.getShortcutgrpid().equals(m_shortcutGrpIDCurrent))
                {
                    iPos=i;
                    //m_shortcutGrpCurrent=oGrp; //Lưu lại cho edit
                    m_shortcutGrpIndexCurrent=iPos; //Lưu lại vị trí chọn trong danh sách
                    if (lst != null) //Nếu muốn lấy đầu ra
                    {
                        lst.add(oGrp);
                        lst.add(iPos);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Hàm dùng để refresh hiển thị vùng shortcutgroup
     */
    protected void refreshShortcutGroup()
    {
        m_lstShortcutGrp=null;
    }

    protected void refreshShortcut()
    {
        m_lstShortcut=null;
    }

    /**
     * Hàm refresh toàn bộ: dùng khi bấm nút chọn
     */
    protected void resetLayoutShortcut()
    {
        //Reset vùng hiển thị bên dưới nút chọn
        m_shortcutGrpIDCurrent=null;
        m_shortcutGrpIndexCurrent=-1;
        m_lstShortcutGrp=null;
        m_lstShortcutGrpE=null;
        m_lstShortcut=null;
        m_bCheckAllShortcut=false;
    }
    public void onAcceptChooseClick(ActionEvent event) {

        resetLayoutShortcut();
        //Test: ghi lại cookie
        //WorkingContext w=new WorkingContext();
        //w.setCookieValue(WorkingContext.APP_KEY_CURRENT,m_AppKeyCurrent);
    }

    public void onEditShortcutGroupClick(ActionEvent event) {
        if (m_shortcutGrpIDCurrent==null)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoChooseGroup"));
            return;
        }

        m_sDlgHeader=m_rf.getMessage("SMLblGroupEdit");
        m_eActionCurrent=enumAction.eGroupEdit;
        //Lấy mới từ cơ sở dữ liệu để tạo con trỏ mới, không nên lấy trực tiếp từ biến lst vì sẽ trỏ trực tiếp tới biến, gây lỗi đè entity
        ISM_BB_BLRemote rm=getISM_BB_BLRemote();
        if (rm == null)
            return;
        m_shortcutGrpNew=(Sm_Shortcutgroup) rm.findById(m_shortcutGrpIDCurrent, Sm_Shortcutgroup.class);

        //Trả về client để điều khiển dialog
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "EditShortcutGroup");
//        context.addCallbackParam("ID", m_shortcutGrpIDCurrent);
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "EditShortcutGroup");
        PrimeFaces.current().ajax().addCallbackParam("ID", m_shortcutGrpIDCurrent);
        
    }

    public void onAddShorcutGroupClick(ActionEvent event) {
        
            m_shortcutGrpNew=new Sm_Shortcutgroup();
            m_shortcutGrpNew.setAppfunctionid(m_AppKeyCurrent);
            m_shortcutGrpNew.setUserid(m_UserID);

            //Trả về client để điều khiển dialog
            m_sDlgHeader=m_rf.getMessage("SMLblGroupAdd");
            m_eActionCurrent=enumAction.eGroupAdd;

            //RequestContext context = RequestContext.getCurrentInstance();
            //context.addCallbackParam("Method", "AddShortcutGroup");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "AddShortcutGroup");
    }

    /**
     * Điều khiển hiển thị hộp thoại confirm Delete: Đồng ý
     * @param event
     */

    public void onDeleteEnd(ActionEvent event)
    {
        switch (m_eActionCurrent)
        {
            case eGroupDelete:
                deleteShorcutGroupEnd(event);
                break;
            case eShortcutDelete:
                deleteShorcutEnd(event);
                break;
        }
    }

    protected void deleteShorcutGroupEnd(ActionEvent event)
    {
        try
        {
            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                if (ism.deleteShortcutGroup(m_shortcutGrpIDCurrent))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    resetLayoutShortcut();
                    WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
                }
               else
               {
                    showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".onDeleteShorcutGroupClick()");
               }
            }
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".onDeleteShorcutGroupClick()");
        }
    }

    /**
     * Điều khiển hiển thị hộp thoại confirm Delete: Bắt đầu
     * @param event
     */
    public void onDeleteShorcutGroupBegin(ActionEvent event) {
        if (m_shortcutGrpIDCurrent==null)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoChooseGroup"));
            return;
        }
        m_sDeleteMsg="";//m_sDeleteMsg=m_rfCommon.getMessage("msgDlgDelete");
        Sm_Shortcutgroup oGrp=(Sm_Shortcutgroup)m_lstShortcutGrpE.get(m_shortcutGrpIndexCurrent);
        m_sDeleteMsg += m_rf.getMessage("SMMsgConfirmDelGroup");
        m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc",oGrp.getShortcutgrpdesc()); //m_rf.getMessage("SMGroupDT_Group") + ": " + oGrp.getShortcutgrpdesc();

        m_eActionCurrent=enumAction.eGroupDelete;
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.addCallbackParam("Method", "DelShortcutGroup");
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "DelShortcutGroup");
    }

    public void onDeleteShorcutBegin(ActionEvent event) {
        //Đếm số lượng shortcut được check
        int iCount=0,iSel=0;
        Sm_Shortcut oInfo=null;

        if (m_lstShortcut !=null)
        {
            for(int i=0;i<m_lstShortcut.size();i++)
            {
                oInfo=m_lstShortcut.get(i);
                if (oInfo.getbChecked())
                {
                    iCount++; iSel=i;
                }
            }
        }
        if (iCount==0)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoCheckSC"));
            return;
        }

        m_sDeleteMsg = m_rf.getMessage("SMMsgConfirmDelSC");
        m_sDeleteMsg=m_sDeleteMsg.replaceAll("@count",String.valueOf(iCount));
        if (iCount==1)
            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc"," ('" + m_lstShortcut.get(iSel).getShortcutdesc() + "')");
        else
            m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc","");

        m_eActionCurrent=enumAction.eShortcutDelete;
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.addCallbackParam("Method", "DelShortcut");
        
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "DelShortcut");
    }

    protected void deleteShorcutEnd(ActionEvent event)
    {
        try
        {
            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                Sm_Shortcut oInfo=null;
                if (m_lstShortcut !=null)
                {
                    for(int i=0;i<m_lstShortcut.size();i++)
                    {
                        oInfo=m_lstShortcut.get(i);
                        if (oInfo.getbChecked())
                            if (!ism.delete(oInfo.getShortcutid(),Sm_Shortcut.class))
                            {
                                showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".deleteShorcutEnd()");
                                return;
                            }
                    }
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
                    refreshShortcut();
                }
            }
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".deleteShorcutEnd()");
        }
    }

    /**
     * Hàm tiền xử lý trước khi di chuyển nhóm
     * @return Chỉ số phần tử nhóm đang chọn, -1 nếu không thấy hoặc không hợp lệ, >=0 là chỉ số hiện tại
     */
    protected int moveShortcutGroup_CurrentIndex(boolean bShowMsg)
    {
       if (m_shortcutGrpIDCurrent==null)
        {
           if (bShowMsg)
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoChooseGroup"));
           return -1; 
        }
        List lstResult = new ArrayList<Object>();
        if (findShortcutgroupCurrent(lstResult))
            return (Integer)lstResult.get(1);

        return -1;
    }

    /**
     * Chuyển vị trí giữa 2 phần tử
     * @param iPos1 Chỉ số phần tử nguồn trong mảng Entity
     * @param iPos2 Chỉ số phần tử đích chuyển sang trong mảng Entity
     */
    protected void moveShortcutGroup(int iPos1, int iPos2)
    {
        try
        {
            //Kiểm tra tính hợp lệ của iPos
            if (iPos1 < 0 || iPos2 < 0 || iPos1 >= m_lstShortcutGrpE.size() || iPos2 >= m_lstShortcutGrpE.size())
                return;

            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                Sm_Shortcutgroup grp1=m_lstShortcutGrpE.get(iPos1), grp2=m_lstShortcutGrpE.get(iPos2);
                Integer iOrd1=grp2.getShortcutgrpord(), iOrd2=grp1.getShortcutgrpord(); //STT đã tráo đổi
                if (iOrd1 == null || iOrd2==null)
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn,null,m_rfCommon.getMessage("msgMoveRecordErrorByOrdNull"));
                    return;
                }

                if (!ism.updateShortcutGrpOrd(grp1.getShortcutgrpid(), iOrd1))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgMoveRecordError"));
                    showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".moveShortcutGroup()");
                }
                if (!ism.updateShortcutGrpOrd(grp2.getShortcutgrpid(), iOrd2))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgMoveRecordError"));
                    showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".moveShortcutGroup()");
                }
                //Thành công
                //resetLayoutShortcut();
                refreshShortcutGroup(); //Cho tạo lại DS
                WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
            }
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".moveShortcutGroup()");
        }
    }

    public void onShorcutGroup_MoveFirst(ActionEvent event) {
        int iPos1=moveShortcutGroup_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=0;
        moveShortcutGroup(iPos1, iPos2);
    }

    public void onShorcutGroup_MoveUp(ActionEvent event) {
        int iPos1=moveShortcutGroup_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=iPos1-1;

        moveShortcutGroup(iPos1, iPos2);

    }
    public void onShorcutGroup_MoveDown(ActionEvent event) {
        int iPos1=moveShortcutGroup_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=iPos1+1;

        moveShortcutGroup(iPos1, iPos2);
    }
    public void onShorcutGroup_MoveLast(ActionEvent event) {
        int iPos1=moveShortcutGroup_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=m_lstShortcutGrpE.size()-1;

        moveShortcutGroup(iPos1, iPos2);
    }

    public void onEditShorcutClick(ActionEvent event) {
        String sid=null;
        if (m_shortcutCurrent != null)
            sid=m_shortcutCurrent.getShortcutid();
        else
            for(int i=0;i<m_lstShortcut.size();i++)
            {
                m_lstShortcut.get(i);
                if (m_lstShortcut.get(i).getbChecked())
                {
                    sid=m_lstShortcut.get(i).getShortcutid();
                    break;
                }
            }
        
        if (sid == null)
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoChooseSC"));
            return;
        }
        m_sDlgHeader=m_rf.getMessage("SMLblSCEdit");
        m_eActionCurrent=enumAction.eShortcutEdit;

//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "EditShortcut");
//        context.addCallbackParam("smid", sid);
//        context.addCallbackParam("Appfunctionid", m_AppKeyCurrent);
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "EditShortcut");
        PrimeFaces.current().ajax().addCallbackParam("smid", sid);
        PrimeFaces.current().ajax().addCallbackParam("Appfunctionid", m_AppKeyCurrent);

    }

    public void onAddShorcutClick(ActionEvent event) {
        m_sDlgHeader=m_rf.getMessage("SMLblSCAdd");
        m_eActionCurrent=enumAction.eShortcutAdd;
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "AddShortcut");
//        //Khởi tạo thêm mới
//        context.addCallbackParam("Appfunctionid", m_AppKeyCurrent);
//        context.addCallbackParam("Userid",m_UserID );
//        context.addCallbackParam("Groupid",m_shortcutGrpIDCurrent==null?"":m_shortcutGrpIDCurrent);
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "AddShortcut");
        PrimeFaces.current().ajax().addCallbackParam("Userid", m_UserID);
        PrimeFaces.current().ajax().addCallbackParam("Groupid", m_shortcutGrpIDCurrent==null?"":m_shortcutGrpIDCurrent);
        PrimeFaces.current().ajax().addCallbackParam("Appfunctionid", m_AppKeyCurrent);
                
                

    }

    public void onDlgSave(ActionEvent event) {
        boolean b=false;
        String sDlgID="";
        switch(m_eActionCurrent){
            case eGroupAdd:
                b=saveShortcutGroupAdd();
                sDlgID="dlgSCGroup";
                refreshShortcutGroup();
                break;
            case eGroupEdit:
                b=saveShortcutGroupEdit();
                sDlgID="dlgSCGroup";
                refreshShortcutGroup();
                break;
            case eShortcutAdd:
                //sDlgID="dlgSC";
                b=saveShortcutAdd();
                break;
            case eShortcutEdit:
                //sDlgID="dlgSC";
                b=saveShorcutEdit();
        }

        if (!sDlgID.isEmpty())
        {
//            RequestContext context = RequestContext.getCurrentInstance();
//            //context.addCallbackParam("componentId", event.getComponent().getClientId());
//            context.addCallbackParam("dlgId", sDlgID);
//            context.addCallbackParam("result",b);
            
            PrimeFaces.current().ajax().addCallbackParam("dlgId", sDlgID);
            PrimeFaces.current().ajax().addCallbackParam("result", b);
        }
        if (b)
            WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
        //Không dùng hàm này nữa vì nhiều thông tin bị reset quá
        //if (b) //Refresh
        //    resetLayoutShortcut();
    }

    /**
     * Hàm kiểm tra đầu vào shortcutGroup trước khi insert, update sau khi đã qua vòng giao diện
     * @return
     */
    protected boolean checkInput_ShortcutGroup()
    {
        if (m_shortcutGrpNew.getShortcutgrpdesc()==null || m_shortcutGrpNew.getShortcutgrpdesc().isEmpty())
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null,m_rf.getMessage("SMGroupDT_Group_Required"));
            return false;
        }
        return true;
    }

    protected boolean saveShortcutGroupEdit()
    {
        try
        {
            if (!checkInput_ShortcutGroup())
                return false;
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (iBL.update(m_shortcutGrpNew))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                }

        }
        catch (Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".saveShortcutGroupEdit()");
        }
        return true;
    }

    protected boolean saveShortcutGroupAdd()
    {
        //String s;
        //s=m_shortcutGrpNew.getShortcutgrpid() + " - " + m_shortcutGrpNew.getShortcutgrpdesc();
        //WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, s);

        try
        {
            if (!checkInput_ShortcutGroup())
                return false;
            //Sinh key
            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            S_Key_ControlInfo kc=new S_Key_ControlInfo(null,Sm_Shortcutgroup.class.getSimpleName());
            kc=iscr.getGenKeyID(kc);
            if (kc==null || kc.getGenStatus()!=0) //Không thành công
            {
                showErrorFromGenKey(kc, PltShortcutMng.class.getName() + ".saveShortcutGroupAdd()");
                return false;
            }

            m_shortcutGrpNew.setShortcutgrpid(kc.getOutputValue());
            if (m_shortcutGrpNew.getShortcutgrpord()==null) //Cập nhật STT nếu chưa nhập
                m_shortcutGrpNew.setShortcutgrpord(kc.getSvalue());

            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (iBL.insert(m_shortcutGrpNew))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".saveShortcutGroupAdd()");
        }


        return true;
    }

    protected boolean checkInput_Shortcut()
    {
        if ((m_shortcutNew.getFunctionid()==null || m_shortcutNew.getFunctionid().isEmpty())
                && (m_shortcutNew.getUrl() == null || m_shortcutNew.getUrl().isEmpty()))
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null,m_rf.getMessage("SMDTValidate_URL"));
            return false;
        }

        return true;
    }

    protected boolean saveShortcutAdd()
    {
        try
        {
            if (!checkInput_Shortcut())
                return false;
            //Kiểm tra tồn tại
            ISM_BB_BLRemote ism = getISM_BB_BLRemote();
            if (ism==null)
               return false;

            //Kiểm tra nếu là gắn với function thì phải chưa có
            if (m_shortcutNew.getFunctionid() != null && !m_shortcutNew.getFunctionid().isEmpty()) {
                if (ism.checkFuncShortcutExist(m_shortcutNew.getUserid(), m_shortcutNew.getAppfunctionid(), m_shortcutNew.getFunctionid()))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMMsgFuncExist"));
                    return false;
                }
            }

            //Sinh key
            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            S_Key_ControlInfo kc=new S_Key_ControlInfo(null,Sm_Shortcut.class.getSimpleName());
            kc=iscr.getGenKeyID(kc);
            if (kc==null || kc.getGenStatus()!=0) //Không thành công
            {
                showErrorFromGenKey(kc, PltShortcutMng.class.getName() + ".saveShortcutAdd()");
                return false;
            }

            m_shortcutNew.setShortcutid(kc.getOutputValue());
            if (m_shortcutNew.getUrlord()==null) //Cập nhật STT nếu chưa nhập
                m_shortcutNew.setUrlord(kc.getSvalue());

            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (iBL.insert(m_shortcutNew))
                {
                    //Cap nhat lai truong ma
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                    if (m_shortcutNew.getFunctionid() != null) {
                        m_lstFunc=null; //refresh lai combo
                        m_shortcutNew.setqfunction((Q_Function)iBL.findById(m_shortcutNew.getFunctionid(), Q_Function.class));
                        //Chuyen method lai
                        //m_eActionCurrent=enumAction.eShortcutEdit;
                    }
                    onResetNewShortcut(null);
                }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".saveShortcutAdd()");
        }
        return true;
    }

    protected boolean saveShorcutEdit()
    {
        try
        {
            if (!checkInput_Shortcut())
                return false;
            //Ghi vào CSDL
            ISM_BB_BLRemote iBL=getISM_BB_BLRemote();
            if (iBL != null)
                if (iBL.update(m_shortcutNew))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgSaveSuccess"));
                }
        }
        catch (Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".saveShorcutEdit()");
        }
        return true;
    }

    public List<SelectItem> getDsFunc()
    {
        if (m_lstFunc==null)
            m_lstFunc=initLstFunc();
        return m_lstFunc;
    }

    public List<SelectItem> initLstFunc()
    {
       try
       {
           List<Q_Function> lst;
           List lstResult = new ArrayList();
           IAdmin iad;
           if (m_eActionCurrent==enumAction.eShortcutEdit)
           {
               
               if (!Tools.fStrIsNullOrEmpty(m_shortcutNew.getUrl()))
                    lstResult.add(new SelectItem(null,m_rf.getMessage("SMNoFunc")));
               else {
                    Q_Function oInfo=m_shortcutNew.getqfunction();
                    lstResult.add(new SelectItem(oInfo.getFunctionid(),oInfo.getFunctionname()));
               }
               return lstResult;
           }

           iad = (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());
           if (iad==null)
               return null;

           lst=iad.getLstFuncOfAppWithUrl(m_AppKeyCurrent,iad.checkUserIsAdmin(m_UserID),m_UserID);
           if (lst==null) //Có lỗi xẩy ra
           {
               showError(iad.getLastActionInfo(), PltShortcutMng.class.getName() + ".initLstFunc()");
               return null;
           }
           lstResult.add(new SelectItem(null,m_rf.getMessage("SMNoFunc")));
           for(int i=0; i< lst.size(); i++)
           {
               Q_Function oInfo=lst.get(i);
               lstResult.add(new SelectItem(oInfo.getFunctionid(),oInfo.getFunctionname()));
           }

           //Khởi tạo tiếp danh sách shortcut: tạm bỏ đi vì cho điều khiển ajax hết qua nút chọn
           //initDsShortcut();
           return lstResult;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, PltShortcutMng.class.getName() + ".initLstFunc()");
       }
       return null;
    }

    public void onFuncChangeAjax()
    {
        if (m_shortcutNew.getFunctionid() != null)
        {
            for(SelectItem si:m_lstFunc)
            {
                if (si.getValue() != null && si.getValue().toString().equals(m_shortcutNew.getFunctionid()))
                {
//                    UIOutput txt=(UIOutput) WorkingContext.findJSFComponent("formDlg:shortcutdesc", false);
//                    if (txt != null)
//                        txt.setValue(si.getLabel());
                    m_shortcutNew.setShortcutdesc(si.getLabel());
                }
            }
        }

    }

    public void onCloseDlgFunc(CloseEvent evt)
    {
        refreshShortcut();
        //m_lstShortcut=initDsShortcut();
    }

    public void onCheckShortcut(AjaxBehaviorEvent event) {
        try {
            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
            //dtData = (DataTable)selectBoolean.getParent().getParent();
            //Lấy vị trí
            String temps[] = selectBoolean.getClientId().split("\\:");
            if (temps.length > 2) {
                int rowIndex = Integer.valueOf(temps[2]);
                //dtData.setRowIndex(rowIndex);
                //System.out.println("rowIndex=" + rowIndex);
                //if (m_bIsLazy)
                //    rowIndex=rowIndex % getPageSize(); //Khi load lazy tung trang thi bien lstData chi chua du lieu cua mot trang con rowIndex la chi so tong

                Sm_Shortcut s_Data = m_lstShortcut.get(rowIndex); // (A_Asset) dtData.getRowData();
                s_Data.setbChecked(!s_Data.getbChecked());

                //System.out.println("Set check " + s_Data.getbChecked() + " for " + s_Data.getAssetid());
                //Gán giá trị chọn vào biến session
                //WorkingContext.setDialogObjSelect(s_Data.getAssetid(), s_Data.getbChecked());
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            showErrorFromException(ex, PltShortcutMng.class.getName() + ".onCheckShortcut()");
        }
    }

    public void onCheckAllShortcut(AjaxBehaviorEvent event) {
        if(m_lstShortcut!=null){
            for (Sm_Shortcut s_Data : m_lstShortcut) {
                s_Data.setbChecked(m_bCheckAllShortcut);
            }
        }

    }

   /**
     * Hàm tiền xử lý trước khi di chuyển shortcut
     * @param bShowErr = true nếu có hiện thông báo lỗi nếu có
     * @return Chỉ số phần tử đang chọn, -1 nếu không thấy hoặc không hợp lệ, >=0 là chỉ số hiện tại
     */
    protected int moveShortcut_CurrentIndex(boolean bShowErr)
    {
        if (m_shortcutCurrent == null)
        {
            if (bShowErr)
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("SMLblNoChooseSC"));
            return -1;
        }
        //Đã test hàm dt.getSelectedRowIndexes() nhưng không ra được
//        DataTable dt=(DataTable) WorkingContext.findJSFComponent("formDlg:grdShortcut", false);
//        List<Integer> lst;
//        if (dt != null)
//            lst=dt.getSelectedRowIndexes();

        //Duyet trong mang shortcut
        for(int i=0;i < m_lstShortcut.size();i++)
        {
            if (m_lstShortcut.get(i).getShortcutid().equals(m_shortcutCurrent.getShortcutid()))
                return i;
        }

        return -1;
    }

    /**
     * Chuyển vị trí giữa 2 phần tử
     * @param iPos1 Chỉ số phần tử nguồn trong mảng Entity
     * @param iPos2 Chỉ số phần tử đích chuyển sang trong mảng Entity
     */
    protected void moveShortcut(int iPos1, int iPos2)
    {
        try
        {
            //Kiểm tra tính hợp lệ của iPos
            if (iPos1 < 0 || iPos2 < 0 || iPos1 >= m_lstShortcut.size() || iPos2 >= m_lstShortcut.size())
                return;

            ISM_BB_BLRemote ism=getISM_BB_BLRemote();
            if (ism != null)
            {
                Sm_Shortcut grp1=m_lstShortcut.get(iPos1), grp2=m_lstShortcut.get(iPos2);
                Integer iOrd1=grp2.getUrlord(), iOrd2=grp1.getUrlord(); //STT đã tráo đổi
                if (iOrd1 == null || iOrd2==null)
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eError,null,m_rfCommon.getMessage("msgMoveRecordErrorByOrdNull"));
                    return;
                }

                if (!ism.updateShortcutOrd(grp1.getShortcutid(), iOrd1))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgMoveRecordError"));
                    showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".moveShortcut()");
                }
                if (!ism.updateShortcutOrd(grp2.getShortcutid(), iOrd2))
                {
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgMoveRecordError"));
                    showError(ism.getLastActionInfo(), PltShortcutMng.class.getName() + ".moveShortcut()");
                }
                //Thành công
                //resetLayoutShortcut();
                refreshShortcut(); //Cho tạo lại DS
            }
        }
        catch(Exception e)
        {
            showErrorFromException(e, PltShortcutMng.class.getName() + ".moveShortcut()");
        }
    }

    public void onShorcut_MoveFirst(ActionEvent event) {
        int iPos1=moveShortcut_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=0;
        moveShortcut(iPos1, iPos2);
    }

    public void onShorcut_MoveUp(ActionEvent event) {
        int iPos1=moveShortcut_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=iPos1-1;

        moveShortcut(iPos1, iPos2);

    }
    public void onShorcut_MoveDown(ActionEvent event) {
        int iPos1=moveShortcut_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=iPos1+1;

        moveShortcut(iPos1, iPos2);
    }
    public void onShorcut_MoveLast(ActionEvent event) {
        int iPos1=moveShortcut_CurrentIndex(true), iPos2;
        if (iPos1 < 0)
            return;
        iPos2=m_lstShortcut.size()-1;

        moveShortcut(iPos1, iPos2);
    }

    public String getScMoveUp_Disabled()
    {
        int i;
        if (m_lstShortcut==null)
            return "true";
        i=moveShortcut_CurrentIndex(false);
        if (i<=0 || m_lstShortcut.size()==1)
            return "true";

        return "false";
    }
    public String getScMoveDown_Disabled()
    {

        int i=-1;
        try
        {
            if (m_lstShortcut==null)
                return "true";
            i=moveShortcut_CurrentIndex(false);
            if (i==-1 || i==m_lstShortcut.size()-1 || m_lstShortcut.size()==1)
                return "true";
        }
        catch (Exception e)
        {
            showErrorFromException(e,PltShortcutMng.class.getName() + ".getScMoveDown_Disabled()");
        }

        return "false";
    }

    public void onResetNewShortcut(ActionEvent ev)
    {
        Sm_Shortcut sm=new Sm_Shortcut();
        if (m_shortcutNew != null)
        {
            sm.setAppfunctionid(m_shortcutNew.getAppfunctionid());
            sm.setShortcutgrpid(m_shortcutNew.getShortcutgrpid());
            sm.setUserid(m_shortcutNew.getUserid());
        }
        else
        {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgNotEnoughDataInput"));
            return;            
        }
        m_lstFunc=null; //reset
        m_shortcutNew=sm;
        m_eActionCurrent=enumAction.eShortcutAdd;
    }

}

class Sm_ShortcutDataModel extends ListDataModel<Sm_Shortcut> implements SelectableDataModel<Sm_Shortcut> {

     public Sm_ShortcutDataModel() {
     }

     public Sm_ShortcutDataModel(List<Sm_Shortcut> data) {
         super(data);
     }

    @Override
    public String getRowKey(Sm_Shortcut t) {
        return Tools.fStandardPdataTableID(t.getShortcutid());
    }

    @Override
    public Sm_Shortcut getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<Sm_Shortcut> list = (List<Sm_Shortcut>) getWrappedData();

        for(Sm_Shortcut lst : list) {
            if(Tools.fStandardPdataTableID(lst.getShortcutid()).equals(rowKey))
                return lst;
        }
        return null;
    }

}