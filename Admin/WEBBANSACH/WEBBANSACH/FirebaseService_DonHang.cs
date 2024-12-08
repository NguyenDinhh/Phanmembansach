using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using WEBBANSACH.Models;

namespace WEBBANSACH
{
    public class FirebaseService_DonHang
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_DonHang()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả đơn hàng từ Firebase
        public async Task<List<DonHang>> GetAllDonHangAsync()
        {
            var url = $"{_firebaseUrl}/DonHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();
                try
                {
                    var donHangList = JsonConvert.DeserializeObject<List<DonHang>>(jsonResponse);
                    if (donHangList != null)
                    {
                        return donHangList; // Nếu trả về dạng mảng, trả về List<DonHang>
                    }
                }
                catch (JsonException)
                {
                    var donHangDict = JsonConvert.DeserializeObject<Dictionary<string, DonHang>>(jsonResponse);
                    if (donHangDict != null)
                    {
                        return new List<DonHang>(donHangDict.Values); // Nếu là Dictionary, trả về List từ các giá trị
                    }
                }
            }

            return new List<DonHang>(); // Trả về danh sách trống nếu có lỗi hoặc không có dữ liệu
        }

        // Lấy đơn hàng theo ID
        public async Task<DonHang> GetDonHangAsync(int donHangID)
        {
            try
            {
                var donHangList = await GetAllDonHangAsync();
                foreach (var item in donHangList)
                {
                    if (item.DonHangID == donHangID)
                    {
                        return item; // Trả về đơn hàng nếu tìm thấy
                    }
                }

                Console.WriteLine($"Không tìm thấy đơn hàng với ID: {donHangID}");
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null;
            }
        }

        // Lấy tất cả đơn hàng dưới dạng Dictionary
        public async Task<Dictionary<string, DonHang>> GetAllDonHangAsync2()
        {
            var url = $"{_firebaseUrl}/DonHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, DonHang> (key là ID Firebase)
                var donHangDict = JsonConvert.DeserializeObject<Dictionary<string, DonHang>>(jsonResponse);

                return donHangDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, DonHang>(); // Nếu không có dữ liệu, trả về Dictionary rỗng
        }
        public async Task<bool> DonHangAsync(DonHang donHang)
        {
            try
            {
                // Lấy toàn bộ danh sách đơn hàng từ Firebase
                var donHangData = await GetAllDonHangAsync2(); // Lấy danh sách đơn hàng từ Firebase dưới dạng Dictionary

                foreach (var item in donHangData)
                {
                    if (item.Value.DonHangID == donHang.DonHangID)
                    {
                        // Nếu tìm thấy đơn hàng có ID khớp, ta sẽ cập nhật nó
                        var jsonContent = JsonConvert.SerializeObject(donHang); // Chuyển đổi đối tượng DonHang thành JSON
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        // URL của Firebase để cập nhật đối tượng theo key
                        var url = $"{_firebaseUrl}/DonHangs/{item.Key}.json?auth={_firebaseSecret}";

                        // Thực hiện yêu cầu PUT để cập nhật đơn hàng
                        var response = await _httpClient.PutAsync(url, content);

                        // Nếu yêu cầu PUT thành công, trả về true
                        return response.IsSuccessStatusCode;
                    }
                }

                // Nếu không tìm thấy đơn hàng, trả về false
                return false;
            }
            catch (Exception ex)
            {
                // Nếu có lỗi, in lỗi ra console và trả về false
                Console.WriteLine($"Lỗi khi cập nhật đơn hàng: {ex.Message}");
                return false;
            }
        }

    }
}
