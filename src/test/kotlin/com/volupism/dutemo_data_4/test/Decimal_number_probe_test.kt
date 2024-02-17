package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Lexer
import com.volupism.dutemo_data_4.Page
import kotlin.test.Test
import kotlin.test.assertEquals

class Decimal_number_probe_test {

    @Test
    fun test0() {
        var page = Page("")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test1() {
        var page = Page("123")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(3, page.letter.position)
    }

    @Test
    fun test2() {
        var page = Page("-123.456")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)

        assertEquals(1, page.token_s.fini())
        assertEquals(8, page.letter.position)
    }

    @Test
    fun test3() {
        var page = Page("+123.456")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        assertEquals(1, page.token_s.fini())
        assertEquals(8, page.letter.position)
    }


    @Test
    fun test4() {
        var page = Page("123.456")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(7, page.letter.position)
    }

    @Test
    fun test5() {
        var page = Page("123,456")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(7, page.letter.position)
    }

    @Test
    fun test6() {
        var page = Page("+123a")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }

    @Test
    fun test7() {
        var page = Page("+123.")
        var lexer = Lexer()
        lexer.probe_decimal_number(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(5, page.letter.position)
    }

}