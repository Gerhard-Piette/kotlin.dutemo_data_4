package com.volupism.dutemo_data_4

/**
 * Context for compiling 1 or more Pages.
 */
class Context {

    /**
     * This map contains all Pages that are linked directly and indirectly for the compilation of the main Page.
     * - This allows to compile a Page only once.
     * - This allows to detect reference cycles.
     *
     *  key = page URI.
     *
     *  data = page.
     */
    var page_d: HashMap<String, Page> = HashMap()

    /**
     * The page that is currently compiled.
     */
    var page: Page

    /**
     * The namespace that is currently compiled.
     */
    var namespace: Namespace? = null

    /**
     * The position of the Token that currently compiled.
     */
    var position: Int = 0

    constructor(page: Page) {
        this.page = page
    }

    /**
     * Increases this.position by 1.
     */
    fun move() {
        this.position++
    }

    fun get_token(): Token {
        return this.page.token_s.get(position)!!
    }

    fun get_token(token_key: String): Token {
        val ret = get_token()
        if (ret.key.equals(token_key)) {
            throw Dutemo_error("Expected token with key '" + Key.Line_offset + "'. Found " + ret)
        }
        return ret
    }

}