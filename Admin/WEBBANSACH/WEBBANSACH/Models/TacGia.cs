using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WEBBANSACH.Models
{
    public class TacGia
    {
        public int TacGiaID { get; set; } // Khóa chính
        public string Ten { get; set; } // Tên tác giả
        public string CongViec { get; set; } // Công việc
        public string MoTa { get; set; } // Mô tả
        public string Anh { get; set; } // Hình ảnh
    }
}