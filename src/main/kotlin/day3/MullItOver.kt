package day3

import java.io.File

fun main() {
    val input = File("data/day3/input.txt").readText()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)

    val resultLunch = lunch(input)
    println(resultLunch)
}

fun breakfast(input: String) : Int {
    val regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
    val results = regex.findAll(input)
    val result = results.sumOf {
        val line = it.value
        val l = line.substringAfter("(").substringBefore(",")
        val r = line.substringAfter(",").substringBefore(")")
        l.toInt() * r.toInt()
    }
    return result
}

fun lunch(input: String) : Int {
    val regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()
    val results = regex.findAll(input).map { it.value }
    var enabled = true
    var result = 0
    results.forEach { line ->
        if (enabled && line.startsWith("mul")) {
            val l = line.substringAfter("(").substringBefore(",")
            val r = line.substringAfter(",").substringBefore(")")
            result += l.toInt() * r.toInt()
        }
        else if (line.startsWith("don't()")) {
            enabled = false
        }
        else if (line.equals("do()")) {
            enabled = true
        }
    }
    return result
}
