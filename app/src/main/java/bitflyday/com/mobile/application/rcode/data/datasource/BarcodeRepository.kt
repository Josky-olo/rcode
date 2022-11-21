package bitflyday.com.mobile.application.rcode.data.datasource

import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BarcodeRepository @Inject constructor(
    private val barcodeDao: BarcodeDao
) {
    fun getAllBarcode() = barcodeDao.getAllBarcode()
    fun insertBarcode(barcode: Barcode) = barcodeDao.insertBarcode(barcode)
}