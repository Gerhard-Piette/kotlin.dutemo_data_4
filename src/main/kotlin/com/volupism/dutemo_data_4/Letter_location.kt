package com.volupism.dutemo_data_4

import com.volupism.string_1.Letter
import com.volupism.string_1.String_creator
import com.volupism.unicode_basic_0.Unicode_basic

class Letter_location {

    var line: Int = 0

    var col: Int = 0

    fun ini(text: String, position: Int) {
        var letter = Letter()
        while (letter.position < position) {
            var fini = letter.probe(text)
            if (letter.number == Unicode_basic.LINE_FEED) {
                line++
                col = 0
            } else {
                col++
            }
            letter.position = fini
        }
    }

    override fun toString(): String {
        var sc = String_creator()
        sc.add("Token")
        sc.add(" ; line " + line)
        sc.add(" ; col " + col)
        return sc.toString()
    }
}