package ir.sahrapanel.app.core.domain

fun String.isPhoneNumber(): Boolean {
    val phoneRegex = Regex("^09\\d{9}$")
    return this.matches(phoneRegex)
}