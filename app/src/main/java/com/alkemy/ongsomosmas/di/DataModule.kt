package com.alkemy.ongsomosmas.di

import android.content.Context
import com.alkemy.ongsomosmas.data.Preferences
import com.alkemy.ongsomosmas.data.PreferencesImpl
import com.alkemy.ongsomosmas.data.contactus.ContactUsDataSource
import com.alkemy.ongsomosmas.data.contactus.ContactUsService
import com.alkemy.ongsomosmas.data.home.welcome.WelcomeDataSource
import com.alkemy.ongsomosmas.data.home.welcome.WelcomeRepository
import com.alkemy.ongsomosmas.data.home.welcome.WelcomeRepositoryImp
import com.alkemy.ongsomosmas.data.home.welcome.WelcomeService
import com.alkemy.ongsomosmas.data.login.LoginDataSource
import com.alkemy.ongsomosmas.data.login.LoginService
import com.alkemy.ongsomosmas.data.signup.SignUpRepository
import com.alkemy.ongsomosmas.data.signup.SignUpService
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeUseCase
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeUseCaseImp
import com.alkemy.ongsomosmas.ui.signup.RegisterUserUseCase
import com.alkemy.ongsomosmas.ui.signup.RegisterUserUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
        return PreferencesImpl(context)
    }

    @Provides
    fun provideContactUsService(@ApiAlkemy retrofit: Retrofit): ContactUsService {
        return retrofit.create(ContactUsService::class.java)
    }

    @Provides
    fun provideContactUsDataSource(contactUsService: ContactUsService): ContactUsDataSource {
        return ContactUsDataSource(contactUsService)
    }

    @Provides
    @Singleton
    fun providerSignUpService(@ApiAlkemy retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    fun provideLoginService(@ApiAlkemy retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    fun provideLoginDataSource(loginService: LoginService): LoginDataSource {
        return LoginDataSource(loginService)
    }

    @Provides
    fun providesWelcomeService(@ApiAlkemy retrofit: Retrofit): WelcomeService {
        return retrofit.create(WelcomeService::class.java)
    }

    @Provides
    fun providesWelcomeRepository(welcomeDataSource: WelcomeDataSource): WelcomeRepository {
        return WelcomeRepositoryImp(welcomeDataSource)
    }

    @Provides
    fun providesRegisterUserUseCase(repository: SignUpRepository): RegisterUserUseCase {
        return RegisterUserUseCaseImp(repository)
    }

    @Provides
    fun providesWelcomeUseCase(repository: WelcomeRepository): WelcomeUseCase {
        return WelcomeUseCaseImp(repository)
    }

    @Provides
    fun providesWelcomeDatasource(welcomeService: WelcomeService): WelcomeDataSource {
        return WelcomeDataSource(welcomeService)
    }

    companion object {
        private const val API_URL = "http://ongapi.alkemy.org/"
    }
}