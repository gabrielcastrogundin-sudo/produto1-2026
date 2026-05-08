package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.ProductDTO;
import br.ifmg.produto1_2026.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
@Tag(name="Produtos")
public class ProductResource {
    @GetMapping
    @Operation(
            description = "retorna todos os produtos",
            summary = "Endpoint para listar um produtos",
            responses = {
                    @ApiResponse( description = "Lista retornada com sucesso",responseCode = "200"),
                    @ApiResponse( description = "Erro interno",responseCode = "500", content = {}),
            }

    )
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> products =  productService.findAll(pageable);

        return ResponseEntity.ok().body(products);
    }


    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
            description = "retorna um produto",
            summary = "Endpoint para resgatar um produto usando seu id",
            responses = {
                    @ApiResponse( description = "Lista retornada com sucesso",responseCode = "200"),
                    @ApiResponse( description = "Produto inesistente",responseCode = "404"),
                    @ApiResponse( description = "Erro interno",responseCode = "500", content = {}),
            }

    )

    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

        ProductDTO dto = productService.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(produces = "application/json")
    @Operation(
            description = "A plataforma precisa disponibilizar um cadastro de produtos",
            summary = "Endpoint para inserir um produto",
            responses = {
                    @ApiResponse( description = "Registro criado",responseCode = "201"),
                    @ApiResponse( description = "Requisição mal feita",responseCode = "400", content = {}),
                    @ApiResponse( description = "Não autorizado",responseCode = "401"),
                    @ApiResponse( description = "Proibido no seu perfil",responseCode = "403"),
                    @ApiResponse( description = "Erro ao processar",responseCode = "422"),
            }

    )
    public ResponseEntity<ProductDTO> insert(@RequestBody @Valid ProductDTO dto) {
        ProductDTO returnDTO =  productService.insert(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(returnDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(returnDTO);
    }


    @DeleteMapping("/id")
    @Operation(
            description = "A plataforma precisa disponibilizar deleção de produtos",
            summary = "Endpoint para deletar um produto",
            responses = {
                    @ApiResponse( description = "Sucesso",responseCode = "204"),
                    @ApiResponse( description = "Requisição mal feita",responseCode = "400", content = {}),
                    @ApiResponse( description = "Não autorizado",responseCode = "401"),
                    @ApiResponse( description = "Proibido no seu perfil",responseCode = "403"),
                    @ApiResponse( description = "Não encontrado",responseCode = "404"),
                    @ApiResponse( description = "Erro ao processar",responseCode = "422"),
            }

    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/id", produces = "application/json")
    @Operation(
            description = "A plataforma precisa disponibilizar atualização de produtos",
            summary = "Endpoint para atualizar um produto",
            responses = {
                    @ApiResponse( description = "Ok",responseCode = "200"),
                    @ApiResponse( description = "Requisição mal feita",responseCode = "400", content = {}),
                    @ApiResponse( description = "Não autorizado",responseCode = "401"),
                    @ApiResponse( description = "Proibido no seu perfil",responseCode = "403"),
                    @ApiResponse( description = "Não encontrado",responseCode = "404"),
                    @ApiResponse( description = "Erro ao processar",responseCode = "422"),
            }

    )
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        ProductDTO returnDTO =  productService.update(id, dto);
        return ResponseEntity.ok().body(returnDTO);
    }
}
