package br.com.raulpe7eira.smartpoint

import br.com.raulpe7eira.smartpoint.documents.Empresa
import br.com.raulpe7eira.smartpoint.documents.Funcionario
import br.com.raulpe7eira.smartpoint.enums.PerfilEnum
import br.com.raulpe7eira.smartpoint.repositories.EmpresaRepository
import br.com.raulpe7eira.smartpoint.repositories.FuncionarioRepository
import br.com.raulpe7eira.smartpoint.repositories.LancamentoRepository
import br.com.raulpe7eira.smartpoint.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SmartpointApplication(
        val empresaRepository: EmpresaRepository,
        val funcionarioRepository: FuncionarioRepository,
        val lancamentoRepository: LancamentoRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        empresaRepository.deleteAll()
        funcionarioRepository.deleteAll()
        lancamentoRepository.deleteAll()

        val empresa: Empresa = Empresa("Empresa", "10443887000146")
        empresaRepository.save(empresa)

        val admin: Funcionario = Funcionario("Admin", "admin@empresa.com",
                SenhaUtils().gerarBcrypt("123456"), "25708317000", PerfilEnum.ROLE_ADMIN,
                empresa.id!!)
        funcionarioRepository.save(admin)

        val funcionario: Funcionario = Funcionario("Funcionário", "funcionario@empresa.com",
                SenhaUtils().gerarBcrypt("123456"), "44325441557", PerfilEnum.ROLE_USUARIO,
                empresa.id!!)
        funcionarioRepository.save(funcionario)

        println("Empresa ID: ${empresa.id}")
        println("Admin ID: ${admin.id}")
        println("Funcionario ID: ${funcionario.id}")
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(SmartpointApplication::class.java, *args)
}
