package service;

import model.PhieuMuon;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuanLyPhieuMuon {
    private List<PhieuMuon> danhSachPhieuMuon;
    private static final String FILE_PATH = "phieumuon.txt"; // File de luu du lieu phieu muon
    // Dinh dang ngay thang de dam bao nhat quan khi doc/ghi file
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE; 

    /**
     * Constructor: Tu dong tai du lieu phieu muon tu file khi khoi tao.
     */
    public QuanLyPhieuMuon() {
        this.danhSachPhieuMuon = docFilePhieuMuon();
    }
    
    // =================================================================
    // CAC CHUC NANG QUAN LY PHIEU MUON
    // =================================================================
    
    /**
     * Them mot phieu muon moi vao danh sach.
     */
    public void themPhieuMuon(PhieuMuon phieu) {
        this.danhSachPhieuMuon.add(phieu);
        System.out.println("=> Da them phieu muon moi thanh cong!");
    }

    /**
     * Tim mot phieu muon theo ID, chi tra ve phieu chua duoc tra.
     * @param idPhieu ID cua phieu muon can tim.
     * @return Doi tuong PhieuMuon neu tim thay va chua tra, null neu nguoc lai.
     */
    public PhieuMuon timPhieuMuonChuaTra(String idPhieu) {
        for (PhieuMuon pm : danhSachPhieuMuon) {
            // So sanh ID khong phan biet hoa thuong va kiem tra phieu chua tra
            if (pm.getIdPhieuMuon().equalsIgnoreCase(idPhieu) && !pm.isDaTra()) {
                return pm;
            }
        }
        return null; // Khong tim thay phieu hop le
    }

    /**
     * Hien thi toan bo danh sach phieu muon hien co.
     */
    public void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH PHIEU MUON ---");
        if (danhSachPhieuMuon.isEmpty()) {
            System.out.println("Chua co phieu muon nao trong he thong.");
            return;
        }
        for (PhieuMuon pm : danhSachPhieuMuon) {
            System.out.println(pm);
        }
        System.out.println("-----------------------------");
    }

    // =================================================================
    // CAC CHUC NANG DOC/GHI FILE
    // =================================================================

    /**
     * Luu toan bo danh sach phieu muon hien tai vao file.
     * Nen goi phuong thuc nay truoc khi ket thuc chuong trinh.
     */
    public void luuFilePhieuMuon() {
        // Su dung try-with-resources de tu dong dong file
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (PhieuMuon phieu : danhSachPhieuMuon) {
                writer.println(phieu.toDataString()); // Goi phuong thuc toDataString()
            }
            System.out.println("=> Da luu danh sach phieu muon vao file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("=> Loi khi ghi file phieu muon: " + e.getMessage());
        }
    }

    /**
     * Doc du lieu phieu muon tu file.
     * Phuong thuc nay la private vi chi duoc su dung noi bo boi constructor.
     */
    private List<PhieuMuon> docFilePhieuMuon() {
        List<PhieuMuon> danhSachDocDuoc = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Kiem tra file ton tai, neu khong thi tra ve danh sach rong
        if (!file.exists()) {
            System.out.println("File phieumuon.txt chua ton tai. Se tao file moi khi luu.");
            return danhSachDocDuoc;
        }

        int maxIdFromFile = 0; // Bien de tim ID lon nhat, tranh bi trung lap

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", -1);
                if (data.length < 6) continue; // Dam bao du lieu co du 6 cot

                // Doc du lieu tu cac cot
                String idPhieuMuon = data[0];
                String idNguoiDoc = data[1];
                String idSach = data[2];
                LocalDate ngayMuon = LocalDate.parse(data[3], DATE_FORMATTER);
                LocalDate ngayTraDuKien = LocalDate.parse(data[4], DATE_FORMATTER);
                boolean daTra = Boolean.parseBoolean(data[5]);

                // Su dung ham tao day du de khoi tao lai doi tuong
                PhieuMuon phieu = new PhieuMuon(idPhieuMuon, idNguoiDoc, idSach, ngayMuon, ngayTraDuKien, daTra);
                danhSachDocDuoc.add(phieu);

                // Cap nhat ID lon nhat de dong bo bo dem nextId
                try {
                    // Cat chuoi "PM" de lay phan so
                    int currentIdNum = Integer.parseInt(idPhieuMuon.substring(2)); 
                    if (currentIdNum > maxIdFromFile) {
                        maxIdFromFile = currentIdNum;
                    }
                } catch (NumberFormatException e) {
                    // Bo qua neu ID trong file khong dung dinh dang
                    System.err.println("Canh bao: Phat hien ID phieu muon khong hop le trong file: " + idPhieuMuon);
                }
            }
            System.out.println("Tai du lieu phieu muon tu file thanh cong!");
        } catch (Exception e) {
            System.err.println("Loi nghiem trong khi doc file phieumuon.txt: " + e.getMessage());
        }

        // Dong bo bo dem ID de phieu moi tao ra khong bi trung
        PhieuMuon.capNhatNextId(maxIdFromFile);
        return danhSachDocDuoc;
    }
}