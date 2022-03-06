/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giao_dien;

import entity.ChiTietPhieuNhap;
import entity.LoaiSanPham;
import entity.NhaPhanPhoi;
import entity.NhanVien;
import entity.PhieuNhap;
import entity.SanPham;
import entity.SanPham_LoaiSanPham;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class frm_SanPham extends javax.swing.JPanel {
    String find_name = "";
    /**
     * Creates new form frm_SanPham
     */
    public frm_SanPham() throws SQLException, ClassNotFoundException {
        initComponents();
        loadDataCombobox_inSanPham();
        LoadData_SanPham();
        
        loadDataCombobox_NhanVien_inPhieuNhap();
        loadDataCombobox_NhaPhanPhoi_inPhieuNhap();
        LoadData_LoaiSanPham();
        LoadData_SanPham_inLoaiSanPham();
        
        LoadData_PhieuNhap(); 
        loadDataCombobox_SanPham_inChiTietPhieuNhap();
        LoadData_ChiTietPhieuNhap();
    }

    public void loadDataCombobox_inSanPham() throws ClassNotFoundException, SQLException {
        List<LoaiSanPham> lstLoaiSanPham = dao.dao_LoaiSanPham.getAllLSP();
        cb_loaisanpham.removeAllItems();
        for(LoaiSanPham sp : lstLoaiSanPham){
            cb_loaisanpham.addItem(sp.getTenloaisanpham());
        }
    }
    public void loadDataCombobox_NhanVien_inPhieuNhap() throws ClassNotFoundException, SQLException {
        List<NhanVien> lstNhanVien = dao.dao_NhanVien.getAll();
        cb_nhanvien.removeAllItems();
        for(NhanVien nv : lstNhanVien){
            cb_nhanvien.addItem(nv.getTenNV());
        }
    }
    public void loadDataCombobox_NhaPhanPhoi_inPhieuNhap() throws ClassNotFoundException, SQLException {
        List<NhaPhanPhoi> lstNhanVien = dao.dao_NhaPhanPhoi.getAllNPP();
        cb_nhaphanphoi.removeAllItems();
        for(NhaPhanPhoi nv : lstNhanVien){
            cb_nhaphanphoi.addItem(nv.getTennhaphanphoi());
        }
    }
    public void loadDataCombobox_SanPham_inChiTietPhieuNhap() throws ClassNotFoundException, SQLException {
        List<SanPham> lstSanPham = dao.dao_SanPham.getAllSP();
        cb_sanpham.removeAllItems();
        for(SanPham nv : lstSanPham){
            cb_sanpham.addItem(nv.getTensanpham());
        }
    }
    
    private void LoadData_SanPham() throws SQLException, ClassNotFoundException{
        List<SanPham> lst_SanPham = dao.dao_SanPham.getAllSP();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Loại sản phẩm");
        tblModel.addColumn("Nơi sản xuất");
        tblModel.addColumn("Giá nhập");
        tblModel.addColumn("Giá bán");
        tblModel.addColumn("Tồn kho");
        for (SanPham sp : lst_SanPham) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(sp.getMasanpham()));
            row.addElement(sp.getTensanpham()); 
            row.addElement(String.valueOf(sp.getMaloaisanpham()));                
            row.addElement(sp.getNoisanxuat());
            row.addElement(sp.getGianhap());
            row.addElement(sp.getGiaban());
            row.addElement(String.valueOf(sp.getTonkho()));
            tblModel.addRow(row);
        }
        tb_sanpham.setModel(tblModel);
    }
    private void loadData_control_tb_sanpham() throws ClassNotFoundException, SQLException, ParseException{
        if (tb_sanpham.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tb_sanpham.getSelectedRow();
            //-- Lấy được danh sách sản phẩm
            List<SanPham> lstSanPham = dao.dao_SanPham.getAllSP();
            //-- Xác định là sản phẩm nào đang được click
            
            SanPham sp = lstSanPham.get(i);
            //--- Set giá trị cho các control
            txt_masanpham.setText(String.valueOf(sp.getMasanpham()));
            txt_tensanpham.setText(sp.getTensanpham());
            
            //LoaiSanPham lsp = dao.dao_LoaiSanPham.getOneLoaiSanPham(String.valueOf(sp.getMaloaisanpham()));
            cb_loaisanpham.setSelectedIndex(sp.getMaloaisanpham()-1);
            
            txt_noisanxuat.setText(sp.getNoisanxuat());
            txt_gianhap.setText(sp.getGianhap());
            txt_giaban.setText(sp.getGiaban());
            txt_tonkho.setText(String.valueOf(sp.getTonkho()));
        }
    }
    
    private void LoadData_LoaiSanPham() throws SQLException, ClassNotFoundException{
        List<LoaiSanPham> lst_LoaiSanPham = dao.dao_LoaiSanPham.getAllLSP();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã loại sản phẩm");
        tblModel.addColumn("Tên loại sản phẩm");
        for (LoaiSanPham sp : lst_LoaiSanPham) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(sp.getMaloaisanpham()));
            row.addElement(sp.getTenloaisanpham());;
            tblModel.addRow(row);
        }
        tb_loaisanpham.setModel(tblModel);
    }
    private void loadData_control_tb_loaisanpham() throws ClassNotFoundException, SQLException, ParseException{
        if (tb_loaisanpham.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tb_loaisanpham.getSelectedRow();
            //-- Lấy được danh sách sinh viên
            List<LoaiSanPham> lst_LoaiSanPham = dao.dao_LoaiSanPham.getAllLSP();
            //-- Xác định là sinh viên nào đang được click
            
            LoaiSanPham lsp = lst_LoaiSanPham.get(i);
            //--- Set giá trị cho các control
            txt_maloaisanpham.setText(String.valueOf(lsp.getMaloaisanpham()));
            txt_tenloaisanpham.setText(lsp.getTenloaisanpham());
            
            //FIX SAU
            
        }
    }
    
    private void loadData_tableSanPham_inLoaiSanPham_afterClick_tbLoaiSanPham() throws ClassNotFoundException, SQLException{
        List<SanPham_LoaiSanPham> lst_SanPham_LoaiSanPham = dao.dao_SanPham_LoaiSanPham.getSanPham_LoaiSanPham(txt_tenloaisanpham.getText());
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Tên loại sản phẩm");
        for (SanPham_LoaiSanPham sp : lst_SanPham_LoaiSanPham) {
        //SanPham_LoaiSanPham sp = new SanPham_LoaiSanPham();
        Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(sp.getMasanpham()));
            row.addElement(sp.getTensanpham());;
            row.addElement(sp.getTenloaisanpham());
            tblModel.addRow(row);
        }
        tb_sanpham_in_panel_loaisanpham.setModel(tblModel);
    }
   
    
    private void LoadData_SanPham_inLoaiSanPham() throws SQLException, ClassNotFoundException{
        List<SanPham_LoaiSanPham> lst_SanPham_LoaiSanPham = dao.dao_SanPham_LoaiSanPham.getAllSP_LSP();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Tên loại sản phẩm");
        for (SanPham_LoaiSanPham sp_lsp : lst_SanPham_LoaiSanPham) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(sp_lsp.getMasanpham()));
            row.addElement(sp_lsp.getTensanpham());;
            row.addElement(sp_lsp.getTenloaisanpham());;
            tblModel.addRow(row);
        }
        tb_sanpham_in_panel_loaisanpham.setModel(tblModel);
    }

    private void LoadData_PhieuNhap() throws ClassNotFoundException, SQLException{
        List<PhieuNhap> lst_PhieuNhap = dao.dao_PhieuNhap.getAllPN();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã phiếu nhập");
        tblModel.addColumn("Mã nhân viên");
        tblModel.addColumn("Mã nhà cung cấp");      
        tblModel.addColumn("Ngày nhập");   
        tblModel.addColumn("Tổng tiền"); 
        for (PhieuNhap pn : lst_PhieuNhap) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(pn.getMaphieunhap()));
            row.addElement(String.valueOf(pn.getManhanvien()));
            row.addElement(String.valueOf(pn.getManhaphanphoi()));   
            row.addElement(pn.getNgaynhap());;
            row.addElement(pn.getTongtien());;
            tblModel.addRow(row);
        }
        tb_phieunhap.setModel(tblModel);
    }
    private void LoadData_PhieuNhap_control() throws ParseException, ClassNotFoundException, SQLException{
        if (tb_phieunhap.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tb_phieunhap.getSelectedRow();
            
            //-- Lấy được danh sách sinh viên
            List<PhieuNhap> lst_pn = dao.dao_PhieuNhap.getAllPN();
            //-- Xác định là sinh viên nào đang được click
            
            PhieuNhap pn = lst_pn.get(i);
            //--- Set giá trị cho các control
            txt_maphieunhap.setText(String.valueOf(pn.getMaphieunhap()));
            cb_nhanvien.setSelectedIndex(pn.getManhanvien()-1);
            cb_nhaphanphoi.setSelectedIndex(pn.getManhaphanphoi()-1);
            //cb_nhanvien.setSelectedItem(pn.getManhanvien());
            //cb_nhaphanphoi.setSelectedItem(pn.getManhaphanphoi());
            txt_tongtien_phieunhap.setText(pn.getTongtien());
            //- Set giá trị của components JXdatepicker
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dtn_ngaynhap.setDate(dateFormat.parse(pn.getNgaynhap()));
        }
    }
    
    private void LoadData_ChiTietPhieuNhap() throws ClassNotFoundException, SQLException{
        List<ChiTietPhieuNhap> lst_ChiTietPhieuNhap = dao.dao_ChiTietPhieuNhap.getAllCTPN();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã CTPN");
        tblModel.addColumn("Mã phiếu nhập");
        tblModel.addColumn("Mã sản phẩm");      
        tblModel.addColumn("Số lượng");   
        tblModel.addColumn("Đơn giá"); 
        for (ChiTietPhieuNhap ctpn : lst_ChiTietPhieuNhap) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(ctpn.getMactpn()));
            row.addElement(String.valueOf(ctpn.getMaphieunhap()));
            row.addElement(String.valueOf(ctpn.getMasanpham()));   
            row.addElement(String.valueOf(ctpn.getSoluong()));;
            row.addElement(ctpn.getDongia());;
            tblModel.addRow(row);
        }
        tb_chitietphieunhap.setModel(tblModel);
    }
    private void LoadData_ChiTietPhieuNhap_control() throws ParseException, ClassNotFoundException, SQLException{
        if (tb_chitietphieunhap.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tb_chitietphieunhap.getSelectedRow();
            
            //-- Lấy được danh sách sinh viên
            List<ChiTietPhieuNhap> lst_ctpn = dao.dao_ChiTietPhieuNhap.getAllCTPN();
            //-- Xác định là sinh viên nào đang được click
            
            ChiTietPhieuNhap ctpn = lst_ctpn.get(i);
            //--- Set giá trị cho các control
            txt_mactpn.setText(String.valueOf(ctpn.getMactpn()));
            txt_phieunhap_in_table_chitietphieunhap.setText(String.valueOf(ctpn.getMaphieunhap()));
            cb_sanpham.setSelectedIndex(ctpn.getMasanpham()-1);
            txt_soluong.setText(String.valueOf(ctpn.getSoluong()));
            txt_tongtien_chitietphieunhap.setText(ctpn.getDongia());
        }
    }
    
    private void findSP() throws ClassNotFoundException, SQLException{
    List<SanPham> lstSanPham = dao.dao_SanPham.searchSP(find_name);
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Loại sản phẩm");
        tblModel.addColumn("Nơi sản xuất");
        tblModel.addColumn("Giá nhập");
        tblModel.addColumn("Giá bán");
        tblModel.addColumn("Tồn kho");
        for (SanPham sp : lstSanPham) {
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(sp.getMasanpham()));
            row.addElement(sp.getTensanpham()); 
            row.addElement(String.valueOf(sp.getMaloaisanpham()));                
            row.addElement(sp.getNoisanxuat());
            row.addElement(sp.getGianhap());
            row.addElement(sp.getGiaban());
            row.addElement(String.valueOf(sp.getTonkho()));
            tblModel.addRow(row);
        }
        tb_sanpham.setModel(tblModel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_masanpham = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_tensanpham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_loaisanpham = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_noisanxuat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tonkho = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_gianhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_giaban = new javax.swing.JTextField();
        btn_themsanpham = new javax.swing.JButton();
        btn_suasanpham = new javax.swing.JButton();
        btn_xoasanpham = new javax.swing.JButton();
        btn_resetsanpham = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_sanpham = new org.jdesktop.swingx.JXTable();
        txt_timsanpham = new javax.swing.JTextField();
        btn_timsanpham = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_maloaisanpham = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_tenloaisanpham = new javax.swing.JTextField();
        btn_themmaloaisanpham = new javax.swing.JButton();
        btn_suamaloaisanpham = new javax.swing.JButton();
        btn_resetmaloaisanpham = new javax.swing.JButton();
        btn_xoamaloaisanpham = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_loaisanpham = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_sanpham_in_panel_loaisanpham = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_maphieunhap = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cb_nhanvien = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        dtn_ngaynhap = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        cb_nhaphanphoi = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txt_tongtien_phieunhap = new javax.swing.JTextField();
        btn_themphieunhap = new javax.swing.JButton();
        btn_xoaphieunhap = new javax.swing.JButton();
        btn_suaphieunhap = new javax.swing.JButton();
        btn_resetphieunhap = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_soluong = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cb_sanpham = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_tongtien_chitietphieunhap = new javax.swing.JTextField();
        btn_themctpn = new javax.swing.JButton();
        btn_xoactpn = new javax.swing.JButton();
        btn_suactpn = new javax.swing.JButton();
        btn_resetctpn = new javax.swing.JButton();
        txt_mactpn = new javax.swing.JTextField();
        txt_phieunhap_in_table_chitietphieunhap = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_phieunhap = new org.jdesktop.swingx.JXTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_chitietphieunhap = new org.jdesktop.swingx.JXTable();

        jLabel1.setText("Mã Sản Phẩm:");

        jLabel2.setText("Tên Sản Phẩm:");

        jLabel3.setText("Loại Sản Phẩm:");

        cb_loaisanpham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Giá Nhập:");

        jLabel5.setText("Giá Bán:");

        jLabel6.setText("Tồn Kho:");

        jLabel7.setText("Nơi Sản Xuất:");

        btn_themsanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder_Add_728898.png"))); // NOI18N
        btn_themsanpham.setText("Thêm");
        btn_themsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themsanphamMouseClicked(evt);
            }
        });

        btn_suasanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/replace.png"))); // NOI18N
        btn_suasanpham.setText("Sửa");
        btn_suasanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suasanphamMouseClicked(evt);
            }
        });

        btn_xoasanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoasanpham.setText("Xóa");
        btn_xoasanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoasanphamMouseClicked(evt);
            }
        });

        btn_resetsanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rs.png"))); // NOI18N
        btn_resetsanpham.setText("Reset");
        btn_resetsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetsanphamMouseClicked(evt);
            }
        });

        tb_sanpham.setBackground(new java.awt.Color(240, 240, 240));
        tb_sanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_sanphamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_sanpham);

        txt_timsanpham.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timsanphamCaretUpdate(evt);
            }
        });

        btn_timsanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btn_timsanpham.setText("Tìm");
        btn_timsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_timsanphamMouseClicked(evt);
            }
        });

        jLabel24.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_themsanpham)
                        .addGap(38, 38, 38)
                        .addComponent(btn_suasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btn_xoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_masanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_noisanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_loaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btn_resetsanpham))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel24))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_giaban, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_gianhap, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_tonkho, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_timsanpham))
                        .addGap(18, 18, 18)
                        .addComponent(btn_timsanpham)))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_masanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_tensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cb_loaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_tonkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_noisanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_timsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_timsanpham)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_themsanpham)
                    .addComponent(btn_suasanpham)
                    .addComponent(btn_xoasanpham)
                    .addComponent(btn_resetsanpham))
                .addGap(77, 77, 77))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Loại Sản Phẩm");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sản Phẩm");

        jLabel10.setText("Mã Loại Sản Phẩm:");

        jLabel11.setText("Tên Loại Sản Phẩm:");

        btn_themmaloaisanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder_Add_728898.png"))); // NOI18N
        btn_themmaloaisanpham.setText("Thêm");
        btn_themmaloaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themmaloaisanphamMouseClicked(evt);
            }
        });

        btn_suamaloaisanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/replace.png"))); // NOI18N
        btn_suamaloaisanpham.setText("Sửa");
        btn_suamaloaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suamaloaisanphamMouseClicked(evt);
            }
        });

        btn_resetmaloaisanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rs.png"))); // NOI18N
        btn_resetmaloaisanpham.setText("Reset");
        btn_resetmaloaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetmaloaisanphamMouseClicked(evt);
            }
        });

        btn_xoamaloaisanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoamaloaisanpham.setText("Xóa");
        btn_xoamaloaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoamaloaisanphamMouseClicked(evt);
            }
        });

        tb_loaisanpham.setBackground(new java.awt.Color(240, 240, 240));
        tb_loaisanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tb_loaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_loaisanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_loaisanpham);

        tb_sanpham_in_panel_loaisanpham.setBackground(new java.awt.Color(240, 240, 240));
        tb_sanpham_in_panel_loaisanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tb_sanpham_in_panel_loaisanpham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_themmaloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_maloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_suamaloaisanpham)
                                .addGap(30, 30, 30)
                                .addComponent(btn_xoamaloaisanpham)
                                .addGap(30, 30, 30)
                                .addComponent(btn_resetmaloaisanpham))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_maloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_tenloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_themmaloaisanpham)
                            .addComponent(btn_suamaloaisanpham)
                            .addComponent(btn_xoamaloaisanpham)
                            .addComponent(btn_resetmaloaisanpham)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76)
                .addComponent(jLabel9)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loại Sản Phẩm", jPanel3);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Phiếu Nhập");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Chi Tiết Phiếu Nhập");

        jLabel14.setText("Mã Phiếu Nhập:");

        jLabel15.setText("Nhân Viên:");

        cb_nhanvien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Ngày Nhập:");

        jLabel17.setText("Nhà Phân Phối:");

        cb_nhaphanphoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Tổng Tiền:");

        btn_themphieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder_Add_728898.png"))); // NOI18N
        btn_themphieunhap.setText("Thêm");
        btn_themphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themphieunhapMouseClicked(evt);
            }
        });

        btn_xoaphieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoaphieunhap.setText("Xóa");
        btn_xoaphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoaphieunhapMouseClicked(evt);
            }
        });

        btn_suaphieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/replace.png"))); // NOI18N
        btn_suaphieunhap.setText("Sửa");
        btn_suaphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suaphieunhapMouseClicked(evt);
            }
        });

        btn_resetphieunhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rs.png"))); // NOI18N
        btn_resetphieunhap.setText("Reset");
        btn_resetphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetphieunhapMouseClicked(evt);
            }
        });

        jLabel19.setText("Mã CTPN:");

        jLabel20.setText("Mã Phiếu Nhập:");

        jLabel21.setText("Sản Phẩm:");

        cb_sanpham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Số Lượng:");

        jLabel23.setText("Đơn Giá:");

        btn_themctpn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder_Add_728898.png"))); // NOI18N
        btn_themctpn.setText("Thêm");
        btn_themctpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themctpnMouseClicked(evt);
            }
        });

        btn_xoactpn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoactpn.setText("Xóa");
        btn_xoactpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoactpnMouseClicked(evt);
            }
        });

        btn_suactpn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/replace.png"))); // NOI18N
        btn_suactpn.setText("Sửa");
        btn_suactpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suactpnMouseClicked(evt);
            }
        });

        btn_resetctpn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rs.png"))); // NOI18N
        btn_resetctpn.setText("Reset");
        btn_resetctpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetctpnMouseClicked(evt);
            }
        });

        tb_phieunhap.setBackground(new java.awt.Color(240, 240, 240));
        tb_phieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu ", "Nhân Viên Nhập", "Nhà Phân Phối", "Ngày Nhập", "Thành Tiền"
            }
        ));
        tb_phieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_phieunhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_phieunhap);

        tb_chitietphieunhap.setBackground(new java.awt.Color(240, 240, 240));
        tb_chitietphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã CTPN", "Mã Phiếu Nhập", "Sản Phẩm", "Số Lượng", "Đơn Giá"
            }
        ));
        tb_chitietphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_chitietphieunhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_chitietphieunhap);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(48, 48, 48)
                        .addComponent(txt_mactpn, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel16))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtn_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_maphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_nhaphanphoi, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tongtien_phieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_phieunhap_in_table_chitietphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tongtien_chitietphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_themphieunhap)
                                        .addGap(35, 35, 35)
                                        .addComponent(btn_suaphieunhap)
                                        .addGap(35, 35, 35)
                                        .addComponent(btn_xoaphieunhap))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_themctpn)
                                        .addGap(35, 35, 35)
                                        .addComponent(btn_suactpn)
                                        .addGap(35, 35, 35)
                                        .addComponent(btn_xoactpn)))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_resetphieunhap)
                                    .addComponent(btn_resetctpn))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel13)
                .addGap(62, 62, 62))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_maphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cb_nhaphanphoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(dtn_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tongtien_phieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_themphieunhap)
                            .addComponent(btn_suaphieunhap)
                            .addComponent(btn_resetphieunhap)
                            .addComponent(btn_xoaphieunhap)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txt_mactpn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txt_phieunhap_in_table_chitietphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(cb_sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tongtien_chitietphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_themctpn)
                            .addComponent(btn_suactpn)
                            .addComponent(btn_xoactpn)
                            .addComponent(btn_resetctpn)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Phiếu Nhập Hàng", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
