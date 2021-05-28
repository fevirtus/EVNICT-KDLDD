/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.ejb;

import diemdo.ADiemdo;
import diemdo.ADiemdoAx;
import diemdo.ADiemdoMix;
import diemdo.ADiemdoNhom;
import diemdo.ADiemdoTariff;
import diemdo.ADiemdoTariffMix;
import diemdo.ADiemdoTariffMix2;
import diemdo.ALstCategory;
import diemdo.ALstLoaiDd;
import diemdo.ALstTcDd;
import diemdo.ALstType;
import diemdo.ALstUlevel;
import diemdo.remote.IDiemDoRemote;
import dulieuthuthap.MHistoryBddd;
import java.awt.Cursor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.ParameterMode;
import main.ejb.shared.common.BaseCommonBL;
import javax.persistence.StoredProcedureQuery;
import main.entity.shared.system.S_Assets;

/**
 *
 * @author TUYEN EVN
 */
@Stateless
public class DiemDoBL extends BaseCommonBL implements IDiemDoRemote {

    @Override
    public List<ADiemdoNhom> getAllDiemDoNhom(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDoNhom", ADiemdoNhom.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<ADiemdoNhom> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ADiemdoNhom> getAllDiemDoNhomByOrgid(String orgid) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDoNhomByOrgid", ADiemdoNhom.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_ORGID", orgid);
            storedProcedure.execute();
            List<ADiemdoNhom> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ADiemdoNhom getDiemDoNhomByID(String maNhomDD) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getDiemDoNhomByID", ADiemdoNhom.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MANHOMDD", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MANHOMDD", maNhomDD);
            storedProcedure.execute();
            ADiemdoNhom diemdo = (ADiemdoNhom) storedProcedure.getSingleResult();
            return diemdo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ADiemdo> getListDiemDoByMaNhomDD(String maNhomDD) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getListDiemDoByMaNhomDD", ADiemdo.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MANHOMDD", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MANHOMDD", maNhomDD);
            storedProcedure.execute();
            List<ADiemdo> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteDiemDoNhomById(String id) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.deleteDiemDoNhomById");
            storedProcedure.registerStoredProcedureParameter("C_MANHOMDD", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MANHOMDD", id);
            storedProcedure.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public List<ADiemdo> getAllDiemDo(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDo", ADiemdo.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<ADiemdo> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<S_Assets> getListOrgIdByUserId(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getListOrgIdByUserId", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<S_Assets> getListChildOrg(String orgid) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getListChildOrg", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_ORGID", orgid);
            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ADiemdoMix> getAllDiemDoMix(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDoMix", ADiemdoMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<ADiemdoMix> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ADiemdoMix> getAllDiemDoMixByOrgid(String orgid) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDoMixByOrgid", ADiemdoMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_ORGID", orgid);
            storedProcedure.execute();
            List<ADiemdoMix> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ADiemdoMix> getAllDiemDoMixByMaNhomDD(String orgid, String maNhomDD) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllDiemDoMixByMaNhomDD", ADiemdoMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MANHOMDD", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_ORGID", orgid);
            storedProcedure.setParameter("C_MANHOMDD", maNhomDD);
            storedProcedure.execute();
            List<ADiemdoMix> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ADiemdoMix getDiemDoMixByID(String id) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getDiemDoMixByID", ADiemdoMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", id);
            storedProcedure.execute();
            ADiemdoMix diemdo = (ADiemdoMix) storedProcedure.getSingleResult();
            return diemdo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ADiemdo getDiemDoByID(String id) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getDiemDoByID", ADiemdo.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", id);
            storedProcedure.execute();
            ADiemdo diemdo = (ADiemdo) storedProcedure.getSingleResult();
            return diemdo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ALstCategory> getAllCategoryId(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllCategoryId", ALstCategory.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<ALstCategory> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ALstCategory> getAllCategoryByTypeId(String typeid) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllCategoryByTypeId", ALstCategory.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_TYPEID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_TYPEID", typeid);
            storedProcedure.execute();
            List<ALstCategory> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ALstType> getAllTypeId() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllTypeId", ALstType.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ALstType> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ALstLoaiDd> getAllLoaiDD() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllLoaiDD", ALstLoaiDd.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ALstLoaiDd> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ALstTcDd> getAllTcDD() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllTcDD", ALstTcDd.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ALstTcDd> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ALstUlevel> getAllUlevel() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllUlevel", ALstUlevel.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ALstUlevel> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoAx> getAllAxByMaDD(String maDD) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllAxByMaDD", ADiemdoAx.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDD);
            storedProcedure.execute();
            List<ADiemdoAx> list = storedProcedure.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoAx> getAllAx(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllAx", ADiemdoAx.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<ADiemdoAx> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<S_Assets> getAllOganization() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllOganization", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoTariff> getAllTariff() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllTariff", ADiemdoTariff.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ADiemdoTariff> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoTariffMix> getAllTariffMix(int monthLoad, int yearLoad) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllTariffMix", ADiemdoTariffMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MONTH", monthLoad);
            storedProcedure.setParameter("C_YEAR", yearLoad);

            storedProcedure.execute();
            List<ADiemdoTariffMix> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoTariffMix2> getAllTariffMix2(int monthLoad, int yearLoad) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getAllTariffMix2", ADiemdoTariffMix2.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MONTH", monthLoad);
            storedProcedure.setParameter("C_YEAR", yearLoad);

            storedProcedure.execute();
            List<ADiemdoTariffMix2> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ADiemdoTariffMix getTariffMixById(String maDD, String thang, String nam, String dateId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getTariffMixById", ADiemdoTariffMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_DATEID", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDD);
            storedProcedure.setParameter("C_MONTH", thang);
            storedProcedure.setParameter("C_YEAR", nam);
            storedProcedure.setParameter("C_DATEID", dateId);

            storedProcedure.execute();
            ADiemdoTariffMix tariffMix = (ADiemdoTariffMix) storedProcedure.getSingleResult();
            return tariffMix;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ADiemdoTariff getTariffById(String maDD, String thang, String nam, String dateId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getTariffById", ADiemdoTariff.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_DATEID", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDD);
            storedProcedure.setParameter("C_MONTH", Integer.parseInt(thang));
            storedProcedure.setParameter("C_YEAR", Integer.parseInt(nam));
            storedProcedure.setParameter("C_DATEID", Integer.parseInt(dateId));

            storedProcedure.execute();
            ADiemdoTariff tariff = (ADiemdoTariff) storedProcedure.getSingleResult();
            return tariff;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ADiemdoTariff getTariffNewest(String maDD) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getTariffNewest", ADiemdoTariff.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDD);
            storedProcedure.execute();
            ADiemdoTariff tariff = (ADiemdoTariff) storedProcedure.getSingleResult();
            return tariff;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ADiemdoTariff> getListTariffOnline() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DIEMDO.getListTariffOnline", ADiemdoTariff.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ADiemdoTariff> list = storedProcedure.getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
