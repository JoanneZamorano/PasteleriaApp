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


        //Menú principal - Definición para poder generar las funciones en los sub-menus

        /*int opcion;

        do{
            opcion = sc.nextInt();

            switch (opcion){
                case 1 -> menuGestionClientes();
                case 2 -> menuGestionProductos();
                case 3 -> realizarNuevaVenta();
                case 4 -> mostrarMenuVentas();
                case 5 -> System.out.println("Gracias por usar la app!");
                default -> System.out.println("Opción no válida.");
            }
        }while (opcion !=5);*/


    }

    //Menú principal -- FUNCIONES



}
