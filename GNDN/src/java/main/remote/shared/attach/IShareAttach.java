package main.remote.shared.attach;


import main.remote.shared.common.ICommon;
import evnit.util.common.BaseDataInfo;
import java.util.List;
import javax.ejb.Local;
import javax.sql.DataSource;
import main.entity.shared.attach.Af_A_Asset_Att_Item;
import main.entity.shared.attach.Af_A_Asset_Img;
import main.entity.shared.attach.Af_Downcontrol;
import main.entity.shared.attach.Af_Lib_Item;
import main.entity.shared.attach.Af_Lst_Attachtype;
import main.entity.shared.attach.Af_Lst_Filetype;
import main.entity.shared.attach.Af_Other;

/**
 *
 * @author khiemvk
 */

@Local
public interface IShareAttach extends ICommon {

    public List<Af_Lst_Filetype> getAllAf_Lst_Filetypes();

    public Af_Lst_Attachtype findAfListAttachTypeByID(String attType);

    public Af_Lst_Filetype findAfListFileTypeByID(String afftid);

    public Af_Downcontrol findAfDowncontrolByID(String prefixFileID);

    public Af_A_Asset_Att_Item findAfAsset_Att_ItemByID(String afFileID);

    public Af_Lib_Item findAf_Lib_ItemByID(String afFileID);

    public Af_A_Asset_Img findAssetImgByAffileId(String affileid);

    public boolean addNewAfAssetImg(Af_A_Asset_Img asset_Img);

    public boolean deleteAfAssetImg(String affileid);

    public DataSource getDataSource();

    public int getAllAf_OtherCount(String ObjTypeID, String ObjID);

    public BaseDataInfo deleteAfOtherAttach(String affileid);

    public List<Af_Other> getAllAf_OtherByObjTypeIDByObjIDByFileTypeID(String m_ObjTypeID, String m_ObjID, String m_FileType);

    public boolean updateAfOtherImg(List<Af_Other> lstAfOther);
}
