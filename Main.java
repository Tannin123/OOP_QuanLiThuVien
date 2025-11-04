import model.NguoiDoc;
import model.PhieuMuon; 
import model.Sach;
import model.SachGiaoKhoa;
import model.TaiLieu;
import model.ThuThu;
import service.QuanLyNguoiDoc;
import service.QuanLyPhieuMuon; 
import service.QuanLySach;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Service để quản lý các nghiệp vụ liên quan đến sách
    private static final QuanLySach qltv = new QuanLySach();
    
    // Service để quản lý phiếu mượn
    private static final QuanLyPhieuMuon quanLyPM = new QuanLyPhieuMuon();
    
    // Service để quản lý người đọc
    private static final QuanLyNguoiDoc quanLyND = new QuanLyNguoiDoc();

    private static final List<ThuThu> danhSachNguoiQuanLy = new ArrayList<>();

    public static void main(String[] args) {
        // --- Khởi tạo dữ liệu người dùng mẫu ---
        // Tạo một người quản lý (Thủ thư) mặc định để đăng nhập
        danhSachNguoiQuanLy.add(new ThuThu("TT01", "Tannin Admin", "0909090909", "TP.HCM", "admin", "123"));

        Scanner sc = new Scanner(System.in);
        
        System.out.println("=========================================");
        System.out.println("||  CHAO MUNG DEN VOI HE THONG THU VIEN ||");
        System.out.println("=========================================");

        // --- Bước 1: Yêu cầu đăng nhập ---
        if (dangNhap(sc)) {
            System.out.println("\n=> Dang nhap thanh cong!");
            // --- Bước 2: Hiển thị MENU CHÍNH sau khi đăng nhập ---
            menuChinh(sc);
        } else {
            System.out.println("\n=> Dang nhap that bai! Ten dang nhap hoac mat khau khong dung.");
        }

        System.out.println("\nCam on ban da su dung chuong trinh. Tam biet!");
        sc.close();
    }

    /**
     * Xử lý chức năng đăng nhập
     */
    public static boolean dangNhap(Scanner sc) {
        System.out.print("Ten dang nhap: ");
        String tenDangNhap = sc.nextLine();
        System.out.print("Mat khau: ");
        String matKhau = sc.nextLine();

        for (ThuThu nguoiQuanLy : danhSachNguoiQuanLy) {
            if (nguoiQuanLy.getTenDangNhap().equals(tenDangNhap) && nguoiQuanLy.kiemTraMatKhau(matKhau)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Menu chính, cấp cao nhất của chương trình
     */
    private static void menuChinh(Scanner sc) {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("||          MENU CHINH         ||");
            System.out.println("=================================");
            System.out.println("|| 1. Quan ly Sach             ||");
            System.out.println("|| 2. Quan ly Phieu Muon       ||");
            System.out.println("|| 3. Quan ly Nguoi Doc        ||");
            System.out.println("|| 0. Luu va Thoat             ||");
            System.out.println("=================================");
            System.out.print("=> Lua chon cua ban: ");
            
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    menuQuanLySach(sc);
                    break;
                case "2":
                    menuQuanLyPhieuMuon(sc);
                    break;
                case "3":
                    menuQuanLyNguoiDoc(sc);
                    break;
                case "0":
                    System.out.println("Dang luu du lieu...");
                    qltv.luuThayDoiVaoFile();
                    quanLyPM.luuFilePhieuMuon();
                    quanLyND.luuThayDoiVaoFile();
                    return; // Thoát khỏi menu chính và kết thúc chương trình
                default:
                    System.out.println("=> Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }

    /**
     * Menu cho các chức năng quản lý sách
     */
    private static void menuQuanLySach(Scanner sc) {
        while (true) {
            System.out.println("\n--- MENU QUAN LY SACH ---");
            System.out.println("1. Them mot cuon sach moi");
            System.out.println("2. Sua thong tin sach theo ID");
            System.out.println("3. Xoa sach theo ID");
            System.out.println("4. Tim kiem sach theo ten");
            System.out.println("5. Hien thi toan bo danh sach sach");
            System.out.println("0. Quay lai Menu Chinh");
            System.out.print("=> Lua chon cua ban: ");
            
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    themMoiSach(sc);
                    break;
                case "2":
                    suaSach(sc);
                    break;
                case "3":
                    xoaSach(sc);
                    break;
                case "4":
                    timSach(sc);
                    break;
                case "5":
                    qltv.hienThiDanhSach();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("=> Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }

    private static void themMoiSach(Scanner sc) {
        try {
            System.out.println("\n--- THEM SACH MOI ---");
            System.out.print("Chon loai sach (1: Sach giao khoa, 2: Tai lieu tham khao): ");
            int loai = Integer.parseInt(sc.nextLine());

            System.out.print("Nhap ten sach: ");
            String ten = sc.nextLine();
            System.out.print("Nhap nha xuat ban: ");
            String nxb = sc.nextLine();
            System.out.print("Nhap nam xuat ban: ");
            int nam = Integer.parseInt(sc.nextLine());

            Sach sachMoi;
            if (loai == 1) {
                System.out.print("Nhap mon hoc: ");
                String monHoc = sc.nextLine();
                sachMoi = new SachGiaoKhoa(ten, nxb, nam, monHoc);
            } else if (loai == 2) {
                System.out.print("Nhap linh vuc: ");
                String linhVuc = sc.nextLine();
                sachMoi = new TaiLieu(ten, nxb, nam, linhVuc);
            } else {
                System.out.println("Loai sach khong hop le.");
                return;
            }
            qltv.themSach(sachMoi);
        } catch (NumberFormatException e) {
            System.err.println("Loi: Vui long nhap so hop le cho 'loai' hoac 'nam xuat ban'.");
        }
    }
    
    private static void suaSach(Scanner sc) {
        System.out.println("\n--- SUA THONG TIN SACH ---");
        System.out.print("Nhap ID sach can sua: ");
        String id = sc.nextLine();
        
        if (qltv.timSachTheoId(id) == null) {
            System.out.println("=> Khong tim thay sach voi ID nay.");
            return;
        }

        try {
            System.out.print("Nhap ten sach moi: ");
            String tenMoi = sc.nextLine();
            System.out.print("Nhap nha xuat ban moi: ");
            String nxbMoi = sc.nextLine();
            System.out.print("Nhap nam xuat ban moi: ");
            int namMoi = Integer.parseInt(sc.nextLine());
            
            qltv.suaSach(id, tenMoi, nxbMoi, namMoi);
        } catch (NumberFormatException e) {
            System.err.println("Loi: Nam xuat ban phai la mot so.");
        }
    }

    private static void xoaSach(Scanner sc) {
        System.out.println("\n--- XOA SACH ---");
        System.out.print("Nhap ID sach can xoa: ");
        String id = sc.nextLine();
        qltv.xoaSach(id);
    }

    private static void timSach(Scanner sc) {
        System.out.println("\n--- TIM SACH ---");
        System.out.print("Nhap tu khoa ten sach can tim: ");
        String keyword = sc.nextLine();
        List<Sach> ketQua = qltv.timSachTheoTen(keyword);
        if (ketQua.isEmpty()) {
            System.out.println("=> Khong tim thay sach nao phu hop.");
        } else {
            System.out.println("=> Tim thay " + ketQua.size() + " ket qua:");
            ketQua.forEach(System.out::println);
        }
    }

    /**
     * Menu dành riêng cho việc quản lý phiếu mượn
     */
    private static void menuQuanLyPhieuMuon(Scanner sc) {
        while (true) {
            System.out.println("\n--- MENU QUAN LY PHIEU MUON ---");
            System.out.println("1. Tao phieu muon moi");
            System.out.println("2. Ghi nhan tra sach");
            System.out.println("3. Hien thi danh sach phieu muon");
            System.out.println("0. Quay lai Menu Chinh");
            System.out.print("=> Lua chon cua ban: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    themMoiPhieuMuon(sc);
                    break;
                case "2":
                    ghiNhanTraSach(sc);
                    break;
                case "3":
                    quanLyPM.hienThiDanhSach();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("=> Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }
    
    /**
     * Chức năng thêm một phiếu mượn mới
     */
    private static void themMoiPhieuMuon(Scanner sc) {
        System.out.println("\n--- THEM PHIEU MUON MOI ---");
        System.out.print("Nhap ID nguoi doc: ");
        String idNguoiDoc = sc.nextLine();

        // Kiểm tra người đọc có tồn tại không
        if (quanLyND.timNguoiDocTheoId(idNguoiDoc) == null) {
            System.out.println("=> Loi: Khong tim thay nguoi doc voi ID nay.");
            return;
        }

        System.out.print("Nhap ID sach muon: ");
        String idSach = sc.nextLine();

        // Kiểm tra xem sách có tồn tại không
        Sach sach = qltv.timSachTheoId(idSach);
        if (sach == null) {
            System.out.println("=> Loi: Khong tim thay sach voi ID nay trong thu vien.");
            return;
        }

        // Kiểm tra sách đã được mượn chưa
        if (sach.isDaMuon()) {
            System.out.println("=> Loi: Sach nay da duoc muon roi.");
            return;
        }

        // Tạo phiếu mượn và cập nhật trạng thái sách
        PhieuMuon phieuMoi = new PhieuMuon(idNguoiDoc, idSach);
        quanLyPM.themPhieuMuon(phieuMoi);
        sach.setDaMuon(true);
    }
    
    /**
     * Chức năng ghi nhận một cuốn sách đã được trả
     */
    private static void ghiNhanTraSach(Scanner sc) {
        System.out.println("\n--- GHI NHAN TRA SACH ---");
        System.out.print("Nhap ID phieu muon can tra: ");
        String idPhieu = sc.nextLine();

        PhieuMuon phieuCanTra = quanLyPM.timPhieuMuonChuaTra(idPhieu);

        if (phieuCanTra != null) {
            phieuCanTra.setDaTra(true);
            
            // Cập nhật trạng thái sách về chưa mượn
            Sach sach = qltv.timSachTheoId(phieuCanTra.getIdSach());
            if (sach != null) {
                sach.setDaMuon(false);
            }
            
            System.out.println("=> Da ghi nhan tra sach thanh cong cho phieu " + idPhieu);
        } else {
            System.out.println("=> Loi: Khong tim thay phieu muon co ID nay hoac phieu da duoc tra truoc do.");
        }
    }

    /**
     * Menu cho các chức năng quản lý người đọc
     */
    private static void menuQuanLyNguoiDoc(Scanner sc) {
        while (true) {
            System.out.println("\n--- MENU QUAN LY NGUOI DOC ---");
            System.out.println("1. Them nguoi doc moi");
            System.out.println("2. Xoa nguoi doc theo ID");
            System.out.println("3. Tim nguoi doc theo ID");
            System.out.println("4. Hien thi danh sach nguoi doc");
            System.out.println("0. Quay lai Menu Chinh");
            System.out.print("=> Lua chon cua ban: ");
            
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    themMoiNguoiDoc(sc);
                    break;
                case "2":
                    xoaNguoiDoc(sc);
                    break;
                case "3":
                    timNguoiDoc(sc);
                    break;
                case "4":
                    quanLyND.hienThiDanhSach();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("=> Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }

    private static void themMoiNguoiDoc(Scanner sc) {
        System.out.println("\n--- THEM NGUOI DOC MOI ---");
        System.out.print("Nhap ID nguoi doc: ");
        String id = sc.nextLine();
        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String sdt = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        String diaChi = sc.nextLine();
        
        NguoiDoc ndMoi = new NguoiDoc(id, hoTen, sdt, diaChi);
        quanLyND.themNguoiDoc(ndMoi);
    }

    private static void xoaNguoiDoc(Scanner sc) {
        System.out.println("\n--- XOA NGUOI DOC ---");
        System.out.print("Nhap ID nguoi doc can xoa: ");
        String id = sc.nextLine();
        quanLyND.xoaNguoiDoc(id);
    }

    private static void timNguoiDoc(Scanner sc) {
        System.out.println("\n--- TIM NGUOI DOC ---");
        System.out.print("Nhap ID nguoi doc can tim: ");
        String id = sc.nextLine();
        NguoiDoc nd = quanLyND.timNguoiDocTheoId(id);
        if (nd != null) {
            System.out.println("=> Tim thay: " + nd);
        } else {
            System.out.println("=> Khong tim thay nguoi doc voi ID: " + id);
        }
    }
}