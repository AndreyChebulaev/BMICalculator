package com.example.bodymassindexcalculator

import com.example.bodymassindexcalculator.data.models.repository.AuthRepository
import com.example.bodymassindexcalculator.data.models.repository.BMIRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://uxmofrndhmvsuunzjjop.supabase.co",
            supabaseKey = "sb_publishable_bwGN916BgNZdUlsEslGbMw_cxrP9AEn"
        ) {
            install(Postgrest)
            install(Auth)
        }
    }

    @Provides
    @Singleton
    fun provideAuthRepository(client: SupabaseClient) = AuthRepository(client)

    @Provides
    @Singleton
    fun provideBMIRepository(client: SupabaseClient) = BMIRepository(supabase = client)
}