/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.system.bean;

import main.entity.shared.system.Sm_Inbox;
import evnit.util.common.Tools;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ba
 */
public class PltInboxDataModel extends ListDataModel<Sm_Inbox> implements SelectableDataModel<Sm_Inbox>{
    public PltInboxDataModel() {

    }

    public PltInboxDataModel(List<Sm_Inbox> data) {
        super(data);
    }

    @Override
    public String getRowKey(Sm_Inbox t) {
        return Tools.fStandardPdataTableID(t.getMsgid());
    }

    @Override
    public Sm_Inbox getRowData(String rowKey) {
        List<Sm_Inbox> list = (List<Sm_Inbox>) getWrappedData();

        for(Sm_Inbox lst : list) {
            if(Tools.fStandardPdataTableID(lst.getMsgid()).equals(rowKey))
                return lst;
        }
        return null;
    }
}