//------------------------------------------------------THÊM SỬA XÓA RESET LOẠI SẢN PHẨM----------------------------------------------------------------------------------------------   
    private void btn_xoamaloaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoamaloaisanphamMouseClicked
        try {
            dao.dao_LoaiSanPham.deleteLSP(txt_maloaisanpham.getText());
            LoadData_LoaiSanPham();
            clear_loaisanpham();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xoamaloaisanphamMouseClicked
    private void btn_resetmaloaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetmaloaisanphamMouseClicked
        clear_loaisanpham();
    }//GEN-LAST:event_btn_resetmaloaisanphamMouseClicked
    private void btn_suamaloaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suamaloaisanphamMouseClicked
        LoaiSanPham lsp = new LoaiSanPham();
        lsp.setMaloaisanpham(Integer.valueOf(txt_maloaisanpham.getText()));
        lsp.setTenloaisanpham(txt_tenloaisanpham.getText());

        try {
            dao.dao_LoaiSanPham.updateLSP(lsp);
            LoadData_LoaiSanPham();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_suamaloaisanphamMouseClicked
    private void btn_themmaloaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themmaloaisanphamMouseClicked
        LoaiSanPham lsp = new LoaiSanPham();
        lsp.setMaloaisanpham(Integer.valueOf(txt_maloaisanpham.getText()));
        lsp.setTenloaisanpham(txt_tenloaisanpham.getText());

        try {
            dao.dao_LoaiSanPham.insertLSP(lsp);
            LoadData_LoaiSanPham();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Mã bị trùng");
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_themmaloaisanphamMouseClicked
    private void clear_loaisanpham(){
        txt_maloaisanpham.setText(" ");
        txt_tenloaisanpham.setText(" ");
    }
//------------------------------------------------------THÊM SỬA XÓA RESET SẢN PHẨM----------------------------------------------------------------------------------------------   
    private void btn_resetsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetsanphamMouseClicked
        clear_sanpham();
    }//GEN-LAST:event_btn_resetsanphamMouseClicked
    private void btn_xoasanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoasanphamMouseClicked
        try {
            dao.dao_SanPham.deleteSP(txt_masanpham.getText());
            LoadData_SanPham();
            clear_sanpham();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xoasanphamMouseClicked
    private void btn_suasanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suasanphamMouseClicked
        SanPham sp = new SanPham();
        sp.setMasanpham(Integer.valueOf(txt_masanpham.getText()));
        sp.setTensanpham(txt_tensanpham.getText());
        sp.setMaloaisanpham(Integer.valueOf(cb_loaisanpham.getSelectedIndex()+1));
        sp.setNoisanxuat(txt_noisanxuat.getText());
        sp.setGianhap(txt_gianhap.getText());
        sp.setGiaban(txt_giaban.getText());
        sp.setTonkho(Integer.valueOf(txt_tonkho.getText()));

        try {
            dao.dao_SanPham.updateSP(sp);
            LoadData_SanPham();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_suasanphamMouseClicked
    private void btn_themsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themsanphamMouseClicked
        SanPham sp = new SanPham();
        sp.setMasanpham(Integer.valueOf(txt_masanpham.getText()));
        sp.setTensanpham(txt_tensanpham.getText());
        sp.setMaloaisanpham(Integer.valueOf(cb_loaisanpham.getSelectedIndex()+1));
        sp.setNoisanxuat(txt_noisanxuat.getText());
        sp.setGianhap(txt_gianhap.getText());
        sp.setGiaban(txt_giaban.getText());
        sp.setTonkho(Integer.valueOf(txt_tonkho.getText()));
        
        try {
            dao.dao_SanPham.insertSP(sp);
            LoadData_SanPham();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Mã bị trùng");
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_themsanphamMouseClicked
    private void clear_sanpham(){
        txt_masanpham.setText("");
        txt_tensanpham.setText("");
        txt_noisanxuat.setText("");
        txt_gianhap.setText("");
        txt_giaban.setText("");
        txt_tonkho.setText("");
    }

    private void tb_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_sanphamMouseClicked
        try {
            loadData_control_tb_sanpham();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_sanphamMouseClicked
    private void tb_phieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_phieunhapMouseClicked
        try {
            LoadData_PhieuNhap_control();
        } catch (ParseException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_phieunhapMouseClicked

//------------------------------------------------------THÊM SỬA XÓA RESET PHIẾU NHẬP----------------------------------------------------------------------------------------------   
    private void btn_themphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themphieunhapMouseClicked
        PhieuNhap pn = new PhieuNhap();
        pn.setMaphieunhap(Integer.valueOf(txt_maphieunhap.getText()));
        pn.setManhanvien(Integer.valueOf(cb_nhanvien.getSelectedIndex()+1));
        pn.setManhaphanphoi(Integer.valueOf(cb_nhaphanphoi.getSelectedIndex()+1));
        pn.setTongtien(txt_tongtien_phieunhap.getText());
        
        Date dtn_ns = dtn_ngaynhap.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        pn.setNgaynhap(dateFormat.format(dtn_ns));
        
        try {
            dao.dao_PhieuNhap.insertPN(pn);
            LoadData_PhieuNhap();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Mã bị trùng");
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_themphieunhapMouseClicked
    private void btn_xoaphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoaphieunhapMouseClicked
        try {
            dao.dao_PhieuNhap.deletePN(txt_maphieunhap.getText());
            LoadData_PhieuNhap();
            clear_phieunhap();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xoaphieunhapMouseClicked
    private void btn_suaphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suaphieunhapMouseClicked
        PhieuNhap pn = new PhieuNhap();
        pn.setMaphieunhap(Integer.valueOf(txt_maphieunhap.getText()));
        pn.setManhanvien(Integer.valueOf(cb_nhanvien.getSelectedIndex()+1));
        pn.setManhaphanphoi(Integer.valueOf(cb_nhaphanphoi.getSelectedIndex()+1));
        pn.setTongtien(txt_tongtien_phieunhap.getText());
        
        Date dtn_ns = dtn_ngaynhap.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        pn.setNgaynhap(dateFormat.format(dtn_ns));
        
        try {
            dao.dao_PhieuNhap.updatePN(pn);
            LoadData_PhieuNhap();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_suaphieunhapMouseClicked
    private void btn_resetphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetphieunhapMouseClicked
        clear_phieunhap();
    }//GEN-LAST:event_btn_resetphieunhapMouseClicked
    private void clear_phieunhap(){
        txt_maphieunhap.setText("");
        dtn_ngaynhap.setDate(null);
        txt_tongtien_phieunhap.setText("");
    }

//------------------------------------------------------THÊM SỬA XÓA RESET CHI TIẾT PHIẾU NHẬP----------------------------------------------------------------------------------------------   
    
    private void tb_chitietphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_chitietphieunhapMouseClicked
        try {
            LoadData_ChiTietPhieuNhap_control();
        } catch (ParseException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_chitietphieunhapMouseClicked
    private void btn_themctpnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themctpnMouseClicked
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setMactpn(Integer.valueOf(txt_mactpn.getText()));
        ctpn.setMaphieunhap(Integer.valueOf(txt_phieunhap_in_table_chitietphieunhap.getText()));
        ctpn.setMasanpham(Integer.valueOf(cb_sanpham.getSelectedIndex()+1));
        ctpn.setSoluong(Integer.valueOf(txt_soluong.getText()));
        ctpn.setDongia(txt_tongtien_chitietphieunhap.getText());
        
        try {
            dao.dao_ChiTietPhieuNhap.insertCTPN(ctpn);
            LoadData_ChiTietPhieuNhap();
            LoadData_PhieuNhap();
            LoadData_SanPham();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Mã bị trùng");
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_themctpnMouseClicked
    private void btn_suactpnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suactpnMouseClicked
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setMactpn(Integer.valueOf(txt_mactpn.getText()));
        ctpn.setMaphieunhap(Integer.valueOf(txt_phieunhap_in_table_chitietphieunhap.getText()));
        ctpn.setMasanpham(Integer.valueOf(cb_sanpham.getSelectedIndex()+1));
        ctpn.setSoluong(Integer.valueOf(txt_soluong.getText()));
        ctpn.setDongia(txt_tongtien_chitietphieunhap.getText());
        
        try {
            dao.dao_ChiTietPhieuNhap.updateCTPN(ctpn);
            LoadData_ChiTietPhieuNhap();
            LoadData_PhieuNhap();
            LoadData_SanPham();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_suactpnMouseClicked
    private void btn_xoactpnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoactpnMouseClicked
        try {
            dao.dao_ChiTietPhieuNhap.deleteCTPN(txt_mactpn.getText());
            LoadData_ChiTietPhieuNhap();
            clear_chitietphieunhap();
            LoadData_PhieuNhap();
            LoadData_SanPham();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xoactpnMouseClicked
    private void btn_resetctpnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetctpnMouseClicked
        clear_chitietphieunhap();
    }//GEN-LAST:event_btn_resetctpnMouseClicked

    private void txt_timsanphamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timsanphamCaretUpdate
       find_name = txt_timsanpham.getText();
        try {
            findSP();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_timsanphamCaretUpdate

    private void btn_timsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_timsanphamMouseClicked
     
        try {
           find_name = txt_timsanpham.getText();
           findSP();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btn_timsanphamMouseClicked

    private void tb_loaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_loaisanphamMouseClicked
        try {
            loadData_control_tb_loaisanpham();
            loadData_tableSanPham_inLoaiSanPham_afterClick_tbLoaiSanPham();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(frm_SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_loaisanphamMouseClicked
    private void clear_chitietphieunhap(){
        txt_mactpn.setText(" ");
        txt_phieunhap_in_table_chitietphieunhap.setText("");
        txt_soluong.setText("");
        txt_tongtien_chitietphieunhap.setText("");        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_resetctpn;
    private javax.swing.JButton btn_resetmaloaisanpham;
    private javax.swing.JButton btn_resetphieunhap;
    private javax.swing.JButton btn_resetsanpham;
    private javax.swing.JButton btn_suactpn;
    private javax.swing.JButton btn_suamaloaisanpham;
    private javax.swing.JButton btn_suaphieunhap;
    private javax.swing.JButton btn_suasanpham;
    private javax.swing.JButton btn_themctpn;
    private javax.swing.JButton btn_themmaloaisanpham;
    private javax.swing.JButton btn_themphieunhap;
    private javax.swing.JButton btn_themsanpham;
    private javax.swing.JButton btn_timsanpham;
    private javax.swing.JButton btn_xoactpn;
    private javax.swing.JButton btn_xoamaloaisanpham;
    private javax.swing.JButton btn_xoaphieunhap;
    private javax.swing.JButton btn_xoasanpham;
    private javax.swing.JComboBox<String> cb_loaisanpham;
    private javax.swing.JComboBox<String> cb_nhanvien;
    private javax.swing.JComboBox<String> cb_nhaphanphoi;
    private javax.swing.JComboBox<String> cb_sanpham;
    private org.jdesktop.swingx.JXDatePicker dtn_ngaynhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXTable tb_chitietphieunhap;
    private org.jdesktop.swingx.JXTable tb_loaisanpham;
    private org.jdesktop.swingx.JXTable tb_phieunhap;
    private org.jdesktop.swingx.JXTable tb_sanpham;
    private org.jdesktop.swingx.JXTable tb_sanpham_in_panel_loaisanpham;
    private javax.swing.JTextField txt_giaban;
    private javax.swing.JTextField txt_gianhap;
    private javax.swing.JTextField txt_mactpn;
    private javax.swing.JTextField txt_maloaisanpham;
    private javax.swing.JTextField txt_maphieunhap;
    private javax.swing.JTextField txt_masanpham;
    private javax.swing.JTextField txt_noisanxuat;
    private javax.swing.JTextField txt_phieunhap_in_table_chitietphieunhap;
    private javax.swing.JTextField txt_soluong;
    private javax.swing.JTextField txt_tenloaisanpham;
    private javax.swing.JTextField txt_tensanpham;
    private javax.swing.JTextField txt_timsanpham;
    private javax.swing.JTextField txt_tongtien_chitietphieunhap;
    private javax.swing.JTextField txt_tongtien_phieunhap;
    private javax.swing.JTextField txt_tonkho;
    // End of variables declaration//GEN-END:variables
}
