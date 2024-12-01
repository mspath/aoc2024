package day1

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("data/day1/input.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

fun String.splitWhitespace() = this.split("\\s+".toRegex())

fun breakfast(input: List<String>) : Int {
    val locations = input.map { it.splitWhitespace() }
    val left = locations.map { it.first().toInt() }.sorted()
    val right = locations.map { it.last().toInt() }.sorted()
    val pairs = left.zip(right)
    val distances = pairs.sumOf { abs(it.first - it.second) }
    return distances
}

fun lunch(input: List<String>) : Int {
    val locations = input.map { it.splitWhitespace() }
    val left = locations.map { it.first().toInt() }.sorted()
    val right = locations.map { it.last().toInt() }.sorted()
    val counter : MutableMap<Int, Int> = mutableMapOf()
    right.forEach { key ->
        val count = counter.getOrDefault(key, 0)
        counter[key] = count + 1
    }
    val score = left.sumOf { it * counter.getOrDefault(it, 0) }
    return score
}

fun testHistorianHysteria() {
    println("running tests for day 1")
    val input = File("data/day1/input.txt").readLines()
    check(breakfast(input) == 2086478) { "breakfast failed" }
    check(lunch(input) == 24941624) { "lunch failed" }
    println("2 tests ok")
}
