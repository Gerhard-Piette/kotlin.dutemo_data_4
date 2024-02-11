package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class Keyword_probe_test {

    @Test
    fun test0() {
        var page = Page("abc0")
        var parser = Parser()
        parser.probe_dutemo_keyword(page, true)
        // assertTrue(dutemo_file.found)
        assertEquals(0, page.token_s.fini())
    }

    @Test
    fun test1() {
        var page = Page(":aa")
        var parser = Parser()
        parser.probe_dutemo_keyword(page, false)
        // assertTrue(dutemo_file.found)
        assertEquals(0, page.token_s.fini())
    }

    @Test
    fun test2() {
        var page = Page(":aa")
        var parser = Parser()
        parser.probe_dutemo_keyword(page, true)
        // assertTrue(dutemo_file.found)
        assertEquals(1, page.token_s.fini())
    }

}