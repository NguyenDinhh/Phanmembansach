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
    public class FirebaseService_TacGia
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_TacGia()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả tác giả từ Firebase
        public async Task<List<TacGia>> GetAllTacGiaAsync()
        {
            var url = $"{_firebaseUrl}/TacGias.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Kiểm tra xem dữ liệu trả về là mảng hay đối tượng
                try
                {
                    // Nếu trả về dạng mảng JSON, deserialize thành List
                    var tacGiaList = JsonConvert.DeserializeObject<List<TacGia>>(jsonResponse);
                    if (tacGiaList != null)
                    {
                        return tacGiaList; // Nếu là mảng, trả về List<TacGia>
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng, thử deserializ thành Dictionary
                    var tacGiaDict = JsonConvert.DeserializeObject<Dictionary<string, TacGia>>(jsonResponse);
                    if (tacGiaDict != null)
                    {
                        return new List<TacGia>(tacGiaDict.Values); // Nếu là Dictionary, trả về List từ các giá trị
                    }
                }
            }

            return new List<TacGia>(); // Nếu không có dữ liệu hoặc có lỗi
        }

        // Lấy một tác giả theo ID từ Firebase
        public async Task<TacGia> GetTacGiaAsync(int tacGiaID)
        {
            try
            {
                var tacGiaList = await GetAllTacGiaAsync();

                // Duyệt qua các tác giả để tìm tác giả có ID khớp
                foreach (var item in tacGiaList)
                {
                    if (item.TacGiaID == tacGiaID)
                    {
                        return item; // Trả về tác giả nếu tìm thấy
                    }
                }

                Console.WriteLine($"Không tìm thấy tác giả với ID: {tacGiaID}");
                return null; // Nếu không tìm thấy tác giả nào
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null; // Xử lý lỗi
            }
        }

        // Thêm một tác giả vào Firebase
        public async Task<bool> AddTacGiaAsync(string key, TacGia tacGia)
        {
            var json = JsonConvert.SerializeObject(tacGia);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var url = $"{_firebaseUrl}/TacGias/{key}.json?auth={_firebaseSecret}";

            var response = await _httpClient.PutAsync(url, content);

            return response.IsSuccessStatusCode;
        }

        // Lấy tất cả tác giả dưới dạng Dictionary từ Firebase
        public async Task<Dictionary<string, TacGia>> GetAllTacGiaAsync2()
        {
            var url = $"{_firebaseUrl}/TacGias.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, TacGia> (key là ID Firebase)
                var tacGiaDict = JsonConvert.DeserializeObject<Dictionary<string, TacGia>>(jsonResponse);

                return tacGiaDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, TacGia>(); // Nếu không có dữ liệu
        }

        // Cập nhật tác giả vào Firebase
        public async Task<bool> UpdateTacGiaAsync(TacGia tacGia)
        {
            try
            {
                var tacGiaData = await GetAllTacGiaAsync2();

                // Duyệt qua các tác giả trong Dictionary để tìm tác giả có ID khớp
                foreach (var item in tacGiaData)
                {
                    if (item.Value.TacGiaID == tacGia.TacGiaID)
                    {
                        var jsonContent = JsonConvert.SerializeObject(tacGia);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        var url = $"{_firebaseUrl}/TacGias/{item.Key}.json?auth={_firebaseSecret}";

                        var response = await _httpClient.PutAsync(url, content);

                        return response.IsSuccessStatusCode; // Nếu cập nhật thành công
                    }
                }

                return false; // Nếu không tìm thấy tác giả với ID khớp
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi cập nhật tác giả: {ex.Message}");
                return false; // Nếu có lỗi
            }
        }

        // Xóa tác giả khỏi Firebase
        public async Task<bool> XoaTacGiaAsync(int tacGiaID)
        {
            try
            {
                var tacGiaData = await GetAllTacGiaAsync2();

                // Duyệt qua các tác giả trong Dictionary để tìm tác giả có ID khớp
                foreach (var item in tacGiaData)
                {
                    if (item.Value.TacGiaID == tacGiaID)
                    {
                        var firebaseKey = item.Key; // Lấy key của tác giả

                        var url = $"{_firebaseUrl}/TacGias/{firebaseKey}.json?auth={_firebaseSecret}";

                        var response = await _httpClient.DeleteAsync(url);

                        return response.IsSuccessStatusCode; // Nếu xóa thành công
                    }
                }

                return false; // Nếu không tìm thấy tác giả với ID khớp
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi xóa tác giả: {ex.Message}");
                return false; // Nếu có lỗi
            }
        }
    }
}
