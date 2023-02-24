/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.interfaces;

import asmjava2.vovandai.model.NhanVien;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface itfNhanVien {

//Lưu
    int save(NhanVien nhanvien);

//Xoá
    int delete(String maNV);

//Tìm theo mã
    int findByMa(String maNV);

//Lấy nhân viên theo vị trí
    NhanVien getNhanVienByPossition(int index);

//Mở file -> đọc file
    List<NhanVien> openFile();

//Ghi file
    void writeFile();

//List nhân viên
    List<NhanVien> getAlls();

//Đếm vị trí
    int count();
}
