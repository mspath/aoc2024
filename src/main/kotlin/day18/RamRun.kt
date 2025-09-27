package day18

import java.io.File

fun main() {
    val input = File("data/day18/sample.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

fun breakfast(input: List<String>): Int {
    println(input)
    val uff = -1
    return uff
}