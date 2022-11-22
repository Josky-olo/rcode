package bitflyday.com.mobile.application.rcode.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BarcodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBarcode(barcode: Barcode): Long

    @Query("SELECT * FROM barcode WHERE barcodeId=:id")
    fun getBarcodeById(id: Long): Flow<Barcode>

    @Query("SELECT * FROM barcode")
    fun getAllBarcode(): Flow<List<Barcode>>
}

