package br.com.raulpe7eira.smartpoint.controllers

import br.com.raulpe7eira.smartpoint.documents.Funcionario
import br.com.raulpe7eira.smartpoint.dtos.FuncionarioDto
import br.com.raulpe7eira.smartpoint.response.Response
import br.com.raulpe7eira.smartpoint.services.FuncionarioService
import br.com.raulpe7eira.smartpoint.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/funcionarios")
class FuncionarioController(val funcionarioService: FuncionarioService) {

    @PutMapping("/{id}")
    fun atualizar(
            @PathVariable("id") id: String,
            @Valid @RequestBody funcionarioDto: FuncionarioDto,
            result: BindingResult
    ): ResponseEntity<Response<FuncionarioDto>> {
        val response: Response<FuncionarioDto> = Response<FuncionarioDto>()

        val funcionario: Funcionario? = funcionarioService.buscarPorId(id)
        if (funcionario == null) {
            response.erros.add("Funcionário não encontrado.")
            return ResponseEntity.badRequest().body(response)
        }

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage)
            return ResponseEntity.badRequest().body(response)
        }

        var funcionarioAtualizado: Funcionario = atualizarDadosFuncionario(funcionario!!, funcionarioDto)
        funcionarioService.persistir(funcionarioAtualizado)

        response.data = converterFuncionarioDto(funcionarioAtualizado)
        return ResponseEntity.ok(response)
    }

    private fun atualizarDadosFuncionario(funcionario: Funcionario, funcionarioDto: FuncionarioDto): Funcionario {
        var senha: String
        if (funcionarioDto.senha == null) {
            senha = funcionario.senha
        } else {
            senha = SenhaUtils().gerarBcrypt(funcionarioDto.senha)
        }
        return Funcionario(funcionarioDto.nome, funcionario.email, senha, funcionario.cpf, funcionario.perfil,
                funcionario.empresaId, funcionarioDto.valorHora?.toDouble(),
                funcionarioDto.qtdHorasTrabalhoDia?.toFloat(), funcionarioDto.qtdHorasAlmoco?.toFloat(),
                funcionario.id)
    }

    private fun converterFuncionarioDto(funcionario: Funcionario): FuncionarioDto = FuncionarioDto(
            funcionario.nome, funcionario.email, "", funcionario.valorHora.toString(),
            funcionario.qtdHorasTrabalhoDia.toString(), funcionario.qtdHorasAlmoco.toString(), funcionario.id)

}