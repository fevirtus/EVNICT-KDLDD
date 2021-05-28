package main.web.upload;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author khiemvk
 */
public class Upload {

    /**
     * Đẩy ảnh lên DB
     *
     * @param imgID
     * @param filePath
     * @return
     */
    public boolean uploadAssetImage(String imgID, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("update Af_A_Asset_Img_File set filedata = ? where affileid=?");
                ps.setBinaryStream(1, file, ilength);
                //ps.setBytes(1, StreamUtils.readStream(fileInputStream));
                ps.setString(2, imgID);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Insert ảnh lên DB
     *
     * @param imgID
     * @param file
     * @param ilength
     * @param filePath
     * @return
     */
    public boolean uploadAssetImage_Insert(String imgID, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("insert into Af_A_Asset_Img_File (filedata,affileid) Values (?,?)");
                ps.setBinaryStream(1, file, ilength);
                //ps.setBytes(1, StreamUtils.readStream(fileInputStream));
                ps.setString(2, imgID);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Đẩy file lên DB
     *
     * @param id
     * @param file
     * @param ilength
     * @param filePath
     * @return
     */
    public boolean uploadAssetItem(String id, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("update Af_A_Asset_Att_Item_File set filedata = ? where affileid=?");
                ps.setBinaryStream(1, file, ilength);
                ps.setString(2, id);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Insert file lên DB
     *
     * @param id
     * @param file
     * @param ilength
     * @param filePath
     * @return
     */
    public boolean uploadAssetItem_Insert(String id, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("insert into Af_A_Asset_Att_Item_File (filedata,affileid) Values (?,?)");
                ps.setBinaryStream(1, file, ilength);
                ps.setString(2, id);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Đẩy ảnh file thư viện lên DB
     *
     * @param imgID
     * @param file
     * @param ilength
     * @param filePath
     * @return
     */
    public boolean uploadLibraryImage(String imgID, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("update Af_Lib_Item_File set filedata = ? where affileid=?");
                ps.setBinaryStream(1, file, ilength);
                //ps.setBytes(1, StreamUtils.readStream(fileInputStream));
                ps.setString(2, imgID);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Đẩy ảnh file đính kèm cho các đối tượng khác lên DB
     *
     * @param affileID
     * @param file
     * @param ilength
     * @param filePath
     * @return
     */
    public boolean uploadAfOtherImage(String affileID, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("update Af_Other_File set filedata = ? where affileid=?");
                ps.setBinaryStream(1, file, ilength);
                //ps.setBytes(1, StreamUtils.readStream(fileInputStream));
                ps.setString(2, affileID);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean uploadFileExcel(String affileID, InputStream file, int ilength) {
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("update s_report_form set filedata = ? where rptformid=?");
                ps.setBinaryStream(1, file, ilength);
                //ps.setBytes(1, StreamUtils.readStream(fileInputStream));
                ps.setString(2, affileID);
                ps.execute();
                ps.close();
                conn.close();
            }
            conn = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    //}}</editor-fold>
}
