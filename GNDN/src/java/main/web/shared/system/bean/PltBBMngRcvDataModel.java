/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;

import main.entity.shared.system.Bb_Message_Rcv_Ext;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ba
 */
public class PltBBMngRcvDataModel extends ListDataModel<Bb_Message_Rcv_Ext> implements SelectableDataModel<Bb_Message_Rcv_Ext>{
    public PltBBMngRcvDataModel() {

    }

    public PltBBMngRcvDataModel(List<Bb_Message_Rcv_Ext> data) {
        super(data);
    }

    @Override
    public String getRowKey(Bb_Message_Rcv_Ext t) {
        return t.getId().getRcvid();
    }

    @Override
    public Bb_Message_Rcv_Ext getRowData(String rowKey) {
        List<Bb_Message_Rcv_Ext> list = (List<Bb_Message_Rcv_Ext>) getWrappedData();

        for(Bb_Message_Rcv_Ext lst : list) {
            if(lst.getId().getRcvid().equals(rowKey))
                return lst;
        }
        return null;
    }
}
