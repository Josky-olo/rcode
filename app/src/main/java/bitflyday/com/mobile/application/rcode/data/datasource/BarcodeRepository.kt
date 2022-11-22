package bitflyday.com.mobile.application.rcode.data.datasource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BarcodeRepository @Inject constructor(
    private val barcodeDao: BarcodeDao
) {

    fun getBarcodeById(barcodeId:Long) = barcodeDao.getBarcodeById(barcodeId)

    fun getAllBarcode() = barcodeDao.getAllBarcode()

    suspend fun insertBarcode(barcode: Barcode):Long = barcodeDao.insertBarcode(barcode)
}