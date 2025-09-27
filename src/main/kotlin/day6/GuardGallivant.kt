package day6

import java.io.File

fun main() {
    val input = File("data/day6/input.txt").readLines()
    val resultBreakfast = breakfast(input)
    println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

enum class Direction(val next: Pair<Int, Int>) {
    NORTH(Pair(0, -1)),
    SOUTH(Pair(0, 1)),
    EAST(Pair(1, 0)),
    WEST(Pair(-1, 0));
}

fun Direction.shift(): Direction =
    when(this) {
        Direction.NORTH -> Direction.EAST
        Direction.EAST -> Direction.SOUTH
        Direction.SOUTH -> Direction.WEST
        Direction.WEST -> Direction.NORTH
    }

data class Guard(val positions: Pair<Int, Int>, val direction: Direction)

data class Area(val width: Int, val height: Int, val blocks: Set<Pair<Int, Int>>)

fun Area.isOver(guard: Guard): Boolean {
    val projected = guard.positions + guard.direction.next
    return projected.first !in 0 until this.width ||
            projected.second !in 0 until this.height
}

fun Area.next(guard: Guard): Guard {
    val projected = guard.positions + guard.direction.next
    if (this.blocks.contains(projected)) return guard.copy(direction = guard.direction.shift())
    else return guard.copy(positions = projected)
}

fun breakfast(input: List<String>) : Int {
    val height = input.size
    val width = input.first().length
    println("---")
    println(height)
    println(width)
    println("---")
    val blocks : MutableSet<Pair<Int, Int>> = mutableSetOf()
    val area = Area(width, height, blocks)
    var guard : Guard = Guard(Pair(-1, -1), Direction.NORTH)
    val visited : MutableSet<Pair<Int, Int>> = mutableSetOf()
    input.forEachIndexed { y: Int, s: String ->
        s.forEachIndexed { x, c ->
            if(c == '#') blocks.add(Pair(x, y))
            if(c == '^') guard = Guard(Pair(x, y), Direction.NORTH)
        }
    }
    println(blocks.toString())
    println(guard)
    println("---")
    println(area)
    println(area.isOver(guard))
    println(area.isOver(Guard(Pair(5, 9), Direction.SOUTH)))
    println(Direction.SOUTH.shift())
    println("---")
    visited.add(guard.positions)
    while (!area.isOver(guard)) {
        println(guard)
        guard = area.next(guard)
        visited.add(guard.positions)
    }
    println(visited.size)
    return -1
}

fun lunch(input: List<String>) : Int {
    return -1
}