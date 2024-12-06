using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using WEBBANSACH.Models;

namespace WEBBANSACH.Repositories
{
    public class SachRepository
    {
        private string _connectionString = ConfigurationManager.ConnectionStrings["DBPHANMEMBANSACHEntities"].ConnectionString;
        public List<Sach> GetDanhSachSach()
        {
            List<Sach> sachList = new List<Sach>();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "SELECT SachID, Ten, TacGiaID, NhaXuatBanID, TheLoaiID, Sao, Sale, Gia,DiemThuong, DaBan, SoLuong, NgayXuatBan, NgayKhoiBan, MoTa, Anh FROM Sachs";
                SqlCommand cmd = new SqlCommand(query, conn);
                conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Sach sach = new Sach
                    {
                        SachID = reader.GetInt32(0),
                        Ten = reader.GetString(1),
                        TacGiaID = reader.GetInt32(2),
                        NhaXuatBanID = reader.GetInt32(3),
                        TheLoaiID = reader.GetInt32(4),
                        Sao = reader.GetDecimal(5),
                        Sale = reader.GetDecimal(6),
                        Gia = reader.GetDecimal(7),
                        DiemThuong = reader.GetInt32(8),
                        DaBan = reader.GetInt32(9),
                        SoLuong = reader.GetInt32(10),
                        NgayXuatBan = reader.GetDateTime(11),
                        NgayKhoiBan = reader.GetDateTime(12),
                        MoTa = reader.GetString(13),
                        Anh = reader.GetString(14)
                    };
                    sachList.Add(sach);
                }
            }

            return sachList;
        }
        public Sach GetSachByID(int ID)
        {
            Sach sach = null; // Khởi tạo với giá trị null để xử lý khi không có kết quả.

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                // Sử dụng câu truy vấn với tham số để tránh SQL Injection
                string query = "SELECT SachID, Ten, TacGiaID, NhaXuatBanID, TheLoaiID, Sao, Sale, Gia, DiemThuong, DaBan, SoLuong, NgayXuatBan, NgayKhoiBan, MoTa, Anh " +
                               "FROM Sachs WHERE SachID = @SachID";

                using (SqlCommand cmd = new SqlCommand(query, conn))
                {
                    // Thêm tham số vào câu truy vấn
                    cmd.Parameters.AddWithValue("@SachID", ID);

                    conn.Open();

                    // Sử dụng SqlDataReader để đọc dữ liệu
                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read()) // Kiểm tra nếu có dữ liệu
                        {
                            sach = new Sach
                            {
                                SachID = reader.GetInt32(0),
                                Ten = reader.GetString(1),
                                TacGiaID = reader.GetInt32(2),
                                NhaXuatBanID = reader.GetInt32(3),
                                TheLoaiID = reader.GetInt32(4),
                                Sao = reader.GetDecimal(5),              
                                Sale = reader.GetDecimal(6),           
                                Gia = reader.GetDecimal(7),
                                DiemThuong = reader.GetInt32(8),
                                DaBan = reader.GetInt32(9),
                                SoLuong = reader.GetInt32(10),
                                NgayXuatBan = reader.GetDateTime(11),
                                NgayKhoiBan = reader.GetDateTime(12),
                                MoTa = reader.GetString(13),
                                Anh = reader.GetString(14)
                            };
                        }
                    }
                }
            }
            return sach; // Nếu không tìm thấy sách, sẽ trả về null
        }

        public bool AddSach(Sach sach)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "INSERT INTO Sachs (Ten, TacGiaID, NhaXuatBanID, TheLoaiID, Sao, Sale, Gia, " +
                               "DiemThuong, DaBan, SoLuong, NgayXuatBan, NgayKhoiBan, MoTa, Anh) " +
                               "VALUES (@Ten, @TacGiaID, @NhaXuatBanID, @TheLoaiID, @Sao, @Sale, @Gia, " +
                               "@DiemThuong, @DaBan, @SoLuong, @NgayXuatBan, @NgayKhoiBan, @MoTa, @Anh)";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@Ten", sach.Ten);
                cmd.Parameters.AddWithValue("@TacGiaID", sach.TacGiaID);
                cmd.Parameters.AddWithValue("@NhaXuatBanID", sach.NhaXuatBanID);
                cmd.Parameters.AddWithValue("@TheLoaiID", sach.TheLoaiID);
                cmd.Parameters.AddWithValue("@Sao", sach.Sao);
                cmd.Parameters.AddWithValue("@Sale", sach.Sale);
                cmd.Parameters.AddWithValue("@Gia", sach.Gia);
                cmd.Parameters.AddWithValue("@DiemThuong", sach.DiemThuong);
                cmd.Parameters.AddWithValue("@DaBan", sach.DaBan);
                cmd.Parameters.AddWithValue("@SoLuong", sach.SoLuong);
                cmd.Parameters.AddWithValue("@NgayXuatBan", sach.NgayXuatBan);
                cmd.Parameters.AddWithValue("@NgayKhoiBan", sach.NgayKhoiBan);
                cmd.Parameters.AddWithValue("@MoTa", sach.MoTa);
                cmd.Parameters.AddWithValue("@Anh", sach.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool UpdateSach(Sach sach)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "UPDATE Sachs SET Ten = @Ten, TacGiaID = @TacGiaID, NhaXuatBanID = @NhaXuatBanID, " +
                               "TheLoaiID = @TheLoaiID, Sao = @Sao, Sale = @Sale, Gia = @Gia, " +
                               "DiemThuong = @DiemThuong, DaBan = @DaBan, SoLuong = @SoLuong, " +
                               "NgayXuatBan = @NgayXuatBan, NgayKhoiBan = @NgayKhoiBan, MoTa = @MoTa, " +
                               "Anh = @Anh WHERE SachID = @SachID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@SachID", sach.SachID);
                cmd.Parameters.AddWithValue("@Ten", sach.Ten);
                cmd.Parameters.AddWithValue("@TacGiaID", sach.TacGiaID);
                cmd.Parameters.AddWithValue("@NhaXuatBanID", sach.NhaXuatBanID);
                cmd.Parameters.AddWithValue("@TheLoaiID", sach.TheLoaiID);
                cmd.Parameters.AddWithValue("@Sao", sach.Sao);
                cmd.Parameters.AddWithValue("@Sale", sach.Sale);
                cmd.Parameters.AddWithValue("@Gia", sach.Gia);
                cmd.Parameters.AddWithValue("@DiemThuong", sach.DiemThuong);
                cmd.Parameters.AddWithValue("@DaBan", sach.DaBan);
                cmd.Parameters.AddWithValue("@SoLuong", sach.SoLuong);
                cmd.Parameters.AddWithValue("@NgayXuatBan", sach.NgayXuatBan);
                cmd.Parameters.AddWithValue("@NgayKhoiBan", sach.NgayKhoiBan);
                cmd.Parameters.AddWithValue("@MoTa", sach.MoTa);
                cmd.Parameters.AddWithValue("@Anh", sach.Anh);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }

        public bool DeleteSach(int sachID)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                string query = "DELETE FROM Sachs WHERE SachID = @SachID";

                SqlCommand cmd = new SqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@SachID", sachID);

                conn.Open();
                int rowsAffected = cmd.ExecuteNonQuery();

                return rowsAffected > 0;
            }
        }
        }
}