
package com.ute.quanlychitieu

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
            themKhoanChi()
        }

        btnXemChiTiet_245.setOnClickListener {
            Toast.makeText(
                this,
                "Chức năng xem chi tiết sẽ được làm ở Câu 2",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun themKhoanChi() {
        val tenKhoanChi =
            edtTenKhoanChi_245.text.toString().trim()

        val soTienText =
            edtSoTien_245.text.toString().trim()

        val ghiChu =
            edtGhiChu_245.text.toString().trim()

        if (tenKhoanChi.isEmpty()) {
            edtTenKhoanChi_245.error =
                "Vui lòng nhập tên khoản chi"
            edtTenKhoanChi_245.requestFocus()
            return
        }

        if (soTienText.isEmpty()) {
            edtSoTien_245.error =
                "Vui lòng nhập số tiền"
            edtSoTien_245.requestFocus()
            return
        }

        val soTien = soTienText.toDoubleOrNull()

        if (soTien == null ||
            soTien.isNaN() ||
            soTien.isInfinite() ||
            soTien <= 0
        ) {
            edtSoTien_245.error =
                "Số tiền phải là số lớn hơn 0"
            edtSoTien_245.requestFocus()
            return
        }

        Toast.makeText(
            this,
            "Thêm khoản chi thành công!",
            Toast.LENGTH_SHORT
        ).show()

        // Tạm thời ghi nhận dữ liệu hợp lệ.
        // Phần quản lý đối tượng KhoanChi sẽ làm ở Câu 4.
    }
}
