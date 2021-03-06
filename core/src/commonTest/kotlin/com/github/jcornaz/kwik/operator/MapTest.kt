package com.github.jcornaz.kwik.operator

import com.github.jcornaz.kwik.AbstractGeneratorTest
import com.github.jcornaz.kwik.Generator
import com.github.jcornaz.kwik.map
import kotlin.test.Test
import kotlin.test.assertEquals

class MapTest : AbstractGeneratorTest() {
    override val generator: Generator<*> = Generator.create { it.nextInt() }.map { it.toString() }

    @Test
    fun applyTransform() {
        val gen: Generator<Pair<Int, String>> = Generator.create { it.nextInt() }.map { it to it.toString() }

        gen.randoms(12).take(200).forEach { (i, s) ->
            assertEquals(i, s.toInt())
        }
    }

    @Test
    fun produceAsManyDifferentValues() {
        val source = Generator.create { it.nextInt() }
        val transformed: Generator<String> = source.map { it.toString() }

        val sourceResults = source.randoms(37).take(200).toSet()
        val transformedResults = transformed.randoms(37).take(200).toSet()

        assertEquals(sourceResults.size, transformedResults.size)
    }
}
