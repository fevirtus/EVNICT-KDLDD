/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;

import main.entity.shared.system.Bb_Message_Ext;
import evnit.util.common.Tools;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ba
 */
public class PltSM_BB_DataModel extends ListDataModel<Bb_Message_Ext> implements SelectableDataModel<Bb_Message_Ext>{
    public PltSM_BB_DataModel() {

    }

    public PltSM_BB_DataModel(List<Bb_Message_Ext> data) {
        super(data);
    }

    @Override
    public String getRowKey(Bb_Message_Ext t) {
        return Tools.fStandardPdataTableID(t.getBbmid());
    }

    @Override
    public Bb_Message_Ext getRowData(String rowKey) {
        List<Bb_Message_Ext> list = (List<Bb_Message_Ext>) getWrappedData();

        for(Bb_Message_Ext lst : list) {
            if(Tools.fStandardPdataTableID(lst.getBbmid()).equals(rowKey))
                return lst;
        }
        return null;
    }
}
