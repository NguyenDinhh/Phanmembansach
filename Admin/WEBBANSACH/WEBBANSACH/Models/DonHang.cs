﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class DonHang
    {
        public int DonHangID { get; set; }
        public int DiaChiNhanHangID { get; set; } 
        public String TenDangNhap { get; set; }
        public String TinhTrang { get; set; } 
        public Double TongTien { get; set; }
    }
}