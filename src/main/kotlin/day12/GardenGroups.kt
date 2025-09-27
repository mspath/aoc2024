package day12

import day7.Equation
import day7.generate
import java.io.File

fun main() {
    val input = File("data/day12/sample.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

data class Parcel(val x: Int, val y: Int)

data class Area(val id: Parcel, val parcels: MutableList<Parcel>)

fun breakfast(input: List<String>) : Long {
    println(input)
    val garden = input.flatMapIndexed { y: Int, s: String ->
        s.mapIndexed { x, c ->
            Parcel(x, y) to c
        }
    }.toMap()
    println(garden)

    return -1
}