package model;

public class ThuThu extends NguoiQuanLi {
   public ThuThu(String id, String hoTen, String soDienThoai, String diaChi, String tenDangNhap, String matKhau) 
    {
        super(id, hoTen, soDienThoai, diaChi, tenDangNhap, matKhau);
    }
    @Override
    public void hienThiVaiTro()
    {
        System.out.println("Vai tro: Thu thu");
    }
        // Các hành động của Thủ thư sẽ gọi các phương thức tương ứng trong lớp Service
    // Ví dụ: public void themSach(ThuVienService service, Sach sach) { service.themSach(sach); }
}
 
