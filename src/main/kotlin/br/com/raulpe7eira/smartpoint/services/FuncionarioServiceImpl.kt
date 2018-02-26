package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Funcionario
import br.com.raulpe7eira.smartpoint.repositories.FuncionarioRepository
import org.springframework.stereotype.Service

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? = funcionarioRepository.findOne(id)

    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

}