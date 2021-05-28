/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.common;

import evnit.util.common.BaseConstant;
import evnit.util.common.Tools;
import java.io.Serializable;

/**
 * Class lưu các thông tin đang làm việc tương ứng với một phiên của người dùng
 *
 * @author thaodd
 */
public class WorkingInfo implements Serializable {

    private String orgid, siteid; //có thể là một id hoặc nhiều id cách nhau dấu , hoặc từ khóa BaseConstant.getAllKey()
    private String userid;
    //Thêm mới, truyền vào nếu muốn link theo Site, Asset, Route. Định dạng "S/A/R:('ID1','ID2',...'IDn')"
    private String listSARLinkSql;

    //Danh sách các OrgId, SiteId ngầm định được phép truy cập (null nếu orgid, siteid <> rỗng vì không cần dùng)
    //Phân tách giữa các id bởi dấu ,
    private String defOrgIdActive, defOrgIdNoActive, defSiteIdActive, defSiteIdNoActive;

    public WorkingInfo() {
    }

    @Override
    public String toString() {
        return "WorkingInfo{" + "orgid=" + orgid + "siteid=" + siteid + "userid=" + userid + '}';
    }

    public String getListSARLinkSql() {
        return listSARLinkSql;
    }

    /**
     * @param listSARLink Định dạng có nháy ở các ID
     * "S/A/R:('ID1','ID2',...'IDn')"
     */
    public void setListSARLinkSql(String listSARLink) {
        this.listSARLinkSql = listSARLink;
    }

    /**
     * @param listSARLink Định dạng không nháy ở các ID, phân tách ID bởi dấu ;.
     * Ví dụ "S/A/R:ID1;ID2;...;IDn"
     */
    public void setListSARLink(String listSARLink) {
        String s = listSARLink;
        if (listSARLink != null && !listSARLink.isEmpty() && listSARLink.length() > 3) {
            s = listSARLink.substring(0, 2) + "(" + Tools.fSQLConvertLstStrValForIn(listSARLink.substring(2, listSARLink.length()), ";") + ")";
        }
        this.listSARLinkSql = s;
    }

    public WorkingInfo(String orgid, String siteid, String userid) {
        this.orgid = orgid;
        this.siteid = siteid;
        this.userid = userid;
    }

