package day2

import day1.splitWhitespace
import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("data/day2/input.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

fun List<Int>.ordered() : Boolean = this == this.sorted() || this.reversed() == this.reversed().sorted()

fun List<Int>.safe() : Boolean {
    val pairs = this.windowed(2)
    return pairs.all { abs(it.first() - it.last()) in 1..3 }
}

fun breakfast(input: List<String>) : Int {
    val reports = input.map { it.splitWhitespace().map { d -> d.toInt() } }
    val safe = reports.filter { it.ordered() && it.safe()}
    return safe.size
}

fun List<Int>.generateReports() : List<List<Int>> {
    val lists : MutableList<List<Int>> = mutableListOf(this)
    repeat(this.size) { i ->
        val l = this.toMutableList()
        l.removeAt(i)
        lists.add(l.toList())
    }
    return lists
}

fun lunch(input: List<String>) : Int {
    val reports = input.map { it.splitWhitespace().map { d -> d.toInt() } }
    val safe = reports.filter { report ->
        val possibilities = report.generateReports()
        possibilities.any { it.ordered() && it.safe()}
    }
    return safe.size
}

fun testRedNosedReports() {
    println("running tests for day 2")
    val input = File("data/day2/input.txt").readLines()
    check(breakfast(input) == 246) { "breakfast failed" }
    check(lunch(input) == 318) { "lunch failed" }
    println("2 tests ok")
}
