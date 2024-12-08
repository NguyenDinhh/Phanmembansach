using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using WEBBANSACH.Models;
using System.Collections.Generic;

namespace WEBBANSACH
{
    public class FirebaseService_DiaChiNhanHang
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_DiaChiNhanHang()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả địa chỉ nhận hàng từ Firebase
        public async Task<List<DiaChiNhanHang>> GetAllDiaChiNhanHangAsync()
        {
            var url = $"{_firebaseUrl}/DiaChiNhanHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();
                try
                {
                    var diaChiNhanHangList = JsonConvert.DeserializeObject<List<DiaChiNhanHang>>(jsonResponse);
                    if (diaChiNhanHangList != null)
                    {
                        return diaChiNhanHangList; // Nếu trả về dạng mảng, trả về List<DiaChiNhanHang>
                    }
                }
                catch (JsonException)
                {
                    var diaChiNhanHangDict = JsonConvert.DeserializeObject<Dictionary<string, DiaChiNhanHang>>(jsonResponse);
                    if (diaChiNhanHangDict != null)
                    {
                        return new List<DiaChiNhanHang>(diaChiNhanHangDict.Values); // Nếu là Dictionary, trả về List từ các giá trị
                    }
                }
            }

            return new List<DiaChiNhanHang>(); // Trả về danh sách trống nếu có lỗi hoặc không có dữ liệu
        }

        // Lấy địa chỉ nhận hàng theo ID
        public async Task<DiaChiNhanHang> GetDiaChiNhanHangAsync(int diaChiNhanHangID)
        {
            try
            {
                var diaChiNhanHangList = await GetAllDiaChiNhanHangAsync();
                foreach (var item in diaChiNhanHangList)
                {
                    if (item.DiaChiNhanHangID == diaChiNhanHangID)
                    {
                        return item; // Trả về địa chỉ nhận hàng nếu tìm thấy
                    }
                }

                Console.WriteLine($"Không tìm thấy địa chỉ nhận hàng với ID: {diaChiNhanHangID}");
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null;
            }
        }
        public async Task<Dictionary<string, DiaChiNhanHang>> GetAllDiaChiNhanHangAsync2()
        {
            var url = $"{_firebaseUrl}/DiaChiNhanHangs.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Deserialize thành Dictionary<string, DiaChiNhanHang> (key là ID Firebase)
                var diaChiNhanHangDict = JsonConvert.DeserializeObject<Dictionary<string, DiaChiNhanHang>>(jsonResponse);

                return diaChiNhanHangDict; // Trả về Dictionary với key là ID Firebase
            }

            return new Dictionary<string, DiaChiNhanHang>(); // Nếu không có dữ liệu, trả về Dictionary rỗng
        }



    }
}
