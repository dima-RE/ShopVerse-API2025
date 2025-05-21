# Guía detallada de anotaciones en Spring Boot
   Spring Boot se apoya fuertemente en el uso de anotaciones para simplificar la configuración y automatizar muchos procesos del framework.
   Estas anotaciones permiten que el desarrollador escriba menos código, evite archivos de configuración extensos y se enfoque en la lógica del negocio.

Las anotaciones en Java son una forma de agregar metadatos al código, y en el contexto de Spring Boot, sirven para:

* Declarar controladores, servicios, repositorios, etc.


* Mapear rutas HTTP a métodos (GET, POST, PUT, DELETE)


* Controlar la inyección de dependencias


* Configurar el comportamiento del proyecto sin usar XML


A continuación, te presento las anotaciones más importantes que vas a usar durante el desarrollo de tu proyecto ShopVerse, con ejemplos prácticos y explicaciones claras.

## @RestController
### ¿Qué hace?
Marca una clase como un controlador RESTful, permitiendo que todos los métodos dentro de esa clase devuelvan objetos directamente en formato JSON o XML (según el cliente), sin necesidad de escribir @ResponseBody en cada método.

### ¿Cómo funciona?
Es una combinación de dos anotaciones:


@Controller: que marca una clase como componente de la capa web


@ResponseBody: que indica que el valor retornado por cada método debe enviarse como respuesta HTTP directamente


### ¿Cuándo usarla?
Siempre que estés construyendo una API que devuelva datos (por ejemplo: listas de productos, detalles de usuarios, etc.), no vistas HTML.

#### 💡 Ejemplo:
```java
@RestController

@RequestMapping("/api/products")

public class ProductController {

    @GetMapping

    public List<String> getProducts() {

        return Arrays.asList("Laptop", "Mouse", "Monitor");

    }

}
```

## @RequestMapping
### ¿Qué hace?
Se utiliza para mapear una ruta de solicitud HTTP a una clase o método específico del controlador. Puedes definir el tipo de solicitud (GET, POST, etc.) y la ruta en una sola anotación.

### ¿Cómo funciona?
Puede colocarse a nivel de clase (para definir la ruta base), y/o a nivel de método.


Puedes usar atributos como value, method, params, headers, etc.


### ¿Cuándo usarla?
Es útil cuando necesitas control más avanzado sobre la ruta o cuando quieres trabajar con múltiples atributos de mapeo.

##### Ejemplo:
```java
@RequestMapping(value = "/productos", method = RequestMethod.GET)

public List<String> getAll() {

    return Arrays.asList("Producto A", "Producto B");

}
```

## @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
### ¿Qué hacen?
Son anotaciones específicas para los métodos HTTP más comunes.
Simplifican @RequestMapping y son más legibles.

@GetMapping → obtener datos


@PostMapping → enviar/crear nuevos datos


@PutMapping → actualizar datos existentes


@DeleteMapping → eliminar recursos


### ¿Cómo funcionan?
Internamente, son atajos de @RequestMapping(method = ...), pero más expresivos.

### ¿Cuándo usarlas?
Úsalas siempre que sepas con claridad el tipo de solicitud que manejará el método. Son ideales para APIs RESTful.

##### Ejemplo:
```java
@GetMapping("/products")

public List<Product> getAllProducts() { }

@PostMapping("/products")

public Product createProduct(@RequestBody Product product) { }
```

## @RequestParam
¿Qué hace?
Vincula parámetros de consulta de una solicitud HTTP (query parameters) a los argumentos de un método.

¿Cómo funciona?
Spring lee los parámetros enviados en la URL (?nombre=valor) y los convierte automáticamente al tipo adecuado.

¿Cuándo usarla?
Cuando necesitas capturar valores simples pasados como parámetros en la URL, como filtros o paginación.

Ejemplo:
@GetMapping("/saludo")

public String saludar(@RequestParam String nombre) {

    return "Hola, " + nombre;

}



Al llamar http://localhost:8081/saludo?nombre=María, devuelve "Hola, María".

## @PathVariable
¿Qué hace?
Permite capturar partes variables dentro de la URL (por ejemplo, un ID).

¿Cómo funciona?
Spring extrae el valor directamente desde la ruta y lo asigna al parámetro del método.

¿Cuándo usarla?
Cuando necesitas obtener un recurso específico por su identificador (como GET /products/2).

💡 Ejemplo:
@GetMapping("/products/{id}")

public Product getById(@PathVariable Long id) {

    return service.findById(id);

}

