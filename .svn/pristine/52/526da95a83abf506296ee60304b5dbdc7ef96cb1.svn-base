/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.admin.bean;

import main.entity.shared.admin.Q_User;
import main.remote.shared.admin.IAdmin;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author NAMCV
 */
@ManagedBean
@ViewScoped
public class GrantReportRightCopyBean extends BasePageCommon
        implements Serializable {

    private String userCopy;
    private String userCopyName;
    private List<Q_User> selectedUsers;


    public GrantReportRightCopyBean() {
        userCopy = WorkingContext.getRequestQueryString("userCopy");
        Q_User user = new Q_User();
        try {
            //user = (Q_User) getIMaintPlanRemote().findById(userCopy, Q_User.class);
            if (user != null) {
                userCopyName = user.getUsername();
            }
            /*Load danh sách User*/
            //selectedUsers = getIWorderRemote().getAllUsers();

        } catch (Exception ex) {
            Logger.getLogger(GrantReportRightCopyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onSave(ActionEvent ev) {
        try {
            Object[] users = selectedUsers.toArray();
            for (int i = 0; i < users.length; i++) {
                Q_User user = (Q_User) users[i];
                //getIWorderRemote().processCopyGrantRightReport(userCopy, user.getUserid());
            }

            WorkingContext.showMessages(enumMessageMode.eInfo, null, "Bạn đã ghi thành công!");

        } catch (Exception ex) {
            showErrorFromException(ex, GrantReportRightCopyBean.class.getName() + ".onSave()");
        }

    }

    public List<Q_User> completeUsers(String query) throws Exception {
        //List<Q_User> allUsers = getIWorderRemote().getAllUsers();
        IAdmin ia = getIAdmin();
        List<Q_User> allUsers = ia.getAllUser(WorkingContext.getUserName(), WorkingContext.getOrganizationCurrent());
        List<Q_User> filteredUsers = new ArrayList<Q_User>();

        for (int i = 0; i < allUsers.size(); i++) {
            Q_User skin = allUsers.get(i);
            if (skin.getUserid().toLowerCase().startsWith(query)) {
                filteredUsers.add(skin);
            }
        }

        return filteredUsers;
    }

    public void handleSelect(SelectEvent e) {
        WorkingContext.showMessages(enumMessageMode.eInfo, null, "AAAAAAAAAAAAAa!");
    }
    

    public String getUserCopy() {
        return userCopy;
    }

    public void setUserCopy(String userCopy) {
        this.userCopy = userCopy;
    }

    public String getUserCopyName() {
        return userCopyName;
    }

    public void setUserCopyName(String userCopyName) {
        this.userCopyName = userCopyName;
    }

    public List<Q_User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<Q_User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

}
