/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ejb.shared.system;

import main.entity.shared.system.Sm_Shortcut;
import main.entity.shared.system.Sm_Shortcutgroup;
import main.entity.shared.system.Bb_Message_Rcv_Ext;
import main.entity.shared.system.Bb_Message_Ext;
import main.entity.shared.system.Sm_Inbox;
import main.entity.shared.system.Bb_Message;
import main.entity.shared.system.Bb_Message_Rcv;
import main.ejb.shared.common.ShareInfoCommonBL;
import main.remote.shared.admin.IAdmin;

import evnit.util.calendar.Day;
import evnit.util.common.BaseConstant;
import evnit.util.common.Tools;
import evnit.util.setting.DBSettings;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;
import main.remote.shared.system.ISM_BB_BLRemote;

/**
 * Lớp thao tác với các bảng SM_..., BB_...
 *
 * @author thaodd
 */
@Stateless
//@RolesAllowed("user")
public class SM_BB_BL extends ShareInfoCommonBL implements ISM_BB_BLRemote {

    @EJB
    private IAdmin m_ia;
    //{{<editor-fold defaultstate="collapsed" desc="Xử lý shortcut, Group">

    public List<Sm_Shortcutgroup> getLstShortcutGroup(String sUserID, String sAppID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            sql = "select dm from Sm_Shortcutgroup dm where dm.userid=:userid and dm.appfunctionid=:appid";
            sql += " order by dm.shortcutgrpord";
            qr = entityManager.createQuery(sql);
            qr.setParameter("userid", sUserID);
            qr.setParameter("appid", sAppID);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;

    }

