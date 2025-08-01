import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionClienteTest {

    private final InputStream systemIn = System.in;

    /**
     * Inicializa la lista de clientes antes de cada test
     */
    @BeforeEach
    void setUp() {
        Main.clientes = new ArrayList<>();
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

    //-----TEST DE FUNCIONES
    /**
     * Test para altaCliente()
     *   1. Simular la entrada de un cliente nuevo
     *   2. Verificar que la lista de clientes tiene un elemento
     *  3. Verificar que los datos del cliente son correctos
     */
    @Test
    public void testAltaCliente_Correcto() {
        // 1
        String testInput = "Javi\n33333333C\n666000333\njavi@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.altaCliente();

        // 2
        assertEquals(1, Main.clientes.size(), "Debería haber un cliente en la lista");

        // 3
        Cliente c = Main.clientes.get(0);
        assertEquals("Javi", c.getNombre());
        assertEquals("33333333C", c.getDni());
        assertEquals("666000333", c.getTelefono());
        assertEquals("javi@mail.com", c.getEmail());
    }

    /**
     * Test para altaCliente() cuando se intenta añadir un cliente con un DNI existente
     *  1. Añadir un cliente inicial
     *  2. Simular la entrada de un segundo cliente con el mismo DNI
     *  3. Verificar que la lista de clientes no aumenta
     */
    @Test
    public void testAltaCliente_DniExistente() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "Xayah\n33333333C\n666000444\nxayah@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.altaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "No debería haber añadido un cliente con el mismo DNI.");
    }

    /**
     * Test para bajaCliente() con posición válida
     * 1. Añadir dos clientes a la lista
     * 2. Simular la entrada del usuario para borrar el cliente en la posición 2
     * 3. Verificar que la lista tiene 1 solo cliente y que el cliente restante es el correcto
     */
    @Test
    public void testBajaCliente_PosicionValida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));
        Main.clientes.add(new Cliente("Xayah", "44444444D", "666000444", "xayah@mail.com"));

        // 2
        String testInput = "2\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.bajaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "Debería quedar 1 solo cliente después de la baja");
        assertEquals("Javi", Main.clientes.get(0).getNombre(), "El cliente incorrecto ha sido borrado");
    }

    /**
     * Test para bajaCliente() con una posición fuera de rango
     *   1. Añadir un cliente a la lista
     *   2. Simular la entrada del usuario con una posición no válida
     *  3. Verificar que la lista no ha cambiado
     */
    @Test
    public void testBajaCliente_PosicionInvalida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "99\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.bajaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "La lista no debería cambiar si la posición no es válida");
    }

    /**
     * Test para bajaCliente() con una entrada que no sea un número
     *  1. Añadir un cliente a la lista
     *  2. Simular una entrada de texto en lugar de un número
     *  3. Verificar que la lista no cambia
     */
    @Test
    public void testBajaCliente_EntradaNoNumerica() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "a\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.bajaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "La lista no debería cambiar si la entrada no es un número");
    }

    /**
     * Test para modificarCliente()
     *  1. Añadir un cliente para modificar
     *  2. Simular la entrada del usuario para modificar todos los campos del cliente
     *  3. Verificar que los datos del cliente se han actualizado correctamente
     */
    @Test
    public void testModificarCliente_Correcto() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "1\nXayah\n44444444D\n666000444\nxayah@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.modificarCliente();

        // 3
        Cliente clienteModificado = Main.clientes.get(0);
        assertEquals("Xayah", clienteModificado.getNombre());
        assertEquals("44444444D", clienteModificado.getDni());
        assertEquals("666000444", clienteModificado.getTelefono());
        assertEquals("xayah@mail.com", clienteModificado.getEmail());
    }

    /**
     * Test para buscarClientePorDNI() -> DNI SI encontrado
     *  1. Añadir un cliente para buscar
     *  2. Ejecutar el metodo con un DNI existente
     *   3. Verificar que el cliente fue encontrado y que es el correcto
     */
    @Test
    public void testBuscarClientePorDNI_Encontrado() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        Cliente clienteEncontrado = GestionCliente.buscarClientePorDNI("33333333C");

        // 3
        assertNotNull(clienteEncontrado, "El cliente debería ser encontrado");
        assertEquals("Javi", clienteEncontrado.getNombre());
    }

    /**
     * Test para buscarClientePorDNI() -> DNI NO es encontrado.
     *  1. Añadir un cliente
     *  2. Ejecutar el metodo con un DNI que no existe
     *  3. Verificar que el cliente no fue encontrado (el resultado es null)
     */
    @Test
    public void testBuscarClientePorDNI_NoEncontrado() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        Cliente clienteEncontrado = GestionCliente.buscarClientePorDNI("99999999Z");

        // 3
        assertNull(clienteEncontrado, "El cliente no debería ser encontrado");
    }

    /**
     * Test para listarClientes()
     *   1. Añadir clientes en desorden alfabético
     *  2. Llamar al metodo listarClientes() que se encarga de ordenar la lista
     *  3. Comprobar que la lista está ordenada correctamente por nombre
     */
    @Test
    public void testListarClientes_OrdenAlfabetico() {
        // 1
        Main.clientes.add(new Cliente("Xayah", "44444444D", "666000444", "xayah@mail.com"));
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));
        Main.clientes.add(new Cliente("Hada", "55555555E", "666000555", "hada@mail.com"));

        // 2
        GestionCliente.listarClientes();

        // 3
        assertEquals("Hada", Main.clientes.get(0).getNombre(), "El primer cliente debería ser Hada.");
        assertEquals("Javi", Main.clientes.get(1).getNombre(), "El segundo cliente debería ser Javi.");
        assertEquals("Xayah", Main.clientes.get(2).getNombre(), "El tercer cliente debería ser Xayah.");
    }

    /**
     * Test para seleccionarCliente() con una posición válida
     *  1. Añadir un cliente a la lista
     *  2. Simular la entrada del usuario para seleccionar el cliente
     *  3. Verificar que el cliente devuelto no es nulo y que es el correcto
     */
    @Test
    public void testSeleccionarCliente_PosicionValida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "1\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Scanner testScanner = new Scanner(System.in);
        Main.sc = new Scanner(System.in); //

        Cliente clienteSeleccionado = GestionCliente.seleccionarCliente(Main.clientes, Main.sc);

        // 3
        assertNotNull(clienteSeleccionado, "El cliente no debería ser nulo.");
        assertEquals("Javi", clienteSeleccionado.getNombre(), "Se seleccionó el cliente incorrecto.");
    }

    /**
     * Test para seleccionarCliente() con una posición NO válida
     *  1. Añadir un cliente a la lista
     *  2. Simular la entrada del usuario con una posición fuera de rango
     *  3. Verificar que el metodo devuelve null
     */
    @Test
    public void testSeleccionarCliente_PosicionInvalida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "33333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "8\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Scanner testScanner = new Scanner(System.in);
        Main.sc = new Scanner(System.in);

        Cliente clienteSeleccionado = GestionCliente.seleccionarCliente(Main.clientes, Main.sc);

        // 3
        assertNull(clienteSeleccionado, "El método debería devolver null para una posición inválida.");
    }
}