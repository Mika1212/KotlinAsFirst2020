@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    try {
        val data = str.split(" ")
        val dataMonth = mutableMapOf(
            "января" to 1,
            "февраля" to 2,
            "марта" to 3,
            "апреля" to 4,
            "мая" to 5,
            "июня" to 6,
            "июля" to 7,
            "августа" to 8,
            "сентября" to 9,
            "октября" to 10,
            "ноября" to 11,
            "декабря" to 12
        )
        if (data[1] !in dataMonth) return ""
        if (daysInMonth(dataMonth[data[1]]!!, data[2].toInt()) < data[0].toInt()) return ""
        if (data[2].toInt() < 0) return ""
        return String.format("%02d.%02d.%d", data[0].toInt(), dataMonth[data[1]], data[2].toInt())
    } catch (e: IndexOutOfBoundsException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    try {
        val a = digital.split(".")
        val namesOfMonths = listOf(
            "января",
            "февраля",
            "марта",
            "апреля",
            "мая",
            "июня",
            "июля",
            "августа",
            "сентября",
            "октября",
            "ноября",
            "декабря"
        )
        if (a.size != 3) return ""
        val result = mutableListOf<String>()
        for (i in 1..12) {
            if (a[1].toInt() > 12) return ""
            if (a[1].toInt() == i) {
                if (daysInMonth(i, a[2].toInt()) < a[0].toInt()) return ""

            }
        }

        result.add(a[0])
        if (result[0].toInt() < 10) result[0] = result[0].toInt().toString()
        if (a[1].toInt() < 1) return ""
        else
            result.add(namesOfMonths[a[1].toInt() - 1])
        if (a[2].toInt() < 0) return ""
        else
            result.add(a[2])
        return result.joinToString(separator = " ")
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    if (!phone.contains(Regex("""[ \-()0-9+]"""))) return ""
    if ("(" in phone && ")" !in phone || ")" in phone && "(" !in phone) return ""
    if (!phone.matches(Regex("""\+?\s*\d*\s*(\(?[ \-0-9+]+\)?)?\s*[ \-0-9+]*"""))) return ""
    return Regex("""[ \-()]""").split(phone).joinToString(separator = "")
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    try {
        val set = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '%', ' ')
        for (i in jumps) if (i !in set) return -1
        val a = jumps.split(" ").filter { it in "0".."9" }
        var a1 = a[0]
        for (length in a) if (length.toInt() > a1.toInt()) a1 = length
        return a1.toInt()
    } catch (e: IndexOutOfBoundsException) {
        return -1
    } catch (e: KotlinNullPointerException) {
        return -1
    }
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    try {
        val set = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '%', ' ', '+')
        for (i in jumps) if (i !in set) return -1
        val a = jumps.split(" ").filter(fun(it: String) = it in "0".."9" || it == "+")
        val checklist = mutableListOf<String>()
        if (a.isNotEmpty()) for (i in 0 until a.size) if (a[i] == "+") checklist.add(a[i - 1])
        var a1 = -1
        for (length in checklist)
            if (length.toInt() > a1)
                a1 = length.toInt()
        return a1
    } catch (e: IndexOutOfBoundsException) {
        return -1
    } catch (e: NumberFormatException) {
        return -1
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    try {
        val set = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', ' ', '+')
        val e = IllegalArgumentException()
        for (i in expression) if (i !in set) throw e
        val a = expression.split(" ")
        for (i in 0 until a.size) if (a[i].length > 1 && (a[i][0] == '+' || a[i][0] == '-')) throw e
        var result = a[0].toInt()
        for ((k, count) in a.withIndex()) {
            if (count == "+") result += a[k + 1].toInt()
            if (count == "-") result -= a[k + 1].toInt()
        }
        return result
    } catch (e: IllegalArgumentException) {
        throw e
    } catch (e: NumberFormatException) {
        throw e
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int =
    try {
        val a = str.split(" ")
        var j = 0
        for (i in 0 until a.size) {
            if (a[i].toLowerCase() == a[i + 1].toLowerCase()) break
            j += a[i].length + 1
        }
        j
    } catch (e: IndexOutOfBoundsException) {
        -1
    }


/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    try {
        val a = description.split(";")
        var maxCost = -1.0
        var name = ""
        for (itemCosts in a) {
            val b = itemCosts.trim().split(" ")
            if (b[1].toDouble() > maxCost) {
                maxCost = b[1].toDouble()
                name = b[0]
            }
        }
        return name
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var result = 0
    val romanMod = listOf(" ", roman, " ").joinToString(separator = "")
    val set = setOf('I', 'V', 'X', 'C', 'D', 'M', 'L', ' ')
    for (i in romanMod) if (i !in set) return -1
    if (roman.isEmpty()) return -1
    try {
        for (i in 1 until romanMod.length - 1) {
            when {
                romanMod[i] == 'C' && romanMod[i + 1] == 'M' -> result += 900
                romanMod[i] == 'C' && romanMod[i + 1] == 'D' -> result += 400
                romanMod[i] == 'X' && romanMod[i + 1] == 'C' -> result += 90
                romanMod[i] == 'X' && romanMod[i + 1] == 'L' -> result += 40
                romanMod[i] == 'I' && romanMod[i + 1] == 'X' -> result += 9
                romanMod[i] == 'I' && romanMod[i + 1] == 'V' -> result += 4
                romanMod[i] == 'M' && romanMod[i - 1] != 'C' -> result += 1000
                romanMod[i] == 'D' && romanMod[i + 1] != 'M' && romanMod[i - 1] != 'C' -> result += 500
                romanMod[i] == 'C' && romanMod[i + 1] != 'D' && romanMod[i + 1] != 'M' && romanMod[i - 1] != 'X' -> result += 100
                romanMod[i] == 'L' && romanMod[i - 1] != 'X' -> result += 50
                romanMod[i] == 'X' && romanMod[i + 1] != 'L' && romanMod[i + 1] != 'C' && romanMod[i - 1] != 'I' -> result += 10
                romanMod[i] == 'V' && romanMod[i - 1] != 'I' -> result += 5
                romanMod[i] == 'I' && romanMod[i + 1] != 'X' && romanMod[i + 1] != 'V' -> result += 1
            }
        }
        return result
    } catch (e: IndexOutOfBoundsException) {
        return -1
    } catch (e: NumberFormatException) {
        return -1
    }
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (commands.split(Regex("""[ \[\]+\-<>]""")).joinToString(separator = "") != "") throw IllegalArgumentException()
    var cursor = cells / 2
    val list = mutableListOf<Int>()

    for (i in 0 until cells)
        list.add(0)

    val a = mutableListOf<Int>()
    val mapOfBrackets = mutableMapOf<Int, Int>()

    try {
        for (i in commands.indices) {
            if (commands[i] == '[') a.add(i)
            if (commands[i] == ']') {
                mapOfBrackets.put(i, a.last())
                a.remove(a.last())
            }
        }
    } catch (e: NoSuchElementException) {
        throw IllegalArgumentException()
    }
    if (a.isNotEmpty()) throw IllegalArgumentException()

    var lim = limit
    var k = 0
    var bracketOpen = true
    var j =0

    while (lim > 0) {
        if (cursor > cells - 1 || cursor < 0) throw IllegalStateException()
        if (k > commands.length - 1) break
        if (bracketOpen) {
            if (commands[k] == '-') list[cursor] -= 1
            if (commands[k] == '+') list[cursor] += 1
            if (commands[k] == '<') cursor -= 1
            if (commands[k] == '>') cursor += 1
            if (commands[k] == '[' && list[cursor] == 0) {
                j = k
                bracketOpen = false
            }
            lim -= 1
        }
        if (commands[k] == ']' && !bracketOpen && mapOfBrackets[k] == j) bracketOpen = true
        if (commands[k] == ']' && list[cursor] != 0) k = mapOfBrackets[k]!!
        k += 1
    }
    return list
}