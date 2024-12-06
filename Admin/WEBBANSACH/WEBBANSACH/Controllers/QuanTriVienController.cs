﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;
using WEBBANSACH.Models;

namespace WEBBANSACH.Controllers
{
    public class QuanTriVienController : Controller
    {
        private readonly FirebaseService_NhaXuatBan _firebaseService;
        private readonly FirebaseService_TacGia _firebaseServiceTacGia;
        private readonly FirebaseService_TheLoai _firebaseServiceTheLoai;
        private readonly FirebaseService_Sach _firebaseServiceSach ;

        // Constructor: Initialize FirebaseService
        public QuanTriVienController()
        {
            _firebaseService = new FirebaseService_NhaXuatBan();
            _firebaseServiceTacGia = new FirebaseService_TacGia();
            _firebaseServiceTheLoai = new FirebaseService_TheLoai();
            _firebaseServiceSach = new FirebaseService_Sach();
        }

        public ActionResult Index()
        {
            return View();
        }

        public ActionResult QuanTriVien()
        {
            return View();
        }

        [HttpGet]
        public async Task<ActionResult> QuanLiNhaXuatBan(int? NhaXuatBanID)
        {
            // Fetch danh sách nhà xuất bản từ Firebase
            var nhaXuatBanList = await _firebaseService.GetAllNhaXuatBanAsync();
            
            // Gán danh sách vào ViewBag
            ViewBag.NhaXuatBanList = nhaXuatBanList;
            // Kiểm tra xem có chọn nhà xuất bản hay không
            if (NhaXuatBanID == null || NhaXuatBanID == 0)
            {
                // Kiểm tra nếu danh sách không có dữ liệu
                if (nhaXuatBanList == null || !nhaXuatBanList.Any())
                {
                    // Nếu không có nhà xuất bản, trả về thông báo hoặc xử lý thích hợp
                    ViewBag.Message = "Không có nhà xuất bản nào trong hệ thống.";
                }
                return View();
            }

            // Nếu có NhaXuatBanID, lấy nhà xuất bản cụ thể
            var nhaXuatBan = await _firebaseService.GetNhaXuatBanAsync(NhaXuatBanID.Value);

            // Kiểm tra xem có nhà xuất bản nào với ID này không
            if (nhaXuatBan == null)
            {
                // Nếu không tìm thấy nhà xuất bản, có thể trả về thông báo hoặc redirect
                return HttpNotFound("Nhà xuất bản không tồn tại.");
            }
            // Trả về View với Model là nhà xuất bản cần sửa
            return View(nhaXuatBan);
        }
        [HttpPost]
        public async Task<ActionResult> ThemNhaXuatBan(NhaXuatBan nhaXuatBan)
        {
            // Kiểm tra ModelState để đảm bảo dữ liệu hợp lệ
            if (ModelState.IsValid)
            {
                // Tạo ID cho nhà xuất bản, bạn có thể dùng GUID hoặc cách tạo ID tự động khác
                var key = Guid.NewGuid().ToString();  // Sử dụng GUID làm ID duy nhất

                // Thêm nhà xuất bản vào Firebase
                bool result = await _firebaseService.AddNhaXuatBanAsync(key, nhaXuatBan);

                if (result)
                {
                    // Nếu thêm thành công, chuyển hướng về trang danh sách nhà xuất bản
                    return RedirectToAction("QuanLiNhaXuatBan"); // Hoặc bạn có thể truyền thêm tham số nếu cần
                }
                else
                {
                    // Nếu thêm thất bại, hiển thị thông báo lỗi
                    ModelState.AddModelError("", "Không thể thêm nhà xuất bản. Vui lòng thử lại.");
                }
            }

            // Nếu dữ liệu không hợp lệ hoặc thêm không thành công, trả về form với lỗi
            return View(nhaXuatBan); // Trả về form để người dùng nhập lại
        }
        // Action để cập nhật nhà xuất bản
        [HttpPost]
        public async Task<ActionResult> SuaNhaXuatBan(NhaXuatBan nhaXuatBan)
        {
            if (ModelState.IsValid)
            {
                // Gọi FirebaseService để cập nhật nhà xuất bản
                bool result = await _firebaseService.UpdateNhaXuatBanAsync( nhaXuatBan);

                if (result)
                {
                    // Nếu cập nhật thành công, chuyển hướng về trang danh sách nhà xuất bản
                    return RedirectToAction("QuanLiNhaXuatBan");
                }
                else
                {
                    // Nếu thất bại, thông báo lỗi
                    ModelState.AddModelError("", "Không thể cập nhật nhà xuất bản. Vui lòng thử lại.");
                }
            }

            // Nếu Model không hợp lệ, trả về lại view để người dùng chỉnh sửa
            return View(nhaXuatBan);
        }

