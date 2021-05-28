/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.admin.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_Function_Wflow;
import main.entity.shared.admin.Q_Function_Wflow_Param;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import main.remote.shared.admin.ISysFunctionFlowRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author huongnd
 */
@ManagedBean
@ViewScoped
public final class SysFunctionFlowBean extends BasePageCommon implements Serializable {

    //{{<editor-fold defaultstate="collapsed" desc="Khai báo">  
    //Cây Flow
    private TreeNode rootFlow;
    private TreeNode selectedNodeFlow;
    //Param
    private LstParamDataModel lsParam = null;
    private Q_Function_Wflow_Param selectedParam;
    private String wflowdataid;
    private String dataname;
    private String datacode;
    private String datatypeid;
    private int type;
    private String value;
    private int dataord;
    private String wflowid;
    private String funidd;

    private ISysFunctionFlowRemote m_ISysFunctionFlowRemote;
    private ISystemCommonRemote m_ISystemCommonRemote;
    private int isAddFlow;

    private boolean l_dataname;
    private boolean l_datacode;
    private boolean l_datatypeid;
    private boolean l_type;
    private boolean l_value;
    private boolean l_dataord;
    private boolean l_addparam;
    private boolean l_editparam;
    private boolean l_delparam;
    private boolean l_copyflow;
    private boolean l_delflow;

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Get Set">

    public boolean isL_copyflow() {
        return l_copyflow;
    }

    public boolean isL_delflow() {
        return l_delflow;
    }

    public boolean isL_addparam() {
        return l_addparam;
    }

    public boolean isL_editparam() {
        return l_editparam;
    }

    public boolean isL_delparam() {
        return l_delparam;
    }

    public boolean isL_dataname() {
        return l_dataname;
    }

    public boolean isL_datacode() {
        return l_datacode;
    }

    public boolean isL_datatypeid() {
        return l_datatypeid;
    }

    public boolean isL_type() {
        return l_type;
    }

    public boolean isL_value() {
        return l_value;
    }

    public boolean isL_dataord() {
        return l_dataord;
    }

    public int getIsAddFlow() {
        return isAddFlow;
    }

    public void setIsAddFlow(int isAddFlow) {
        this.isAddFlow = isAddFlow;
    }

    public String getFunidd() {
        return funidd;
    }

    public void setFunidd(String funidd) {
        this.funidd = funidd;
    }

    public TreeNode getRootFlow() {
        return rootFlow;
    }

    public void setRootFlow(TreeNode rootFlow) {
        this.rootFlow = rootFlow;
    }

    public TreeNode getSelectedNodeFlow() {
        return selectedNodeFlow;
    }

    public void setSelectedNodeFlow(TreeNode selectedNodeFlow) {
        this.selectedNodeFlow = selectedNodeFlow;
    }

    public LstParamDataModel getLsParam() {
        return lsParam;
    }

    public void setLsParam(LstParamDataModel lsParam) {
        this.lsParam = lsParam;
    }

    public Q_Function_Wflow_Param getSelectedParam() {
        return selectedParam;
    }

    public void setSelectedParam(Q_Function_Wflow_Param selectedParam) {
        this.selectedParam = selectedParam;
    }

    public String getWflowdataid() {
        return wflowdataid;
    }

