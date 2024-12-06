using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using WEBBANSACH.Models;

namespace WEBBANSACH.Repositories
{
    public class TacGiaRepository
    {
        private string _connectionString = ConfigurationManager.ConnectionStrings["DBPHANMEMBANSACHEntities"].ConnectionString;

        public List<TacGia> GetDanhSachTacGia()
        {
            List<TacGia> tacGiaList = new List<TacGia>();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "SELECT TacGiaID, Ten, CongViec, MoTa, Anh FROM TacGias";
                SqlCommand cmd = new SqlCommand(query, conn);
                conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    TacGia tacGia = new TacGia
                    {
                        TacGiaID = reader.GetInt32(0),
                        Ten = reader.GetString(1),
                        CongViec = reader.GetString(2),
                        MoTa = reader.GetString(3),
                        Anh = reader.GetString(4)
                    };
                    tacGiaList.Add(tacGia);
                }
            }

            return tacGiaList;
        }
        public TacGia GetTacGiaByID(int ID)
        {
            TacGia tacGia = null; // Khởi tạo với giá trị null để xử lý khi không có kết quả.

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                // Sử dụng câu truy vấn với tham số để tránh SQL Injection
                string query = "SELECT TacGiaID, Ten, CongViec, MoTa, Anh FROM TacGias WHERE TacGiaID = @TacGiaID";

                using (SqlCommand cmd = new SqlCommand(query, conn))
                {
                    // Thêm tham số vào câu truy vấn
                    cmd.Parameters.AddWithValue("@TacGiaID", ID);

                    conn.Open();

                    // Sử dụng SqlDataReader để đọc dữ liệu
                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read()) // Kiểm tra nếu có dữ liệu
                        {
                            tacGia = new TacGia
                            {
                                TacGiaID = reader.GetInt32(0),
                                Ten = reader.GetString(1),
                                CongViec = reader.GetString(2),
                                MoTa = reader.GetString(3),
                                Anh = reader.GetString(4)
                            };
                        }
                    }
                }
            }

            return tacGia; // Nếu không tìm thấy tác giả, sẽ trả về null
        }

        public bool AddTacGia(TacGia tacGia)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "INSERT INTO TacGias (Ten, CongViec, MoTa, Anh) " +
                               "VALUES (@Ten, @CongViec, @MoTa, @Anh)";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@Ten", tacGia.Ten);
                cmd.Parameters.AddWithValue("@CongViec", tacGia.CongViec);
                cmd.Parameters.AddWithValue("@MoTa", tacGia.MoTa);
                cmd.Parameters.AddWithValue("@Anh", tacGia.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool UpdateTacGia(TacGia tacGia)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "UPDATE TacGias SET Ten = @Ten, CongViec = @CongViec, " +
                               "MoTa = @MoTa, Anh = @Anh WHERE TacGiaID = @TacGiaID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@TacGiaID", tacGia.TacGiaID);
                cmd.Parameters.AddWithValue("@Ten", tacGia.Ten);
                cmd.Parameters.AddWithValue("@CongViec", tacGia.CongViec);
                cmd.Parameters.AddWithValue("@MoTa", tacGia.MoTa);
                cmd.Parameters.AddWithValue("@Anh", tacGia.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool DeleteTacGia(int tacGiaID)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "DELETE FROM TacGias WHERE TacGiaID = @TacGiaID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@TacGiaID", tacGiaID);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }
    }
}