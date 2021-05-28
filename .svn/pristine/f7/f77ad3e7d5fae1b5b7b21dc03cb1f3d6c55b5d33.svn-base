package main.web.upload;

import java.io.InputStream;

/**
 *
 * @author khiemvk
 */
public class ThreadUpload extends Thread {
    String id;
    InputStream file;
    int iLengh;
    String PRFIXFILEID;

    public ThreadUpload(String id, InputStream file,int iLengh,String PRFIXFILEID) {
        super();
        this.id = id;
        this.file = file;
        this.PRFIXFILEID = PRFIXFILEID;
        this.setPriority(NORM_PRIORITY);
        this.iLengh=iLengh;
    }

    public void run() {
        Upload upload = new Upload();
        boolean bUpload = false;
        if(PRFIXFILEID.toUpperCase().equals(UploadUtils.PRFIXFILEID_AD)){
            //bUpload = upload.uploadAssetItem(id, filePath);
            //Sửa thành Insert vì tách FILEDATA ra bảng riêng
            bUpload = upload.uploadAssetItem_Insert(id, file,iLengh);
        }else if(PRFIXFILEID.toUpperCase().equals(UploadUtils.PRFIXFILEID_AI)){
            //bUpload = upload.uploadAssetImage(id, filePath);
            //Sửa thành Insert vì tách FILEDATA ra bảng riêng
            bUpload = upload.uploadAssetImage_Insert(id, file,iLengh);
        }else if(PRFIXFILEID.toUpperCase().equals(UploadUtils.PRFIXFILEID_LI)){
            bUpload = upload.uploadLibraryImage(id, file,iLengh);
        }else if(PRFIXFILEID.toUpperCase().equals(UploadUtils.PRFIXFILEID_OT)){
            bUpload = upload.uploadAfOtherImage(id, file,iLengh);
        }
        else if(PRFIXFILEID.toUpperCase().equals(UploadUtils.ATTTYPE_RPT)){
            bUpload = upload.uploadFileExcel(id, file,iLengh);
        }
        if(!bUpload)
            System.out.println("Upload is error!");
        //else
         //   System.out.println("Upload is success!");

    }
}
