using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using WEBBANSACH.Models;

namespace WEBBANSACH.Repositories
{
    public class NhaXuatBanRepository
    {
        private string _connectionString = ConfigurationManager.ConnectionStrings["DBPHANMEMBANSACHEntities"].ConnectionString;
        public List<NhaXuatBan> GetDanhSachNhaXuatBan()
        {
            List<NhaXuatBan> nhaXuatBanList = new List<NhaXuatBan>();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "SELECT NhaXuatBanID, Ten, DiaChi, DienThoai FROM NhaXuatBans";
                SqlCommand cmd = new SqlCommand(query, conn);
                conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    NhaXuatBan nhaXuatBan = new NhaXuatBan
                    {
                        NhaXuatBanID = reader.GetInt32(0),
                        Ten = reader.GetString(1),
                        DiaChi = reader.GetString(2),
                        DienThoai = reader.GetString(3)
                    };
                    nhaXuatBanList.Add(nhaXuatBan);
                }
            }

            return nhaXuatBanList;
        }
        public NhaXuatBan GetNhaXuatBanByID(int ID)
        {
            NhaXuatBan nhaXuatBan = null; // Khởi tạo với giá trị null để xử lý khi không có kết quả.

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                // Sử dụng câu truy vấn với tham số để tránh SQL Injection
                string query = "SELECT NhaXuatBanID, Ten, DiaChi, DienThoai FROM NhaXuatBans WHERE NhaXuatBanID = @NhaXuatBanID";

                using (SqlCommand cmd = new SqlCommand(query, conn))
                {
                    // Thêm tham số vào câu truy vấn
                    cmd.Parameters.AddWithValue("@NhaXuatBanID", ID);

                    conn.Open();

                    // Sử dụng SqlDataReader để đọc dữ liệu
                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read()) // Kiểm tra nếu có dữ liệu
                        {
                            nhaXuatBan = new NhaXuatBan
                            {
                                NhaXuatBanID = reader.GetInt32(0),
                                Ten = reader.GetString(1),
                                DiaChi = reader.GetString(2),
                                DienThoai = reader.GetString(3)
                            };
                        }
                    }
                }
            }
            return nhaXuatBan; // Nếu không tìm thấy nhà xuất bản, sẽ trả về null
        }

        public bool AddNhaXuatBan(NhaXuatBan nhaXuatBan)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "INSERT INTO NhaXuatBans (Ten, DiaChi, DienThoai) " +
                               "VALUES (@Ten, @DiaChi, @DienThoai)";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@Ten", nhaXuatBan.Ten);
                cmd.Parameters.AddWithValue("@DiaChi", nhaXuatBan.DiaChi);
                cmd.Parameters.AddWithValue("@DienThoai", nhaXuatBan.DienThoai);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool UpdateNhaXuatBan(NhaXuatBan nhaXuatBan)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "UPDATE NhaXuatBans SET Ten = @Ten, DiaChi = @DiaChi, DienThoai = @DienThoai " +
                               "WHERE NhaXuatBanID = @NhaXuatBanID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@NhaXuatBanID", nhaXuatBan.NhaXuatBanID);
                cmd.Parameters.AddWithValue("@Ten", nhaXuatBan.Ten);
                cmd.Parameters.AddWithValue("@DiaChi", nhaXuatBan.DiaChi);
                cmd.Parameters.AddWithValue("@DienThoai", nhaXuatBan.DienThoai);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool DeleteNhaXuatBan(int nhaXuatBanID)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "DELETE FROM NhaXuatBans WHERE NhaXuatBanID = @NhaXuatBanID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@NhaXuatBanID", nhaXuatBanID);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }
    }
}