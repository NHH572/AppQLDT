/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class MsgBox {
//    public static void showMessageDialog(Component parent, String content, String title ){
//        JOptionPane.showMessageDialog(parent, content, title,JOptionPane.INFORMATION_MESSAGE);
//    }
//    
//    public static void showErrorDialog(Component parent, String content, String title ){
//        JOptionPane.showMessageDialog(parent, content, title,JOptionPane.ERROR_MESSAGE);
//    }
//    
//    public static int showConfrimDialog(Component parent, String content, String title ){
//        int choose = JOptionPane.showConfirmDialog(parent, content, title,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        return choose;
//    }
    
    public static void alert(Component parent,String messages){
        JOptionPane.showMessageDialog(parent, messages, "Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component parent,String messages){
        int result = JOptionPane.showConfirmDialog(parent, messages, "Hệ thống quản lý đào tạo",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component parent,String messages){
        return JOptionPane.showInputDialog(parent, messages, "Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
}
