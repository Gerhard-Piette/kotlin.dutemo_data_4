package com.volupism.dutemo_data_4

import com.volupism.string_1.String_creator

class Token {

    var page: Page? = null

    /**
     * The ini position in a text.<br>
     * The number of long_move letters in case this.key == Key.Line.<br>
     */
    var number: Int = 0

    /**
     * Token key.
     */
    var key: String = ""

    /**
     * The text of the token.
     */
    var text: String = ""

    /**
     * The model token indicates the model for the output from this token.
     *
     * A model token is either name token or keyword token.
     *
     * A model without name is implemented as model with a name that is created by the compiler.
     *
     */
    var output_model: Token? = null

    /**
     * Used for the implementation of :m models.<br>
     * <br>
     * input key = The :m name.<br>
     * output value = The model token linked to the :m name.
     */
    var m_model_l: Map<String, Token>? = null

    constructor(key: String) {
        this.key = key
    }

    constructor(number: Int, key: String, text: String, page: Page?) {
        this.number = number
        this.key = key
        this.text = text
        this.page = page
    }

    override fun toString(): String {
        var sc = String_creator()
        sc.add("( Token")
        sc.add(" ; number " + number)
        var t = text.replace("\n", "\\n")
        sc.add(" ; text \"" + t + "\"")
        sc.add(" ; key " + key)
        sc.add(" ; page " + page)
        sc.add(" )")
        return sc.toString()
    }

    /**
     * Gives a copy of this token except for this.m_model_l.
     */
    fun copy(): Token {
        var ret = Token(number, key, text, page)
        ret.output_model = this.output_model
        return ret
    }
}