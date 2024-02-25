package com.volupism.dutemo_data_4

import com.volupism.string_1.Letter
import com.volupism.string_1.String_creator
import com.volupism.string_1.get_letter_location
import com.volupism.unicode_basic_0.Unicode
import com.volupism.unicode_basic_0.Unicode_basic

class Lexer {

    var keyword = Keyword()

    var unicode_probe = Unicode_probe()

    fun probe(page: Page) {
        var last_letter = page.text.last()
        if (last_letter != Unicode_basic.LINE_FEED.toChar()) {
            val report = "Dutemo text must end with a Line_move letter."
            throw RuntimeException(report)
        }
        while (true) {
            if (probe_end_of_text(page)) {
                return
            }
            var line_offset = probe_line_offset_token(page, true)
            var ini = page.letter.position
            if (probe_t_data(page, line_offset, Key._Colon_t)) {
                continue
            }
            page.letter.position = ini
            if (probe_t_data(page, line_offset, Key._Colon_tl)) {
                continue
            }
            page.letter.position = ini
            if (probe_line(page)) {
                continue
            }
        }
    }

    fun probe_line_offset_token(page: Page, create_token: Boolean): Int {
        var ret = 0
        while (true) {
            var fini = page.letter.probe(page.text)
            if (page.letter.number != Unicode_basic.HORIZONTAL_TAB) {
                break
            }
            page.letter.position = fini
            ret++
        }
        if (create_token) {
            var token = Token(ret, Key.Line_offset, "")
            page.token_s.add(token)
        }
        return ret
    }

