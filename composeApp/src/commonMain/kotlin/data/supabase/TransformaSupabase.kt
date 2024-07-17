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

private const val SUPABASE_URL = "https://bahsvnqmjcsyeslxuknn.supabase.co"

private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJhaHN2bnFtamNzeWVzbHh1a25uIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTU5NjYwMDEsImV4cCI6MjAzMTU0MjAwMX0.c1yKL9VGM3kuKFt7kG9V1kI5ajkKdszI-1EplbzcRBw"

private const val GOOGLE_SERVER_CLIENT_ID = "254342349593-4v8sduav7j24q6po90ajo0jlash7bu3f.apps.googleusercontent.com"

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