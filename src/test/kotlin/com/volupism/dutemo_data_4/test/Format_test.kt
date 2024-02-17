package com.volupism.dutemo_data_4.test

import com.volupism.dutemo_data_4.Lexer
import com.volupism.dutemo_data_4.Page
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Format_test {

    fun read_file(file_name: String): String {
        return Files.readString(Path.of("src/test/resources").resolve(file_name), StandardCharsets.UTF_8)
    }

    @Test
    fun test0() {
        var text = read_file("format_0.dutemo.u")
        var page = Page(text)
        var lexer = Lexer()
        lexer.probe(page)
        println(page.get_token_string())
        assertEquals(text, page.get_formatted_dutemo())
    }

}