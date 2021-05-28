/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.remote.shared.system;

import main.entity.shared.common.WorkingInfo;
import main.entity.shared.system.Bb_Message_Ext;
import main.entity.shared.system.Bb_Message_Rcv;
import main.entity.shared.system.Bb_Message_Rcv_Ext;
import main.entity.shared.system.Plt_SystemMessage;
import main.entity.shared.system.Sm_Inbox;
import main.entity.shared.system.Sm_Shortcut;
import main.entity.shared.system.Sm_Shortcutgroup;
import main.remote.shared.common.ICommon;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author thaodd
 */
@Local
public interface ISM_BB_BLRemote extends ICommon {

    List<Sm_Shortcutgroup> getLstShortcutGroup(String sUserID, String sAppID);

    List<Sm_Shortcut> getLstShortcutByGroup(String sShortcutGrpID, String sUserID, String sAppID);

    Boolean deleteShortcutGroup(String sShortcutGrpID);

    Boolean updateShortcutGrpOrd(String sGrpID, int iOrd);
    Boolean updateShortcutOrd(String sID, int iOrd);

    List<Sm_Shortcut> getLstShortcutByUser(String sUserID, String sAppID);

     /**
     * Hàm kiểm tra một shortcut trỏ tới một function đã tồn tại hay chưa
     * @param sUserID Mã người dùng mà shortcut gắn vào
     * @param sAppID Mã ứng dụng (phân hệ) hiển thị shortcut
     * @param sFuncID Mã chức năng gắn vào
     * @return
     */
    public boolean checkFuncShortcutExist(String sUserID, String sAppID, String sFuncID);

    List<Bb_Message_Ext> getLstBB(String sUserID_CR, String sValid);

    List getLstReceiverTypeBB(String sBBID);

    List<Bb_Message_Rcv_Ext> getLstRcvObj(String sBBID, String sObjTypeID);

    boolean saveObjRcvList(List<Bb_Message_Rcv> lstAdd, List<Bb_Message_Rcv> lstRemove);

    boolean setVisibleBB(String sBBID, boolean bVisible);

    boolean setEffDayBB(String sBBID,Boolean bEDFrom, Date dEDFrom, Boolean bEDTo, Date dEDTo);

    public List<Bb_Message_Ext> getLstBBByUserRcv(String sUserID_Rcv, String sValid);

    Integer getRcvUserCount(String sBBMID);

    List<Sm_Inbox> getLstInbox(String sUserID, String sAppID, boolean bVisible);

    /**
     * Hàm thêm một dự toán vừa mở vào danh sách các dự toán mới mở gần đây để tạo shortcut
     */
    public void addShortcutDutoanOpen(String sMaDT, String sTenDT, String sUserID);

    /**
     * Hàm cập nhật thông tin shortcut khi xóa, sửa dự toán
     * @param sMethod: nhận giá trị "d" nếu là vừa xóa dự toán, "u" nếu là có sửa tên dự toán
     * @param sTenDT: truyền vào nếu là update, không thì để null
     */
    public void delupShortcutDutoanOpen(String sMaDT, String sMethod, String sTenDT, String sUserID);
    
}
