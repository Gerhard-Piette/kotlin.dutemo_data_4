package com.volupism.dutemo_data_4

import com.volupism.ds2_0.Ds2
import com.volupism.string_1.Letter
import com.volupism.string_1.String_creator

/**
 * Represents the data of a Dutemo file.
 */
class Page {

    /**
     * Uri of the page.
     */
    var uri: String = ""

    var dutemo_edition: String = ""

    var text: String = ""

    var letter = Letter()

    var token_s: Ds2<Token> = Ds2(256)

    var error_s: Ds2<Dutemo_error> = Ds2(0)

    var namespace: Namespace = Namespace()

    constructor(text: String) {
        this.text = text
    }

    constructor(uri: String, text: String) {
        this.uri = uri
        this.text = text
    }

    override fun toString(): String {
        return get_string()
    }

    fun get_text(ini: Int, fini: Int): String {
        return text.substring(ini, fini)
    }

    fun get_text(ini: Int): String {
        return get_text(ini, letter.position)
    }

    fun get_string(): String {
        var sc = String_creator()
        sc.add("( Page")
        sc.add(" ; uri \"" + uri + "\"")
        sc.add(" )")
        return sc.toString()
    }

    fun get_token_string(): String {
        var sc = String_creator()
        for (token in token_s) {
            sc.add_line(token.toString())
        }
        return sc.toString()
    }

    fun get_formatted_dutemo(): String {
        var sc = String_creator()
        var prev_token: Token? = null
        var token_pos = 0
        for (token in token_s) {
            if (token.text == "") {
                if (token.key == Key.Line_offset) {
                    if (token_pos > 0) {
                        sc.add("\n")
                    }
                    for (i in 0..<token.number) {
                        sc.add("\t")
                    }
                } else if (token.key == Key.END_OF_TEXT) {
                    sc.add("\n")
                } else {
                    sc.add(token.key)
                }
            } else {
                if (prev_token != null) {
                    if (
                        prev_token.key != Key.Line_offset
                    ) {
                        sc.add(" ")
                    }
                }
                sc.add(token.text)
            }
            prev_token = token
            token_pos++
        }
        return sc.toString()
    }

}

