
package com.ute.quanlychitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtTenKhoanChi_245: EditText
    private lateinit var edtSoTien_245: EditText
    private lateinit var edtGhiChu_245: EditText
    private lateinit var btnThemKhoanChi_245: Button
    private lateinit var btnXemChiTiet_245: Button

    private var khoanChiHienTai_245: KhoanChi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTenKhoanChi_245 =
            findViewById(R.id.edtTenKhoanChi_245)

        edtSoTien_245 =
            findViewById(R.id.edtSoTien_245)

        edtGhiChu_245 =
            findViewById(R.id.edtGhiChu_245)

        btnThemKhoanChi_245 =
            findViewById(R.id.btnThemKhoanChi_245)

        btnXemChiTiet_245 =
            findViewById(R.id.btnXemChiTiet_245)

        btnThemKhoanChi_245.setOnClickListener {
            themKhoanChi_245()
        }

        btnXemChiTiet_245.setOnClickListener {
            xemChiTietKhoanChi_245()
        }
    }

    private fun taoKhoanChi_245(): KhoanChi? {
        val tenKhoanChi_245 =
            edtTenKhoanChi_245.text.toString().trim()

        val soTienText_245 =
            edtSoTien_245.text.toString().trim()

        val ghiChu_245 =
            edtGhiChu_245.text.toString().trim()

        if (tenKhoanChi_245.isEmpty()) {
            edtTenKhoanChi_245.error =
                "Vui lòng nhập tên khoản chi"
            edtTenKhoanChi_245.requestFocus()
            return null
        }

        if (soTienText_245.isEmpty()) {
            edtSoTien_245.error =
                "Vui lòng nhập số tiền"
            edtSoTien_245.requestFocus()
            return null
        }

        val soTien_245 = soTienText_245.toDoubleOrNull()

        if (soTien_245 == null) {
            edtSoTien_245.error =
                "Số tiền không hợp lệ"
            edtSoTien_245.requestFocus()
            return null
        }

        val khoanChiMoi_245 = KhoanChi(
            maKhoanChi_245 = "KC-${System.currentTimeMillis()}",
            tenKhoanChi_245 = tenKhoanChi_245,
            soTien_245 = soTien_245,
            ghiChu_245 = ghiChu_245,
            daThanhToan_245 = false
        )

        if (!khoanChiMoi_245.laSoTienHopLe_245()) {
            edtSoTien_245.error =
                "Số tiền phải lớn hơn 0"
            edtSoTien_245.requestFocus()
            return null
        }

        return khoanChiMoi_245
    }

    private fun themKhoanChi_245() {
        val khoanChiMoi_245 =
            taoKhoanChi_245() ?: return

        khoanChiHienTai_245 = khoanChiMoi_245

        Toast.makeText(
            this,
            "Đã thêm: ${khoanChiMoi_245.tenKhoanChi_245}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun xemChiTietKhoanChi_245() {
        val khoanChiMoi_245 =
            taoKhoanChi_245() ?: return

        khoanChiHienTai_245 = khoanChiMoi_245

        val intent_245 = Intent(
            this,
            ChiTietChiTieuActivity::class.java
        ).apply {
            putExtra(
                "MA_KHOAN_CHI",
                khoanChiMoi_245.maKhoanChi_245
            )
            putExtra(
                "TEN_KHOAN_CHI",
                khoanChiMoi_245.tenKhoanChi_245
            )
            putExtra(
                "SO_TIEN",
                khoanChiMoi_245.soTien_245
            )
            putExtra(
                "GHI_CHU",
                khoanChiMoi_245.ghiChu_245
            )
            putExtra(
                "DA_THANH_TOAN",
                khoanChiMoi_245.daThanhToan_245
            )
        }

        startActivity(intent_245)
    }
}
