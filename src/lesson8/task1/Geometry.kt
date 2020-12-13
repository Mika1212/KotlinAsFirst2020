@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import kotlin.math.*

// Урок 8: простые классы
// Максимальное количество баллов = 40 (без очень трудных задач = 11)

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая (2 балла)
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val result = sqrt(sqr(center.x - other.center.x) + sqr(center.y - other.center.y))
        return if (result < 0.0) 0.0 else result
    }

    /**
     * Тривиальная (1 балл)
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = p.distance(center) - radius <= 1e-6
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя (3 балла)
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */

fun diameter(vararg points: Point): Segment {
    val result = mutableMapOf<Double, Pair<Point, Point>>()
    var max = 0.0
    val set = mutableSetOf<Point>()
    for (arg in points) {
        set.add(arg)
        for (arg1 in points)
            result[arg.distance(arg1)] = Pair(arg, arg1)
    }
    if (set.size < 2) throw IllegalArgumentException()
    max = points[0].distance(points[1])
    var a = points[0]
    var b = points[1]
    for ((distance, pair) in result)
        if (distance > max) {
            max = distance
            a = pair.first
            b = pair.second
        }
    println("$a,$b")
    var markA = false
    var markB = false
    for (i in 0..points.size) {
        if (points[i] == a) markA = true
        if (points[i] == b) markB = true
        if (markA) return Segment(a, b) else if (markB) return Segment(b, a)
    }
    return Segment(a, b)

}

/**
 * Простая (2 балла)
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val (a, b) = diameter
    val point = Point((a.x + b.x) * 0.5, (a.y + b.y) * 0.5)
    return Circle(point, point.distance(a))
}

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя (3 балла)
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point = Point(
        (other.b * cos(angle) - b * cos(other.angle)) / sin(angle - other.angle),
        (b * sin(other.angle) - other.b * sin(angle)) / sin(other.angle - angle)
    )


    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя (3 балла)
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = TODO()

/**
 * Средняя (3 балла)
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    var angle = atan((a.y - b.y) / (a.x - b.x))
    if (angle < 0) angle += PI
    if (angle == PI) angle = 0.0
    return Line(a, angle)
}

/**
 * Сложная (5 баллов)
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val halfPoint = Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0)
    val line = lineByPoints(a, b)
    val angle = if (line.angle + PI / 2.0 >= PI) line.angle + PI / 2.0 - PI
    else line.angle + PI / 2.0
    return Line(halfPoint, angle)
}

/**
 * Средняя (3 балла)
 *
 * Задан список из n окружностей на плоскости.
 * Найти пару наименее удалённых из них; расстояние между окружностями
 * рассчитывать так, как указано в Circle.distance.
 *
 * При наличии нескольких наименее удалённых пар,
 * вернуть первую из них по порядку в списке circles.
 *
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle = Circle(
    bisectorByPoints(c, b).crossPoint(bisectorByPoints(b, a)),
    bisectorByPoints(c, b).crossPoint(bisectorByPoints(b, a)).distance(a)
)

/**
 * Очень сложная (10 баллов)
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle {
    if (points.isEmpty()) throw java.lang.IllegalArgumentException()
    if (points.size == 1) return Circle(points[0], 0.0)
    var circle = Circle(Point(0.0, 0.0), Double.MAX_VALUE)
    var circle1 = Circle(Point(0.0, 0.0), 0.0)
    var mark = true

    circle1 = circleByDiameter(diameter(*points))
    for (point in points)
        if (!circle1.contains(point)) mark = false
    if (mark) circle = circle1

    for (i in 0..points.size - 3) {
        for (j in i + 1..points.size - 2) {
            loop1@ for (l in j + 1 until points.size) {
                if (points[i] == points[j] || points[i] == points[l] || points[j] == points[l]) continue@loop1
                if (points[i].x == points[j].x && points[j].x == points[l].x ||
                    points[i].y == points[j].y && points[j].y == points[l].y
                ) continue@loop1
                circle1 = circleByThreePoints(points[i], points[j], points[l])

                mark = true
                for (point in points)
                    if (!circle1.contains(point)) mark = false

                if (mark && circle1.radius < circle.radius)
                    circle = circle1
            }
        }
    }
    return circle
}