        [HttpPost]
        public async Task<ActionResult> XoaNhaXuatBan(int nhaXuatBanID)
        {
            if (nhaXuatBanID == 0)
            {
                // Nếu không có ID hợp lệ, trả về thông báo lỗi
                ModelState.AddModelError("", "Mã nhà xuất bản không hợp lệ.");
                return RedirectToAction("QuanLiNhaXuatBan");
            }

            // Thực hiện xóa nhà xuất bản từ Firebase
            bool result = await _firebaseService.XoaNhaXuatBanAsync(nhaXuatBanID);

            if (result)
            {
                // Nếu xóa thành công, chuyển hướng về trang danh sách nhà xuất bản
                return RedirectToAction("QuanLiNhaXuatBan");
            }
            else
            {
                // Nếu xóa thất bại, hiển thị thông báo lỗi
                ModelState.AddModelError("", "Không thể xóa nhà xuất bản. Vui lòng thử lại.");
            }

            return RedirectToAction("QuanLiNhaXuatBan");
        }

        [HttpGet]
     
        public async Task<ActionResult> QuanLiTacGia(int? TacGiaID)
        {
            // Fetch danh sách tác giả từ Firebase hoặc từ nguồn dữ liệu
            var tacGiaList = await _firebaseServiceTacGia.GetAllTacGiaAsync();

            // Gán danh sách tác giả vào ViewBag
            ViewBag.TacGiaList = tacGiaList;

            // Kiểm tra xem có chọn tác giả nào không
            if (TacGiaID == null || TacGiaID == 0)
            {
                // Kiểm tra nếu danh sách không có dữ liệu
                if (tacGiaList == null || !tacGiaList.Any())
                {
                    // Nếu không có tác giả, trả về thông báo hoặc xử lý thích hợp
                    ViewBag.Message = "Không có tác giả nào trong hệ thống.";
                }
                return View();  // Trả về view với danh sách tác giả
            }

            // Nếu có TacGiaID, lấy tác giả cụ thể
            var tacGia = await _firebaseServiceTacGia.GetTacGiaAsync(TacGiaID.Value);

            // Kiểm tra xem có tác giả nào với ID này không
            if (tacGia == null)
            {
                // Nếu không tìm thấy tác giả với ID này, trả về lỗi không tìm thấy
                return HttpNotFound("Tác giả không tồn tại.");
            }

            // Trả về View với Model là tác giả cần sửa
            return View(tacGia);
        }


        [HttpPost]
        public async Task<ActionResult> ThemTacGia(TacGia tacGia)
        {
            // Kiểm tra ModelState để đảm bảo dữ liệu hợp lệ
            if (ModelState.IsValid)
            {
                // Tạo ID cho tác giả, bạn có thể dùng GUID hoặc cách tạo ID tự động khác
                var key = Guid.NewGuid().ToString();  // Sử dụng GUID làm ID duy nhất

                // Thêm tác giả vào Firebase
                bool result = await _firebaseServiceTacGia.AddTacGiaAsync(key, tacGia);

                if (result)
                {
                    // Nếu thêm thành công, chuyển hướng về trang danh sách tác giả
                    return RedirectToAction("QuanLiTacGia"); // Hoặc bạn có thể truyền thêm tham số nếu cần
                }
                else
                {
                    // Nếu thêm thất bại, hiển thị thông báo lỗi
                    ModelState.AddModelError("", "Không thể thêm tác giả. Vui lòng thử lại.");
                }
            }

            // Nếu dữ liệu không hợp lệ hoặc thêm không thành công, trả về form với lỗi
            return View(tacGia); // Trả về form để người dùng nhập lại
        }

        // Action để cập nhật tác giả
        [HttpPost]
        public async Task<ActionResult> SuaTacGia(TacGia tacGia)
        {
            if (ModelState.IsValid)
            {
                // Gọi FirebaseService để cập nhật tác giả
                bool result = await _firebaseServiceTacGia.UpdateTacGiaAsync(tacGia);

                if (result)
                {
                    // Nếu cập nhật thành công, chuyển hướng về trang danh sách tác giả
                    return RedirectToAction("QuanLiTacGia");
                }
                else
                {
                    // Nếu thất bại, thông báo lỗi
                    ModelState.AddModelError("", "Không thể cập nhật tác giả. Vui lòng thử lại.");
                }
            }

            // Nếu Model không hợp lệ, trả về lại view để người dùng chỉnh sửa
            return View(tacGia);
        }

