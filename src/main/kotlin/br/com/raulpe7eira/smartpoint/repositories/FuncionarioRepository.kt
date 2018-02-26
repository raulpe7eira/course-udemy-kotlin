package br.com.raulpe7eira.smartpoint.repositories

import br.com.raulpe7eira.smartpoint.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario

    fun findByCpf(cpf: String): Funcionario

}