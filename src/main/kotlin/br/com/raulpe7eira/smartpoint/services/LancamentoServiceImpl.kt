package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Lancamento
import br.com.raulpe7eira.smartpoint.repositories.LancamentoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository) : LancamentoService {

    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> = lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento? = lancamentoRepository.findOne(id)

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    override fun remover(id: String) = lancamentoRepository.delete(id)

}