package net.weg.topcare.dataLoader;

import jakarta.persistence.criteria.Order;
import net.weg.topcare.entity.*;
<<<<<<< Updated upstream
import net.weg.topcare.enums.EmployeeRole;
import net.weg.topcare.enums.FederativeUnit;
import net.weg.topcare.enums.OrderStatusEnum;
import net.weg.topcare.enums.PaymentMethod;
=======
import net.weg.topcare.enums.*;
>>>>>>> Stashed changes
import net.weg.topcare.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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
<<<<<<< Updated upstream
    private CartOrderRepository cartOrderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public static long generateRandomLong(long x) {
        Random random = new Random();
        return 1 + random.nextLong(x - 1); // Generates a long between 1 and x
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Path imagePath = Paths.get("src/main/resources/images");
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
=======
    private PetRepository petRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    private ServiceArea serviceArea;
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private PetSchedulingRepository petSchedulingRepository;

    @Override
    public void run(String... args) throws Exception {
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
//                // Create and set image for brand
//                MultipartFile file = new MockMultipartFile("brandImage" + i, "brandImage" + i + ".jpg", "image/jpeg", ("brand image content " + i).getBytes());
//                Image image = new Image(file);
//                imageRepository.save(image);
                Image image = new Image();
                image.setId(generateRandomLong(6));
=======
                MultipartFile file = new MockMultipartFile("brandImage" + i, "brandImage" + i + ".jpg", "image/jpeg", ("brand image content " + i).getBytes());
                Image image = new Image(file);
                imageRepository.save(image);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                // Create and set image for product
//                MultipartFile file = new MockMultipartFile("productImage" + i, "productImage" + i + ".jpg", "image/jpeg", ("product image content " + i).getBytes());
                Image image = new Image();
                image.setId(generateRandomLong(6));
=======
                MultipartFile file = new MockMultipartFile("productImage" + i, "productImage" + i + ".jpg", "image/jpeg", ("product image content " + i).getBytes());
                Image image = new Image(file);
                imageRepository.save(image);
>>>>>>> Stashed changes
                product.getImages().add(image);

                productRepository.save(product);
            } catch (Exception ignored) {
            }
        }

<<<<<<< Updated upstream


        CartOrder cartOrder = new CartOrder();
        cartOrder.setAddress(address);
        cartOrder.setClient(cliente);
        cartOrder.setDiscount(5.0);
        cartOrder.setFreight(19.99);
        cartOrder.setNumberOrder(1);
        cartOrder.setProductsTotal(200.0);
        cartOrder.setPaymentMethod(PaymentMethod.PIX);
        try {
            cartOrder = cartOrderRepository.save(cartOrder);
        }catch (Exception ignored) {

        }

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setChangedTime(LocalDateTime.now());
        orderStatus.setOrderStatus(OrderStatusEnum.PEDIDO_RECEBIDO);
        orderStatus.setCartOrder(cartOrder);

        try {
            orderStatusRepository.save(orderStatus);
        }catch (Exception ignored) {

        }

        cartOrder.setOrderStatuses(List.of(orderStatus));
        try {
            cartOrderRepository.save(cartOrder);
        }catch (Exception ignored) {

        }

=======
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

        Client client = clientRepository.findAll().stream().findFirst().orElse(null);
        Subsidiary subsidiary = subsidiaryRepository.findAll().stream().findFirst().orElse(null);
        List<Pet> pets = petRepository.findAll();

        if (client != null && subsidiary != null && !pets.isEmpty()) {
            createScheduling(ServiceArea.VETERINARIA, "VET001", LocalDateTime.of(2024, 9, 19, 10, 0), client, subsidiary, pets);
            createScheduling(ServiceArea.VETERINARIA, "VET002", LocalDateTime.of(2024, 10, 19, 10, 0), client, subsidiary, pets);
            createScheduling(ServiceArea.VETERINARIA, "VET003", LocalDateTime.of(2024, 8, 19, 10, 0), client, subsidiary, pets);

            createScheduling(ServiceArea.SERVICO, "SER001", LocalDateTime.of(2024, 9, 19, 10, 0), client, subsidiary, pets);
            createScheduling(ServiceArea.SERVICO, "SER002", LocalDateTime.of(2024, 10, 19, 10, 0), client, subsidiary, pets);
            createScheduling(ServiceArea.SERVICO, "SER003", LocalDateTime.of(2024, 8, 19, 10, 0), client, subsidiary, pets);
        }

        List<Pet> pet = petRepository.findAll();
        List<Service> service = serviceRepository.findAll();

        if (!pet.isEmpty() && !service.isEmpty()) {
            if (pet.size() > 2) {
                createPetScheduling(pet.get(0), service);
                createPetScheduling(pet.get(1), service);
                createPetScheduling(pet.get(2), service);
            } else {
                System.out.println("Não há animais de estimação suficientes disponíveis para agendamento.");
            }
        }



    }

    private void createScheduling(ServiceArea serviceArea, String schedulingNumber, LocalDateTime date, Client client, Subsidiary subsidiary, List<Pet> pets) {
        Scheduling scheduling = new Scheduling();
        scheduling.setSchedulingNumber(schedulingNumber);
        scheduling.setServiceArea(serviceArea);
        scheduling.setScheduledDate(date);
        scheduling.setClient(client);
        scheduling.setSubsidiary(subsidiary);
        scheduling.setPets(pets.stream().map(pet -> new PetScheduling()).toList());

        try {
            schedulingRepository.save(scheduling);
        } catch (Exception ignored) {
        }

    }

    private void createPetScheduling(Pet pet, List<Service> services) {
        PetScheduling petScheduling = new PetScheduling();
        petScheduling.setPet(pet);
        petScheduling.setServicesSelected(services);

        try {
            petSchedulingRepository.save(petScheduling);
        } catch (Exception ignored) {
        }
>>>>>>> Stashed changes
    }
}