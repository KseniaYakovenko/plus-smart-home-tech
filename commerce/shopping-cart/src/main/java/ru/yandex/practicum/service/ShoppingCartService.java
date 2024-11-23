package ru.yandex.practicum.service;

import shoppingCart.dto.ChangeProductQuantityRequest;
import shoppingCart.dto.ShoppingCartDto;
import warehouse.dto.BookedProductsDto;

import java.util.Map;
import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCartDto addProducts(String username, Map<UUID, Integer> products);

    ShoppingCartDto getShoppingCart(String username);

    ShoppingCartDto removeProducts(String username, Map<UUID, Integer> products);

    void deactivateShoppingCart(String username);

    ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest request);

    BookedProductsDto bookProducts(String username);
}