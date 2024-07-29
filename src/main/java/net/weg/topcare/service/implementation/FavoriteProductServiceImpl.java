/**
 * Classe que implementa o serviço de produtos favoritos.
 *
 * @author Kaue Correa Colling
 * @version 1.0
 */
package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.favorite.FavoritePostRequestDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.FavoriteProductServiceInt;
import org.springframework.stereotype.Service;

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
     * @param dto - dto onde busca o clientId e o productId.
     */
    public void addProductFavorite(FavoritePostRequestDTO dto) {
        /**
         * Busca o cliente e o produto pelo ID.
         */
        Optional<Client> client = clientRepository.findById(dto.clientId());
        Optional<Product> product = productRepository.findById(dto.productId());

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

}