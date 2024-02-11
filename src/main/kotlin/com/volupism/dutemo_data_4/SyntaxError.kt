package com.volupism.dutemo_data_4

class SyntaxError : RuntimeException {

    var token: Token?

    constructor(token: Token, message: String) : super(message) {
        this.token = token
    }

}