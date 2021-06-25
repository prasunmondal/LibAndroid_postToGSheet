package com.prasunmondal.postjsontosheets.clients.get

import java.util.function.Consumer

interface IsPresentConditionalAndFlow {

    interface ScriptIdBuilder {
        fun scriptId(scriptId: String): SheetIdBuilder
    }

    interface SheetIdBuilder {
        fun sheetId(sheetId: String): TabNameBuilder
    }

    interface KeysBuilder {
        fun keys(key: String): ValuesBuilder
    }

    interface ValuesBuilder {
        fun values(value: String): FinalRequestBuilder
    }

    interface TabNameBuilder {
        fun tabName(tabName: String): KeysBuilder
    }

    interface FinalRequestBuilder {
        // All optional parameters goes here
        fun build(): IsPresentConditionalAnd
        fun postCompletion(onCompletion: Consumer<IsPresentConditionalAndResponse>?): FinalRequestBuilder
    }

    fun execute(): IsPresentConditionalAndResponse
}