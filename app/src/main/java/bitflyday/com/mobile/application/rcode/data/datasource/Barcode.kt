package bitflyday.com.mobile.application.rcode.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode")
data class Barcode(
    @PrimaryKey(autoGenerate = true)
    val barcodeId: Long = 0,
    val barcodeText: String ="",
    val eventTime: Long = System.currentTimeMillis()
)
