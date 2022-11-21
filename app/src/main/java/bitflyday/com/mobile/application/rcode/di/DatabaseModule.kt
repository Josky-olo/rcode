package bitflyday.com.mobile.application.rcode.di

import android.content.Context
import bitflyday.com.mobile.application.rcode.data.datasource.AppDatabase
import bitflyday.com.mobile.application.rcode.data.datasource.BarcodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideBarcodeDao(appDatabase: AppDatabase): BarcodeDao{
        return appDatabase.barcodeDao()
    }
}