    /**
     * Gives <b>true<b> if a line was found.
     */
    fun probe_line(page: Page): Boolean {
        var ini = 0
        while (true) {
            // token after token in a line
            this.move_after_codepoint(page, Unicode_basic.SPACE)
            ini = page.letter.position
            var found = probe_name(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            found = probe_dutemo_keyword(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            found = probe_decimal_number(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            found = probe_decimal_signed_integer(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            found = probe_decimal_integer(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            found = probe_word(page, true)
            if (found) {
                continue
            }
            page.letter.position = ini
            if (probe_line_move(page)) {
                return true
            }
            var report = "Encountered codepoint that is not allowed in Dutemo text."
            report += "\n Letter : '" + Unicode.Companion.create_string(page.letter.number) + "'"
            report += "\n Codepoint number : " + page.letter.number
            report += "\n Codepoint position : " + page.letter.position
            var letter_location = get_letter_location(page.text, page.letter.position)
            report += "\n Line index ( 0 for first line ) : " + letter_location.line
            report += "\n Column index ( 0 for first column ) : " + letter_location.col
            throw RuntimeException(report)
        }
    }

    /**
     * Sets page.letter.position after the Line_move.
     */
    fun probe_line_move(page: Page): Boolean {
        var fini = page.letter.probe(page.text)
        if (page.letter.number != Unicode_basic.LINE_FEED) {
            return false
        }
        page.letter.position = fini
        return true
    }

    fun probe_end_of_text(page: Page): Boolean {
        if (page.letter.position < page.text.length) {
            return false
        }
        var token = Token(page.text.length, Key.END_OF_TEXT, "")
        page.token_s.add(token)
        return true
    }

    fun add_token(ini: Int, key: String, page: Page): Token {
        var text = page.text.substring(ini, page.letter.position)
        var token = Token(ini, key, text)
        page.token_s.add(token)
        return token
    }

    fun add_keyword_token(ini: Int, model: String, page: Page) {
        var token = Token(ini, model, "")
        page.token_s.add(token)
    }

    fun move_after_codepoint(page: Page, codepoint: Int): Int {
        var ret = 0
        while (true) {
            var fini = page.letter.probe(page.text)
            if (page.letter.number == codepoint) {
                page.letter.position = fini
                ret++
            } else {
                return ret
            }
        }
    }

    fun probe_decimal_integer(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        var pos = page.letter.probe(page.text)
        if (!unicode_probe.probe_number(page.letter.number)) {
            return false
        }
        while (true) {
            page.letter.position = pos
            pos = page.letter.probe(page.text)
            if (!unicode_probe.probe_number(page.letter.number)) {
                break
            }
        }
        if (add_token) {
            var token = add_token(ini_pos, Key.Decimal_integer, page)
            token.output_model = Base_model_token._Colon_0_Plus_
        }
        return true
    }

    fun probe_decimal_signed_integer(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        var pos = page.letter.probe(page.text)
        if (!(unicode_probe.probe_minus(page.letter.number)
                    || unicode_probe.probe_plus(page.letter.number))
        ) {
            return false
        }
        page.letter.position = pos
        var found = probe_decimal_integer(page, false)
        if (found) {
            if (add_token) {
                var token = add_token(ini_pos, Key.Decimal_signed_integer, page)
                token.output_model = Base_model_token._Colon_0_Minus_
            }
            return true
        }
        return false
    }

    fun probe_decimal_number(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        //
        var found = probe_decimal_signed_integer(page, false)
        if (!found) {
            var found = probe_decimal_integer(page, false)
            if (!found) {
                return false
            }
        }
        //
        var pos = page.letter.probe(page.text)
        if (!(unicode_probe.probe_point(page.letter.number)
                    || unicode_probe.probe_comma(page.letter.number))
        ) {
            return false
        }
        page.letter.position = pos
        //
        found = probe_decimal_integer(page, false)
        if (found) {
            if (add_token) {
                var token = add_token(ini_pos, Key.Decimal_number, page)
                token.output_model = Base_model_token._Colon_0
            }
            return true
        }
        return false
    }

    /**
     * Probes the given keyword.
     */
    fun probe_keyword(page: Page, letter1: Letter, keyword: String, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        if (page.letter.probe_text(page.text, letter1, keyword)) {
            if (add_token) {
                add_keyword_token(ini_pos, keyword, page)
            }
            return true
        } else {
            return false
        }
    }

    /**
     * Tries to find a Dutemo keyword.
     */
    fun probe_dutemo_keyword(page: Page, add_token: Boolean): Boolean {
        var ini = page.letter.position
        var letter1 = Letter()
        for (keyword in keyword.keyword_s.iterator()) {
            page.letter.position = ini
            letter1.position = 0
            if (probe_keyword(page, letter1, keyword, add_token)) {
                return true
            }
        }
        return false
    }

    fun probe_name(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        var pos = page.letter.probe(page.text)
        if (!unicode_probe.probe_first_name_letter(page.letter.number)) {
            return false
        }
        var first_letter = page.letter.number
        while (true) {
            page.letter.position = pos
            pos = page.letter.probe(page.text)
            if (!unicode_probe.probe_other_name_letter(page.letter.number)) {
                if (first_letter == Unicode_basic.LOW_LINE) {
                    if ((page.letter.position - ini_pos) == 1) {
                        return false
                    }
                }
                if (add_token) {
                    add_token(ini_pos, Key.Name, page)
                }
                return true
            }
        }
    }


    fun probe_private_line(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        var pos = page.letter.probe(page.text)
        if (page.letter.number != Unicode_basic.NUMBER_SIGN) {
            return false
        }
        while (true) {
            page.letter.position = pos
            pos = page.letter.probe(page.text)
            if (
                page.letter.number == Unicode_basic.LINE_FEED
                || page.letter.number == Int.MAX_VALUE
                || page.letter.position >= page.text.length
            ) {
                if (add_token) {
                    add_token(ini_pos, Key.Private_line, page)
                }
                return true
            }
        }
    }

    fun probe_word(page: Page, add_token: Boolean): Boolean {
        var ini_pos = page.letter.position
        var pos = page.letter.probe(page.text)
        if (unicode_probe.probe_space(page.letter.number)
            || page.letter.position >= page.text.length
        ) {
            return false
        }
        while (true) {
            page.letter.position = pos;
            pos = page.letter.probe(page.text)
            if (
                unicode_probe.probe_space(page.letter.number)
                || page.letter.position >= page.text.length
            ) {
                if (add_token) {
                    add_token(ini_pos, Key.Word, page)
                }
                return true
            }
        }
    }

    fun probe_t_data(page: Page, line_offset: Int, keyword: String): Boolean {
        var ini = page.letter.position
        //
        if (!probe_keyword(page, Letter(), keyword, false)) {
            return false
        }
        move_after_end_of_line(page)
        var sc = String_creator()
        while (true) {
            if (probe_high_line(page, line_offset)) {
                var high_ini = page.letter.position
                var high_fini = move_after_end_of_line(page)
                sc.add(page.text.substring(high_ini, high_fini))
            } else {
                break
            }
        }
        var ret = Token(ini, keyword, sc.toString())
        page.token_s.add(ret)
        return true
    }

    /**
     * Checks if the line is a high line relative to line_offset.<br>
     */
    fun probe_high_line(page: Page, line_offset: Int): Boolean {
        var ln = probe_line_offset_token(page, false)
        return line_offset < ln
    }

    /**
     * Sets page.letter.position after the end of line.<br>
     */
    fun move_after_end_of_line(page: Page): Int {
        while (true) {
            var ret = page.letter.probe(page.text)
            page.letter.position = ret
            if (page.letter.number == Unicode_basic.LINE_FEED
                || page.letter.position >= page.text.length
            ) {
                return ret
            }
        }
    }

}
