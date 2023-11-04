package five.head.core.domain.utils

class FixedList<T>(
    val fixedSize: Int,
) {
    private val container = mutableListOf<T>()
    val size: Int
        get() = container.size

    private operator fun get(index: Int) : T?{
        return if (size<=index) null else container[index]
    }
    fun add(element: T) : Boolean{
        if (fixedSize > size){
            return container.add(element)
        }
        for (item in 1 until size){
            container[item-1] = container[item]
        }
        container[size-1] = element
        return true
    }

    fun <R> map(transform: (T) -> R, transformWhenNull: () -> R): List<R> {
        val answer = mutableListOf<R>()
        for (item in container){
            answer.add(transform(item))
        }
        for(index in answer.size until fixedSize){
            answer.add(index, transformWhenNull())
        }
        return answer
    }


}