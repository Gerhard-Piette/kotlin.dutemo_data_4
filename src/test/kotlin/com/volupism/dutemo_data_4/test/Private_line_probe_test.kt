package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class Private_line_probe_test {

    @Test
    fun test0() {
        var page = Page("")
        var parser = Parser()
        parser.probe_private_line(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(0, page.token_s.fini())
        assertEquals(0, page.letter.position)
    }

    @Test
    fun test1() {
        var page = Page("#")
        var parser = Parser()
        parser.probe_private_line(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(1, page.letter.position)
    }

    @Test
    fun test2() {
        var page = Page("#\n")
        var parser = Parser()
        parser.probe_private_line(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(1, page.letter.position)
    }

    @Test
    fun test3() {
        var page = Page("#a")
        var parser = Parser()
        parser.probe_private_line(page, true)
        //  assertEquals(true, dutemo_file.found)
        assertEquals(1, page.token_s.fini())
        assertEquals(2, page.letter.position)
    }

}