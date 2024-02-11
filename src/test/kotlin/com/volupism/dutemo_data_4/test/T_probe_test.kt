package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Key
import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.String_parser
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class T_probe_test {

    fun read_file(file_name: String): String {
        return Files.readString(Path.of("src/test/resources").resolve(file_name), StandardCharsets.UTF_8)
    }

    @Test
    fun test0() {
        var text = read_file("t_data_0.dutemo.u")
        var page = Page(text)
        var stringparser = String_parser()
        stringparser.probe_t_data(page, 0, Key._Colon_t)
        assertEquals("a\nb\n", page.token_s.get()!!.text)
    }

    @Test
    fun test1() {
        var text = read_file("t_data_1.dutemo.u")
        var page = Page(text)
        var stringparser = String_parser()
        stringparser.probe_t_data(page, 0, Key._Colon_t)
        assertEquals("a\nb\n", page.token_s.get()!!.text)
    }

}