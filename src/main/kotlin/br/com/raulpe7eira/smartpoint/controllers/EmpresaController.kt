package br.com.raulpe7eira.smartpoint.controllers

import br.com.raulpe7eira.smartpoint.documents.Empresa
import br.com.raulpe7eira.smartpoint.dtos.EmpresaDto
import br.com.raulpe7eira.smartpoint.response.Response
import br.com.raulpe7eira.smartpoint.services.EmpresaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresas")
class EmpresaController(val empresaService: EmpresaService) {

    @GetMapping("/cnpj/{cnpj}")
    fun buscarPorCnpj(@PathVariable("cnpj") cnpj: String): ResponseEntity<Response<EmpresaDto>> {
        val response: Response<EmpresaDto> = Response<EmpresaDto>()

        val empresa: Empresa? = empresaService.buscarPorCnpj(cnpj)
        if (empresa == null) {
            response.erros.add("Empresa não encontrada para o CNPJ ${cnpj}.")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterEmpresaDto(empresa)
        return ResponseEntity.ok(response)
    }

    private fun converterEmpresaDto(empresa: Empresa): EmpresaDto = EmpresaDto(
            empresa.razaoSocial, empresa.cnpj, empresa.id)

}