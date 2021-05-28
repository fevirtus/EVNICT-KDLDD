/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class PltSettingInfo extends BasePageCommon implements Serializable {

    private List<String> selectSettingInfo = new ArrayList<String>();
    private Map<String, String> lstGroupInfo;
    private String userSettingInfo;

    public PltSettingInfo() {
        List<String> tempData = new ArrayList<String>();
        if (!isPostback()) {
            selectSettingInfo = new ArrayList<String>();
            tempData = getISystemCommonRemote().getChannelByUser(WorkingContext.getUserName());
            if (tempData != null && tempData.size() > 0 && tempData.get(0) != null) {
                userSettingInfo = tempData.get(0);
                String[] tempLstChannel = userSettingInfo.split(",");
                for (int i = 0; i < tempLstChannel.length; i++) {
                    selectSettingInfo.add(tempLstChannel[i]);
                }
            }
        }
    }

    public void onThietlap(ActionEvent event) {
        String tempChannel = null;
        if (selectSettingInfo != null && selectSettingInfo.size() > 0) {
            tempChannel="";
            for (int i = 0; i < selectSettingInfo.size(); i++) {
                if (tempChannel.equals("")) {
                    tempChannel = selectSettingInfo.get(i);
                } else {
                    tempChannel = tempChannel + "," + selectSettingInfo.get(i);
                }
            }

        }
        if (getISystemCommonRemote().updateChannelByUser(WorkingContext.getUserName(), tempChannel)) {
            userSettingInfo = tempChannel;
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Cập nhật thành công");
        } else {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Cập nhật thất bại");
        }
    }

    public List<String> getSelectSettingInfo() {
        return selectSettingInfo;
    }

    public void setSelectSettingInfo(List<String> selectSettingInfo) {
        this.selectSettingInfo = selectSettingInfo;
    }

    public Map<String, String> getLstGroupInfo() {
        return lstGroupInfo;
    }

    public void setLstGroupInfo(Map<String, String> lstGroupInfo) {
        this.lstGroupInfo = lstGroupInfo;
    }

    public String getUserSettingInfo() {
        return userSettingInfo;
    }

    public void setUserSettingInfo(String userSettingInfo) {
        this.userSettingInfo = userSettingInfo;
    }

    protected final ISystemCommonRemote getISystemCommonRemote() {
        try {
            ISystemCommonRemote ism = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            return ism;
        } catch (Exception ex) {
            showErrorFromException(ex, PltShortcutMng.class.getSimpleName());
        }
        return null;
    }
}
