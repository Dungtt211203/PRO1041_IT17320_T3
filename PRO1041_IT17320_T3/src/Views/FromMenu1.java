/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModel.CTSanPham;
import DomainModel.HoaDon;
import DomainModel.KhachHang;
import ViewModels.HoaDonViewModel;
import DomainModel.NhanVien;
import DomainModel.Size;
import Reponsitories.GioHangReponsitory;

import Reponsitories.QLHoaDonReponsitory;

import Services.ChatLieuService;
import Services.DsSanPhamService;
import Services.HoaDonChoService;
import Services.HoaDonService;
import Services.KhachHangService;
import Services.MauSacService;
import Services.NhaCungCapService;
import Services.NhanVienService;
import Services.QLHoaDonService;
import Services.QLSPService;
import Services.SanPhamService;
import Services.SizeService;
import Services.CTSanPhamService;
import Services.impl.CTSanPhamServiceImpl;
import Services.impl.ChatLieuServiceImpl;
import Services.impl.DsSanPhamServiceImpl;
import Services.impl.GioHangServiceImpl;
import Services.impl.HoaDonChoServiceImpl;
import Services.impl.HoaDonServiceImpl;
import Services.impl.KhachHangServiceImpl;
import Services.impl.MauSacServiceImpl;
import Services.impl.NhaCungCapServiceimpl;
import Services.impl.NhanVienServiceImpl;
import Services.impl.QLHoaDonServiceImpl;
import Services.impl.QLSPServiceImpl;
import Services.impl.SanPhamServiceImpl;
import Services.impl.SizeServiceImpl;
import ViewModels.CTSanPhamViewModel;
import ViewModels.ChatLieuViewModel;
import ViewModels.DsSanPhamViewModel;
import ViewModels.GioHangViewModel;
import ViewModels.HoaDonChoViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.KhachHangVM;
import ViewModels.MauSacViewModel;
import ViewModels.NhaCungCapVM;
import ViewModels.NhanVienViewModel;
import ViewModels.QLHoaDonViewModel;
import ViewModels.QLSPVM;
import ViewModels.SanPhamViewmodel;
import ViewModels.SizeVM;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Random;
import Reponsitories.HoaDonRepo;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FromMenu1 extends javax.swing.JFrame {
    private DefaultTableModel _DefaultTableModel;
    private DefaultComboBoxModel dfcbkh;
    private DefaultComboBoxModel dfnvbh;

    private DefaultComboBoxModel _dcbMauSac;
    private DefaultComboBoxModel _dcbChatLieu;
    private DefaultComboBoxModel _dcbNcc;
    private DefaultComboBoxModel _dcbSize;
    private DefaultComboBoxModel _dcbSp;

    private MauSacService _ServiceMauSac;
    private ChatLieuService _ServiceChatLieu;
    private NhaCungCapService _ServiceNcc;
    private SizeService _ServiceSize;
    private SanPhamService _ServiceSp;
    private CTSanPhamService _ServiceCTSP;
    private String _IdWhenClick;
    /**
     * Creates new form FromMenu1
     */
    private SanPhamServiceImpl spService = new SanPhamServiceImpl();
    private MauSacServiceImpl msService = new MauSacServiceImpl();
    private QLSPService qlspService = new QLSPServiceImpl();
    private HoaDonService hdService = new HoaDonServiceImpl();
    private DsSanPhamService dsService = new DsSanPhamServiceImpl();
    private DefaultTableModel defaultTableModelGioHang;
    private DefaultTableModel defaultTableModelHoaDonCho;
    private GioHangServiceImpl ghService = new GioHangServiceImpl();
    private ArrayList<GioHangViewModel> list = new ArrayList<>();
    String find = " ";
    String findMS = " ";
    String findSize = " ";
    String findNCC = " ";
    String findChatLieu = " ";
    GioHangReponsitory gioHangReponsitory = new GioHangReponsitory();
    private HoaDonChoService hdcService = new HoaDonChoServiceImpl();

    private KhachHangService khService = new KhachHangServiceImpl();
    private NhanVienService nvService = new NhanVienServiceImpl();
    private QLHoaDonService qlhd = new QLHoaDonServiceImpl();

    
    private HoaDonRepo hoaDonRepo = new HoaDonRepo();

    public FromMenu1() {
        initComponents();
//        this.getTable();
        //findSanPham();
        //tableDSSanPham();

        //LoadgioHang(gioHangReponsitory.GetAll());
        findSanPham();
        findMauSac();
        findChatLieu();
        findNCC();
        findSize();
        tableDSSanPham();
        lblNgayTao.setEnabled(false);
        Date date = new Date();// Return thời gian hiện tại với định dạng rất khó coi
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng lại date                                                                                           
        lblNgayTao.setText(sdf.format(date));
       
        //
        _ServiceChatLieu = new ChatLieuServiceImpl();
        _ServiceMauSac = new MauSacServiceImpl();
        _ServiceNcc = new NhaCungCapServiceimpl();
        _ServiceSize = new SizeServiceImpl();

        _dcbChatLieu = (DefaultComboBoxModel) cbChatLieu.getModel();
        _dcbMauSac = (DefaultComboBoxModel) cbMauSac.getModel();
        _dcbNcc = (DefaultComboBoxModel) cbNCC.getModel();
        _dcbSize = (DefaultComboBoxModel) cbSize.getModel();
        addCbKh();
        addnvbh();
        setCBO();
        LoadHoaDonCho();
        loadQLHoaDon();

        _ServiceSp = new SanPhamServiceImpl();
        _ServiceCTSP = new CTSanPhamServiceImpl();

        _dcbChatLieu = (DefaultComboBoxModel) cboidchatlieu.getModel();
        _dcbMauSac = (DefaultComboBoxModel) cboidmausac.getModel();
        _dcbNcc = (DefaultComboBoxModel) cboidncc.getModel();
        _dcbSize = (DefaultComboBoxModel) cboidsize.getModel();
        _dcbSp = (DefaultComboBoxModel) cboidsp.getModel();

        //setCBO();
        LoadHoaDon();
        LoadTable(null);
        LoadHoaDonCho();
        LoadTable(null);

        LoadCbo();

    }

    public String getTrangThaiHDC(int tt) {
        if (tt == 1) {
            return "Da thanh toan";
        } else if (tt == 2) {
            return "Chua thanh toan";
        } else {
            return null;
        }

    }

    void addCbKh() {
        dfcbkh = (DefaultComboBoxModel) cbKhachHang.getModel();
        for (KhachHangVM kh : khService.getall()) {
            dfcbkh.addElement(kh.getSdt());
        }
    }

    void addnvbh() {
        dfnvbh = (DefaultComboBoxModel) cbNhanVienBanHang.getModel();
        for (NhanVienViewModel nv : nvService.GetAll()) {
            dfnvbh.addElement(nv.getTen());
        }
    }

    //
    private CTSanPham GetDataFromGui() {
        try {
            SanPhamViewmodel idSanPham = (SanPhamViewmodel) cboidsp.getSelectedItem();
            MauSacViewModel idMauSac = (MauSacViewModel) cboidmausac.getSelectedItem();
            SizeVM idSize = (SizeVM) cboidsize.getSelectedItem();
            NhaCungCapVM idNcc = (NhaCungCapVM) cboidncc.getSelectedItem();
            ChatLieuViewModel idChatLieu = (ChatLieuViewModel) cboidchatlieu.getSelectedItem();

            String mota = tarmota.getText();
            Double soLuong = Double.parseDouble(txtsoluong.getText());
            Double giaBan = Double.parseDouble(txtgiaban.getText());
            Double giaNhap = Double.parseDouble(txtgianhap.getText());

            CTSanPham ctspv = new CTSanPham();
            ctspv.setIdSp(idSanPham.getId());
            ctspv.setIdMauSac(idMauSac.getId());
            ctspv.setIdSize(idSize.getId());
            ctspv.setIdNhaCungCap(idNcc.getId());
            ctspv.setIdChatLieu(idChatLieu.getId());
            ctspv.setMoTa(mota);
            ctspv.setSoLuongTon(soLuong);
            ctspv.setGiaBan(giaBan);
            ctspv.setGiaNhap(giaNhap);

            return ctspv;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    public void LoadCbo() {

        cboidchatlieu.removeAllItems();
        List<ChatLieuViewModel> dsChatLieu = _ServiceChatLieu.GetAll();
        for (ChatLieuViewModel chatLieuViewModel : dsChatLieu) {
            cboidchatlieu.addItem(chatLieuViewModel);
        }
        //
        cbolocchatlieu.removeAllItems();
        List<ChatLieuViewModel> dsChatLieu1 = _ServiceChatLieu.GetAll();
        for (ChatLieuViewModel chatLieuViewModel : dsChatLieu) {
            cbolocchatlieu.addItem(chatLieuViewModel);
        }

        //
        cboidmausac.removeAllItems();
        List<MauSacViewModel> dsMauSac = _ServiceMauSac.getAll();
        for (MauSacViewModel mauSac : dsMauSac) {
            cboidmausac.addItem(mauSac);
        }
        //
        cbolocmausac.removeAllItems();
        List<MauSacViewModel> dsMauSac1 = _ServiceMauSac.getAll();
        for (MauSacViewModel mauSac : dsMauSac) {
            cbolocmausac.addItem(mauSac);
        }
        //
        cboidncc.removeAllItems();
        List<NhaCungCapVM> dsNcc = _ServiceNcc.getAll();
        for (NhaCungCapVM ncc : dsNcc) {
            cboidncc.addItem(ncc);
        }
        //
        cboidsize.removeAllItems();
        List<SizeVM> dsSize = _ServiceSize.getListSize();
        for (SizeVM size : dsSize) {
            cboidsize.addItem(size);
        }
        //
        cbolocsize.removeAllItems();
        List<SizeVM> dsSize1 = _ServiceSize.getListSize();
        for (SizeVM size : dsSize) {
            cbolocsize.addItem(size);
        }
        //
        cboidsp.removeAllItems();
        List<SanPhamViewmodel> dsSp = _ServiceSp.getAll();
        for (SanPhamViewmodel sanPhamViewmodel : dsSp) {
            cboidsp.addItem(sanPhamViewmodel);
        }
    }

    public void loadQLHoaDon() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbhd.getModel();
        dtm.setRowCount(0);
        int stt = 1;
        for (QLHoaDonViewModel hd : this.qlhd.getListQLHoaDon()) {
            Object[] rowData = {
                stt++, hd.getMaHD(), hd.getNgayThanhToan(), hd.getTrangThai(), hd.getTenNV(), hd.getTenKH()

            };
            dtm.addRow(rowData);
        }
    }

    public void findSanPham() {

        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel d : this.dsService.findSanPham(find)) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()

            };
            dtm.addRow(rowData);
        }
    }

    public void findMauSac() {

        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel d : this.dsService.findMauSac(findMS)) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()

            };
            dtm.addRow(rowData);
        }
    }

    public void findSize() {

        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel d : this.dsService.findSize(findSize)) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()

            };
            dtm.addRow(rowData);
        }
    }

    public void findNCC() {

        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel d : this.dsService.findNCC(findNCC)) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()

            };
            dtm.addRow(rowData);
        }
    }

    public void findChatLieu() {

        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel d : this.dsService.findChatLieu(findChatLieu)) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()

            };
            dtm.addRow(rowData);
        }
    }

    public void tableDSSanPham() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
        dtm.setRowCount(0);
        Random rd = new Random();
        for (DsSanPhamViewModel ds : this.dsService.getAll()) {
            Object[] rowData = {
                "SP" + rd.nextInt(1000), ds.getSanPham(), ds.getSize(), ds.getMauSac(), ds.getNcc(), ds.getChatLieu(), ds.getSoLuong(), ds.getGiaBan()

            };
            dtm.addRow(rowData);
            //
    public void LoadTable(String input) { // nhận vào list cho dễ. tôi viết cái khác nhé
        _DefaultTableModel = (DefaultTableModel) tblctsanpham.getModel();
        _DefaultTableModel.setRowCount(0);
        int stt = 1;

        for (CTSanPhamViewModel x : _ServiceCTSP.getAll(input)) {
            _DefaultTableModel.addRow(new Object[]{
                x.getId(),
                x.getIdSp(),
                x.getIdSize(),
                x.getIdMauSac(),
                x.getIdNhaCungCap(),
                x.getIdChatLieu(),
                x.getSoLuongTon(),
                x.getGiaNhap(),
                x.getGiaBan()
            });
        }
    }

    public void LoadTableNew(List<CTSanPhamViewModel> list) { // nhận vào list cho dễ. tôi viết cái khác nhé
        _DefaultTableModel = (DefaultTableModel) tblctsanpham.getModel();
        _DefaultTableModel.setRowCount(0);
        int stt = 1;

        for (CTSanPhamViewModel x : list) {
            _DefaultTableModel.addRow(new Object[]{
                x.getId(),
                x.getIdSp(),
                x.getIdSize(),
                x.getIdMauSac(),
                x.getIdNhaCungCap(),
                x.getIdChatLieu(),
                x.getSoLuongTon(),
                x.getGiaNhap(),
                x.getGiaBan()
            });
        }
    }

    //
    public void LoadTableLoc(SizeVM obj) {
        _DefaultTableModel = (DefaultTableModel) tblctsanpham.getModel();
        _DefaultTableModel.setRowCount(0);
        int stt = 1;

        for (CTSanPhamViewModel x : _ServiceCTSP.GetAll()) {
            _DefaultTableModel.addRow(new Object[]{
                x.getId(),
                x.getIdSp(),
                x.getIdSize(),
                x.getIdMauSac(),
                x.getIdNhaCungCap(),
                x.getIdChatLieu(),
                x.getSoLuongTon(),
                x.getGiaNhap(),
                x.getGiaBan()
            });

        }
    }
    //
//    public void setCBO() {
//        _dcbChatLieu.removeAllElements();
//        for (ChatLieuViewModel x : _ServiceChatLieu.GetAll()) {
//            _dcbChatLieu.addElement(x.getTen());
//        }
//
//        _dcbMauSac.removeAllElements();
//        for (MauSacViewModel x : _ServiceMauSac.getAll()) {
//            _dcbMauSac.addElement(x.getTen());
//        }
//
//        _dcbNcc.removeAllElements();
//        for (NhaCungCapVM x : _ServiceNcc.getAll()) {
//            _dcbNcc.addElement(x.getTenNCC());
//        }
//
//        _dcbSize.removeAllElements();
//        for (SizeVM x : _ServiceSize.getListSize()) {
//            _dcbSize.addElement(x.getTen());
//        }
//    }

//    public void findSanPham() {
//
//        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
//        dtm.setRowCount(0);
//        int masp = 1;
//        for (DsSanPhamViewModel d : this.dsService.findSanPham(find)) {
//            Object[] rowData = {
//                masp++, d.getSanPham(), d.getSize(), d.getMauSac(), d.getNcc(), d.getChatLieu(), d.getSoLuong(), d.getGiaBan()
//
//            };
//            dtm.addRow(rowData);
//        }
//    }
//    public void tableDSSanPham() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tbSanPham.getModel();
//        dtm.setRowCount(0);
//        int masp = 1;
//        for (DsSanPhamViewModel ds : this.dsService.getAll()) {
//            Object[] rowData = {
//                masp++, ds.getSanPham(), ds.getSize(), ds.getMauSac(), ds.getNcc(), ds.getChatLieu(), ds.getSoLuong(), ds.getGiaBan()
//
//            };
//            dtm.addRow(rowData);
//        }
//    }
//    public void getTable() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tbQuanLySanPham.getModel();
//        dtm.setRowCount(0);
//        int maSP = 1;
//        for (QLSPVM qlsp : this.qlspService.getALL()) {
//            Object[] rowData = {
//                maSP++,qlsp.getTenSP(), qlsp.getMauSac(), qlsp.getChatlieu(), qlsp.getSize(), qlsp.getNcc(), qlsp.getSoLuong(), qlsp.getGiaNhap(), qlsp.getGiaBan()
//            };
//            dtm.addRow(rowData);
//        }
//    }
    public void LoadgioHang(ArrayList<GioHangViewModel> listGioHang) {
        defaultTableModelGioHang = (DefaultTableModel) tbgiohang.getModel();
        defaultTableModelGioHang.setRowCount(0);
        int stt = 1;
        for (GioHangViewModel x : listGioHang) {
            defaultTableModelGioHang.addRow(new Object[]{
                stt++, x.getMaSP(), x.getTenSP(), x.getSoLuong(), x.getDonGia(), x.getSoLuong() * x.getDonGia()
            });
        }
    }

//    public QLSPVM GetdatafromGui() {
//        return new QLSPVM(txtMa.getText(),txtTenSP.getText(), cbMauSac.getSelectedItem().toString(), cbChatLieu.getSelectedItem().toString(), cbSize.getSelectedItem().toString(), cbNCC.getSelectedItem().toString(), Integer.parseInt(txtSoLuong.getText()), Double.parseDouble(txtGiaNhap.getText()), Double.parseDouble(txtGiaBan.getText()));
//    }
    public void LoadHoaDon() {
        defaultTableModelHoaDonCho = (DefaultTableModel) tbhdc.getModel();
        defaultTableModelHoaDonCho.setRowCount(0);
        int stt = 1;
        for (HoaDonViewModel x : hdService.getAll()) {
            defaultTableModelHoaDonCho.addRow(new Object[]{
                stt++, x.getMa(), x.getNgayThanhToan(), getTrangThaiHDC(x.getTinhTrang()), x.getTenNguoiNhan()
            });
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

        jPanel3 = new javax.swing.JPanel();
        btnnv = new javax.swing.JButton();
        btnsp = new javax.swing.JButton();
        btnbanhang = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        btnhd = new javax.swing.JButton();
        NV = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblctsanpham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblid = new javax.swing.JLabel();
        txttimkiem2 = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tarmota = new javax.swing.JTextArea();
        txtsoluong = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        cboidsp = new javax.swing.JComboBox<SanPhamViewmodel>();
        cboidsize = new javax.swing.JComboBox<SizeVM>();
        cboidmausac = new javax.swing.JComboBox<MauSacViewModel>();
        cboidncc = new javax.swing.JComboBox<NhaCungCapVM>();
        cboidchatlieu = new javax.swing.JComboBox<ChatLieuViewModel>();
        jLabel22 = new javax.swing.JLabel();
        cbolocsize = new javax.swing.JComboBox<SizeVM>();
        jLabel23 = new javax.swing.JLabel();
        cbolocmausac = new javax.swing.JComboBox<MauSacViewModel>();
        jLabel24 = new javax.swing.JLabel();
        cbolocchatlieu = new javax.swing.JComboBox<ChatLieuViewModel>();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbgiohang = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtFind = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbFindSize = new javax.swing.JComboBox<>();
        cbFindMS = new javax.swing.JComboBox<>();
        cbFindNCC = new javax.swing.JComboBox<>();
        cbFindChatLieu = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbNhanVienBanHang = new javax.swing.JComboBox<>();
        btnThayDoi = new javax.swing.JButton();
        cbKhachHang = new javax.swing.JComboBox<>();
        txtKhachHang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtTienKhachTra = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        lblMaHD = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTienThanhToan = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btntaohd = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbhdc = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbhd = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        txttimkiem1 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbhdct = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        btnnv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnnv.setText("Nhân Viên");
        btnnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvActionPerformed(evt);
            }
        });

        btnsp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnsp.setText("Sản Phẩm");
        btnsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnspActionPerformed(evt);
            }
        });

        btnbanhang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnbanhang.setText("Bán Hàng");
        btnbanhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbanhangActionPerformed(evt);
            }
        });

        btnKhachHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnDangXuat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        btnhd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnhd.setText("Hóa Đơn");
        btnhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnhd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(btnnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbanhang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnnv, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnsp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnhd, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        NV.setBackground(new java.awt.Color(153, 51, 255));
        NV.setForeground(new java.awt.Color(255, 0, 51));

        tblctsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "TênSP", "Size", "Màu Sắc", "NCC", "Chất Liệu", "Số Lượng", "Gía Nhập", "Gía Bán"
            }
        ));
        tblctsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblctsanphamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblctsanpham);

        jLabel2.setText("ID:");

        jLabel3.setText("TênSP:");

        jLabel4.setText("Size:");

        jLabel5.setText("MauSac:");

        jLabel6.setText("Ncc:");

        jLabel7.setText("ChatLieu:");

        jLabel8.setText("Mô tả:");

        jLabel9.setText("Số lượng:");

        lblid.setText("-");

        txttimkiem2.setText("Tìm kiếm");
        txttimkiem2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttimkiem2CaretUpdate(evt);
            }
        });
        txttimkiem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimkiem2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txttimkiem2MouseExited(evt);
            }
        });

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        jLabel17.setText("Gía Nhập:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        jLabel10.setText("Gía bán:");

        tarmota.setColumns(20);
        tarmota.setRows(5);
        jScrollPane9.setViewportView(tarmota);

        cboidsp.setModel(new javax.swing.DefaultComboBoxModel<SanPhamViewmodel>());

        cboidsize.setModel(new javax.swing.DefaultComboBoxModel<SizeVM>());

        cboidmausac.setModel(new javax.swing.DefaultComboBoxModel<MauSacViewModel>());
        cboidmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboidmausacActionPerformed(evt);
            }
        });

        cboidncc.setModel(new javax.swing.DefaultComboBoxModel<NhaCungCapVM>());

        cboidchatlieu.setModel(new javax.swing.DefaultComboBoxModel<ChatLieuViewModel>());

        jLabel22.setText("Size");

        cbolocsize.setModel(new javax.swing.DefaultComboBoxModel<SizeVM>());
        cbolocsize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbolocsizeItemStateChanged(evt);
            }
        });
        cbolocsize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbolocsizeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbolocsizeMouseExited(evt);
            }
        });
        cbolocsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbolocsizeActionPerformed(evt);
            }
        });

        jLabel23.setText("Màu sắc");

        cbolocmausac.setModel(new javax.swing.DefaultComboBoxModel<MauSacViewModel>());
        cbolocmausac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbolocmausacItemStateChanged(evt);
            }
        });
        cbolocmausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbolocmausacMouseExited(evt);
            }
        });
        cbolocmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbolocmausacActionPerformed(evt);
            }
        });

        jLabel24.setText("Chất liệu");

        cbolocchatlieu.setModel(new javax.swing.DefaultComboBoxModel<ChatLieuViewModel>());
        cbolocchatlieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbolocchatlieuItemStateChanged(evt);
            }
        });
        cbolocchatlieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbolocchatlieuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 534, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(cboidsp, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboidsize, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboidmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboidncc, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboidchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(31, 31, 31)))
                                .addGap(0, 323, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsoluong)
                            .addComponent(txtgiaban, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnthem)
                                .addGap(35, 35, 35)
                                .addComponent(btnsua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnxoa))
                            .addComponent(txtgianhap, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 286, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbolocsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbolocmausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbolocchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(lblid))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboidsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboidsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboidmausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboidncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboidchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(cbolocsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(cbolocmausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(cbolocchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthem)
                            .addComponent(btnsua)
                            .addComponent(btnxoa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
        );

        NV.addTab("SP", jPanel2);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1157, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );

        NV.addTab("NV", jPanel13);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1157, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );

        NV.addTab("KH", jPanel14);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tbgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng ", "Đơn giá", "Thành tiền"
            }
        ));
        tbgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbgiohangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbgiohang);

        jButton5.setText("Xóa Sản Phẩm");

        jButton6.setText("Xóa Tất Cả");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton5)
                        .addGap(43, 43, 43)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách SP"));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Sản Phẩm", "Size", "Màu Sắc", "Nhà Cung Cấp", "Chất Liệu", "Số Lượng", "Giá Bán"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSanPham);

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });

        jButton3.setText("Tìm Kiếm");

        jButton4.setText("Thêm Sản Phẩm");

        cbFindSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL" }));
        cbFindSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFindSizeActionPerformed(evt);
            }
        });

        cbFindMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xanh Lá", "Đỏ", "Tím", "Vàng", "Đen", "Trắng" }));
        cbFindMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFindMSActionPerformed(evt);
            }
        });

        cbFindNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LV", "Dior", "Gucci", "Chanel" }));
        cbFindNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFindNCCActionPerformed(evt);
            }
        });

        cbFindChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Len", "Kaki", "Cotton", "Jean", "Nỉ" }));
        cbFindChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFindChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbFindSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbFindMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbFindNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cbFindChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbFindSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbFindMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbFindNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbFindChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        jLabel16.setText("Mã HĐ");

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Tên Khách Hàng:");

        jLabel13.setText("SĐT Khách Hàng:");

        jLabel18.setText("Nhân Viên Bán Hàng:");

        btnThayDoi.setText("Thay Đổi");
        btnThayDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThayDoiMouseClicked(evt);
            }
        });

        cbKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(cbKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThayDoi))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addComponent(cbNhanVienBanHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(btnThayDoi)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbNhanVienBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel12.setText("Tổng Tiền");

        jLabel14.setText("Tiền Thanh Toán");

        jLabel19.setText("Tiền Thừa");

        jLabel20.setText("Ngày Tạo");

        jLabel21.setText("Tiền Khách Trả");

        jButton1.setText("Làm Mới");

        jButton2.setText("Hủy Hóa Đơn");

        btnThanhToan.setText("Thanh Toán");

        txtTienKhachTra.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachTraCaretUpdate(evt);
            }
        });

        txtGiamGia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGiamGiaCaretUpdate(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 51, 0));

        lblTienThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThanhToan.setForeground(new java.awt.Color(255, 51, 0));

        lblTienThua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 51, 0));

        lblNgayTao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(0, 0, 255));

        jLabel15.setText("Giảm Giá");

        btntaohd.setText("Tạo Hóa Đơn");
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel20)
                                .addComponent(jLabel19)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addGap(57, 57, 57))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblMaHD)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(lblTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntaohd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tbhdc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Trạng Thái", "Tên KH"
            }
        ));
        tbhdc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhdcMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbhdc);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        NV.addTab("Bán Hàng", jPanel1);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        tbhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MaHD", "NgayThanhToan", "TrangThai", "TenNV", "TenKH"
            }
        ));
        jScrollPane6.setViewportView(tbhd);

        txttimkiem.setText("Tìm kiếm ...");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txttimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn Chi Tiết"));

        txttimkiem1.setText("Tìm kiếm ...");

        tbhdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng ", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane8.setViewportView(tbhdct);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiem1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(385, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        NV.addTab("HĐ", jPanel16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 1162, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(NV))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvActionPerformed
        // TODO add your handling code here:
        NhanVienForm nv = new NhanVienForm();
        nv.setVisible(true);
    }//GEN-LAST:event_btnnvActionPerformed

    private void btnspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnspActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(0);
    }//GEN-LAST:event_btnspActionPerformed

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(3);

    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        KhachHangForm kh = new KhachHangForm();
        kh.setVisible(true);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        FormDangNhap dn = new FormDangNhap();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnexitActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        GioHangViewModel gioHangViewModel = new GioHangViewModel();
        int row = tbSanPham.getSelectedRow();
        gioHangViewModel.setMaSP((String) tbSanPham.getValueAt(row, 0));
        gioHangViewModel.setTenSP((String) tbSanPham.getValueAt(row, 1));
        JOptionPane.showInputDialog("Mời nhập số lượng: ");
        gioHangViewModel.setSoLuong(1);
        gioHangViewModel.setDonGia((Double) tbSanPham.getValueAt(row, 7));
        list.add(gioHangViewModel);
        LoadgioHang(list);
