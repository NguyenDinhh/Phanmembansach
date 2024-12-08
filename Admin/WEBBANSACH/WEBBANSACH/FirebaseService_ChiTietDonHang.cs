using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using WEBBANSACH.Models;

namespace WEBBANSACH
{
    public class FirebaseService_ChiTietDonHang
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_ChiTietDonHang()
        {
            _httpClient = new HttpClient();
        }
        // Lấy tất cả chi tiết đơn hàng từ Firebase dưới dạng List
        public async Task<List<ChiTietDonHang>> GetAllChiTietDonHangAsync()
        {
            var url = $"{_firebaseUrl}/ChiTietDonHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();
                try
                {
                    // Deserializing trả về danh sách nếu là mảng JSON
                    var chiTietDonHangList = JsonConvert.DeserializeObject<List<ChiTietDonHang>>(jsonResponse);
                    if (chiTietDonHangList != null)
                    {
                        return chiTietDonHangList; // Trả về List<ChiTietDonHang>
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng JSON, thử deserializing sang Dictionary
                    var chiTietDonHangDict = JsonConvert.DeserializeObject<Dictionary<string, ChiTietDonHang>>(jsonResponse);
                    if (chiTietDonHangDict != null)
                    {
                        // Trả về danh sách từ các giá trị trong Dictionary
                        return new List<ChiTietDonHang>(chiTietDonHangDict.Values);
                    }
                }
            }

            return new List<ChiTietDonHang>(); // Trả về danh sách trống nếu có lỗi hoặc không có dữ liệu
        }

        // Lấy tất cả ChiTietDonHang từ Firebase
        public async Task<Dictionary<string, ChiTietDonHang>> GetAllChiTietDonHangAsync2()
        {
            var url = $"{_firebaseUrl}/ChiTietDonHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, ChiTietDonHang> (key là ID Firebase)
                var chiTietDonHangDict = JsonConvert.DeserializeObject<Dictionary<string, ChiTietDonHang>>(jsonResponse);

                return chiTietDonHangDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, ChiTietDonHang>(); // Nếu không có dữ liệu, trả về Dictionary rỗng
        }

        // Lấy chi tiết đơn hàng theo ID đơn hàng
        public async Task<List<ChiTietDonHang>> GetChiTietDonHangByDonHangIdAsync(int donHangId)
        {
            var chiTietDonHangList = new List<ChiTietDonHang>();
            var chiTietDonHangDict = await GetAllChiTietDonHangAsync2();

            foreach (var item in chiTietDonHangDict)
            {
                if (item.Value.DonHangID == donHangId)
                {
                    chiTietDonHangList.Add(item.Value); // Thêm vào danh sách nếu DonHangID khớp
                }
            }

            return chiTietDonHangList;
        }

       
    }
}
