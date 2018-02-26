package br.com.raulpe7eira.smartpoint.dtos

data class EmpresaDto (
        val razaoSocial: String,
        val cnpj: String,
        val id: String? = null
)