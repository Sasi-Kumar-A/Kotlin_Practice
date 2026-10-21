package ScopeFunction

import java.util.Locale
import java.util.Locale.getDefault

class let_also_put {
    val name : String = "ailce"

    fun user() {
        name.let {
            val cap = it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() }
            println(cap)
        }
    }

    val name1 = "Monaliza"
    val length = name1.let{
        println("The name is $it")
        it.length
    }

    val basket = mutableListOf("Apple","Banana","Orange","Mango")

    val pack = basket.also {
        println(it)
    }

    class Person{
        var name2 = ""
        var price = 0
    }

    val mat = Person().apply{
        name2 = "Manobala"
        price = 49000
    }

}

fun main(){
    val obj = let_also_put()
    obj.user()

    println(obj.mat.toString())
}