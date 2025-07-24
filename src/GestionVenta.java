import java.util.Scanner;
import java.util.UUID;

public class GestionVenta {

    static Scanner sc = new Scanner(System.in);



    //------------------------------
    public static void crearVenta(Scanner sc) {
        System.out.println("\n--- INICIAR NUEVA VENTA ---");

        // 1 Seleccionar un cliente
        Cliente clienteSeleccionado = GestionCliente.seleccionarCliente(Main.clientes, sc);
        if (clienteSeleccionado == null) {
            System.out.println("No se pudo seleccionar un cliente. Volviendo al menú de ventas.");
            return;
        }

        // Generar un ID de venta único (puedes usar un contador simple si prefieres)
        String idVenta = UUID.randomUUID().toString().substring(0, 8); // Un ID corto y único
        Venta nuevaVenta = new Venta(clienteSeleccionado, idVenta);

        // 2 Añadir productos a la venta
        boolean SumarProductos = true;
        while (SumarProductos) {
            System.out.println("\n--- Añadir productos: ---");

            GestionProducto.listarProducto(); // Mostrar lista de productos disponibles para que el usuario elija

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

        // 3 Finalizar y guardar la venta
        if (!nuevaVenta.getLineasDeVenta().isEmpty()) { // Solo guardar si hay productos
            Main.ventas.add(nuevaVenta);

            System.out.println("\nVenta creada exitosamente. Detalles del ticket:");
            nuevaVenta.mostrarTicket();
        } else {
            System.out.println("Venta cancelada: No se añadieron productos.");
        }
    }

        //----- LISTAR TODAS VENTAS
        public static void listarVentas(){
            System.out.println("\n--- LISTADO DE TODAS LAS VENTAS ---");
            if (Main.ventas.isEmpty()) {
                System.out.println("No hay ventas registradas aún.");
                return;
            }
            for (int i = 0; i < Main.ventas.size(); i++) {
                Venta v = Main.ventas.get(i);
                System.out.println((i + 1) + ". Venta ID: " + v.getIdVenta() + "\t- Cliente: " + v.getCliente().getNombre() + "\t- Total: " + v.getTotalVenta() + "€");

                v.mostrarTicket();
            }
        }

        //-----LISTAR POR CLIENTE
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

    //----- LISTAR TODAS VENTAS por importe
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
