package com.tommyappdevs.albertsonsapp.model

data class DisplayData(
    val lf: String,
    val vars: List<VarsDefinition>
)

data class VarsDefinition(
    val header: String,
    val since: Int
){
    override fun toString(): String {
        return "Header: $header \\nSince: $since"
    }
}