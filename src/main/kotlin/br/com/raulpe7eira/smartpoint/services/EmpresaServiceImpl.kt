package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Empresa
import br.com.raulpe7eira.smartpoint.repositories.EmpresaRepository
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)

}