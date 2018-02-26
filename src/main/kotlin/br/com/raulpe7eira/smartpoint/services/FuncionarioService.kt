package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Funcionario

interface FuncionarioService {

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?

    fun persistir(funcionario: Funcionario): Funcionario

}