using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using WEBBANSACH.Models;
using System.Collections.Generic;

namespace WEBBANSACH
{
    public class FirebaseService_TheLoai
    {
        private readonly string _firebaseUrl = "https://phanmembansach-fd6ed-default-rtdb.firebaseio.com/"; // Firebase URL của bạn
        private readonly string _firebaseSecret = "ANiCVm6k2gCgtAiYm5lV48HhwYxeyulQv6EUfP94"; // Firebase secret của bạn

        private readonly HttpClient _httpClient;

        public FirebaseService_TheLoai()
        {
            _httpClient = new HttpClient();
        }

        // Lấy tất cả các thể loại từ Firebase
        public async Task<List<TheLoai>> GetAllTheLoaiAsync()
        {
            var url = $"{_firebaseUrl}/TheLoais.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Kiểm tra xem dữ liệu trả về là mảng hay đối tượng
                try
                {
                    // Nếu trả về dạng mảng JSON, deserialize thành List
                    var theLoaiList = JsonConvert.DeserializeObject<List<TheLoai>>(jsonResponse);
                    if (theLoaiList != null)
                    {
                        return theLoaiList;
                    }
                }
                catch (JsonException)
                {
                    // Nếu không phải mảng, thử deserializ thành Dictionary
                    var theLoaiDict = JsonConvert.DeserializeObject<Dictionary<string, TheLoai>>(jsonResponse);
                    if (theLoaiDict != null)
                    {
                        return new List<TheLoai>(theLoaiDict.Values); // Trả về List từ các giá trị
                    }
                }
            }

            return new List<TheLoai>(); // Nếu có lỗi hoặc không có dữ liệu
        }

        // Lấy thể loại theo ID
        public async Task<TheLoai> GetTheLoaiAsync(int theLoaiId)
        {
            try
            {
                var theLoaiList = await GetAllTheLoaiAsync();

                foreach (var item in theLoaiList)
                {
                    if (item.TheLoaiID == theLoaiId)
                    {
                        return item; // Trả về thể loại nếu tìm thấy
                    }
                }

                Console.WriteLine($"Không tìm thấy thể loại với ID: {theLoaiId}");
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi lấy dữ liệu từ Firebase: {ex.Message}");
                return null;
            }
        }

        // Thêm thể loại vào Firebase
        public async Task<bool> AddTheLoaiAsync(string key, TheLoai theLoai)
        {
            var json = JsonConvert.SerializeObject(theLoai);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var url = $"{_firebaseUrl}/TheLoais/{key}.json?auth={_firebaseSecret}";

            var response = await _httpClient.PutAsync(url, content);

            return response.IsSuccessStatusCode;
        }

        // Lấy tất cả thể loại dưới dạng Dictionary
        public async Task<Dictionary<string, TheLoai>> GetAllTheLoaiAsync2()
        {
            var url = $"{_firebaseUrl}/TheLoais.json?auth={_firebaseSecret}";
            var response = await _httpClient.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                var theLoaiDict = JsonConvert.DeserializeObject<Dictionary<string, TheLoai>>(jsonResponse);
                return theLoaiDict;
            }

            return new Dictionary<string, TheLoai>();
        }

        // Cập nhật thể loại trong Firebase
        public async Task<bool> UpdateTheLoaiAsync(TheLoai theLoai)
        {
            try
            {
                var theLoaiData = await GetAllTheLoaiAsync2();
                foreach (var item in theLoaiData)
                {
                    if (item.Value.TheLoaiID == theLoai.TheLoaiID)
                    {
                        var jsonContent = JsonConvert.SerializeObject(theLoai);
                        var content = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                        var url = $"{_firebaseUrl}/TheLoais/{item.Key}.json?auth={_firebaseSecret}";
                        var response = await _httpClient.PutAsync(url, content);

                        return response.IsSuccessStatusCode;
                    }
                }
                return false;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi cập nhật thể loại: {ex.Message}");
                return false;
            }
        }

        // Xóa thể loại từ Firebase
        public async Task<bool> XoaTheLoaiAsync(int theLoaiID)
        {
            try
            {
                var theLoaiData = await GetAllTheLoaiAsync2();

                foreach (var item in theLoaiData)
                {
                    if (item.Value.TheLoaiID == theLoaiID)
                    {
                        var firebaseKey = item.Key;
                        var url = $"{_firebaseUrl}/TheLoais/{firebaseKey}.json?auth={_firebaseSecret}";

                        var response = await _httpClient.DeleteAsync(url);

                        return response.IsSuccessStatusCode;
                    }
                }
                return false;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Lỗi khi xóa thể loại: {ex.Message}");
                return false;
            }
        }
    }
}
