package wirehouse.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shoppingCart.dto.ShoppingCartDto;
import wirehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import wirehouse.dto.BookedProductsDto;

@FeignClient(name = "warehouse-service", path = "/api/v1/warehouse")
public interface WarehouseClient {

    @PostMapping("/booking")
    BookedProductsDto bookProducts(@RequestBody ShoppingCartDto shoppingCart);

    @PostMapping("/assembly")
    BookedProductsDto assembleOrder(@RequestBody AssemblyProductForOrderFromShoppingCartRequest request);
}
