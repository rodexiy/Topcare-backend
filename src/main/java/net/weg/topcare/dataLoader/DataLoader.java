package net.weg.topcare.dataLoader;

import net.weg.topcare.entity.*;
import net.weg.topcare.enums.EmployeeRole;
import net.weg.topcare.enums.FederativeUnit;
import net.weg.topcare.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.weg.topcare.enums.AnimalSize.MEDIO;
import static net.weg.topcare.enums.EmployeeRole.GERENTE;
import static net.weg.topcare.enums.PetGender.MALE;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void run(String... args) throws Exception {
        ;

        Address address = new Address();
        address.setCep("89258070");
        address.setCity("São Paulo");
        address.setComplement("Casa");
        address.setDistrict("Centro");
        address.setFederativeUnit(FederativeUnit.valueOf("SP"));
        address.setIdentification("Casa");
        address.setNumber(Integer.valueOf("123"));
        address.setStreet("Rua 1");
        try {
            addressRepository.save(address);
        } catch (Exception ignored) {

        }

        Client cliente = new Client();
        cliente.setName("Correa");
        cliente.setEmail("correa@gmail.com");
        cliente.setPassword("123");
        cliente.setPhone("123456789");
        cliente.setCpf("12345678901");
        cliente.setBirthdate(LocalDate.of(1999, 12, 12));
        cliente.setMainAddress(address);
        try {
            clientRepository.save(cliente);
        } catch (Exception ignored) {
        }

        String[] brandNames = {
                "Dolores Rações",
                "Valerius",
                "PetLovers",
                "Animalia",
                "PetCare",
                "FurryFriends",
                "PetWorld",
                "HappyPets",
                "PetParadise",
                "PetEssentials"
        };
        for (int i = 0; i < brandNames.length; i++) {
            Brand brand = new Brand();
            brand.setName(brandNames[i]);
            brand.setGeneralRating(i + 1);
            try {
                // Create and set image for brand
                MultipartFile file = new MockMultipartFile("brandImage" + i, "brandImage" + i + ".jpg", "image/jpeg", ("brand image content " + i).getBytes());
                Image image = new Image(file);
                imageRepository.save(image);
                brand.setImage(image);

                brandRepository.save(brand);
            } catch (Exception ignored) {
            }
        }

        List<Brand> brands = brandRepository.findAll();
        String[] productNames = {
                "Ração Premium",
                "Brinquedo de Borracha",
                "Cama Confortável",
                "Coleira Ajustável",
                "Shampoo para Pets",
                "Arranhador para Gatos",
                "Comedouro Automático",
                "Bebedouro Portátil",
                "Osso de Nylon",
                "Casinha de Madeira"
        };
        for (int i = 0; i < productNames.length; i++) {
            Product product = new Product();
            product.setName(productNames[i]);
            product.setDescription("Description for product " + (i + 1));
            product.setPrice(ThreadLocalRandom.current().nextDouble(50.0, 200.0)); // Random price between 50.0 and 200.0
            product.setStock(10 + i);
            product.setDiscount(i);
            product.setGeneralRating(4);
            product.setBrand(brands.get(i % brands.size()));
            try {
                // Create and set image for product
                MultipartFile file = new MockMultipartFile("productImage" + i, "productImage" + i + ".jpg", "image/jpeg", ("product image content " + i).getBytes());
                Image image = new Image(file);
                imageRepository.save(image);
                product.getImages().add(image);

                productRepository.save(product);
            } catch (Exception ignored) {
            }
        }

    }
}