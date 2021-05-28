/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;

/**
 * Entity cung cấp cho portlet hiển thị link
 * @author thaodd
 */
public class Sm_Shortcut_Plt implements Serializable{
    String html_img;
    String html_link;

    public String getHtml_img() {
        return html_img;
    }

    public void setHtml_img(String html_img) {
        this.html_img = html_img;
    }

    public String getHtml_link() {
        return html_link;
    }

    public void setHtml_link(String html_link) {
        this.html_link = html_link;
    }


}