    public String getOrgid() {
        return orgid;

    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isMultipleOrg() {
        if (orgid == null || orgid.isEmpty()) {
            return false;
        }
        String[] arr = orgid.split(",", 0);
        if (arr.length > 1) {
            return true;
        }
        return false;
    }

    /**
     * Hàm trả ra điều kiện truy vấn Org theo điều kiện biến môi trường
     *
     * @param sOrgIDFieldName Tên trường OrgID, ví dụ ORGID hoặc O.ORGID
     * @param bWithDefaultAll = true nếu có xử lý orgid là null/rỗng/all thì trả
     * ra chuỗi các org được quyền. Nếu = false thì trả ra ""
     * @param iActive 0: không active, 1 có active, 2 bỏ qua điều kiện, tất cả
     * @return mệnh đề đầy đủ đưa vào where, có thể trả về "" nếu
     * bWithDefaultAll=false và orgid không xác định Nếu orgid là null hoặc
     * emtpy hoặc từ khóa all thì ngầm định lấy tất cả đơn vị được quyền. Lúc
     * này nếu user id là null thì throw Exception Ví dụ trả về: ORGID='PL',
     * O.ORGID IN ('PL1', 'PL2') Có Exception
     */
    public String getWhereOrg(String sOrgIDFieldName, boolean bWithDefaultAll, int iActive) throws Exception {
        String sResult, s, s1;
        if (orgid == null || orgid.isEmpty() || orgid.equals(BaseConstant.getAllKey())) {
            if (bWithDefaultAll) {
                if (userid == null || userid.isEmpty()) {
                    throw new Exception("UserID is null");
                }
                if (iActive == 1) {
                    s = getDefOrgIdActive();
                } else if (iActive == 0) {
                    s = getDefOrgIdNoActive();
                } else {
                    s = getDefOrgIdActive();
                    s1 = getDefOrgIdNoActive();
                    if (s != null && !s.isEmpty() && s1 != null && !s1.isEmpty()) {
                        s += "," + s1;
                    } else {
                        s = s1;
                    }
                }
                if (s != null && !s.isEmpty()) {
                    sResult = sOrgIDFieldName + Tools.fSQLConvertIdStrForWhere(s);
                } else {
                    throw new Exception("Default Org is not inited!");
                }

                //Đoạn code cũ là câu lệnh lồng: bỏ đi hiệu năng chậm
//                sResult=sOrgIDFieldName + " IN (Select distinct org.orgid from S_ORGANIZATION org ";
//                if (iActive==0 || iActive==1)
//                    sResult +="where org.active=" + iActive + " and org.orgid in (";
//                else
//                    sResult +="where org.orgid in (";
//
//                sResult +=" Select orgid from Q_Org_Grant_User where userid = '" + userid + "'"  +
//                    " union Select sr.orgid from Q_Org_Grant_Role sr " +
//                    " inner join Q_User_Role ur on ur.enable=1 and sr.roleid = ur.roleid where ur.userid = '" + userid + "'))";
                return sResult;
            } else {
                return "";
            }
        }
        try {
            sResult = sOrgIDFieldName + Tools.fSQLConvertIdStrForWhere(orgid);
            return sResult;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Hàm trả ra điều kiện truy vấn Org theo điều kiện biến môi trường, ngầm
     * định active=1
     *
     * @param sOrgIDFieldName Tên trường OrgID, ví dụ ORGID hoặc O.ORGID
     * @param bWithDefaultAll = true nếu có xử lý orgid là null/rỗng/all thì trả
     * ra chuỗi các org được quyền. Nếu = false thì trả ra ""
     * @return mệnh đề đầy đủ đưa vào where, có thể trả về "" nếu
     * bWithDefaultAll=false và orgid không xác định Nếu orgid là null hoặc
     * emtpy hoặc từ khóa all thì ngầm định lấy tất cả đơn vị được quyền. Lúc
     * này nếu user id là null thì throw Exception Ví dụ trả về: ORGID='PL',
     * O.ORGID IN ('PL1', 'PL2') Có Exception
     */
    public String getWhereOrg(String sOrgIDFieldName, boolean bWithDefaultAll) throws Exception {
        return getWhereOrg(sOrgIDFieldName, bWithDefaultAll, 1);
    }

    /**
     * Hàm trả ra điều kiện truy vấn Site theo điều kiện biến môi trường
     *
     * @param iActive 0: không active, 1 có active, 2 bỏ qua điều kiện, tất cả
     * @param sSiteIDFieldName Tên trường OrgID, ví dụ ORGID hoặc O.ORGID
     * @param bWithDefaultAll = true nếu có xử lý siteid là null/rỗng/all thì
     * trả ra chuỗi các site được quyền. Nếu = false thì trả ra ""
     * @return mệnh đề đầy đủ đưa vào where, có thể trả về "" nếu
     * bWithDefaultAll=false và siteid không xác định Nếu siteid là null hoặc
     * emtpy hoặc từ khóa all thì ngầm định lấy tất cả site được quyền. Lúc này
     * nếu user id là null thì throw Exception Ví dụ trả về: SITEID='PL',
     * S.SITEID IN ('PL1', 'PL2') Có Exception
     */
    public String getWhereSite(String sSiteIDFieldName, boolean bWithDefaultAll, int iActive) throws Exception {
        String sResult, s, s1;
        if (siteid == null || siteid.isEmpty() || siteid.equals(BaseConstant.getAllKey())) {
            if (bWithDefaultAll) {
                if (userid == null || userid.isEmpty()) {
                    throw new Exception("UserID is null");
                }
                if (iActive == 1) {
                    s = getDefSiteIdActive();
                } else if (iActive == 0) {
                    s = getDefSiteIdNoActive();
                } else {
                    s = getDefSiteIdActive();
                    s1 = getDefSiteIdNoActive();
                    if (s != null && !s.isEmpty() && s1 != null && !s1.isEmpty()) {
                        s += "," + s1;
                    } else {
                        s = s1;
                    }
                }
                if (s != null && !s.isEmpty()) {
                    sResult = sSiteIDFieldName + Tools.fSQLConvertIdStrForWhere(s);
                } else {
                    sResult = sSiteIDFieldName + " is null"; //tạo ra điều kiện không trả về bản ghi nào được
                    //throw new Exception ("Default Site is not inited!");
                }

                //Đoạn code cũ là câu lệnh lồng: bỏ đi hiệu năng chậm
//                try {
//                    s=getWhereOrg("st.ORGID", true);
//                }
//                catch (Exception ex) {throw ex;};
//                sResult=sSiteIDFieldName + " IN (Select distinct st.SITEID from S_SITE st ";
//                if (iActive==0 || iActive==1)
//                    sResult+=" where st.active=" + iActive + " and " + s + " and st.siteid in (";
//                else
//                    sResult+=" where st.siteid in (";
//                sResult +=" Select siteid from Q_Site_Grant_User where userid = '" + userid + "'" +
//                        " union Select sr.siteid from Q_Site_Grant_Role sr" +
//                        " inner join Q_User_Role ur on ur.enable=1 and sr.roleid = ur.roleid where ur.userid = '" + userid + "'))";
                return sResult;
            }
            return "";
        }
        try {
            sResult = sSiteIDFieldName + Tools.fSQLConvertIdStrForWhere(siteid);
            return sResult;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Hàm trả ra điều kiện truy vấn Site theo điều kiện biến môi trường
     *
     * @param sSiteIDFieldName Tên trường OrgID, ví dụ ORGID hoặc O.ORGID
     * @param bWithDefaultAll = true nếu có xử lý siteid là null/rỗng/all thì
     * trả ra chuỗi các site được quyền. Nếu = false thì trả ra ""
     * @return mệnh đề đầy đủ đưa vào where, có thể trả về "" nếu
     * bWithDefaultAll=false và siteid không xác định Nếu siteid là null hoặc
     * emtpy hoặc từ khóa all thì ngầm định lấy tất cả site được quyền. Lúc này
     * nếu user id là null thì throw Exception Ví dụ trả về: SITEID='PL',
     * S.SITEID IN ('PL1', 'PL2') Có Exception
     */
    public String getWhereSite(String sSiteIDFieldName, boolean bWithDefaultAll) throws Exception {
        return getWhereSite(sSiteIDFieldName, bWithDefaultAll, 1);
    }

    /**
     * Hàm lấy ra mệnh đề tất cả các OBJID mà người dùng được quyền truy cập
     *
     * @param sUserIDCrFieldName Tên trường mã người dùng tạo đối tượng, đặt
     * bằng null nếu lấy ngầm định là user_cr_id
     * @param sObjIDFieldName Tên trường của đối tượng, ví dụ: w.worderid
     * @param sObjTypeID Mã kiểu đối tượng trong bảng S_LST_OBJ, ví dụ: worder
     * @return
     */
    public String getWhereObjRight(String sUserIDCrFieldName, String sObjIDFieldName, String sObjTypeID) {
        String s, sU, sO, sEv;
        sU = "'" + userid + "'";
        sO = "'" + Tools.fSQLStandardValue(sObjTypeID) + "'";
        sEv = "'" + BaseConstant.getEveryoneKey() + "'";
        if (sUserIDCrFieldName == null || sUserIDCrFieldName.isEmpty()) {
            sUserIDCrFieldName = "user_cr_id";
        }

        s = " (" + sUserIDCrFieldName + " = " + sU + " or " + sObjIDFieldName + " in (select objid from q_pqobj_user where objtypeid=" + sO + " and (userid=" + sEv + " or userid=" + sU + ")";
        s += " union ";
        s += " select objid from q_pqobj_role where objtypeid=" + sO;
        s += " and roleid in (select roleid from q_user_role where userid=" + sU + " and enable=1)";
        s += " union";
        s += " select objid from q_pqobj_dept where objtypeid=" + sO;
        s += " and deptid in (select deptid from q_user_dept where userid=" + sU;
        s += ")))";

        return s;
    }

    /**
     * Hàm trả ra mệnh đề liên kết Site, Asset, Route (không có where). Chỉ cần
     * dùng để nối tiếp vào mệnh đề where
     *
     * @return "": nếu không có liên kết Kết quả: ví dụ "a.assetid in
     * ('id1','id2',...'idn')"
     */
    public String getWhereSAR(String sAssetIDFieldName, String sRouteIDFieldName) {
        if (listSARLinkSql == null || listSARLinkSql.isEmpty() || listSARLinkSql.length() <= 3) {
            return "";
        }
        String sql = "";
        if (listSARLinkSql.startsWith("A:")) {
            sql += sAssetIDFieldName + " in " + listSARLinkSql.substring(2);
        } else //route
        {
            sql += sRouteIDFieldName + " in " + listSARLinkSql.substring(2);
        }
        return sql;
    }

    /**
     * Hàm trả ra câu lệnh truy vấn Org theo điều kiện biến môi trường
     *
     * @param bWithDefaultAll = true nếu có xử lý orgid là null/rỗng/all thì trả
     * ra chuỗi các org được quyền. Nếu = false thì trả ra ""
     * @return Câu lệnh đầy đủ để lấy ra được các orgid null nếu không cần truy
     * vấn, orgid đã chứa danh sách Có Exception
     */
    public String getRightOrgSql(int iActive) throws Exception {
        String sResult;
        if (orgid == null || orgid.isEmpty() || orgid.equals(BaseConstant.getAllKey())) {
            if (userid == null || userid.isEmpty()) {
                throw new Exception("UserID is null");
            }
            sResult = "Select distinct org.orgid from S_ORGANIZATION org ";
            if (iActive == 0 || iActive == 1) {
                sResult += "where org.active=" + iActive + " and org.orgid in (";
            } else {
                sResult += "where org.orgid in (";
            }

            sResult += " Select orgid from Q_Org_Grant_User where userid = '" + userid + "'"
                    + " union Select sr.orgid from Q_Org_Grant_Role sr "
                    + " inner join Q_User_Role ur on ur.enable=1 and sr.roleid = ur.roleid where ur.userid = '" + userid + "')";
            return sResult;
        }
        //Trường hợp orgid là tập các org
        return null;
    }

    /**
     * Hàm trả ra câu lệnh truy vấn Site theo điều kiện biến môi trường
     *
     * @param bWithDefaultAll = true nếu có xử lý orgid là null/rỗng/all thì trả
     * ra chuỗi các org được quyền. Nếu = false thì trả ra ""
     * @param iActive 0: không active, 1 có active, 2 bỏ qua điều kiện, tất cả
     * @return Câu lệnh đầy đủ để lấy ra được các siteid null nếu không cần truy
     * vấn, siteid đã chứa danh sách
     */
    public String getRightSiteSql(int iActive) throws Exception {
        String sResult, s;
        if (siteid == null || siteid.isEmpty() || siteid.equals(BaseConstant.getAllKey())) {
            if (userid == null || userid.isEmpty()) {
                throw new Exception("UserID is null");
            }
            try {
                s = getWhereOrg("st.ORGID", true);
            } catch (Exception ex) {
                throw ex;
            };
            sResult = "Select distinct st.SITEID from S_SITE st ";
            if (iActive == 0 || iActive == 1) {
                sResult += " where st.active=" + iActive + " and " + s + " and st.siteid in (";
            } else {
                sResult += " where st.siteid in (";
            }
            sResult += " Select siteid from Q_Site_Grant_User where userid = '" + userid + "'"
                    + " union Select sr.siteid from Q_Site_Grant_Role sr"
                    + " inner join Q_User_Role ur on ur.enable=1 and sr.roleid = ur.roleid where ur.userid = '" + userid + "')";
            if (s != null) {
                sResult += " and " + s;
            }

            return sResult;
        }
        return null;
    }

    public String getDefOrgIdActive() {
        return defOrgIdActive;
    }

    public void setDefOrgIdActive(String defOrgIdActive) {
        this.defOrgIdActive = defOrgIdActive;
    }

    public String getDefOrgIdNoActive() {
        return defOrgIdNoActive;
    }

    public void setDefOrgIdNoActive(String defOrgIdNoActive) {
        this.defOrgIdNoActive = defOrgIdNoActive;
    }

    public String getDefSiteIdActive() {
        return defSiteIdActive;
    }

    public void setDefSiteIdActive(String defSiteIdActive) {
        this.defSiteIdActive = defSiteIdActive;
    }

    public String getDefSiteIdNoActive() {
        return defSiteIdNoActive;
    }

    public void setDefSiteIdNoActive(String defSiteIdNoActive) {
        this.defSiteIdNoActive = defSiteIdNoActive;
    }

}
