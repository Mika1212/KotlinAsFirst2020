@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1


import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var n1 = abs(n)
    var count = 0
    do {
        count++
        n1 /= 10
    } while (n1 > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var n1 = n
    var f1 = 0
    var f2 = 1
    var a: Int
    while (n1 - 1 > 0) {
        a = f2
        f2 += f1
        f1 = a
        n1 -= 1
    }
    return f2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    while (n1 != 0 && m1 != 0) {
        if (m1 > n1) m1 %= n1
        else
            n1 %= m1
    }
    return n / (n1 + m1) * m
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt())
        if (n % i == 0) {
            return i
        }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo sqrt(n.toDouble()).toInt())
        if (n % i == 0) {
            return i
        }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean =
    lcm(m, n) == m * n

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var m1 = sqrt(m.toDouble())
    var n1 = sqrt(n.toDouble())
    val m2 = m.toDouble()
    val n2 = n.toDouble()
    m1 = floor(m1)
    n1 = floor(n1)
    return m == n || m2 == sqr(m1) || n2 == sqr(n1) || abs(n1 - m1) > 0
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var f = x
    var k = 0
    while (f != 1) {
        f = if (f % 2 == 0) f / 2
        else
            3 * f + 1
        k += 1
    }
    return k
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val xNormal = x % (2.0 * PI)
    var result = xNormal
    var n = 3
    var x1 = -xNormal.pow(n.toDouble()) / factorial(n)
    do {
        result += x1
        n += 2
        x1 *= ((-1) * xNormal * xNormal) / n / (n - 1)
    } while (abs(x1) >= eps)
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val xNormal = x % (2.0 * PI)
    var result = 1.0
    var n = 2
    var x1 = -xNormal.pow(n.toDouble()) / factorial(n)
    do {
        result += x1
        n += 2
        x1 *= ((-1) * xNormal * xNormal) / n / (n - 1)
    } while (abs(x1) >= eps)
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n1 = n
    var result = 0
    do {
        result = result * 10 + n1 % 10
        n1 /= 10
    } while (n1 > 0)
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var n1 = n
    var a: Int
    var n2 = n
    var j = 1
    while (n1 > 0) {
        do {
            a = n2 % 10
            n2 /= 10
        } while (n2 > j - 1)
        if (n1 % 10 != a) return false
        n1 /= 10
        n2 = n1
        j *= 10
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val x = n % 10
    var n1 = n
    while (n1 > 0) {
        if (n1 % 10 != x) return true
        n1 /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 0
    for (i in 1..n) {
        val number = i * i
        var j = digitNumber(number)
        if (k + j > n) {
            var numberFake = number
            for (l in 1..n) {
                k++
                if (k == n) return numberFake / 10.0.pow(j - 1.toDouble()).toInt() else
                    numberFake %= 10.0.pow(j - 1.toDouble()).toInt()
                j--
            }
        }
        k += j
        if (k == n) return number % 10
    }
    return k
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 0
    for (i in 1..n) {
        val number = fib(i)
        var j = digitNumber(number)
        if (k + j > n) {
            var numberFake = number
            for (l in 1..n) {
                k++
                if (k == n) return numberFake / 10.0.pow(j - 1.toDouble()).toInt() else
                    numberFake %= 10.0.pow(j - 1.toDouble()).toInt()
                j--
            }
        }
        k += j
        if (k == n) return number % 10
    }
    return k
}