        [HttpPost]
        public async Task<ActionResult> XoaTacGia(int tacGiaID)
        {
            if (tacGiaID == 0)
            {
                // Nếu không có ID hợp lệ, trả về thông báo lỗi
                ModelState.AddModelError("", "Mã tác giả không hợp lệ.");
                return RedirectToAction("QuanLiTacGia");
            }

            // Thực hiện xóa tác giả từ Firebase
            bool result = await _firebaseServiceTacGia.XoaTacGiaAsync(tacGiaID);

            if (result)
            {
                // Nếu xóa thành công, chuyển hướng về trang danh sách tác giả
                return RedirectToAction("QuanLiTacGia");
            }
            else
            {
                // Nếu xóa thất bại, hiển thị thông báo lỗi
                ModelState.AddModelError("", "Không thể xóa tác giả. Vui lòng thử lại.");
            }

            return RedirectToAction("QuanLiTacGia");
        }

        [HttpGet]
        public async Task<ActionResult> QuanLiTheLoai(int? TheLoaiID)
        {
            // Fetch danh sách thể loại từ Firebase
            var theLoaiList = await _firebaseServiceTheLoai.GetAllTheLoaiAsync();

            // Gán danh sách vào ViewBag
            ViewBag.TheLoaiList = theLoaiList;

            // Kiểm tra xem có chọn thể loại nào hay không
            if (TheLoaiID == null || TheLoaiID == 0)
            {
                // Kiểm tra nếu danh sách không có dữ liệu
                if (theLoaiList == null || !theLoaiList.Any())
                {
                    // Nếu không có thể loại, trả về thông báo hoặc xử lý thích hợp
                    ViewBag.Message = "Không có thể loại nào trong hệ thống.";
                }
                return View();
            }

            // Nếu có TheLoaiID, lấy thể loại cụ thể
            var theLoai = await _firebaseServiceTheLoai.GetTheLoaiAsync(TheLoaiID.Value);

            // Kiểm tra xem có thể loại nào với ID này không
            if (theLoai == null)
            {
                // Nếu không tìm thấy thể loại, có thể trả về thông báo hoặc redirect
                return HttpNotFound("Thể loại không tồn tại.");
            }

            // Trả về View với Model là thể loại cần sửa
            return View(theLoai);
        }

        [HttpPost]
        public async Task<ActionResult> ThemTheLoai(TheLoai theLoai)
        {
            // Kiểm tra ModelState để đảm bảo dữ liệu hợp lệ
            if (ModelState.IsValid)
            {
                // Tạo ID cho thể loại, bạn có thể dùng GUID hoặc cách tạo ID tự động khác
                var key = Guid.NewGuid().ToString();  // Sử dụng GUID làm ID duy nhất

                // Thêm thể loại vào Firebase
                bool result = await _firebaseServiceTheLoai.AddTheLoaiAsync(key, theLoai);

                if (result)
                {
                    // Nếu thêm thành công, chuyển hướng về trang danh sách thể loại
                    return RedirectToAction("QuanLiTheLoai"); // Hoặc bạn có thể truyền thêm tham số nếu cần
                }
                else
                {
                    // Nếu thêm thất bại, hiển thị thông báo lỗi
                    ModelState.AddModelError("", "Không thể thêm thể loại. Vui lòng thử lại.");
                }
            }

            // Nếu dữ liệu không hợp lệ hoặc thêm không thành công, trả về form với lỗi
            return View(theLoai); // Trả về form để người dùng nhập lại
        }

        [HttpPost]
        public async Task<ActionResult> SuaTheLoai(TheLoai theLoai)
        {
            if (ModelState.IsValid)
            {
                // Gọi FirebaseService để cập nhật thể loại
                bool result = await _firebaseServiceTheLoai.UpdateTheLoaiAsync(theLoai);

                if (result)
                {
                    // Nếu cập nhật thành công, chuyển hướng về trang danh sách thể loại
                    return RedirectToAction("QuanLiTheLoai");
                }
                else
                {
                    // Nếu thất bại, thông báo lỗi
                    ModelState.AddModelError("", "Không thể cập nhật thể loại. Vui lòng thử lại.");
                }
            }

            // Nếu Model không hợp lệ, trả về lại view để người dùng chỉnh sửa
            return View(theLoai);
        }

        [HttpPost]
        public async Task<ActionResult> XoaTheLoai(int theLoaiID)
        {
            if (theLoaiID == 0)
            {
                // Nếu không có ID hợp lệ, trả về thông báo lỗi
                ModelState.AddModelError("", "Mã thể loại không hợp lệ.");
                return RedirectToAction("QuanLiTheLoai");
            }

            // Thực hiện xóa thể loại từ Firebase
            bool result = await _firebaseServiceTheLoai.XoaTheLoaiAsync(theLoaiID);

            if (result)
            {
                // Nếu xóa thành công, chuyển hướng về trang danh sách thể loại
                return RedirectToAction("QuanLiTheLoai");
            }
            else
            {
                // Nếu xóa thất bại, hiển thị thông báo lỗi
                ModelState.AddModelError("", "Không thể xóa thể loại. Vui lòng thử lại.");
            }

            return RedirectToAction("QuanLiTheLoai");
        }

