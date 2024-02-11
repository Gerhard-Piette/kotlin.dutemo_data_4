package com.volupism.dutemo_data_4.token_parser

import com.volupism.dutemo_data_4.Page
import com.volupism.dutemo_data_4.Token

class Parser_context {

    var page: Page

    /**
     * Position in the sequence of tokens.
     */
    var position: Int = 0

    /**
     * If the probed item was found.
     */
    var found: Boolean = false

    constructor(page: Page) {
        this.page = page
    }

    fun get_token(): Token? {
        return this.page.token_s.get(position)
    }
    
}