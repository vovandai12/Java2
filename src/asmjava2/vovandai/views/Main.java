package asmjava2.vovandai.views;

import asmjava2.vovandai.helper.Check;
import asmjava2.vovandai.helper.JOP;
import asmjava2.vovandai.model.NhanVien;
import asmjava2.vovandai.model.NhanVienDAO;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VoVanDai-PD05406
 */
public class Main extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();
    DefaultTableModel datalist;
    int pos = -1;
    JOP jop = new JOP();

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        initTable();
        showTime();
        setEnabled();
        setLocationRelativeTo(null);
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }

    //Phương thức vô hiệu hoá btn
    public void setEnabled() {
        if (tableList.getRowCount() == 0) {
            btnNew.setEnabled(false);
            btnSave.setEnabled(false);
            btnDelete.setEnabled(false);
            btnFind.setEnabled(false);

            btnFirst.setEnabled(false);
            btnLast.setEnabled(false);
            btnBack.setEnabled(false);
            btnNext.setEnabled(false);
        } else {
            btnNew.setEnabled(true);
            btnSave.setEnabled(true);
            btnDelete.setEnabled(true);
            btnFind.setEnabled(true);

            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
            btnBack.setEnabled(true);
            btnNext.setEnabled(true);
        }
    }

    //Phương thức tạo cột
    public void initTable() {
        datalist = new DefaultTableModel();
        String[] columns = new String[]{"MÃ", "HỌ VÀ TÊN", "TUỔI", "EMAIL", "LƯƠNG"};
        datalist.setColumnIdentifiers(columns);
        tableList.setModel(datalist);
    }

    //Phương thức đổ dữ liệu lên table
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tableList.getModel();
        model.setRowCount(0);
        for (NhanVien nhanVien : dao.getAlls()) {
            Object[] row = new Object[]{
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getTuoiNV(),
                nhanVien.getEmailNV(),
                nhanVien.getLuongNV()
            };
            model.addRow(row);
        }
    }

    //Hiễn thị giờ máy tính
    public void showTime() {
        Thread t1;
        t1 = new Thread(() -> {
            while (true) {
                try {
                    Date now = new Date();
                    SimpleDateFormat formater = new SimpleDateFormat();
                    formater.applyPattern("hh:mm:ss aa");
                    String time = formater.format(now);
                    btGio.setText(time);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t1.start();
        btGio.setEnabled(false);
    }

    //Phương thức reset
    public void reset() {
        txtEmail.setText("");
        txtLuongNV.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtTuoiNV.setText("");

        txtEmail.setBackground(Color.white);
        txtLuongNV.setBackground(Color.white);
        txtMaNV.setBackground(Color.white);
        txtTenNV.setBackground(Color.white);
        txtTuoiNV.setBackground(Color.white);

        txtEmail.setForeground(Color.black);
        txtLuongNV.setForeground(Color.black);
        txtMaNV.setForeground(Color.black);
        txtTenNV.setForeground(Color.black);
        txtTuoiNV.setForeground(Color.black);
    }

    //Lấy dữ liệu trên form
    public NhanVien getModel() {
        NhanVien model = new NhanVien();
        try {
            //Kiểm tra mã nhân viên
            if (Check.checkString(txtMaNV.getText()) == -1) {
                jop.showMessage(this, "Không để trống mã nhân viên.", "Lỗi...!!!");
                txtMaNV.setForeground(Color.white);
                txtMaNV.setBackground(Color.red);
                return null;
            } else {
                if (Check.checkMa(txtMaNV.getText()) == -1) {
                    jop.showMessage(this, "Mã nhân viên không đúng định dạng.", "Lỗi...!!!");
                    txtMaNV.setForeground(Color.white);
                    txtMaNV.setBackground(Color.red);
                    return null;
                } else {
                    model.setMaNV(txtMaNV.getText());
                }
            }

            //Kiểm tra họ và tên
            if (Check.checkString(txtTenNV.getText()) == -1) {
                jop.showMessage(this, "Không để trống tên nhân viên.", "Lỗi...!!!");
                txtTenNV.setForeground(Color.white);
                txtTenNV.setBackground(Color.red);
                return null;
            } else {
                model.setTenNV(txtTenNV.getText());
            }

            //Kiểm tra tuổi 
            if (Check.checkString(txtTuoiNV.getText()) == -1) {
                jop.showMessage(this, "Không để trống tuổi nhân viên.", "Lỗi...!!!");
                txtTuoiNV.setForeground(Color.white);
                txtTuoiNV.setBackground(Color.red);
                return null;
            } else {
                try {
                    if (Check.checkAge(Integer.valueOf(txtTuoiNV.getText())) == -1) {
                        jop.showMessage(this, "Tuổi nhân viên từ 16 -> 55.", "Lỗi...!!!");
                        txtTuoiNV.setForeground(Color.white);
                        txtTuoiNV.setBackground(Color.red);
                        return null;
                    } else {
                        model.setTuoiNV(Integer.valueOf(txtTuoiNV.getText()));
                    }
                } catch (Exception e) {
                    System.out.println("=>Lỗi: " + e.toString());
                    jop.showMessage(this, "Giá trị tuổi phải là kiểu số.", "Lỗi...!!!");
                    txtTuoiNV.setForeground(Color.white);
                    txtTuoiNV.setBackground(Color.red);
                    return null;
                }
            }

            //Kiểm tra email
            if (Check.checkString(txtEmail.getText()) == -1) {
                jop.showMessage(this, "Không để trống email nhân viên.", "Lỗi...!!!");
                txtEmail.setForeground(Color.white);
                txtEmail.setBackground(Color.red);
                return null;
            } else {
                if (Check.checkEmail(txtEmail.getText()) == -1) {
                    jop.showMessage(this, "Không đúng định dạng email.", "Lỗi...!!!");
                    txtEmail.setForeground(Color.white);
                    txtEmail.setBackground(Color.red);
                    return null;
                } else {
                    model.setEmailNV(txtEmail.getText());
                }
            }

            //Kiểm tra lương
            if (Check.checkString(txtLuongNV.getText()) == -1) {
                jop.showMessage(this, "Không để trống lương nhân viên.", "Lỗi...!!!");
                txtLuongNV.setForeground(Color.white);
                txtLuongNV.setBackground(Color.red);
                return null;
            } else {
                try {
                    if (Check.checkSalary(Double.valueOf(txtLuongNV.getText())) == -1) {
                        jop.showMessage(this, "Lương phải >= 5.000.000.", "Lỗi...!!!");
                        txtLuongNV.setForeground(Color.white);
                        txtLuongNV.setBackground(Color.red);
                        return null;
                    } else {
                        model.setLuongNV(Double.valueOf(txtLuongNV.getText()));
                    }
                } catch (Exception e) {
                    System.out.println("=>Lỗi: " + e.toString());
                    jop.showMessage(this, "Giá trị lương là kiểu số.", "Lỗi...!!!");
                    txtLuongNV.setForeground(Color.white);
                    txtLuongNV.setBackground(Color.red);
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("=>Lỗi: " + e.toString());
            jop.showError(this, e.getMessage(), "Lỗi...!!!");
        }
        return model;
    }

    //Hiễn thị nhân viên theo pos
    public void setModel(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtTenNV.setText(nv.getTenNV());
        txtTuoiNV.setText(String.valueOf(nv.getTuoiNV()));
        txtEmail.setText(nv.getEmailNV());
        txtLuongNV.setText(String.valueOf(nv.getLuongNV()));
    }

    //Phương thức lưu
    public void save() {
        NhanVien model = getModel();
        if (model == null) {
            jop.showError(this, "Không thể lưu hoặc cập nhập nhân viên.", "Lỗi...!!!");
        } else {
            if (dao.save(model) >= 0) {
                jop.showMessage(this, "Lưu nhân viên thành công.", "Thành công.");
                reset();
                fillTable();
                record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
            } else {
                jop.showError(this, "Lưu nhân viên không thành công.", "Thất bại...!!!");
            }
        }
    }

    //Phương thức xoá
    public void delete() {
        String maNV = jop.showInputDialog(this, "Nhập mã nhân viên cần xoá ?");
        if (Check.checkString(maNV) < 0) {
            jop.showError(this, "Bạn không nhập mã nhân viên.", "Lỗi ...!!!");
        } else {
            pos = dao.delete(maNV);
            if (pos < 0) {
                jop.showMessage(this, "Không tìm thấy nhân viên.", "Thông báo ...!!!");
            } else {
                jop.showMessage(this, "Xoá nhân viên thành công.", "Thông báo ...!!!");
                reset();
                fillTable();
            }
        }
    }

    //Phương thức tìm kiếm
    public void find() {
        String maNV = jop.showInputDialog(this, "Nhập mã nhân viên cần tìm ?");
        if (Check.checkString(maNV) < 0) {
            jop.showError(this, "Bạn không nhập mã nhân viên.", "Lỗi ...!!!");
        } else {
            pos = dao.findByMa(maNV);
            if (pos < 0) {
                jop.showMessage(this, "Không tìm thấy nhân viên.", "Thông báo ...!!!");
            } else {
                setModel(dao.getNhanVienByPossition(pos));
            }
        }
    }

    //
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtTuoiNV = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLuongNV = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btGio = new javax.swing.JLabel();
        record = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÂN VIÊN");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel2.setText("MÃ NHÂN VIÊN");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel3.setText("HỌ VÀ TÊN");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel4.setText("TUỔI");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel5.setText("EMAIL");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel6.setText("LƯƠNG");

        btnFirst.setText("| <");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setText("< <");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText("> >");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText("> |");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ", "HỌ VÀ TÊN", "TUỔI", "EMAIL", "LƯƠNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setText("FIND");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnOpen.setText("OPEN");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNew)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnFind)
                .addGap(18, 18, 18)
                .addComponent(btnOpen)
                .addGap(18, 18, 18)
                .addComponent(btnExit)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btGio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btGio.setForeground(new java.awt.Color(153, 0, 0));
        btGio.setText("jLabel7");

        record.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        record.setForeground(new java.awt.Color(204, 0, 0));
        record.setText("record 1 of 10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(283, 283, 283)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(62, 62, 62)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTuoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(62, 62, 62)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGio, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(record))
                .addGap(89, 89, 89))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btGio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtTuoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtLuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnBack)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(record))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        // TODO add your handling code here:
        int selected = tableList.getSelectedRow();
        setModel(dao.getNhanVienByPossition(selected));
    }//GEN-LAST:event_tableListMouseClicked

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        find();
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int result = jop.showYNO(this, "Bạn có muốn thoát chương trình ?", "Thoát");
        if (result == JOptionPane.YES_OPTION) {
            dao.writeFile();
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        //TODO add your handling code here:
        dao.openFile();
        fillTable();
        setEnabled();
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        pos = 0;
        NhanVien nhanVien = dao.getNhanVienByPossition(pos);
        setModel(nhanVien);
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        pos = dao.count() - 1;
        NhanVien nhanVien = dao.getNhanVienByPossition(pos);
        setModel(nhanVien);
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        pos++;
        if (pos >= dao.count() - 1) {
            pos = dao.count() - 1;
        }
        NhanVien nhanVien = dao.getNhanVienByPossition(pos);
        setModel(nhanVien);
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        pos--;
        if (pos <= 0) {
            pos = 0;
        }
        NhanVien nhanVien = dao.getNhanVienByPossition(pos);
        setModel(nhanVien);
        record.setText("Record - " + (pos + 1) + "- of - " + dao.count() + "-");
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btGio;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel record;
    private javax.swing.JTable tableList;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLuongNV;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTuoiNV;
    // End of variables declaration//GEN-END:variables

}
