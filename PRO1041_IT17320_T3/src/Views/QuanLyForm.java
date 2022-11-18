/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModel.QuanLy;
import Services.QuanLyService;
import Services.impl.QuanLyServiceImpl;
import ViewModels.QuanLyViewModel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MyPC
 */
public class QuanLyForm extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyForm
     */
    private QuanLyService qService = new QuanLyServiceImpl();
    ButtonGroup buttonGroupg = new ButtonGroup();
    ButtonGroup buttonGroupt = new ButtonGroup();
    public QuanLyForm() {
        initComponents();
        rdGioiTinh();
        rdTrangThai();
        
        getData();
    }

    public String getGioiTinh(int gioiTinh) {
        if (gioiTinh == 1) {
            return "Nam";
        } else if (gioiTinh == 2) {
            return "Nữ";
        } else {
            return null;
        }
    }

    public String getTrangThai(int trangThai) {
        if (trangThai == 1) {
            return "Đi Làm";
        } else if (trangThai == 2) {
            return "Nghỉ Làm";
        } else {
            return null;
        }
    }

    public void rdGioiTinh() {
        buttonGroupg.add(rdNam);
        buttonGroupg.add(rdNu);
    }

    public void rdTrangThai() {
        buttonGroupt.add(rdDiLam);
        buttonGroupt.add(rdNghiLam);
    }

    public void getData() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbQuanLy.getModel();
        dtm.setRowCount(0);
        for (QuanLyViewModel q : this.qService.getAll()) {
            Object[] rowData = {
                q.getId(), q.getMa(), q.getTen(), q.getNgaySinh(), getGioiTinh(q.getGioiTinh()), q.getDiaChi(), q.getSdt(), getTrangThai(q.getTrangThai())
            };
            dtm.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        rdDiLam = new javax.swing.JRadioButton();
        rdNghiLam = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQuanLy = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 51));
        jLabel1.setText("Quản Lý");

        jLabel2.setText("Id");

        jLabel3.setText("Mã");

        jLabel4.setText("Tên");

        jLabel5.setText("Ngày Sinh");

        jLabel6.setText("Giới Tính");

        lblID.setText("-");

        jLabel8.setText("Địa Chỉ");

        jLabel9.setText("SDT");

        jLabel10.setText("Trạng Thái");

        rdNam.setText("Nam");

        rdNu.setText("Nữ");

        rdDiLam.setText("Đi Làm");

        rdNghiLam.setText("Nghỉ Làm");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tbQuanLy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã", "Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Trạng Thái"
            }
        ));
        tbQuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQuanLyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbQuanLy);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(btnThem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                        .addComponent(btnSua))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMa)
                                            .addComponent(txtTen)
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdDiLam)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdNghiLam))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel6))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rdNam)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(rdNu))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtDiaChi)
                                                        .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(btnXoa))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel1)))
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblID)
                    .addComponent(jLabel6)
                    .addComponent(rdNam)
                    .addComponent(rdNu))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(rdDiLam)
                    .addComponent(rdNghiLam))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        QuanLy q = this.getLoadData();
        if (q == null) {
            return;
        }
        if(rdNam.isSelected()){
            q.setGioiTinh(1);
        }else if(rdNu.isSelected()){
            q.setGioiTinh(2);
        }
        
        if(rdDiLam.isSelected()){
            q.setTrangThai(1);
        }else if(rdNghiLam.isSelected()){
            q.setTrangThai(2);
        }
        this.qService.insert(q);
        this.getData();
        this.clear();
        JOptionPane.showMessageDialog(this, "Thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tbQuanLy.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần sửa");
        }
        QuanLy q = this.getLoadData();
        if (q == null) {
            return;
        }
        if(rdNam.isSelected()){
            q.setGioiTinh(1);
        }else if(rdNu.isSelected()){
            q.setGioiTinh(2);
        }
        
        if(rdDiLam.isSelected()){
            q.setTrangThai(1);
        }else if(rdNghiLam.isSelected()){
            q.setTrangThai(2);
        }
        this.qService.update(q, q.getId());
        this.getData();
        this.clear();
        JOptionPane.showMessageDialog(this, "Sửa thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tbQuanLy.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần xóa");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa??");
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String id = tbQuanLy.getValueAt(row, 0).toString();
                this.qService.delete(id);
                this.getData();
                this.clear();
            }
        }
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbQuanLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQuanLyMouseClicked
        // TODO add your handling code here:
        int row = tbQuanLy.getSelectedRow();
        lblID.setText(tbQuanLy.getValueAt(row, 0).toString());
        txtMa.setText(tbQuanLy.getValueAt(row, 1).toString());
        txtTen.setText(tbQuanLy.getValueAt(row, 2).toString());
        txtNgaySinh.setText(tbQuanLy.getValueAt(row, 3).toString());
        if (tbQuanLy.getValueAt(row, 4).equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        txtDiaChi.setText(tbQuanLy.getValueAt(row, 5).toString());
        txtSdt.setText(tbQuanLy.getValueAt(row, 6).toString());
        if (tbQuanLy.getValueAt(row, 7).equals("Đi Làm")) {
            rdDiLam.setSelected(true);
        } else {
            rdNghiLam.setSelected(true);
        }
    }//GEN-LAST:event_tbQuanLyMouseClicked

    private QuanLy getLoadData() {
        String id = this.lblID.getText();
        String ma = this.txtMa.getText().trim();
        String ten = this.txtTen.getText().trim();
        String ns = this.txtNgaySinh.getText().trim();
        String dc = this.txtDiaChi.getText().trim();
        String sdt = this.txtSdt.getText().trim();
        if (ma.equals("") || ten.equals("") || ns.equals("") || dc.equals("") || sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống!!!");
            return null;
        }
        QuanLy q = new QuanLy(id, ma, ten, ns, WIDTH, dc, sdt, WIDTH);
        return q;
    }

    private void clear() {
        lblID.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        buttonGroupg.clearSelection();
        txtDiaChi.setText("");
        txtSdt.setText("");
        buttonGroupt.clearSelection();
    }

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
            java.util.logging.Logger.getLogger(QuanLyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JRadioButton rdDiLam;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNghiLam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbQuanLy;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}