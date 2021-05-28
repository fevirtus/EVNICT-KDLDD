/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;

import main.entity.shared.system.Bb_Message_Ext;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ba
 */
public class PltBBMngDataModel extends ListDataModel<Bb_Message_Ext> implements SelectableDataModel<Bb_Message_Ext>{
    public PltBBMngDataModel() {

    }

    public PltBBMngDataModel(List<Bb_Message_Ext> data) {
        super(data);
    }

    @Override
    public String getRowKey(Bb_Message_Ext t) {
        return t.getBbmid();
    }

    @Override
    public Bb_Message_Ext getRowData(String rowKey) {
        List<Bb_Message_Ext> list = (List<Bb_Message_Ext>) getWrappedData();

        for(Bb_Message_Ext lst : list) {
            if(lst.getBbmid().equals(rowKey))
                return lst;
        }
        return null;
    }
}
