package ir.sahrapanel.app.core.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

val utcTimeZone = TimeZone.UTC
fun LocalDateTime.toUtcInstant() = this.toInstant(utcTimeZone)