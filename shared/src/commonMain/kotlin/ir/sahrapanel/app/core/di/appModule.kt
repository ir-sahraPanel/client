package ir.sahrapanel.app.core.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module


val appModule = module {
    single<Json>() { appJson()}
    //    single<UserStorage>()
}

private fun appJson() : Json = Json {
        // 1. Ignore keys in the JSON string that aren't declared in your @Serializable class
        ignoreUnknownKeys = true

        // 2. Format the output JSON string with indents and line breaks
        prettyPrint = true

        // 3. If a key is missing or null, use the default values defined in your class properties
        coerceInputValues = true

        // 4. Don't serialize properties if they match their default values
        encodeDefaults = false
    }