    public List<Sm_Shortcut> getLstShortcutByGroup(String sShortcutGrpID, String sUserID, String sAppID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            sql = "select dm from Sm_Shortcut dm where";
            if (sShortcutGrpID != null && !sShortcutGrpID.isEmpty()) {
                sql += " dm.shortcutgrpid=:grpid and";
            } else {
                sql += " dm.shortcutgrpid is null and";
            }
            sql += " dm.userid=:userid and dm.appfunctionid=:appid";
            sql += " order by dm.urlord";
            qr = entityManager.createQuery(sql);
            if (sShortcutGrpID != null && !sShortcutGrpID.isEmpty()) {
                qr.setParameter("grpid", sShortcutGrpID);
            }
            qr.setParameter("userid", sUserID);
            qr.setParameter("appid", sAppID);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    public Boolean deleteShortcutGroup(String sShortcutGrpID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            Sm_Shortcutgroup grp = (Sm_Shortcutgroup) findById(sShortcutGrpID, Sm_Shortcutgroup.class);
            if (grp != null) {
                //Xoa cac shortcut thuoc group
                sql = "delete from Sm_Shortcut dm where dm.shortcutgrpid=:grpid";
                qr = entityManager.createQuery(sql);
                qr.setParameter("grpid", sShortcutGrpID);
                qr.executeUpdate();

                //Xoa group
                deleteEntity(grp);
            }
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    /**
     * Hàm cập nhật STT của ShortcutGroup
     *
     * @param sGrpID Mã
     * @param iOrd Số thứ tự mới
     * @return
     */
    public Boolean updateShortcutGrpOrd(String sGrpID, int iOrd) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            sql = "update Sm_Shortcutgroup dm set dm.shortcutgrpord=:iord where dm.shortcutgrpid=:grpid";
            qr = entityManager.createQuery(sql);
            qr.setParameter("grpid", sGrpID);
            qr.setParameter("iord", iOrd);
            int i = qr.executeUpdate();

            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    /**
     * Hàm cập nhật STT của Shortcut
     *
     * @param sGrpID Mã
     * @param iOrd Số thứ tự mới
     * @return
     */
    public Boolean updateShortcutOrd(String sID, int iOrd) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            sql = "update Sm_Shortcut dm set dm.urlord=:iord where dm.shortcutid=:id";
            qr = entityManager.createQuery(sql);
            qr.setParameter("id", sID);
            qr.setParameter("iord", iOrd);
            int i = qr.executeUpdate();

            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    public List<Sm_Shortcut> getLstShortcutByUser(String sUserID, String sAppID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
//            sql="select dm from Sm_Shortcut dm left join dm.smShortcutgroup where";
//            sql += " dm.userid=:userid and dm.appfunctionid=:appid ";
//            sql += " order by dm.smShortcutgroup.shortcutgrpord, dm.urlord";
//            qr=entityManager.createQuery(sql);
//            qr.setParameter("userid", sUserID);
//            qr.setParameter("appid", sAppID);

            sql = "select s.* from sm_shortcut s left join sm_shortcutgroup g on s.shortcutgrpid = g.shortcutgrpid";
            sql += " where s.userid=?1 and s.appfunctionid=?2";
            sql += " order by g.shortcutgrpord, s.urlord";
            qr = entityManager.createNativeQuery(sql, Sm_Shortcut.class);
            qr.setParameter(1, sUserID);
            qr.setParameter(2, sAppID);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

     /**
     * Hàm kiểm tra một shortcut trỏ tới một function đã tồn tại hay chưa
     * @param sUserID Mã người dùng mà shortcut gắn vào
     * @param sAppID Mã ứng dụng (phân hệ) hiển thị shortcut
     * @param sFuncID Mã chức năng gắn vào
     * @return
     */
    public boolean checkFuncShortcutExist(String sUserID, String sAppID, String sFuncID) {
        try {
            String sql;
            Query qr;
            sql = "select count(*) from SM_SHORTCUT where userid=?1 and AppFunctionid=?2 and functionid=?3";
            qr = entityManager.createNativeQuery(sql);
            qr.setParameter(1, sUserID);
            qr.setParameter(2, sAppID);
            qr.setParameter(3, sFuncID);
            Object oResult=qr.getSingleResult();
            if (oResult !=null && oResult.toString().equals("0"))
                return false;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new EJBException(e);
        }
        return true;

    }

    /**
     * Hàm thêm một dự toán vừa mở vào danh sách các dự toán mới mở gần đây để tạo shortcut
     */
    public void addShortcutDutoanOpen(String sMaDT, String sTenDT, String sUserID) {
        try {
            String sql,s;
            String[] arr,arr2;
            HashMap hmMaDT;
            Query qr;
            sql = "select madtlst from SM_SHORTCUT_DT where userid='" + Tools.fSQLStandardValue(sUserID) + "'";
            qr = entityManager.createNativeQuery(sql);
            List<Object> lst = qr.getResultList();
            //Chuẩn lại mã, tên dự toán
            if (sMaDT.indexOf("\\,") >= 0 || sMaDT.indexOf("\\|") >=0) //MaDT chứa ký tự điều khiển ,| thì không xử lý
                 return;
            sTenDT=sTenDT.replaceAll("\\,", "").replaceAll("\\|", "");
            if (lst.isEmpty())
            {
                s=sMaDT + "," + sTenDT;
                sql = "insert into SM_SHORTCUT_DT(USERID,MADTLST,LASTUPDATED) values (?1,?2,?3)";
                qr=entityManager.createNativeQuery(sql);
                qr.setParameter(1, sUserID);
                qr.setParameter(2, s);
                qr.setParameter(3, new Date());
                qr.executeUpdate();
                return;
            }

            s=(String)lst.get(0);
            arr=s.split("\\|");
            int iSize=10; //Định kích cỡ của số bản ghi mã dự toán làm việc
            s=sMaDT + "," + sTenDT;
            iSize--;
            hmMaDT=new HashMap(); //Danh sách mã dự toán đang mở
            hmMaDT.put(sMaDT, sMaDT);
            if (arr != null) {
                for(int i=0;i<arr.length;i++)
                {
                    if (iSize <= 0)
                        break;
                    if (Tools.fStrIsNullOrEmpty(arr[i]))
                        continue;
                    arr2=arr[i].split("\\,");
                    if (arr2.length < 2)
                        continue;
                    if (!hmMaDT.containsKey(arr2[0])) //Thêm bản ghi nếu danh sách chưa chứa mã DT này
                    {
                        hmMaDT.put(arr2[0],arr2[0]);
                        s += "|" + arr2[0] + "," + arr2[1];
                        iSize--;
                    }
                }
            }
            sql = "update SM_SHORTCUT_DT set MADTLST=?2,LASTUPDATED=?3 where userid=?1";
            qr=entityManager.createNativeQuery(sql);
            qr.setParameter(1, sUserID);
            qr.setParameter(2, s);
            qr.setParameter(3, new Date());
            qr.executeUpdate();

        } catch (Exception e) {
            //e.printStackTrace();
            throw new EJBException(e);
        }
    }

    /**
     * Hàm cập nhật thông tin shortcut khi xóa, sửa dự toán
     * @param sMethod: nhận giá trị "d" nếu là vừa xóa dự toán, "u" nếu là có sửa tên dự toán
     * @param sTenDT: truyền vào nếu là update, không thì để null
     */
    public void delupShortcutDutoanOpen(String sMaDT, String sMethod, String sTenDT, String sUserID) {
        try {
            String sql,s;
            String[] arr,arr2;
            boolean bUpdate=false;
            Query qr;
            sql = "select madtlst from SM_SHORTCUT_DT where userid='" + Tools.fSQLStandardValue(sUserID) + "'";
            qr = entityManager.createNativeQuery(sql);
            List<Object> lst = qr.getResultList();
            if (lst.isEmpty())
                return;

            s=(String)lst.get(0);
            arr=s.split("\\|");
            s="";
            if (arr != null) {
                for(int i=0;i<arr.length;i++)
                {
                    if (Tools.fStrIsNullOrEmpty(arr[i]))
                        continue;
                    arr2=arr[i].split("\\,");
                    if (arr2.length < 2)
                        continue;
                    if (arr2[0].equals(sMaDT))
                    {
                        bUpdate=true;
                        if (sMethod.equalsIgnoreCase("d"))
                            continue;
                        else //update
                            arr2[1]=sTenDT;
                    }
                    if (!s.isEmpty())
                        s+="|";
                    s += arr2[0] + "," + arr2[1];
                }
            }
            if (bUpdate) {
                sql = "update SM_SHORTCUT_DT set MADTLST=?2,LASTUPDATED=?3 where userid=?1";
                qr=entityManager.createNativeQuery(sql);
                qr.setParameter(1, sUserID);
                qr.setParameter(2, s);
                qr.setParameter(3, new Date());
                qr.executeUpdate();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            throw new EJBException(e);
        }
    }
    //{{</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Xử lý Bulletin Board">
    /**
     * Hàm lấy danh sách các thông báo chung
     *
     * @param sUserID_CR Mã nguời dùng tạo thông báo, = null nếu lấy cho tất cả
     * người dùng
     * @param sValid Xác định thời hạn hiệu lực của thông báo, truyền vào:
     * BBValid, BBInvalid, BBFuture, BBAll
     * @return List<Bb_Message_Ext>
     */
    @Override
    public List<Bb_Message_Ext> getLstBB(String sUserID_CR, String sValid) {
        setLastActionInfo("");
        try {
            String sql, sWhere = "";
            Query qr;
            sql = "SELECT M.*, (SELECT COUNT(*) FROM AF_OTHER A WHERE A.OBJID=M.BBMID AND OBJTYPEID='BBMSG') ATTACHCOUNT";
            sql += " FROM BB_MESSAGE M";
            if (sUserID_CR == null || sValid == null) {
                return null;
            }

            if (!sUserID_CR.equals(BaseConstant.getAllKey())) {
                sWhere = " M.USER_CR_ID=?1";
            }

            if (sValid.equals("BBValid")) //Con hieu luc
            {
                if (!sWhere.isEmpty()) {
                    sWhere += " AND";
                }
                sWhere += " (EDFROM IS NULL OR EDFROM <= ?2) AND (EDTO IS NULL OR EDTO >= ?2)";
            } else if (sValid.equals("BBInvalid")) //Het hieu luc
            {
                if (!sWhere.isEmpty()) {
                    sWhere += " AND";
                }
                sWhere += " EDTO < ?2";
            } else if (sValid.equals("BBFuture")) //Chua hieu luc (tuong lai)
            {
                if (!sWhere.isEmpty()) {
                    sWhere += " AND";
                }
                sWhere += " EDFROM > ?2";
            } //Khong thi la tat ca

            if (!sWhere.isEmpty()) {
                sql += " WHERE " + sWhere;
            }
            sql += " ORDER BY M.USER_CR_DTIME DESC";

            qr = entityManager.createNativeQuery(sql, Bb_Message_Ext.class);
            qr.setParameter(1, sUserID_CR);
            Date dNow = new Day().getDate();
            qr.setParameter(2, dNow);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    /**
     * Hàm lấy danh sách các kiểu đối tượng nhận, kèm theo số lượng bản ghi đối
     * tượng tương ứng thông báo
     *
     * @param sBBID Mã thông báo cần lấy số lượng đối tượng
     * @return Mảng các bản ghi B.BBRCVTYPEID,B.BBRCVTYPEDESC, COUNTREC
     */
    public List getLstReceiverTypeBB(String sBBID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            if (sBBID != null && !sBBID.isEmpty()) {
                sql = "SELECT B.BBRCVTYPEID,B.BBRCVTYPEDESC, (SELECT COUNT(*) FROM BB_MESSAGE_RCV WHERE BBMID=?1 AND BBRCVTYPEID=B.BBRCVTYPEID) COUNTREC";
            } else {
                sql = "SELECT B.BBRCVTYPEID,B.BBRCVTYPEDESC, 0 COUNTREC";
            }
            sql += " FROM BB_LST_RECEIVERTYPE B ORDER BY B.BBRCVTYPEORD";

            qr = entityManager.createNativeQuery(sql);
            qr.setParameter(1, sBBID);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    /**
     * Hàm lấy danh sách các đối tượng nhận của một thông báo
     *
     * @param sBBID Mã thông báo
     * @param sObjTypeID Kiểu đối tượng cần lấy: BYUSER, BYDEPT, BYROLE
     * @return List<Bb_Message_RcvPK> kết quả, null nếu có lỗi
     */
    public List<Bb_Message_Rcv_Ext> getLstRcvObj(String sBBID, String sObjTypeID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            if (sObjTypeID.equals("BYUSER")) {
                sql = "SELECT DM.*, U.USERNAME OBJDESC";
                sql += " FROM BB_MESSAGE_RCV DM INNER JOIN Q_USER U ON DM.RCVID = U.USERID";
                sql += " WHERE BBMID=?1 AND BBRCVTYPEID=?2";
            } else if (sObjTypeID.equals("BYDEPT")) {
                sql = "SELECT DM.*, D.DEPTDESC OBJDESC";
                sql += " FROM BB_MESSAGE_RCV DM INNER JOIN S_DEPT D ON DM.RCVID = D.DEPTID";
                sql += " WHERE BBMID=?1 AND BBRCVTYPEID=?2";
            } else if (sObjTypeID.equals("BYROLE")) {
                sql = "SELECT DM.*, R.ROLEDESC OBJDESC";
                sql += " FROM BB_MESSAGE_RCV DM INNER JOIN Q_ROLE R ON DM.RCVID = R.ROLEID";
                sql += " WHERE BBMID=?1 AND BBRCVTYPEID=?2";
            } else {
                return null;
            }

            qr = entityManager.createNativeQuery(sql, Bb_Message_Rcv_Ext.class);
            qr.setParameter(1, sBBID);
            qr.setParameter(2, sObjTypeID);
            List lst = qr.getResultList();
            return lst;
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    @Override
    public boolean saveObjRcvList(List<Bb_Message_Rcv> lstAdd, List<Bb_Message_Rcv> lstRemove) {
        setLastActionInfo("");
        //EntityTransaction trans= entityManager.getTransaction();
        try {
            //trans.begin();
            //Thêm đối tượng
            if (lstAdd != null) {
                for (Bb_Message_Rcv b : lstAdd) {
                    insert(b);
                }
            }

            //Xóa đối tượng
            if (lstRemove != null) {
                for (Bb_Message_Rcv b : lstRemove) {
                    delete(b.getId(), Bb_Message_Rcv.class);
                }
            }
            //trans.commit();
            return true;
        } catch (Exception e) {
            //trans.getRollbackOnly();
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    public boolean setVisibleBB(String sBBID, boolean bVisible) {
        setLastActionInfo("");
        try {
            Bb_Message Bb = (Bb_Message) findById(sBBID, Bb_Message.class);
            if (Bb == null) {
                setLastActionInfo(BaseConstant.getActionInfoObjNotExist() + " (ID=" + sBBID + ")");
                return false;
            }
            Bb.setEnable(bVisible);
            return true;
        } catch (Exception e) {
            //trans.getRollbackOnly();
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    /**
     * Hàm ghi ngày hiệu lực bắt đầu, kết thúc của một thông báo
     *
     * @param sBBID Mã thông báo
     * @param bEDFrom =true nếu có ghi ngày bắt đầu, <> không ghi ngày bắt đầu
     * @param dEDFrom
     * @param bEDTo = true nếu có ghi ngày kết thúc, <> không ghi ngày kết thúc
     * @param dEDTo
     * @return true nếu thành công, false nếu có lỗi
     */
    public boolean setEffDayBB(String sBBID, Boolean bEDFrom, Date dEDFrom, Boolean bEDTo, Date dEDTo) {
        setLastActionInfo("");
        try {
            Bb_Message Bb = (Bb_Message) findById(sBBID, Bb_Message.class);
            if (Bb == null) {
                setLastActionInfo(BaseConstant.getActionInfoObjNotExist() + " (ID=" + sBBID + ")");
                return false;
            }
            if (bEDFrom) {
                Bb.setEdfrom(dEDFrom);
            }
            if (bEDTo) {
                Bb.setEdto(dEDTo);
            }
            return true;
        } catch (Exception e) {
            //trans.getRollbackOnly();
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return false;
    }

    /**
     * Hàm lấy danh sách các thông báo được nhận bởi user
     *
     * @param sUserID_RCV Mã người dùng nhận thông báo
     * @param sValid sValid Xác định thời hạn hiệu lực của thông báo, truyền
     * vào: BBValid, BBInvalid
     * @return
     */
    @Override
    public List<Bb_Message_Ext> getLstBBByUserRcv(String sUserID_Rcv, String sValid) {
        setLastActionInfo("");
        try {
            String sql, sWhere = "";
            Query qr;
            sql = "SELECT M.*, (SELECT COUNT(*) FROM AF_OTHER A WHERE A.OBJID=M.BBMID AND OBJTYPEID='BBMSG') ATTACHCOUNT, L.READDTIME, L.ISUPDATE";
            sql += " FROM BB_MESSAGE M";
            sql += " LEFT JOIN LOG_OBJREAD L ON M.BBMID=L.OBJID AND L.OBJTYPEID='BBMSG' AND L.USERID=?1";

            if (sUserID_Rcv == null || sValid == null) {
                return null;
            }

            sWhere = "M.ENABLE=1";
            if (sValid.equals("BBValid")) //Con hieu luc
            {
                if (!sWhere.isEmpty()) {
                    sWhere += " AND";
                }
                sWhere += " (EDFROM IS NULL OR EDFROM <= ?2) AND (EDTO IS NULL OR EDTO >= ?2)";
            } else if (sValid.equals("BBInvalid")) //Het hieu luc
            {
                if (!sWhere.isEmpty()) {
                    sWhere += " AND";
                }
                sWhere += " EDTO < ?2";
            } else {
                return null;
            }

            //Điều kiện gửi cho người dùng nhận
            sWhere += " AND (M.USER_CR_ID=?1 OR M.USER_MDF_ID=?1 OR M.ISPUBLIC=1 OR M.BBMID IN (";
            sWhere += "SELECT BBMID FROM BB_MESSAGE_RCV WHERE BBRCVTYPEID='BYUSER' AND RCVID=?1";
            sWhere += " UNION";
            sWhere += " SELECT BBMID FROM BB_MESSAGE_RCV WHERE BBRCVTYPEID='BYROLE' AND RCVID IN (SELECT ROLEID FROM Q_USER_ROLE WHERE USERID=?1 AND Enable=1)";
            sWhere += " UNION";
            sWhere += " SELECT BBMID FROM BB_MESSAGE_RCV WHERE BBRCVTYPEID='BYDEPT' AND RCVID IN (SELECT DEPTID FROM Q_USER_DEPT WHERE USERID=?1)";
            sWhere += "))";


            if (!sWhere.isEmpty()) {
                sql += " WHERE " + sWhere;
            }
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.Oracle) {
                sql += " ORDER BY NVL(USER_MDF_DTIME, USER_CR_DTIME) DESC";
            } else {
                sql += " ORDER BY ISNULL(USER_MDF_DTIME, USER_CR_DTIME) DESC";
            }

            qr = entityManager.createNativeQuery(sql, Bb_Message_Ext.class);
            qr.setParameter(1, sUserID_Rcv);
            Date dNow = new Day().getDate();
            qr.setParameter(2, dNow);
            return qr.getResultList();
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    /**
     * Thủ tục đếm số lượng người dùng nhận thông báo
     *
     * @param sBBMID Mã thông báo
     * @return null nếu lỗi, <> trả về tổng số lượng người nhận (tính user có
     * enable=1)
     */
    @Override
    public Integer getRcvUserCount(String sBBMID) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            Bb_Message bb = (Bb_Message) findById(sBBMID, Bb_Message.class);
            if (bb == null) {
                setLastActionInfo(BaseConstant.getActionInfoObjNotExist());
                return null;
            }

            if (bb.getIspublic() != null && bb.getIspublic()) //Thông báo cho tất cả người dùng
            {
                sql = "SELECT COUNT(*) FROM Q_USER WHERE ENABLE=1";
            } else {
                sql = "SELECT COUNT(*) FROM Q_USER WHERE ENABLE=1 AND USERID IN (";
                sql += "SELECT RCVID USERID FROM BB_MESSAGE_RCV WHERE BBRCVTYPEID='BYUSER' AND BBMID = ?1";
                sql += " UNION";
                sql += " SELECT D.USERID FROM Q_USER_DEPT D INNER JOIN BB_MESSAGE_RCV M ON D.DEPTID=M.RCVID WHERE M.BBRCVTYPEID='BYDEPT' AND BBMID = ?1";
                sql += " UNION";
                sql += " SELECT R.USERID FROM Q_USER_ROLE R INNER JOIN BB_MESSAGE_RCV M ON R.ROLEID=M.RCVID WHERE M.BBRCVTYPEID='BYROLE' AND BBMID = ?1";
                sql += ")";
            }
            qr = entityManager.createNativeQuery(sql);
            qr.setParameter(1, sBBMID);
            return Integer.parseInt(qr.getSingleResult().toString());
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Xử lý Inbox">
    @Override
    public List<Sm_Inbox> getLstInbox(String sUserID, String sAppID, boolean bVisible) {
        setLastActionInfo("");
        try {
            String sql;
            Query qr;
            List<Sm_Inbox> lst;
            sql = "SELECT dm FROM Sm_Inbox dm WHERE dm.userid=:userid AND (dm.appfunctionid is null or dm.appfunctionid=:appid)";
            if (bVisible) {
                sql += " AND (dm.hidden is null or dm.hidden = false)";
            } else {
                sql += " AND (dm.hidden = true)";
            }
            sql += " ORDER BY dm.senddtime DESC";

            qr = entityManager.createQuery(sql);
            qr.setParameter("userid", sUserID);
            qr.setParameter("appid", sAppID);
            lst = qr.getResultList();
            return lst;
        } catch (Exception e) {
            //e.printStackTrace();
            setLastActionInfoFromException(e);
        }
        return null;
    }

    
    //}}</editor-fold>
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
