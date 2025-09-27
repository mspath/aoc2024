package day7

import java.io.File
import java.lang.Thread.sleep
import kotlin.math.E

fun main() {
    val input = File("data/day7/input.txt").readLines()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

data class Equation(val result: Long, val parts: List<Long>)

fun generate(tmp: List<Long>, rest: List<Long>): List<Long> {
    if (rest.isEmpty()) return tmp
    if (tmp.isEmpty()) return generate(rest.subList(0, 1), rest.subList(1, rest.size))
    val newTmp = tmp.map { it + rest.first() } + tmp.map { it * rest.first() }
    return generate(newTmp, rest.subList(1, rest.size))
}

fun append(a: Long, b: Long) = (a.toString() + b.toString()).toLong()

fun generate2(tmp: List<Long>, rest: List<Long>): List<Long> {
    if (rest.isEmpty()) return tmp
    if (tmp.isEmpty()) return generate2(rest.subList(0, 1), rest.subList(1, rest.size))
    val newTmp = tmp.map { it + rest.first() } + tmp.map { it * rest.first() } + tmp.map { append(it, rest.first()) }
    return generate2(newTmp, rest.subList(1, rest.size))
}

fun breakfast(input: List<String>) : Long {
    println("---")
    val data = input.map {
        val result = it.substringBefore(":").toLong()
        val parts = it.substringAfter(": ").split(" ").map { it.toLong() }
        Equation(result, parts)
    }
    println(data)
    var s = 0L
    data.forEach {
        val results = generate(emptyList(), it.parts)
        println(results)
        if (results.contains(it.result)) s += it.result
    }
    println(s)
    return -1
}

fun lunch(input: List<String>) : Long {
    println("---")
    val data = input.map {
        val result = it.substringBefore(":").toLong()
        val parts = it.substringAfter(": ").split(" ").map { it.toLong() }
        Equation(result, parts)
    }
    println(data)
    var s = 0L
    data.forEach {
        val results = generate2(emptyList(), it.parts)
        println(results)
        if (results.contains(it.result)) s += it.result
    }
    println(s)
    return -1
}