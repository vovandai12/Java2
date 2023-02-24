/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.model;

import asmjava2.vovandai.helper.RWObject;
import java.util.ArrayList;
import java.util.List;
import asmjava2.vovandai.interfaces.itfNhanVien;

/**
 *
 * @author ACER
 */
public class NhanVienDAO implements itfNhanVien {

    public static List<NhanVien> list = new ArrayList<>();

    @Override
    public int save(NhanVien student) {
        int pos = findByMa(student.getMaNV());
        if (pos < 0) {
            list.add(student);
            System.out.println("=>Them NV thanh cong !!!");
        } else {
            list.set(pos, student);
            System.out.println("=>Cap nhap NV thanh cong !!!");
        }
        return 1;
    }

    @Override
    public int delete(String maNV) {
        int pos = findByMa(maNV);
        if (pos >= 0) {
            list.remove(pos);
            System.out.println("=>Xoa NV thanh cong !!!");
        } else {
            System.out.println("=>Khong tim thay NV !!!");
        }
        return pos;
    }

    @Override
    public int findByMa(String maNV) {
        int pos = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaNV().equals(maNV)) {
                pos = i;
                System.out.println("=>Da tim thay NV !!!");
                break;
            } else {
                System.out.println("=>Khong tim thay NV !!! -> " + i);
            }
        }
        return pos;
    }

    @Override
    public void writeFile() {
        try {
            RWObject.writeFile(list);
        } catch (Exception e) {
            System.out.println("=>Lỗi ghi file: " + e.toString());
        }
    }

    @Override
    public List<NhanVien> openFile() {
        //Dữ liệu mẫu
        list.clear();
        try {
            list = RWObject.readFile();
        } catch (Exception e) {
            System.out.println("=>Lỗi đọc file: " + e.toString() + "->" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<NhanVien> getAlls() {
        return list;
    }

    @Override
    public int count() {
        return list.size();
    }

    @Override
    public NhanVien getNhanVienByPossition(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

}
