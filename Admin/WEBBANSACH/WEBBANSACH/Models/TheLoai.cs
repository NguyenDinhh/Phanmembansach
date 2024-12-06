using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class TheLoai
    {
        public int TheLoaiID { get; set; } // Khóa chính
        public string TenTheLoai { get; set; } // Tên thể loại
        public int SoLuong { get; set; } // Số lượng
        public int DaBan { get; set; } // Đã bán
        public string MoTa { get; set; } // Mô tả
        public string Anh { get; set; } // Hình ảnh
    }
}