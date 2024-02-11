package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class Decimal_name_probe_test {

    @Test
    fun test0() {
        var page = Page("")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test1() {
        var page = Page("123")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test2() {
        var page = Page("abc")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(3, page.letter.position)
    }

    @Test
    fun test3() {
        var page = Page("a123")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }


    @Test
    fun test4() {
        var page = Page("_abc")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(4, page.letter.position)
    }

    @Test
    fun test5() {
        var page = Page("_")
        var parser = Parser()
        parser.probe_name(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(1, page.letter.position)
    }

}