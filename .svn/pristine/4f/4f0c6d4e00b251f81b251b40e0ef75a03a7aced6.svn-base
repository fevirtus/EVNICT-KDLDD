package main.web.upload;

import main.ContextResources.EjbContext;
import main.remote.shared.attach.IShareAttach;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khiemvk
 */
public class Download {

    private IShareAttach m_IShareAttach;
//    private IAssetRemote m_IAssetRemote;

    /**
     * Tải tất cả hình ảnh của tài sản, kiểm tra tồn tại
     *
     * @param assetID
     * @param serverPath
     */
//    public void downloadAllImagesForAsset(String assetID, String serverPath){
//        try{
//            List<A_Asset_ImgData> lstImgData = getIAssetRemote().getAllA_Asset_ImgDataByAssetID(assetID);
//            if(lstImgData ==null || lstImgData.isEmpty()) return;
//            for(A_Asset_ImgData imgData : lstImgData){
//                if(imgData.getAtttype().equalsIgnoreCase(UploadUtils.ATTTYPE_FILE))
//                    downloadAf_Asset_Img(imgData.getImgid(),serverPath);
//                else if(imgData.getAtttype().equalsIgnoreCase(UploadUtils.ATTTYPE_LIB)){
//                    if(imgData.getLibitemid()==null) continue;
//                    downloadAf_LibItem(imgData.getLibitemid(),serverPath);
//                }else if(imgData.getAtttype().equalsIgnoreCase(UploadUtils.ATTTYPE_SELF)){
//                    if(imgData.getAitemid()==null || getIAssetRemote()==null) continue;
//                    //Tìm đối tượng đính kèm
//                    A_Asset_Att_Item attItem = (A_Asset_Att_Item)getIAssetRemote().findById(imgData.getAitemid(), A_Asset_Att_Item.class);
//                    if(attItem==null) continue;
//                    if(attItem.getAtttype().equalsIgnoreCase(UploadUtils.ATTTYPE_FILE))
//                        downloadAf_Asset_Att_Item(attItem.getAitemid(),serverPath);
//                    else if(attItem.getAtttype().equalsIgnoreCase(UploadUtils.ATTTYPE_LIB)){
//                        if(attItem.getLibitem()==null) continue;
//                        downloadAf_LibItem(attItem.getLibitem(),serverPath);
//                    }
//                }
//            }
//        }catch(Exception ex){
//        }
//    }
//    public void downloadAf_Asset_Img(String affileid, String serverPath){
//        try{
//            InputStream inputStream = null;
//            String sqlQueryData = "";
//            String sqlQueryFileName = "";
//            String fileName = "";
//            String filePath = "";
//            String filePathYear = "";
//            try{
//                Connection conn = UploadUtils.getConnectionDsShareAttach();
//                if(conn==null || getIShareAttach() == null)
//                    return;
//                //Lấy dữ liệu
//                sqlQueryFileName = "Select filename, filepath from Af_A_Asset_Img where affileid =?";
//                sqlQueryData = "Select filedata from Af_A_Asset_Img_File where affileid =?";
//                
//                PreparedStatement stmt = conn.prepareStatement(sqlQueryFileName);
//                stmt.setString(1, affileid);
//                ResultSet resultSet = stmt.executeQuery();
//                while (resultSet.next()) {
//                    fileName = resultSet.getString(1);
//                    filePathYear = resultSet.getString(2);
//                    break;
//                }
//                stmt.close();
//                resultSet.close();
//                //Kiểm tra file
//                if(fileName !=null)
//                    if(!fileName.isEmpty()){
//                        //Lấy đường dẫn ghi file
//                        filePath = UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_AI, getIShareAttach());
//                        filePath +="\\" + filePathYear + "\\" + fileName;
//                        File file = new File(filePath);
//                        if(!file.exists()){
//                            stmt = conn.prepareStatement(sqlQueryData);
//                            stmt.setString(1, affileid);
//                            resultSet = stmt.executeQuery();
//                            while (resultSet.next()) {
//                                inputStream = resultSet.getBinaryStream(1);
//                                break;
//                            }
//                            if(inputStream!=null){
//                                //Ghi file
//                                FileUtils.writeToFile(filePath, inputStream, true);
//                                //Đóng stream
//                                inputStream.close();
//                            }
//                            stmt.close();
//                            resultSet.close();
//                        }
//                    }
//                conn.close();
//            } catch(Exception ex){
//            }
//        }catch(Exception ex){
//        }
//    }
    /**
     * Tải một file đính kèm về web server
     *
     * @param affileid
     * @param serverPath
     */
    public InputStream downloadAf_Asset_Att_Item(String affileid, String serverPath) {
        //try{
        InputStream inputStream = null;
        String sqlQueryData = "";
//            String sqlQueryFileName = "";
//            String fileName = "";
//            String filePath = "";
//            String filePathYear = "";
        try {
            Connection conn = UploadUtils.getConnectionDsShareAttach();
//                if(conn==null || getIShareAttach() == null)
//                    return;
//                //Lấy dữ liệu
//                sqlQueryFileName = "Select filename,filepath from Af_A_Asset_Att_Item where affileid =?";
            sqlQueryData = "Select filedata from Af_A_Asset_Att_Item_File where affileid =?";
//                
//                PreparedStatement stmt = conn.prepareStatement(sqlQueryFileName);
//                stmt.setString(1, affileid);
//                ResultSet resultSet = stmt.executeQuery();
//                while (resultSet.next()) {
//                    fileName = resultSet.getString(1);
//                    filePathYear = resultSet.getString(2);
//                    break;
//                }
//                stmt.close();
//                resultSet.close();

                //Kiểm tra file
//                if(fileName !=null)
//                    if(!fileName.isEmpty()){
            //Lấy đường dẫn ghi file
//                        filePath = UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_AD, getIShareAttach());
//                        filePath +="\\" + filePathYear + "\\" + fileName;
//                        File file = new File(filePath);
//                        if(!file.exists()){
            PreparedStatement stmt = conn.prepareStatement(sqlQueryData);
            stmt.setString(1, affileid);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                inputStream = new ByteArrayInputStream(resultSet.getBytes(1));
                break;
            }
//                            if(inputStream!=null){
//                                //Ghi file
//                                FileUtils.writeToFile(filePath, inputStream, true);
//                                //Đóng stream
//                                inputStream.close();
//                            }
            stmt.close();
            resultSet.close();
//                        }
//                    }
            conn.close();
//            } catch(Exception ex){
//            }
        } catch (Exception ex) {
        }
        return inputStream;
    }

    /**
     * Tải một file đính kèm cho đối tượng khác về web server - LanTH thêm
     *
     * @param affileid
     * @param serverPath
     * @return
     */
    public InputStream downloadAf_Other(String affileid, String serverPath) {
        InputStream inputStream = null;
        try {

            String sqlQueryData = "";
            try {
                Connection conn = UploadUtils.getConnectionDsShareAttach();
                if (conn == null || getIShareAttach() == null) {
                    return null;
                }
                sqlQueryData = "Select filedata from Af_Other_File where affileid =?";
                PreparedStatement stmt = conn.prepareStatement(sqlQueryData);
                stmt.setString(1, affileid);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    inputStream = new ByteArrayInputStream(resultSet.getBytes(1));
                    break;
                }
                stmt.close();
                resultSet.close();
                conn.close();
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
        }
        return inputStream;
    }

    public InputStream downloadExcelTemplate(String RPTFORMID) {
        InputStream inputStream = null;
        try {

            String sqlQueryData = "";
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn == null || getIShareAttach() == null) {
                return null;
            }
            sqlQueryData = "Select filedata from S_REPORT_FORM where RPTFORMID =?";

            PreparedStatement stmt = conn.prepareStatement(sqlQueryData);
            stmt.setString(1, RPTFORMID);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                inputStream = new ByteArrayInputStream(resultSet.getBytes(1));
                break;
            }
            stmt.close();
            resultSet.close();

        } catch (Exception ex) {
        }
        return inputStream;
    }

    /**
     * Tải một file thu vien về web server - LanTH thêm
     *
     * @param affileid
     * @param serverPath
     * @return
     */
    public InputStream downloadAf_LibItem(String affileid, String serverPath) {
        InputStream inputStream = null;
        try {

            String sqlQueryData = "";
//            String sqlQueryFileName = "";
//            String fileName = "";
//            String filePath = "";
//            String filePathYear = "";
            Connection conn = UploadUtils.getConnectionDsShareAttach();
            if (conn == null || getIShareAttach() == null) {
                return null;
            }
            //Lấy dữ liệu
//                sqlQueryFileName = "Select filename,filepath from Af_Lib_Item where affileid =?";
            sqlQueryData = "Select filedata from Af_Lib_Item_File where affileid =?";
//                PreparedStatement stmt = conn.prepareStatement(sqlQueryFileName);
//                stmt.setString(1, affileid);
//                ResultSet resultSet = stmt.executeQuery();
//                while (resultSet.next()) {
//                    fileName = resultSet.getString(1);
//                    filePath = resultSet.getString(2);
//                    filePathYear = filePath.substring(0,filePath.lastIndexOf("\\"));
//                    filePathYear = filePathYear.substring(filePathYear.lastIndexOf("\\")+1,filePathYear.length());
//                    break;
//                }
//                stmt.close();
//                resultSet.close();
            //Kiểm tra file
//                if(filePath !=null)
//                    if(!filePath.isEmpty()){
            //Lấy đường dẫn ghi file
//                        filePath = UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_LI, getIShareAttach());
//                        filePath +="\\" + filePathYear + "\\" + fileName;
//                        //filePath = UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_LI, getIShareAttach());
//                        File file = new File(filePath);
//                        if(!file.exists()){
            PreparedStatement stmt = conn.prepareStatement(sqlQueryData);
            stmt.setString(1, affileid);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                inputStream = new ByteArrayInputStream(resultSet.getBytes(1));
                break;
            }
//                            if(inputStream!=null){
//                                //Ghi file
//                                FileUtils.writeToFile(filePath, inputStream, true);
//                                //Đóng stream
//                                inputStream.close();
//                            }
            stmt.close();
            resultSet.close();

//                conn.close();
//            } catch(Exception ex){
//            }
        } catch (Exception ex) {
        }
        return inputStream;
    }

    /**
     * Created by Hangntd on Sep.05.2012 Description : Download file van ban
     * nhan cong
     *
     * @param maVanBan
     * @param serverPath
     */
