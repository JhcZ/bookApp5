package cdu.jhc.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

//自定义EL函数类
public class MyFunction {
    public static String formatMoney(BigDecimal money){
        return DecimalFormat.getCurrencyInstance(Locale.CHINA).format(money);
    }

    public static String formatDateTime(long date){
        return new SimpleDateFormat("yyyy-M-d HH:mm:ss",Locale.CHINA).format(date);
    }

    public static String formatDateShort(Date date){
        return new SimpleDateFormat("yyyy年M月",Locale.CHINA).format(date);
    }

    public static String formatDateLong(Date date){
        return new SimpleDateFormat("yyyy年M月d日",Locale.CHINA).format(date);
    }

    public static String formatDateShort(long date){
        return new SimpleDateFormat("yyyy年M月",Locale.CHINA).format(date);
    }

    public static String formatDateLong(long date){
        return new SimpleDateFormat("yyyy年M月d日",Locale.CHINA).format(date);
    }
}
