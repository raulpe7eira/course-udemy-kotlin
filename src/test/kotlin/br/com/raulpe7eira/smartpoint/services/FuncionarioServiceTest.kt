package br.com.raulpe7eira.smartpoint.services

import br.com.raulpe7eira.smartpoint.documents.Funcionario
import br.com.raulpe7eira.smartpoint.enums.PerfilEnum
import br.com.raulpe7eira.smartpoint.repositories.FuncionarioRepository
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
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @Autowired
    val funcionarioService: FuncionarioService? = null

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    private val CPF = "51463645000100"
    private val EMAIL = "email@email.com"
    private val ID = "1"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(funcionarioRepository?.findByCpf(CPF))
                .willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByEmail(EMAIL))
                .willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findOne(ID))
                .willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
                .willReturn(funcionario())
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorCpf(CPF)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorEmail(EMAIL)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario: Funcionario? = funcionarioService?.buscarPorId(ID)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testPersistirFuncionario() {
        val funcionario: Funcionario? = funcionarioService?.persistir(funcionario())
        Assert.assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario = Funcionario("Nome", EMAIL, SenhaUtils().gerarBcrypt("123456"), CPF, PerfilEnum.ROLE_USUARIO, ID)

}