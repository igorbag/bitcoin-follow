package com.example.bitcoinfollow.utils

import java.io.IOException

class BusinessException(message: String?, cause: Throwable?) : IOException(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}