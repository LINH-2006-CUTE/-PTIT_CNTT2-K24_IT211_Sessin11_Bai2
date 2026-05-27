package com.example.session11_it211_bai2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductService.ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldUpdateStockSuccessfully() {
        // Given
        ProductService.Product product = new ProductService.Product("P001", 10);
        when(productRepository.findById("P001")).thenReturn(Optional.of(product));

        // When
        int newStock = productService.updateStock("P001", 5);

        // Then
        assertThat(newStock).isEqualTo(15);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // không tìm thấy sản phẩm
        when(productRepository.findById("UNKNOWN")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.updateStock("UNKNOWN", 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product not found with id: UNKNOWN");
    }
}