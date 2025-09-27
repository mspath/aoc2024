package day5

import day1.splitWhitespace
import java.io.File

fun main() {
    val input = File("data/day5/input.txt").readText()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

fun breakfast(input: String) : Int {
    val parts = input.split("\n\n")
    val instructions = parts.first()
        .splitWhitespace()
        .map { it.split("|").map { it.toInt()} }
    println(instructions)
    val prints = parts.last().splitWhitespace().map { it.split(",").map { it.toInt() } }
    println(prints)
    val map = mutableMapOf<Int, MutableSet<Int>>()
    for ((key, value) in instructions) { map.computeIfAbsent(key) { mutableSetOf() }.add(value)
    }
    println(map)
    var result = 0
    prints.forEach {
        println("checking: " + it)
        var okk = true
        it.mapIndexed { index, i ->
            //println("checking: " + i)
            val previous = it.subList(0, index)
            //println("previous: " + previous)
            val ok = previous.all {
                val rules = map.get(it) ?: emptySet<Int>()
                rules.contains(i)
            }
            //println("ok: " + ok)
            if (ok == false) okk = false
        }
        //println("okk: " + okk)
        val m = it[it.size/2]

        if (okk) {
            println(it)
            println(m)
            result += m }
    }
    println(result)
    return -1
}

fun lunch(input: List<String>) : Int {
    return -1
}