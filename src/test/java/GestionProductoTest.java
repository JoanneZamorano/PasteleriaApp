import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionProductoTest {

    private final InputStream systemIn = System.in;

    /**
     * Prepara el entorno antes de cada test y limpia el mapa de productos
     */
    @BeforeEach
    void setUp() {
        Main.productosConStock = new HashMap<>();
    }

    /**
     * Restaura los valores originales después de cada test
     */
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        if (Main.sc != null) {
            Main.sc.close();
        }
    }


    //---TEST DE FUNCIONES:
    /**
     * Test para altaProducto() - Añadir un producto nuevo
     *  1. Simular la entrada de un producto nuevo
     *  2. Verificar que el mapa de productos tiene un elemento
     *  3. Comprobar que los datos del producto son correctos
     */
    @Test
    public void testAltaProducto_NuevoProducto() {
        // 1
        String testInput = "Bizcocho\nVainilla\n7,50\n3\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionProducto.altaProducto();

        // 2
        assertEquals(1, Main.productosConStock.size(), "Debería haber un producto en el mapa");

        // 3
        ProductoStock ps = Main.productosConStock.get("bizcocho-vainilla");
        assertNotNull(ps, "El producto no debería ser nulo");
        assertEquals("Bizcocho", ps.getProducto().getTipoBollo());
        assertEquals("Vainilla", ps.getProducto().getSabor());
        assertEquals(7.50, ps.getProducto().getPrecio());
        assertEquals(3, ps.getStock());
    }

    /**
     * Test para altaProducto() -Añadir un producto existente (se suma el stock introducido)
     *  1. Añadir un producto inicial con 3 unidades
     *  2. Simular la entrada para añadir 10 unidades más al mismo producto
     *  3. Comprobar que el stock total es 13
     */
    @Test
    public void testAltaProducto_ActualizaStock() {
        // 1
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 3));

        // 2
        String testInput = "Donut\nChocolate\n1,50\n10\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionProducto.altaProducto();

        // 3
        assertEquals(1, Main.productosConStock.size(), "No debería haber añadido un nuevo producto, solo actualizar el stock");
        ProductoStock ps = Main.productosConStock.get("donut-chocolate");
        assertEquals(13, ps.getStock(), "El stock total debería ser 13");
    }

    /**
     * Test para buscarProductoPorSabor() -> se encuentran productos
     *  1. Añadir varios productos
     *  2. Buscar productos por un sabor
     *  3. Comprobar que la lista de resultados no está vacía y que su tamaño es correcto
     */
    @Test
    public void testBuscarProductoPorSabor_Encontrado() {
        // 1
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 10));
        Main.productosConStock.put("galleta-chocolate", new ProductoStock(new Producto("Galleta", "Chocolate", 1.20), 5));
        Main.productosConStock.put("tarta-fresa", new ProductoStock(new Producto("Tarta", "Fresa", 12.99), 3));

        // 2
        var productosEncontrados = GestionProducto.buscarProductoPorSabor("chocolate");

        // 3
        assertFalse(productosEncontrados.isEmpty(), "La lista de productos no debería estar vacía");
        assertEquals(2, productosEncontrados.size(), "Debería haber 2 productos con sabor a chocolate");
    }

    /**
     * Test para buscarProductoPorSabor() -> NO se encuentran productos
     * 1. Añadir productos al mapa
     * 2. Buscar productos por un sabor que NO existe
     * 3. Verificar que la lista de resultados está vacía
     */
    @Test
    public void testBuscarProductoPorSabor_NoEncontrado() {
        // 1
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 10));

        // 2
        var productosEncontrados = GestionProducto.buscarProductoPorSabor("vainilla");

        // 3
        assertTrue(productosEncontrados.isEmpty(), "La lista de productos debería estar vacía");
    }
}