package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Lexer
import com.volupism.dutemo_data_4.Page
import kotlin.test.Test
import kotlin.test.assertEquals

class Keyword_probe_test {

    @Test
    fun test0() {
        var page = Page("abc0")
        var lexer = Lexer()
        lexer.probe_dutemo_keyword(page, true)
        // assertTrue(dutemo_file.found)
        assertEquals(0, page.token_s.fini())
    }

    @Test
    fun test1() {
        var page = Page(":aa")
        var lexer = Lexer()
        lexer.probe_dutemo_keyword(page, false)
        // assertTrue(dutemo_file.found)
        assertEquals(0, page.token_s.fini())
    }

    @Test
    fun test2() {
        var page = Page(":aa")
        var lexer = Lexer()
        lexer.probe_dutemo_keyword(page, true)
        // assertTrue(dutemo_file.found)
        assertEquals(1, page.token_s.fini())
    }

}