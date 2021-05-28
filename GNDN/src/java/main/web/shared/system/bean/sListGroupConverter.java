/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SListGroupAll;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import main.remote.shared.system.IDanhMucDungChungRemote;


/**
 *
 * @author Administrator
 */
@ManagedBean
public class sListGroupConverter implements Converter {
    private IDanhMucDungChungRemote idanhmucRemote = null;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                String ma = submittedValue.toString();
                return getdanhmucRemote().findById(ma, SListGroupAll.class);

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid AppNhomVattu"));
            }
        }        
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((SListGroupAll) value).getGrouplistid());
        }
    }
    public IDanhMucDungChungRemote getdanhmucRemote() {
        try {
            if (idanhmucRemote == null) {
                idanhmucRemote = (IDanhMucDungChungRemote) EjbContext.getLocalEJBRemote(IDanhMucDungChungRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return idanhmucRemote;
    }
}

