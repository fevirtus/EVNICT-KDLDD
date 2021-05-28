/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoTariff;
import diemdo.ADiemdoTariffPK;
import diemdo.remote.IDiemDoRemote;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.ContextResources.EjbContext;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class DiemDo_TariffDetailBean extends BasePageCommon implements Serializable {

    private ADiemdoTariff tariff;
    private IDiemDoRemote iDiemDoRemote;
    private List<ADiemdo> listDiemdo = new ArrayList<>();                   //List all Diemdo
    private List<ADiemdoTariff> listTariffOnline = new ArrayList<>();       //list Diemdo have config still working
    private List<ADiemdoTariff> listAllTariff = new ArrayList<>();

    //0: default    1: configNewDD      2: createNewConfig1
    //3: createNewConfig2   4: view     5: edit
    private int mode = 0;

    private String placeholder;
    private String userId;
    private String insertTariff;                                            //Config 
    private boolean lockAllInput = false;
    private boolean lockTenDD = false;
    private boolean lockTuNgay = false;

    public DiemDo_TariffDetailBean() {
        try {
            userId = WorkingContext.getUserName();
            listDiemdo = getIDiemDoRemote().getAllDiemDo(userId);
            listTariffOnline = getIDiemDoRemote().getListTariffOnline();
            listAllTariff = getIDiemDoRemote().getAllTariff();

            String maDD = WorkingContext.getRequestQueryString("maDD");
            String thang = WorkingContext.getRequestQueryString("thang");
            String nam = WorkingContext.getRequestQueryString("nam");
            String dateId = WorkingContext.getRequestQueryString("dateId");
            insertTariff = WorkingContext.getRequestQueryString("insertTariff");

            if (maDD != null) {
                tariff = getIDiemDoRemote().getTariffById(maDD, thang, nam, dateId);
                if (insertTariff.equals("true")) {
                    if (tariff.getDenNgay() == null) {
                        mode = 2;
                        setupForMode();
                    } else {
                        mode = 3;
                        setupForMode();
                    }
                } else {
                    if (tariff.getDenNgay() == null) {
                        mode = 5;
                        setupForMode();
                    } else {
                        mode = 4;
                        setupForMode();
                    }
                }
            } else {
                mode = 1;
                setupForMode();

                //remove tariff is online from list
                for (ADiemdo diemdo : listDiemdo) {
                    for (ADiemdoTariff tariffOnline : listTariffOnline) {
                        if (diemdo.getMaDiemdo() == tariffOnline.getADiemdoTariffPK().getMaDiemdo()) {
                            listDiemdo.remove(getIDiemDoRemote().getDiemDoByID(diemdo.getMaDiemdo()));
                        }
                    }
                }

                tariff = getIDiemDoRemote().getTariffById(listAllTariff.get(0).getADiemdoTariffPK().getMaDiemdo(), listAllTariff.get(0).getADiemdoTariffPK().getThang().toString(), listAllTariff.get(0).getADiemdoTariffPK().getNam().toString(), listAllTariff.get(0).getADiemdoTariffPK().getDateid().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTenDDChange() {
        try {
            if (isMaDDHadConfiged()) {
                mode = 2;
                setupForMode();
            } else {
                mode = 1;
                setupForMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupForMode() {
        try {
            switch (mode) {
                case 1:
                    lockTenDD = lockTuNgay = lockAllInput = false;
                    break;
                case 2:
                    lockTenDD = false;
                    lockTuNgay = false;
                    lockAllInput = false;
//                    placeholder = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay().toString();
                    break;
                case 3:
                    lockTenDD = false;
                    lockTuNgay = false;
                    lockAllInput = false;
                    placeholder = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay().toString();
                    break;
                case 4:
                    lockAllInput = lockTenDD = lockTuNgay = true;
                    break;
                case 5:
                    lockTenDD = true;
                    lockTuNgay = true;
                    lockAllInput = false;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMaDDHadConfiged() {
        return listAllTariff.stream().anyMatch(tariffx -> tariffx.getADiemdoTariffPK().getMaDiemdo().equals(tariff.getADiemdoTariffPK().getMaDiemdo()));
    }

    public void excuteMode1() {
        try {
            if (checkInput()) {
                tariff.setUserCrId(WorkingContext.getUserName());
                tariff.setUserCrDtime(new Date(System.currentTimeMillis()));
                tariff.setUserMdfId(WorkingContext.getUserName());
                tariff.setUserMdfDtime(new Date(System.currentTimeMillis()));
                tariff.getADiemdoTariffPK().setDateid(BigInteger.valueOf(1));
                tariff.getADiemdoTariffPK().setThang(BigInteger.valueOf((tariff.getTuNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue()));
                tariff.getADiemdoTariffPK().setNam(BigInteger.valueOf((tariff.getDenNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYear()));

                getIDiemDoRemote().insert(tariff);
                sendMessage("Thống báo", "Cấu hình điểm đo thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excuteMode2() {
        try {
            if (checkInput()) {
                tariff.setUserCrId(WorkingContext.getUserName());
                tariff.setUserCrDtime(new Date(System.currentTimeMillis()));
                tariff.setUserMdfId(WorkingContext.getUserName());
                tariff.setUserMdfDtime(new Date(System.currentTimeMillis()));
                tariff.getADiemdoTariffPK().setDateid(BigInteger.valueOf(1));
                tariff.getADiemdoTariffPK().setThang(BigInteger.valueOf((tariff.getTuNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue()));
                tariff.getADiemdoTariffPK().setNam(BigInteger.valueOf((tariff.getDenNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue()));

                getIDiemDoRemote().insert(tariff);
                sendMessage("Thống báo", "Cấu hình điểm đo thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excuteMode3() {
        try {
            if (checkInput()) {
                tariff.setUserCrId(WorkingContext.getUserName());
                tariff.setUserCrDtime(new Date(System.currentTimeMillis()));
                tariff.setUserMdfId(WorkingContext.getUserName());
                tariff.setUserMdfDtime(new Date(System.currentTimeMillis()));
                tariff.getADiemdoTariffPK().setDateid(BigInteger.valueOf(1));
                tariff.getADiemdoTariffPK().setThang(BigInteger.valueOf((tariff.getTuNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue()));
                tariff.getADiemdoTariffPK().setNam(BigInteger.valueOf((tariff.getDenNgay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue()));
                getIDiemDoRemote().insert(tariff);

                ADiemdoTariff lastest = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo());
                lastest.setDenNgay(tariff.getTuNgay());
                getIDiemDoRemote().update(lastest);

                sendMessage("Thống báo", "Cấu hình điểm đo thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void excuteMode4() {
        try {
            if (checkInput()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excuteMode5() {
        try {
            if (checkInput()) {
                tariff.setUserMdfId(WorkingContext.getUserName());
                tariff.setUserMdfDtime(new Date(System.currentTimeMillis()));
                getIDiemDoRemote().update(tariff);

                sendMessage("Thống báo", "Cấu hình điểm đo thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onSaveDiemdo() {
        try {
            switch (mode) {
                case 1:
                    excuteMode1();
                    break;
                case 2:
                    excuteMode2();
                    break;
                case 3:
                    excuteMode3();
                    break;
                case 4:
                    excuteMode4();
                    break;
                case 5:
                    excuteMode5();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkInput() {
        boolean check = true;
        switch (mode) {
            case 1:
                if (tariff.getTuNgay() == null) {
                    sendMessage("Thông báo", "Không được để trống Từ Ngày");
                    check = false;
                }
                if (tariff.getDenNgay().compareTo(tariff.getTuNgay()) <= 0) {
                    sendMessage("Thông báo", "Đến ngày phải gần hơn Từ ngày");
                    check = false;
                }
                break;
            case 2:
                if (tariff.getTuNgay() == null) {
                    sendMessage("Thông báo", "Không được để trống Từ Ngày");
                    check = false;
                } else if (getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay() == null) {
                    sendMessage("Thông báo", "Ngày bắt đầu mới phải gần hơn ngày kết thúc cũ");
                    check = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getTuNgay().compareTo(tariff.getTuNgay()) <= 0;
                } else {
                    sendMessage("Thông báo", "Ngày bắt đầu mới phải gần hơn ngày kết thúc cũ");
                    check = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay().compareTo(tariff.getTuNgay()) <= 0;
                }
                if (tariff.getDenNgay().compareTo(tariff.getTuNgay()) <= 0) {
                    sendMessage("Thông báo", "Đến ngày phải gần hơn Từ ngày");
                    check = false;
                }
                break;
            case 3:
                if (tariff.getTuNgay() == null) {
                    sendMessage("Thông báo", "Không được để trống Từ Ngày");
                    check = false;
                } else if (getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay() == null) {
                    sendMessage("Thông báo", "Ngày bắt đầu mới phải gần hơn ngày kết thúc cũ");
                    check = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getTuNgay().compareTo(tariff.getTuNgay()) <= 0;
                } else {
                    sendMessage("Thông báo", "Ngày bắt đầu mới phải gần hơn ngày kết thúc cũ");
                    check = getIDiemDoRemote().getTariffNewest(tariff.getADiemdoTariffPK().getMaDiemdo()).getDenNgay().compareTo(tariff.getTuNgay()) <= 0;
                }
                if (tariff.getDenNgay().compareTo(tariff.getTuNgay()) <= 0) {
                    sendMessage("Thông báo", "Đến ngày phải gần hơn Từ ngày");
                    check = false;
                }
                break;
            case 4:
                break;
            case 5:
                if (tariff.getDenNgay().compareTo(tariff.getTuNgay()) <= 0) {
                    sendMessage("Thông báo", "Đến ngày phải gần hơn Từ ngày");
                    check = false;
                }
                break;
        }
        return check;
    }

    public void sendMessage(String title, String content) {
        try {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, content));
            PrimeFaces.current().ajax().update("tariffForm:messages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public List<ADiemdo> getListDiemdo() {
        return listDiemdo;
    }

    public void setListDiemdo(List<ADiemdo> listDiemdo) {
        this.listDiemdo = listDiemdo;
    }

    public boolean isLockTenDD() {
        return lockTenDD;
    }

    public void setLockTenDD(boolean lockTenDD) {
        this.lockTenDD = lockTenDD;
    }

    public boolean isLockTuNgay() {
        return lockTuNgay;
    }

    public void setLockTuNgay(boolean lockTuNgay) {
        this.lockTuNgay = lockTuNgay;
    }

    public boolean isLockAllInput() {
        return lockAllInput;
    }

    public void setLockAllInput(boolean lockAllInput) {
        this.lockAllInput = lockAllInput;
    }

    public ADiemdoTariff getTariff() {
        return tariff;
    }

    public void setTariff(ADiemdoTariff tariff) {
        this.tariff = tariff;
    }

    public IDiemDoRemote getIDiemDoRemote() {
        try {
            if (iDiemDoRemote == null) {
                iDiemDoRemote = (IDiemDoRemote) EjbContext.getLocalEJBRemote(IDiemDoRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iDiemDoRemote;
    }
}
