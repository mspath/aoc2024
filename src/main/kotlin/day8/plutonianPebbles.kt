package day11

import java.io.File

fun main() {
    val input = File("data/day11/input.txt").readText()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

fun even(number: Long): Boolean {
    val numDigits = number.toString().length
    return numDigits % 2 == 0
}

fun splitInteger(number: Long): List<Long> {
    val numString = number.toString()
    val length = numString.length
    val midpoint = length / 2

    val firstHalf = numString.substring(0, midpoint).toLong()
    val secondHalf = numString.substring(midpoint).toLong()

    return listOf(firstHalf, secondHalf)
}

fun transformStones(stones: List<Long>): List<Long> {
    return stones.flatMap { stone ->
        if (stone == 0L) listOf(1L)
        else if (even(stone)) splitInteger(stone)
        else listOf(stone * 2024)
    }
}

fun transformStone(stone: Long): List<Long> {
    if (stone == 0L) return listOf(1L)
    else if (even(stone)) return splitInteger(stone)
    else return listOf(stone * 2024)
}

fun solveStone(stone: Long, level: Int, cache: MutableMap<Pair<Long, Int>, Long>): Long {
    if (level == 0) return 1
    println("stone: $stone level: $level")
    cache[Pair(stone, level)]?.let { value ->
        println("cache: $value size: ${cache.size}")
        return value
    }
    val stones = transformStone(stone)
    stones.forEach {
        cache[Pair(it, level - 1)] = solveStone(it, level - 1, cache)
    }
    return stones.sumOf {
        solveStone(it, level - 1, cache)
    }
}

fun breakfast(input: String) : Long {
    println(input)
    var stones: List<Long> = emptyList()
    stones = input.split(" ").map { it.toLong() }.toList()
    println(stones)
    repeat(25) {
        stones = transformStones(stones)
    }
    println(stones.size)
    return -1L
}

fun lunch(input: String) : Long {
    println(input)
    val stones: List<Long> = input.split(" ").map { it.toLong() }.toList()
    println(stones)
    println(stones.size)
    val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()
    val result = stones.sumOf { solveStone(it, 75, cache) }
    println(result)
    println(cache.size)
    return -1L
}


