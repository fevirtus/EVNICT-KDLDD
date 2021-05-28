package evnit.util.setting;

/**
 *
 * @author khiemvk
 */
public class DBSettings {

    public enum enumDBMode {
        Oracle,
        SQLServer
    }

    private static enumDBMode m_EnumDBMode;

    public static enumDBMode getEnumDBMode(){        
        if(m_EnumDBMode==null)
            m_EnumDBMode = getDBSettings();
        return m_EnumDBMode;
    }

    private static enumDBMode getDBSettings(){
        enumDBMode dBMode = enumDBMode.SQLServer;
        try{
            ResourcesFactory rf = new ResourcesFactory("resources/DBSettings");
            String strActive = rf.getMessage("DB_ORACLE");
            if(strActive.equals("1"))
                dBMode = enumDBMode.Oracle;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dBMode;
    }
}
