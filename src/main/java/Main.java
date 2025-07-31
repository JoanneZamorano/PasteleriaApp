import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 */

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static HashMap<String, ProductoStock> productosConStock = new HashMap<>();
    static ArrayList<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {

        clientes.add(new Cliente("Joa", "11111111A", "600123456", "joa@mail.com"));
        clientes.add(new Cliente("Adrian", "22222222B", "600987654", "adrian@mail.com"));

        productosConStock.put("donut-chocolate", new ProductoStock(new Producto("Donut", "Chocolate", 1.50), 2));
        productosConStock.put("galleta-coco", new ProductoStock(new Producto("Galleta", "Coco", 1.20), 3));
        productosConStock.put("tarta-fresa", new ProductoStock(new Producto("Tarta", "Fresa", 12.99), 5));

        //---- MENU PRINCIPAL
        int opcion;

        do {
            System.out.println("\n* * * MENÚ PASTELERÍA * * *");
            System.out.println("\t1. Gestión de Clientes");
            System.out.println("\t2. Gestión de Producto");
            System.out.println("\t3. Realizar una Venta");
            System.out.println("\t4. Mostrar ventas");

            System.out.println("\n\t0. Cierre de la aplicación");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Tienes que introducir un número");
                sc.nextLine();
                opcion = -1;
            }

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
            System.out.println("\n1: GESTIÓN DE CLIENTES");
            System.out.println("\t1. ALTA Clientes");
            System.out.println("\t2. BAJA Clientes");
            System.out.println("\t3. MODIFICACIÓN Clientes");
            System.out.println("\t4. Búsqueda por DNI");
            System.out.println("\t5. LISTAR todos los Clientes");

            System.out.println("\n\t0. Salir > Menu Principal");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Tienes que introducir un número");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion){
                case 1 -> GestionCliente.altaCliente();//ALTA CLIENTE
                case 2 -> GestionCliente.bajaCliente(); //BAJA CLIENTE
                case 3 -> GestionCliente.modificarCliente(); //MODIFICAR CLIENTES
                case 4 -> GestionCliente.mostrarClientePorDNI(); //BUSCAR CLIENTE POR DNI
                case 5 -> GestionCliente.listarClientes();//LISTAR TODOS LOS CLIENTES

                case 0 -> System.out.println("Volviendo menú principal");
                default -> System.out.println("Opción no válida");
                }
        }while(opcion != 0);
    }

    //---- SUBMENU 2: MENU GESTIÓN PRODUCTOS
    public static void menuGestionProductos(Scanner sc){
        int opcion;

        do{
            System.out.println("\n2. GESTIÓN DE PRODUCTOS");
            System.out.println("\t1. ALTA Productos + AÑADIR STOCK en productos existentes");
            System.out.println("\t2. LISTAR Productos");
            System.out.println("\t3. BÚSQUEDA por Sabor");

            System.out.println("\n\t0. Salir > Menu Principal");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Tienes que introducir un número");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion){
                case 1 -> GestionProducto.altaProducto(); //ALTA PRODUCTO
                case 2 -> GestionProducto.listarProducto(); //LISTAR PRODUCTO
                case 3 -> GestionProducto.mostrarProductoPorSabor(); //BUSCAR PRODUCTO POR SABOR

                case 0 -> System.out.println("Volviendo menú principal");
                default -> System.out.println("Opción no válida");
            }
        }while(opcion != 0);
    }


    //---- SUBMENU 3: MENU CREAR UNA NUEVA VENTA
    public static void realizarNuevaVenta(Scanner sc){
        int opcion;

        do{
            System.out.println("\n3. CREAR NUEVA VENTA");
            System.out.println("\t1. Crear Venta");

            System.out.println("\n\t0. Salir > Menu Principal");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Tienes que introducir un número");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion){
                case 1 -> GestionVenta.crearVenta(sc); //CREAR VENTA

                case 0 -> System.out.println("Volviendo menú de Venta");
                default -> System.out.println("Opción no válida");
            }
        }while(opcion != 0);
    }

    //---- SUBMENU 4: MENU MOSTRAR VENTAS
    public static void mostrarMenuVentas(Scanner sc){
        int opcion;

        do{
            System.out.println("\n4. MOSTRAR VENTAS");
            System.out.println("\t1. Mostrar TODAS las ventas");
            System.out.println("\t2. Mostrar las ventas por CLIENTE");
            System.out.println("\t3. Mostrar IMPORTE TOTAL por venta");

            System.out.println("\n\t0. Salir > Menu Principal");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Tienes que introducir un número");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion){
                case 1 -> GestionVenta.listarVentas(); //MOSTRAR TODAS LAS VENTAS
                case 2 -> GestionVenta.mostrarVentasPorCliente(); //MOSTRAR VENTAS POR CLIENTES
                case 3 -> GestionVenta.listarVentasSoloImporte(); //MOSTAR IMPORTE TOTAL/VENTA

                case 0 -> System.out.println("Volviendo menú principal");
                default -> System.out.println("Opción no válida");
            }
        }while(opcion != 0);
    }




}

