using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class DonHang
    {
        public int DonHangID { get; set; } // Khóa chính
        public int DiaChiNhanHangID { get; set; } // Khóa chính
        public String TenDangNhap { get; set; } // Khóa chính
        public String TinhTrang { get; set; } // Khóa chính
       
    }
}