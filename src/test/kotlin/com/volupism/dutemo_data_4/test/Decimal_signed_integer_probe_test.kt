package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class Decimal_signed_integer_probe_test {

    @Test
    fun test0() {
        var page = Page("")
        var parser = Parser()
        parser.probe_decimal_signed_integer(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test1() {
        var page = Page("123")
        var parser = Parser()
        parser.probe_decimal_signed_integer(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test2() {
        var page = Page("-123")
        var parser = Parser()
        parser.probe_decimal_signed_integer(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }

    @Test
    fun test3() {
        var page = Page("-123a")
        var parser = Parser()
        parser.probe_decimal_signed_integer(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }

    @Test
    fun test4() {
        var page = Page("+123a")
        var parser = Parser()
        parser.probe_decimal_signed_integer(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }

}