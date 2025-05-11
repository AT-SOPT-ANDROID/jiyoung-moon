package org.sopt.at.ui.common

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_data")

object UserDataStore {
    private val USER_ID = intPreferencesKey("user_id")

    // userId 저장하는 함수
    suspend fun saveUserId(context: Context, userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
        }
    }

    // userId 불러오는 함수
    fun getUserId(context: Context): Flow<Int?> {
        return context.dataStore.data.map { prefs ->
            prefs[USER_ID]
        }
    }

    // userId 삭제하는 함수
    suspend fun clearUserId(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID)
        }
    }
}
