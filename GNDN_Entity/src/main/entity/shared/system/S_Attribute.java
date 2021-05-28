package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_ATTRIBUTE", uniqueConstraints = {})
public class S_Attribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ATTRID")
    private String attrid;
    @Basic(optional = false)
    @Column(name = "ATTRDESC")
    private String attrdesc;
    @Column(name = "ATTRORD")
    private Integer attrord;
    @Column(name = "COLNAME")
    private String colname;
    @Column(name = "COLLENGTH")
    private Integer collength;
    @Column(name = "COLPRECISION")
    private Integer colprecision;
    @Column(name = "COLSCALE")
    private Integer colscale;
    @Column(name = "COLLALLOWNULL")
    private Boolean collallownull;
    @Column(name = "COLDEFAULT")
    private String coldefault;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "ASSETVIEW")
    private Boolean assetview;

    @Column(name = "SIGN")
    private String sign;
    @Column(name = "ATTRID_PARENT")
    private String attridParent;
    @Column(name = "DATAQUERYLST")
    private String dataquerylst;
    @Column(name = "DATAQUERYONE")
    private String dataqueryone;
    @Column(name = "DATAFIELDVALUE")
    private Integer datafieldvalue;
    @Column(name = "DATAFIELDDESC")
    private Integer datafielddesc;
    @Column(name = "ALLOWHISTORY")
    private Boolean allowhistory;
    @Column(name = "DATAHEADERLST")
    private String dataheaderlst;

    @Column(name = "COLDATATYPEID")
    private String coldatatypeid;
    @Column(name = "ATTRGROUPID")
    private String attrgroupid;
    @Column(name = "UOMID")
    private String uomid;

    @Column(name = "ATTTYPEID")
    private String atttypeid;
    @Column(name = "ATTVALUES")
    private String attvalues;
    @Column(name = "ATTRNOTE")
    private String attrnote;

    @Column(name = "ASSETSIMILAR")
    private String assetsimilar;

    @Column(name = "COLHEIGHT")
    private Integer colheight;

    @Column(name = "CTRL_TYPE")
    private String ctrlType;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "COLDATATYPEID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Lst_Datatype sLstDatatype;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRGROUPID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Attribute_Group attributeGroup;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "UOMID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Uom sUom;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTTYPEID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Attribute_Type attributeType;

    @Transient
    private Integer plevel;

    public Integer getPlevel() {
        return plevel;
    }

    public void setPlevel(Integer ilevel) {
        this.plevel = ilevel;
    }

    public int getPaddingLeft() {
        int padding_left = 0;
        if (plevel != null) {
            return (plevel - 1) * 20;
        }
        return padding_left < 0 ? 0 : padding_left;
    }

    public S_Attribute() {
    }

    public S_Attribute(String attrid) {
        this.attrid = attrid;
    }

    public S_Attribute(String attrid, String attrdesc) {
        this.attrid = attrid;
        this.attrdesc = attrdesc;
    }

    public String getAttrid() {
        return attrid;
    }

    public void setAttrid(String attrid) {
        this.attrid = attrid;
    }

    public String getAttrdesc() {
        return attrdesc;
    }

    public void setAttrdesc(String attrdesc) {
        this.attrdesc = attrdesc;
    }

    public Integer getAttrord() {
        return attrord;
    }

    public void setAttrord(Integer attrord) {
        this.attrord = attrord;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public Integer getCollength() {
//        collength = 50;
        return collength;
    }

    public void setCollength(Integer collength) {
        this.collength = collength;
    }

    public Integer getColheight() {
        return colheight;
    }

    public void setColheight(Integer colheight) {
        this.colheight = colheight;
    }

    public Integer getColprecision() {
        colprecision = 18;
        return colprecision;
    }

    public void setColprecision(Integer colprecision) {
        this.colprecision = colprecision;
    }

    public Integer getColscale() {
        colscale = 2;
        return colscale;
    }

    public void setColscale(Integer colscale) {
        this.colscale = colscale;
    }

    public Boolean getCollallownull() {
        return collallownull;
    }

    public void setCollallownull(Boolean collallownull) {
        this.collallownull = collallownull;
    }

    public String getColdefault() {
        return coldefault;
    }

    public void setColdefault(String coldefault) {
        this.coldefault = coldefault;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAttrgroupid() {
        return attrgroupid;
    }

    public void setAttrgroupid(String attrgroupid) {
        this.attrgroupid = attrgroupid;
    }

    public String getColdatatypeid() {
        return coldatatypeid;
    }

    public void setColdatatypeid(String coldatatypeid) {
        this.coldatatypeid = coldatatypeid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAttridParent() {
        return attridParent;
    }

    public void setAttridParent(String attridParent) {
        this.attridParent = attridParent;
    }

    public String getDataquerylst() {
        return dataquerylst;
    }

    public void setDataquerylst(String dataquerylst) {
        this.dataquerylst = dataquerylst;
    }

    public String getDataqueryone() {
        return dataqueryone;
    }

    public void setDataqueryone(String dataqueryone) {
        this.dataqueryone = dataqueryone;
    }

    public Integer getDatafieldvalue() {
        return datafieldvalue;
    }

    public void setDatafieldvalue(Integer datafieldvalue) {
        this.datafieldvalue = datafieldvalue;
    }

    public Integer getDatafielddesc() {
        return datafielddesc;
    }

    public void setDatafielddesc(Integer datafielddesc) {
        this.datafielddesc = datafielddesc;
    }

    public Boolean getAllowhistory() {
        return allowhistory;
    }

    public void setAllowhistory(Boolean allowhistory) {
        this.allowhistory = allowhistory;
    }

    public String getDataheaderlst() {
        return dataheaderlst;
    }

    public void setDataheaderlst(String dataheaderlst) {
        this.dataheaderlst = dataheaderlst;
    }

    public String getUomid() {
        return uomid;
    }

    public void setUomid(String uomid) {
        this.uomid = uomid;
    }

    public String getAttTypeid() {
        return atttypeid;
    }

    public void setAttTypeid(String atttypeid) {
        this.atttypeid = atttypeid;
    }

    public S_Lst_Datatype getsLstDatatype() {
        return sLstDatatype;
    }

    public void setsLstDatatype(S_Lst_Datatype sLstDatatype) {
        this.sLstDatatype = sLstDatatype;
    }

    public S_Attribute_Group getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(S_Attribute_Group attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public S_Uom getsUom() {
        return sUom;
    }

    public void setsUom(S_Uom sUom) {
        this.sUom = sUom;
    }

    public S_Attribute_Type getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(S_Attribute_Type attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttvalues() {
        return attvalues;
    }

    public void setAttvalues(String attvalues) {
        this.attvalues = attvalues;
    }

    public Boolean getAssetview() {
        return assetview;
    }

    public void setAssetview(Boolean assetview) {
        this.assetview = assetview;
    }

    public String getAttrnote() {
        return attrnote;
    }

    public void setAttrnote(String attrnote) {
        this.attrnote = attrnote;
    }

    public String getAssetsimilar() {
        return assetsimilar;
    }

    public void setAssetsimilar(String assetsimilar) {
        this.assetsimilar = assetsimilar;
    }

    public String getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(String ctrlType) {
        this.ctrlType = ctrlType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attrid != null ? attrid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Attribute)) {
            return false;
        }
        S_Attribute other = (S_Attribute) object;
        if ((this.attrid == null && other.attrid != null) || (this.attrid != null && !this.attrid.equals(other.attrid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Attribute[attrid=" + attrid + "]";
    }

}