    public void setWflowdataid(String wflowdataid) {
        this.wflowdataid = wflowdataid;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDatacode() {
        return datacode;
    }

    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    public String getDatatypeid() {
        return datatypeid;
    }

    public void setDatatypeid(String datatypeid) {
        this.datatypeid = datatypeid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDataord() {
        return dataord;
    }

    public void setDataord(int dataord) {
        this.dataord = dataord;
    }

    public String getWflowid() {
        return wflowid;
    }

    public void setWflowid(String wflowid) {
        this.wflowid = wflowid;
    }

    public ISysFunctionFlowRemote getISysFunctionFlowRemote() {
        if (m_ISysFunctionFlowRemote == null) {
            try {
                m_ISysFunctionFlowRemote = (ISysFunctionFlowRemote) EjbContext.getLocalEJBRemote(ISysFunctionFlowRemote.class.getSimpleName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return m_ISysFunctionFlowRemote;
    }

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
    //}}</editor-fold>

    public SysFunctionFlowBean() {
        if (!isPostback()) {
            funidd = getFunID();
            AddRootTreeFlow();
        }
    }

    private String getFunID() {
        return WorkingContext.getRequestQueryString("id");
    }

    private void LockScreen(boolean root, boolean sys) {
        l_dataname = !root;
        l_datacode = !root;
        l_dataord = !root;
        l_datatypeid = !root;
        l_type = !root;
        l_value = false;

        l_addparam = !root;
        l_editparam = false;
        l_delparam = !root;

        l_copyflow = !root;
        l_delflow = !sys;
    }

    private void AddRootTreeFlow() {
        rootFlow = new DefaultTreeNode("root", null);
        List<Q_Function_Wflow> lsRootFlow = getISysFunctionFlowRemote().getFlowByFunctionID(funidd);
        for (int i = 0; i < lsRootFlow.size(); i++) {
            TreeNode nodeChild = new DefaultTreeNode(lsRootFlow.get(i), rootFlow);
            if (selectedNodeFlow == null && i == 0) {
                nodeChild.setExpanded(true);
                selectedNodeFlow = nodeChild;
                nodeChild.setSelected(true);
                AddChildNodeFlow(nodeChild);
                getLsParam(lsRootFlow.get(i).getWflowid());
            } else {
                nodeChild.setExpanded(false);
                if (lsRootFlow.get(i).getNote().equals("1")) {
                    new DefaultTreeNode("a", nodeChild);
                }
            }
        }
    }

    private void AddChildNodeFlow(TreeNode node) {
        node.getChildren().clear();
        List<Q_Function_Wflow> listChild = getISysFunctionFlowRemote().getFlowByFlowParentID(((Q_Function_Wflow) node.getData()).getWflowid());
        for (int i = 0; i < listChild.size(); i++) {
            TreeNode nodeChild = new DefaultTreeNode(listChild.get(i), node);
            nodeChild.setExpanded(false);
            if (listChild.get(i).getNote().equals("1")) {
                new DefaultTreeNode("a", nodeChild);
            }
        }
    }

    public void onNodeFlowExpand(NodeExpandEvent event) {
        AddChildNodeFlow(event.getTreeNode());
    }

    public void onNodeFlowSelect(NodeSelectEvent event) {
        SelectNodeFlow(event.getTreeNode());
    }

    private void SelectNodeFlow(TreeNode n) {
        if (!n.isExpanded()) {
            AddChildNodeFlow(n);
            n.setExpanded(true);
        }
        Q_Function_Wflow flowNode = (Q_Function_Wflow) n.getData();
        getLsParam(((Q_Function_Wflow) n.getData()).getWflowid());
        LockScreen(flowNode.getWflowidParent() == null || flowNode.getWflowidParent().equals(""), !flowNode.isSys());
    }

    private void getLsParam(String flowID) {
        List<Q_Function_Wflow_Param> ls = new ArrayList<>();
        if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
            ls = getISysFunctionFlowRemote().getParamByFlowID(flowID);
        }
        if (ls.size() > 0) {
            if (!ls.contains(selectedParam)) {
                selectedParam = ls.get(0);
            }
            EntityToScreen(selectedParam);
        } else {
            selectedParam = null;
            CleanScreen();
        }
        lsParam = new LstParamDataModel(ls);
    }

    public void onRowParamSelect(SelectEvent event) {
        EntityToScreen(selectedParam);
    }

    private void EntityToScreen(Q_Function_Wflow_Param p) {
        wflowdataid = p.getWflowdataid();
        dataname = p.getDataname();
        datacode = p.getDatacode();
        datatypeid = p.getDatatypeid();
        type = p.getType();
        value = p.getValue();
        dataord = p.getDataord();
        wflowid = p.getWflowid().getWflowid();
    }

    private Q_Function_Wflow_Param ScreenToEntity() {
        Q_Function_Wflow_Param p = new Q_Function_Wflow_Param();
        p.setWflowid((Q_Function_Wflow) selectedNodeFlow.getData());
        p.setWflowdataid(wflowdataid);
        p.setDatacode(datacode);
        p.setDataname(dataname);
        p.setDataord(dataord);
        p.setDatatypeid(datatypeid);
        p.setType(type);
        p.setValue(value);
        return p;
    }

    private void CleanScreen() {
        wflowdataid = "";
        dataname = "";
        datacode = "";
        datatypeid = "NUM";
        type = 0;
        value = "";
        dataord = 0;
        wflowid = "";
    }

    public void btnAddNewParamClick(ActionEvent evt) {
        if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
            CleanScreen();
            wflowid = ((Q_Function_Wflow) selectedNodeFlow.getData()).getWflowid();
        } else {
            WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn chưa chọn một Luồng công việc nào!!!");
        }
    }

    public void btnSaveNewParamClick(ActionEvent evt) {
        Q_Function_Wflow_Param p = ScreenToEntity();
        if (p.getWflowdataid() == null || p.getWflowdataid().equals("")) {
            S_Key_ControlInfo controlInfo = new S_Key_ControlInfo(null, Q_Function_Wflow_Param.class.getSimpleName());
            controlInfo = getISystemCommonRemote().getGenKeyID(controlInfo);
            p.setWflowdataid(controlInfo.getOutputValue());

            List<Q_Function_Wflow> lsFl = getISysFunctionFlowRemote().getFlowByFlowParentID(p.getWflowid().getWflowid());
            if (lsFl.size() > 0) {
                List<Q_Function_Wflow_Param> lsPR = new ArrayList<>();
                S_Key_ControlInfo controlInfo1 = new S_Key_ControlInfo(null, Q_Function_Wflow_Param.class.getSimpleName());
                for (Q_Function_Wflow _fl : lsFl) {
                    Q_Function_Wflow_Param x = ScreenToEntity();
                    x.setWflowdataidParent(p.getWflowdataid());
                    x.setWflowdataid(getISystemCommonRemote().getGenKeyID(controlInfo1).getOutputValue());
                    x.setWflowid(_fl);
                    lsPR.add(x);
                }
                if (getISysFunctionFlowRemote().insertLstParam(lsPR)) {
                    if (getISysFunctionFlowRemote().insertParam(p)) {
                        WorkingContext.showMessages(enumMessageMode.eInfo, null, "Thêm mới thành công!");
                        getLsParam(p.getWflowid().getWflowid());
                    } else {
                        getISysFunctionFlowRemote().deleteLsParamByParamParent(p.getWflowdataid());
                        WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
                    }
                } else {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, "Có lỗi xảy ra AddNewParam!");
                }
            } else {
                if (getISysFunctionFlowRemote().insertParam(p)) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, "Thêm mới thành công!");
                    getLsParam(p.getWflowid().getWflowid());
                } else {
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
                }
            }
        } else {
            if (getISysFunctionFlowRemote().updateParam(p)) {
                WorkingContext.showMessages(enumMessageMode.eInfo, null, "Cập nhật thành công!");
                lsParam.getRowData(p.getWflowdataid()).setDatacode(p.getDatacode());
                lsParam.getRowData(p.getWflowdataid()).setDataname(p.getDataname());
                lsParam.getRowData(p.getWflowdataid()).setDataord(p.getDataord());
                lsParam.getRowData(p.getWflowdataid()).setDatatypeid(p.getDatatypeid());
                lsParam.getRowData(p.getWflowdataid()).setType(p.getType());
                lsParam.getRowData(p.getWflowdataid()).setValue(p.getValue());
            } else {
                WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
            }
        }
        List<Q_Function_Wflow_Param> ls = getISysFunctionFlowRemote().getParamByFlowID(wflowid);
        WorkingContext.reloadFunctionParam();
    }

