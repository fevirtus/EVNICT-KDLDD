/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanhanh.remote;

import dulieuthuthap.ALstLoaiCso;
import dulieuthuthap.MHistoryBddd;
import dulieuthuthap.MHistoryBdddMix;
import dulieuthuthap.MHistoryChiso;
import dulieuthuthap.MHistorySanluong;
import dulieuthuthap.MHistorySanluongMix;
import java.math.BigInteger;
import java.util.List;
import main.entity.shared.admin.Q_Org_Grant_User;
import main.entity.shared.system.S_Assets;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface IVanHanhRemote extends ICommon {

    //getting primary data
    public List<Q_Org_Grant_User> getAllOrgGrantUser();

    public List<S_Assets> getAllOrganization();

    public List<MHistoryChiso> getAllHistoryChiSo();

    public List<MHistorySanluong> getAllHistorySanLuong();

    public List<ALstLoaiCso> getListLoaiCso();
    //end

    public List<S_Assets> getOrgByUserIdSorted(String userId);

    public List<S_Assets> getAllOrgTreetable(String userId);

    public int getCountTotalChiSoDD(int thang, int nam, String orgId);

    public int getCountChiSoThuThapDD(int thang, int nam, String orgId);

    public List<MHistorySanluongMix> getSanLuongBySelect(int thang, int nam, String orgId, String userId);

    public List<Object> test();

    public List<MHistoryBdddMix> getListBDDDByMaDDTimeMix(String maDD, int thang, int nam);
    
    public MHistoryBddd getBDDDByKey(String maDiemDo, BigInteger dateId, String maloai);
    
    public MHistoryBddd getNewestBDDD(String maDiemDo, int month, int year);
}
