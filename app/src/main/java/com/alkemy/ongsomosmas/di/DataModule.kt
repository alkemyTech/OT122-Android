package com.alkemy.ongsomosmas.di

import android.content.Context
import com.alkemy.ongsomosmas.data.Preferences
import com.alkemy.ongsomosmas.data.contactus.ContactUsDataSource
import com.alkemy.ongsomosmas.data.contactus.ContactUsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @ApiAlkemy
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun providePreferences(@ApplicationContext context: Context): Preferences {
        return Preferences(context)
    }

    @Provides
    fun provideContactUsService(@ApiAlkemy retrofit: Retrofit) =
        retrofit.create(ContactUsService::class.java)

    @Provides
    fun provideContactUsDataSource(contactUsService: ContactUsService): ContactUsDataSource {
        return ContactUsDataSource(contactUsService)
    }

    companion object {
        private const val API_URL = "http://ongapi.alkemy.org/"
    }
}