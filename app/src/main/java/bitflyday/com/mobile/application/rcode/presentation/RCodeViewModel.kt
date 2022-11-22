package bitflyday.com.mobile.application.rcode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.domain.InsertBarcodeUseCase
import bitflyday.com.mobile.application.rcode.domain.LoadAllBarcodeUseCase
import bitflyday.com.mobile.application.rcode.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RCodeViewModel @Inject constructor(
    loadAllBarcodeUseCase: LoadAllBarcodeUseCase,
    private val insertBarcodeUseCase: InsertBarcodeUseCase
) : ViewModel() {


    val getAllBarcode: StateFlow<List<Barcode>?> = loadAllBarcodeUseCase(Unit).map {
        it.data
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    suspend fun addBarcodeData(barcode: Barcode): Long? = insertBarcodeUseCase(barcode).data

}