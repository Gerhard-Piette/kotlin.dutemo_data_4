package com.volupism.dutemo_data_4

class Token_getter {

    /**
     * The page that contains the tokens.
     */
    var page: Page

    /**
     * Offset that indicates all high tokens.
     */
    var offset_token: Token

    constructor(page: Page, offset_token: Token) {
        this.page = page
        this.offset_token = offset_token
    }

    fun get(position: Int): Token? {
        var ret = page.token_s.get(position)
        if (ret != null) {
            if (ret.key != Key.Line_offset) {
                return ret
            } else {
                // Token is Line_offset token.
                if (ret.number <= offset_token.number) {
                    return null
                } else {
                    return get(position + 1)
                }
            }
        } else {
            return null
        }
    }

}