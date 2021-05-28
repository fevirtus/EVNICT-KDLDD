/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ejb.shared.system;

import main.ejb.shared.common.ShareInfoCommonBL;
import main.entity.shared.system.SListAll;
import main.entity.shared.system.SListFieldAll;
import main.entity.shared.system.SListGroupAll;
import main.entity.shared.system.S_List_All_Category;
import evnit.util.setting.DBSettings;
import java.util.ArrayList;
import java.util.List;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.Query;
import main.remote.shared.system.IDanhMucDungChungRemote;

/**
 *
 * @author HaNV
 */
@Stateless
//@RolesAllowed("user")
public class DanhMucDungChungBL extends ShareInfoCommonBL implements IDanhMucDungChungRemote {

    @Override
    public List<SListFieldAll> SelectListFieldALL(String strListID) {
        String queryString = "Select * from S_LIST_FIELD_ALL WHERE listid = ? ORDER BY fieldord,fielddesc";
        Query query = entityManager.createNativeQuery(queryString, SListFieldAll.class);
        query.setParameter(1, strListID);
        return query.getResultList();
    }

    @Override
    public List<SListAll> SelectListALL() {
        String queryString = "Select * from S_LIST_ALL ORDER BY GROUPLISTID,LISTDESC";
        Query query = entityManager.createNativeQuery(queryString, SListAll.class);
        return query.getResultList();

    }

    public List<SListGroupAll> SelectListGroupALL() {
        String queryString = "Select * from S_LIST_GROUP_ALL ORDER BY GROUPLISTORD";
        Query query = entityManager.createNativeQuery(queryString, SListGroupAll.class);
        return query.getResultList();
    }

    ;
    @Override
    public Boolean DeleteSListFieldALL(String nFieldID) {
        try {
            String sql;
            Query qr;
            SListFieldAll obj = (SListFieldAll) findById(nFieldID, SListFieldAll.class);
            if (obj != null) {

                sql = "delete from S_LIST_FIELD_ALL where FieldID=?";
                qr = entityManager.createNativeQuery(sql);
                qr.setParameter(1, nFieldID);
                qr.executeUpdate();
                deleteEntity(obj);
            }
            return true;
        } catch (Exception e) {
            return false;
//            e.printStackTrace();
//            throw new EJBException(e);
        }
    }

    @Override
    public Boolean CheckKeys(String ListID, String FieldCode) {
        try {
            String sql;
            Query qr;
            List<SListAll> m_lst;
            sql = "Select * from S_LIST_FIELD_ALL where FieldCode=? and ListID = ?";
            qr = entityManager.createNativeQuery(sql, SListFieldAll.class);
            qr.setParameter(1, FieldCode);
            qr.setParameter(2, ListID);
            m_lst = qr.getResultList();
            if (m_lst.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<String[]> getListGroupForSearch() {
        String queryString;
        if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
            queryString = "SELECT GROUPLISTID,ISNULL(GROUPLISTDESC,'') FROM S_LIST_GROUP_ALL a";
        } else {
            queryString = "SELECT GROUPLISTID,NVL(GROUPLISTDESC,'') FROM S_LIST_GROUP_ALL a";
        }
        Query query = entityManager.createNativeQuery(queryString);
        List<String[]> lstResult = new ArrayList<String[]>();
        List<Object[]> tempData = query.getResultList();
        for (int i = 0; i < tempData.size(); i++) {
            String[] tempDataItem = new String[2];
            tempDataItem[0] = tempData.get(i)[0].toString();
            tempDataItem[1] = tempData.get(i)[1].toString();
            lstResult.add(tempDataItem);
        }
        return lstResult;
    }

    @Override
    public List<S_List_All_Category> getLstCategory(String listID) {
        String queryString = "Select * from S_List_All_Category WHERE listid = ?";
        Query query = entityManager.createNativeQuery(queryString, S_List_All_Category.class);
        query.setParameter(1, listID);
        return query.getResultList();
    }

}
