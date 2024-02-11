package com.volupism.dutemo_data_4

import com.volupism.unicode_basic_0.Unicode_basic

class Unicode_probe {

    fun probe_latin_small(codepoint: Int): Boolean {
        if (codepoint < Unicode_basic.SMALL_A || codepoint > Unicode_basic.SMALL_Z) {
            return false
        } else {
            return true
        }
    }

    fun probe_latin_capital(codepoint: Int): Boolean {
        if (codepoint < Unicode_basic.CAPITAL_A || codepoint > Unicode_basic.CAPITAL_Z) {
            return false
        } else {
            return true
        }
    }

    fun probe_latin(codepoint: Int): Boolean {
        return probe_latin_small(codepoint)
                || probe_latin_capital(codepoint)
    }

    fun probe_number(codepoint: Int): Boolean {
        if (codepoint < Unicode_basic.DIGIT_0 || codepoint > Unicode_basic.DIGIT_9) {
            return false
        } else {
            return true
        }
    }

    fun probe_plus(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.PLUS
    }

    fun probe_minus(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.HYPHEN_MINUS
    }


    fun probe_point(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.FULL_STOP
    }

    fun probe_comma(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.COMMA
    }


    fun probe_low_line(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.LOW_LINE
    }


    fun probe_first_name_letter(cp: Int): Boolean {
        return probe_latin(cp)
                || probe_low_line(cp)
    }

    fun probe_other_name_letter(cp: Int): Boolean {
        return probe_first_name_letter(cp)
                || probe_number(cp)
    }

    fun probe_short_move(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.SPACE
    }

    fun probe_long_move(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.HORIZONTAL_TAB
    }

    fun probe_line_move(codepoint: Int): Boolean {
        return codepoint == Unicode_basic.LINE_FEED
    }

    fun probe_space(codepoint: Int): Boolean {
        return probe_short_move(codepoint)
                || probe_long_move(codepoint)
                || probe_line_move(codepoint)
    }
}