    public void btnDelNewParamClick(ActionEvent evt) {
        Q_Function_Wflow_Param p = ScreenToEntity();
        if (getISysFunctionFlowRemote().deleteLsParamByParamParent(p.getWflowdataid())) {
            getISysFunctionFlowRemote().deleteParam(p);
            WorkingContext.showMessages(enumMessageMode.eInfo, null, "Xóa thành công!");
            getLsParam(p.getWflowid().getWflowid());
        } else {
            WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
        }
    }

    public void btnCopyFlow(ActionEvent evt) {
        try {
            if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
                isAddFlow = 0;
//                RequestContext contextr = RequestContext.getCurrentInstance();
//                contextr.addCallbackParam("Method", "CopyFlow");
//                contextr.addCallbackParam("funid", funidd);
//                contextr.addCallbackParam("id", ((Q_Function_Wflow) selectedNodeFlow.getData()).getWflowid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "CopyFlow");
                PrimeFaces.current().ajax().addCallbackParam("funid", funidd);
                PrimeFaces.current().ajax().addCallbackParam("id", ((Q_Function_Wflow) selectedNodeFlow.getData()).getWflowid());
                
            } else {
                WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn cần chọn một Flow!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void btnAddFlow(ActionEvent evt) {
        isAddFlow = 1;
//        RequestContext contextr = RequestContext.getCurrentInstance();
//        contextr.addCallbackParam("Method", "AddFlow");
//        contextr.addCallbackParam("funid", funidd);
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "AddFlow");
        PrimeFaces.current().ajax().addCallbackParam("funid", funidd);
    }

    public void btnEditFlow(ActionEvent evt) {
        if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
            isAddFlow = 0;
//            RequestContext contextr = RequestContext.getCurrentInstance();
//            contextr.addCallbackParam("Method", "EditFlow");
//            contextr.addCallbackParam("funid", funidd);
//            contextr.addCallbackParam("id", ((Q_Function_Wflow) selectedNodeFlow.getData()).getWflowid());
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "EditFlow");
            PrimeFaces.current().ajax().addCallbackParam("funid", funidd);
            PrimeFaces.current().ajax().addCallbackParam("Method", ((Q_Function_Wflow) selectedNodeFlow.getData()).getWflowid());
        } else {
            WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn cần chọn một Flow!");
        }
    }

    public void btnDelFlow(ActionEvent evt) {
        if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
            Q_Function_Wflow q = (Q_Function_Wflow) selectedNodeFlow.getData();
            if (getISysFunctionFlowRemote().deleteFlow(q)) {
                TreeNode node = selectedNodeFlow.getParent();
                node.getChildren().remove(selectedNodeFlow);
                node.getChildren().get(0).setSelected(true);
                selectedNodeFlow = node.getChildren().get(0);
                SelectNodeFlow(selectedNodeFlow);
            }
        } else {
            WorkingContext.showMessages(enumMessageMode.eWarn, null, "Bạn cần chọn một Flow!");
        }
    }

    public void onCloseDetailFlow(CloseEvent event) {
        if (isAddFlow == 0) {
            if (selectedNodeFlow != null && selectedNodeFlow.getData() != null) {
                Q_Function_Wflow q = (Q_Function_Wflow) selectedNodeFlow.getData();
                q = (Q_Function_Wflow) getISystemCommonRemote().findById(q.getWflowid(), Q_Function_Wflow.class);
                ((DefaultTreeNode) selectedNodeFlow).setData(q);
                AddChildNodeFlow(selectedNodeFlow);
            }
        } else {
            AddRootTreeFlow();
        }
    }
}

class LstParamDataModel extends ListDataModel<Q_Function_Wflow_Param> implements SelectableDataModel<Q_Function_Wflow_Param> {

    public LstParamDataModel() {
    }

    public LstParamDataModel(List<Q_Function_Wflow_Param> data) {
        super(data);
    }

    @Override
    public String getRowKey(Q_Function_Wflow_Param t) {
        return Tools.fStandardPdataTableID(t.getWflowdataid());
    }

    @Override
    public Q_Function_Wflow_Param getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<Q_Function_Wflow_Param> list = (List<Q_Function_Wflow_Param>) getWrappedData();

        for (Q_Function_Wflow_Param lst : list) {
            if (Tools.fStandardPdataTableID(lst.getWflowdataid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }

    public Q_Function_Wflow_Param getRowData(int index) {
        List<Q_Function_Wflow_Param> list = (List<Q_Function_Wflow_Param>) getWrappedData();
        if (list.size() > index) {
            return list.get(index);
        } else {
            return null;
        }
    }
}
