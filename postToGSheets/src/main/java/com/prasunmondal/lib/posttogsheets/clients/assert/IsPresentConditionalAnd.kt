package com.prasunmondal.postjsontosheets.clients.get

import com.prasunmondal.postjsontosheets.clients.commons.APICalls
import com.prasunmondal.postjsontosheets.clients.commons.ExecutePostCalls
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

class IsPresentConditionalAnd : APICalls, IsPresentConditionalAndFlow, IsPresentConditionalAndFlow.ScriptIdBuilder,
        IsPresentConditionalAndFlow.SheetIdBuilder,
        IsPresentConditionalAndFlow.TabNameBuilder,
        IsPresentConditionalAndFlow.FinalRequestBuilder,
        IsPresentConditionalAndFlow.KeysBuilder,
        IsPresentConditionalAndFlow.ValuesBuilder {
    private lateinit var scriptURL: String
    private lateinit var sheetId: String
    private lateinit var tabName: String
    private lateinit var keys: String
    private lateinit var values: String

    var onCompletion: Consumer<IsPresentConditionalAndResponse>? = null

    override fun scriptId(scriptId: String): IsPresentConditionalAndFlow.SheetIdBuilder {
        this.scriptURL = scriptId
        return this
    }

    override fun sheetId(sheetId: String): IsPresentConditionalAndFlow.TabNameBuilder {
        this.sheetId = sheetId
        return this
    }

    override fun tabName(tabName: String): IsPresentConditionalAndFlow.KeysBuilder {
        this.tabName = tabName
        return this
    }

    override fun keys(key: String): IsPresentConditionalAndFlow.ValuesBuilder {
        this.keys = key
        return this
    }

    override fun values(value: String): IsPresentConditionalAndFlow.FinalRequestBuilder {
        this.values = value
        return this
    }

    override fun postCompletion(onCompletion: Consumer<IsPresentConditionalAndResponse>?): IsPresentConditionalAndFlow.FinalRequestBuilder {
        this.onCompletion = onCompletion
        return this
    }

    override fun build(): IsPresentConditionalAnd {
        return this
    }

    override fun execute(): IsPresentConditionalAndResponse {
        val scriptUrl = URL(this.scriptURL)
        val postDataParams = JSONObject()
        postDataParams.put("opCode", "IS_PRESENT_CONDITIONAL_AND")
        postDataParams.put("sheetId", this.sheetId)
        postDataParams.put("tabName", this.tabName)
        postDataParams.put("dataValue", keys)
        postDataParams.put("dataColumn", values)

        val c = ExecutePostCalls(scriptUrl, postDataParams, null)
        val response = c.execute().get()
        return IsPresentConditionalAndResponse(response).getObject()
    }

    companion object {
        fun builder(): IsPresentConditionalAndFlow.ScriptIdBuilder {
            return IsPresentConditionalAnd()
        }
    }
}