## @RequestBody
¿Qué hace?
Indica que el cuerpo de la solicitud HTTP debe deserializarse (convertirse) a un objeto Java automáticamente.

¿Cómo funciona?
Spring usa Jackson (o Gson) para transformar JSON en un objeto Java. Es útil para solicitudes POST y PUT.

¿Cuándo usarla?
Cuando envías un objeto complejo en el cuerpo de la solicitud (JSON, XML, etc.).

Ejemplo:

@PostMapping("/products")

public Product crear(@RequestBody Product product) {

    return service.save(product);

}



Se espera un JSON como:

{

"id": 1,

"name": "Teclado",

"description": "Mecánico RGB",

"price": 49.99

}

## @ModelAttribute (avanzado)
¿Qué hace?
Vincula valores del modelo (como formularios HTML o parámetros de una vista) a un objeto Java.

¿Cómo funciona?
Similar a @RequestParam, pero pensado para formularios complejos (más usado con Thymeleaf o aplicaciones web con HTML).

¿Cuándo usarla?
En aplicaciones web tradicionales (MVC), no tanto en APIs REST.

Ejemplo:
@PostMapping("/registro")

public String registrar(@ModelAttribute Usuario usuario) {

    return "registro-exitoso";

}

## @Configuration
¿Qué hace?
Marca una clase como contenedora de definiciones de configuración y beans de Spring.

¿Cómo funciona?
Permite declarar beans manualmente dentro de métodos anotados con @Bean.

Ejemplo:
@Configuration

public class AppConfig {



    @Bean

    public Servicio servicio() {

        return new ServicioImpl();

    }

}

## @ComponentScan
¿Qué hace?
Indica a Spring en qué paquetes buscar clases anotadas con @Component, @Service, @Repository, etc.

¿Cómo funciona?
Se usa junto a @Configuration o en la clase principal de la app para que Spring descubra los componentes.

Ejemplo:
@ComponentScan(basePackages = "com.technova.shopverse")

public class AppConfig { ... }

## @Service y @Repository
¿Qué hacen?
Marcan clases como componentes de lógica de negocio (@Service) o de acceso a datos (@Repository), para que puedan ser inyectadas y gestionadas por Spring.

Ejemplo:
@Service

public class ProductService { ... }



@Repository

public class ProductRepository { ... }

## Tabla resumen – Anotaciones en Spring Boot
Anotación

¿Para qué sirve?

¿Dónde se usa?

Tipo de operación común

@RestController

Marca una clase como controlador REST. Devuelve datos (JSON) directamente.

Clase (controladores)

Todas (GET, POST, etc.)

@RequestMapping

Mapea rutas HTTP. Se puede usar para clases o métodos con configuraciones extra.

Clase o método

Definir rutas personalizadas

@GetMapping

Maneja solicitudes HTTP GET.

Método

Obtener datos

@PostMapping

Maneja solicitudes HTTP POST.

Método

Crear nuevos datos

@PutMapping

Maneja solicitudes HTTP PUT.

Método

Actualizar datos existentes

@DeleteMapping

Maneja solicitudes HTTP DELETE.

Método

Eliminar datos

@RequestParam

Captura parámetros en la URL (?nombre=valor).

Método (parámetro)

Filtros, búsquedas, paginación

@PathVariable

Captura partes de la URL dinámica (/producto/1).

Método (parámetro)

Obtener recurso por ID

@RequestBody

Recibe un objeto completo desde el cuerpo de la solicitud (JSON).

Método (parámetro)

Crear o actualizar recursos

@Service

Marca una clase como lógica de negocio o servicio.

Clase

Capa de servicios

@Repository

Marca una clase como acceso a base de datos.

Clase

Capa de persistencia (JPA)

@ComponentScan

Indica a Spring qué paquetes debe escanear.

Clase de configuración

Inicio del proyecto

@Configuration

Define una clase que contiene beans manuales.

Clase

Configuración avanzada

@ModelAttribute

Enlaza parámetros de formularios a objetos Java.

Método o parámetro (MVC)

Formularios HTML (Spring MVC)

## Introducción: ¿Qué es un controlador en Spring Boot?
En una aplicación Spring Boot, un controlador (controller) es una clase que se encarga de recibir las solicitudes HTTP (como GET, POST, etc.) y responder a ellas. Se ubican dentro de la capa de presentación del sistema y son clave para construir API RESTful.

Spring Boot hace esto muy sencillo gracias a varias anotaciones. Las más importantes en esta etapa son:

@RestController
Se coloca en la clase para decirle a Spring que será un controlador REST, es decir, que las respuestas serán en formato JSON directamente.


