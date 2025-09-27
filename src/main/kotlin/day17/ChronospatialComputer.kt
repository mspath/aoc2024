package day17

import java.io.File

fun main() {
    val input = File("data/day17/sample.txt").readText()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

fun breakfast(input: String): Int {
    val (registers, program) = input.split("\n\n")
    println(registers)
    println(program)
    val (a, b, c) = registers.split("\n").map { it.substringAfter(": ").toInt() }
    println(a)
    println(b)
    println(c)
    val uff = -1
    return uff
}