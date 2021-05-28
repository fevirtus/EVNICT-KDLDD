/**
*
* @author Vu Khac Khiem
*/

package main.remote.shared.system;

import java.util.List;

import javax.ejb.Local;
import main.entity.shared.system.S_Organization;

import main.remote.shared.common.ICommon;


@Local
public interface IAssetsRemote extends ICommon{

	public void addS_Organization(S_Organization org);

	public void updateS_Organization(S_Organization org);

	public void removeS_Organization(S_Organization org);

	public void removeListS_Organization(List<S_Organization> lstOrg);

	public S_Organization findS_OrganizationById(String orgid);

	public List<S_Organization> getAllS_Organization();

	public List<S_Organization> getOrganizationGrantByUserID( String userID) throws Exception;


}
