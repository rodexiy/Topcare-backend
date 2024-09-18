/**
 * Controlador de pedidos.
 *
 * @author Kaue Correa Colling
 * @since [Versão]
 */
package net.weg.topcare.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cartorder.*;
import net.weg.topcare.service.implementation.OrdersServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/clientOrders/{id}")
    public Page<CartOrderMinimalGetDTO> getDescriptionOrder(@PathVariable Long id, @RequestParam int page, @RequestParam String sorter) {
        return ordersService.getClientOrders(id, page, sorter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartOrderMaximalGetDTO> getDescriptionOrder(@PathVariable Long id) {
        return ResponseEntity.ok(ordersService.getOrder(id));
    }

    @GetMapping("/lastOrder/{id}")
    public ResponseEntity<CartOrderMinimalGetDTO> getLastOrder(@PathVariable Long id) {
        return ResponseEntity.ok(ordersService.getLastOrder(id));
    }
}