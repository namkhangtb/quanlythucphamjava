/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giao_dien;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author DELL
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public frmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp_main = new javax.swing.JSplitPane();
        sp_trai = new javax.swing.JPanel();
        btn_khachhang = new javax.swing.JButton();
        btn_sanpham = new javax.swing.JButton();
        btn_hoadon = new javax.swing.JButton();
        btn_thongke = new javax.swing.JButton();
        btn_dangxuat = new javax.swing.JButton();
        sp_phai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_khachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder_guest_1642861.png"))); // NOI18N
        btn_khachhang.setText("Khách hàng");
        btn_khachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_khachhangMouseClicked(evt);
            }
        });

        btn_sanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sanpham.png"))); // NOI18N
        btn_sanpham.setText("Sản phẩm");
        btn_sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sanphamMouseClicked(evt);
            }
        });

        btn_hoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hoadon.png"))); // NOI18N
        btn_hoadon.setText("Hoá Đơn");
        btn_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hoadonMouseClicked(evt);
            }
        });

        btn_thongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thongke.png"))); // NOI18N
        btn_thongke.setText("Thống kê");
        btn_thongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_thongkeMouseClicked(evt);
            }
        });

        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dangxuat.png"))); // NOI18N
        btn_dangxuat.setText("Đăng xuất");
        btn_dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dangxuatMouseClicked(evt);
            }
        });
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sp_traiLayout = new javax.swing.GroupLayout(sp_trai);
        sp_trai.setLayout(sp_traiLayout);
        sp_traiLayout.setHorizontalGroup(
            sp_traiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_khachhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addComponent(btn_sanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_hoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_thongke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_dangxuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sp_traiLayout.setVerticalGroup(
            sp_traiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sp_traiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_khachhang)
                .addGap(18, 18, 18)
                .addComponent(btn_sanpham)
                .addGap(18, 18, 18)
                .addComponent(btn_hoadon)
                .addGap(18, 18, 18)
                .addComponent(btn_thongke)
                .addGap(18, 18, 18)
                .addComponent(btn_dangxuat)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        sp_main.setLeftComponent(sp_trai);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cuahang.jpg"))); // NOI18N

        javax.swing.GroupLayout sp_phaiLayout = new javax.swing.GroupLayout(sp_phai);
        sp_phai.setLayout(sp_phaiLayout);
        sp_phaiLayout.setHorizontalGroup(
            sp_phaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sp_phaiLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sp_phaiLayout.setVerticalGroup(
            sp_phaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        sp_main.setRightComponent(sp_phai);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp_main)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp_main, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_khachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_khachhangMouseClicked
        frm_KhachHang frmkh = null;
        try {
            frmkh = new frm_KhachHang();
        } catch (SQLException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        sp_main.setRightComponent(frmkh);
    }//GEN-LAST:event_btn_khachhangMouseClicked

    private void btn_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sanphamMouseClicked
        frm_SanPham frmsp = null;
        try {
            frmsp = new frm_SanPham();
        } catch (SQLException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        sp_main.setRightComponent(frmsp);
    }//GEN-LAST:event_btn_sanphamMouseClicked

    private void btn_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hoadonMouseClicked
        frm_HoaDon fmhd = null;
        try {
            // TODO add your handling code here:
            fmhd = new frm_HoaDon();
        } catch (SQLException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        sp_main.setRightComponent(fmhd);
    }//GEN-LAST:event_btn_hoadonMouseClicked

    private void btn_thongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_thongkeMouseClicked
        // TODO add your handling code here:
        frm_ThongKe fmtk = null;
        try {
            // TODO add your handling code here:
            fmtk = new frm_ThongKe();
        } catch (SQLException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        sp_main.setRightComponent(fmtk);
    }//GEN-LAST:event_btn_thongkeMouseClicked

    private void btn_dangxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dangxuatMouseClicked
        // TODO add your handling code here:
        login frm = new login();
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_dangxuatMouseClicked

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dangxuatActionPerformed

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JButton btn_hoadon;
    private javax.swing.JButton btn_khachhang;
    private javax.swing.JButton btn_sanpham;
    private javax.swing.JButton btn_thongke;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane sp_main;
    private javax.swing.JPanel sp_phai;
    private javax.swing.JPanel sp_trai;
    // End of variables declaration//GEN-END:variables
}