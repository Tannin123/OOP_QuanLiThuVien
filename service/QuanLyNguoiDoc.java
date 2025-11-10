package service;


import model.NguoiDoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyNguoiDoc {
    
    private List<NguoiDoc> danhSachNguoiDoc;  
    private static final String FILE_PATH = "nguoidoc.txt";  

    /**
     * Constructor: Tự động tải dữ liệu từ file khi khởi tạo.
     */
    public QuanLyNguoiDoc() {
        this.danhSachNguoiDoc = docFile();
    }

    // =================================================================
    // CÁC CHỨC NĂNG QUẢN LÝ NGƯỜI ĐỌC
    // =================================================================

    /**
     * Thêm một người đọc mới vào danh sách.
     */
    public void themNguoiDoc(NguoiDoc nguoiDoc) {
        this.danhSachNguoiDoc.add(nguoiDoc);
        System.out.println("=> Da them nguoi doc moi thanh cong: " + nguoiDoc.getHoTen());
    }

    /**
     * Xóa người đọc theo ID.
     */
    public boolean xoaNguoiDoc(String id) {
        boolean removed = this.danhSachNguoiDoc.removeIf(nd -> nd.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("=> Da xoa thanh cong nguoi doc co ID: " + id);
        } else {
            System.out.println("=> Loi: Khong tim thay nguoi doc co ID " + id + " de xoa.");
        }
        return removed;
    }

    /**
     * Tìm người đọc theo ID.
     */
    public NguoiDoc timNguoiDocTheoId(String id) {
        for (NguoiDoc nd : this.danhSachNguoiDoc) {
            if (nd.getId().equalsIgnoreCase(id)) {
                return nd;
            }
        }
        return null;
    }

    /**
     * Hiển thị toàn bộ danh sách người đọc.
     */
    public void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH NGUOI DOC ---");
        if (danhSachNguoiDoc.isEmpty()) {
            System.out.println("Chua co nguoi doc nao trong he thong.");
            return;
        }
        for (NguoiDoc nd : this.danhSachNguoiDoc) {
            System.out.println(nd);
        }
        System.out.println("---------------------------");
    }

    // =================================================================
    // CÁC CHỨC NĂNG ĐỌC/GHI FILE
    // =================================================================

    /**
     * Lưu toàn bộ danh sách người đọc vào file.
     */
    public void luuThayDoiVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (NguoiDoc nd : danhSachNguoiDoc) {
                // Định dạng: id;hoTen;soDienThoai;diaChi
                writer.println(String.join(";", 
                    nd.getId(), 
                    nd.getHoTen(), 
                    nd.getSoDienThoai(), 
                    nd.getDiaChi()));
            }
            System.out.println("\n=> Da luu thanh cong moi thay doi vao file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("=> Loi nghiem trong khi ghi file: " + e.getMessage());
        }
    }

    /**
     * Đọc dữ liệu người đọc từ file.
     */
    private List<NguoiDoc> docFile() {
        List<NguoiDoc> danhSachDocDuoc = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            System.out.println("File nguoidoc.txt chua ton tai. Se tao file moi khi luu.");
            return danhSachDocDuoc;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", -1);
                if (data.length < 4) continue; // Đảm bảo đủ 4 cột
                
                String id = data[0];
                String hoTen = data[1];
                String soDienThoai = data[2];
                String diaChi = data[3];
                
                NguoiDoc nd = new NguoiDoc(id, hoTen, soDienThoai, diaChi);
                danhSachDocDuoc.add(nd);
            }
            System.out.println("Tai du lieu nguoi doc tu file thanh cong! Tong so: " + danhSachDocDuoc.size());
        } catch (IOException e) {
            System.err.println("Loi khi doc file nguoidoc.txt: " + e.getMessage());
        }
        
        return danhSachDocDuoc;
    }
} 

