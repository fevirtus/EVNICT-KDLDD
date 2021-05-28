/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieuthuthap;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Admin
 */
@Entity
public class countThuThap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String orgId;

    private int total;

    private int thuthap;

    private int thieu;

    public countThuThap() {
    }

    public countThuThap(String orgId, int total, int thuthap, int thieu) {
        this.orgId = orgId;
        this.total = total;
        this.thuthap = thuthap;
        this.thieu = thieu;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getThuthap() {
        return thuthap;
    }

    public void setThuthap(int thuthap) {
        this.thuthap = thuthap;
    }

    public int getThieu() {
        return thieu;
    }

    public void setThieu(int thieu) {
        this.thieu = thieu;
    }

}
