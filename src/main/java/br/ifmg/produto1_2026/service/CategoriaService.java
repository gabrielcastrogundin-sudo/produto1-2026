package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        // lista com os dados do BD.
        List<Categoria> categorias = categoriaRepository.findAll();
        // lista com os dados convertidos em DTO ( por que DTO? porque você pode não querer trafegar tudo que está na entity, então vc "filtra" a entity,
        // esse "filtro" se chama DTO
        List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();

//        for (Categoria categoria : categorias){
//            categoriasDTO.add(new CategoriaDTO(categoria));
//        }

        return categorias.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());

//        return categoriasDTO;
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(long id) {
        Optional<Categoria> opt = categoriaRepository.findById(id);

        Categoria categoria = opt.orElseThrow(() -> new RegistroNaoEncontrado("Categoria não encontrada"));
        // Convertemos entity para categoriaDTO
        return new CategoriaDTO(categoria);
    }
}
