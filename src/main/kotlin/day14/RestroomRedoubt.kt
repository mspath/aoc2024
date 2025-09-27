package day14

import day13.Price
import day13.component6
import java.io.File

fun main() {
    val input = File("data/day14/input.txt").readLines()
    val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

data class Robot(var pX: Int, var pY: Int, val vX: Int, val vY: Int)

fun Robot.isQ1(widthBreak: Int, heightBreak: Int) =
    this.pX < widthBreak && this.pY < heightBreak

fun Robot.isQ2(widthBreak: Int, heightBreak: Int) =
    this.pX > widthBreak && this.pY < heightBreak

fun Robot.isQ3(widthBreak: Int, heightBreak: Int) =
    this.pX < widthBreak && this.pY > heightBreak

fun Robot.isQ4(widthBreak: Int, heightBreak: Int) =
    this.pX > widthBreak && this.pY > heightBreak

fun printRobots(robots: List<Robot>, width: Int, height: Int) {
    println()
    repeat(height) { y ->
        repeat(width) { x ->
            val count = robots.count {
                it.pX == x && it.pY == y
            }
            if (count == 0) print('.')
            else print(count)
        }
        println()
    }
}

fun printoutRobots(robots: List<Robot>, width: Int, height: Int) {
    val output = File("data/day14/out.txt")
    output.appendText("\n")
    println()
    repeat(height) { y ->
        repeat(width) { x ->
            val count = robots.count {
                it.pX == x && it.pY == y
            }
            if (count == 0) output.appendText(".")
            else output.appendText(count.toString())
        }
        output.appendText("\n")
    }
}

fun breakfast(input: List<String>) : Int {
    //val output = File("data/day14/out.txt")
    val width = 101
    val height = 103
    val widthBreak = (width) / 2
    val heightBreak = (height) / 2
    val regex = """-?\d+""".toRegex()
    val robots = input.map {
        val results = regex.findAll(it).map { d -> d.value.toInt() }.take(4).toList()
        val (pX, pY, vX, vY) = results
        Robot(pX + width, pY + height, vX, vY)
    }
    println("--------")
    repeat(800) {
        robots.forEach {
            it.pX = (it.pX + it.vX + 2 * width) % width
            it.pY = (it.pY + it.vY + 2 * height) % height
        }
        if (it > 500) {
            println("---")
            println(it + 1)
            printoutRobots(robots, width, height)
            //printRobots(robots, width, height)
        }
    }
    val r1 = robots.filter { it.isQ1(widthBreak, heightBreak) }
    val r2 = robots.filter { it.isQ2(widthBreak, heightBreak) }
    val r3 = robots.filter { it.isQ3(widthBreak, heightBreak) }
    val r4 = robots.filter { it.isQ4(widthBreak, heightBreak) }
    //println(r1.count() * r2.count() * r3.count() * r4.count())
    //printRobots(robots, width, height)
    return -1
}