package com.inventrack.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = Constants.PREFS_NAME)

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val TOKEN = stringPreferencesKey(Constants.TOKEN_KEY)
    private val REFRESH = stringPreferencesKey(Constants.REFRESH_TOKEN_KEY)
    private val ROLE = stringPreferencesKey(Constants.USER_ROLE_KEY)

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs -> prefs[TOKEN] = token }
    }

    suspend fun saveRefreshToken(refresh: String) {
        context.dataStore.edit { prefs -> prefs[REFRESH] = refresh }
    }

    suspend fun saveRole(role: String) {
        context.dataStore.edit { prefs -> prefs[ROLE] = role }
    }

    suspend fun clear() {
        context.dataStore.edit { prefs -> prefs.clear() }
    }

    suspend fun getToken(): String? {
        val prefs = context.dataStore.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .first()
        return prefs[TOKEN]
    }

    suspend fun getRefreshToken(): String? {
        val prefs = context.dataStore.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .first()
        return prefs[REFRESH]
    }

    fun tokenFlow() = context.dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs -> prefs[TOKEN] }

    fun roleFlow() = context.dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs -> prefs[ROLE] ?: Roles.ANONYMOUS }
}
