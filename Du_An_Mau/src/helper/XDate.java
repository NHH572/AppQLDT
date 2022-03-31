/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hp
 */
public class XDate {
    public static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    // Chuyển đổi String sang Date => đưa vào data
    public static Date toDate(String date,String...pattern){
        try {
            if(pattern.length>0)DATE_FORMATER.applyPattern(pattern[0]);
            if(date==null)return XDate.now();
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex); 
        }
    }
    
    //chuyển Date sang String
    /*
    @param date chuyền vào date kiểu date
    @param pattern định dạng date
    return date kiểu String đã định theo dạng pattern
    */
    public static String toString(Date date, String...pattern){
        if(pattern.length>0)DATE_FORMATER.applyPattern(pattern[0]);
        if(date==null)date=XDate.now();
        return DATE_FORMATER.format(date);
    }

    
    // bổ sung thêm ngày sau date
    public static Date addDays(Date date, long days){
            date.setTime(date.getTime()+days*24*60*60*1000);
            return date;
    }
    
    public static Date now() {
        return new Date();   //new Date lấy giờ hiện tại
    }
}
