using System;

namespace WEBBANSACH.Models
{
    public class ChiTietDonHang
    {
        public int ChiTietDonHangID { get; set; } // ID của chi tiết đơn hàng
        public int DonHangID { get; set; } // ID của đơn hàng
        public int SachID { get; set; } // ID của sách
        public int SoLuong { get; set; } // Số lượng sách trong đơn hàng
        public Double Gia { get; set; } // Giá của sách trong đơn hàng
        public String Anh {  get; set; }
        public String TenSach { get; set; }
    }
}
