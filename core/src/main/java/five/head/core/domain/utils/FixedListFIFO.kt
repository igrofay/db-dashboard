package five.head.core.domain.utils

class FixedListFIFO<T>(
    val fixedSize: Int,
) {
    private var container = mutableListOf<T>()
//    private constructor(fixedSize: Int, container: MutableList<T>) : this(fixedSize){
//        this.container = container
//    }
    val size: Int
        get() = container.size

    private operator fun get(index: Int) : T?{
        return if (size<=index) null else container[index]
    }
    fun add(element: T) : FixedListFIFO<T> {
        if (fixedSize > size){
            container.add(element)
            return this
        }
        for (item in 1 until size){
            container[item-1] = container[item]
        }
        container[size-1] = element
        return this
    }

    fun <R> map(transform: (Int, T) -> R, transformWhenNull: (Int) -> R): List<R> {
        val answer = mutableListOf<R>()
        for (index in 0 until (fixedSize - size)){
            answer.add(index, transformWhenNull(index))
        }
        for((count, index) in (answer.size until fixedSize).withIndex()){
            answer.add(index, transform(index, container[count]))
        }
        return answer
    }

    override fun hashCode(): Int {
        return container.sumOf { it.hashCode() } * size
    }



    override fun toString(): String {
        return this.map(
            transform = { _, data-> data.toString()},
            transformWhenNull = { "null" }
        ).joinToString(", ")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as FixedListFIFO<*>
        return other.hashCode() == this.hashCode()
    }
}