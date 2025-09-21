package de.janhopp.luebeckmensawidget.utils

import androidx.work.Constraints

fun buildConstraints(builder: Constraints.Builder.() -> Unit): Constraints =
    Constraints.Builder().apply(builder).build()
