using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using WEBBANSACH.Models;

namespace WEBBANSACH.Repositories
{
    public class TaiKhoanRepository
    {
        private string _connectionString = ConfigurationManager.ConnectionStrings["DBPHANMEMBANSACHEntities"].ConnectionString;

        // Hàm lấy danh sách các admin từ cơ sở dữ liệu
        public List<TaiKhoan> GetAdmin()
        {
            List<TaiKhoan> adminList = new List<TaiKhoan>();

            try
            {
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    string query = "SELECT TenDangNhap, MatKhau FROM TaiKhoans WHERE KhachHangID IS NULL";
                    SqlCommand cmd = new SqlCommand(query, conn);
                    conn.Open();

                    SqlDataReader reader = cmd.ExecuteReader();
                    while (reader.Read())
                    {
                        TaiKhoan tk = new TaiKhoan
                        {
                            TenDangNhap = reader.GetString(0),
                            MatKhau = reader.GetString(1)

                        };
                        adminList.Add(tk);
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            return adminList;
        }
    }
}