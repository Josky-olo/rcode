package bitflyday.com.mobile.application.rcode.domain

import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeRepository
import bitflyday.com.mobile.application.rcode.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadAllBarcodeUseCase @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
):UseCase<Unit, List<Barcode>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<Barcode>{
        return barcodeRepository.getAllBarcode()
    }

}