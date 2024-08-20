/**
 * Classe que implementa o serviço de produtos favoritos.
 *
 * @author Kaue Correa Colling
 * @version 1.0
 */
package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.favorite.FavoritePostRequestDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.FavoriteProductServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteProductServiceImpl implements FavoriteProductServiceInt {

    /**
     * Repositório de clientes.
     */
    private ClientRepository clientRepository;

    /**
     * Repositório de produtos.
     */
    private ProductRepository productRepository;

    /**
     * Adiciona um produto à lista de produtos favoritos de um cliente.
     *
     * @param clientId - id do cliente.
     * @param productId - id do produto.
     */
    public void addProductFavorite(Long clientId, Long productId) {
        /**
         * Busca o cliente e o produto pelo ID.
         */
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Product> product = productRepository.findById(productId);

        /**
         * Verifica se o cliente e o produto foram encontrados.
         */
        if (client.isPresent() && product.isPresent()) {
            /**
             * Obtém o cliente e o produto da Optional.
             */
            Client cliente = client.get();
            Product produto = product.get();

            /**
             * Adiciona o produto à lista de produtos favoritos do cliente.
             */
            cliente.addProductFavorite(produto);

            /**
             * Salva as alterações no cliente.
             */
            clientRepository.save(cliente);

        }
    }

    /**
     * Remove um produto à lista de produtos favoritos de um cliente.
     *
     * @param clientId - id do cliente.
     * @param productId - id do produto.
     */
    public void removeProductFavorite(Long clientId, Long productId) {
        /**
         * Busca o cliente e o produto pelo ID.
         */
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Product> product = productRepository.findById(productId);

        /**
         * Verifica se o cliente e o produto foram encontrados.
         */
        if (client.isPresent() && product.isPresent()) {
            /**
             * Obtém o cliente e o produto da Optional.
             */
            Client cliente = client.get();
            Product produto = product.get();

            /**
             * Remove o produto à lista de produtos favoritos do cliente.
             */
            cliente.removeProductFavorite(produto);

            /**
             * Salva as alterações no cliente.
             */
            clientRepository.save(cliente);
        }
    }

    public List<ProductMinimalGetDTO> findClientFavoriteProducts(Long clientId) {
        return clientRepository.findById(clientId).
                get().getProductsFavorite().
                stream().map(Product::toMinimalGetDTO).toList();
    }

    public Boolean isProductFavorited(Long clientId, Long productId) {
        List<Product> products = clientRepository.findById(clientId).get().getProductsFavorite();

        for (Product product: products) {
            if (product.getId().equals(productId)) {
                return true;
            }
        }

        return false;
    }
}