package day1

import java.io.File

fun main() {
    val input = File("data/day1/input.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

fun breakfast(input: List<String>) : Int {
    val result = input.size
    return result
}

fun lunch(input: List<String>) : Int = -1