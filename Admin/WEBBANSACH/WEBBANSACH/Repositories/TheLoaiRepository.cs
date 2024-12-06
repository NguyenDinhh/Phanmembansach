using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using WEBBANSACH.Models;

namespace WEBBANSACH.Repositories
{
    public class TheLoaiRepository
    {
        private string _connectionString = ConfigurationManager.ConnectionStrings["DBPHANMEMBANSACHEntities"].ConnectionString;

        // Lấy danh sách thể loại
        public List<TheLoai> GetDanhSachTheLoai()
        {
            List<TheLoai> theLoaiList = new List<TheLoai>();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "SELECT TheLoaiID, TenTheLoai, SoLuong, DaBan, MoTa, Anh FROM TheLoais";
                SqlCommand cmd = new SqlCommand(query, conn);
                conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    TheLoai theLoai = new TheLoai
                    {
                        TheLoaiID = reader.GetInt32(0),
                        TenTheLoai = reader.GetString(1),
                        SoLuong = reader.GetInt32(2),
                        DaBan = reader.GetInt32(3),
                        MoTa = reader.GetString(4),
                        Anh = reader.GetString(5)
                    };
                    theLoaiList.Add(theLoai);
                }
            }

            return theLoaiList;
        }
        public TheLoai GetTheLoaiByID(int ID)
        {
            TheLoai theLoai = null; // Khởi tạo với giá trị null để xử lý khi không có kết quả.

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                // Sử dụng câu truy vấn với tham số để tránh SQL Injection
                string query = "SELECT TheLoaiID, TenTheLoai, SoLuong, DaBan, MoTa, Anh FROM TheLoais WHERE TheLoaiID = @TheLoaiID";

                using (SqlCommand cmd = new SqlCommand(query, conn))
                {
                    // Thêm tham số vào câu truy vấn
                    cmd.Parameters.AddWithValue("@TheLoaiID", ID);

                    conn.Open();

                    // Sử dụng SqlDataReader để đọc dữ liệu
                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read()) // Kiểm tra nếu có dữ liệu
                        {
                            theLoai = new TheLoai
                            {
                                TheLoaiID = reader.GetInt32(0),
                                TenTheLoai = reader.GetString(1),
                                SoLuong = reader.GetInt32(2),
                                DaBan = reader.GetInt32(3),
                                MoTa = reader.GetString(4),
                                Anh = reader.GetString(5)
                            };
                        }
                    }
                }
            }
            return theLoai; // Nếu không tìm thấy thể loại, sẽ trả về null
        }

        // Thêm thể loại
        public bool AddTheLoai(TheLoai theLoai)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "INSERT INTO TheLoais (TenTheLoai, SoLuong, DaBan, MoTa, Anh) " +
                               "VALUES (@TenTheLoai, @SoLuong, @DaBan, @MoTa, @Anh)";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@TenTheLoai", theLoai.TenTheLoai);
                cmd.Parameters.AddWithValue("@SoLuong", theLoai.SoLuong);
                cmd.Parameters.AddWithValue("@DaBan", theLoai.DaBan);
                cmd.Parameters.AddWithValue("@MoTa", theLoai.MoTa);
                cmd.Parameters.AddWithValue("@Anh", theLoai.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        // Cập nhật thể loại
        public bool UpdateTheLoai(TheLoai theLoai)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "UPDATE TheLoais SET TenTheLoai = @TenTheLoai, SoLuong = @SoLuong, " +
                               "DaBan = @DaBan, MoTa = @MoTa, Anh = @Anh WHERE TheLoaiID = @TheLoaiID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@TheLoaiID", theLoai.TheLoaiID);
                cmd.Parameters.AddWithValue("@TenTheLoai", theLoai.TenTheLoai);
                cmd.Parameters.AddWithValue("@SoLuong", theLoai.SoLuong);
                cmd.Parameters.AddWithValue("@DaBan", theLoai.DaBan);
                cmd.Parameters.AddWithValue("@MoTa", theLoai.MoTa);
                cmd.Parameters.AddWithValue("@Anh", theLoai.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        // Xóa thể loại
        public bool DeleteTheLoai(int theLoaiID)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "DELETE FROM TheLoais WHERE TheLoaiID = @TheLoaiID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@TheLoaiID", theLoaiID);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }
    }
}