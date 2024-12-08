using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using WEBBANSACH.Models;
using System.Collections.Generic;
using System.Linq;

namespace WEBBANSACH
{
    public class FirebaseService_TaiKhoan
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_TaiKhoan()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả tài khoản từ Firebase
        public async Task<List<TaiKhoan>> GetAllTaiKhoanAsync()
        {
            var url = $"{_firebaseUrl}/TaiKhoans.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                try
                {
                    // Nếu dữ liệu trả về là mảng JSON, deserializ thành List
                    var taiKhoanList = JsonConvert.DeserializeObject<List<TaiKhoan>>(jsonResponse);
                    if (taiKhoanList != null)
                    {
                        return taiKhoanList; // Trả về List<TaiKhoan>
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng, thử deserializ thành Dictionary
                    var taiKhoanDict = JsonConvert.DeserializeObject<Dictionary<string, TaiKhoan>>(jsonResponse);
                    if (taiKhoanDict != null)
                    {
                        return new List<TaiKhoan>(taiKhoanDict.Values); // Trả về List từ các giá trị của Dictionary
                    }
                }
            }

            // Trả về danh sách trống nếu có lỗi hoặc không có dữ liệu
            return new List<TaiKhoan>();
        }

        // Lấy tài khoản theo ID
        public async Task<TaiKhoan> GetTaiKhoanAsync(string tenDangNhap)
        {
            try
            {
                // Lấy tất cả tài khoản và tìm tài khoản theo tên đăng nhập
                var taiKhoanList = await GetAllTaiKhoanAsync();
                return taiKhoanList.FirstOrDefault(tk => tk.TenDangNhap == tenDangNhap);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null;
            }
        }

        // Thêm tài khoản vào Firebase
        public async Task<bool> AddTaiKhoanAsync(string key, TaiKhoan taiKhoan)
        {
            var json = JsonConvert.SerializeObject(taiKhoan);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var url = $"{_firebaseUrl}/TaiKhoans/{key}.json?auth={_firebaseSecret}";

            var response = await _httpClient.PutAsync(url, content);

            return response.IsSuccessStatusCode;
        }

        public async Task<bool> UpdateTaiKhoanAsync(TaiKhoan taiKhoan)
        {
            try
            {
                // Lấy tất cả tài khoản từ Firebase
                var taiKhoanData = await GetAllTaiKhoanAsync2(); // Giả sử GetAllTaiKhoanAsync trả về Dictionary<string, TaiKhoan>

                // Duyệt qua các tài khoản trong Dictionary để tìm tài khoản có TenDangNhap khớp
                foreach (var item in taiKhoanData)
                {
                    if (item.Value.TenDangNhap.Equals(taiKhoan.TenDangNhap))
                    {
                        taiKhoan.Anh = item.Value.Anh;
                        taiKhoan.TinhTrang = item.Value.TinhTrang;
                        if(item.Value.KhachHangID is null)
                        {
                            taiKhoan.NhanVienID = item.Value.NhanVienID;
                            taiKhoan.KhachHangID = null;
                        }
                        else
                        {
                            taiKhoan.KhachHangID = item.Value.KhachHangID;
                            taiKhoan.NhanVienID = null;
                        }     
                        // Chuyển đối tượng taiKhoan thành JSON
                        var jsonContent = JsonConvert.SerializeObject(taiKhoan);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        // URL của tài khoản trong Firebase
                        var url = $"{_firebaseUrl}/TaiKhoans/{item.Key}.json?auth={_firebaseSecret}";

                        // Gửi yêu cầu PUT tới Firebase để cập nhật tài khoản
                        var response = await _httpClient.PutAsync(url, content);

                        // Nếu cập nhật thành công, trả về true
                        return response.IsSuccessStatusCode;
                    }
                }

                // Nếu không tìm thấy tài khoản với TenDangNhap khớp
                return false;
            }
            catch (Exception ex)
            {
                // In lỗi nếu có và trả về false
                Console.WriteLine($"Lỗi khi cập nhật tài khoản: {ex.Message}");
                return false;
            }
        }


