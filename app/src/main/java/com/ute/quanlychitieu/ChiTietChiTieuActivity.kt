
package com.ute.quanlychitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtTenKhoanChi_245: TextView
    private lateinit var txtSoTien_245: TextView
    private lateinit var txtGhiChu_245: TextView
    private lateinit var txtTrangThaiThanhToan_245: TextView

    private lateinit var btnSuaKhoanChi_245: Button
    private lateinit var btnQuayLai_245: Button
    private lateinit var btnDoiTrangThai_245: Button

    private lateinit var khoanChi_245: KhoanChi

    private val suaKhoanChiLauncher_245 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { ketQua_245 ->

            if (ketQua_245.resultCode == RESULT_OK) {
                val duLieuTraVe_245 = ketQua_245.data

                val tenMoi_245 =
                    duLieuTraVe_245
                        ?.getStringExtra("TEN_KHOAN_CHI")

                val soTienMoi_245 =
                    duLieuTraVe_245
                        ?.getDoubleExtra("SO_TIEN", -1.0)
                        ?: -1.0

                val ghiChuMoi_245 =
                    duLieuTraVe_245
                        ?.getStringExtra("GHI_CHU")
                        .orEmpty()

                if (tenMoi_245.isNullOrBlank() ||
                    !soTienMoi_245.isFinite() ||
                    soTienMoi_245 <= 0
                ) {
                    Toast.makeText(
                        this,
                        "Dữ liệu chỉnh sửa không hợp lệ",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@registerForActivityResult
                }

                khoanChi_245.tenKhoanChi_245 = tenMoi_245
                khoanChi_245.soTien_245 = soTienMoi_245
                khoanChi_245.ghiChu_245 = ghiChuMoi_245

                hienThiKhoanChi_245()

                Toast.makeText(
                    this,
                    "Cập nhật khoản chi thành công",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Nếu hủy, không cập nhật đối tượng KhoanChi.
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        txtTenKhoanChi_245 =
            findViewById(R.id.txtTenKhoanChi_245)

        txtSoTien_245 =
            findViewById(R.id.txtSoTien_245)

        txtGhiChu_245 =
            findViewById(R.id.txtGhiChu_245)

        txtTrangThaiThanhToan_245 =
            findViewById(R.id.txtTrangThaiThanhToan_245)

        btnSuaKhoanChi_245 =
            findViewById(R.id.btnSuaKhoanChi_245)

        btnQuayLai_245 =
            findViewById(R.id.btnQuayLai_245)

        btnDoiTrangThai_245 =
            findViewById(R.id.btnDoiTrangThai_245)

        val maKhoanChi_245 =
            intent.getStringExtra("MA_KHOAN_CHI").orEmpty()

        val tenKhoanChi_245 =
            intent.getStringExtra("TEN_KHOAN_CHI").orEmpty()

        val soTien_245 =
            intent.getDoubleExtra("SO_TIEN", -1.0)

        val ghiChu_245 =
            intent.getStringExtra("GHI_CHU").orEmpty()

        val daThanhToan_245 =
            intent.getBooleanExtra("DA_THANH_TOAN", false)

        if (tenKhoanChi_245.isBlank() ||
            !soTien_245.isFinite() ||
            soTien_245 <= 0
        ) {
            Toast.makeText(
                this,
                "Thông tin khoản chi không hợp lệ",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        khoanChi_245 = KhoanChi(
            maKhoanChi_245 = maKhoanChi_245.ifBlank {
                "KC-${System.currentTimeMillis()}"
            },
            tenKhoanChi_245 = tenKhoanChi_245,
            soTien_245 = soTien_245,
            ghiChu_245 = ghiChu_245,
            daThanhToan_245 = daThanhToan_245
        )

        // Sử dụng lớp KhoanChi để kiểm tra dữ liệu.
        if (!khoanChi_245.laSoTienHopLe_245()) {
            finish()
            return
        }

        hienThiKhoanChi_245()

        btnSuaKhoanChi_245.setOnClickListener {
            moManHinhSua_245()
        }

        btnQuayLai_245.setOnClickListener {
            finish()
        }

        btnDoiTrangThai_245.setOnClickListener {
            khoanChi_245.daThanhToan_245 =
                !khoanChi_245.daThanhToan_245

            hienThiKhoanChi_245()
        }
    }

    private fun hienThiKhoanChi_245() {
        txtTenKhoanChi_245.text =
            khoanChi_245.tenKhoanChi_245

        txtSoTien_245.text = String.format(
            Locale("vi", "VN"),
            "%,.0f VNĐ",
            khoanChi_245.soTien_245
        )

        txtGhiChu_245.text =
            if (khoanChi_245.ghiChu_245.isBlank()) {
                "Không có ghi chú"
            } else {
                khoanChi_245.ghiChu_245
            }

        if (khoanChi_245.daThanhToan_245) {
            txtTrangThaiThanhToan_245.text =
                "Đã thanh toán"

            txtTrangThaiThanhToan_245.setTextColor(
                android.graphics.Color.rgb(22, 101, 52)
            )

            btnDoiTrangThai_245.text =
                "Đánh dấu chưa thanh toán"
        } else {
            txtTrangThaiThanhToan_245.text =
                "Chưa thanh toán"

            txtTrangThaiThanhToan_245.setTextColor(
                android.graphics.Color.rgb(180, 83, 9)
            )

            btnDoiTrangThai_245.text =
                "Đánh dấu đã thanh toán"
        }
    }

    private fun moManHinhSua_245() {
        val intentSua_245 = Intent(
            this,
            ChinhSuaChiTieuActivity::class.java
        ).apply {
            putExtra(
                "TEN_KHOAN_CHI",
                khoanChi_245.tenKhoanChi_245
            )

            putExtra(
                "SO_TIEN",
                khoanChi_245.soTien_245
            )

            putExtra(
                "GHI_CHU",
                khoanChi_245.ghiChu_245
            )
        }

        suaKhoanChiLauncher_245.launch(intentSua_245)
    }
}
