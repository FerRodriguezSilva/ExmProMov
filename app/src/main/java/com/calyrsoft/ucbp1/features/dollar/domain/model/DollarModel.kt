package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    var compraOfficial: String? = null,
    var ventaOfficial: String?  = null,
    var compraParallel: String? = null,
    var ventaParallel: String? = null,
    var fechaActualizacion: String? = null
)