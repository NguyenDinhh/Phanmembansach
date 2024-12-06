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
    public class FirebaseService_NhaXuatBan
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_NhaXuatBan()
        {
            _httpClient = new HttpClient();
        }

        // Firebase có thể trả về dữ liệu dưới dạng một mảng JSON hoặc một đối tượng JSON với các khóa là ID của các nhà xuất bản
        public async Task<List<NhaXuatBan>> GetAllNhaXuatBanAsync()
        {
            // Sửa URL thành NhaXuatBans
            var url = $"{_firebaseUrl}/NhaXuatBans.json?auth={_firebaseSecret}";

            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Kiểm tra xem dữ liệu trả về là mảng hay đối tượng
                try
                {
                    // Nếu trả về dạng mảng JSON, deserializ thành List
                    var nhaXuatBanList = JsonConvert.DeserializeObject<List<NhaXuatBan>>(jsonResponse);
                    if (nhaXuatBanList != null)
                    {
                        return nhaXuatBanList; // Nếu là mảng, trả về List<NhaXuatBan>
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng, thử deserializ thành Dictionary
                    var nhaXuatBanDict = JsonConvert.DeserializeObject<Dictionary<string, NhaXuatBan>>(jsonResponse);
                    if (nhaXuatBanDict != null)
                    {
                        return new List<NhaXuatBan>(nhaXuatBanDict.Values); // Nếu là Dictionary, trả về List từ các giá trị
                    }
                }
            }

            // Trả về danh sách trống nếu có lỗi hoặc không có dữ liệu
            return new List<NhaXuatBan>();
        }
        public async Task<NhaXuatBan> GetNhaXuatBanAsync(int nhaXuatBanId)
        {
            try
            {
                // Gọi hàm GetAllNhaXuatBanAsync và await để lấy danh sách các nhà xuất bản
                var nhaXuatBanList = await GetAllNhaXuatBanAsync();

                // Duyệt qua các nhà xuất bản trong danh sách để tìm nhà xuất bản có ID phù hợp
                foreach (var item in nhaXuatBanList)
                {
                    if (item.NhaXuatBanID == nhaXuatBanId)
                    {
                        // Trả về nhà xuất bản nếu tìm thấy
                        return item;
                    }
                }

                // Nếu không tìm thấy nhà xuất bản nào với ID này, trả về null
                Console.WriteLine($"Không tìm thấy nhà xuất bản với ID: {nhaXuatBanId}");
                return null;
            }
            catch (Exception ex)
            {
                // Bắt tất cả các ngoại lệ khác và ghi lại lỗi
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null;
            }
        }

        // Thêm NhaXuatBan vào Firebase Realtime Database
        public async Task<bool> AddNhaXuatBanAsync(string key, NhaXuatBan nhaXuatBan)
        {
            var json = JsonConvert.SerializeObject(nhaXuatBan);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var url = $"{_firebaseUrl}/NhaXuatBans/{key}.json?auth={_firebaseSecret}";

            var response = await _httpClient.PutAsync(url, content);

            return response.IsSuccessStatusCode;
        }

        public async Task<Dictionary<string, NhaXuatBan>> GetAllNhaXuatBanAsync2()
        {
            var url = $"{_firebaseUrl}/NhaXuatBans.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, NhaXuatBan> (key là ID Firebase)
                var nhaXuatBanDict = JsonConvert.DeserializeObject<Dictionary<string, NhaXuatBan>>(jsonResponse);

                return nhaXuatBanDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, NhaXuatBan>(); // Nếu không có dữ liệu, trả về Dictionary rỗng
        }

        // Hàm cập nhật nhà xuất bản
        public async Task<bool> UpdateNhaXuatBanAsync(NhaXuatBan nhaXuatBan)
        {
            try
            {
                // Lấy toàn bộ danh sách nhà xuất bản từ Firebase (có thể trả về List hoặc Dictionary)
                var nhaXuatBanData2 = await GetAllNhaXuatBanAsync2();
                    foreach (var item in nhaXuatBanData2)
                    {
                        if (item.Value.NhaXuatBanID == nhaXuatBan.NhaXuatBanID)
                        {
                            // Nếu tìm thấy nhà xuất bản có ID khớp, ta sẽ cập nhật nó
                            var jsonContent = JsonConvert.SerializeObject(nhaXuatBan);
                            var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                            // URL của Firebase để cập nhật đối tượng theo key
                            var url = $"{_firebaseUrl}/NhaXuatBans/{ item.Key}.json?auth={_firebaseSecret}";

                            // Thực hiện yêu cầu PUT để cập nhật nhà xuất bản
                            var response = await _httpClient.PutAsync(url, content);

                            // Nếu yêu cầu PUT thành công, trả về true
                            return response.IsSuccessStatusCode;
                        }
                    }
                return false;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public async Task<bool> XoaNhaXuatBanAsync(int nhaXuatBanID)
        {
            try
            {
                // Lấy toàn bộ danh sách nhà xuất bản từ Firebase (dạng Dictionary<string, NhaXuatBan>)
                var nhaXuatBanData2 = await GetAllNhaXuatBanAsync2();

                foreach (var item in nhaXuatBanData2)
                {
                    // Kiểm tra nếu NhaXuatBanID trùng khớp
                    if (item.Value.NhaXuatBanID == nhaXuatBanID)
                    {
                        // Lấy key từ Firebase, đây chính là phần mà bạn cần để xóa
                        var firebaseKey = item.Key;

                        // Tạo URL Firebase cho yêu cầu xóa
                        var url = $"{_firebaseUrl}/NhaXuatBans/{firebaseKey}.json?auth={_firebaseSecret}";

                        // Thực hiện yêu cầu DELETE để xóa
                        var response = await _httpClient.DeleteAsync(url);

                        // Nếu xóa thành công, trả về true
                        return response.IsSuccessStatusCode;
                    }
                }

                // Nếu không tìm thấy nhà xuất bản với NhaXuatBanID khớp, trả về false
                return false;
            }
            catch (Exception ex)
            {
                // Nếu có lỗi, trả về false
                Console.WriteLine($"Lỗi khi xóa nhà xuất bản: {ex.Message}");
                return false;
            }
        }


    }
}
