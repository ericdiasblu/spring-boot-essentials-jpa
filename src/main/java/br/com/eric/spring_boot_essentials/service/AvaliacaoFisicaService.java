package br.com.eric.spring_boot_essentials.service;

import br.com.eric.spring_boot_essentials.database.model.AlunosEntity;
import br.com.eric.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.eric.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.eric.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.eric.spring_boot_essentials.dto.AvaliacaoFisicDto;
import br.com.eric.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.eric.spring_boot_essentials.exception.BadRequestException;
import br.com.eric.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

   private final IAlunosRepository alunosRepository;
   private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

   public void criarAvaliacaoFisica(AvaliacaoFisicDto avaliacaoFisicDto) throws NotFoundException,BadRequestException {
    AlunosEntity aluno = alunosRepository.findById(avaliacaoFisicDto.getAlunoId())
            .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

       AvaliacoesFisicasEntity avaliacaoFisica = aluno.getAvaliacaoFisica();

       if(avaliacaoFisica != null) {
           throw new BadRequestException("Avaliação física já cadastrada para este aluno");
       }

       avaliacaoFisica = AvaliacoesFisicasEntity.builder()
               .peso(avaliacaoFisicDto.getPeso())
               .altura(avaliacaoFisicDto.getAltura())
               .porcentagemGorduraCorporal(avaliacaoFisicDto.getPercentualGorduraCorporal())
               .build();

       aluno.setAvaliacaoFisica(avaliacaoFisica);
       alunosRepository.save(aluno);
   }

   public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
      return avaliacoesFisicasRepository.getAllAvaliacoes();
   }

   public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Integer page, Integer size) {
      return avaliacoesFisicasRepository.getAllAvaliacoesPage(PageRequest.of(page,size));
   }
}
