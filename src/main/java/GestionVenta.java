import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        System.out.println("\nIniciando una venta nueva");

        Cliente clienteSeleccionado = GestionCliente.seleccionarCliente(Main.clientes, sc);
        if (clienteSeleccionado == null) {
            System.out.println("VENTA CANCELADA: Cliente seleccionado no es válido");
            return;
        }

        Venta nuevaVenta = new Venta(clienteSeleccionado);
        System.out.println("\n * * * Venta asignada a: " + clienteSeleccionado.getNombre() + " * * * ");

        boolean SumarProductos = true;
        while (SumarProductos) {
            System.out.println("\nSelecciona los productos a vender de la siguiente lista:\n");
            GestionProducto.listarProducto();

            System.out.print("\nIntroduce el número del producto / 0 para finalizar la venta: ");
            int numProducto = 0;

            try {
                numProducto = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduce un número, por favor");
                sc.nextLine();
                continue;
            }

            if (numProducto == 0) {
                SumarProductos = false;
            } else if (numProducto > 0 && numProducto <= Main.productosConStock.size()) {
                List<ProductoStock> listaProductos = new ArrayList<>(Main.productosConStock.values());
                ProductoStock productoStockSeleccionado = listaProductos.get(numProducto - 1);

                if (productoStockSeleccionado.getStock() > 0) {
                    nuevaVenta.addProducto(productoStockSeleccionado.getProducto());
                    productoStockSeleccionado.decrementarStock(1);

                    System.out.println("\t - " + productoStockSeleccionado.getProducto().getTipoBollo() + " - " + productoStockSeleccionado.getProducto().getSabor() + " añadido a la venta. Stock restante: " + productoStockSeleccionado.getStock());
                } else {
                    System.out.println("\n * * * PRODUCTO AGOTADO: " + productoStockSeleccionado.getProducto().getTipoBollo() + " - " + productoStockSeleccionado.getProducto().getSabor() + " * * * ");
                }

            } else {
                System.out.println("\n * * * PRUEBA OTRA VEZ: El número de producto no es válido * * * ");
            }
        }

        if (!nuevaVenta.getLineasDeVenta().isEmpty()) {
            Main.ventas.add(nuevaVenta);
            System.out.println("\n * * * VENTA REALIZADA * * * \n");
            nuevaVenta.mostrarTicket();
        } else {
            System.out.println("\n * * * VENTA CANCELADA: No se ha añadido ningún producto * * * ");
        }
    }


    //--- LISTAR VENTAS ------------------------------------------------------------------
    /**
     * Función para listar TODAS las ventas - Muestra todos los tickets realizados
     */
    public static void listarVentas(){
        System.out.println("\n--- LISTADO DE TODAS LAS VENTAS:");

        if (Main.ventas.isEmpty()) {
            System.out.println("\n* * * No se han realizado ventas * * *");
            return;
        }

        for (int i = 0; i < Main.ventas.size(); i++) {
            Venta v = Main.ventas.get(i);
            v.mostrarTicket();
        }
    }

    /**
     * Función para listar el total de ventas ACUMULADO de cada cliente
     */
    public static void mostrarVentasPorCliente() {
        System.out.println("\n--- TOTAL DE VENTAS POR CLIENTE:");

        if (Main.ventas.isEmpty()) {
            System.out.println("\n* * * No se han realizado ventas * * *");
            return;
        }

        ArrayList<String> clientesProcesados = new ArrayList<>(); //creo lista para saber los clientes procesados

        for (Cliente cliente : Main.clientes) {
            String dniCliente = cliente.getDni();

            if (clientesProcesados.contains(dniCliente)) { //si el cliente ha sido procesado -> lo salta
                continue;
            }

            double totalAcumulado = 0.0;
            boolean tieneVentas = false;

            //recorre TODAS las ventas para encontrar las del cliente actual
            for (Venta venta : Main.ventas) {
                if (venta.getCliente().getDni().equalsIgnoreCase(dniCliente)) {
                    totalAcumulado += venta.getTotalVenta();
                    tieneVentas = true;
                }
            }

            // si el cliente tiene ventas -> imprime el total
            if (tieneVentas) {
                System.out.printf("\t- Cliente: %s \t|\tTotal acumulado: %.2f €\n", cliente.getNombre(), totalAcumulado);
            } else {
                System.out.printf("\t- Cliente: %s \t|\tTotal acumulad: 0.00 €\n", cliente.getNombre());
            }

            clientesProcesados.add(dniCliente); // añadp el DNI a la lista de procesados para no duplicar clientes
        }
    }


    /**
     * Función para listar TODAS las ventas por importe
     * Ejemplo Muestra: Venta 1: 19,99€
     */
    public static void listarVentasSoloImporte(){
        System.out.println("\n--- LISTADO VENTAS CON SU IMPORTE TOTAL:");
        if (Main.ventas.isEmpty()) {
            System.out.println("\n* * * No se han realizado ventas * * *");
            return;
        }
        for (int i = 0; i < Main.ventas.size(); i++) {
            Venta v = Main.ventas.get(i);
            System.out.printf("\n\t- Venta " + (i + 1) + ": %.2f € ", v.getTotalVenta() );
        }
        System.out.println();
    }

}
