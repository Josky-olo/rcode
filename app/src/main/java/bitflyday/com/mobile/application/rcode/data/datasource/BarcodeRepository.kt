package bitflyday.com.mobile.application.rcode.data.datasource

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BarcodeRepository @Inject constructor(
    private val barcodeDao: BarcodeDao
) {
    fun getAllBarcode() = barcodeDao.getAllBarcode()

    suspend fun insertBarcode(barcode: Barcode):Long = barcodeDao.insertBarcode(barcode)
}