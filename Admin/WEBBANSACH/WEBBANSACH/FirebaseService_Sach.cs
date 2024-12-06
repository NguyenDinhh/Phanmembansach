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
    public class FirebaseService_Sach
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_Sach()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả sách từ Firebase
        public async Task<List<Sach>> GetAllSachAsync()
        {
            var url = $"{_firebaseUrl}/Sachs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Kiểm tra xem dữ liệu trả về là mảng hay đối tượng
                try
                {
                    // Nếu trả về dạng mảng JSON, deserialize thành List
                    var sachList = JsonConvert.DeserializeObject<List<Sach>>(jsonResponse);
                    if (sachList != null)
                    {
                        return sachList; // Nếu là mảng, trả về List<Sach>
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng, thử deserializ thành Dictionary
                    var sachDict = JsonConvert.DeserializeObject<Dictionary<string, Sach>>(jsonResponse);
                    if (sachDict != null)
                    {
                        return new List<Sach>(sachDict.Values); // Nếu là Dictionary, trả về List từ các giá trị
                    }
                }
            }

            return new List<Sach>(); // Nếu không có dữ liệu hoặc có lỗi
        }

        // Lấy một sách theo ID từ Firebase
        public async Task<Sach> GetSachAsync(int sachID)
        {
            try
            {
                var sachList = await GetAllSachAsync();

                // Duyệt qua các sách để tìm sách có ID khớp
                foreach (var item in sachList)
                {
                    if (item.SachID == sachID)
                    {
                        return item; // Trả về sách nếu tìm thấy
                    }
                }

                Console.WriteLine($"Không tìm thấy sách với ID: {sachID}");
                return null; // Nếu không tìm thấy sách nào
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null; // Xử lý lỗi
            }
        }

        // Thêm một sách vào Firebase
        public async Task<bool> AddSachAsync(string key, Sach sach)
        {
            var json = JsonConvert.SerializeObject(sach);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var url = $"{_firebaseUrl}/Sachs/{key}.json?auth={_firebaseSecret}";

            var response = await _httpClient.PutAsync(url, content);

            return response.IsSuccessStatusCode;
        }

        // Lấy tất cả sách dưới dạng Dictionary từ Firebase
        public async Task<Dictionary<string, Sach>> GetAllSachAsync2()
        {
            var url = $"{_firebaseUrl}/Sachs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, Sach> (key là ID Firebase)
                var sachDict = JsonConvert.DeserializeObject<Dictionary<string, Sach>>(jsonResponse);

                return sachDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, Sach>(); // Nếu không có dữ liệu
        }

        // Cập nhật sách vào Firebase
        public async Task<bool> UpdateSachAsync(Sach sach)
        {
            try
            {
                var sachData = await GetAllSachAsync2();

                // Duyệt qua các sách trong Dictionary để tìm sách có ID khớp
                foreach (var item in sachData)
                {
                    if (item.Value.SachID == sach.SachID)
                    {
                        var jsonContent = JsonConvert.SerializeObject(sach);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        var url = $"{_firebaseUrl}/Sachs/{item.Key}.json?auth={_firebaseSecret}";

                        var response = await _httpClient.PutAsync(url, content);

                        return response.IsSuccessStatusCode; // Nếu cập nhật thành công
                    }
                }

                return false; // Nếu không tìm thấy sách với ID khớp
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi cập nhật sách: {ex.Message}");
                return false; // Nếu có lỗi
            }
        }

        // Xóa sách khỏi Firebase
        public async Task<bool> DeleteSachAsync(int sachID)
        {
            try
            {
                var sachData = await GetAllSachAsync2();

                // Duyệt qua các sách trong Dictionary để tìm sách có ID khớp
                foreach (var item in sachData)
                {
                    if (item.Value.SachID == sachID)
                    {
                        var firebaseKey = item.Key; // Lấy key của sách

                        var url = $"{_firebaseUrl}/Sachs/{firebaseKey}.json?auth={_firebaseSecret}";

                        var response = await _httpClient.DeleteAsync(url);

                        return response.IsSuccessStatusCode; // Nếu xóa thành công
                    }
                }

                return false; // Nếu không tìm thấy sách với ID khớp
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi xóa sách: {ex.Message}");
                return false; // Nếu có lỗi
            }
        }
    }
}
