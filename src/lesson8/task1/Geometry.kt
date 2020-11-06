@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import lesson4.task1.center
import java.lang.Double.MAX_VALUE
import java.text.DecimalFormat
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
    fun distance(other: Circle): Double = if (sqrt(sqr(center.x - other.center.x) + sqr(center.y - other.center.y))
        < radius + other.radius
    )
        0.0 else
        sqrt(sqr(center.x - other.center.x) + sqr(center.y - other.center.y)) - radius - other.radius

    /**
     * Тривиальная (1 балл)
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = radius >= p.distance(center)
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
        for (arg1 in points) {
            result[arg.distance(arg1)] = Pair(arg, arg1)
            max = arg.distance(arg1)
        }
    }
    if (set.size < 2) throw IllegalArgumentException()
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
    val point = Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0)
    return Circle(point, point.distance(b))
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
        ((other.b * cos(angle) - b * cos(other.angle)) / sin(angle - other.angle) * sin(other.angle) + other.b) / cos(
            other.angle
        )
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
fun lineByPoints(a: Point, b: Point): Line = TODO()

/**
 * Сложная (5 баллов)
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val a1 = if (a.x <= b.x) a else b
    val b1 = if (a1 == a) b else a
    val halfPoint = Point((a1.x + b1.x) / 2.0, (a1.y + b1.y) / 2.0)
    var angleWithHorizon = if (a1.y == b1.y) PI / 2.0 else atan((b1.y - a1.y) / (b1.x - a1.x)) + PI / 2
    if (angleWithHorizon == PI) angleWithHorizon = 0.0
    return Line(
        halfPoint, angleWithHorizon
    )
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
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val scale = 10.0.pow(14.0)
    val a1 = Point(a.x / scale * scale, a.y / scale * scale)
    val b1 = Point(b.x / scale * scale, b.y / scale * scale)
    val c1 = Point(c.x / scale * scale, c.y / scale * scale)
    val x = bisectorByPoints(a1, b1)
    val y = bisectorByPoints(b1, c1)
    val z = bisectorByPoints(a1, c1)
    val center1 = x.crossPoint(z)
    val center2 = x.crossPoint(y)
    val center = if (center1.distance(a1) < center2.distance(a1)) center1 else center2
    return Circle(center, center.distance(a1))
}

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
    var i = 0
    for (point in points)
        i++
    if (i == 0) throw IllegalArgumentException()
    if (i == 1) return Circle(points[0], 0.0)

    val circle = circleByDiameter(diameter(*points))
    println(circle)
    var pointChange = points[0]
    var center = Point(circle.center.x, circle.center.y)
    var radius = circle.radius
    while (true) {
        val circle1 = Circle(center, radius)
        val pointCheck = pointChange
        for (point in points)
            if (!circle1.contains(point)) {
                pointChange = point
                println(point)
                center = Point((circle1.center.x + point.x) / 2.0, (circle.center.y + point.y) / 2.0)
                radius = point.distance(circle1.center)
                break
            }
        println(circle1)
        if (pointChange == pointCheck) return Circle(center, radius)
    }
}