        // Quản lý sách
        [HttpGet]
        public async Task<ActionResult> QuanLiSach(int? SachID)
        {
            // Fetch danh sách sách từ Firebase
            var sachList = await _firebaseServiceSach.GetAllSachAsync();
            var TacGiaList = await _firebaseServiceTacGia.GetAllTacGiaAsync();
            var TheLoaiList = await _firebaseServiceTheLoai.GetAllTheLoaiAsync();
            var NhaXuatBanList = await _firebaseService.GetAllNhaXuatBanAsync();
            // Gán danh sách vào ViewBag
            ViewBag.SachList = sachList;
            ViewBag.TacGiaList = TacGiaList;
            ViewBag.TheLoaiList = TheLoaiList;  
            ViewBag.NhaXuatBanList = NhaXuatBanList;

            // Kiểm tra xem có chọn sách nào không
            if (SachID == null || SachID == 0)
            {
                // Kiểm tra nếu danh sách không có dữ liệu
                if (sachList == null || !sachList.Any())
                {
                    // Nếu không có sách, trả về thông báo hoặc xử lý thích hợp
                    ViewBag.Message = "Không có sách nào trong hệ thống.";
                }
                return View();
            }

            // Nếu có SachID, lấy sách cụ thể
            var sach = await _firebaseServiceSach.GetSachAsync(SachID.Value);

            // Kiểm tra xem có sách nào với ID này không
            if (sach == null)
            {
                // Nếu không tìm thấy sách, có thể trả về thông báo hoặc redirect
                return HttpNotFound("Sách không tồn tại.");
            }

            // Trả về View với Model là sách cần sửa
            return View(sach);
        }

        [HttpPost]
        public async Task<ActionResult> ThemSach(Sach sach)
        {
            // Kiểm tra ModelState để đảm bảo dữ liệu hợp lệ
            if (ModelState.IsValid)
            {
                // Tạo ID cho sách, bạn có thể dùng GUID hoặc cách tạo ID tự động khác
                var key = Guid.NewGuid().ToString();  // Sử dụng GUID làm ID duy nhất

                // Thêm sách vào Firebase
                bool result = await _firebaseServiceSach.AddSachAsync(key, sach);

                if (result)
                {
                    // Nếu thêm thành công, chuyển hướng về trang danh sách sách
                    return RedirectToAction("QuanLiSach"); // Hoặc bạn có thể truyền thêm tham số nếu cần
                }
                else
                {
                    // Nếu thêm thất bại, hiển thị thông báo lỗi
                    ModelState.AddModelError("", "Không thể thêm sách. Vui lòng thử lại.");
                }
            }

            // Nếu dữ liệu không hợp lệ hoặc thêm không thành công, trả về form với lỗi
            return View(sach); // Trả về form để người dùng nhập lại
        }

        // Action để cập nhật sách
        [HttpPost]
        public async Task<ActionResult> SuaSach(Sach sach)
        {
            if (ModelState.IsValid)
            {
                // Gọi FirebaseService để cập nhật sách
                bool result = await _firebaseServiceSach.UpdateSachAsync(sach);

                if (result)
                {
                    // Nếu cập nhật thành công, chuyển hướng về trang danh sách sách
                    return RedirectToAction("QuanLiSach");
                }
                else
                {
                    // Nếu thất bại, thông báo lỗi
                    ModelState.AddModelError("", "Không thể cập nhật sách. Vui lòng thử lại.");
                }
            }

            // Nếu Model không hợp lệ, trả về lại view để người dùng chỉnh sửa
            return View(sach);
        }

        [HttpPost]
        public async Task<ActionResult> XoaSach(int sachID)
        {
            if (sachID == 0)
            {
                // Nếu không có ID hợp lệ, trả về thông báo lỗi
                ModelState.AddModelError("", "Mã sách không hợp lệ.");
                return RedirectToAction("QuanLiSach");
            }

            // Thực hiện xóa sách từ Firebase
            bool result = await _firebaseServiceSach.DeleteSachAsync(sachID);

            if (result)
            {
                // Nếu xóa thành công, chuyển hướng về trang danh sách sách
                return RedirectToAction("QuanLiSach");
            }
            else
            {
                // Nếu xóa thất bại, hiển thị thông báo lỗi
                ModelState.AddModelError("", "Không thể xóa sách. Vui lòng thử lại.");
            }

            return RedirectToAction("QuanLiSach");
        }
    }
}
