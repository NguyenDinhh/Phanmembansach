using System;

namespace WEBBANSACH.Models
{
    public class DiaChiNhanHang
    {
        public int DiaChiNhanHangID { get; set; } // ID của địa chỉ nhận hàng
        public string TenDangNhap { get; set; } // Tên đăng nhập của khách hàng
        public string Ten { get; set; } // Tên khách hàng
        public string DienThoai { get; set; } // Số điện thoại của khách hàng
        public string DiaChi { get; set; } // Địa chỉ nhận hàng
    }
}
