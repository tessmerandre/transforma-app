package data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.appleNativeLogin
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import io.github.jan.supabase.storage.Storage

private const val SUPABASE_URL = ""
private const val SUPABASE_KEY = ""
private const val GOOGLE_SERVER_CLIENT_ID = ""

fun createTransformaSupabaseClient(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest)
        install(Storage)
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = GOOGLE_SERVER_CLIENT_ID)
            appleNativeLogin()
        }
    }
}