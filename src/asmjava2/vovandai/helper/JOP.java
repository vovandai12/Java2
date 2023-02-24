/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class JOP {
    //Thông báo lỗi
    public void showError(Component parentComponent, String mess, String title) {
        JOptionPane.showMessageDialog(parentComponent, mess, title, JOptionPane.ERROR_MESSAGE);
    }

    //Thông báo
    public void showMessage(Component parentComponent, String mess, String title) {
        JOptionPane.showMessageDialog(parentComponent, mess, title, JOptionPane.INFORMATION_MESSAGE);
    }

    //Y - N
    public int showYNO(Component parentComponent, String mess, String title) {
        int result = JOptionPane.showConfirmDialog(parentComponent, mess, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result;
    }

    //ô nhập
    public String showInputDialog(Component parentComponent, String mess) {
        return JOptionPane.showInputDialog(parentComponent, mess);
    }

}
