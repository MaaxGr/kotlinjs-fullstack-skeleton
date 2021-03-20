import components.HelloWorldComponent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

external interface AppState : RState {

}

class App : RComponent<RProps, AppState>() {

    override fun RBuilder.render() {
        child(HelloWorldComponent::class) {}
    }

}
