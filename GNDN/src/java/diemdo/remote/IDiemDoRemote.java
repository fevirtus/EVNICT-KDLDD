/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.remote;

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
import dulieuthuthap.MHistoryBddd;
import java.util.List;
import main.remote.shared.common.ICommon;

import javax.ejb.Local;
import main.entity.shared.system.S_Assets;

@Local
public interface IDiemDoRemote extends ICommon {

    public List<ADiemdoNhom> getAllDiemDoNhom(String userId);

    public ADiemdoNhom getDiemDoNhomByID(String id);

    public void deleteDiemDoNhomById(String id);

    public List<ADiemdo> getAllDiemDo(String userId);
    
    public List<ADiemdo> getListDiemDoByMaNhomDD(String maNhomDD);
    
    public List<S_Assets> getListOrgIdByUserId(String userId);
    
    public List<ADiemdoNhom> getAllDiemDoNhomByOrgid(String orgid);
    
    public List<ADiemdoMix> getAllDiemDoMixByOrgid(String orgid);
    
    public List<S_Assets> getListChildOrg(String orgid);

    public List<ALstCategory> getAllCategoryByTypeId(String typeid);
    
    public List<ADiemdoMix> getAllDiemDoMix(String userId);
    
    public List<ADiemdoMix> getAllDiemDoMixByMaNhomDD(String orgid, String maNhomDD);

    public ADiemdo getDiemDoByID(String id);

    public ADiemdoMix getDiemDoMixByID(String id);

    public List<ALstCategory> getAllCategoryId(String userId);

    public List<ALstType> getAllTypeId();

    public List<ALstLoaiDd> getAllLoaiDD();

    public List<ALstTcDd> getAllTcDD();

    public List<ALstUlevel> getAllUlevel();

    public List<ADiemdoAx> getAllAxByMaDD(String maDD);

    public List<ADiemdoAx> getAllAx(String userId);

    public List<S_Assets> getAllOganization();

    public List<ADiemdoTariff> getAllTariff();
    
    public ADiemdoTariff getTariffById(String maDD, String thang, String nam, String dateId);

    public ADiemdoTariffMix getTariffMixById(String maDD, String thang, String nam, String dateId);

    public List<ADiemdoTariffMix> getAllTariffMix(int monthLoad, int yearLoad);

    public List<ADiemdoTariffMix2> getAllTariffMix2(int monthLoad, int yearLoad);
    
    public ADiemdoTariff getTariffNewest(String maDD);
    
    public List<ADiemdoTariff> getListTariffOnline();    
}
