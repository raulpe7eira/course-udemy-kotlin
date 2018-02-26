package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Funcionario
import br.com.raulpe7eira.smartpoint.documents.Lancamento
import br.com.raulpe7eira.smartpoint.enums.PerfilEnum
import br.com.raulpe7eira.smartpoint.enums.TipoEnum
import br.com.raulpe7eira.smartpoint.repositories.FuncionarioRepository
import br.com.raulpe7eira.smartpoint.repositories.LancamentoRepository
import br.com.raulpe7eira.smartpoint.utils.SenhaUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class LancamentoServiceTest {

    @Autowired
    val lancamentoService: LancamentoService? = null

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    private val ID = "1"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given<Page<Lancamento>>(lancamentoRepository?.findByFuncionarioId(ID, PageRequest(0, 30)))
                .willReturn(PageImpl(ArrayList<Lancamento>()))
        BDDMockito.given(lancamentoRepository?.findOne(ID))
                .willReturn(lancamento())
        BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java)))
                .willReturn(lancamento())
    }

    @Test
    fun testBuscarLancamentosPorFuncionarioId() {
        val lancamentos: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(ID, PageRequest(0, 30))
        Assert.assertNotNull(lancamentos)
    }

    @Test
    fun testBuscarLancamentoPorId() {
        val lancamento: Lancamento? = lancamentoService?.buscarPorId(ID)
        Assert.assertNotNull(lancamento)
    }

    @Test
    fun testPersistirLancamento() {
        val lancamento: Lancamento? = lancamentoService?.persistir(lancamento())
        Assert.assertNotNull(lancamento)
    }

    private fun lancamento(): Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO, ID)

}