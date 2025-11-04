package model;

public class SachGiaoKhoa extends Sach {
    private String monHoc;

    public SachGiaoKhoa(String tenSach, String nhaXuatBan, int namXuatBan, String monHoc) {
        super(tenSach, nhaXuatBan, namXuatBan);
        this.monHoc = monHoc;
    }
    // Constructor 2: Để ĐỌC TỪ FILE (giữ ID từ file)
    public SachGiaoKhoa(String id, String tenSach, String nhaXuatBan, int namXuatBan, boolean daMuon, String monHoc) {
        super(id, tenSach, nhaXuatBan, namXuatBan, daMuon);
        this.monHoc = monHoc;
    }

    @Override
    public String getLoaiSach() {
        return "Sach giao khoa";
    }
    
   @Override
    public String toString() {
        // Giữ nguyên toString của bạn để hiển thị đẹp
        return super.toString() + " | Mon hoc: " + monHoc;
    }
    
    /**
     * Dùng để ghi ra file, theo định dạng:
     * LoaiSach;id;tenSach;nhaXuatBan;namXuatBan;daMuon;monHoc
     */
    @Override
    public String toDataString() {
        // super.toDataString() sẽ trả về "id;tenSach;nhaXuatBan;namXuatBan;daMuon"
        return String.join(";", getLoaiSach(), super.toDataString(), this.monHoc);
    }
}
