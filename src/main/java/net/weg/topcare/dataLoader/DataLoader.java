package net.weg.topcare.dataLoader;

import jakarta.persistence.criteria.Order;
import net.weg.topcare.entity.*;
import net.weg.topcare.enums.EmployeeRole;
import net.weg.topcare.enums.FederativeUnit;
import net.weg.topcare.enums.OrderStatusEnum;
import net.weg.topcare.enums.PaymentMethod;
import net.weg.topcare.enums.*;
import net.weg.topcare.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

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
    @Autowired
    private CartOrderRepository cartOrderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    
    private ServiceArea serviceArea;
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private PetSchedulingRepository petSchedulingRepository;

    public static long generateRandomLong(long x) {
        Random random = new Random();
        return 1 + random.nextLong(x - 1);
    }

    @Override
    public void run(String... args) throws Exception {
        Path path = Paths.get("src/main/resources/images");
        try {
            Path imagePath = path;
            Stream<Path> paths = Files.walk(imagePath);
            paths.filter(Files::isRegularFile).forEach(filePath -> {
                try {
                    Image imagem = new Image();
                    imagem.setOriginalFileName("image");
                    imagem.setContentType("image/jpeg");
                    imagem.setBytes(Files.readAllBytes(filePath));
                    imageRepository.save(imagem);
                } catch (IOException ignored) {
                }
            });
        } catch (IOException ignored) {
        }

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
            address = addressRepository.save(address);
        } catch (Exception ignored) {

        }

        Address firstAddress = addressRepository.findAll().stream().findFirst().orElse(null);
        Client cliente = new Client();
        cliente.setName("Correa");
        cliente.setEmail("correa@gmail.com");
        cliente.setPassword("123");
        cliente.setPhone("123456789");
        cliente.setCpf("12345678901");
        cliente.setBirthdate(LocalDate.of(1999, 12, 12));
        cliente.setMainAddress(firstAddress);
        try {
            cliente = clientRepository.save(cliente);
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
//                // Create and set image for brand
//                MultipartFile file = new MockMultipartFile("brandImage" + i, "brandImage" + i + ".jpg", "image/jpeg", ("brand image content " + i).getBytes());
//                Image image = new Image(file);
//                imageRepository.save(image);
//                Image image = new Image();
//                image.setId(generateRandomLong(6));
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
                Path imagePath = path;
                Stream<Path> paths = Files.walk(imagePath);
                Path filePath = paths.filter(Files::isRegularFile).toList().get(i);
                Image imagem = new Image();
                imagem.setOriginalFileName("image");
                imagem.setContentType("image/jpeg");
                try {
                    imagem.setBytes(Files.readAllBytes(filePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                product =  productRepository.save(product);
                imagem.setProduct(product);
                imageRepository.save(imagem);

            } catch (Exception ignored) {
            }
        }

        CartOrder cartOrder = new CartOrder();
        cartOrder.setAddress(address);
        cartOrder.setClient(cliente);
        cartOrder.setDiscount(5.0);
        cartOrder.setFreight(19.99);
        cartOrder.setNumberOrder(1);
        cartOrder.setProductsTotal(200.0);
        cartOrder.setPaymentMethod(PaymentMethod.PIX);
        cartOrder.setDiscount(5.0);
        try {
            cartOrder = cartOrderRepository.save(cartOrder);
        }catch (Exception ignored) {

        }

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setChangedTime(LocalDateTime.now());
        orderStatus.setOrderStatus(OrderStatusEnum.PEDIDO_RECEBIDO);
        orderStatus.setCartOrder(cartOrder);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(new Product(1L));

        Path imagePath = path;
        Stream<Path> paths = Files.walk(imagePath);
        CartOrder finalCartOrder = cartOrder;
        paths.filter(Files::isRegularFile).forEach(filePath -> {
            try {
                Image imagem = new Image();
                imagem.setOriginalFileName("image");
                imagem.setContentType("image/jpeg");
                imagem.setBytes(Files.readAllBytes(filePath));
                imageRepository.save(imagem);

                productOrder.setImage(imagem);
                productOrder.setUnitPrice(99.99);
                productOrder.setUnits(2);
                productOrder.setName("Produto pedido");
                productOrder.setCartOrder(finalCartOrder);
            } catch (IOException ignored) {
            }
        });



        try {
            productOrderRepository.save(productOrder);
        }catch (Exception ignored) {}




        try {
            orderStatusRepository.save(orderStatus);
        }catch (Exception ignored) {

        }

        cartOrder.setOrderStatuses(List.of(orderStatus));
        try {
            cartOrderRepository.save(cartOrder);
        }catch (Exception ignored) {

        }

        String[] petNames = {
                "Buddy",
                "Max",
                "Bella",
                "Charlie",
                "Lucy",
                "Cooper",
                "Daisy",
                "Milo",
                "Luna",
                "Rocky"
        };
        String[] petBreeds = {
                "Labrador",
                "Poodle",
                "Bulldog",
                "Beagle",
                "Rottweiler",
                "German Shepherd",
                "Golden Retriever",
                "Chihuahua",
                "Boxer",
                "Dachshund"
        };
        AnimalSize[] petSizes = {AnimalSize.PEQUENO, AnimalSize.MEDIO, AnimalSize.GRANDE};
        PetGender[] petGenders = {PetGender.MALE, PetGender.FEMALE};
        for (int i = 0; i < petNames.length; i++) {
            Pet pet = new Pet();
            pet.setName(petNames[i]);
            pet.setBreed(petBreeds[i]);
            pet.setSize(petSizes[i % petSizes.length]);
            pet.setGender(petGenders[i % petGenders.length]);
            pet.setWeight(ThreadLocalRandom.current().nextDouble(5.0, 30.0));
            pet.setBirthdate(LocalDate.of(2020, ThreadLocalRandom.current().nextInt(1, 13), ThreadLocalRandom.current().nextInt(1, 29)));
            pet.setAble(true);
            pet.setClient(cliente);
            try {
                MultipartFile file = new MockMultipartFile("petImage" + i, "petImage" + i + ".jpg", "image/jpeg", ("pet image content " + i).getBytes());
                Image image = new Image(file);
                imageRepository.save(image);
                pet.setPicture(image);

                petRepository.save(pet);
            } catch (Exception ignored) {
            }
        }


        List<Service> services = List.of(
                new Service(0L, ServiceArea.VETERINARIA, "Catração", "catração de pet", 10.0),
                new Service(1L, ServiceArea.SERVICO, "Banho Completo", "Banho completo", 15.0)
        );

        for (Service service : services) {
            try {
                serviceRepository.save(service);
            } catch (Exception ignored) {
            }
        }

        List<Subsidiary> subsidiaries = List.of(
                new Subsidiary(0L, address, "123456789"),
                new Subsidiary(1L, address, "987654321")
        );
        for (Subsidiary subsidiary : subsidiaries) {
            try {
                subsidiaryRepository.save(subsidiary);
            } catch (Exception ignored) {
            }
        }

        List<Service> servicos = List.of(
                new Service(null, ServiceArea.SERVICO, "Banho Completo", "Banho completo para pets", 50.0),
                new Service(null, ServiceArea.SERVICO, "Tosa", "Tosa de pets", 40.0),
                new Service(null, ServiceArea.SERVICO, "Hidratação", "Hidratação de pelagem", 30.0)
        );
        List<Service> consultas = List.of(
                new Service(null, ServiceArea.VETERINARIA, "Consulta Rotina", "Consulta veterinária de rotina", 150.0),
                new Service(null, ServiceArea.VETERINARIA, "Vacinação", "Vacinação de pets", 100.0),
                new Service(null, ServiceArea.VETERINARIA, "Exame de Sangue", "Exame de sangue para pets", 200.0)
        );

        // Salvando os serviços no banco de dados
        servicos.forEach(service -> {
            try {
                serviceRepository.save(service);
            } catch (Exception ignored) {
            }
        });

        consultas.forEach(service -> {
            try {
                serviceRepository.save(service);
            } catch (Exception ignored) {
            }
        });

        // Recuperando subsidiária
        Subsidiary subsidiary = subsidiaryRepository.findAll().get(0);  // Pegando a primeira subsidiária

        // Recuperando pets
        List<Pet> pets = petRepository.findAll().subList(0, 2);  // Selecionando 2 pets

        // Criando agendamento para serviços da área SERVICO
        createScheduling(ServiceArea.SERVICO, "SCHED001", LocalDateTime.now().plusDays(1), cliente, subsidiary, pets, servicos);

        // Criando agendamento para consultas da área VETERINARIA
        createScheduling(ServiceArea.VETERINARIA, "SCHED002", LocalDateTime.now().plusDays(2), cliente, subsidiary, pets, consultas);
    }

    private void createScheduling(ServiceArea serviceArea, String schedulingNumber, LocalDateTime date, Client client, Subsidiary subsidiary, List<Pet> pets, List<Service> services) {
        Scheduling scheduling = new Scheduling();
        scheduling.setSchedulingNumber(schedulingNumber);
        scheduling.setServiceArea(serviceArea);
        scheduling.setScheduledDate(date);
        scheduling.setClient(client);
        scheduling.setSubsidiary(subsidiary);

        // Para cada pet, crie um PetScheduling e associe os serviços
        List<PetScheduling> petSchedulings = pets.stream().map(pet -> {
            PetScheduling petScheduling = new PetScheduling();
            petScheduling.setPet(pet);
            petScheduling.setServicesSelected(services);  // Associa os serviços ao pet
            return petScheduling;
        }).toList();

        scheduling.setPets(petSchedulings);  // Associa os PetSchedulings ao agendamento

        try {
            schedulingRepository.save(scheduling);
        } catch (Exception ignored) {
        }

        // Salvando os PetSchedulings
        petSchedulings.forEach(petScheduling -> {
            try {
                petSchedulingRepository.save(petScheduling);
            } catch (Exception ignored) {
            }
        });
    }

    private void createPetScheduling(Pet pet, List<Service> services) {
        PetScheduling petScheduling = new PetScheduling();
        petScheduling.setPet(pet);
        petScheduling.setServicesSelected(services);

        try {
            petSchedulingRepository.save(petScheduling);
        } catch (Exception ignored) {
        }
    }
}