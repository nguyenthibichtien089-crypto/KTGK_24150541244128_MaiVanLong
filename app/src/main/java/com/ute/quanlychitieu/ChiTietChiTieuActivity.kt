
package com.ute.quanlychitieu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ChiTietChiTieuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        val txtTenKhoanChi =
            findViewById<TextView>(R.id.txtTenKhoanChi_245)
        val txtSoTien =
            findViewById<TextView>(R.id.txtSoTien_245)
        val txtGhiChu =
            findViewById<TextView>(R.id.txtGhiChu_245)
        val btnQuayLai =
            findViewById<Button>(R.id.btnQuayLai_245)

        val tenKhoanChi =
            intent.getStringExtra("TEN_KHOAN_CHI")
        val soTien =
            intent.getDoubleExtra("SO_TIEN", -1.0)
        val ghiChu =
            intent.getStringExtra("GHI_CHU")

        if (tenKhoanChi.isNullOrBlank() ||
            !soTien.isFinite() ||
            soTien <= 0
        ) {
            Toast.makeText(
                this,
                "Thông tin khoản chi không hợp lệ",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        txtTenKhoanChi.text = tenKhoanChi
        txtSoTien.text =
            String.format(Locale("vi", "VN"), "%,.0f VNĐ", soTien)
        txtGhiChu.text =
            if (ghiChu.isNullOrBlank()) {
                "Không có ghi chú"
            } else {
                ghiChu
            }

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}
