/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_Function;
import main.entity.shared.attach.Af_Other;
import main.entity.shared.system.*;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.BasePortletBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import main.remote.shared.system.ISM_BB_BLRemote;
import main.web.upload.Download;
import main.web.upload.UploadUtils;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

/**
 * Bean cho các portlet thuộc nhóm SM, BB
 *
 * @author thaodd
 */
@ManagedBean
@ViewScoped
public class PltSM_BB_Bean extends BasePortletBean implements Serializable {

    private ResourcesFactory m_rf;
    public List<Sm_Shortcut_Plt> m_pscDsShortcut;
    private List<Af_Other> m_AfOther;
    private StreamedContent fileSelected;
    private String hfObjTypeId;
    private String hfObjId;
    private InputStream stream;

    /**
     * Creates a new instance of PltSM_BB_Bean
     */
    public PltSM_BB_Bean() {
        m_rf = new ResourcesFactory("main/web/shared/system/prop/SM_BB_Prop");
        khoitao("pltBB");
        hfObjId = "";
        hfObjTypeId = "BBMSG";
    }

    public String getAppid() {
        return WorkingContext.getAppKeyCurrent();
    }

    public String getAffParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("afId");
    }

    public void FileDownloadView() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String affileid = "";
        affileid = getAffParam(fc);
        Af_Other tempLib = null;
        String app = "video";
        //edit by thinhnd
        SerializableSupplier<InputStream> temp_stream = new SerializableSupplier<InputStream>() {
            @Override
            public InputStream get() {
                return stream;
            }
        };
        try {

            tempLib = (Af_Other) getISM_HomepageBLRemote().findById(affileid, Af_Other.class);
            //Tải file về server
            Download download = new Download();

            String[] strTemp = tempLib.getFilename().split("\\.");
            if (strTemp.length > 1) {
                app = strTemp[strTemp.length - 1];
            }
            if (strTemp.length > 1) {
                app = strTemp[strTemp.length - 1];
            }
            app = app.toLowerCase();
            if (app.equals("pdf") || app.equals("gif") || app.equals("jpeg") || app.equals("jpg") || app.equals("png")) {
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "ViewFile");
//                context.addCallbackParam("bid", new String(Base64.encodeBase64("exWorkBeanMng".getBytes())));
//                context.addCallbackParam("pfid", new String(Base64.encodeBase64(affileid.getBytes())));
//                context.addCallbackParam("fid", new String(Base64.encodeBase64(app.getBytes())));
//                context.addCallbackParam("nid", new String(Base64.encodeBase64(tempLib.getFilename().getBytes())));

                PrimeFaces.current().ajax().addCallbackParam("Method", "ViewFile");
                PrimeFaces.current().ajax().addCallbackParam("bid", new String(Base64.encodeBase64("exWorkBeanMng".getBytes())));
                PrimeFaces.current().ajax().addCallbackParam("pfid", new String(Base64.encodeBase64(affileid.getBytes())));
                PrimeFaces.current().ajax().addCallbackParam("fid", new String(Base64.encodeBase64(app.getBytes())));
                PrimeFaces.current().ajax().addCallbackParam("nid", new String(Base64.encodeBase64(tempLib.getFilename().getBytes())));

                WorkingContext.removeBeanSessionObject("s_Content_ViewBean");
                return;
            } else {
                stream = download.downloadAf_Other(affileid, UploadUtils.getServerPath());
            }
        } catch (Exception ex) {

        } finally {
            if (stream == null) {
                byte[] bytes = "error file".getBytes();
                stream = new ByteArrayInputStream(bytes);
                fileSelected = new DefaultStreamedContent();
            } else {
                if (app.equals("pdf") || app.equals("gif") || app.equals("jpeg") || app.equals("jpg") || app.equals("png")) {
                } else {
                    fileSelected = DefaultStreamedContent.builder().name(tempLib.getFilename()).contentType("application/" + app).stream(temp_stream).build();
                    //RequestContext context = RequestContext.getCurrentInstance();
                    //context.addCallbackParam("Method", "DownFile");

                    PrimeFaces.current().ajax().addCallbackParam("Method", "DownFile");
                }

            }
        }
    }

    //{{<editor-fold defaultstate="collapsed" desc="Portlet Shortcut">
    /**
     * Hàm xây dựng kết quả đầu ra cho web để hiển thị lên web. Chuyển đổi từ
     * list shortcut thành list cho hiển thị lên web của portlet
     *
     * @param lstShortcut Đầu vào
     * @param lstResult Đầu ra. Mỗi phần tử Shortcut sẽ được xây dựng thành một
     * kết quả đầu ra
     */
    protected void buildDsShortcut_Link(List<Sm_Shortcut> lstShortcut, List<Sm_Shortcut_Plt> lstResult) {
        if (lstShortcut == null) {
            return;
        }
        Sm_Shortcut sc;
        Sm_Shortcut_Plt scr;
        String s;
        //Bổ sung các shortcut trong lst
        for (int i = 0; i < lstShortcut.size(); i++) {
            sc = lstShortcut.get(i);
            //Nếu là chức  năng thì kiểm tra quyền
            if (sc.getFunctionid() != null) {
                continue;
            }
            scr = new Sm_Shortcut_Plt();
            if (sc.getqfunction() != null) {
                if (sc.getqfunction().getIcon() != null) {
                    sc.setIcon(sc.getqfunction().getIcon());
                }
                if (sc.getqfunction().getUrl() != null) {
                    sc.setUrl(sc.getqfunction().getUrl());
                }
            } else if (sc.getUrl() != null) //Chỉ là url
            {
                s = "<img src='" + WorkingContext.getFullUrl("/images/Func/slink.png") + "' />";
                scr.setHtml_img(s);
            }
            if (sc.getIcon() != null) {
                s = "<span class=\"ui-icon " + sc.getIcon() + "\"></span>";
                scr.setHtml_img(s);
            }
            if (sc.getUrl() != null) {
                s = "<a href='" + WorkingContext.getFullUrl(sc.getUrl()) + "'";
                if (sc.getOnnewwindow() != null && sc.getOnnewwindow()) {
                    s += " target='_blank'";
                }
                s += ">";
                s += sc.getShortcutdesc() + "</a>";
                scr.setHtml_link(s);
            } else {
                scr.setHtml_link(sc.getShortcutdesc());
            }

            lstResult.add(scr);
        }
    }

    /**
     * Thêm phần tử nhóm vào danh sách dưới dạng hiển thị cho web
     *
     * @param groupDesc Đầu vào, mô tả nhóm
     * @param lstResult Đầu ra, thêm vào danh sách
     */
    protected void buildDsShortcut_Group(String groupDesc, List<Sm_Shortcut_Plt> lstResult) {
        Sm_Shortcut_Plt scr = new Sm_Shortcut_Plt();
        String s = "<img src='" + WorkingContext.getFullUrl("/images/Func/group.png") + "' />";
        scr.setHtml_img(s);
        scr.setHtml_link("<b>" + groupDesc + "</b>");
        lstResult.add(scr);
    }

    public List<Sm_Shortcut_Plt> getDsShortcut() {
        if (m_pscDsShortcut != null) {
            return m_pscDsShortcut;
        }
        try {
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            if (smbb != null) {
                List<Sm_Shortcutgroup> lstGroup;
                List<Sm_Shortcut> lstShortcut;
                List<Sm_Shortcut_Plt> lstResult = new ArrayList<Sm_Shortcut_Plt>();
                String sUserID = WorkingContext.getUserName(), sAppID = WorkingContext.getAppKeyCurrent(), s;

                Sm_Shortcut_Plt scr;
                s = WorkingContext.getAppKeyCurrent();
                if (s != null && s.equals(BaseConstant.AppKey_EAM)) {
                    //Bổ sung shortcut mở cửa sổ addnew portlet
                    scr = new Sm_Shortcut_Plt();
                    s = "<img src='" + WorkingContext.getFullUrl("images/addwindow.png") + "' />";
                    scr.setHtml_img(s);
                    s = "<a href='javascript:void(0);' onclick = 'showDlgAddnewPortlet();return false;'>";
                    s += "<b>" + m_rf.getMessage("SMBtnAddPortlet") + "</b></a>";
                    scr.setHtml_link(s);
                    lstResult.add(scr);
                }
                //Lấy các shortcut có group null
                lstShortcut = smbb.getLstShortcutByGroup(null, sUserID, sAppID);
                if (lstShortcut.isEmpty() && s != null && s.equals(BaseConstant.AppKey_ETM)) { //Khởi tạo các short cut ngầm định
                    initShortcutForEtm(sUserID);
                    lstShortcut = smbb.getLstShortcutByGroup(null, sUserID, sAppID);
                }
                buildDsShortcut_Link(lstShortcut, lstResult);
                //Lấy các shortcut có group khác null
                lstGroup = smbb.getLstShortcutGroup(sUserID, sAppID);
                if (lstGroup != null) {
                    for (int i = 0; i < lstGroup.size(); i++) {
                        Sm_Shortcutgroup grp = lstGroup.get(i);
                        lstShortcut = smbb.getLstShortcutByGroup(grp.getShortcutgrpid(), sUserID, sAppID);
                        if (lstShortcut != null && lstShortcut.size() > 0) {
                            buildDsShortcut_Group(grp.getShortcutgrpdesc(), lstResult);
                            buildDsShortcut_Link(lstShortcut, lstResult);
                        }
                    }
                }

                if (s != null && s.equals(BaseConstant.AppKey_ETM)) {
                    List<Sm_Shortcut_Plt> lstDT = getLastDutoanShortcut();
                    if (lstDT != null) {
                        lstResult.addAll(lstDT);
                    }
                }

                m_pscDsShortcut = lstResult;
                return m_pscDsShortcut;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public void onpscCloseDlgMan(CloseEvent ev) {
        String s;
        s = (String) WorkingContext.getSessionValue(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null && s.equalsIgnoreCase("true")) {
            m_pscDsShortcut = null;
        }
    }

    /**
     * Hàm khởi tạo các truy cập nhanh cho user khi mới đăng nhập vào chương
     * trình dự toán
     */
    private void initShortcutForEtm(String sUserID) {
        try {
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());

            //Lấy danh sách các function
            String s = "select * from Q_FUNCTION where functionid in ('05.01.01','05.02.01','05.03.01') order by functionid";
            List<Q_Function> lstF = iscr.getResultFromSql(s, Q_Function.class);
            for (Q_Function qf : lstF) {
                Sm_Shortcut sc = new Sm_Shortcut();
                //Sinh key
                S_Key_ControlInfo kc = new S_Key_ControlInfo(null, Sm_Shortcut.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {

                    return;
                }
                sc.setShortcutid(kc.getOutputValue());
                if (sc.getUrlord() == null) //Cập nhật STT nếu chưa nhập
                {
                    sc.setUrlord(kc.getSvalue());
                }
                sc.setUserid(sUserID);
                sc.setAppfunctionid(BaseConstant.AppKey_ETM);
                sc.setFunctionid(qf.getFunctionid());
                sc.setShortcutdesc(qf.getFunctionname());
                //Ghi vào CSDL
                smbb.insert(sc);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Lấy danh sách các mã dự toán được mở gần nhất
     *
     * @return null nếu chưa có
     */
    private String[] getMaDTListLastUsed() {
        try {
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            String[] arr;
            String s = "select madtlst from SM_SHORTCUT_DT where userid='" + WorkingContext.getUserName() + "'";
            List<Object> lst = iscr.getResultFromSql(s);
            if (lst.isEmpty()) {
                return null;
            }

            s = (String) lst.get(0);
            arr = s.split("\\|");
            return arr;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Hàm lấy danh sách shorcut dự toán mở gần nhất
     */
    private List<Sm_Shortcut_Plt> getLastDutoanShortcut() {
        //Test
        //addShortcutDutoanOpen("1001", "Dự toán xây dựng lâu đài Alessandro đại đế");
        //addShortcutDutoanOpen("1002", "Dự toán 2sdfjsdklfjsd sdfjsdkfjsd sdflsdfkj sdfjsdklfsd dflksdjfsdf aaaa");
        //addShortcutDutoanOpen("1003", "Dự toán 3");
        //addShortcutDutoanOpen("1004", "Dự toán 4");
        ////////////////////

        List<Sm_Shortcut_Plt> lst = null;
        String[] arr = getMaDTListLastUsed(), arr2;
        String s;
        if (arr == null) {
            return null;
        }
        lst = new ArrayList<Sm_Shortcut_Plt>();
        Sm_Shortcut_Plt scr;
        //Bổ sung shortcut group
        scr = new Sm_Shortcut_Plt();
        //s = "<img src='" + WorkingContext.getFullUrl("/images/Func/group.png") + "' />";
        //scr.setHtml_img(s);
        scr.setHtml_link("<br/><b>" + m_rf.getMessage("SMLblDutoanLast") + "</b>");
        lst.add(scr);

        for (int i = 0; i < arr.length; i++) {
            scr = new Sm_Shortcut_Plt();
            if (Tools.fStrIsNullOrEmpty(arr[i])) {
                continue;
            }
            s = "<img src='" + WorkingContext.getFullUrl("/images/Func/dtlast.png") + "' />";
            scr.setHtml_img(s);
            arr2 = arr[i].split(",");
            s = "<a href='" + WorkingContext.getFullUrl("/etm/dtc/dutoan.jsf") + "?MaDT=" + arr2[0] + "'>";
            if (arr2.length > 1) {
                s += arr2[1];
            }
            s += "</a>";
            scr.setHtml_link(s);
            lst.add(scr);
        }
        return lst;

    }

    //}}</editor-fold>
//{{<editor-fold defaultstate="collapsed" desc="Portlet BulletinBoard: PBB">
    private Bb_Message_Ext m_pbbCurrentBB;
    List<Bb_Message_Ext> m_pbbLstBB;

    public Bb_Message_Ext getM_pbbCurrentBB() {
        return m_pbbCurrentBB;
    }

    public void setM_pbbCurrentBB(Bb_Message_Ext m_currentBB) {
        this.m_pbbCurrentBB = m_currentBB;
    }

    public List<Bb_Message_Ext> getDsBBRcv() {
        if (m_pbbLstBB != null) {
            return m_pbbLstBB;
        }
        try {
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            if (smbb != null) {
                m_pbbLstBB = smbb.getLstBBByUserRcv(WorkingContext.getUserName(), "BBValid");
                if (m_pbbLstBB == null) {

                }
                return m_pbbLstBB;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public String getpbbRecordCountBB() {
        String s;
        s = "Tổng số: ";
        if (m_pbbLstBB == null) {
            s += "0";
        } else {
            s += m_pbbLstBB.size();
        }

        return s;
    }

    public String getpbbViewAttach() {
        if (m_pbbCurrentBB == null) {
            return "";
        }
        String s = m_pbbCurrentBB.getAttachCount().toString();
        return s;
    }

    public void onpbbGrdRowSelect(SelectEvent ev) {
        try {
            //Ghi nhật ký đọc
            Bb_Message_Ext pb = (Bb_Message_Ext) ev.getObject();
            //if (m_pbbCurrentBB == null)
            if (pb == null) {
                return;
            }
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            iscr.fLogObj_Write(new Log_ObjreadPK(pb.getBbmid(), "BBMSG", WorkingContext.getUserName()));
            hfObjTypeId = "BBMSG";
            hfObjId = pb.getBbmid();

            try {
                m_AfOther = getISM_HomepageBLRemote().getAfileByBBMSGID(pb.getBbmid());
            } catch (Exception ex) {
                //Logger.getLogger(ExWorkBeanMng.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Ghi session để refresh cha
            WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
        } catch (Exception ex) {

        }
    }

    public void onpbbGrdRowUnSelect(SelectEvent ev) {
        try {
            m_pbbCurrentBB = null;
            //Ghi nhật ký đọc
//            if (m_pbbCurrentBB == null)
//                return;
//            ISystemCommonRemote iscr=(ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
//            iscr.fLogObj_Write(new Log_ObjreadPK(m_pbbCurrentBB.getBbmid(),"BBMSG",WorkingContext.getUserName()));
        } catch (Exception ex) {

        }
    }

    public void onpbbCloseDlgMan(CloseEvent ev) {
        String s;
        s = (String) WorkingContext.getSessionValue(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null && s.equalsIgnoreCase("true")) {
            m_pbbLstBB = null;
        }
    }

    public void onpbbShowDlgAttach(ActionEvent ev) {
        WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, BaseConstant.getObjRightReadonly());
    }

    public void onShowDetail(Bb_Message_Ext id) {
        try {
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            m_pbbCurrentBB = id;
            if (m_pbbCurrentBB == null) {
                return;
            }
            iscr.fLogObj_Write(new Log_ObjreadPK(m_pbbCurrentBB.getBbmid(), "BBMSG", WorkingContext.getUserName()));

            //Ghi session để refresh cha
            WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
        } catch (Exception ex) {
            Logger.getLogger(PltSM_BB_Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ondlgBBDetail(CloseEvent ev) {
        String s = (String) WorkingContext.getSessionValueAndRemove(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null && !s.isEmpty()) {
            m_pbbLstBB = null;
        }
        m_pbbCurrentBB = null;
    }
    private PltSM_BB_DataModel pltSM_BB_DataModel;

    public PltSM_BB_DataModel getPltSM_BB_DataModel() {
        if (m_pbbLstBB != null && pltSM_BB_DataModel != null) {
            return pltSM_BB_DataModel;
        }

        getDsBBRcv();
        if (m_pbbLstBB != null) {
            pltSM_BB_DataModel = new PltSM_BB_DataModel(m_pbbLstBB);
            return pltSM_BB_DataModel;
        }
        return null;
    }
//}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Portlet Inbox">
    private List<Sm_Inbox> m_pibLstIB;
    private String m_pibFilterVal = "true";
    private Sm_Inbox m_pibCurrentIB;
    private boolean m_pibCheckAll;

    public String getHfObjTypeId() {
        return hfObjTypeId;
    }

    public void setHfObjTypeId(String hfObjTypeId) {
        this.hfObjTypeId = hfObjTypeId;
    }

    public String getHfObjId() {
        return hfObjId;
    }

    public void setHfObjId(String hfObjId) {
        this.hfObjId = hfObjId;
    }

    public List<Af_Other> getM_AfOther() {
        return m_AfOther;
    }

    public void setM_AfOther(List<Af_Other> m_AfOther) {
        this.m_AfOther = m_AfOther;
    }

    public StreamedContent getFileSelected() {
        return fileSelected;
    }

    public void setFileSelected(StreamedContent fileSelected) {
        this.fileSelected = fileSelected;
    }

    public boolean isRenderEAMDesc() {
        String s = WorkingContext.getAppKeyCurrent();
        if (s != null && s.equals(BaseConstant.AppKey_EAM)) {
            return true;
        }
        return false;
    }

    public boolean isM_pibCheckAll() {
        return m_pibCheckAll;
    }

    public void setM_pibCheckAll(boolean m_pibCheckAll) {
        this.m_pibCheckAll = m_pibCheckAll;
    }

    public Sm_Inbox getM_pibCurrentIB() {
        return m_pibCurrentIB;
    }

    public void setM_pibCurrentIB(Sm_Inbox m_pibCurrentIB) {
        this.m_pibCurrentIB = m_pibCurrentIB;
    }

    public String getM_pibFilterVal() {
        return m_pibFilterVal;
    }

    public void setM_pibFilterVal(String m_pibFilterVal) {
        this.m_pibFilterVal = m_pibFilterVal;
    }

    public List<Sm_Inbox> getpibDsIB() {
        if (m_pibLstIB != null) {
            return m_pibLstIB;
        }

        try {
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            m_pibLstIB = smbb.getLstInbox(WorkingContext.getUserName(), WorkingContext.getAppKeyCurrent(), Boolean.valueOf(m_pibFilterVal));
            if (m_pibLstIB == null) {

            }
            return m_pibLstIB;
        } catch (Exception ex) {

        }
        return null;
    }

    public String getpibRecordCountIB() {
        List lst = getpibDsIB();
        String s;
        s = "Tổng số bản ghi: ";
        if (lst == null) {
            s += "0";
        } else {
            s += lst.size();
        }
        return s;
    }

    public void onpibGrdRowSelect(SelectEvent ev) {
        try {
            m_pibCurrentIB = (Sm_Inbox) ev.getObject();
            //Ghi nhật ký đọc
            if (m_pibCurrentIB == null) {
                return;
            }
            m_pibCurrentIB.setReaddtime(new Date());
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            smbb.update(m_pibCurrentIB);
        } catch (Exception ex) {

        }
    }

    public void onpibCheckAll(AjaxBehaviorEvent event) {
        //public void onpibCheckAll() {
        if (m_pibLstIB != null) {
            for (Sm_Inbox s_Data : m_pibLstIB) {
                s_Data.setbChecked(m_pibCheckAll);
            }
        }

    }

//    public void onpibCheckOne() {
    public void onpibCheckOne(AjaxBehaviorEvent event) {
        try {
            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
            //dtData = (DataTable)selectBoolean.getParent().getParent();
            //Lấy vị trí
            String temps[] = selectBoolean.getClientId().split("\\:");
            if (temps.length > 2) {
                int rowIndex = Integer.valueOf(temps[2]);

                Sm_Inbox s_Data = m_pibLstIB.get(rowIndex); // (A_Asset) dtData.getRowData();
                s_Data.setbChecked(!s_Data.getbChecked());
            }
        } catch (Exception ex) {
            //ex.printStackTrace();

        }
    }

    public void onpibCmdHidden(ActionEvent ev) {
        try {
            int iCount = 0;
            if (m_pibLstIB != null) {
                ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
                for (Sm_Inbox s_Data : m_pibLstIB) {
                    if (s_Data.getbChecked() && (s_Data.getHidden() == null || !s_Data.getHidden())) {
                        s_Data.setHidden(true);
                        if (smbb.update(s_Data)) {
                            iCount++;
                        } else {

                            return;
                        }
                    }
                }
            }
            if (iCount > 0) {
                WorkingContext.showMessages(enumMessageMode.eInfo, null, "Lưu dữ liệu thành công");
                onpibChangeFilter(); //refresh
            } else {
                WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn chưa chọn dữ liệu");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();

        }
    }

    public void onpibCmdVisible(ActionEvent ev) {
        try {
            int iCount = 0;
            if (m_pibLstIB != null) {
                ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
                for (Sm_Inbox s_Data : m_pibLstIB) {
                    if (s_Data.getbChecked() && (s_Data.getHidden() == null || s_Data.getHidden())) {
                        s_Data.setHidden(false);
                        if (smbb.update(s_Data)) {
                            iCount++;
                        } else {

                            return;
                        }
                    }
                }
            }
            if (iCount > 0) {
                WorkingContext.showMessages(enumMessageMode.eInfo, null, "Lưu thành công");
                onpibChangeFilter(); //refresh
            } else {
                WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn chưa chọn dữ liệu");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();

        }
    }

    public void onpibCmdDelete(ActionEvent ev) {
        try {
            int iCount = 0;
            if (m_pibLstIB != null) {
                ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
                for (Sm_Inbox s_Data : m_pibLstIB) {
                    if (s_Data.getbChecked()) {
                        if (smbb.delete(s_Data.getMsgid(), Sm_Inbox.class)) {
                            iCount++;
                        } else {

                            return;
                        }
                    }
                }
            }
            if (iCount > 0) {

                onpibChangeFilter(); //refresh
            } else {

            }
        } catch (Exception ex) {
            //ex.printStackTrace();

        }

    }

    public void onpibChangeFilter() {
        m_pibLstIB = null; //refresh
    }

    /**
     * Hàm tạo tin nhắn gửi cho người dùng
     *
     * @param s_Data Khởi tạo các trường dữ liệu
     * s_Data.setAppfunctionid(WorkingContext.getAppKeyCurrent());
     * s_Data.setSubject("Tin test"); s_Data.setContent("Chi tiết tin nhắn
     * test!"); s_Data.setUserid("admin");
     * @return true: thành công, false: không cần ghi (do cấu hình) hoặc có lỗi
     * (đã có hiện message lỗi trong hàm luôn)
     */
    public static boolean createInboxMsg(Sm_Inbox s_Data) {
        try {
            if (s_Data == null) {
                return false;
            }
            //Sinh key
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            //Kiểm tra trường hợp khi có cho phép tạo msg hay không
            S_Common co = (S_Common) iscr.findById("CRTINBOXMSGW", S_Common.class);
            if (co == null || co.getIntval() == null || co.getIntval() == 0) {
                return false;
            }

            S_Key_ControlInfo kc = new S_Key_ControlInfo(null, Sm_Inbox.class.getSimpleName());
            kc = iscr.getGenKeyID(kc);
            if (kc == null || kc.getGenStatus() != 0) //Không thành công
            {
                BasePageCommon commmonPage = new BasePageCommon();
                commmonPage.showErrorFromGenKey(kc, PltSM_BB_Bean.class.getSimpleName());
                return false;
            }
            s_Data.setMsgid(kc.getOutputValue());
            s_Data.setSenddtime(new Date());
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            if (smbb.insert(s_Data)) {
                ResourcesFactory rf = new ResourcesFactory(ResourcesFactory.RF_PATH_COMMONPROP);
                //WorkingContext.showMessages(enumMessageMode.eInfo, null, rf.getMessage("msgAddNewSuccess"));
                return true;
            } else {
                WorkingContext.showMessages(enumMessageMode.eWarn, null, smbb.getLastActionInfo());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void onpibCmdTestCreateMsg(ActionEvent ev) {
        try {
            Sm_Inbox s_Data = new Sm_Inbox();
            //Sinh key
            //Sinh key
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            S_Key_ControlInfo kc = new S_Key_ControlInfo(null, Sm_Inbox.class.getSimpleName());
            kc = iscr.getGenKeyID(kc);
            if (kc == null || kc.getGenStatus() != 0) //Không thành công
            {

                return;
            }
            s_Data.setMsgid(kc.getOutputValue());
            s_Data.setAppfunctionid(WorkingContext.getAppKeyCurrent());
            s_Data.setContent("Chi tiết tin nhắn test!");
            s_Data.setSenddtime(new Date());
            s_Data.setSubject("Tin test");
            s_Data.setUserid("admin");
            ISM_BB_BLRemote smbb = (ISM_BB_BLRemote) EjbContext.getLocalEJBRemote(ISM_BB_BLRemote.class.getSimpleName());
            if (smbb.insert(s_Data)) {

                m_pibFilterVal = "true";
                onpibChangeFilter();
            } else {

            }
        } catch (Exception ex) {
            //ex.printStackTrace();

        }
    }
    private PltInboxDataModel pltInboxDataModel;

    public PltInboxDataModel getPltInboxDataModell() {
        getpibDsIB();
        pltInboxDataModel = new PltInboxDataModel(m_pibLstIB);
        return pltInboxDataModel;
    }
    //}}</editor-fold>
}
