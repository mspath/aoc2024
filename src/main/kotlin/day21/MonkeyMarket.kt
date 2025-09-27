package day21

import java.io.File

fun main() {
    val input = File("data/day21/sample.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

fun transform(number: Int): Int {
    val n1 = number * 64
    return n1
}

fun breakfast(input: List<String>): Int {
    println(input)
    val numbers = input.map { it.toInt() }
    println(numbers)
    val uff = -1
    return uff
}