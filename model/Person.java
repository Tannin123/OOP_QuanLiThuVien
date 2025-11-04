package model;
// Phương thức trừu trượng
public abstract class Person
{
    protected String id;
    protected String hoTen;
    protected String soDienThoai;
    protected String diaChi;
// constructor
public Person ( String id, String hoTen, String soDienThoai, String diaChi)
{
    this.id = id;
    this.hoTen = hoTen;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
}
// getter
public String getId()
{
    return id;
}
public String getHoTen()
{
    return hoTen;
}
    public String getSoDienThoai()
    {
        return soDienThoai;
    }
    
    public String getDiaChi()
    {
        return diaChi;
    }
// phương thức trừu trượng
public abstract void hienThiVaiTro();

@Override 
public String toString()
{
   return "ID: " + id + " | Ten: " + hoTen + " | SĐT: " + soDienThoai + " | Dia chi: " + diaChi;
}
}