//        GioHangViewModel gioHangViewModel = new GioHangViewModel();
//        int row = tbSanPham.getSelectedRow();
//        gioHangViewModel.setMaSP((String) tbSanPham.getValueAt(row, 1));
//        gioHangViewModel.setTenSP((String) tbSanPham.getValueAt(row, 2));
//        JOptionPane.showInputDialog("Mời nhập số lượng: ");
//        gioHangViewModel.setSoLuong(1);
//        gioHangViewModel.setDonGia((Double) tbSanPham.getValueAt(row, 7));
//        list.add(gioHangViewModel);
//        LoadgioHang(list);

    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        // TODO add your handling code here:
//        find = txtFind.getText();
//        findSanPham();
    }//GEN-LAST:event_txtFindCaretUpdate

    private void tbhdcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhdcMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbhdcMouseClicked

    private void btnhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdActionPerformed
        // TODO add your handling code here:
        NV.setSelectedIndex(4);
    }//GEN-LAST:event_btnhdActionPerformed

    private void tbgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbgiohangMouseClicked
        // TODO add your handling code here:
        int row = tbgiohang.getSelectedRow();
        lblTongTien.setText(tbgiohang.getValueAt(row, 4).toString());
        int row = tbgiohang.getSelectedRow();
        lblTongTien.setText(tbgiohang.getValueAt(row, 5).toString());

    }//GEN-LAST:event_tbgiohangMouseClicked

    private void txtTienKhachTraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachTraCaretUpdate
        // TODO add your handling code here:
        if (txtTienKhachTra.getText().trim().length() == 0) {
            lblTienThua.setText("0");
        }
        double tienDua = 0;
        try {
            tienDua = Double.parseDouble(txtTienKhachTra.getText());
        } catch (Exception e) {
        }
        double tongTien = Double.parseDouble(lblTienThanhToan.getText());
        double tienThua = tienDua - tongTien;

        lblTienThua.setText(String.valueOf(tienThua));
        if (tienThua < 0) {
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
        }


    }//GEN-LAST:event_txtTienKhachTraCaretUpdate

    private void txtGiamGiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGiamGiaCaretUpdate
        // TODO add your handling code here:
        if (txtGiamGia.getText().trim().length() == 0) {
            lblTienThanhToan.setText("0");
        }

        double giamGia = 1;

        try {
            giamGia = Double.parseDouble(txtGiamGia.getText());
        } catch (Exception e) {
            e.getMessage();
        }
        double tongTien = Double.parseDouble(lblTongTien.getText());
        double tienThanhToan = tongTien * giamGia;

        lblTienThanhToan.setText(String.valueOf(tienThanhToan));
    }//GEN-LAST:event_txtGiamGiaCaretUpdate

    private void btnThayDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThayDoiMouseClicked
        // TODO add your handling code here:
        Random rd = new Random();
        String sdt = cbKhachHang.getSelectedItem().toString();
        String ten = txtKhachHang.getText();
        String ma = "KH" + rd.nextInt(1000);
        KhachHang kh = new KhachHang();
        kh.setMaKH(ma);
        kh.setTenKH(ten);
        kh.setSdt(sdt);
        khService.insert(kh);
        addCbKh();
    }//GEN-LAST:event_btnThayDoiMouseClicked

    private void cbKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhachHangActionPerformed
        // TODO add your handling code here:

        String sdt = cbKhachHang.getSelectedItem().toString();
        KhachHang kh = khService.getListSDT(sdt);
        if (kh != null) {
            txtKhachHang.setText(kh.getTenKH());
        } else {
            txtKhachHang.setText("");
        }
    }//GEN-LAST:event_cbKhachHangActionPerformed

    private void cbFindMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFindMSActionPerformed
        // TODO add your handling code here:
        findMS = cbFindMS.getSelectedItem().toString();
        findMauSac();
    }//GEN-LAST:event_cbFindMSActionPerformed

    private void cbFindSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFindSizeActionPerformed
        // TODO add your handling code here:
        findSize = cbFindSize.getSelectedItem().toString();
        findSize();
    }//GEN-LAST:event_cbFindSizeActionPerformed

    private void cbFindNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFindNCCActionPerformed
        // TODO add your handling code here:
        findNCC = cbFindNCC.getSelectedItem().toString();
        findNCC();
    }//GEN-LAST:event_cbFindNCCActionPerformed

    private void cbFindChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFindChatLieuActionPerformed
        // TODO add your handling code here:
        findChatLieu = cbFindChatLieu.getSelectedItem().toString();
        findChatLieu();
    }//GEN-LAST:event_cbFindChatLieuActionPerformed

    double tienThanhToan = tongTien * giamGia;

    lblTienThanhToan.setText (String.valueOf

(giamGia));
    }                                      

    private void tblctsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblctsanphamMouseClicked
        // TODO add your handling code here:
        int index = tblctsanpham.getSelectedRow();
        _IdWhenClick = _ServiceCTSP.GetAll().get(index).getId();

        Double soLuong = (Double) tblctsanpham.getValueAt(index, 6);
        Double giaNhap = (Double) tblctsanpham.getValueAt(index, 7);
        Double giaBan = (Double) tblctsanpham.getValueAt(index, 8);

        SanPhamViewmodel spv = (SanPhamViewmodel) tblctsanpham.getValueAt(index, 1);
        SizeVM svm = (SizeVM) tblctsanpham.getValueAt(index, 2);
        MauSacViewModel msv = (MauSacViewModel) tblctsanpham.getValueAt(index, 3);
        NhaCungCapVM nccv = (NhaCungCapVM) tblctsanpham.getValueAt(index, 4);
        ChatLieuViewModel clv = (ChatLieuViewModel) tblctsanpham.getValueAt(index, 5);
        tarmota.setText(_ServiceCTSP.GetAll().get(index).getMoTa());
        txtsoluong.setText(soLuong.toString());
        txtgianhap.setText(giaNhap.toString());
        txtgiaban.setText(giaBan.toString());

        int soLuongSp = cboidsp.getItemCount();
        for (int i = 0; i < soLuongSp; i++) {
            SanPhamViewmodel spv1 = cboidsp.getItemAt(i);
            if (spv1.getId().equals(spv.getId())) {
                cboidsp.setSelectedIndex(i);
            }
        }
        //
        int soLuongSize = cboidsize.getItemCount();        for (int i = 0; i < soLuongSize; i++) {
            SizeVM svm1 = cboidsize.getItemAt(i);
            if (svm1.getId().equals(svm.getId())) {
                cboidsize.setSelectedIndex(i);
            }
        }
        //
        int soLuongMs = cboidmausac.getItemCount();
        for (int i = 0; i < soLuongMs; i++) {
            MauSacViewModel msv1 = cboidmausac.getItemAt(i);
            if (msv1.getId().equals(msv.getId())) {
                cboidmausac.setSelectedIndex(i);
            }
        }
        //
        int soLuongNcc = cboidncc.getItemCount();
        for (int i = 0; i < soLuongNcc; i++) {
            NhaCungCapVM ncc1 = cboidncc.getItemAt(i);
            if (ncc1.getId().equals(nccv.getId())) {
                cboidncc.setSelectedIndex(i);
            }
        }
        //
        int soLuongChatLieu = cboidchatlieu.getItemCount();
        for (int i = 0; i < soLuongChatLieu; i++) {
            ChatLieuViewModel cl1 = cboidchatlieu.getItemAt(i);
            if (cl1.getId().equals(clv.getId())) {
                cboidchatlieu.setSelectedIndex(i);
            }
        }
        System.out.println(_IdWhenClick);
    }//GEN-LAST:event_tblctsanphamMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        CTSanPham ctsp = GetDataFromGui();
        _ServiceCTSP.them(ctsp);
        LoadTable(null);
        System.out.println(ctsp);
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        CTSanPham ctsp = GetDataFromGui();
        ctsp.setId(_IdWhenClick);
        JOptionPane.showMessageDialog(this, _ServiceCTSP.sua(ctsp));
        LoadTable(null);
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        CTSanPhamViewModel temp = new CTSanPhamViewModel();
        temp.setId(_IdWhenClick);
        JOptionPane.showMessageDialog(this, _ServiceCTSP.xoa(temp));
        LoadTable(null);
    }//GEN-LAST:event_btnxoaActionPerformed

    private void cboidmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboidmausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboidmausacActionPerformed

    private void cbolocmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbolocmausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbolocmausacActionPerformed


    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayThanhToan("2022/12/2");
        hoaDon.setTinhTrang(2);
        hoaDon.setTenNguoiNhan("Thành");
        for (int i = 3; i < hoaDonRepo.GetAll().size(); i++) {
            hoaDon.setMa("HD" + i);
        }
        
        hdService.add(hoaDon);
        LoadHoaDon();
        
    }//GEN-LAST:event_btntaohdActionPerformed

    private void txttimkiem2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttimkiem2CaretUpdate
        // TODO add your handling code here:
        if (txttimkiem2.getText().isEmpty()) {
            LoadTable(null);
            return;
        }
        LoadTable(txttimkiem2.getText());
    }//GEN-LAST:event_txttimkiem2CaretUpdate

    private void txttimkiem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiem2MouseClicked
        // TODO add your handling code here:
        txttimkiem2.setText("");
    }//GEN-LAST:event_txttimkiem2MouseClicked

    private void txttimkiem2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiem2MouseExited
        // TODO add your handling code here:
        txttimkiem2.setText("Tìm kiếm");
        LoadTable(null);
    }//GEN-LAST:event_txttimkiem2MouseExited

    private void cbolocsizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbolocsizeItemStateChanged
