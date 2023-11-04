package five.head.core

import five.head.core.domain.utils.FixedList
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val s = FixedList<Int>(4)
        s.add(1)
        s.add(2)
        s.add(3)
        s.add(4)
        val list = s.map(
            transform = { it.toString() },
            transformWhenNull = { "9999" }
        )
        println(list)
    }
}