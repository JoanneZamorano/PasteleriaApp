public class ProductoStock {
    private Producto producto;
    private int stock;

    public ProductoStock(Producto producto, int stock) {
        this.producto = producto;
        this.stock = stock;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método para decrementar el stock de manera segura
    public void decrementarStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        } else {
            System.out.println("No hay suficiente stock para el producto: " + producto.getTipoBollo());
        }
    }
}