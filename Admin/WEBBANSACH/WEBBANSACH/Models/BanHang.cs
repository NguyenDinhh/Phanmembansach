using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class BanHang
    {
        public int DonHangID { get; set; }
        public String DiaChiNhanHang { get; set; } 
        public String TenDangNhap { get; set; } 
        public String Ten { get; set; }
        public String DienThoai { get; set; }
        public Double TongTien { get; set; }
       
    }
}