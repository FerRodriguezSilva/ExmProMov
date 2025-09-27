package com.calyrsoft.ucbp1.features.profile.domain.model

@JvmInline
value class ProfileName(val value: String) {
    init {
        require(value.isNotBlank()) { "El nombre no puede estar vacío" }
    }
}

@JvmInline
value class ProfileEmail(val value: String) {
    init {
        require(value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            "Email inválido"
        }
    }
}

@JvmInline
value class ProfileCellphone(val value: String) {
    init {
        require(value.isNotBlank()) { "El celular no puede estar vacío" }
        require(value.length >= 7) { "El celular debe tener al menos 7 caracteres" }
    }
}

@JvmInline
value class ProfileUrl(val value: String) {
    init {
        require(value.startsWith("http")) { "La URL debe ser válida" }
    }
}

@JvmInline
value class ProfileSummary(val value: String) {
    init {
        require(value.isNotBlank()) { "El resumen no puede estar vacío" }
    }
}
