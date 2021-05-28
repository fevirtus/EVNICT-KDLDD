package main.ejb.shared.attach;

import evnit.util.common.BaseConstant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author khiemvk
 */

public class ShareAttachCommonBL {
    @PersistenceContext(unitName = "TMS_SHAREATTACH_EJBPU")
    public EntityManager entityManager;
    protected String m_sLastActionInfo=""; //Bien luu thong tin xu ly cuoi cung cua mot phuong thuc    
    
    //{{<editor-fold defaultstate="collapsed" desc="Messages">
    public String getLastActionInfo()
    {
            return m_sLastActionInfo;
    }

    protected void setLastActionInfo(String s)
    {
            m_sLastActionInfo=s;
    }

    protected void setLastActionInfoFromException(Exception e)
    {
        m_sLastActionInfo=e.toString();
        if (m_sLastActionInfo==null || m_sLastActionInfo.isEmpty())
            m_sLastActionInfo=e.getMessage();
    }
    //{{</editor-fold>

    /**
     * Hàm lấy đối tượng theo khóa
     * @param id:  đối tượng khóa
     * @param entityClass: lớp entity trả về
     * @return entity
     */
    public Object findById(Object id,Class<?> entityClass) {
        return entityManager.find(entityClass, id);
    }

    public boolean insert(Object object) {
        setLastActionInfo("");
        try {
            entityManager.persist(object);
            setLastActionInfo(BaseConstant.getActionInfoSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(Object object) {
        setLastActionInfo("");
        try {
            entityManager.merge(object);
            setLastActionInfo(BaseConstant.getActionInfoSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean delete(Object id,Class<?> entityClass) {
        setLastActionInfo("");
        try {
            Object entity = findById(id, entityClass);
            if(entity!=null)
                entityManager.remove(entity);
            setLastActionInfo(BaseConstant.getActionInfoSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            setLastActionInfo(e.getMessage());
            return false;
        }
        return true;
    }
}
