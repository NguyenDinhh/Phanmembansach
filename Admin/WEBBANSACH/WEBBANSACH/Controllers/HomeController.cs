using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WEBBANSACH.Repositories;

namespace WEBBANSACH.Controllers
{
    public class HomeController : Controller
    {
        private TaiKhoanRepository TaiKhoanRepository = new TaiKhoanRepository();
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
        
        public ActionResult DangNhap()
        {
            return View();

        }
        public ActionResult Login(string username, string password)
        {
            // Lấy danh sách tài khoản admin từ cơ sở dữ liệu
            var adminList = TaiKhoanRepository.GetAdmin();
            ViewBag.ErrorMessage = null;

            // Kiểm tra nếu danh sách admin trống
            if (adminList == null || adminList.Count == 0)
            {
                ViewBag.ErrorMessage = "Không có tài khoản admin nào trong hệ thống.";
                return View("DangNhap");
            }

            foreach (var admin in adminList)
            {
                // So sánh tên đăng nhập không phân biệt chữ hoa chữ thường
                if (string.Equals(admin.TenDangNhap.Trim(), username.Trim(), StringComparison.OrdinalIgnoreCase)== true)
                {
                    // Kiểm tra mật khẩu
                    if (string.Equals(admin.MatKhau.Trim(), password.Trim(), StringComparison.Ordinal) == true)
                    {
                        // Đăng nhập thành công
                        return RedirectToAction("QuanTriVien", "QuanTriVien");
                    }
                    else
                    {
                        // Mật khẩu không đúng
                        ViewBag.ErrorMessage = "Mật khẩu không đúng.";
                        return View("DangNhap");
                    }
                }
            }

            // Nếu không tìm thấy tên đăng nhập khớp
            ViewBag.ErrorMessage = "Không tồn tại tài khoản với tên đăng nhập này.";
            return View("DangNhap");
        }
        
    }
}