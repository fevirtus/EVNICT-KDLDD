package main.ejb.shared.attach;

import main.ejb.shared.common.CommonJpaDao;
import main.entity.shared.attach.Af_A_Asset_Att_Item;
import main.entity.shared.attach.Af_A_Asset_Img;
import main.entity.shared.attach.Af_A_Asset_Img_File;
import main.entity.shared.attach.Af_Downcontrol;
import main.entity.shared.attach.Af_Lib_Item;
import main.entity.shared.attach.Af_Lst_Attachtype;
import main.entity.shared.attach.Af_Lst_Filetype;
import main.entity.shared.attach.Af_Other;

import evnit.util.common.BaseConstant;
import evnit.util.common.BaseDataInfo;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.sql.DataSource;
import main.remote.shared.attach.IShareAttach;

/**
 *
 * @author khiemvk
 */
@Stateless
//@RolesAllowed("user")
public class ShareAttachBL extends ShareAttachCommonBL implements IShareAttach {

    @Resource(mappedName = "jdbc/shareattach")
    DataSource ds;

    //{{<editor-fold defaultstate="collapsed" desc="Config Attach">
    @Override
    public List<Af_Lst_Filetype> getAllAf_Lst_Filetypes() {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager);
            return dal.getAllAf_Lst_Filetypes();
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    /**
     * Lấy loại attach
     *
     * @param attType
     * @return
     */
    @Override
    public Af_Lst_Attachtype findAfListAttachTypeByID(String attType) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_Lst_Attachtype.class);
            Af_Lst_Attachtype af = (Af_Lst_Attachtype) dal.findById(attType);
            return af;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    /**
     * Lấy định dạng file theo lại tài liệu
     *
     * @param afftid
     * @return
     */
    @Override
    public Af_Lst_Filetype findAfListFileTypeByID(String afftid) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_Lst_Filetype.class);
            Af_Lst_Filetype af = (Af_Lst_Filetype) dal.findById(afftid);
            return af;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    /**
     * Lấy thiết lập đường dẫn ghi file
     *
     * @param prefixFileID
     * @return
     */
    @Override
    public Af_Downcontrol findAfDowncontrolByID(String prefixFileID) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_Downcontrol.class);
            Af_Downcontrol af = (Af_Downcontrol) dal.findById(prefixFileID);
            return af;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }
    //{{</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Asset Attach">
    @Override
    public Af_A_Asset_Img findAssetImgByAffileId(String affileid) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_A_Asset_Img.class);
            Af_A_Asset_Img a_Asset_Img = (Af_A_Asset_Img) dal.findById(affileid);
            return a_Asset_Img;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    @Override
    public Af_A_Asset_Att_Item findAfAsset_Att_ItemByID(String afFileID) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_A_Asset_Att_Item.class);
            Af_A_Asset_Att_Item af = (Af_A_Asset_Att_Item) dal.findById(afFileID);
            return af;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addNewAfAssetImg(Af_A_Asset_Img asset_Img) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_A_Asset_Img.class);
            dal.insert(asset_Img);
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAfAssetImg(String affileid) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_A_Asset_Img.class);
            Af_A_Asset_Img a_Asset_Img = (Af_A_Asset_Img) dal.findById(affileid);
            if (a_Asset_Img != null) {
                dal.delete(a_Asset_Img);
            }
            ShareAttachDAL dal1 = new ShareAttachDAL(entityManager, Af_A_Asset_Img_File.class);
            Af_A_Asset_Img_File a_Asset_Img_File = (Af_A_Asset_Img_File) dal1.findById(affileid);
            if (a_Asset_Img_File != null) {
                dal.delete(a_Asset_Img_File);
            }

            setLastActionInfo(BaseConstant.getActionInfoSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
            return false;
        }
        return true;
    }
    //{{</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Lib Attach">
    @Override
    public Af_Lib_Item findAf_Lib_ItemByID(String afFileID) {
        setLastActionInfo("");
        try {
            ShareAttachDAL dal = new ShareAttachDAL(entityManager, Af_Lib_Item.class);
            Af_Lib_Item aF = (Af_Lib_Item) dal.findById(afFileID);
            return aF;
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
        }
        return null;
    }

    public DataSource getDataSource() {
        return ds;
    }
    //{{</editor-fold>

    public List<Af_Other> getAllAf_OtherByObjTypeIDByObjIDByFileTypeID(String m_ObjTypeID, String m_ObjID, String m_FileType) {
        String queryString = "Select a from Af_Other a where a.objtypeid =:objtypeid and a.objid =:objid";
        if (!m_FileType.equals(BaseConstant.getAllKey())) {
            queryString += " and a.filetypeid=:filetypeid";
        }
        Query query = entityManager.createQuery(queryString);
        query.setParameter("objtypeid", m_ObjTypeID);
        query.setParameter("objid", m_ObjID);
        if (!m_FileType.equals(BaseConstant.getAllKey())) {
            query.setParameter("filetypeid", m_FileType);
        }
        List<Af_Other> lst = query.getResultList();
        return lst;
    }

    /**
     * Hàm đếm số file đính kèm cho một đối tượng
     */
    public int getAllAf_OtherCount(String ObjTypeID, String ObjID) {
        String queryString = "Select count(a) from Af_Other a where a.objtypeid =:objtypeid and a.objid =:objid";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("objtypeid", ObjTypeID);
        query.setParameter("objid", ObjID);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public BaseDataInfo deleteAfOtherAttach(String affileid) {
        BaseDataInfo dataInfo = new BaseDataInfo();
        dataInfo.setColBol1(false);
        try {
            CommonJpaDao dao = new CommonJpaDao(entityManager, Af_Other.class);
            Af_Other img = (Af_Other) dao.findById(affileid);
            if (img != null) {
                if (img.getAtttype().toUpperCase().equals("FILE") && img.getFilePath() != null)//File trực tiếp
                {
                    dataInfo.setColStr1(img.getFilePath());
                }
                dao.delete(img);
                //ThảoDD bỏ đoạn code bên dưới (thêm lại vào trên): không hiểu sao lại viết như vậy. Trên đã xóa rồi, dưới lại tìm bản ghi!!!!!
//                if(img.getAtttype().toUpperCase().equals("FILE")){//File trực tiếp
//                    Af_Other af_Other = (Af_Other)findById(img.getAffileid(),Af_Other.class);
//                    if(af_Other!=null){
//                        dataInfo.setColStr1(af_Other.getFilePath());
//                    }
//                }
            }
            dataInfo.setColBol1(true);
        } catch (Exception ex) {
            dataInfo.setColStr2(ex.getMessage());
        }
        return dataInfo;
    }

    public boolean updateAfOtherImg(List<Af_Other> lstAfOther) {
        CommonJpaDao dao = new CommonJpaDao(entityManager, Af_Other.class);
        if (lstAfOther != null) {
            for (Af_Other imgData : lstAfOther) {
                Af_Other af_Other = (Af_Other) dao.findById(imgData.getAffileid());
                if (af_Other != null) {
                    af_Other.setSumdesc(imgData.getSumdesc());
                    af_Other.setFiletypeid(imgData.getFiletypeid());
                }
            }
        }
        return true;
    }
    
}
