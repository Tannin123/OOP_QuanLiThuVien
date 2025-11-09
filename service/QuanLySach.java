package service;

import interfaces.IQuanLy; // <-- Import interface mới
import model.Sach;
import model.SachGiaoKhoa;
import model.TaiLieu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Cho lớp triển khai interface IQuanLySach
public class QuanLySach implements IQuanLy {
    
    private List<Sach> danhSachSach;
    private static final String FILE_PATH = "sach.txt";

    public QuanLySach() {
        this.danhSachSach = docFile();
    }

    @Override
    public void themSach(Sach sach) {
        this.danhSachSach.add(sach);
        System.out.println("=> Da them sach moi thanh cong: " + sach.getTenSach());
    }
    
    @Override
    public boolean suaSach(String id, String tenMoi, String nxbMoi, int namMoi) {
        Sach sachCanSua = timSachTheoId(id);
        if (sachCanSua != null) {
            sachCanSua.setTenSach(tenMoi);
            sachCanSua.setNhaXuatBan(nxbMoi);
            sachCanSua.setNamXuatBan(namMoi);
            System.out.println("=> Da cap nhat thanh cong sach co ID: " + id);
            return true;
        }
        System.out.println("=> Loi: Khong tim thay sach co ID " + id + " de sua.");
        return false;
    }

    @Override
    public boolean xoaSach(String id) {
        boolean removed = this.danhSachSach.removeIf(sach -> sach.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("=> Da xoa thanh cong sach co ID: " + id);
        } else {
            System.out.println("=> Loi: Khong tim thay sach co ID " + id + " de xoa.");
        }
        return removed;
    }
    
    @Override
    public Sach timSachTheoId(String id) {
        for (Sach sach : this.danhSachSach) {
            if (sach.getId().equalsIgnoreCase(id)) {
                return sach;
            }
        }
        return null;
    }
    
    public List<Sach> timSachTheoTen(String keyword) {
    // 1. Làm sạch từ khóa (keyword) người dùng nhập
    String cleanKeyword = keyword.toLowerCase().trim(); 
    
    return this.danhSachSach.stream()
            .filter(sach -> sach.getTenSach()
                                .toLowerCase()
                                .trim() 
                                .contains(cleanKeyword)) 
            .collect(Collectors.toList());
}

    @Override
    public void xemDanhSachSach() { // <-- Đổi tên từ hienThiDanhSach
        System.out.println("\n--- DANH SACH SACH TRONG THU VIEN ---");
        if (danhSachSach.isEmpty()) {
            System.out.println("Thu vien hien dang trong.");
            return;
        }
        for (Sach sach : this.danhSachSach) {
            System.out.println(sach);
        }
        System.out.println("------------------------------------");
    }

    public void luuThayDoiVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Sach sach : danhSachSach) {
                writer.println(sach.toDataString());
            }
            System.out.println("\n=> Da luu thanh cong moi thay doi vao file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("=> Loi nghiem trong khi ghi file: " + e.getMessage());
        }
    }
    
    /**
     * ĐÃ SỬA: Đọc file và GIỮ NGUYÊN ID gốc
     */
    private List<Sach> docFile() {
        // ... (Giữ nguyên phần code đọc file của bạn) ...
        List<Sach> danhSachDocDuoc = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File du lieu chua ton tai. Se tao file moi khi luu.");
            return danhSachDocDuoc; 
        }

        System.out.println("===== BAT DAU DOC FILE: " + FILE_PATH + " =====");
        int maxIdFromFile = 0; // Để cập nhật nextID

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println("Dong " + lineNumber + ": " + line);
                String[] data = line.split(";", -1);
                if (data.length < 7) {
                    System.err.println("Canh bao: Dong du lieu khong hop le: " + line);
                    continue;
                }
                
                String loaiSach = data[0];
                String idFromFile = data[1]; 
                String tenSach = data[2];
                String nxb = data[3];
                int namXB = Integer.parseInt(data[4]);
                boolean daMuon = Boolean.parseBoolean(data[5]);
                String thuocTinhRieng = data[6];

                Sach sach = null;
                
                if (loaiSach.equals("Sach giao khoa")) {
                    sach = new SachGiaoKhoa(idFromFile, tenSach, nxb, namXB, daMuon, thuocTinhRieng);
                } else if (loaiSach.equals("Tai lieu tham khao")) {
                    sach = new TaiLieu(idFromFile, tenSach, nxb, namXB, daMuon, thuocTinhRieng);
                } else {
                    System.err.println("Canh bao: Loai sach khong xac dinh: " + loaiSach);
                }

                if (sach != null) {
                    danhSachDocDuoc.add(sach);
                    
                    try {
                        int currentIdNum = Integer.parseInt(idFromFile.substring(1)); // Bỏ ký tự "$"
                        if (currentIdNum > maxIdFromFile) {
                            maxIdFromFile = currentIdNum;
                        }
                    } catch (Exception e) {
                        System.err.println("Canh bao: ID khong hop le: " + idFromFile);
                    }
                }
            }
            System.out.println("Tai du lieu tu file thanh cong! Tong so sach: " + danhSachDocDuoc.size());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Loi khi doc file: " + e.getMessage());
            e.printStackTrace();
        }
        
        Sach.capNhatNextId(maxIdFromFile);
        
        return danhSachDocDuoc;
    }
}