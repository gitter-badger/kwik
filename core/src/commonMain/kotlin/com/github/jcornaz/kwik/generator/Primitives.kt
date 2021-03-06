package com.github.jcornaz.kwik.generator

import com.github.jcornaz.kwik.Generator
import com.github.jcornaz.kwik.withSamples
import kotlin.random.nextInt
import kotlin.random.nextLong

/**
 * Returns a generator of integer, all values being generated between [min] and [max] (inclusive)
 *
 * Few edge cases (namely, -1, 0, 1, [min] and [max]) are generated more often than the other values
 */
fun Generator.Companion.ints(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Generator<Int> {
    require(max > min) { "Max must be greater than min but min was $min and max was $max" }

    val range = min..max
    val samples = listOf(0, 1, -1, min, max).filter { it in range }

    return create { it.nextInt(range) }.withSamples(samples)
}

/**
 * Returns a generator of longs, all values being generated between [min] and [max] (inclusive)
 *
 * Few edge cases (namely, -1, 0, 1, [min] and [max]) are generated more often than the other values
 */
fun Generator.Companion.longs(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): Generator<Long> {
    require(max > min) { "Max must be greater than min but min was $min and max was $max" }

    val range = min..max
    val samples = listOf(0, 1, -1, min, max).filter { it in range }

    return create { it.nextLong(range) }.withSamples(samples)
}

/**
 * Returns a generator of floats, all values being generated between [from] and [until] (exclusive)
 *
 * Few edge cases (namely, -1, 0, 1) are generated more often than the other values
 */
fun Generator.Companion.floats(
    from: Float = Float.NEGATIVE_INFINITY,
    until: Float = Float.POSITIVE_INFINITY
): Generator<Float> {
    require(until > from) { "Until must be greater than from but from was $from and until was $until" }

    val range = from..until
    val samples = listOf(0f, 1f, -1f).filter { it in range }
    val generate = create { it.nextFloat() * (until - from) + from }

    return if (samples.isEmpty()) generate else generate.withSamples(samples)
}

/**
 * Returns a generator of floats, all values being generated between [from] and [until] (exclusive)
 *
 * Few edge cases (namely, -1, 0, 1) are generated more often than the other values
 */
fun Generator.Companion.doubles(
    from: Double = Double.NEGATIVE_INFINITY,
    until: Double = Double.POSITIVE_INFINITY
): Generator<Double> {
    require(until > from) { "Until must be greater than from but from was $from and until was $until" }

    val range = from..until
    val samples = listOf(0.0, 1.0, -1.0).filter { it in range }
    val generator = create { it.nextDouble(from, until) }

    return if (samples.isEmpty()) generator else generator.withSamples(samples)
}

/**
 * Returns a generator of booleans
 */
fun Generator.Companion.booleans(): Generator<Boolean> = create { it.nextBoolean() }
