package model;

public class TaiLieu extends Sach {
    private String linhVuc; // Ví dụ: Khoa học, Lịch sử,...

    public TaiLieu(String tenSach, String nhaXuatBan, int namXuatBan, String linhVuc) {
        super(tenSach, nhaXuatBan, namXuatBan);
        this.linhVuc = linhVuc;
    }
    // Constructor 2: Để ĐỌC TỪ FILE (giữ ID từ file)
    public TaiLieu(String id, String tenSach, String nhaXuatBan, int namXuatBan, boolean daMuon, String linhVuc) {
        super(id, tenSach, nhaXuatBan, namXuatBan, daMuon);
        this.linhVuc = linhVuc;
    }
    @Override
    public String getLoaiSach() {
        return "Tai lieu tham khao";
    }

    @Override
    public String toString() {
        // Giữ nguyên toString của bạn để hiển thị đẹp
        return super.toString() + " | Linh vuc: " + linhVuc;
    }
    
    /**
     * Dùng để ghi ra file, theo định dạng:
     * LoaiSach;id;tenSach;nhaXuatBan;namXuatBan;daMuon;linhVuc
     */
    @Override
    public String toDataString() {
        return String.join(";", getLoaiSach(), super.toDataString(), this.linhVuc);
    }
}