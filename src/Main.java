import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        * clientes: alta, baja, modificación, búsqueda por DNI, listado
        * Producto: alta bollos disponibles, listado catálogo, búsqueda por tipo
        * venta: crear venta (selecionar cliente, añadir 1 o + bollos, mostrar ventas realizadas, mostrar ventas por clientes, mostrar importe total de cada venta)
        * */
        Scanner sc = new Scanner(System.in);



        Cliente c1 = new Cliente("Joa", "123456789A", "666555444", "joa@joa.com");

        Producto p1 = new Producto("Galleta", "Chocolate", 5.90);


        //---- MENU PRINCIPAL - Definición para poder generar las funciones en los sub-menus

        /*int opcion;

        do{
            System.out.println("\n Menú de opciones");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Producto");
            System.out.println("3. Realizar una Venta");
            System.out.println("4. Mostrar ventas");
            System.out.println("0. Salir");


            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> menuGestionClientes();
                case 2 -> //menuGestionProductos();
                case 3 -> //realizarNuevaVenta();
                case 4 -> //mostrarMenuVentas();
                case 0 -> System.out.println("Gracias por usar la app!");
                default -> System.out.println("Opción no válida.");
            }
        }while (opcion !=0);*/




    }

    //---- SUBMENU 1: MENU GESTIÓN CLIENTES
    /*public static void menuGestionClientes(){
        Scanner sc = new Scanner(System.in);

        int opcion;

        do{
            System.out.println("\n --SUBMENU 1: GESTIÓN DE CLIENTES-- ");
            System.out.println("1. ALTA Clientes");
            System.out.println("2. BAJA Clientes");
            System.out.println("3. MODIFICACIÓN Clientes");
            System.out.println("4. Búsqueda por DNI");
            System.out.println("5. LISTAR todos los Clientes");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> altaCliente();
                case 2 -> bajaCliente();
                case 3 -> modificarCliente();
                case 4 -> buscarClientePorDNI();
                case 5 -> listarTodosClientes();
                case 0 -> System.out.println("Volviendo menú principal.");
                default -> System.out.println("Opción no válida.");

        }while (opcion !=0);

    }*/
        public static void altaCliente(){
            Scanner sc = new Scanner(System.in);

            System.out.println("-- 1.1 GESTIÓN CLIENTES | ALTA CLIENTE--");
            System.out.println("Introduce los siguientes datos:");

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("DNI: "); //este será el ID para comprobar si hay otro cliente igual -> hacer if equals dni
            String dni = sc.nextLine();
                //if

            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            clientes.add(new Cliente(nombre, dni, telefono, email));
            System.out.println("Cliente " + nombre + "añadido correctamente.");
        }



    //---- SUBMENU 2: MENU GESTIÓN PRODUCTOS



    //---- SUBMENU 3: REALIZAR NUEVA VENTA



    //---- SUBMENU 4: MENU MOSTRAR VENTAS



}
