using System;

namespace WEBBANSACH.Models
{
    public class Sach
    {
        public int SachID { get; set; } // Khóa chính
        public string Ten { get; set; } // Tên sách
        public int TacGiaID { get; set; } // Khóa ngoại tham chiếu đến TacGias
        public int NhaXuatBanID { get; set; } // Khóa ngoại tham chiếu đến NhaXuatBans
        public int TheLoaiID { get; set; } // Khóa ngoại tham chiếu đến TheLoais

        // Chuyển kiểu dữ liệu từ float sang decimal để tương thích với DECIMAL trong SQL
        public decimal Sao { get; set; } // Số sao
        public decimal Sale { get; set; } // Giảm giá
        public decimal Gia { get; set; } // Giá

        public int DiemThuong { get; set; } // Điểm thưởng
        public int DaBan { get; set; } // Số lượng đã bán
        public int SoLuong { get; set; } // Số lượng còn lại
        public DateTime NgayXuatBan { get; set; } // Ngày xuất bản
        public DateTime NgayKhoiBan { get; set; } // Ngày khởi bán
        public string MoTa { get; set; } // Mô tả sách
        public string Anh { get; set; } // Hình ảnh sách
    }
}
