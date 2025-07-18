public class Cliente {
    /* private String nombre; private String dni; private String telefono; private String email;
     + Constructor, getters y setters */

    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    //Constructor:
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    //getters:
    public String getNombre() {
        return nombre;
    }
    public String getDni() {
        return dni;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }

    //Métodos
    public void listarClientes(){
        System.out.println("Nombre: " + this.nombre + "\nDNI: " + this.dni + "\nTeléfono: " + this.telefono + "\nEmail: " + this.email);
        System.out.println("\t- - - - -");
    }


}
