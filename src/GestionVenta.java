import java.util.Scanner;

/**
 * Funciones para el menú de Ventas
 * @author Joanne Zamorano
 * @version 1.0
 */
public class GestionVenta {

    /**
     * Función para gestionar la creación de una nueva venta
     * @param sc Scanner para introducir datos
     */
    public static void crearVenta(Scanner sc) {
        System.out.println("\n--- INICIAR NUEVA VENTA ---");

    // 1 seleccionar un cliente
        Cliente clienteSeleccionado = GestionCliente.seleccionarCliente(Main.clientes, sc);
        if (clienteSeleccionado == null) {
            System.out.println("No se pudo seleccionar un cliente. Volviendo al menú de ventas.");
            return;
        }

        Venta nuevaVenta = new Venta(clienteSeleccionado);

    // 2 Añadir productos a la venta
        boolean SumarProductos = true;
        while (SumarProductos) {
            System.out.println("\n--- Añadir productos: ---");

            GestionProducto.listarProducto(); //lista de productos disponibles para que el usuario elija

            System.out.print("Introduce el número del producto a añadir (0 para terminar): ");
            int numProducto = sc.nextInt();
            sc.nextLine();

            if (numProducto == 0) {
                SumarProductos = false;
            } else if (numProducto > 0 && numProducto <= Main.productos.size()) {
                Producto productoAnadido = Main.productos.get(numProducto - 1);
                nuevaVenta.addProducto(productoAnadido);
                System.out.println("Producto '" + productoAnadido.getTipoBollo() + " - " + productoAnadido.getSabor() + "' añadido a la venta.");
            } else {
                System.out.println("Número de producto inválido. Inténtalo de nuevo.");
            }
        }

    //3 Guardar la venta
        if (!nuevaVenta.getLineasDeVenta().isEmpty()) { //solo guarda si hay productos
            Main.ventas.add(nuevaVenta);

            System.out.println("\nVenta creada exitosamente:");
            nuevaVenta.mostrarTicket();
        } else {
            System.out.println("Venta cancelada: No se añadieron productos.");
        }
    }


    /**
     * Función para listar TODAS las ventas
     */
    public static void listarVentas(){
        System.out.println("\n--- LISTADO DE TODAS LAS VENTAS ---");

        if (Main.ventas.isEmpty()) {
            System.out.println("No hay ventas registradas aún.");
            return;
        }

        for (int i = 0; i < Main.ventas.size(); i++) {
            Venta v = Main.ventas.get(i);
            System.out.println((i + 1) + ". Cliente: " + v.getCliente().getNombre() + "\t- Total: " + v.getTotalVenta() + "€");

            v.mostrarTicket();
        }
    }


    /**
     * Función para listar las ventas por cliente
     * @param sc Scanner para introducir datos
     */
    public static void mostrarVentasPorCliente(Scanner sc) {
        System.out.println("\n--- LISTAR VENTAS POR CLIENTE ---");

        if (Main.ventas.isEmpty()) {
            System.out.println("No hay ventas registradas en el sistema.");
            return;
        }

        Cliente clienteBuscado = GestionCliente.seleccionarCliente(Main.clientes, sc);
        if (clienteBuscado == null) {
            System.out.println("No se pudo seleccionar un cliente válido.");
            return;
        }

        System.out.println("\n--- Ventas para el Cliente: " + clienteBuscado.getNombre() + " (DNI: " + clienteBuscado.getDni() + ") ---");
        boolean ventasEncontradas = false;
        int contadorVentas = 0;

        for (Venta venta : Main.ventas) {

            if (venta.getCliente().getDni().equalsIgnoreCase(clienteBuscado.getDni())) {
                ventasEncontradas = true;
                contadorVentas++;
                System.out.println();
                venta.mostrarTicket();
            }
        }

        if (!ventasEncontradas) {
            System.out.println("No se encontraron ventas para el cliente " + clienteBuscado.getNombre() + ".");
        }
        System.out.println("------------------------------------\n");
    }


    /**
     * Función para listar TODAS las ventas por importe
     * Ejemplo Muestra: Venta 1: 19,99€
     */
    public static void listarVentasSoloImporte(){
        System.out.println("\n--- LISTADO DE TODAS LAS VENTAS ---");
        if (Main.ventas.isEmpty()) {
            System.out.println("No hay ventas registradas aún.");
            return;
        }
        for (int i = 0; i < Main.ventas.size(); i++) {
            Venta v = Main.ventas.get(i);
            System.out.println("Venta " + (i + 1) + ": " + "\t- Total: " + v.getTotalVenta() + "€");
        }
    }



}
