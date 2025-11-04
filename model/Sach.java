package model;

public abstract class Sach{
    protected String id;
    protected String tenSach;
    protected String nhaXuatBan;
    protected int namXuatBan;
    protected boolean daMuon;
    
    private static int nextID = 1;
    public Sach (String tenSach, String nhaXuatBan, int namXuatBan)
    {
        this.id = String.format("$%04d", nextID++);
        this.tenSach = tenSach;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.daMuon = false;
    }
    // ✅ THÊM: Constructor để ĐỌC TỪ FILE (giữ nguyên ID)
    public Sach(String id, String tenSach, String nhaXuatBan, int namXuatBan, boolean daMuon) {
        this.id = id;
        this.tenSach = tenSach;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.daMuon = daMuon;
    }
    // Phương thức trừu tượng 
    public abstract String getLoaiSach();
    // get, set
    public String getId() {
        return id;
    }
    public String getTenSach() {
        return tenSach;
    }
    public boolean isDaMuon() {
        return daMuon;
    }
    public void setTenSach(String tenSach) {
    this.tenSach = tenSach;
}

public void setNhaXuatBan(String nhaXuatBan) {
    this.nhaXuatBan = nhaXuatBan;
}

public void setNamXuatBan(int namXuatBan) {
    this.namXuatBan = namXuatBan;
}

    public void setDaMuon(boolean daMuon) {
        this.daMuon = daMuon;
    }

    @Override
    public String toString()
    {
        return String.format("ID: %s | Ten: %s | NXB: %s | Nam: %d | Loai: %s | Trang thai: %s",
                id, tenSach, nhaXuatBan, namXuatBan, getLoaiSach(), (daMuon ? "Đa muon" : "Co san"));
    }
    // Dùng để lưu vào file text
    public String toDataString() {
        return String.join(";", id, tenSach, nhaXuatBan, String.valueOf(namXuatBan), String.valueOf(daMuon));
    }
    // Phương thức đồng bộ nextID sau khi đọc file
public static void capNhatNextId(int maxIdFromFile) {
    nextID = maxIdFromFile + 1;
}
}
