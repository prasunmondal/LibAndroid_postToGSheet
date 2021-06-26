package com.prasunmondal.postjsontosheets.clients.get

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.commons.JSONUtils
import com.prasunmondal.postjsontosheets.clients.commons.JsonTags
import java.lang.reflect.Type
import java.util.ArrayList

class GetResponse: APIResponse {

    constructor(responsePayload: String) {
        this.responsePayload = responsePayload
    }

    fun getObject(): GetResponse {
        return this
    }

    private fun getRecordsArray(str: String): JsonArray? {
        val jsonString = JSONUtils.jsonStringCleanUp(str)
        Log.e("parsing: ", jsonString)
        val arrayLabel = "records"
        val parser = JsonParser()
        val jsonObject = parser.parse(jsonString).asJsonObject
        var jsonarray: JsonArray? = null
        try {
            jsonarray = jsonObject.getAsJsonArray(arrayLabel)
        } catch (e: Exception) {
            Log.e("parseJSONObject", "Error while parsing")
        }
        return jsonarray
    }

    /**
     * Sample use:
     * val type = object : TypeToken<List<Person>>() {}.type
     * val object: Person = getParsedList<Person>(response.getRawResponse(), type)[0]
     */
    fun <T> getParsedList(typeToken: Type): ArrayList<T> {
        var jsonarray = getRecordsArray(this.getRawResponse())
        val result: ArrayList<T> = GsonBuilder().create().fromJson(
            jsonarray.toString(),
            typeToken
        )
        return result
    }

    fun numberOfRecordsDeleted(): Int {
        return getJsonObject()!!.get(JsonTags.RESPONSE_ROWS_AFFECTED).asInt
    }
}