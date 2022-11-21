package bitflyday.com.mobile.application.rcode.domain

import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeRepository
import bitflyday.com.mobile.application.rcode.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertBarcodeUseCase @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Barcode, Long>(dispatcher) {

    override suspend fun execute(parameters: Barcode): Long {
        return barcodeRepository.insertBarcode(parameters)
    }

}