import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main(){
    println("Hello World")

    GlobalScope.launch {
        println("Hello from coroutine")
    }
}