package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.cliente.ClienteGetDTO;
import net.weg.topcare.controller.dto.cliente.ClientePostDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Cliente extends Usuario{
    @OneToOne
    private Endereco enderecoPrincipal;

    @OneToMany
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Scheduling> agendamentos = new ArrayList<>();

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Carrinho carrinho;

    @OneToMany(mappedBy = "cliente") // cascade = CascadeType.REFRESH
    private List<Pagamento> pagamentos = new ArrayList<>();

    @ManyToMany
    private List<Produto> produtosFavoritos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente") // cascade = CascadeType.DETACH
    private List<Pet> pets = new ArrayList<>();

    @OneToOne
    private Imagem banner;

    @OneToOne
    private Imagem fotoPerfil;

    public Cliente(ClientePostDTO dto) {
        BeanUtils.copyProperties(dto, this);

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(this);
        this.setCarrinho(carrinho);


    }

    public ClienteGetDTO toGetDTO() {;
        return new ClienteGetDTO(
                this.getId(),
                this.getNome(),
                this.getEmail(),
                this.getCpf(),
                this.getTelefone(),
                this.getEnderecoPrincipal(),
                this.getEnderecos(),
                this.getCarrinho(),
                this.getProdutosFavoritos(),
                this.getPets(),
                this.getBanner(),
                this.getFotoPerfil(),
                this.getDataNascimento());
    }
}
