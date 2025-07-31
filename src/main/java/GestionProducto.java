import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class GestionProducto {

    /**
     * Función para gestionar el alta de un nuevo producto
     */
    public static void altaProducto() {
        System.out.println("-- ALTA PRODUCTO -- \n\nIntroduce los siguientes datos:");

        System.out.print("Tipo producto: ");
        String tipoBollo = Main.sc.nextLine();

        System.out.print("Sabor: ");
        String sabor = Main.sc.nextLine();

        double precio = 0.0;
        try {
            System.out.print("Precio: ");
            precio = Main.sc.nextDouble();
            Main.sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes introducir un número para el precio");
            Main.sc.nextLine();
            return;
        }

        int stock = 0;
        try {
            System.out.print("Stock inicial: ");
            stock = Main.sc.nextInt();
            Main.sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes introducir un número entero para el stock");
            Main.sc.nextLine();
            return;
        }

        String claveProducto = tipoBollo.toLowerCase() + "-" + sabor.toLowerCase();

        // Verificar si el producto ya existe en el mapa
        if (Main.productosConStock.containsKey(claveProducto)) {
            System.out.println("\n ** Este producto ya existe. Se actualizará el Stock");

            // Obtener el objeto ProductoStock y actualizar su stock
            ProductoStock productoExistente = Main.productosConStock.get(claveProducto);
            productoExistente.setStock(productoExistente.getStock() + stock);

            System.out.println("\n ** Stock de " + tipoBollo + " - " + sabor + " actualizado correctamente. Nuevo stock: " + productoExistente.getStock());
        } else {
            // Si el producto no existe, crearlo y añadirlo al mapa
            Producto nuevoProducto = new Producto(tipoBollo, sabor, precio);
            Main.productosConStock.put(claveProducto, new ProductoStock(nuevoProducto, stock));

            System.out.println("\n ** " + tipoBollo + " - " + sabor + " añadido correctamente. Stock: " + stock);
        }
    }


    /**
     * Función para listar los productos
     */
    public static void listarProducto() {
        System.out.println("-- LISTADO DE PRODUCTOS --\n");

        if (Main.productosConStock.isEmpty()) {
            System.out.println("No hay productos registrados");
        } else {
            int i = 1;
            for (Map.Entry<String, ProductoStock> entry : Main.productosConStock.entrySet()) {
                ProductoStock ps = entry.getValue();
                Producto p = ps.getProducto();
                System.out.println("Producto " + (i) + " : " + p.getTipoBollo() + "\t\tSabor: " + p.getSabor() + "\t\tPrecio: " + p.getPrecio() + "€" + "\t\tStock: " + ps.getStock());
                i++;
            }
        }
    }


    /**
     * Función para buscar un producto por sabor
     * @param sabor sabor del Producto
     * @return producto filtrado por sabor
     */
    public static List<ProductoStock> buscarProductoPorSabor(String sabor) {
        List<ProductoStock> productosEncontrados = new ArrayList<>();
        for (ProductoStock ps : Main.productosConStock.values()) {
            if (ps.getProducto().getSabor().equalsIgnoreCase(sabor)) {
                productosEncontrados.add(ps);
            }
        }
        return productosEncontrados;
    }

    /**
     * Imprime los productos encontrados por sabor de la función: buscarProductoPorSabor()
     */
    public static void mostrarProductoPorSabor() {
        System.out.println("-- BUSCAR PRODUCTO POR SABOR --");
        System.out.print("\nIntroduce el sabor del producto a buscar: ");
        String saborBuscado = Main.sc.nextLine();

        List<ProductoStock> productosEncontrados = GestionProducto.buscarProductoPorSabor(saborBuscado);

        if (!productosEncontrados.isEmpty()) {
            System.out.println("\nProductos con el sabor " + saborBuscado + ":");
            for (ProductoStock ps : productosEncontrados) {
                System.out.println("* Producto: " + ps.getProducto().getTipoBollo() + "\t\tPrecio: " + ps.getProducto().getPrecio() + "\t\tStock: " + ps.getStock());
            }
        } else {
            System.out.println("No hay ningún producto con el sabor: " + saborBuscado);
        }
    }
}