package bitflyday.com.mobile.application.rcode.domain

import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeRepository
import bitflyday.com.mobile.application.rcode.di.IoDispatcher
import bitflyday.com.mobile.application.rcode.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadAllBarcodeUseCase @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<Barcode>>(dispatcher){

    override fun execute(parameters: Unit): Flow<Result<List<Barcode>>> {
        return barcodeRepository.getAllBarcode().map { Result.Success(it) }
    }

}