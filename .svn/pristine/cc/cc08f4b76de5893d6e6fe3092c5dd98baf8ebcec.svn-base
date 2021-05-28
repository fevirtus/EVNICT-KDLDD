/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.shared.system.bean.SM_HomepageViewBean;
import org.primefaces.event.CloseEvent;

@ManagedBean
@ViewScoped
public class HomeBean extends SM_HomepageViewBean implements Serializable {
    
    public HomeBean() {
        m_sHomepageID = "99";        
        initPortletArrDefault();
        
    }

    public void updatePortletArrDefault() {
        initPortletArrDefault();
    }

    protected void refreshAll() {
        //WorkingContext.redirectEAM();
        initPortletArrDefault();
    }

    public String getDlgHeaderAddnewPlt() {
        ResourcesFactory rf = new ResourcesFactory("evnit/tms/web/shared/system/prop/SM_HomepageProp");
        return rf.getMessage("MsgDlgHeaderAddNew");
    }

    /**
     * Điều khiển sự kiện close cửa sổ addnew hoặc update/di chuyển portlet
     *
     * @param ev
     */
    public void onCloseDlgFuncPlt(CloseEvent ev) {
        String s = (String) WorkingContext.getSessionValueAndRemove(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null) {
            refreshAll();
        }
    }

    public String getDlgHeaderUpdatePlt() {
        ResourcesFactory rf = new ResourcesFactory("evnit/tms/web/shared/system/prop/SM_HomepageProp");
        return rf.getMessage("MsgDlgHeaderEdit");
    }

    public void resetParamPortlet() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.renderResponse();
        WorkingContext.resetParamPortlet();
    }

}
