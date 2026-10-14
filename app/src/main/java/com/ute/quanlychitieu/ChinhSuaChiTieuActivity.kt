
package com.ute.quanlychitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var edtTenSua_245: EditText
    private lateinit var edtSoTienSua_245: EditText
    private lateinit var edtGhiChuSua_245: EditText
    private lateinit var btnLuuSua_245: Button
    private lateinit var btnHuySua_245: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        edtTenSua_245 =
            findViewById(R.id.edtTenSua_245)

        edtSoTienSua_245 =
            findViewById(R.id.edtSoTienSua_245)

        edtGhiChuSua_245 =
            findViewById(R.id.edtGhiChuSua_245)

        btnLuuSua_245 =
            findViewById(R.id.btnLuuSua_245)

        btnHuySua_245 =
            findViewById(R.id.btnHuySua_245)

        edtTenSua_245.setText(
            intent.getStringExtra("TEN_KHOAN_CHI").orEmpty()
        )

        val soTienBanDau_245 =
            intent.getDoubleExtra("SO_TIEN", -1.0)

        edtSoTienSua_245.setText(
            if (soTienBanDau_245 > 0 &&
                soTienBanDau_245.isFinite()
            ) {
                soTienBanDau_245.toString()
            } else {
                ""
            }
        )

        edtGhiChuSua_245.setText(
            intent.getStringExtra("GHI_CHU").orEmpty()
        )

        btnLuuSua_245.setOnClickListener {
            luuThayDoi_245()
        }

        btnHuySua_245.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun luuThayDoi_245() {
        val tenMoi_245 =
            edtTenSua_245.text.toString().trim()

        val soTienText_245 =
            edtSoTienSua_245.text.toString().trim()

        val ghiChuMoi_245 =
            edtGhiChuSua_245.text.toString().trim()

        if (tenMoi_245.isEmpty()) {
            edtTenSua_245.error =
                "Vui lòng nhập tên khoản chi"
            edtTenSua_245.requestFocus()
            return
        }

        val soTienMoi_245 =
            soTienText_245.toDoubleOrNull()

        if (soTienMoi_245 == null ||
            !soTienMoi_245.isFinite() ||
            soTienMoi_245 <= 0
        ) {
            edtSoTienSua_245.error =
                "Số tiền phải lớn hơn 0"
            edtSoTienSua_245.requestFocus()
            return
        }

        val ketQuaIntent_245 = Intent().apply {
            putExtra("TEN_KHOAN_CHI", tenMoi_245)
            putExtra("SO_TIEN", soTienMoi_245)
            putExtra("GHI_CHU", ghiChuMoi_245)
        }

        setResult(Activity.RESULT_OK, ketQuaIntent_245)
        finish()
    }
}
