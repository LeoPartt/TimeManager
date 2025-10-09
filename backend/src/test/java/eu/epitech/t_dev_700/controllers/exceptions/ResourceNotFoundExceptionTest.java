package eu.epitech.t_dev_700.controllers.exceptions;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResourceNotFoundExceptionTest {

    @Test
    void testSupply_shouldCreateSupplierWithCorrectMessage() {
        Supplier<ResourceNotFoundException> supplier = ResourceNotFoundException.supply("User", 123L);

        ResourceNotFoundException exception = supplier.get();

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        assertThat(exception.getMessage()).contains("User");
        assertThat(exception.getMessage()).contains("123");
    }

    @Test
    void testSupply_withDifferentEntityAndId_shouldCreateCorrectMessage() {
        Supplier<ResourceNotFoundException> supplier = ResourceNotFoundException.supply("Team", 456L);

        ResourceNotFoundException exception = supplier.get();

        assertThat(exception.getMessage()).contains("Team");
        assertThat(exception.getMessage()).contains("456");
    }

    @Test
    void testSupply_canBeUsedInOptionalOrElseThrow() {
        assertThatThrownBy(() -> {
            throw ResourceNotFoundException.supply("Product", 789L).get();
        })
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Product")
                .hasMessageContaining("789");
    }
}
