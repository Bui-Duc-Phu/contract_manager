package com.example.contract_app.di

import android.content.Context
import com.example.contract_app.database.AppDatabase
import com.example.contract_app.database.ContactDao
import com.example.contract_app.database.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideContactDao(database: AppDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepository(contactDao)
    }


}
