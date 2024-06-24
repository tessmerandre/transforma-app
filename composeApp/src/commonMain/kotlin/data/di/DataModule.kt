package data.di

import data.post.repository.PostRepositoryImpl
import data.supabase.createTransformaSupabaseClient
import domain.post.repository.PostRepository
import io.github.jan.supabase.SupabaseClient
import org.koin.dsl.module

val dataModule = module {
    single<SupabaseClient> { createTransformaSupabaseClient() }
    factory<PostRepository> { PostRepositoryImpl(get()) }
}