/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.remote.shared.system;

import main.entity.shared.system.SListAll;
import main.entity.shared.system.SListFieldAll;
import main.entity.shared.system.SListGroupAll;
import main.entity.shared.system.S_List_All_Category;
import main.remote.shared.common.ICommon;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author HaNV
 */
@Local
public interface IDanhMucDungChungRemote extends ICommon {
    public List<SListFieldAll> SelectListFieldALL(String strListID);
    public List<SListAll> SelectListALL();
    public List<SListGroupAll> SelectListGroupALL();
    public Boolean DeleteSListFieldALL(String nFieldID);
    public List<String[]> getListGroupForSearch();
    public Boolean CheckKeys(String ListID,String FieldCode);    
    public List<S_List_All_Category> getLstCategory(String listID);
    
    
    
}
