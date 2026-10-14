
package com.ute.quanlychitieu

data class KhoanChi(
    val maKhoanChi_245: String,
    var tenKhoanChi_245: String,
    var soTien_245: Double,
    var ghiChu_245: String,
    var daThanhToan_245: Boolean = false
) {

    fun layThongTin_245(): String {
        return """
            Mã khoản chi: $maKhoanChi_245
            Tên khoản chi: $tenKhoanChi_245
            Số tiền: $soTien_245 VNĐ
            Ghi chú: ${ghiChu_245.ifBlank { "Không có ghi chú" }}
            Trạng thái: ${
            if (daThanhToan_245) "Đã thanh toán"
            else "Chưa thanh toán"
        }
        """.trimIndent()
    }

    fun laSoTienHopLe_245(): Boolean {
        return soTien_245.isFinite() && soTien_245 > 0
    }
}
