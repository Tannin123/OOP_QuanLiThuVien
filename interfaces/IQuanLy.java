package interfaces;

import model.Sach;


public interface IQuanLy {
    void themSach(Sach sach);
    
    boolean suaSach(String id, String tenMoi, String nxbMoi, int namMoi);
    
    boolean xoaSach(String idSach);
    
    void xemDanhSachSach();
    
    Sach timSachTheoId(String id);

}