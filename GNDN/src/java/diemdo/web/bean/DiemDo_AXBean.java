/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo.web.bean;

import diemdo.ADiemdo;
import diemdo.ADiemdoAx;
import diemdo.ADiemdoAxPK;
import diemdo.remote.IDiemDoRemote;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Assets;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.PrimeFaces;

/**
 *
 * @author TUYEN EVN
 */
@ManagedBean
@ViewScoped
public class DiemDo_AXBean extends BasePageCommon implements Serializable {

//  1: DD did not have any Ax before -> New Ax        
//  2: DD have Ax before but all is done -> New AX
//  3: DD have Ax what is online -> End this Ax
//  4: DD have Ax what is online -> Create new Ax after End old Ax
    private int mode = 0;

    private boolean disableThemMoi = false;     // disable button Them moi
    private boolean lockInput = true;           //disable orgidRef, maDiemdoRef, ngay
    private boolean lockOther = false;          //disable moTa, ngayKt
    private boolean manyNewest = false;         //when oldEnd=newStart=newEnd

    private IDiemDoRemote iDiemDoRemote;
    private ADiemdo diemDo;                     //Diem do have data and won't change
    private String userId;                      //user id of current user
    private ADiemdoAx temp_Ax;                  //cloned from selectAx, store fixed value of object
    private ADiemdoAx selectAx;                 //object Ax had been selected from datatable
    private ADiemdoAx Ax;                       //cloned from selectAx, store original value of object

    private List<ADiemdoAx> listAx = new ArrayList<>();
    private List<ADiemdo> listDiemdo = new ArrayList<>();
    private List<S_Assets> listOrganization = new ArrayList<>();

