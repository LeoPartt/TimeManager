package eu.epitech.t_dev_700.controllers.exceptions;

import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResourceNotFoundTest {

    @Test
    void testSupply_shouldCreateSupplierWithCorrectMessage() {
        Supplier<ResourceNotFound> supplier = new ResourceNotFound("User", 123L);

        ResourceNotFound exception = supplier.get();

        assertThat(exception).isInstanceOf(ResourceNotFound.class);
        assertThat(exception.getMessage()).contains("User");
        assertThat(exception.getMessage()).contains("123");
    }

    @Test
    void testSupply_withDifferentEntityAndId_shouldCreateCorrectMessage() {
        Supplier<ResourceNotFound> supplier = new ResourceNotFound("Team", 456L);

        ResourceNotFound exception = supplier.get();

        assertThat(exception.getMessage()).contains("Team");
        assertThat(exception.getMessage()).contains("456");
    }

    @Test
    void testSupply_canBeUsedInOptionalOrElseThrow() {
        assertThatThrownBy(() -> {
            throw new ResourceNotFound("Product", 789L).get();
        })
                .isInstanceOf(ResourceNotFound.class)
                .hasMessageContaining("Product")
                .hasMessageContaining("789");
    }
}
