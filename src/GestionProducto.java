import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionProducto {
    static Scanner sc = new Scanner(System.in);


    //----1 ALTA PRODUCTO---------------------------------------------------------------------------
    public static void altaProducto(){
        System.out.println("-- 1.1 GESTIÓN PRODUCTOS | ALTA PRODUCTO -- \nIntroduce los siguientes datos:");

        System.out.print("Tipo producto: ");
        String tipoBollo = sc.nextLine();

        System.out.print("Sabor: ");
        String sabor = sc.nextLine();

        System.out.print("Precio: ");
        Double precio = sc.nextDouble();
        sc.nextLine();

        Main.productos.add(new Producto(tipoBollo, sabor, precio));
        System.out.println("Producto " + tipoBollo + " añadido correctamente.");
    }


    //----2 LISTAR PRODUCTO---------------------------------------------------------------------------
    public static void listarProducto(){
        System.out.println("-- 1.2 GESTIÓN PRODUCTOS | LISTAR PRODUCTOS --");

        for (int i = 0; i < Main.productos.size(); i++){
            Producto p = Main.productos.get(i);
            System.out.println((i+1) + ". Producto: " + p.getTipoBollo() + "\n\tSabor: " + p.getSabor() + "\n\tPrecio: " + p.getPrecio());
            System.out.println("\t- - - - - - - - - -");
        }
    }


    //----3 BUSCAR PRODUCTO POR SABOR---------------------------------------------------------------------------
    public static List<Producto> buscarProductoPorSabor(String sabor) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : Main.productos) {
            if (p.getSabor().equalsIgnoreCase(sabor)) {
                productosEncontrados.add(p);
            }
        }
        return productosEncontrados;
    }

    public static void mostrarProductoPorSabor(){
        System.out.println("-- 1.2 GESTIÓN PRODUCTOS | BUSCAR PRODUCTO POR SABOR --");
        System.out.print("Introduce el sabor del producto a buscar: ");
        String saborBuscado = sc.nextLine();

        List<Producto> productosEncontrados = GestionProducto.buscarProductoPorSabor(saborBuscado);

        if (!productosEncontrados.isEmpty()) {
            System.out.println("Productos con el sabor " + saborBuscado + ":");
            for (Producto p : productosEncontrados) {
                System.out.println("* * * Producto: " + p.getTipoBollo() + "\t\tPrecio: " + p.getPrecio());
            }
        } else {
            System.out.println("No se encontró ningún producto con el sabor: " + saborBuscado);
        }
    }
}
