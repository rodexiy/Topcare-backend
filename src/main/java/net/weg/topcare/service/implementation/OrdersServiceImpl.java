/**
 * Implementação do serviço de pedidos.
 *
 * @author Kaue Correa Colling
 * @since [Versão]
 */
package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cartorder.*;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.*;
import net.weg.topcare.enums.OrderStatusEnum;
import net.weg.topcare.repository.AddressRepository;
import net.weg.topcare.repository.CartOrderRepository;
import net.weg.topcare.repository.ClientRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço de pedidos.
 *
 * Responsável por realizar operações de negócios relacionadas a pedidos.
 */
@Service
@AllArgsConstructor
public class OrdersServiceImpl {

    /**
     * Repositório de clientes.
     * Responsável por realizar operações de CRUD em clientes.
     */
    private ClientRepository clientRepository;

    /**
     * Repositório de pedidos.
     * Responsável por realizar operações de CRUD em pedidos.
     */
    private CartOrderRepository cartOrderRepository;

    private AddressRepository addressRepository;

    /**
     * Adiciona um pedido ao histórico de pedidos de um cliente.
     *
     * @param dto Pedido a ser adicionado.
     */
    public void addCartOrderToOrders(@RequestBody CartOrderPostDTO dto) {
        Optional<CartOrder> cartOrders = cartOrderRepository.findById(dto.cartOrderId());
        Optional<Client> client = clientRepository.findById(dto.clientId());

        if (client.isPresent() && cartOrders.isPresent()) {
            Client cliente = client.get();
            CartOrder cartsOrder = cartOrders.get();

            cliente.addCartOrderToOrders(cartsOrder);
            clientRepository.save(cliente);
        }
    }

    public void crateCartOrderFromCart(Client client) {
        Cart cart = client.getCart();
        CartOrder cartOrder = new CartOrder();

        Address address = cart.getSelectedAddress();

        cartOrder.setAddress(address);
        cartOrder.setDiscount(cart.getCartTotalDiscountAmount());
        cartOrder.setProductsTotal(cart.getCartTotalNotDiscounted());
        cartOrder.setFreight(Math.random() * 10);
        cartOrder.setNumberOrder(1);

        client.addCartOrderToOrders(cartOrder);
        clientRepository.save(client);

    }

    /**
     * Retorna uma lista de pedidos concluídos.
     * @return Lista de pedidos concluídos.
     */
    public List<CartOrderMinimalGetDTO> getCompletedOrders() {
        return cartOrderRepository.findAll()
                .stream()
                .filter(this::isCompletedOrder)
                .map(CartOrder::convertToMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista de pedidos em andamento.
     * @return Lista de pedidos em andamento.
     */
    public List<CartOrderMinimalGetDTO> getInProgressOrders() {
        return cartOrderRepository.findAll()
                .stream()
                .filter(this::isInProgressOrder)
                .map(CartOrder::convertToMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista de todos os pedidos.
     * @return Lista de pedidos.
     */
    public List<CartOrderMinimalGetDTO> getOrders() {
        return cartOrderRepository.findAll()
                .stream().map(CartOrder::convertToMinimalGetDTO).collect(Collectors.toList());
    }

    public List<CartOrderMaximalGetDTO> getDescriptionOrders(CartOrderGetAllDTO dto) {
        return cartOrderRepository.findAll()
                .stream().map(CartOrder::convertToMaximalGetDTO).collect(Collectors.toList());
    }

    /**
     * Verifica se um pedido está concluído.
     * @param cartOrder Pedido a ser verificado.
     * @return true se o pedido está concluído, false caso contrário.
     */
    private boolean isCompletedOrder(CartOrder cartOrder) {
        return cartOrder.getOrderStatuses().stream()
                .anyMatch(status -> status.getOrderStatus() == OrderStatusEnum.PEDIDO_ENTREGUE);
    }

    /**
     * Verifica se um pedido está em andamento.
     * @param cartOrder Pedido a ser verificado.
     * @return true se o pedido está em andamento, false caso contrário.
     */
    private boolean isInProgressOrder(CartOrder cartOrder) {
        return cartOrder.getOrderStatuses().stream()
                .noneMatch(status -> status.getOrderStatus() == OrderStatusEnum.PEDIDO_ENTREGUE);
    }

    private PageRequest createPageRequest(int page, int size, String sorter) {
        Sort sort = Sort.by(
                Sort.Order.asc("orderCreated"));

        return switch (sorter) {
            case "decrescent" -> PageRequest.of(page, size, sort.descending());
            case "crescent" -> PageRequest.of(page, size, sort.ascending());
            default -> PageRequest.of(page, size, Sort.Direction.DESC, "orderCreated");
        };
    }

    public Page<CartOrderMinimalGetDTO> getClientOrders(Long clientId, int page, String sorter) {
        PageRequest pageRequest = createPageRequest(page, 20, sorter);
        Page<CartOrder> orders = cartOrderRepository.findAllByClient_Id(clientId, pageRequest);

        List<CartOrderMinimalGetDTO> dtos = orders.stream()
                .map(CartOrder::convertToMinimalGetDTO)
                .toList();

        return new PageImpl<>(dtos, pageRequest, orders.getTotalElements());
    }

    public CartOrderMaximalGetDTO getOrder(Long id) {
        return cartOrderRepository.findById(id).get().convertToMaximalGetDTO();
    }

    public CartOrderMinimalGetDTO getLastOrder(Long id) {
        List<CartOrder> orders = cartOrderRepository.findAllByClient_Id(id);
        try {

            CartOrder last = orders.get(orders.size() - 1);
            return last.convertToMinimalGetDTO();
        }catch (Exception e) {
            return null;
        }
    }
}