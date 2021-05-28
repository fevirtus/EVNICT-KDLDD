package main.web.upload;

/**
 *
 * @author khiemvk
 */
public class ThreadDownload extends Thread {
//    String m_AssetID;
    /**
     * Loại download: 1: Ảnh của tài sản
     */
    int m_Type;
    String m_ServerPath;

//    public ThreadDownload(String assetID, int type, String serverPath) {
//        super();
//        this.m_AssetID = assetID;
//        this.m_Type = type;
//        this.m_ServerPath = serverPath;
//        this.setPriority(NORM_PRIORITY);
//    }

    public ThreadDownload(int type,String serverPath) {
        super();
        this.m_Type = type;
        this.m_ServerPath = serverPath;
        this.setPriority(NORM_PRIORITY);
    }

    public void run() {
        switch (m_Type) {
            //Tải hình ảnh tài sản
            case 1:
                Download download = new Download();
                //download.downloadAllImagesForAsset(m_AssetID,m_ServerPath); //Chuyển sang class ThreadDownloadEAM
                break;
            default:
                break;
        }
    }
}
