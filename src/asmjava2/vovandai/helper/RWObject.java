/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.helper;

import asmjava2.vovandai.model.NhanVien;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
//RWObject đọc và ghi đối tượng object
public class RWObject {

    public static final String FILE_NAME = "nhanvien.dat";

    //Hàm ghi file
    public static void writeFile(List<NhanVien> list) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    //Hàm đọc file
    public static  List<NhanVien> readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<NhanVien> list = new ArrayList<>();
        list = (List<NhanVien>) ois.readObject();
        return list;
    }
}
