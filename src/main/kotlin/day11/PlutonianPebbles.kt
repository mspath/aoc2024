package day8

import java.io.File

fun main() {
    val input = File("data/day8/sample.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

fun generatePair(a: Pair<Int, Int>, b: Pair<Int, Int>): List<Pair<Int, Int>> {
    return emptyList()
}

fun findSymmetricPoints(p1: Pair<Int, Int>, p2: Pair<Int, Int>): List<Pair<Int, Int>> {
    val dx = p2.first - p1.first
    val dy = p2.second - p1.second

    val point3 = Pair(p1.first - dx, p1.second - dy)
    val point4 = Pair(p2.first + dx, p2.second + dy)

    return listOf(point3, point4)
}

fun generateAllPairs(values: List<Pair<Int, Int>>): List<Pair<Pair<Int,Int>, Pair<Int,Int>>> {
    val pairs = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int,Int>>>()

    for (a in values) {
        for (b in values) {
            if (a != b) {
                pairs.add(Pair(a, b))
            }
        }
    }

    return pairs.toList()
}

fun breakfast(input: List<String>) : Int {
    println(input)
    val antennas: MutableMap<Char, MutableSet<Pair<Int, Int>>> = mutableMapOf()
    println(antennas)
    input.forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            if (c != '.') {
                antennas.computeIfAbsent(c) { mutableSetOf() }.add(Pair(x, y))
            }
        }
    }
    println(antennas)
    val s = antennas.toList().first().second.toList()
    val pairs = generateAllPairs(s)
    println(pairs)
    val ps = pairs.first()
    val psr = findSymmetricPoints(ps.first, ps.second)
    println(psr)
    val all = antennas.values.toList().map {
        val pai = it.toList()
        val pais = generateAllPairs(pai)
        val gpais = pais.map {
            findSymmetricPoints(it.first, it.second)
        }.flatten()
    }
    println(all)
    return -1
}