package br.com.raulpe7eira.smartpoint.response

data class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        var data: T? = null
)