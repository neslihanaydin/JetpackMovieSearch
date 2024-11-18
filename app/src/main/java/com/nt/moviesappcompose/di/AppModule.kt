package com.nt.moviesappcompose.di

import com.nt.moviesappcompose.api.RetrofitAPI
import com.nt.moviesappcompose.repository.MovieRepository
import com.nt.moviesappcompose.repository.MovieRepositoryInterface
import com.nt.moviesappcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepositoryInterface(retrofitAPI: RetrofitAPI): MovieRepositoryInterface {
        return MovieRepository(retrofitAPI)
    }
}