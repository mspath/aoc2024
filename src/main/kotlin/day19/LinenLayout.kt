package day19

import day1.splitWhitespace
import java.io.File

fun main() {
    val input = File("data/day19/input.txt").readText()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
}

fun processToken(pattern: String, tokens: List<String>, cache: MutableMap<String, Boolean>): Boolean {

    if (pattern.isEmpty()) return true

    return cache.getOrPut(pattern) {
        tokens.any {
            pattern.startsWith(it) && processToken(pattern.substringAfter(it), tokens, cache)
        }
    }
}

fun processToken2(pattern: String, tokens: List<String>, cache: MutableMap<String, Int>): Int {

    if (pattern.isEmpty()) return 1

    return cache.getOrPut(pattern) {
        tokens.sumOf {
            processToken2(pattern.substringAfter(it), tokens, cache)
        }
    }
}

fun breakfast(input: String): Int {
    val (inputTokens, inputPatterns) = input.split("\n\n")
    val tokens = inputTokens.split(", ")
    val patterns = inputPatterns.splitWhitespace()
    val cache: MutableMap<String, Boolean> = mutableMapOf()
    println(tokens)
    println(patterns)
    patterns.forEach {
        println(it)
        println(processToken(it, tokens, cache))
    }
    val result = patterns.count {
        processToken(it, tokens, cache)
    }
    println(result)
    val uff = -1
    return uff
}
