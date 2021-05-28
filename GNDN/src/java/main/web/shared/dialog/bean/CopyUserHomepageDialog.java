/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.dialog.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_User;
import main.entity.shared.system.Sm_Homepage;
import main.remote.shared.admin.IAdmin;
import main.remote.shared.system.ISM_HomepageBLRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author thaodd
 */
@ManagedBean
@ViewScoped
public class CopyUserHomepageDialog extends BasePageCommon implements Serializable {
    protected List<Q_User> m_lstUser, m_lstUserAdmin;
    protected List<Sm_Homepage> m_lstHomepage;
    protected String m_userIDSource, m_userIDDest, m_homepageID;

    private static String m_cRfPath="main/web/shared/dialog/prop/DialogCopyUsrHpProp";

    //{{<editor-fold defaultstate="collapsed" desc="Các Get/set">
    public String getM_homepageID() {
        return m_homepageID;
    }

    public void setM_homepageID(String m_homepageID) {
        this.m_homepageID = m_homepageID;
    }

    public String getM_userIDDest() {
        return m_userIDDest;
    }

    public void setM_userIDDest(String m_userIDDest) {
        this.m_userIDDest = m_userIDDest;
    }

    public String getM_userIDSource() {
        return m_userIDSource;
    }

    public void setM_userIDSource(String m_userIDSource) {
        this.m_userIDSource = m_userIDSource;
    }
    //{{</editor-fold> //get/set

    /** Creates a new instance of CopyUserHomepageDialog */
    public CopyUserHomepageDialog() {
        m_userIDDest=WorkingContext.getRequestQueryString("userid");
    }

   /**
     * Hàm lấy ra danh sách các user. 
     * @return List of SelectItem đưa vào combo
     */
    public List<Q_User> getDsUser()
    {
      if (m_lstUser !=null)
            return m_lstUser;
      try
      {
           String s;
           IAdmin ad= (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());// SharedCaller.getIAdmin();
            m_lstUser=ad.getAllUserWithOrder("userid");
            if (m_lstUser==null)
            {
                s=ad.getLastActionInfo();
                showError(s, CopyUserHomepageDialog.class.getName()+ ".getDsUser()" );
            }
           return m_lstUser;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, CopyUserHomepageDialog.class.getName() + ".getDsUser()");
       }
       return null;
    }

    /**
     * Hàm lấy ra danh sách các user. Nếu user hiện tại có quyền admin thì lấy tất cả, không thì chỉ có user hiện tại
     * @return List of SelectItem đưa vào combo
     */
    public List getDsUserWithCheckAdmin()
    {
      if (m_lstUserAdmin !=null)
            return m_lstUserAdmin;
      try
       {
           String s;
           boolean b;
           IAdmin ad= (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());// SharedCaller.getIAdmin();
           s=WorkingContext.getUserName();
           if (s==null || s.isEmpty())
               return null;
           b=ad.checkUserIsAdmin(WorkingContext.getUserName());

           Q_User q;
           if (b) //is admin
           {
                m_lstUserAdmin=ad.getAllUserWithOrder("userid");
                if (m_lstUserAdmin==null)
                {
                    s=ad.getLastActionInfo();
                    showError(s, CopyUserHomepageDialog.class.getName()+ ".getDsUserWithCheckAdmin()" );
                }
           }
           else //khong phai admin
           {
                 q=ad.findUserByUserID(s);
                 m_lstUserAdmin=new ArrayList<Q_User>();
                 if (q != null)
                    m_lstUserAdmin.add(q);
           }
           return m_lstUserAdmin;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, CopyUserHomepageDialog.class.getName() + ".getDsUserWithCheckAdmin()");
       }
       return null;
    }

   /**
     * Hàm lấy ra danh sách homepage
     * @return List of SelectItem đưa vào combo
     */
    public List<Sm_Homepage> getDsHomepage()
    {
      if (m_lstHomepage !=null)
            return m_lstHomepage;
      try
      {
           ISM_HomepageBLRemote ism= (ISM_HomepageBLRemote) EjbContext.getLocalEJBRemote(ISM_HomepageBLRemote.class.getSimpleName());
           m_lstHomepage=ism.getLstHomepage();
           return m_lstHomepage;
       }
       catch (Exception ex)
       {
          //ex.printStackTrace();
          showErrorFromException(ex, CopyUserHomepageDialog.class.getName() + ".getDsHomepage()");
       }
       return null;
    }

    public void onCopy(ActionEvent ev)
    {
        try
        {
           ResourcesFactory rf=getRf(m_cRfPath);
           //Check input
           if (m_homepageID==null || m_homepageID.isEmpty())
           {
               WorkingContext.showMessages(enumMessageMode.eWarn, null, rf.getMessage("errHomepage"));
               return;
           }
           if (m_userIDSource==null || m_userIDSource.isEmpty())
           {
               WorkingContext.showMessages(enumMessageMode.eWarn, null, rf.getMessage("errUserSource"));
               return;
           }
           if (m_userIDDest==null || m_userIDDest.isEmpty())
           {
               WorkingContext.showMessages(enumMessageMode.eWarn, null, rf.getMessage("errUserDest"));
               return;
           }
           if (m_userIDDest.equals(m_userIDSource))
           {
               WorkingContext.showMessages(enumMessageMode.eWarn, null, rf.getMessage("errUserSame"));
               return;
           }
           //Gọi lênh copy
           ISM_HomepageBLRemote ism= (ISM_HomepageBLRemote) EjbContext.getLocalEJBRemote(ISM_HomepageBLRemote.class.getSimpleName());
           ism.copyUserHomepage(m_homepageID, m_userIDSource, m_userIDDest);
           String s=rf.getMessage("infSuccess");
           s=s.replaceAll("@us", "'" + m_userIDSource + "'"); s=s.replaceAll("@ud", "'" + m_userIDDest + "'");
           WorkingContext.showMessages(enumMessageMode.eInfo, null, s);
           //Ghi session để refress cha
           WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "true");
        }
        catch(Exception ex)
        {
            showErrorFromException(ex, CopyUserHomepageDialog.class.getName() + ".onCopy()");
        }
    }


}