@RequestMapping("/ruta")
Define la ruta base que va a atender el controlador. Por ejemplo, si usamos @RequestMapping("/api/products"), todo lo que venga desde esa URL será procesado por ese controlador.


@GetMapping, @PostMapping, etc.
Indican el tipo de solicitud HTTP (GET, POST, PUT, DELETE) y se colocan sobre los métodos del controlador.

## . ¿Cómo funciona?
Cada método en un controlador puede estar vinculado a una ruta específica y a un tipo de solicitud HTTP.

Por ejemplo:

@GetMapping("/api/products")

public List<Product> getAllProducts() {

    // devuelve todos los productos

}

Este método se ejecutará cuando alguien haga una solicitud GET a:

http://localhost:8081/api/products




Anotaciones de enrutamiento en Spring Boot

Anotación

Método HTTP

Uso común

@GetMapping

GET

Obtener recursos

@PostMapping

POST

Crear un nuevo recurso

@PutMapping

PUT

Actualizar un recurso existente

@DeleteMapping

DELETE

Eliminar un recurso

## Ejemplos prácticos adicionales
Obtener un solo producto por ID
@GetMapping("/{id}")

public Product getProductById(@PathVariable Long id) {

    return new Product(id, "Producto simulado", "Detalle del producto", 100.0);

}

📌 Acceso vía:

GET /api/products/2



@PathVariable se usa para capturar el valor que viene en la URL.

### Crear un nuevo producto (ejemplo básico sin persistencia)

@PostMapping

public String createProduct(@RequestBody Product product) {

    return "Producto recibido: " + product.getName();

}

📌 Acceso vía:

POST /api/products



Enviar en el body un JSON como:

{

"id": 10,

"name": "Teclado Gamer",

"description": "RGB y mecánico",

"price": 45.99

}

@RequestBody le dice a Spring que debe convertir el JSON recibido en un objeto Product.

### Actualizar un producto (simulado)
@PutMapping("/{id}")

public String updateProduct(@PathVariable Long id, @RequestBody Product product) {

    return "Producto con ID " + id + " actualizado a: " + product.getName();

}



📌 Acceso vía:

PUT /api/products/1



Actualiza el producto con ID 1 (simulado).

### Eliminar un producto
@DeleteMapping("/{id}")

public String deleteProduct(@PathVariable Long id) {

    return "Producto con ID " + id + " eliminado.";

}



📌 Acceso vía:

DELETE /api/products/3



No elimina nada realmente (no hay base de datos aún), pero muestra cómo se manejaría esa ruta.

## Buenas prácticas de enrutamiento REST
Usa nombres en plural: /products, /categories


Usa rutas predecibles y legibles: /api/products, /api/products/{id}


Separa los métodos por tipo de operación (GET, POST, etc.)


Centraliza la ruta base con @RequestMapping("/api/entidad") en la clase

## Tipos de relaciones más comunes
* Relación
   * ¿Qué significa?
      * Ejemplo

* @OneToOne
  * Un objeto está relacionado con uno y solo un objeto.
      * Un usuario y su perfil.

* @OneToMany
  * Un objeto tiene muchos relacionados.
    * Una categoría tiene muchos productos.

* @ManyToOne
  * Muchos objetos pertenecen a uno solo.
    * Muchos productos pertenecen a una categoría.

* @ManyToMany
  * Muchos objetos están relacionados con muchos.
    * Un producto puede tener muchas etiquetas.

## 📎 Lectura recomendada
[📘 Spring Boot – JPA Relationships Guide (Baeldung)](https://www.baeldung.com/jpa-join-types)

[📘 What is a DTO? – Martin Fowler](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

[📘 Spring Boot – DTOs and MapStruct](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)

# . Resolviendo errores comunes en JPA
1.2. Opción 2 – Usar anotaciones de Json
Si necesitas devolver entidades directamente (por ejemplo, en proyectos rápidos o internos), podés usar:

// En Product.java
@ManyToOne
@JsonBackReference
private Category category;

// En Category.java
@OneToMany(mappedBy = "category")
@JsonManagedReference
private List<Product> products;

O podés evitar la serialización del campo con:

// En Category.java
@JsonIgnore
private List<Product> products;

Esto le indica a Json:

* @JsonManagedReference: esta parte se serializa.

* @JsonBackReference: esta parte no se serializa, para evitar el bucle.

* @JsonIgnore: Esto funciona, pero pierdes completamente esa relación en la respuesta. Úsalo solo si sabés que no necesitás mostrar ese campo.

Útil si quieres mantener relaciones pero solo mostrar una dirección.

