package main.entity.shared.report;
import main.entity.shared.system.S_Attribute_Group;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HANV
 */
@Entity
@Table(name = "S_REPORT_ATTR",uniqueConstraints={})
public class S_Report_Attr implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected S_Report_AttrPK id;
    @Column(name = "DEFAULTADD")
    private Boolean defaultadd;
    @Column(name = "COUNTA")
    private int counta;

    @Column(name = "ATTRORD")
    private int attrord;
    
    @JoinColumn(name = "ATTRGROUPID", referencedColumnName = "ATTRGROUPID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Attribute_Group sAttributeGroup;

    public S_Report_Attr() {
    }

    public S_Report_Attr(S_Report_AttrPK id) {
        this.id = id;
    }

    public S_Report_Attr(String categoryid, String attrgroupid) {
        this.id = new S_Report_AttrPK(categoryid, attrgroupid);
    }

    public S_Report_AttrPK getId() {
        return id;
    }

    public void setId(S_Report_AttrPK id) {
        this.id = id;
    }

    public Boolean getDefaultadd() {
        return defaultadd;
    }

    public void setDefaultadd(Boolean defaultadd) {
        this.defaultadd = defaultadd;
    }

     public int getCounta() {
        return counta;
    }

    public void setCounta(int counta) {
        this.counta = counta;
    }

    public int getAttrord() {
        return attrord;
    }

    public void setAttrord(int attrord) {
        this.attrord = attrord;
    }

    

    public S_Attribute_Group getsAttributeGroup() {
        return sAttributeGroup;
    }

    public void setsAttributeGroup(S_Attribute_Group sAttributeGroup) {
        this.sAttributeGroup = sAttributeGroup;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_Attr)) {
            return false;
        }
        S_Report_Attr other = (S_Report_Attr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EAM.S_Report_Attr[a_Lst_Category_AttrPK=" + id + "]";
    }

}
