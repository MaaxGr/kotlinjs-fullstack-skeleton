package components

import HelloWorldMessage
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.*
import react.dom.h1
import react.dom.p

external interface HelloWorldProps : RProps {
}

external interface HelloWorldState : RState {
    var serverMessageContent: String
}

class HelloWorldComponent : RComponent<HelloWorldProps, HelloWorldState>() {

    override fun HelloWorldState.init() {
        serverMessageContent = ""

        MainScope().launch {
            val serverMessage = fetchHelloWorldMessage()
            setState {
                serverMessageContent = serverMessage.message
            }
        }
    }

    override fun RBuilder.render() {

        h1 { +"Test Heading" }
        p { +state.serverMessageContent }

    }

    private suspend fun fetchHelloWorldMessage(): HelloWorldMessage {
        val json = window.fetch("http://localhost:8448/hello")
            .await()
            .json()
            .await()
        println(JSON.stringify(json))

        return json.unsafeCast<HelloWorldMessage>()
    }

}