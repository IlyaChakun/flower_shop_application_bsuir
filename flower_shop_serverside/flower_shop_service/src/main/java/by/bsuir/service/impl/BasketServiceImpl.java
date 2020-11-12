//package by.bsuir.service.impl;
//
//import by.bsuir.dto.model.basket.AddProductBasketDTO;
//import by.bsuir.dto.model.basket.BasketDTO;
//import by.bsuir.dto.model.basket.CountedProductBasketDTO;
//import by.bsuir.dto.model.basket.UpdateProductBasketDTO;
//import by.bsuir.entity.basket.Basket;
//import by.bsuir.entity.product.AbstractFlowerProduct;
//import by.bsuir.entity.product.bouqet.FlowerBouquet;
//import by.bsuir.entity.product.flower.Flower;
//import by.bsuir.entity.user.Client;
//import by.bsuir.payload.exception.ResourceNotFoundException;
//import by.bsuir.payload.exception.ServiceException;
//import by.bsuir.repository.api.user.ClientRepository;
//import by.bsuir.service.api.BasketService;
//import lombok.AllArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class BasketServiceImpl implements BasketService {
//
//    private static final Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);
//
//    private final ClientRepository clientRepository;
//
//
//    @Override
//    @Transactional
//        public BasketDTO addProduct(AddProductBasketDTO addProductBasketDTO) {
//        Client client = findUserById(addProductBasketDTO.getUserId());
//        AbstractFlowerProduct product = resolveAbstractFlowerProduct(addProductBasketDTO.getProductId());
//
//        Basket basket = client.getBasket();
//        List<AbstractFlowerProduct> basketAbstractFlowerProducts = basket.getBasketProducts();
//        basketAbstractFlowerProducts.add(product);
//
////        BigDecimal totalPrice = basket.getTotalPrice();
////        basket.setTotalPrice(totalPrice.add(product.getPrice()));
////
////        return buildCountedProductList(new ArrayList<>(basketProducts));
//
//        BigDecimal totalPrice = basket.getTotalPrice();
//        Integer productAmount = addProductBasketDTO.getQuantity();
//        Double price = addProductBasketDTO.getProduct().getFlowerLengthCosts().stream().findFirst().get().getPrice();
//        Double cost = price * productAmount;
//
//        basket.setTotalPrice(totalPrice.add(BigDecimal.valueOf(cost)));
//
//        return buildCountedAbstractFlowerProductList(new ArrayList<>(basketAbstractFlowerProducts));
//    }
//
//    private AbstractFlowerProduct resolveAbstractFlowerProduct(Long productId) {
//        AbstractFlowerProduct product = findAbstractFlowerProductById(productId);
//
//        if (product instanceof Flower) {
//            if (product.getAvailableAmountOnStock() == 0) {
//                throw new ServiceException(HttpStatus.CONFLICT.value(),
//                        "flower_not_on_stock",
//                        "Flower with type=" + ((Flower) product).getFlowerType().getFlowerType() + " is unavailable now!");
//            }
//        } else {
//            if (product.getAvailableAmountOnStock() == 0) {
//                throw new ServiceException(HttpStatus.CONFLICT.value(),
//                        "flower_bouquet_not_on_stock",
//                        "Flower Bouquet with name=" + ((FlowerBouquet) product).getTitle() + " is unavailable now!");
//            }
//        }
//
//
//        return product;
//    }
//
//
//    @Override
//    @Transactional
//    public BasketDTO updateProduct(UpdateProductBasketDTO updateProductBasketDTO) {
//        Client client = findUserById(updateProductBasketDTO.getUserId());
//        AbstractFlowerProduct product = findAbstractFlowerProductById(updateProductBasketDTO.getProductId());
//
//        Basket basket = client.getBasket();
//        List<AbstractFlowerProduct> basketAbstractFlowerProducts = basket.getBasketProducts();
//
//        BigDecimal previousTotalPrice = basket.getTotalPrice();
//        int previousQuantity = Collections.frequency(basketAbstractFlowerProducts, product);
//
//        calculateBasketAbstractFlowerProductCount(updateProductBasketDTO, product, basket, basketAbstractFlowerProducts, previousTotalPrice, previousQuantity);
//
//        return buildCountedAbstractFlowerProductList(basketAbstractFlowerProducts);
//    }
//
//    @Override
//    @Transactional
//    public BasketDTO deleteProduct(AddProductBasketDTO addProductBasketDTO) {
//        Client client = findUserById(addProductBasketDTO.getUserId());
//        AbstractFlowerProduct product = findAbstractFlowerProductById(addProductBasketDTO.getProductId());
//
//        Basket basket = client.getBasket();
//        List<AbstractFlowerProduct> basketAbstractFlowerProducts = basket.getBasketProducts();
//
//        int quantity = Collections.frequency(basketAbstractFlowerProducts, product);
//        basketAbstractFlowerProducts.removeIf(basketAbstractFlowerProduct -> basketAbstractFlowerProduct.equals(product));
//
//        BigDecimal previousTotalPrice = basket.getTotalPrice();
//        BigDecimal newTotalPrice = previousTotalPrice.subtract(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
//        basket.setTotalPrice(newTotalPrice);
//
//
//        return buildCountedAbstractFlowerProductList(basketAbstractFlowerProducts);
//    }
//
//    @Override
//    @Transactional
//    public BasketDTO findAll(Long userId) {
//        Client client = findUserById(userId);
//        Basket basket = client.getBasket();
//        List<AbstractFlowerProduct> basketAbstractFlowerProducts = basket.getBasketProducts();
//        return buildCountedAbstractFlowerProductList(basketAbstractFlowerProducts);
//    }
//
//
//    private void calculateBasketAbstractFlowerProductCount(UpdateProductBasketDTO updateAbstractFlowerProductBasketDto,
//                                                           AbstractFlowerProduct product, Basket basket,
//                                                           List<AbstractFlowerProduct> basketAbstractFlowerProducts,
//                                                           BigDecimal previousTotalPrice,
//                                                           int previousQuantity) {
//        if (previousQuantity > updateAbstractFlowerProductBasketDto.getQuantity()) {
//            for (int i = updateAbstractFlowerProductBasketDto.getQuantity(); previousQuantity != i; i++) {
//                basketAbstractFlowerProducts.remove(product);
//                basket.setTotalPrice(previousTotalPrice.subtract(product.getPrice()));
//            }
//        } else {
//            for (int i = previousQuantity; updateAbstractFlowerProductBasketDto.getQuantity() != i; i++) {
//                basketAbstractFlowerProducts.add(product);
//                basket.setTotalPrice(previousTotalPrice.add(product.getPrice()));
//            }
//        }
//    }
//
//
//    private BasketDTO buildCountedAbstractFlowerProductList(List<AbstractFlowerProduct> products) {
//        Map<AbstractFlowerProduct, Integer> productQuantityMap = getAbstractFlowerProductsMapWithQuantity(products);
//
//        BigDecimal totalPrice = calculateTotalPrice(products);
//        int totalElements = products.size();
//
//        List<CountedProductBasketDTO> countedAbstractFlowerProductsList = convertAbstractFlowerProductMapToListWithQuantity(productQuantityMap);
//
//        return new BasketDTO(countedAbstractFlowerProductsList, totalPrice, totalElements);
//    }
//
//    private List<CountedProductBasketDTO> convertAbstractFlowerProductMapToListWithQuantity(Map<AbstractFlowerProduct, Integer> productQuantityMap) {
//        return productQuantityMap
//                .entrySet()
//                .stream()
//                .map(obj -> new CountedProductBasketDTO(productMapper.toDto(obj.getKey()), obj.getValue()))
//                .collect(Collectors.toList());
//    }
//
//    private Map<AbstractFlowerProduct, Integer> getAbstractFlowerProductsMapWithQuantity(List<AbstractFlowerProduct> products) {
//        Map<AbstractFlowerProduct, Integer> productQuantityMap = new HashMap<>();
//        products.forEach(product -> productQuantityMap.put(product, Collections.frequency(products, product)));
//        return productQuantityMap;
//    }
//
//    private BigDecimal calculateTotalPrice(List<AbstractFlowerProduct> products) {
//        return products
//                .stream()
//                .map(AbstractFlowerProduct::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//
//    private Client findUserById(Long userId) {
//        return clientRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("No user with id=" + userId));
//    }
//
//    private AbstractFlowerProduct findAbstractFlowerProductById(Long productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("No product with id=" + productId));
//    }
//}