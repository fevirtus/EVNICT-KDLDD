package evnit.tms.web.common.bean;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.column.Column;
import org.primefaces.component.columngroup.ColumnGroup;
import org.primefaces.component.row.Row;

/**
 *
 * @author khiemvk
 */
public class DataTableUtils {
    /**
     * Tạo value binding
     * @param valueExpression
     * @param valueType
     * @return
     */
    public static ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createValueExpression(
            facesContext.getELContext(), valueExpression, valueType);
    }

    /**
     * Tạo cột cho grid
     * @param strHeader
     * @param strStyle
     * @return
     */
    public static Column createColumn(String strHeader, String strStyle){
        Column c = new Column();
        //Header
        if(strHeader!=null){
            HtmlOutputText uiHeader = new HtmlOutputText();
            uiHeader.setValue(strHeader);
            //c.setHeader(uiHeader);
            c.setHeaderText(strHeader);
        }
        //Style
        if(strStyle!=null){
            c.setStyle(strStyle);
        }        
        return c;
    }

    public static UIComponent createUIForColumn(String valueBinding,Class<?> valueType){
        HtmlOutputText output = new HtmlOutputText();
        output.setValueExpression("value", createValueExpression(valueBinding, valueType));
        //Loại control

        return output;
    }

    /**
     * Tạo nhóm cột
     * @param type: header, footer
     * @param rowCount: số hàng
     * @return
     */
    public static ColumnGroup createColumnGroup(String type, int rowCount){
        ColumnGroup cg = new ColumnGroup();
        cg.setType(type);
        for(int i = 0; i<rowCount; i++){
            Row row = new Row();
            cg.getChildren().add(row);
        }
        return cg;
    }

    /**
     * Tạo cột cho nhóm cột
     * @param cg: nhóm côt
     * @param columnIndex: vị trí cột
     * @param strHeader: tiêu đề
     * @param rowIndex: vị trí hàng
     * @param rowspan
     * @param colspan
     */
    public static void createColumnForColumnGroup(ColumnGroup cg, Integer columnIndex, String strHeader,
            int rowIndex, Integer rowspan, Integer colspan){
        if(cg.getChildCount()>rowIndex){
            Row row = (Row)cg.getChildren().get(rowIndex);
            Column c = createColumn(strHeader, null);
            if(rowspan!=null)
                c.setRowspan(rowspan);
            if(colspan!=null)
                c.setColspan(colspan);
            if(columnIndex!=null)
                row.getChildren().add(columnIndex, c);
            else
                row.getChildren().add(c);
        }
    }
}