//    public void downloadFileVanBan(String maVanBan, String serverPath){
//        try{
//            InputStream inputStream = null;
//            String sqlQueryData = "";
//            String sqlQueryFileName = "";
//            String fileName = "";
//            String filePath = "";
//            String filePathYear = "";
//            try{
//                Connection conn = UploadUtils.getConnectionDsShareAttach();
//                if(conn==null || getIShareAttach() == null)
//                    return;
//                sqlQueryFileName = "SELECT TEN_FILE, DUONG_DAN FROM gnc_vanban g WHERE g.MA_VANBAN=?1";
//                sqlQueryData = "SELECT  NOIDUNG_FILE FROM gnc_vanban g WHERE g.MA_VANBAN=?1";
//                PreparedStatement stmt = conn.prepareStatement(sqlQueryFileName);
//                stmt.setString(1, maVanBan);
//                ResultSet resultSet = stmt.executeQuery();
//                while (resultSet.next()) {
//                    fileName = resultSet.getString(1);
//                    filePath = resultSet.getString(2);
//                    filePathYear = filePath.substring(0,filePath.lastIndexOf("\\"));
//                    filePathYear = filePathYear.substring(filePathYear.lastIndexOf("\\")+1,filePathYear.length());
//                    break;
//                }
//                stmt.close();
//                resultSet.close();
//                //Kiểm tra file
//                if(filePath !=null)
//                    if(!filePath.isEmpty()){
//                        //Lấy đường dẫn ghi file
////                        String filePath = UploadUtils.getServerPath() + "\\" + m_CurrentObject.getDuongDan();
//                        filePath = serverPath;//UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_OT, getIShareAttach());
//                        filePath +="\\" + filePathYear + "\\" + fileName;
//                        //filePath = UploadUtils.getFilePathDir(serverPath, UploadUtils.PRFIXFILEID_OT, getIShareAttach());
//                        //filePath +="\\" + fileName;
//                        File file = new File(filePath);
//                        if(!file.exists()){
//                            stmt = conn.prepareStatement(sqlQueryData);
//                            stmt.setString(1, maVanBan);
//                            resultSet = stmt.executeQuery();
//                            while (resultSet.next()) {
//                                inputStream = resultSet.getBinaryStream(1);
//                                break;
//                            }
//                            if(inputStream!=null){
//                                //Ghi file
//                                FileUtils.writeToFile(filePath, inputStream, true);
//                                //Đóng stream
//                                inputStream.close();
//                            }
//                            stmt.close();
//                            resultSet.close();
//                        }
//                    }
//                conn.close();
//            } catch(Exception ex){
//            }
//        }catch(Exception ex){
//        }
//    }
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
//    public IAssetRemote getIAssetRemote(){
//        try {
//            if (m_IAssetRemote == null) {
//                m_IAssetRemote = (IAssetRemote) EjbContext.getLocalEJBRemote(IAssetRemote.class.getSimpleName());
//            } else {
//                EjbContext.LoginEJB();
//            }
//        } catch (Exception ex) {
//        }
//        return m_IAssetRemote;
//    }
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
    //}}</editor-fold>
}
