/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import main.entity.shared.portlet.Sm_Portlet;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_HOMEPAGE_PLT")
public class Sm_Homepage_Plt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Sm_Homepage_PltPK id;
    @Column(name = "POSORD")
    private Integer posord;
    @Column(name = "VISIBLE")
    private Boolean visible;
    @JoinColumn(name = "PLTID", referencedColumnName = "PLTID", unique = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sm_Portlet smPortlet;
    @JoinColumn(name = "PPOSID", referencedColumnName = "PPOSID", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    private Sm_Lst_Pltpos smLstPltpos;
    @JoinColumn(name = "HOMEPAGEID", referencedColumnName = "HOMEPAGEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sm_Homepage smHomepage;

    public Sm_Homepage_Plt() {
    }

    public Sm_Homepage_Plt(Sm_Homepage_PltPK sm_Homepage_PltPK) {
        this.id = sm_Homepage_PltPK;
    }

    public Sm_Homepage_Plt(String homepageid, String pltid, String userid) {
        this.id = new Sm_Homepage_PltPK(homepageid, pltid, userid);
    }

    public Sm_Homepage_PltPK getId() {
        return id;
    }

    public void setId(Sm_Homepage_PltPK sm_Homepage_PltPK) {
        this.id = sm_Homepage_PltPK;
    }

    public Integer getPosord() {
        return posord;
    }

    public void setPosord(Integer posord) {
        this.posord = posord;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Sm_Portlet getSmPortlet() {
        return smPortlet;
    }

    public void setSmPortlet(Sm_Portlet smPortlet) {
        this.smPortlet = smPortlet;
    }

    public Sm_Lst_Pltpos getSmLstPltpos() {
        return smLstPltpos;
    }

    public void setSmLstPltpos(Sm_Lst_Pltpos smLstPltpos) {
        this.smLstPltpos = smLstPltpos;
    }

    public Sm_Homepage getSmHomepage() {
        return smHomepage;
    }

    public void setSmHomepage(Sm_Homepage smHomepage) {
        this.smHomepage = smHomepage;
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
        if (!(object instanceof Sm_Homepage_Plt)) {
            return false;
        }
        Sm_Homepage_Plt other = (Sm_Homepage_Plt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Homepage_Plt[sm_Homepage_PltPK=" + id + "]";
    }

}
