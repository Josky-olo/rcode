package bitflyday.com.mobile.application.rcode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeDao
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeRepository
import bitflyday.com.mobile.application.rcode.domain.InsertBarcodeUseCase
import bitflyday.com.mobile.application.rcode.domain.LoadAllBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RCodeViewModel @Inject constructor(
    loadAllBarcodeUseCase: LoadAllBarcodeUseCase,
    insertBarcodeUseCase: InsertBarcodeUseCase,
) : ViewModel() {

    val barcodeData: StateFlow<Any> = flow {
        emit(loadAllBarcodeUseCase(Unit))
    }.stateIn(viewModelScope, SharingStarted.Eagerly, Barcode())


    fun v() = viewModelScope.launch(Dispatchers.IO) {
        insert
    }

}