package interfaces;
import model.NguoiDoc;
import model.Sach;

public interface IQuanLi {
    // QUẢN LÍ SÁCH
    void themSach(Sach sach);
    void suaSach(String idSach);
    void xoaSach(String idSach);
    void xemDanhSachSach();

    // QUẢN LÍ NGƯỜI ĐỌC
    void themNguoiDoc(NguoiDoc nguoiDoc);
    void xoaNguoiDoc(String idNguoiDoc);
    void xemDanhSachNguoiDoc();
}