    public DiemDo_AXBean() {
        try {
            //get maDD of diemdoSelected from diemdo_details.xhtml            
            String maDD = WorkingContext.getRequestQueryString("maDD");
            if (maDD != null) {
                userId = WorkingContext.getUserName();
                diemDo = getIDiemDoRemote().getDiemDoByID(maDD);
                listDiemdo = getIDiemDoRemote().getAllDiemDo(userId);
                listAx = getIDiemDoRemote().getAllAxByMaDD(maDD);
                listOrganization = getIDiemDoRemote().getAllOganization();
                temp_Ax = new ADiemdoAx(maDD, maDD, listOrganization.get(0).getOrgid(), BigInteger.ZERO);

                if (listAx.isEmpty()) {
                    mode = 1;
                    setupMode();
                } else {
                    if (selectNewestAx().getNgayKt() != null) {
                        mode = 2;
                        setupMode();
                    } else {
                        mode = 3;
                        setupMode();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Setup status of components
    public void setupMode() {
        try {
            switch (mode) {
                case 1:
                    temp_Ax.setNgay(new Date(System.currentTimeMillis()));
                    lockInput = lockOther = false;
                    disableThemMoi = true;
                    break;
                case 2:
                    selectAx = selectNewestAx();
                    temp_Ax = selectNewestAx();
                    lockInput = lockOther = true;
                    disableThemMoi = false;
                    break;
                case 3:
                    selectAx = selectNewestAx();
                    temp_Ax = selectNewestAx();
                    lockInput = true;
                    lockOther = false;
                    disableThemMoi = false;
                    break;
                case 4:                             //after click on button Them Moi
                    selectAx = new ADiemdoAx();
                    temp_Ax = new ADiemdoAx();
                    temp_Ax.setNgay(new Date(System.currentTimeMillis()));
                    lockInput = lockOther = false;
                    disableThemMoi = false;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //change status and variable when select item on datatable
    public void onRowSelected() {
        try {
            //clone variable from selectAx
            temp_Ax = (ADiemdoAx) SerializationUtils.clone(selectAx);
            Ax = (ADiemdoAx) SerializationUtils.clone(selectAx);

            switch (mode) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    if (Ax.getNgayKt().compareTo(selectNewestAx().getNgayKt()) != 0) {
                        lockInput = true;
                        lockOther = true;
                    } else {
                        if (manyNewest && Ax.getNgayKt().compareTo(Ax.getNgay()) != 0) {
                            lockInput = true;
                            lockOther = true;
                        } else {
                            lockInput = false;
                            lockOther = false;
                        }
                    }
                    break;
                case 4:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Check day when user input
    public boolean checkDay(Date oldStart, Date oldEnd, Date newStart, Date newEnd) {
        boolean checkNewStart = true;
        boolean checkNewEnd = true;

        try {
            //start date must nearer than end date of old Ax
            if (oldEnd == null) {
                checkNewStart = newStart.compareTo(oldStart) >= 0;
            } else {
                checkNewStart = newStart.compareTo(oldEnd) >= 0;
            }

            //end date (if have) must nearer than start date of one Ax
            if (newEnd == null) {
                checkNewEnd = true;
            } else {
                checkNewEnd = newEnd.compareTo(newStart) >= 0;
            }

            if (!checkNewStart) {
                sendMessage("Thông báo", "Ngày bắt đầu Ánh xạ mới phải sau ngày kết thúc của ánh xạ cũ");
            }
            if (!checkNewEnd) {
                sendMessage("Thông báo", "Ngày kết thúc của Ánh xạ phải sau ngày mở đầu của Ánh xạ đó");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkNewStart == checkNewEnd == true;
    }

    //Add new AX when Diemdo dont have Ax
    public void addListEmptyOrDone() {
        try {
            Ax = (ADiemdoAx) SerializationUtils.clone(selectAx);
            if (mode == 1) {
                temp_Ax.setUserCrId(WorkingContext.getUserName());
                temp_Ax.setUserCrTime(new Date(System.currentTimeMillis()));
                temp_Ax.setUserMdfId(WorkingContext.getUserName());
                temp_Ax.setUserMdfTime(new Date(System.currentTimeMillis()));
                temp_Ax.setADiemdoAxPK(new ADiemdoAxPK(temp_Ax.getADiemdoAxPK().getMaDiemdo(), temp_Ax.getADiemdoAxPK().getMaDiemdoRef(), temp_Ax.getADiemdoAxPK().getOrgidRef(), new BigInteger(new SimpleDateFormat("yyyymmdd").format(temp_Ax.getNgay()))));
                getIDiemDoRemote().insert(temp_Ax);

                if (temp_Ax.getNgayKt() == null) {
                    diemDo.setOrgidRef(temp_Ax.getADiemdoAxPK().getOrgidRef());
                    diemDo.setMaDiemdoRef(temp_Ax.getADiemdoAxPK().getMaDiemdoRef());
                } else {
                    diemDo.setOrgidRef(null);
                    diemDo.setMaDiemdoRef(null);
                }
                getIDiemDoRemote().update(diemDo);
                sendMessage("Thông báo", "Thêm mới ánh xạ thành công");
            } else if (mode == 2) {
                if (checkDay(Ax.getNgay(), Ax.getNgayKt(), temp_Ax.getNgay(), temp_Ax.getNgayKt())) {
                    temp_Ax.setUserCrId(WorkingContext.getUserName());
                    temp_Ax.setUserCrTime(new Date(System.currentTimeMillis()));
                    temp_Ax.setUserMdfId(WorkingContext.getUserName());
                    temp_Ax.setUserMdfTime(new Date(System.currentTimeMillis()));
                    temp_Ax.setADiemdoAxPK(new ADiemdoAxPK(temp_Ax.getADiemdoAxPK().getMaDiemdo(), temp_Ax.getADiemdoAxPK().getMaDiemdoRef(), temp_Ax.getADiemdoAxPK().getOrgidRef(), new BigInteger(new SimpleDateFormat("yyyymmdd").format(temp_Ax.getNgay()))));
                    getIDiemDoRemote().insert(temp_Ax);

                    if (temp_Ax.getNgayKt() == null) {
                        diemDo.setOrgidRef(temp_Ax.getADiemdoAxPK().getOrgidRef());
                        diemDo.setMaDiemdoRef(temp_Ax.getADiemdoAxPK().getMaDiemdoRef());
                    } else {
                        diemDo.setOrgidRef(null);
                        diemDo.setMaDiemdoRef(null);
                    }
                    getIDiemDoRemote().update(diemDo);
                    sendMessage("Thông báo", "Thêm mới ánh xạ thành công");
                } else {
                    sendMessage("Thông báo", "Ngày bắt đầu ánh xạ mới phải sau ngày kết thúc của ánh xạ cũ!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Add new AX when Diemdo still have Ax
    public void addContinue() {
        try {
            if (mode == 4) {
                temp_Ax.setUserCrId(WorkingContext.getUserName());
                temp_Ax.setUserCrTime(new Date(System.currentTimeMillis()));
                temp_Ax.setUserMdfId(WorkingContext.getUserName());
                temp_Ax.setUserMdfTime(new Date(System.currentTimeMillis()));
                temp_Ax.setADiemdoAxPK(new ADiemdoAxPK(temp_Ax.getADiemdoAxPK().getMaDiemdo(), temp_Ax.getADiemdoAxPK().getMaDiemdoRef(), temp_Ax.getADiemdoAxPK().getOrgidRef(), new BigInteger(new SimpleDateFormat("yyyymmdd").format(temp_Ax.getNgay()))));
                getIDiemDoRemote().insert(temp_Ax);

                Ax.setNgayKt(temp_Ax.getNgay());
                getIDiemDoRemote().update(Ax);

                if (temp_Ax.getNgayKt() == null) {
                    diemDo.setOrgidRef(temp_Ax.getADiemdoAxPK().getOrgidRef());
                    diemDo.setMaDiemdoRef(temp_Ax.getADiemdoAxPK().getMaDiemdoRef());
                } else {
                    diemDo.setOrgidRef(null);
                    diemDo.setMaDiemdoRef(null);
                }
                getIDiemDoRemote().update(diemDo);

                sendMessage("Thông báo", "Thêm mới ánh xạ thành công");
            } else if (mode == 3) {
                temp_Ax.setUserMdfId(WorkingContext.getUserName());
                temp_Ax.setUserMdfTime(new Date(System.currentTimeMillis()));
                temp_Ax.setADiemdoAxPK(new ADiemdoAxPK(temp_Ax.getADiemdoAxPK().getMaDiemdo(), temp_Ax.getADiemdoAxPK().getMaDiemdoRef(), temp_Ax.getADiemdoAxPK().getOrgidRef(), new BigInteger(new SimpleDateFormat("yyyymmdd").format(temp_Ax.getNgay()))));
                getIDiemDoRemote().update(temp_Ax);

                diemDo.setOrgidRef(null);
                diemDo.setMaDiemdoRef(null);
                getIDiemDoRemote().update(diemDo);

                sendMessage("Thông báo", "Kết thúc ánh xạ thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Save change when click button Save
    public void saveChange() {
        try {
            switch (mode) {
                case 1:
                    addListEmptyOrDone();
                    break;
                case 2:
                    addListEmptyOrDone();
                    break;
                case 3:
                    addContinue();
                    break;
                case 4:
                    addContinue();
                    break;
            }
        } catch (Exception e) {
            sendMessage("Thông báo", "Chọn ánh xạ mới nhất để tiếp tục thao tác");
        }
    }

    //check ngayKt = null to select type of Add function
    public boolean isDoned() {
        return listAx.stream().noneMatch((item) -> (item.getNgayKt() == null));
    }

    public void onThemMoi() {
        if (mode == 2) {
            temp_Ax = new ADiemdoAx(selectAx.getADiemdoAxPK().getMaDiemdo(), "  ", listOrganization.get(0).getOrgid(), BigInteger.ZERO);
            temp_Ax.setNgay(new Date(System.currentTimeMillis()));
            lockInput = lockOther = false;
            disableThemMoi = false;
            PrimeFaces.current().executeScript("PrimeFaces.focus('AXForm:maDiemdoREF');");
        } else if (mode == 3) {
            mode = 4;
            setupMode();
        }
    }

    //select Anh xa have ngayKt is newest
    public ADiemdoAx selectNewestAx() {
        ADiemdoAx ax = new ADiemdoAx();
        try {
            ax = listAx.get(0);
            for (ADiemdoAx item : listAx) {
                if (item.getNgayKt() == null) {
                    ax = item;
                    return ax;
                }
                if (item.getNgayKt().compareTo(ax.getNgayKt()) > 0) {
                    ax = item;
                }
            }

            //when oldEnd=newStart=newEnd
            int count = 0;
            for (ADiemdoAx item1 : listAx) {
                if (item1.getNgayKt().compareTo(ax.getNgayKt()) == 0) {
                    count++;
                }
            }
            if (count > 1) {
                manyNewest = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ax;
    }

    public void sendMessage(String title, String content) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, content));
        PrimeFaces.current().ajax().update("AXForm:messages");
    }

    public boolean isDisableThemMoi() {
        return disableThemMoi;
    }

    public void setDisableThemMoi(boolean disableThemMoi) {
        this.disableThemMoi = disableThemMoi;
    }

    public List<S_Assets> getListOrganization() {
        return listOrganization;
    }

    public void setListOrganization(List<S_Assets> listOrganization) {
        this.listOrganization = listOrganization;
    }

    public ADiemdoAx getSelectAx() {
        return selectAx;
    }

    public void setSelectAx(ADiemdoAx selectAx) {
        this.selectAx = selectAx;
    }

    public boolean isLockOther() {
        return lockOther;
    }

    public void setLockOther(boolean lockOther) {
        this.lockOther = lockOther;
    }

    public ADiemdoAx getTemp_Ax() {
        return temp_Ax;
    }

    public void setTemp_Ax(ADiemdoAx temp_Ax) {
        this.temp_Ax = temp_Ax;
    }

    public boolean isLockInput() {
        return lockInput;
    }

    public void setLockInput(boolean lockInput) {
        this.lockInput = lockInput;
    }

    public ADiemdoAx getAx() {
        return Ax;
    }

    public void setAx(ADiemdoAx Ax) {
        this.Ax = Ax;
    }

    public List<ADiemdoAx> getListAx() {
        return listAx;
    }

    public void setListAx(List<ADiemdoAx> listAx) {
        this.listAx = listAx;
    }

    public List<ADiemdo> getListDiemdo() {
        return listDiemdo;
    }

    public void setListDiemdo(List<ADiemdo> listDiemdo) {
        this.listDiemdo = listDiemdo;
    }

    public ADiemdo getDiemDo() {
        return diemDo;
    }

    public void setDiemDo(ADiemdo diemDo) {
        this.diemDo = diemDo;
    }

    public IDiemDoRemote getIDiemDoRemote() {
        try {
            if (iDiemDoRemote == null) {
                iDiemDoRemote = (IDiemDoRemote) EjbContext.getLocalEJBRemote(IDiemDoRemote.class
                        .getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iDiemDoRemote;
    }
}
