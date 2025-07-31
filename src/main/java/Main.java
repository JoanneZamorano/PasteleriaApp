import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {

        clientes.add(new Cliente("Joa", "11111111A", "600123456", "joa@mail.com"));
        clientes.add(new Cliente("Adrian", "22222222B", "600987654", "adrian@mail.com"));
        productos.add(new Producto("Donut", "Chocolate", 1.50));
        productos.add(new Producto("Croissant", "Mantequilla", 1.20));
        productos.add(new Producto("Tarta", "Fresa", 12.99));

        //---- MENU PRINCIPAL
        int opcion;

        do{
            System.out.println("\n- - - - MENÚ PASTELERÍA - - - - ");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Producto");
            System.out.println("3. Realizar una Venta");
            System.out.println("4. Mostrar ventas");
            System.out.println("0. Salir");

            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion){
                case 1 -> menuGestionClientes(sc);
                case 2 -> menuGestionProductos(sc);
                case 3 -> realizarNuevaVenta(sc);
                case 4 -> mostrarMenuVentas(sc);

                case 0 -> System.out.println("Gracias por usar la app!");

                default -> System.out.println("Opción no válida");
             }
        }while(opcion != 0);
    }


    //---- SUBMENU 1: MENU GESTIÓN CLIENTES
     public static void menuGestionClientes(Scanner sc){
        int opcion;

        do{
            System.out.println("\n- - - - 1: GESTIÓN DE CLIENTES - - - - ");
            System.out.println("1. ALTA Clientes");
            System.out.println("2. BAJA Clientes");
            System.out.println("3. MODIFICACIÓN Clientes");
            System.out.println("4. Búsqueda por DNI");
            System.out.println("5. LISTAR todos los Clientes");
            System.out.println("0. Salir");

            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion){
                case 1 -> GestionCliente.altaCliente();//ALTA CLIENTE
                case 2 -> GestionCliente.bajaCliente(); //BAJA CLIENTE
                case 3 -> GestionCliente.modificarCliente(); //MODIFICAR CLIENTES
                case 4 -> GestionCliente.mostrarClientePorDNI(); //BUSCAR CLIENTE POR DNI
                case 5 -> GestionCliente.listarClientes();//LISTAR TODOS LOS CLIENTES

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
                }
        }while(opcion != 0);
    }

    //---- SUBMENU 2: MENU GESTIÓN PRODUCTOS
    public static void menuGestionProductos(Scanner sc){
        int opcion;

        do{
            System.out.println("\n- - - - 2. GESTIÓN DE PRODUCTOS - - - - ");
            System.out.println("1. ALTA Productos");
            System.out.println("2. LISTAR Productos");
            System.out.println("3. BÚSQUEDA por Sabor");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> GestionProducto.altaProducto(); //ALTA PRODUCTO
                case 2 -> GestionProducto.listarProducto(); //LISTAR PRODUCTO
                case 3 -> GestionProducto.mostrarProductoPorSabor(); //BUSCAR PRODUCTO POR SABOR

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
            }
        }while(opcion != 0);
    }


    //---- SUBMENU 3: MENU CREAR UNA NUEVA VENTA
    public static void realizarNuevaVenta(Scanner sc){
        int opcion;

        do{
            System.out.println("\n- - - - 3. CREAR NUEVA VENTA - - - - ");
            System.out.println("1. Crear Venta");

            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> GestionVenta.crearVenta(sc); //CREAR VENTA

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
            }
        }while(opcion != 0);
    }

    //---- SUBMENU 4: MENU MOSTRAR VENTAS
    public static void mostrarMenuVentas(Scanner sc){
        int opcion;

        do{
            System.out.println("\n- - - - 3. MOSTRAR VENTAS - - - - ");
            System.out.println("1. Mostrar TODAS las ventas");
            System.out.println("2. Mostrar las ventas por CLIENTE");
            System.out.println("3. Mostrar IMPORTE TOTAL por venta");


            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> GestionVenta.listarVentas(); //MOSTRAR TODAS LAS VENTAS
                case 2 -> GestionVenta.mostrarVentasPorCliente(sc); //MOSTRAR VENTAS POR CLIENTES
                case 3 -> GestionVenta.listarVentasSoloImporte(); //MOSTAR IMPORTE TOTAL/VENTA

                case 0 -> System.out.println("Volviendo menú principal.\n");
                default -> System.out.println("Opción no válida.");
            }
        }while(opcion != 0);
    }




}

