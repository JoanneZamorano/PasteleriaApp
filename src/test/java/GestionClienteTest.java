import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GestionClienteTest {

    private final InputStream systemIn = System.in;
    private final Scanner originalScanner = Main.sc;

    /**
     * Reinicia las listas estáticas y el Scanner antes de cada test
     */
    @BeforeEach
    void setUp() {
        Main.clientes = new ArrayList<>();
        Main.sc = new Scanner(System.in);
    }

    /**
     * Restaura los valores originales después de cada test
     */
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        Main.sc = originalScanner;
    }


    //----------- TEST DE FUNCIONES:

    /**
     * Test para altaCliente().
     *  1. Simular la entrada de un cliente nuevo
     *  2. Verificar que la lista de clientes tiene al menos un elemento
     *  3. Verificar que los datos son correctos
     */
    @Test
    public void testAltaCliente() {
        // 1
        String testInput = "Javi\n333333333C\n666000333\njavi@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.altaCliente();

        // 2
        assertEquals(1, Main.clientes.size(), "Debería haber un cliente en la lista");

        // 3
        Cliente c = Main.clientes.get(0);
        assertEquals("Javi", c.getNombre());
        assertEquals("333333333C", c.getDni());
        assertEquals("666000333", c.getTelefono());
        assertEquals("javi@mail.com", c.getEmail());
    }


    /**
     * Test testAltaCliente_DniExistente() - para comprobar que no se añade otro cliente con el MISMO DNI
     *  1. Simular la entrada de un nuevo cliente
     *  2. Simular la entrada de un segundo nuevo cliente con mismo Dni
     *  3. Verificar que no se ha añadido el segundo cliente
     */
    @Test
    public void testAltaCliente_DniExistente() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "Xayah\n333333333C\n666000444\nxayah@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.altaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "No debería haber añadido un cliente con mismo DNI");
    }


    /**
     * Test para bajaCliente
     *  1. Simular la entrada de dos nuevos clientes
     *  2. Simular la baja del usuario (borrar el cliente en la posición 2 - Xayah)
     *  3. Verifica que la lista tiene 1 solo cliente y que es el correcto - (Javi)
     */
    @Test
    public void testBajaCliente_PosicionValida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));
        Main.clientes.add(new Cliente("Xayah", "444444444D", "666000444", "xayah@mail.com"));

        // 2 --??????
        String testInput = "2\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.bajaCliente();

        // 3
        assertEquals(1, Main.clientes.size(), "Debería quedar 1 solo cliente");
        assertEquals("Javi", Main.clientes.get(0).getNombre(), "El cliente correcto debería haber sido borrado");
    }


    /**
     * Test baja cliente, con una posición válida.
     *  1. Simular la entrada de un cliente
     *  2. Simular la entrada del usuario con una posición fuera de rango
     *  3. Verificar que la lista no ha cambiado
     */
    @Test
    public void testBajaCliente_PosicionInvalida() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "2\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.bajaCliente();

        // 2
        assertEquals(1, Main.clientes.size(), "La lista no debería cambiar si la posición no es válida");
    }


    /**
     * Test para modificarCliente
     *  1. Simular añadir un cliente para modificar
     *  2. Simular la entrada del usuario para modificar todos los campos
     *  3. Verificar qeu los datos del cliente se han actualizado correctamente
     */
    @Test
    public void testModificarCliente() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));

        // 2
        String testInput = "1\nXayah\n444444444D\n666000444\nxayah@mail.com\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        Main.sc = new Scanner(System.in);

        GestionCliente.modificarCliente();

        // 3
        Cliente clienteModificado = Main.clientes.get(0);
        assertEquals("Xayah", clienteModificado.getNombre());
        assertEquals("444444444D", clienteModificado.getDni());
        assertEquals("666000444", clienteModificado.getTelefono());
        assertEquals("xayah@mail.com", clienteModificado.getEmail());
    }


    /**
     * Test para buscarClientePorDNI - DNI SI ENCONTRADO
     *  1. Simular un cliente para buscar por DNI
     *  2. Ejecutar el método a comprobar
     *  3. Verificar que el cliente fue encontrado y que es el correcto
     */
    @Test
    public void testBuscarClientePorDNI_Encontrado() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));

        // 2
        Cliente clienteEncontrado = GestionCliente.buscarClientePorDNI("333333333C");

        // 3
        assertNotNull(clienteEncontrado, "El cliente debería ser encontrado");
        assertEquals("Javi", clienteEncontrado.getNombre());
    }


    /**
     * Test para buscarClientePorDNI - DNI NO ENCONTRADO
     *  1. Simular un cliente para buscar por DNI
     *  2. Ejecutar el método a comprobar con un DNI uqe NO existe
     *  3. Verificar que el cliente NO fue encontrado
     */
    @Test
    public void testBuscarClientePorDNI_NoEncontrado() {
        // 1
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));

        // 2
        Cliente clienteEncontrado = GestionCliente.buscarClientePorDNI("99999999Z");

        // 3
        assertNull(clienteEncontrado, "El cliente no deberia ser encontrado");
    }


    /**
     * Test para listarClientes
     *  1. Añadir clientes en desorden alfabético
     *  2. Llamar al método listarClientes() para ordenar la lista
     *  3. Verificar que la lista está ordenada correctamente por nombre
     */
    @Test
    public void testListarClientes_OrdenAlfabetico() {
        // 1
        Main.clientes.add(new Cliente("Xayah", "444444444D", "666000444", "xayah@mail.com"));
        Main.clientes.add(new Cliente("Javi", "333333333C", "666000333", "javi@mail.com"));
        Main.clientes.add(new Cliente("Hada", "555555555E", "666000555", "hada@mail.com"));

        // 2
        GestionCliente.listarClientes();

        // 3
        assertEquals("Hada", Main.clientes.get(0).getNombre(), "El 1er cliente debería ser Hada");
        assertEquals("Javi", Main.clientes.get(1).getNombre(), "El 2do cliente debería ser Javi");
        assertEquals("Xayah", Main.clientes.get(2).getNombre(), "El 3er cliente debería ser Xayah");
    }
}