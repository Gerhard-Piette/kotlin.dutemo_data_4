package com.volupism.dutemo_data_4.parser

import com.volupism.dutemo_data_4.Context
import com.volupism.dutemo_data_4.Key

/**
 * Probes the :dutemo statement.
 */
fun probe_colon_a_statement(c: Context): Boolean {
    var token = c.get_token()
    if (token.key != Key.Name) {
        return false
    }
    c.move()
    token = c.get_token()
    if (token.key != Key._Colon_a) {
        return false
    }
    c.move()
    return true
}