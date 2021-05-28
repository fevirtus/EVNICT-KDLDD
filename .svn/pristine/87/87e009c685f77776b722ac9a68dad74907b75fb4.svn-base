/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.attach.Af_Lib_Item;
import main.entity.shared.attach.Af_Other;
import main.remote.shared.attach.IShareAttach;
import main.web.common.bean.WorkingContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import main.web.upload.Download;
import main.web.upload.UploadUtils;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.util.SerializableSupplier;

@ManagedBean
@SessionScoped
public final class S_Content_ViewBean implements Serializable {

    private String pfid;
    private StreamedContent fileData;
    private String fileType;
    private String fileName;
    private IShareAttach m_IShareAttach;
    private String idFile;
    private String ftype = "";
    private InputStream stream;

    public S_Content_ViewBean() {
        loadData();
    }

    public void loadData() {
        try {
            //edit by thinhnd
            SerializableSupplier<InputStream> temp_stream = new SerializableSupplier<InputStream>() {
                @Override
                public InputStream get() {
                    return stream;
                }
            };
            pfid = WorkingContext.getRequestQueryString("pfid");
            try {
                ftype = WorkingContext.getRequestQueryString("ftype");
            } catch (Exception e) {
                ftype = "";
            }
            if (ftype == null || ftype.equals("")) {
                Af_Other tempLib = null;
                tempLib = (Af_Other) getIShareAttach().findById(new String(Base64.decodeBase64(pfid.getBytes())), Af_Other.class);
                String[] strTemp = tempLib.getFilename().split("\\.");
                if (strTemp.length > 1) {
                    fileType = strTemp[strTemp.length - 1];
                }
                if (strTemp.length > 1) {
                    fileType = strTemp[strTemp.length - 1];
                }
                fileType = fileType.toLowerCase();
                fileName = tempLib.getFilename();
                Download download = new Download();
                stream = download.downloadAf_Other(new String(Base64.decodeBase64(pfid.getBytes())), UploadUtils.getServerPath());
                if (stream == null) {
                    byte[] bytes = "error file".getBytes();
                    stream = new ByteArrayInputStream(bytes);
                    fileData = new DefaultStreamedContent();
                } else {
                    if (fileType.equals("gif") || fileType.equals("jpeg") || fileType.equals("jpg") || fileType.equals("png")) {
                        fileData = DefaultStreamedContent.builder().name(fileName).contentType("image/" + fileType).stream(temp_stream).build();
                    } else {
                        fileData = DefaultStreamedContent.builder().name(fileName).contentType("application/" + fileType).stream(temp_stream).build();
                    }

                }
            } else {
                Af_Lib_Item tempLib = null;
                tempLib = (Af_Lib_Item) getIShareAttach().findById(new String(Base64.decodeBase64(pfid.getBytes())), Af_Lib_Item.class);
                String[] strTemp = tempLib.getFilename().split("\\.");
                if (strTemp.length > 1) {
                    fileType = strTemp[strTemp.length - 1];
                }
                if (strTemp.length > 1) {
                    fileType = strTemp[strTemp.length - 1];
                }
                fileType = fileType.toLowerCase();
                fileName = tempLib.getFilename();
                Download download = new Download();
                stream = download.downloadAf_LibItem(new String(Base64.decodeBase64(pfid.getBytes())), UploadUtils.getServerPath());
                if (stream == null) {
                    byte[] bytes = "error file".getBytes();
                    stream = new ByteArrayInputStream(bytes);
                    fileData = new DefaultStreamedContent();
                } else {
                    if (fileType.equals("gif") || fileType.equals("jpeg") || fileType.equals("jpg") || fileType.equals("png")) {
                        fileData = DefaultStreamedContent.builder().name(fileName).contentType("image/" + fileType).stream(temp_stream).build();
                    } else {
                        fileData = DefaultStreamedContent.builder().name(fileName).contentType("application/" + fileType).stream(temp_stream).build();
                    }

                }
            }

        } catch (Exception e) {
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public StreamedContent getFileData() {
        //loadData();
        return fileData;
    }

    public String getIdFile() {
        return java.util.UUID.randomUUID().toString();
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public void setFileData(StreamedContent fileData) {
        this.fileData = fileData;
    }

    private IShareAttach getIShareAttach() {
        try {
            if (m_IShareAttach == null) {
                m_IShareAttach = (IShareAttach) EjbContext.getLocalEJBRemote(IShareAttach.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception ex) {
        }
        return m_IShareAttach;
    }

}
