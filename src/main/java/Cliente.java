
/**
 * Clase Cliente
 * @author Joanne Zamorano
 * @version 1.0
 */

public class Cliente {

    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    //Constructor:
    /**
     * Constructor de la clase Cliente:
     * @param nombre Nombre del Cliente.
     * @param dni DNI del Cliente.
     * @param telefono Teléfono del Cliente.
     * @param email E-mail del Cliente.
     */
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    //getters:
    /**
     * Devuelve el nombre del Cliente.
     * @return Nombre del Cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el DNI del Cliente
     * @return DNI del Cliente
     */
    public String getDni() {
        return dni;
    }

    /**
     * Devuelve el teléfono del Cliente
     * @return Teléfono del Cloente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Devuelve el e-mail del Cliente
     * @return Email del Cliente
     */
    public String getEmail() {
        return email;
    }


    //Setters:
    /**
     * Método para modificar el nombre de un cliente
     * @param nombre Nuevo nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para modificar el DNI de un cliente
     * @param dni Nuevo DNI del Cliente
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método para modificar el Teléfono del Cliente
     * @param telefono Nuevo teléfono del Cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para modificar el E-mail del Cliente
     * @param email Nuevo email del Cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }


    }


