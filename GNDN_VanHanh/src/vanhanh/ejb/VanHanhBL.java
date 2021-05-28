/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanhanh.ejb;

import dulieuthuthap.ALstLoaiCso;
import dulieuthuthap.MHistoryBddd;
import dulieuthuthap.MHistoryBdddMix;
import dulieuthuthap.MHistoryChiso;
import dulieuthuthap.MHistorySanluong;
import dulieuthuthap.MHistorySanluongMix;
import evnit.util.common.VanHanhCommonBL;
import vanhanh.remote.IVanHanhRemote;
import java.awt.Cursor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import main.entity.shared.admin.Q_Org_Grant_User;
import main.entity.shared.system.S_Assets;
import javax.ejb.Stateless;

/**
 *
 * @author Admin
 */
@Stateless
public class VanHanhBL extends VanHanhCommonBL implements IVanHanhRemote {

    //getting primary data
    @Override
    public List<Q_Org_Grant_User> getAllOrgGrantUser() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllOrgGrantUser", Q_Org_Grant_User.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<Q_Org_Grant_User> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<S_Assets> getAllOrganization() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllOrganization", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<MHistoryChiso> getAllHistoryChiSo() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllHistoryChiSo", MHistoryChiso.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<MHistoryChiso> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<MHistorySanluong> getAllHistorySanLuong() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllHistorySanLuong", MHistorySanluong.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<MHistorySanluong> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ALstLoaiCso> getListLoaiCso() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getListLoaiCso", ALstLoaiCso.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<ALstLoaiCso> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //end of getting primary data

    @Override
    public List<S_Assets> getOrgByUserIdSorted(String userId) {
        try {
            List<S_Assets> result = new ArrayList<>();
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllOrgByUserId", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();

            if (lst.stream().anyMatch(item -> item.getOrlevel() == 1)) {
                for (S_Assets item1 : lst) {
                    if (item1.getOrlevel() == 2 || item1.getOrlevel() == 1) {
                        result.add(item1);
                    }
                }
                return result;
            } else if (lst.stream().anyMatch(item -> item.getOrlevel() == 2)) {
                for (S_Assets item1 : lst) {
                    if (item1.getOrlevel() == 2 || item1.getOrlevel() == 3) {
                        result.add(item1);
                    }
                }
                return result;
            } else if (lst.stream().anyMatch(item -> item.getOrlevel() == 3)) {
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<S_Assets> getAllOrgTreetable(String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getAllOrgByUserId", S_Assets.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<S_Assets> lst = storedProcedure.getResultList();

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getCountTotalChiSoDD(int thang, int nam, String orgId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getCountTotalChiSoDD", MHistoryChiso.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MONTH", thang);
            storedProcedure.setParameter("C_YEAR", nam);
            storedProcedure.setParameter("C_ORGID", orgId);
            storedProcedure.execute();
            List<MHistoryChiso> listChiSo = storedProcedure.getResultList();
            int count = listChiSo.size();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getCountChiSoThuThapDD(int thang, int nam, String orgId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getCountChiSoThuThapDD", MHistoryChiso.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MONTH", thang);
            storedProcedure.setParameter("C_YEAR", nam);
            storedProcedure.setParameter("C_ORGID", orgId);
            storedProcedure.execute();
            List<MHistoryChiso> listChiSo = storedProcedure.getResultList();
            int count = listChiSo.size();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<MHistorySanluongMix> getSanLuongBySelect(int thang, int nam, String orgId, String userId) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getSanLuongBySelect", MHistorySanluongMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_ORGID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_USERID", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MONTH", thang);
            storedProcedure.setParameter("C_YEAR", nam);
            storedProcedure.setParameter("C_ORGID", orgId);
            storedProcedure.setParameter("C_USERID", userId);
            storedProcedure.execute();
            List<MHistorySanluongMix> list = storedProcedure.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MHistoryBdddMix> getListBDDDByMaDDTimeMix(String maDD, int thang, int nam) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getListBDDDByMaDDTimeMix", MHistoryBdddMix.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDD);
            storedProcedure.setParameter("C_MONTH", thang);
            storedProcedure.setParameter("C_YEAR", nam);

            storedProcedure.execute();
            List<MHistoryBdddMix> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object> test() {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.test", Object.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);

            storedProcedure.execute();
            List<Object> lst = storedProcedure.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MHistoryBddd getBDDDByKey(String maDiemDo, BigInteger dateId, String maloai) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getBDDDByKey", MHistoryBddd.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_DATEID", BigInteger.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MALOAI", String.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDiemDo);
            storedProcedure.setParameter("C_DATEID", dateId);
            storedProcedure.setParameter("C_MALOAI", maloai);
            storedProcedure.execute();
            MHistoryBddd lst = (MHistoryBddd) storedProcedure.getSingleResult();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public MHistoryBddd getNewestBDDD(String maDiemDo, int month, int year){
         try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VANHANH.getNewestBDDD", MHistoryBddd.class);
            storedProcedure.registerStoredProcedureParameter("PCUR", Cursor.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("C_MADIEMDO", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_MONTH", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("C_YEAR", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("C_MADIEMDO", maDiemDo);
            storedProcedure.setParameter("C_MONTH", month);
            storedProcedure.setParameter("C_YEAR", year);
            storedProcedure.execute();
            MHistoryBddd lst = (MHistoryBddd) storedProcedure.getSingleResult();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
