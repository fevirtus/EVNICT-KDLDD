package main.ejb.shared.attach;

import main.ejb.shared.common.CommonJpaDao;
import main.entity.shared.attach.Af_Lst_Filetype;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author khiemvk
 */

public class ShareAttachDAL extends CommonJpaDao{
    public ShareAttachDAL(EntityManager entityManager, Class<?> entityClass) {
        super(entityManager, entityClass);
    }

    public ShareAttachDAL(EntityManager entityManager) {
        super(entityManager, null);
    }

    public List<Af_Lst_Filetype> getAllAf_Lst_Filetypes(){
        String queryString = "SELECT a from Af_Lst_Filetype as a" +
                " order by a.aford";
        Query query = entityManager.createQuery(queryString);
        List<Af_Lst_Filetype> lst = query.getResultList();
        return lst;
    }
}
