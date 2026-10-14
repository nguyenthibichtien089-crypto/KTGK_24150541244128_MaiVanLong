
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
    private lateinit var btnSuaKhoanChi_245: Button
    private lateinit var btnQuayLai_245: Button

    private var tenKhoanChi_245: String = ""
    private var soTien_245: Double = 0.0
    private var ghiChu_245: String = ""

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

                if (!tenMoi_245.isNullOrBlank() &&
                    soTienMoi_245.isFinite() &&
                    soTienMoi_245 > 0
                ) {
                    tenKhoanChi_245 = tenMoi_245
                    soTien_245 = soTienMoi_245
                    ghiChu_245 = ghiChuMoi_245

                    hienThiKhoanChi_245()

                    Toast.makeText(
                        this,
                        "Cập nhật khoản chi thành công",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // Nếu người dùng hủy, dữ liệu hiện tại không đổi.
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

        btnSuaKhoanChi_245 =
            findViewById(R.id.btnSuaKhoanChi_245)

        btnQuayLai_245 =
            findViewById(R.id.btnQuayLai_245)

        tenKhoanChi_245 =
            intent.getStringExtra("TEN_KHOAN_CHI").orEmpty()

        soTien_245 =
            intent.getDoubleExtra("SO_TIEN", -1.0)

        ghiChu_245 =
            intent.getStringExtra("GHI_CHU").orEmpty()

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

        hienThiKhoanChi_245()

        btnSuaKhoanChi_245.setOnClickListener {
            moManHinhSua_245()
        }

        btnQuayLai_245.setOnClickListener {
            finish()
        }
    }

    private fun hienThiKhoanChi_245() {
        txtTenKhoanChi_245.text = tenKhoanChi_245

        txtSoTien_245.text = String.format(
            Locale("vi", "VN"),
            "%,.0f VNĐ",
            soTien_245
        )

        txtGhiChu_245.text =
            if (ghiChu_245.isBlank()) {
                "Không có ghi chú"
            } else {
                ghiChu_245
            }
    }

    private fun moManHinhSua_245() {
        val intentSua_245 = Intent(
            this,
            ChinhSuaChiTieuActivity::class.java
        ).apply {
            putExtra("TEN_KHOAN_CHI", tenKhoanChi_245)
            putExtra("SO_TIEN", soTien_245)
            putExtra("GHI_CHU", ghiChu_245)
        }

        suaKhoanChiLauncher_245.launch(intentSua_245)
    }
}
