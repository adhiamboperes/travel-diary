package com.adhiambooyier.mytraveldiary.common

fun Throwable.orCause(): Throwable {
    return cause?.orCause() ?: this
}
