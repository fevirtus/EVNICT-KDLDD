/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.ejb.shared.system;

import main.ejb.shared.common.ShareInfoCommonBL;
import main.entity.shared.system.S_Organization;

import java.util.List;
import javax.ejb.EJBException;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import main.entity.shared.system.S_OrganizationView;
import main.remote.shared.system.IChonDonViRemote;






/**
 *
 * @author ChuotCong
 */
@Stateless
//@RolesAllowed("user")
public class ChonDonViBL extends ShareInfoCommonBL implements IChonDonViRemote{

    
    @Override
    public List<S_OrganizationView> getAllOrganizationByParent(String orgidParent)
    {
        try {
            String queryString = "PKG_ASSET_NEW.getAllOrganizationByParent";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_OrganizationView.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);            
            query.registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, orgidParent);            
            List<S_OrganizationView> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }
    @Override
    public List<S_OrganizationView> getAllOrganization(String orgid)
    {
        try {
            String queryString = "PKG_ASSET_NEW.getAllOrganization";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_OrganizationView.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);            
            query.registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, orgid);            
            List<S_OrganizationView> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }
    @Override
    public List<S_Organization> SelectOrg(String strMaDVQLCapTren) {
        String queryString = "Select * from dbo.fn_DONVI_QUANLY(?)";
        Query query = entityManager.createNativeQuery(queryString, S_Organization.class);
        query.setParameter(1, strMaDVQLCapTren);
        return query.getResultList();
    }
    

    @Override
    public List<S_Organization> SelectOrgShort(String UserId) {
        String queryString = "SELECT *\n" +
        "FROM   S_ORGANIZATION  so\n" +
        "WHERE  SO.ORGID IN (SELECT DISTINCT org.ORGID\n" +
        "                        FROM   S_ORGANIZATION org\n" +
        "                        WHERE  org.active = 1\n" +
        "                               AND org.orgid IN (SELECT orgid\n" +
        "                                                 FROM   Q_Org_Grant_User\n" +
        "                                                 WHERE  userid = ? \n" +
        "                                                UNION SELECT sr.orgid\n" +
        "                                                      FROM   Q_Org_Grant_Role \n" +
        "                                                             sr\n" +
        "                                                             INNER JOIN \n" +
        "                                                                  Q_User_Role \n" +
        "                                                                  ur\n" +
        "                                                                  ON  sr.roleid = \n" +
        "                                                                      ur.roleid\n" +
        "                                                      WHERE  ur.userid = \n" +
        "                                                             ?\n" +
        "                                                             AND ur.enable = 1))\n" +
        "ORDER BY\n" +
        "       so.orgord,\n" +
        "       so.orgdesc";
        Query query = entityManager.createNativeQuery(queryString, S_Organization.class);        
        query.setParameter(1, UserId);
        query.setParameter(2, UserId);
        return query.getResultList();
    }

    @Override
    public List<S_Organization> SelectOrgByAssetID(String strMaDVQLCapTren,String strAssetID) {
        String queryString = "Select * from dbo.fn_DONVI_QUANLY_byASSETID_ORG(?,?)";
        Query query = entityManager.createNativeQuery(queryString, S_Organization.class);
        query.setParameter(1, strMaDVQLCapTren);
        query.setParameter(2, strAssetID);
        return query.getResultList();
    }

    @Override
    public List<S_Organization> SelectOrgByUserID(String strUserID) {
        try {
            m_sLastActionInfo = "";
            OrganizationDAL service = new OrganizationDAL(entityManager);
            List<S_Organization> lst = service.getOrganizationGrantByUserID(strUserID);
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            m_sLastActionInfo = e.toString();

            //throw e;
            return null;
        }
    }

    
    }

    

    
    
