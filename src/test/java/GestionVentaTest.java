import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionVentaTest {

    private final InputStream originalSystemIn = System.in;

    /**
     * @BeforeEach Limpia las listas y prepara los datos de prueba antes de cada test
     */
    @BeforeEach
    void setUp() {
        Main.clientes = new ArrayList<>();
        Main.productos = new ArrayList<>();
        Main.ventas = new ArrayList<>();
        Main.sc = new Scanner(System.in);

        // Datos de prueba
        Main.clientes.add(new Cliente("Joa", "11111111A", "600123456", "joa@mail.com"));
        Main.clientes.add(new Cliente("Adrian", "22222222B", "600987654", "adrian@mail.com"));
        Main.productos.add(new Producto("Donut", "Chocolate", 1.50));
        Main.productos.add(new Producto("Croissant", "Mantequilla", 1.20));
        Main.productos.add(new Producto("Tarta", "Fresa", 12.99));
    }

    /**
     * @AfterEach Restaura System.in después de cada test
     */
    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }


    /**
     * Test para crearVenta() - CON PRODUCTOS
     *  1. Simular la entrada para crear una venta
     *      1. Seleccionar cliente 1 (Joa)
     *      2. Añadir producto 1 (Donut)
     *      3. Añadir producto 2 (Croissant)
     *      4. Terminar venta con 0
     *  2. Llamar al metodo
     *  3. Comprobar que la venta se ha realizado y se ha añadido a la lista
     *  4. Comprobar los datos de la venta
     */
    @Test
    void testCrearVenta_VentaExitosa() {
        // 1
        String testInput = "1\n1\n2\n0\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        // 2
        GestionVenta.crearVenta(Main.sc);

        //
        assertEquals(1, Main.ventas.size(), "Debería haber una venta en la lista");
        Venta venta = Main.ventas.get(0);

        //
        assertEquals(Main.clientes.get(0), venta.getCliente(), "La venta debe ser del cliente Adrián");
        assertEquals(2, venta.getLineasDeVenta().size(), "La venta debe tener 2 productos");
        double expectedTotal = 1.50 + 1.20;
        assertEquals(expectedTotal, venta.getTotalVenta(), 0.001, "El total de la venta no es correcto");
    }

    /**
     * Test para crearVenta() - SIN PRODUCTOS
     *  1. Simular la venta
     *      1. Seleccionar cliente 1 (Joa)
     *      2. Terminar la venta - 0
     *  2. Comprobar que no se haya creado ninguna venta
     */
    @Test
    void testCrearVenta_VentaCanceladaSinProductos() {
        // 1
        String testInput = "1\n0\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionVenta.crearVenta(Main.sc);

        // 2
        assertTrue(Main.ventas.isEmpty(), "No se debería crear una venta si no se añaden productos");
    }

    /**
     * Test para mostrarVentasPorCliente()
     *  1. Crear dos ventas de prueba
     *  2. Simular la entrada para seleccionar el cliente 2 (Adrián)
     *  3. Llamar al metodo a testear (La prueba es que no lance una excepción y que la lógica interna sea correcta)
     */
    @Test
    void testMostrarVentasPorCliente_Encontradas() {
        // 1
        Cliente cliente1 = Main.clientes.get(0); //Joa
        Cliente cliente2 = Main.clientes.get(1); //Adrián
        Producto producto1 = Main.productos.get(0); //Donut

        Venta venta1 = new Venta(cliente1);
        venta1.addProducto(producto1);
        Main.ventas.add(venta1);

        Venta venta2 = new Venta(cliente2);
        venta2.addProducto(producto1);
        Main.ventas.add(venta2);

        // 2
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.sc = new Scanner(System.in);

        // 3
        assertDoesNotThrow(() -> GestionVenta.mostrarVentasPorCliente(Main.sc));
    }

    /**
     * Test para mostrarVentasPorCliente() - SIN VENTAS
     *  1. Simular la entrada para seleccionar el cliente 1 (Joa), pero no hay ventas para él
     *  2. La lista de ventas está vacía
     */
    @Test
    void testMostrarVentasPorCliente_NoEncontradas() {
        // 1
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.sc = new Scanner(System.in);

        // 2
        assertDoesNotThrow(() -> GestionVenta.mostrarVentasPorCliente(Main.sc));
    }

    /**
     * Test para listarVentas()
     *  1. Crear una venta de prueba
     *  2. Comprobar que el metodo se ejecuta sin lanzar excepciones
     */
    @Test
    void testListarVentas() {
        // 1
        Venta venta = new Venta(Main.clientes.get(0));
        venta.addProducto(Main.productos.get(0));
        Main.ventas.add(venta);

        // 2
        assertDoesNotThrow(() -> GestionVenta.listarVentas());
    }


    /**
     * Test para listarVentasSoloImporte()
     *  1. Crear una venta de prueba
     *  2. Comprobar que el metodo se ejecuta sin lanzar excepciones
     */
    @Test
    void testListarVentasSoloImporte() {
        // 1
        Venta venta = new Venta(Main.clientes.get(0));
        venta.addProducto(Main.productos.get(0));
        Main.ventas.add(venta);

        // 2
        assertDoesNotThrow(() -> GestionVenta.listarVentasSoloImporte());
    }
}