/**
 * Classe que controla as operações relacionadas a produtos favoritos.
 *
 * @author  Kaue Correa Colling
 * @version 1.0
 */
package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.favorite.FavoritePostRequestDTO;
import net.weg.topcare.service.implementation.FavoriteProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/favorite")
@AllArgsConstructor
public class FavoriteProductController {

    /**
     * Serviço de produtos favoritos.
     */
    private FavoriteProductServiceImpl favoriteProductService;

    /**
     * Adiciona um produto à lista de produtos favoritos de um cliente.
     *
     * @param dto - dto onde busca o clientId e o productId.
     */
    @PostMapping
    public void addProductFavorite(@RequestBody FavoritePostRequestDTO dto) {
        /**
         * Chama o método addProductFavorite do serviço de produtos favoritos,
         * passando o ID do cliente e o ID do produto como parâmetros.
         */
        favoriteProductService.addProductFavorite(dto);
    }
}