package com.palash.realtimedatabase_with_mvvm_and_hilt_di.di

import com.google.firebase.database.FirebaseDatabase
import com.palash.realtimedatabase_with_mvvm_and_hilt_di.repository.RealtimeDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRealtimeDatabase() : FirebaseDatabase{
        val database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true) //Offline persistence
        return database
    }

    @Provides
    @Singleton
    fun provideRealtimeDatabaseRepository(
        firebaseDatabase: FirebaseDatabase
    ): RealtimeDatabaseRepository {
        return RealtimeDatabaseRepository(firebaseDatabase)
    }
}