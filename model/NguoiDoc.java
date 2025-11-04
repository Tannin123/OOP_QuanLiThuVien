package model;

public class NguoiDoc extends Person {
 
    public NguoiDoc(String id, String hoTen, String soDienThoai, String diaChi)
    {
        super(id, hoTen, soDienThoai, diaChi);
    }
    @Override
    public void hienThiVaiTro()
    {
        System.out.println("Vai tro: Nguoc doc");
    }
 }