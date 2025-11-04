package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PhieuMuon {
    // THÊM MỚI: Định nghĩa DATE_FORMATTER ngay tại đây
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private String idPhieuMuon;
    private String idNguoiDoc;
    private String idSach;
    private LocalDate ngayMuon;
    private LocalDate ngayTraDuKien;
    private boolean daTra;

    private static int nextId = 1;

    // Constructor để TẠO MỚI phiếu mượn
    public PhieuMuon(String idNguoiDoc, String idSach) {
        this.idPhieuMuon = String.format("PM%04d", nextId++);
        this.idNguoiDoc = idNguoiDoc;
        this.idSach = idSach;
        this.ngayMuon = LocalDate.now();
        this.ngayTraDuKien = this.ngayMuon.plusDays(14); // Mượn trong 14 ngày
        this.daTra = false;
    }

    // THÊM MỚI: Constructor để ĐỌC TỪ FILE (khôi phục đối tượng)
    public PhieuMuon(String idPhieuMuon, String idNguoiDoc, String idSach, LocalDate ngayMuon, LocalDate ngayTraDuKien, boolean daTra) {
        this.idPhieuMuon = idPhieuMuon;
        this.idNguoiDoc = idNguoiDoc;
        this.idSach = idSach;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.daTra = daTra;
    }

    // Getters
    public String getIdPhieuMuon() { return idPhieuMuon; }
    public String getIdNguoiDoc() { return idNguoiDoc; }
    public String getIdSach() { return idSach; }
    public boolean isDaTra() { return daTra; }
    
    // Setter
    public void setDaTra(boolean daTra) { this.daTra = daTra; }
    
    // THÊM MỚI: Phương thức tĩnh để đồng bộ ID khi đọc file
    public static void capNhatNextId(int maxIdFromFile) {
        nextId = maxIdFromFile + 1;
    }

    // Phương thức để ghi vào file
    public String toDataString() {
        return String.join(";",
                idPhieuMuon,
                idNguoiDoc,
                idSach,
                ngayMuon.format(DATE_FORMATTER), // Luôn dùng định dạng yyyy-MM-dd khi lưu file
                ngayTraDuKien.format(DATE_FORMATTER),
                String.valueOf(daTra));
    }

    // THÊM MỚI: Ghi đè phương thức toString() để hiển thị đẹp hơn
    @Override
    public String toString() {
        return String.format("ID Phiếu: %s | ID Độc giả: %s | ID Sách: %s | Ngày mượn: %s | Hạn trả: %s | Tình trạng: %s",
                idPhieuMuon, idNguoiDoc, idSach, 
                ngayMuon.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
                ngayTraDuKien.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                daTra ? "Đã trả" : "Chưa trả");
    }
}