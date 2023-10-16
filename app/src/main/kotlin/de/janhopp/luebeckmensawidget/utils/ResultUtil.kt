package de.janhopp.luebeckmensawidget.utils

inline fun <reified R, reified T : Throwable> Result<R>.alsoThrow(): Result<R> = also { result ->
    result.onFailure { exception ->
        if (exception is T)
            throw exception
    }
}
