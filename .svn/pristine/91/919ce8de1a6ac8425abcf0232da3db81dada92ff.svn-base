/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evnit.util.common;

import evnit.util.setting.DBSettings;
import evnit.util.setting.ResourcesFactory;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author thaodd
 */
public class Tools {
    
    /**
     * Hàm gộp giá trị giờ vào kiểu Date
     * @param dDate Giá trị ngày
     * @param strTime Giá trị giờ theo định dạng hh:mm:ss
     * @return Ngày và giờ đã gộp vào, null nếu có lỗi hoặc dDay là null
     */
    public static Date fDateMergeWithStrTime(Date dDay, String strTime)
    {
        try
        {
            if (dDay==null)
                return null;

            if (strTime == null || strTime.isEmpty())
                return dDay;

            String s;
            SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
            s=df.format(dDay);
            s += " " + strTime;

            df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return df.parse(s);
        }
        catch (Exception e)
        {

        }
        return null;
    }

    /**
     * Hàm lấy chuỗi giờ phút giây theo định dạng hh:mm:ss ra khỏi biến ngày
     * @param dDayTime Ngày giờ cần lấy
     * @return Kết quả, null nếu dDayTime là null
     */
    public static String fDateSplitToStrTime(Date dDayTime)
    {
        try
        {
            if (dDayTime==null)
                return null;

            SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
            return df.format(dDayTime);
        }
        catch (Exception e)
        {

        }
        return null;

    }

    /**
     * Hàm chuẩn hóa giá trị trước khi truyền vào câu lệnh SQL, tránh SQL Injection
     * Ví dụ: truyền vào là 103'5, đầu ra sẽ là 1035. Gỡ bỏ ký tự '
     * @param sValue Tham số giá trị
     * @return Kết quả đã chuẩn hóa, có thể ghép vào chuỗi SQL an toàn
     */
    public static String fSQLStandardValue(String sValue)
    {
        if (sValue == null || sValue.isEmpty())
            return sValue;
        sValue=sValue.replaceAll("'", "");
        return sValue;
    }

    /**
     * Hàm chuyển đỗi chuỗi các giá trị String phân tách bởi dấu , thành chuỗi giá trị an toàn để đưa vào mệnh đề IN trong SQL
     * Ví dụ: đầu vào là 101, 102, 103'5 -> đầu ra là '101', '102', '1035'
     * @param sLstVal Danh sách giá trị
     * @param sSep Phần tử phân tách trong sLstVal
     * @return
     */
    public static String fSQLConvertLstStrValForIn(String sLstVal, String sSep)
    {
        String sResult;
        sLstVal=fSQLStandardValue(sLstVal);
        if (sLstVal == null || sLstVal.isEmpty())
            return sLstVal;
        String[] lst=sLstVal.split(sSep);
        sResult="";
        for(int i=0;i<lst.length;i++)
        {
            if (!lst[i].isEmpty())
            {
                if (!sResult.isEmpty())
                    sResult += ",";
                if (DBSettings.getEnumDBMode()==DBSettings.enumDBMode.SQLServer)
                    sResult += "N'" + lst[i].trim() + "'";
                else
                    sResult += "'" + lst[i].trim() + "'";
            }
        }
        return sResult;
    }

    /**
     * Mảng giá trị cần đưa vào câu lệnh in SQL
     * @param sLstVal
     * @return ví dụ đầu ra là '101', '102', '1035'
     */
    public static String fSQLConvertLstStrValForIn(List<String> sLstVal)
    {
        String sResult="";
        if (sLstVal == null || sLstVal.isEmpty())
            return sResult;        
        for(String str : sLstVal)
        {
            if (!str.isEmpty())
            {
                if (!sResult.isEmpty())
                    sResult += ",";
                if (DBSettings.getEnumDBMode()==DBSettings.enumDBMode.SQLServer)
                    sResult += "N'" + fSQLStandardValue(str).trim() + "'";
                else
                    sResult += "'" + fSQLStandardValue(str).trim() + "'";
            }
        }
        return sResult;
    }

    /**
     * Hàm chuyển đổi giá trị một xâu về giá trị tương ứng kiểu biến
     * @param sVal Giá trị đối tượng
     * @param sValTypeID Mã kiểu đối tượng gồm các giá trị: NUM, DATE
     * @return null nếu có lỗi, trả ra các giá trị kiểu Double, Date, cần ép kiểu từ Object về kiểu này
     */
    public static Object convertObjValue(String sVal, String sValTypeID) {
        if(sVal!=null && !sVal.isEmpty()){
            try{
                if(sValTypeID.equals("NUM")){
                    return Double.valueOf(sVal);
                } else if(sValTypeID.equals("DATE")){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    return format.parse(sVal);
                }else
                    return sVal;
            }
            catch(Exception ex){
                return null;
            }
        }
        return null;
    }

    /**
     * Hàm chuyển đổi một sID thành mệnh đề sử dụng trong where, tùy thuộc sID là 1 id hay nhiều id mà có dạng = hoặc IN
     * Ví dụ:
     *    sID là PL thì đầu ra là = 'PL'
     *    sID là PL1, PL2 thì đầu ra là IN ('PL1', 'PL2')
     * @param sID Một mã hoặc danh sách mã cách nhau bởi dấu ,
     */
    public static String fSQLConvertIdStrForWhere(String sID) throws Exception
    {
        if (sID==null || sID.isEmpty())
            throw new Exception("ID is not null or empty!");
        String[] arr=sID.split(",", 0);
        if (arr.length ==1)
        {
            if (DBSettings.getEnumDBMode()==DBSettings.enumDBMode.SQLServer)
                return " = N'" + fSQLStandardValue(sID) + "'";
            else
                return " = '" + fSQLStandardValue(sID) + "'";
        }
        return " IN (" + fSQLConvertLstStrValForIn(sID, ",") + ")";

    }

    /**
     * Hàm chuẩn hóa ID trong dùng p:dataTable emptyMessage="Không có dữ liệu" dùng DataModel
     * @param sID
     * @return
     */
    public static String fStandardPdataTableID(String sID)
    {
        if (sID == null)
            return "";

         return sID.replaceAll("_", "");
     }

    public static String fFormatNumber(Object obj, String sFormat)
    {
        if (obj==null)
            return "";
        double d=Double.parseDouble(obj.toString());

        ResourcesFactory rf=new ResourcesFactory();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale(rf.getDateLocale()));
        return (new DecimalFormat(sFormat, symbols)).format(d);
    }

    public static String fFormatNumber(double d)
    {
        return fFormatNumber(d, "#,##0.00");
    }

    public static String fFormatNumber(long l)
    {
        return fFormatNumber(l, "#,##0");
    }

    public static String fFormatNumber(int i)
    {
        return fFormatNumber(i, "#,##0");
    }

    public static boolean fStrIsNullOrEmpty(String s)
    {
        if (s==null || s.isEmpty())
            return true;
        return false;
    }
}
