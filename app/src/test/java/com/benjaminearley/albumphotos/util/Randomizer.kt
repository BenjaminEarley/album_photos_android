package com.benjaminearley.albumphotos.util

import kotlin.random.Random

typealias Randomizer<A> = (Random) -> A

fun String.Companion.randomizer(from: Int, until: Int): Randomizer<String> =
    { random ->
        (from until until)
            .map { random.nextInt(32, 127).toChar() }
            .joinToString(separator = "")
    }

fun Int.Companion.randomizer(from: Int, until: Int): Randomizer<Int> =
    { random -> random.nextInt(from, until) }


fun <A> Randomizer<A>.listRandomizer(from: Int, until: Int): Randomizer<List<A>> =
    { random -> (0..Int.randomizer(from, until)(random)).map { invoke(random) } }

fun <A : Any> Randomizer<A>.sequenceRandomizer(): Randomizer<Sequence<A>> =
    { random -> generateSequence { invoke(random) } }


