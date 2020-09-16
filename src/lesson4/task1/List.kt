@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double =
    sqrt(v.map { it * it }.sum())
//sqrt(v.map { it * it }.fold(0.0) { previousResult, element -> previousResult + element })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.fold(0.0) { previousResult, element ->
        previousResult + element
    } / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val a = mean(list)
    for ((index, element) in list.withIndex()) {
        list[index] = element - a
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in b.indices) c += a[i] * b[i]
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var b = 1
    return p.fold(0) { previousResult, element ->
        if (b != 1 || previousResult != 0) b *= x
        previousResult + element * b
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var a = 0
    for (i in 0 until list.size) {
        a += list[i]
        list[i] = a
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    while (number > 1) {
        for (i in 2..n)
            while (number % i == 0) {
                list.add(i)
                number /= i
                if (number == 1) break
            }
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    while (number > 0) {
        val digit = number % base
        list.add(digit)
        number /= base
    }
    return list.reversed()
}

/* Подскажите, пожалуйста, почему эта программа выполнялась неправильно?
Я попытался перевернуть список, но не вышло.
{
    val list = mutableListOf<Int>()
    var digit = 0
    var number = n
    while (number > 0) {
        digit = number % base
        list.add(digit)
        number /= base
    }
    val list1 = mutableListOf<Int>()
    if (list.isNotEmpty())
        for (i in 0 until list.size) {
            list1.add(list[list.size - i-1])
        }
    return list1
}
 */

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */

fun convertToStringHelper(number: Int): String =
    if (number < 10) "$number"
    else {
        val a = 'a' + number - 10
        a.toString()
    }


fun convertToString(n: Int, base: Int): String {
    val result = mutableListOf<String>()
    for (i in convert(n, base)) {
        result.add(convertToStringHelper(i))
    }
    if (result.isEmpty()) result.add("0")
    return result.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var a = 0
    for (element in digits) {
        a = a * base + element
    }
    return a
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */

fun decimalFromSrtingHelper(number: Char): Int = if (number - 'a' > -1) number - 'a' + 10
else
    number.toString().toInt()

fun decimalFromString(str: String, base: Int): Int {
    val a = mutableListOf<Int>()
    for (i in str) {
        a.add(decimalFromSrtingHelper(i))
    }
    return decimal(a, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val result = mutableListOf<String>()
    var k1000 = n / 1000
    var k100 = n % 1000 / 100
    var k10 = n % 100 / 10
    var k1 = n % 10
    while (k1000 > 0) {
        result += "M"
        k1000 -= 1
    }
    when {
        k100 == 9 -> {
            k100 = -9
            result += "CM"
        }
        k100 == 4 -> {
            k100 -= 4
            result += "CD"
        }
        k100 > 4 -> {
            result += "D"
            k100 -= 5
        }
    }
    while (k100 > 0) {
        result += "C"
        k100 -= 1
    }
    when {
        k10 == 9 -> {
            result += "XC"
            k10 -= 9
        }
        k10 == 4 -> {
            result += "XL"
            k10 -= 4
        }
        k10 > 4 -> {
            result += "L"
            k10 -= 5
        }
    }
    while (k10 > 0) {
        result += "X"
        k10 -= 1
    }
    when {
        k1 == 9 -> {
            result += "IX"
            k1 -= 9
        }
        k1 == 4 -> {
            k1 -= 4
            result += "IV"
        }
        k1 > 4 -> {
            result += "V"
            k1 -= 5
        }
    }
    while (k1 > 0) {
        result += "I"
        k1 -= 1
    }
    return result.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun russian(n: Int): String {
    var n1 = n
    val result = mutableListOf<String>()
    if (n1 % 100 in 10..19) {
        when (n1 % 100) {
            10 -> result.add(0, "десять")
            11 -> result.add(0, "одиннадцать")
            12 -> result.add(0, "двенадцать")
            13 -> result.add(0, "тринадцать")
            14 -> result.add(0, "четырнадцать")
            15 -> result.add(0, "пятнадцать")
            16 -> result.add(0, "шестнадцать")
            17 -> result.add(0, "семнадцать")
            18 -> result.add(0, "восемнадцать")
            else -> result.add(0, "девятнадцать")
        }
        n1 /= 100
        if (n1 == 0) return result.joinToString(separator = " ")
    } else {
        when (n1 % 10) {
            1 -> result.add(0, "один")
            2 -> result.add(0, "два")
            3 -> result.add(0, "три")
            4 -> result.add(0, "четыре")
            5 -> result.add(0, "пять")
            6 -> result.add(0, "шесть")
            7 -> result.add(0, "семь")
            8 -> result.add(0, "восемь")
            9 -> result.add(0, "девять")
        }
        if (n1 == 0) return "ноль"
        n1 /= 10
        if (n1 == 0) return result.joinToString(separator = " ")
        when (n1 % 10) {
            2 -> result.add(0, "двадцать")
            3 -> result.add(0, "тридцать")
            4 -> result.add(0, "сорок")
            5 -> result.add(0, "пятьдесят")
            6 -> result.add(0, "шестьдесят")
            7 -> result.add(0, "семьдесят")
            8 -> result.add(0, "восемьдесят")
            9 -> result.add(0, "девяносто")
        }
        n1 /= 10
        if (n1 == 0) return result.joinToString(separator = " ")
    }
    when (n1 % 10) {
        1 -> result.add(0, "сто")
        2 -> result.add(0, "двести")
        3 -> result.add(0, "триста")
        4 -> result.add(0, "четыреста")
        5 -> result.add(0, "пятьсот")
        6 -> result.add(0, "шестьсот")
        7 -> result.add(0, "семьсот")
        8 -> result.add(0, "восемьсот")
        9 -> result.add(0, "девятьсот")
    }
    n1 /= 10
    if (n1 == 0) return result.joinToString(separator = " ")
    if (n1 % 100 in 10..19) {
        when (n1 % 100) {
            10 -> result.add(0, "десять тысяч")
            11 -> result.add(0, "одиннадцать тысяч")
            12 -> result.add(0, "двенадцать тысяч")
            13 -> result.add(0, "тринадцать тысяч")
            14 -> result.add(0, "четырнадцать тысяч")
            15 -> result.add(0, "пятнадцать тысяч")
            16 -> result.add(0, "шестнадцать тысяч")
            17 -> result.add(0, "семнадцать тысяч")
            18 -> result.add(0, "восемнадцать тысяч")
            else -> result.add(0, "девятнадцать тысяч")
        }
        n1 /= 100
        if (n1 == 0) return result.joinToString(separator = " ")
    } else {
        when (n1 % 10) {
            0 -> result.add(0, "тысяч")
            1 -> result.add(0, "одна тысяча")
            2 -> result.add(0, "две тысячи")
            3 -> result.add(0, "три тысячи")
            4 -> result.add(0, "четыре тысячи")
            5 -> result.add(0, "пять тысяч")
            6 -> result.add(0, "шесть тысяч")
            7 -> result.add(0, "семь тысяч")
            8 -> result.add(0, "восемь тысяч")
            else -> result.add(0, "девять тысяч")
        }
        n1 /= 10
        if (n1 == 0) return result.joinToString(separator = " ")
        when (n1 % 10) {
            2 -> result.add(0, "двадцать")
            3 -> result.add(0, "тридцать")
            4 -> result.add(0, "сорок")
            5 -> result.add(0, "пятьдесят")
            6 -> result.add(0, "шестьдесят")
            7 -> result.add(0, "семьдесят")
            8 -> result.add(0, "восемьдесят")
            9 -> result.add(0, "девяносто")
        }
        n1 /= 10
    }
    if (n1 == 0) return result.joinToString(separator = " ")
    when (n1 % 10) {
        1 -> result.add(0, "сто")
        2 -> result.add(0, "двести")
        3 -> result.add(0, "триста")
        4 -> result.add(0, "четыреста")
        5 -> result.add(0, "пятьсот")
        6 -> result.add(0, "шестьсот")
        7 -> result.add(0, "семьсот")
        8 -> result.add(0, "восемьсот")
        else -> result.add(0, "девятьсот")
    }
    return result.joinToString(separator = " ")
}