        public async Task<bool> KhoaTaiKhoanAsync(TaiKhoan taiKhoan)
        {
            try
            {
                // Lấy tất cả tài khoản từ Firebase
                var taiKhoanData = await GetAllTaiKhoanAsync2(); // Giả sử GetAllTaiKhoanAsync trả về Dictionary<string, TaiKhoan>

                // Duyệt qua các tài khoản trong Dictionary để tìm tài khoản có TenDangNhap khớp
                foreach (var item in taiKhoanData)
                {
                    if (item.Value.TenDangNhap.Equals(taiKhoan.TenDangNhap))
                    {
                        taiKhoan.Anh = item.Value.Anh;
                        taiKhoan.TenDangNhap = item.Value.TenDangNhap;
                        taiKhoan.MatKhau = item.Value.MatKhau;  
                        if (item.Value.KhachHangID is null)
                        {
                            taiKhoan.NhanVienID = item.Value.NhanVienID;
                            taiKhoan.KhachHangID = null;
                        }
                        else
                        {
                            taiKhoan.KhachHangID = item.Value.KhachHangID;
                            taiKhoan.NhanVienID = null;
                        }
                        taiKhoan.TinhTrang = "Đã khóa";
                        // Chuyển đối tượng taiKhoan thành JSON
                        var jsonContent = JsonConvert.SerializeObject(taiKhoan);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        // URL của tài khoản trong Firebase
                        var url = $"{_firebaseUrl}/TaiKhoans/{item.Key}.json?auth={_firebaseSecret}";

                        // Gửi yêu cầu PUT tới Firebase để cập nhật tài khoản
                        var response = await _httpClient.PutAsync(url, content);

                        // Nếu cập nhật thành công, trả về true
                        return response.IsSuccessStatusCode;
                    }
                }

                // Nếu không tìm thấy tài khoản với TenDangNhap khớp
                return false;
            }
            catch (Exception ex)
            {
                // In lỗi nếu có và trả về false
                Console.WriteLine($"Lỗi khi cập nhật tài khoản: {ex.Message}");
                return false;
            }
        }

        public async Task<bool> MoKhoaTaiKhoanAsync(TaiKhoan taiKhoan)
        {
            try
            {
                // Lấy tất cả tài khoản từ Firebase
                var taiKhoanData = await GetAllTaiKhoanAsync2(); // Giả sử GetAllTaiKhoanAsync trả về Dictionary<string, TaiKhoan>

                // Duyệt qua các tài khoản trong Dictionary để tìm tài khoản có TenDangNhap khớp
                foreach (var item in taiKhoanData)
                {
                    if (item.Value.TenDangNhap.Equals(taiKhoan.TenDangNhap))
                    {
                        taiKhoan.Anh = item.Value.Anh;
                        taiKhoan.TenDangNhap = item.Value.TenDangNhap;
                        taiKhoan.MatKhau = item.Value.MatKhau;
                        if (item.Value.KhachHangID is null)
                        {
                            taiKhoan.NhanVienID = item.Value.NhanVienID;
                            taiKhoan.KhachHangID = null;
                        }
                        else
                        {
                            taiKhoan.KhachHangID = item.Value.KhachHangID;
                            taiKhoan.NhanVienID = null;
                        }
                        taiKhoan.TinhTrang = "Đang hoạt động";
                        // Chuyển đối tượng taiKhoan thành JSON
                        var jsonContent = JsonConvert.SerializeObject(taiKhoan);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        // URL của tài khoản trong Firebase
                        var url = $"{_firebaseUrl}/TaiKhoans/{item.Key}.json?auth={_firebaseSecret}";

                        // Gửi yêu cầu PUT tới Firebase để cập nhật tài khoản
                        var response = await _httpClient.PutAsync(url, content);

                        // Nếu cập nhật thành công, trả về true
                        return response.IsSuccessStatusCode;
                    }
                }

                // Nếu không tìm thấy tài khoản với TenDangNhap khớp
                return false;
            }
            catch (Exception ex)
            {
                // In lỗi nếu có và trả về false
                Console.WriteLine($"Lỗi khi cập nhật tài khoản: {ex.Message}");
                return false;
            }
        }

        // Lấy toàn bộ tài khoản dưới dạng Dictionary
        public async Task<Dictionary<string, TaiKhoan>> GetAllTaiKhoanAsync2()
        {
            var url = $"{_firebaseUrl}/TaiKhoans.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, TaiKhoan> (key là tên đăng nhập)
                var taiKhoanDict = JsonConvert.DeserializeObject<Dictionary<string, TaiKhoan>>(jsonResponse);

                return taiKhoanDict; 
            }

            return new Dictionary<string, TaiKhoan>(); // Nếu không có dữ liệu, trả về Dictionary rỗng
        }
    }
}
