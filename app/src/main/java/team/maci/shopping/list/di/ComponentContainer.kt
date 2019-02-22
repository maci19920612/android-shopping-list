package team.maci.shopping.list.di

object ComponentContainer {
    private var component: Any? = null
    fun init(component: Any) {
        if(this.component != null){
            throw IllegalStateException("Component container already intiailized")
        }
        this.component = component
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> get(): T {
        val component = component ?: throw IllegalStateException("Component container never initialized")
        return component as T
    }
}