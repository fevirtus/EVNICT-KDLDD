/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_SEQUENCE",uniqueConstraints={})
public class S_Sequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SEQUENCEKEY")
    private String sequencekey;
    @Column(name = "SEQVAL")
    private Integer seqval;
    @Column(name = "STEP")
    private Integer step;
    @Column(name = "NOTE")
    private String note;

    public S_Sequence() {
    }

    public S_Sequence(String sequencekey) {
        this.sequencekey = sequencekey;
    }

    public String getSequencekey() {
        return sequencekey;
    }

    public void setSequencekey(String sequencekey) {
        this.sequencekey = sequencekey;
    }

    public Integer getSeqval() {
        return seqval;
    }

    public void setSeqval(Integer seqval) {
        this.seqval = seqval;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequencekey != null ? sequencekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Sequence)) {
            return false;
        }
        S_Sequence other = (S_Sequence) object;
        if ((this.sequencekey == null && other.sequencekey != null) || (this.sequencekey != null && !this.sequencekey.equals(other.sequencekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Sequence[sequencekey=" + sequencekey + "]";
    }

}
