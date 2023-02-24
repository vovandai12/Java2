/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.helper;

import java.util.regex.Pattern;

/**
 *
 * @author ACER
 */
public class Check {

    //Kiểm tra chuỗi có bằng rỗng
    public static int checkString(String text) {
        if ("".equals(text)) {
            System.out.println("Chuoi bang rong...!");
            return -1;
        } else {
            return 1;
        }
    }

    //Kiểm tra định dạng mã
    public static int checkMa(String text) {
        String maPattern = "^(NV)\\d";
        Pattern pattern = Pattern.compile(maPattern);
        if (pattern.matcher(text).find()) {
            System.out.println("Dung dinh dang ma...!");
            return 1;
        } else {
            System.out.println("Ma sai dinh dang...!");
            return -1;
        }
    }

    //Kiểm tra định dạng email
    public static int checkEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (pattern.matcher(email).find()) {
            System.out.println("Dung dinh dang email...!");
            return 1;
        } else {
            System.out.println("Sai dinh dang email...!");
            return -1;
        }
    }

    //Kiểm tra độ tuổi
    public static int checkAge(int age) {
        if ("".equals(age)) {
            System.out.println("Tuoi bang rong...!");
            return -1;
        } else {
            if (age <= 16 || 55 <= age) {
                System.out.println("16 <= Tuoi <= 55...!");
                return -1;
            } else {
                return 1;
            }
        }
    }

    //Kiểm tra lương
    public static int checkSalary(double salary) {
        if (salary >= 5000000) {
            return 1;
        } else {
            System.out.println("Luong phai tren 5000000");
            return -1;
        }
    }
}
