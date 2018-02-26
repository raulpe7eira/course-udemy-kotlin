package br.com.raulpe7eira.smartpoint.repositories

import br.com.raulpe7eira.smartpoint.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository

interface EmpresaRepository : MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa

}