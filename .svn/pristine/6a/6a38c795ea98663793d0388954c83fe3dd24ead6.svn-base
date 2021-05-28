package main.web.upload;

import main.ContextResources.EjbContext;
import main.entity.shared.attach.Af_Downcontrol;
import main.remote.shared.attach.IShareAttach;
import java.sql.Connection;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

/**
 *
 * @author khiemvk
 */
public class UploadUtils {
    //Loại file
    public final static String FILETYPE_DOC ="DOC";
    public final static String FILETYPE_IMG ="IMG";
    public final static String FILETYPE_OTHER ="OTHER";
    public final static String FILETYPE_SOUND ="SOUND";
    public final static String FILETYPE_VIDEO ="VIDEO";
    public final static String FILETYPE_ZIP ="ZIP";
    //Prefix file
    public final static String PRFIXFILEID_AI ="AI";
    public final static String PRFIXFILEID_AD ="AD";
    public final static String PRFIXFILEID_LI ="LI";
    public final static String PRFIXFILEID_OT ="OT";
    //Kiểu attach
    public final static String ATTTYPE_FILE ="FILE";
    public final static String ATTTYPE_LIB ="LIB";
    public final static String ATTTYPE_SELF ="SELF";
    public final static String ATTTYPE_URL ="URL";
    public final static String ATTTYPE_RPT ="RPT";

    /**
     * Thu mục web deploy
     * @return
     */
    public static String getServerPath() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String serverPath = facesContext.getExternalContext().getRealPath("/");
        return serverPath;
    }

    /**
     * Lấy đường dẫn đầy đủ của file
     * @param dir: thư mục lưu file
     * @param PRFIXFILEID
     * @param AFFILEID
     * @param FILENAME
     * @return
     */
    public static String getFullFilePath(String strPath,String PRFIXFILEID,String AFFILEID, String FILENAME){
        return strPath + "\\" + getFileName(PRFIXFILEID,AFFILEID, FILENAME);
    }

    /**
     * Lấy tên của file sẽ được ghi ở server
     * @param PRFIXFILEID
     * @param AFFILEID
     * @param FILENAME
     * @return
     */
    public static String getFileName(String PRFIXFILEID,String AFFILEID, String FILENAME){
        return PRFIXFILEID + AFFILEID + "_" + FILENAME.replace(" ","_");
    }

    public static String getFileNameForAf_Other(String FILENAME){
        return FILENAME.replace(" ","_");
    }

    /**
     * Thư mục cấu hình ghi file
     * @param PRFIXFILEID
     * @param iShareAttach
     * @return
     */
    public static String getFilePathDir(String PRFIXFILEID, IShareAttach iShareAttach){
        String dir ="";
        dir = UploadUtils.getServerPath();
        if(iShareAttach!=null){
            Af_Downcontrol downcontrol = iShareAttach.findAfDowncontrolByID(PRFIXFILEID);
            if(downcontrol!=null)
                if(downcontrol.getDir()!=null){
                    dir = dir + "\\" + downcontrol.getDir();
                }
        }
        return dir;
    }

    /**
     * Thư mục cấu hình ghi file
     * @param serverPath: đường dẫn server
     * @param PRFIXFILEID
     * @param iShareAttach
     * @return
     */
    public static String getFilePathDir(String serverPath,String PRFIXFILEID, IShareAttach iShareAttach){
        String dir ="";
        dir = serverPath;
        if(iShareAttach!=null){
            Af_Downcontrol downcontrol = iShareAttach.findAfDowncontrolByID(PRFIXFILEID);
            if(downcontrol!=null)
                if(downcontrol.getDir()!=null){
                    dir = dir + "\\" + downcontrol.getDir();
                }
        }
        return dir;
    }

    public static Connection getConnectionDsShareAttach(){
        Connection connection = null;
        try {
                IShareAttach iIShareAttach = (IShareAttach) EjbContext.getLocalEJBRemote(IShareAttach.class.getSimpleName());
                DataSource ds=iIShareAttach.getDataSource();
                if(ds!=null)
                    connection = ds.getConnection();
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        return connection;
    }
    
}
