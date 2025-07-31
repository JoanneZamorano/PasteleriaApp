import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionProductoTest {

    private final InputStream originalSystemIn = System.in;

    /**
     * Limpia las listas estáticas y reinicia el Scanner antes de cada test
     */
    @BeforeEach
    void setUp() {
        Main.clientes = new ArrayList<>();
        Main.productos = new ArrayList<>();
        Main.ventas = new ArrayList<>();
        Main.sc = new Scanner(System.in);
    }

    /**
     * Restaura System.in después de cada test
     */
    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }


    /**
     * Test para altaProducto()
     *  1. Simular la entrada de un nuevo producto
     *  2. Verificar qeu el producto se ha añadido y que sus datos son correctos
     */
    @Test
    void testAltaProducto() {
        // 1
        String testInput = "Tarta chocolate\nChocolate\n5,50\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionProducto.altaProducto();

        // 2
        assertEquals(1, Main.productos.size(), "Debería haber un producto en la lista");
        Producto p = Main.productos.get(0);
        assertEquals("Tarta chocolate", p.getTipoBollo());
        assertEquals("Chocolate", p.getSabor());
        assertEquals(5.50, p.getPrecio(), 0.001);
    }


    /**
     * Test para buscarProductoPorSabor() - SABOR SI ENCONTRADO
     *  1. Añadir productos de prueba
     *  2. Ejecutar el método con un sabor existente
     *  3. Verificar que se encontró un producto y que es el correcto
     */
    @Test
    void testBuscarProductoPorSabor_Encontrado() {
        // 1
        Main.productos.add(new Producto("Galleta", "Mantequilla", 1.20));
        Main.productos.add(new Producto("Tarta", "Fresa", 12.99));

        // 2
        List<Producto> productosEncontrados = GestionProducto.buscarProductoPorSabor("Fresa");

        // 3
        assertFalse(productosEncontrados.isEmpty(), "Debe encontrar al menos un producto");
        assertEquals(1, productosEncontrados.size());
        assertEquals("Tarta", productosEncontrados.get(0).getTipoBollo());
    }

    /**
     * Test para buscarProductoPorSabor() - SABOR NO ENCONTRADO
     *  1. Añadir productos de prueba
     *  2. Ejecutar el método con un sabor que NO existe
     *  3. Verificar que NO se encontró ningún producto
     */
    @Test
    void testBuscarProductoPorSabor_NoEncontrado() {
        // 1
        Main.productos.add(new Producto("Galleta", "Mantequilla", 1.20));

        // 2
        List<Producto> productosEncontrados = GestionProducto.buscarProductoPorSabor("Limón");

        // 3
        assertTrue(productosEncontrados.isEmpty(), "No debería encontrar productos con ese sabor");
    }


    /**
     * Test para listarProducto()
     *  1. Añadir productos de prueba
     *  2. Comprobar que el método se ejecuta sin errores
     */
    @Test
    void testListarProducto() {
        // 1
        Main.productos.add(new Producto("Galleta", "Mantequilla", 1.20));
        Main.productos.add(new Producto("Tarta", "Fresa", 12.99));

        // 2
        assertDoesNotThrow(() -> GestionProducto.listarProducto());
        assertEquals(2, Main.productos.size(), "La lista debería tener 2 productos");
    }
}