import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {
        //Cliente c1 = new Cliente("Joa", "123456789A", "666555444", "joa@joa.com");
        //Producto p1 = new Producto("Galleta", "Chocolate", 5.90);
        Scanner sc = new Scanner(System.in);

        //---- MENU PRINCIPAL - Definición para poder generar las funciones en los sub-menus
        int opcion;

        do{
            System.out.println("--MENÚ PASTELERÍA--");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Producto");
            System.out.println("3. Realizar una Venta");
            System.out.println("4. Mostrar ventas");
            System.out.println("0. Salir");

            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion){
                case 1 -> menuGestionClientes(sc);
                case 2 -> menuGestionProductos(sc);
                //case 3 -> realizarNuevaVenta(sc);
                //case 4 -> mostrarMenuVentas();

                case 0 -> System.out.println("Gracias por usar la app!");

                default -> System.out.println("Opción no válida.");
             }
        }while(opcion != 0);
    }


    //---- SUBMENU 1: MENU GESTIÓN CLIENTES
     static void menuGestionClientes(Scanner sc){
        int opcion;

        do{
            System.out.println(" --SUBMENU 1: GESTIÓN DE CLIENTES-- ");
            System.out.println("1. ALTA Clientes");
            System.out.println("2. BAJA Clientes");
            System.out.println("3. MODIFICACIÓN Clientes");
            System.out.println("4. Búsqueda por DNI");
            System.out.println("5. LISTAR todos los Clientes");
            System.out.println("0. Salir");

            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion){
                case 1 -> Cliente.altaCliente();//ALTA CLIENTE
                case 2 -> Cliente.bajaCliente(); //BAJA CLIENTE
                case 3 -> Cliente.modificarCliente();
                case 4 -> { //BUSCAR CLIENTE POR DNI
                    System.out.print("Introduce el DNI del cliente a buscar: ");
                    String dniBuscado = sc.nextLine();
                    Cliente clienteEncontrado = Cliente.buscarClientePorDNI(dniBuscado);

                    if (clienteEncontrado != null) {
                        System.out.println("\n--- Cliente Encontrado ---");
                        System.out.println("Nombre: " + clienteEncontrado.getNombre());
                        System.out.println("DNI: " + clienteEncontrado.getDni());
                        System.out.println("Teléfono: " + clienteEncontrado.getTelefono());
                        System.out.println("Email: " + clienteEncontrado.getEmail());
                        System.out.println("--------------------------\n");
                    } else {
                        System.out.println("No se encontró ningún cliente con el DNI: " + dniBuscado + "\n");
                    }
                }
                case 5 -> Cliente.listarClientes();//LISTAR TODOS LOS CLIENTES

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
                }
        }while(opcion != 0);
    }

    //---- SUBMENU 2: MENU GESTIÓN PRODUCTOS
    static void menuGestionProductos(Scanner sc){
        int opcion;

        do{
            System.out.println(" --SUBMENU 1: GESTIÓN DE PRODUCTOS-- ");
            System.out.println("1. ALTA Productos");
            System.out.println("2. LISTAR Productos");
            System.out.println("3. BÚSQUEDA por Sabor");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> Producto.altaProducto();//ALTA PRODUCTO
                case 2 -> Producto.listarProducto(); //LISTAR PRODUCTO
                case 3 -> { //BUSCAR PRODUCTO POR SABOR
                    System.out.println("-- 1.2 GESTIÓN PRODUCTOS | BUSCAR PRODUCTO POR SABOR --");
                    System.out.print("Introduce el sabor del producto a buscar: ");
                    String saborBuscado = sc.nextLine();

                    List<Producto> productosEncontrados = Producto.buscarProductoPorSabor(saborBuscado);

                    if (!productosEncontrados.isEmpty()) {
                        System.out.println("Productos con el sabor " + saborBuscado + ":");
                        for (Producto p : productosEncontrados) {
                            System.out.println("* * * Producto: " + p.getTipoBollo() + "\t\tPrecio: " + p.getPrecio());
                        }
                    } else {
                        System.out.println("No se encontró ningún producto con el sabor: " + saborBuscado);
                    }
                }

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
            }
        }while(opcion != 0);
    }

    }

