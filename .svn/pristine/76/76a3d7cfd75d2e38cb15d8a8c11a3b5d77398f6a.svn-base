/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.remote.shared.system;


import main.remote.shared.common.ICommon;
import java.util.List;
import javax.ejb.Local;
import main.entity.shared.system.S_Organization;
import main.entity.shared.system.S_OrganizationView;



/**
 *
 * @author HaNV
 */
@Local
public interface IChonDonViRemote extends ICommon {

    public List<S_OrganizationView> getAllOrganization(String orgid);
    public List<S_OrganizationView> getAllOrganizationByParent(String orgidParent);
    
    public List<S_Organization> SelectOrg(String strMaDVQLCapTren);
    public List<S_Organization> SelectOrgShort(String strUserID);
    public List<S_Organization> SelectOrgByUserID(String strUserID);
    
    public List<S_Organization> SelectOrgByAssetID(String strMaDVQLCapTren,String strAssetID);
    
}
