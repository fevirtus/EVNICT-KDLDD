/**
 * 
 */
package evnit.util.common;

import evnit.util.calendar.Day;
import java.util.Date;

/**
 * @author thaodd
 *
 */
public class BaseConstant {
        public enum enumMessageMode
	{
		eInfo,
		eError,
		eWarn,
                eFatal
	}

	public enum enumFormMode
	{
		eView,
		eUpdate,
		eAddNew,
                eInit
	}

        public enum enumResultSetPosition
	{
		eFirst,
		ePrevious,
		eNext,
                eLast,
                eInit
	}

	public enum enumRight
	{
		eAll,
		eView,
		eDisable				
	}
	
	public enum enumOrderBy
	{
		eByID,
		eByName,
		eByShortName
        }
	
	//Ham dung trong them cac ban ghi 'Tất cả', để lấy key trong mô tả này
	public static String getAllKey()
	{
		return "All";
	}
	
	//Ham dung trong them cac ban ghi 'Null', để lấy key trong mô tả này
	public static String getNullKey()
	{
		return "Null";
	}

        public static String getSomeKey()
        {
                return "Some";
        }
        
	//Hàm dùng các hàm EJB trả về kết quả thành công
	public static String getActionInfoSuccess()
	{
		return "Successful";
	}

	//Hàm dùng các hàm EJB trả về kết quả lỗi khi cập nhật: không tìm thấy đối tượng
	public static String getActionInfoObjNotExist()
	{
		return "Failed: Object is not Exists";
	}
	
	//Hàm dùng trong tầng Caller, lớp CommonCaller
	public static String getActionInfoNotInit()
	{
		return "Unknown, not init ICommon";
	}

	public static String getActionInfoObjExist()
	{
		return "Object'ID existed";
	}
	
	public static String CRLF()
	{	
		return "<br/>"; 
	}
	
	public static String NOISUY()
	{
		return "NOI SUY";
	}
	
	//Lay ra ngay ngam dinh cho cac phan van hanh
	public static Date getDefaultOperDay()
	{
		Day d=new Day();
		//Day dy=new Day(d.getYear(),d.getMonth(),d.getDayOfMonth());
		d=d.addDays(-1);
		//Date dt=dy.getDate();
		//dt.setHours(0); dt.setMinutes(0); dt.setSeconds(0);
		
		return d.getDate();
	}

        /**
         * Biến lưu mã người dùng trong bảng q_user xác định tất cả người dùng
         * @return
         */
	public static String getEveryoneKey()
	{
		return "everyone";
	}

        /**
         * Dùng trong phân quyền đối tượng, trao đổi giữa các form, trả ra giá trị được tất cả các quyền
         */
        public static String getObjRightFull()
        {
            return "Full";
        }
        public static String getObjRightReadonly()
        {
            return "Readonly";
        }

        /**
         * Lấy thẻ HTML đánh dấu một chuỗi dạng không sử dụng, thông qua mầu
         * @param bOpenTag true: trường mở, ví dụ: <span...>, false: trường đóng, ví dụ:</span> để xử lý ghép chuỗi
         * @param sTitle <> null nếu muốn kèm thêm dạng tooltip
         */
        public static String getHtmlMarkState_NotUse(boolean bOpenTag, String sTitle)
        {
            String s="";
            if (bOpenTag) {
               if (sTitle != null && sTitle.length() > 0)
                   s= "<span style=\"color: #6F6E6E\" title=\"" + sTitle + "\">";
               else
                   s= "<span style=\"color: #6F6E6E\">";
            }
            else
                s="</span>";
            return s;
        }

        //Các PROGID liên quan license
        public static String PROGID_TMIS="TMIS", PROGID_EAM="EAM", PROGID_ETM="ETM";

        //Các appkey liên quan điều khiển phân hệ
        public static String AppKey_EAM = "04", AppKey_ETM="05";
}
