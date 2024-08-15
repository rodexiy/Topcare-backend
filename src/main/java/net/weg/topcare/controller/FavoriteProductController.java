/**
 * Classe que controla as operações relacionadas a produtos favoritos.
 *
 * @author  Kaue Correa Colling
 * @version 1.0
 */
package net.weg.topcare.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.favorite.FavoritePostRequestDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.service.implementation.FavoriteProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param clientId - id do cliente.
     * @param productId - id do produto.
     */
    @PostMapping("/{clientId}/{productId}")
    public void addProductFavorite(@PathVariable Long clientId, @PathVariable Long productId) {
        /**
         * Chama o método addProductFavorite do serviço de produtos favoritos,
         * passando a dto que contem o ID do cliente e o ID do produto como parâmetros.
         */
        favoriteProductService.addProductFavorite(clientId, productId);
    }

    /**
     * Remove um produto à lista de produtos favoritos de um cliente.
     *
     * @param clientId - id do cliente.
     * @param productId - id do produto.
     */
    @DeleteMapping("/{clientId}/{productId}")
    public void removeProductFromFavorites(@PathVariable Long clientId, @PathVariable Long productId) {
        /**
         * Chama o método removeProductFavorite do serviço de produtos favoritos,
         * passando a dto que contem o ID cliente e o ID do produto como parâmetros.
         */
        favoriteProductService.removeProductFavorite(clientId, productId);
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<List<ProductMinimalGetDTO>> findClientFavoriteProducts(@PathVariable Long clientId) {
        return ResponseEntity.ok(favoriteProductService.findClientFavoriteProducts(clientId));
    }

    @GetMapping("/{clientId}/{productId}")
    public ResponseEntity<Boolean> isProductFavorited(@PathVariable Long clientId, @PathVariable Long productId) {
        return ResponseEntity.ok(favoriteProductService.isProductFavorited(clientId, productId));
    }
}