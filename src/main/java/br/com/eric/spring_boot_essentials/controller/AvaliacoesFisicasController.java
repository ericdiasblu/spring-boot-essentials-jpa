package br.com.eric.spring_boot_essentials.controller;

import br.com.eric.spring_boot_essentials.dto.AvaliacaoFisicDto;
import br.com.eric.spring_boot_essentials.exception.BadRequestException;
import br.com.eric.spring_boot_essentials.exception.NotFoundException;
import br.com.eric.spring_boot_essentials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicDto avaliacaoFisicDto) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicDto);

    }

}
