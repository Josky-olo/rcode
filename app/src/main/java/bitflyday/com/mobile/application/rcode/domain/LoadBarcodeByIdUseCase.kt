package bitflyday.com.mobile.application.rcode.domain

import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeRepository
import bitflyday.com.mobile.application.rcode.di.IoDispatcher
import bitflyday.com.mobile.application.rcode.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadBarcodeByIdUseCase @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Long, Barcode>(dispatcher){

    override fun execute(parameters: Long): Flow<Result<Barcode>> {
        return barcodeRepository.getBarcodeById(parameters).map { Result.Success(it) }
    }

}