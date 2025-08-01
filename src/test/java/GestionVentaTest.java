import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionVentaTest {

    private final InputStream systemIn = System.in;

    /**
     * Prepara el entorno antes de cada test y limpia todas las listas y mapas
     */
    @BeforeEach
    void setUp() {
        Main.clientes = new ArrayList<>();
        Main.productosConStock = new HashMap<>();
        Main.ventas = new ArrayList<>();
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



    //---TEST DE FUNCIONES
    /**
     * Test para crearVenta() -> venta realizada correctamente
     *  1. Preparar un cliente y dos productos con stock
     *  2. Simular la entrada para seleccionar el cliente y los productos
     *  3. Verificar que se ha añadido una venta a la lista y que su importe total es correcto
     *  4. Comprobar que el stock de los productos ha disminuido
     */
    @Test
    public void testCrearVenta_Correcto() {
        // 1
        Cliente cliente = new Cliente("Javi", "33333333C", "666000333", "javi@mail.com");
        Main.clientes.add(cliente);
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 5));
        Main.productosConStock.put("galleta-coco", new ProductoStock(new Producto("Galleta", "Coco", 1.20), 10));

        // 2

        String testInput = "1\n1\n2\n0\n"; // cliente 1 -> producto 1-> producto 2 -> finalizar 0
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionVenta.crearVenta(Main.sc);

        // 3
        assertEquals(1, Main.ventas.size(), "Debería haber una venta en la lista");

        Venta ventaRealizada = Main.ventas.get(0);
        assertEquals(cliente, ventaRealizada.getCliente(), "La venta debería estar asignada al cliente correcto");
        assertEquals(2, ventaRealizada.getLineasDeVenta().size(), "La venta debería tener 2 productos");
        assertEquals(2.70, ventaRealizada.getTotalVenta(), 0.001, "El total de la venta no es correcto");

        // 4
        assertEquals(4, Main.productosConStock.get("donut-chocolate").getStock(), "El stock del donut debería haber -1");
        assertEquals(9, Main.productosConStock.get("galleta-coco").getStock(), "El stock de la galleta debería haber -1");
    }

    /**
     * Test para crearVenta() -> No se añaden productos
     *  1. Preparar un cliente y un producto
     *  2. Simular la entrada para seleccionar el cliente y finalizar la venta (0)
     *  3. Comprobar que no se ha añadido ninguna venta a la lista
     */
    @Test
    public void testCrearVenta_SinProductos() {
        // 1
        Cliente cliente = new Cliente("Javi", "33333333C", "666000333", "javi@mail.com");
        Main.clientes.add(cliente);
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 5));

        // 2
        String testInput = "1\n0\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionVenta.crearVenta(Main.sc);

        // 3
        assertEquals(0, Main.ventas.size(), "No debería haberse añadido ninguna venta si no hay productos");
    }

    /**
     * Test para crearVenta() -> stock agotado
     *  1. Preparar un cliente y un producto con stock 1
     *  2. Simular la entrada para intentar comprar el producto dos veces
     *  3. Verificar que la venta solo contiene un producto y que el stock final es 0
     */
    @Test
    public void testCrearVenta_StockAgotado() {
        // 1
        Cliente cliente = new Cliente("Javi", "33333333C", "666000333", "javi@mail.com");
        Main.clientes.add(cliente);
        Main.productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 1));

        // 2
        String testInput = "1\n1\n1\n0\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionVenta.crearVenta(Main.sc);

        // 3
        assertEquals(1, Main.ventas.size(), "Debería haber una venta");
        Venta ventaRealizada = Main.ventas.get(0);
        assertEquals(1, ventaRealizada.getLineasDeVenta().size(), "La venta solo debería tener 1 producto");
        assertEquals(1.50, ventaRealizada.getTotalVenta(), 0.001, "El total venta no es correcto");

        assertEquals(0, Main.productosConStock.get("donut-chocolate").getStock(), "El stock no puede ser 0");
    }
}