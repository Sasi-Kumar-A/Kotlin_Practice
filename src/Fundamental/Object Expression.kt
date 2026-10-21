package Fundamental

class `Object Expression` {

    interface clickListener{
        fun onClick()
    }

    class button {
        private var listener: clickListener? = null

        fun setonClick(newlistener : clickListener){
            this.listener = newlistener
        }

        fun press(){
            listener?.onClick()
        }
    }
}

fun main(){
    val but = `Object Expression`.button()

    but.setonClick(object : `Object Expression`.clickListener{
        override fun onClick() {
            println("This is a Button click function.....`")
        }
    })
}