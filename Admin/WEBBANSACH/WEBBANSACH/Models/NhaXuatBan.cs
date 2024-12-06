using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class NhaXuatBan
    {
        public int NhaXuatBanID { get; set; } // Khóa chính
        public string Ten { get; set; } // Tên nhà xuất bản
        public string DiaChi { get; set; } // Địa chỉ
        public string DienThoai { get; set; } // Điện thoại
    }
}