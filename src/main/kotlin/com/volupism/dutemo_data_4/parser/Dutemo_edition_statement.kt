package com.volupism.dutemo_data_4.parser

import com.volupism.dutemo_data_4.Context
import com.volupism.dutemo_data_4.Key

/**
 * Probes the :dutemo statement.
 */
fun probe_dutemo_edition_statement(c: Context): Boolean {
    var token = c.get_token()
    if (token.key != Key._Colon_dutemo) {
        return false
    }
    c.move()
    c.get_token(Key.Line_offset)
    c.move()
    token = c.get_token(Key.Decimal_integer)
    c.page.dutemo_edition = token.text
    c.move()
    return true
}