/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.portlet;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_PORTLET")
public class Sm_Portlet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLTID")
    private String pltid;
    @Column(name = "PLTGROUP_ID")
    private String pltGroupid;
    @Column(name = "PLTDESC")
    private String pltdesc;
    @Column(name = "FILEPATH")
    private String filepath;
    @Column(name = "FILEPATH_MOBILE")
    private String filepathMobile;
    @Column(name = "APPFUNCTIONID")
    private String appfunctionid;
    @Column(name = "ENABLE")
    private Boolean enable;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smPortlet")
//    private List<Sm_Homepage_Plt> smHomepagePltList;
    @Column(name = "ISFIX")
    private Boolean isfix;
    @Column(name = "UPDATE_PLTID")
    private String updatePltId;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "LOCAL")
    private String local;
    @Column(name = "SHEADER")
    private Boolean sHeader;
    @Column(name = "IHEIGHT")
    private Integer iHeight;
    @Column(name = "ISFLOW")
    private Boolean isFlow;
    @Column(name = "LTOP")
    private Boolean lTop;
    @Column(name = "LLEFT")
    private Boolean lLeft;
    @Column(name = "LCENTER")
    private Boolean lCenter;
    @Column(name = "LBOTTOM")
    private Boolean lBottom;
    @Column(name = "LRIGHT")
    private Boolean lRight;
    @Column(name = "ISMOBILE")
    private Boolean isMobile;

    @Column(name = "DEFAULT_AREA")
    private String defaultArea;

    @JoinColumn(name = "PLTGROUP_ID", referencedColumnName = "PLTGROUP_ID", unique = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sm_Portlet_Group smPortletGroup;

    public Sm_Portlet() {
    }

    public Sm_Portlet(String pltid) {
        this.pltid = pltid;
    }

    public String getPltid() {
        return pltid;
    }

    public void setPltid(String pltid) {
        this.pltid = pltid;
    }

    public String getPltGroupid() {
        return pltGroupid;
    }

    public void setPltGroupid(String pltGroupid) {
        this.pltGroupid = pltGroupid;
    }

    public String getPltdesc() {
        return pltdesc;
    }

    public void setPltdesc(String pltdesc) {
        this.pltdesc = pltdesc;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepathMobile() {
        return filepathMobile;
    }

    public void setFilepathMobile(String filepathMobile) {
        this.filepathMobile = filepathMobile;
    }

    public String getAppfunctionid() {
        return appfunctionid;
    }

    public void setAppfunctionid(String appfunctionid) {
        this.appfunctionid = appfunctionid;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getIsfix() {
        return isfix;
    }

    public void setIsfix(Boolean isfix) {
        this.isfix = isfix;
    }

    public String getUpdatePltId() {
        return updatePltId;
    }

    public void setUpdatePltId(String updatePltId) {
        this.updatePltId = updatePltId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Boolean getsHeader() {
        return sHeader;
    }

    public void setsHeader(Boolean sHeader) {
        this.sHeader = sHeader;
    }

    public Integer getiHeight() {
        return iHeight;
    }

    public void setiHeight(Integer iHeight) {
        this.iHeight = iHeight;
    }

    public Boolean getIsFlow() {
        return isFlow;
    }

    public void setIsFlow(Boolean isFlow) {
        this.isFlow = isFlow;
    }

    public Boolean getlTop() {
        return lTop;
    }

    public void setlTop(Boolean lTop) {
        this.lTop = lTop;
    }

    public Boolean getlLeft() {
        return lLeft;
    }

    public void setlLeft(Boolean lLeft) {
        this.lLeft = lLeft;
    }

    public Boolean getlCenter() {
        return lCenter;
    }

    public void setlCenter(Boolean lCenter) {
        this.lCenter = lCenter;
    }

    public Boolean getlBottom() {
        return lBottom;
    }

    public void setlBottom(Boolean lBottom) {
        this.lBottom = lBottom;
    }

    public Boolean getlRight() {
        return lRight;
    }

    public void setlRight(Boolean lRight) {
        this.lRight = lRight;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    public Sm_Portlet_Group getSmPortletGroup() {
        return smPortletGroup;
    }

    public void setSmPortletGroup(Sm_Portlet_Group smPortletGroup) {
        this.smPortletGroup = smPortletGroup;
    }

    public String getDefaultArea() {
        return defaultArea;
    }

    public void setDefaultArea(String defaultArea) {
        this.defaultArea = defaultArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pltid != null ? pltid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Portlet)) {
            return false;
        }
        Sm_Portlet other = (Sm_Portlet) object;
        if ((this.pltid == null && other.pltid != null) || (this.pltid != null && !this.pltid.equals(other.pltid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Portlet[pltid=" + pltid + "]";
    }

}
