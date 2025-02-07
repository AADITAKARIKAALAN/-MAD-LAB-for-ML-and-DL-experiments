/*
 * Copyright 2024 Yatik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yatik.qrscanner.models.barcode.data

import android.os.Parcelable
import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
enum class Security {
    @SerializedName("OPEN") OPEN,
    @SerializedName("WEP") WEP,
    @SerializedName("WPA") WPA,
    @SerializedName("WPA2") WPA2
}

@Keep
@Parcelize
data class WiFi(
    val ssid: String?,
    val password: String?,
    val security: Security?
) : Parcelable
