package day13

import java.io.File

fun main() {
    val input = File("data/day13/sample.txt").readLines()
    val input2 = File("data/day13/input.txt").readText()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    val resultLunch = lunch(input2)
    println(resultLunch)
}

data class Price(val aX: Long, val aY: Long, val bX: Long, val bY: Long, val pX: Long, val pY: Long)

operator fun <T> List<T>.component6() = this[5]

fun solve(price: Price): Long {
    var min = Long.MAX_VALUE
   repeat(100) { y ->
       repeat(100) { x ->
           if (price.aX * (x + 1) + price.bX * (y + 1) == price.pX && price.aY * (x + 1) + price.bY * (y + 1) == price.pY) {
               println("found : ${x + 1} , ${y + 1}")
               val result = (x.toLong() + 1) * 3 + y + 1
               if (result < min) min = result
           }
       }
   }
    if (min == Long.MAX_VALUE) return 0 else return min
}

fun breakfast(input: List<String>) : Long {
    println(input)
    val buttons = input.windowed(4, 4)
    val regex = """-?\d+""".toRegex()
    val prices = buttons.map {
        val results = regex.findAll(it.joinToString(" ")).map { it.value.toLong() }.take(6).toList()
        var enabled = true
        val (aX, aY, bX, bY, pX, pY) = results
        Price(aX, aY, bX, bY, pX.toLong(), pY.toLong())
    }
    prices.forEach {
        //println(it)
        val result = solve(it)
        println(result)
    }
    val sum = prices.sumOf {
        solve(it)
    }
    println(sum)
    return -1
}

fun solve2(price: Price): Long {
    val ca = (price.pX * price.bY - price.pY * price.bX) / (price.aX * price.bY - price.aY * price.bX)
    val cb = (price.pX - price.aX * ca) / price.bX
    if (ca * price.aX + cb * price.bX == price.pX && ca * price.aY + cb * price.bY == price.pY) {
        println("$ca - $cb - solved for $price")
        return ca * 3 + cb
    } else return 0
}

fun lunch(input: String) : Long {
    println(input)
    val buttons = input.split("\n\n").map { it.split("\n") }
    println(buttons)
    val regex = """-?\d+""".toRegex()
    val prices = buttons.map {
        val results = regex.findAll(it.joinToString(" ")).map { it.value.toLong() }.take(6).toList()
        val (aX, aY, bX, bY, pX, pY) = results
        Price(aX, aY, bX, bY, pX + 10000000000000, pY + 10000000000000)
    }
    val sum = prices.sumOf {
        solve2(it)
    }
    println(sum)
    return -1
}