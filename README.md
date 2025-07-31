# Pasteleria APP

## Descripción funcional

Pasteleria APP es una aplicación de gestión de negocios diseñada para optimizar las operaciones de una pastelería. La aplicación permite a los usuarios:

- **Gestionar clientes:** Mantener una base de datos completa de clientes, con funciones para **dar de alta, baja y modificar** sus datos. También se pueden realizar búsquedas detalladas por DNI y generar listados de todos los clientes.

- **Gestionar productos:** Controlar el inventario de productos de pastelería, como bollitos, tartas y galletas. Se pueden **dar de alta** nuevos productos, **listar** todos los disponibles, realizar **búsquedas por sabor** y **asignar precios** a cada artículo.

- **Gestionar ventas:** Asignar productos a un cliente para generar una venta y, posteriormente, **imprimir un ticket** detallado.

- **Informes y análisis:** Obtener un panorama claro del rendimiento del negocio con funciones para **mostrar las ventas totales**, desglosar las **ventas por cliente** y **visualizar todos los tickets** generados.

## Requisitos para compilar y ejecutar

Para compilar y ejecutar este proyecto, se requiere tener instalados los siguientes elementos:

* **Java Development Kit (JDK):** Versión 8 o superior.
* **Apache Maven:** La versión 3.6 o posterior es recomendada.

Para compilar el proyecto, navega a la raíz del directorio del proyecto donde se encuentra el archivo `pom.xml` y ejecuta el siguiente comando:

```bash
mvn clean install
```

## Instrucciones de uso

Una vez que el proyecto ha sido compilado (siguiendo las instrucciones en la sección de "Requisitos para compilar y ejecutar"), puedes ejecutar la aplicación desde la línea de comandos.

1.  Abre una terminal o símbolo del sistema.
2.  Navega hasta el directorio `target` dentro de la raíz del proyecto. Aquí se encuentra el archivo `.jar` generado por Maven.

    ```bash
    cd target
    ```

3.  Ejecuta la aplicación con el siguiente comando:

    ```bash
    java -jar pasteleria.jar
    ```

4.  La aplicación se iniciará en la consola, mostrando el menú principal con las diferentes opciones para gestionar clientes, productos, ventas e informes. Sigue las instrucciones que aparezcan en pantalla para interactuar con la aplicación.

## Autoría y licencia

- **Autor:** Joanne Zamorano Berrocal.
- **Proyecto Módulos:** DAM: Programación y Entornos de Desarrollo.
- **Año:** 2025
- **Licencia:** Este proyecto tiene fines educativos, creado para practicar los conceptos aprendidos hasta este momento en el curso. 

## Documentación, pruebas y control de versiones

- **Documentación:** Se ha generado documentación del código fuente utilizando JavaDoc.
- **Pruebas unitarias:** Se han implementado pruebas unitarias con JUnit5.
- **Control de versiones:** Se utiliza Git y GitHub para el control de versiones, con las siguientes ramas:
    - `main`: Contiene la versión estable del proyecto.
    - `dev`: Rama de desarrollo activo.
    - `feature/<Joanne>`: Ramas para implementar nuevas mejoras o tareas específicas.
    - `testing`: Rama utilizada para realizar pruebas.

## Estructura del proyecto

├── docs/                                 # Documentación generada con JavaDoc
├── src/
│   ├── main/java/                        # Código fuente
│   └── test/java/                        # Tests con JUnit
├── pom.xml                               # Configuración de Maven
└── README.md