package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Lexer
import com.volupism.dutemo_data_4.Page
import kotlin.test.Test
import kotlin.test.assertEquals

class Decimal_integer_probe_test {

    @Test
    fun test0() {
        var page = Page("")
        var lexer = Lexer()
        var found = lexer.probe_decimal_integer(page, true)
        assertEquals(false, found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test1() {
        var page = Page("123")
        var lexer = Lexer()
        var found = lexer.probe_decimal_integer(page, true)
        assertEquals(true, found)
        assertEquals(1, page.token_s.fini())
        assertEquals(3, page.letter.position)
    }

    @Test
    fun test2() {
        var page = Page("-123")
        var lexer = Lexer()
        var found = lexer.probe_decimal_integer(page, true)
        assertEquals(false, found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test3() {
        var page = Page("123a")
        var lexer = Lexer()
        var found = lexer.probe_decimal_integer(page, true)
        assertEquals(true, found)
        assertEquals(1, page.token_s.fini())
        assertEquals(3, page.letter.position)
    }

}