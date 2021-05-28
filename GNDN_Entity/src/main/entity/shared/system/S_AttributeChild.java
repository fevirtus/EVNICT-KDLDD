package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author khiemvk
 */
@Entity
public class S_AttributeChild implements Serializable {
    
    @Id
    private String attrid;
    private String attrdesc;
    private Integer attrord;
    private String attvalues;

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

    public String getAttvalues() {
        return attvalues;
    }

    public void setAttvalues(String attvalues) {
        this.attvalues = attvalues;
    }
}
