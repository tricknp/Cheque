package util;

import java.time.LocalDate;

public class Util {
    
    public static String convertLocalDateUStoBR(LocalDate ldate) {
        String data = ldate.toString().replaceAll("-", "/");
        String[] s = data.split("/");
        return s[2]+"/"+s[1]+"/"+s[0];
    }
    
    public static String convertLocalDateBRtoUS(String dataTxt) {
        String data = dataTxt.replaceAll("/", "-");
        String[] s = data.split("-");
        return s[2]+"-"+s[1]+"-"+s[0];
    }

    
    
}
