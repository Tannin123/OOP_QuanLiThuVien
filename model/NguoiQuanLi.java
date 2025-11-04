package model;

public abstract class NguoiQuanLi extends Person {
    private String tenDangNhap;
    private String matKhau;

    public NguoiQuanLi(String id, String hoTen, String soDienThoai, String diaChi, String tenDangNhap, String matKhau) {
        super(id, hoTen, soDienThoai, diaChi);
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public boolean kiemTraMatKhau(String matKhau) {
        return this.matKhau != null && this.matKhau.equals(matKhau);
    }

    @Override
    public abstract void hienThiVaiTro();
}