//        int index = cbolocsize.getSelectedIndex();
//        String id = _ServiceSize.getListSize().get(index).getId();
//        List<CTSanPhamViewModel> listTemp = new ArrayList<>();
//        for (CTSanPhamViewModel x : _ServiceCTSP.GetAll()) {
//            if (x.getIdSize().getId().equals(id)) {
//                listTemp.add(x);
//            }
//        }
//        LoadTableNew(listTemp);
    }//GEN-LAST:event_cbolocsizeItemStateChanged

    private void cbolocmausacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbolocmausacItemStateChanged
        int index = cbolocmausac.getSelectedIndex();
        String id = _ServiceMauSac.getAll().get(index).getId();
        List<CTSanPhamViewModel> listTemp = new ArrayList<>();
        for (CTSanPhamViewModel x : _ServiceCTSP.GetAll()) {
            if (x.getIdMauSac().getId().equals(id)) {
                listTemp.add(x);
            }
        }
        LoadTableNew(listTemp);
    }//GEN-LAST:event_cbolocmausacItemStateChanged

    private void cbolocchatlieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbolocchatlieuItemStateChanged
        int index = cbolocchatlieu.getSelectedIndex();
        String id = _ServiceChatLieu.GetAll().get(index).getId();
        List<CTSanPhamViewModel> listTemp = new ArrayList<>();
        for (CTSanPhamViewModel x : _ServiceCTSP.GetAll()) {
            if (x.getIdChatLieu().getId().equals(id)) {
                listTemp.add(x);
            }
        }
        LoadTableNew(listTemp);
    }//GEN-LAST:event_cbolocchatlieuItemStateChanged

    private void cbolocsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbolocsizeActionPerformed

    }//GEN-LAST:event_cbolocsizeActionPerformed

    private void cbolocsizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbolocsizeMouseClicked
        // TODO add your handling code here:
        //cbolocsize.setSelectedIndex(-1);
        int index = cbolocsize.getSelectedIndex();
        String id = _ServiceSize.getListSize().get(index).getId();
        List<CTSanPhamViewModel> listTemp = new ArrayList<>();
        for (CTSanPhamViewModel x : _ServiceCTSP.GetAll()) {
            if (x.getIdSize().getId().equals(id)) {
                listTemp.add(x);
            }
        }
        if (index < 0) {
            LoadTableNew(null);
            return;
        }
        LoadTableNew(listTemp);
    }//GEN-LAST:event_cbolocsizeMouseClicked

    private void cbolocsizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbolocsizeMouseExited
        // TODO add your handling code here:
        LoadTable(null);
    }//GEN-LAST:event_cbolocsizeMouseExited

    private void cbolocmausacMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbolocmausacMouseExited
        // TODO add your handling code here:
        LoadTable(null);
    }//GEN-LAST:event_cbolocmausacMouseExited

    private void cbolocchatlieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbolocchatlieuMouseExited
        // TODO add your handling code here:
        LoadTable(null);
    }//GEN-LAST:event_cbolocchatlieuMouseExited



    /**
     * @param args the command line arguments
     */
    /**
     *
     * @return
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
            java.util.logging.Logger.getLogger(FromMenu1.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromMenu1.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromMenu1.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromMenu1.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromMenu1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane NV;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnhd;
    private javax.swing.JButton btnnv;
    private javax.swing.JButton btnsp;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbFindChatLieu;
    private javax.swing.JComboBox<String> cbFindMS;
    private javax.swing.JComboBox<String> cbFindNCC;
    private javax.swing.JComboBox<String> cbFindSize;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JComboBox<String> cbNhanVienBanHang;
    private javax.swing.JComboBox<ChatLieuViewModel> cboidchatlieu;
    private javax.swing.JComboBox<MauSacViewModel> cboidmausac;
    private javax.swing.JComboBox<NhaCungCapVM> cboidncc;
    private javax.swing.JComboBox<SizeVM> cboidsize;
    private javax.swing.JComboBox<SanPhamViewmodel> cboidsp;
    private javax.swing.JComboBox<ChatLieuViewModel> cbolocchatlieu;
    private javax.swing.JComboBox<MauSacViewModel> cbolocmausac;
    private javax.swing.JComboBox<SizeVM> cbolocsize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTienThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblid;
    private javax.swing.JTextArea tarmota;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbgiohang;
    private javax.swing.JTable tbhd;
    private javax.swing.JTable tbhdc;
    private javax.swing.JTable tbhdct;
    private javax.swing.JTable tblctsanpham;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiem1;
    private javax.swing.JTextField txttimkiem2;
    // End of variables declaration//GEN-END:variables
}
