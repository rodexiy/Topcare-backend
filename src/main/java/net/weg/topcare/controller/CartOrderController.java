/**
 * Controlador de pedidos.
 *
 * @author Kaue Correa Colling
 * @since [Versão]
 */
package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cartorder.CartOrderMaximalGetDTO;
import net.weg.topcare.controller.dto.cartorder.CartOrderGetAllDTO;
import net.weg.topcare.controller.dto.cartorder.CartOrderMinimalGetDTO;
import net.weg.topcare.controller.dto.cartorder.CartOrderPostDTO;
import net.weg.topcare.service.implementation.OrdersServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de pedidos.
 *
 * Responsável por lidar com requisições relacionadas a pedidos.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cartorder")
@AllArgsConstructor
public class CartOrderController {

    /**
     * Serviço de pedidos.
     *
     * Responsável por realizar operações de negócios relacionadas a pedidos.
     */
    private OrdersServiceImpl ordersService;

    /**
     * Adiciona um pedido ao histórico de pedidos de um cliente.
     *
     * @param dto Pedido a ser adicionado.
     */
    @PostMapping
    public void addCartOrderToOrders(@RequestBody CartOrderPostDTO dto) {
        ordersService.addCartOrderToOrders(dto);
    }

    /**
     * Retorna uma lista de pedidos concluídos.
     *
     * @return Lista de pedidos concluídos.
     */
    @GetMapping("/completed")
    public List<CartOrderMinimalGetDTO> getCompletedOrders() {
        return ordersService.getCompletedOrders();
    }

    /**
     * Retorna uma lista de pedidos em andamento.
     *
     * @return Lista de pedidos em andamento.
     */
    @GetMapping("/in-progress")
    public List<CartOrderMinimalGetDTO> getInProgressOrders() {
        return ordersService.getInProgressOrders();
    }

    /**
     * Retorna uma lista de todos os pedidos.
     *
     * @return Lista de todos os pedidos.
     */
    @GetMapping("/all-orders")
    public List<CartOrderMinimalGetDTO> getOrders() {
        return ordersService.getOrders();
    }

    @GetMapping("/descriptionOder")
    public List<CartOrderMaximalGetDTO> getDescriptionOrder(@RequestBody CartOrderGetAllDTO dto) {
        return ordersService.getDescriptionOrders(dto);
    }
}