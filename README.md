# Registro de Integrantes

Aplicación de escritorio en **Java Swing** con arquitectura **MVC** para registrar a los integrantes de proyectos educativos.

## Objetivo

Capturar la información de cada integrante (nombres, apellidos, repositorio de GitHub, tipo de estudiante y proyecto educativo), validar los datos, almacenarlos en memoria en un `ArrayList<Integrante>` y consultarlos en una tabla.

## Características

- Formulario con campos de texto, selección única (Nuevo / Antiguo) y lista desplegable de proyectos.
- Validaciones:
  - Nombres y apellidos: obligatorios, solo letras (con tildes y ñ); no permite números ni símbolos.
  - Repositorio: obligatorio, sin espacios y con formato `https://github.com/usuario/repositorio`.
  - Tipo de estudiante: debe seleccionarse una opción.
- Mensaje de confirmación al registrar con éxito.
- Tabla con los integrantes registrados.
- Persistencia temporal en memoria (no usa base de datos).

## Requisitos

- Java JDK 11 o superior (`java -version` y `javac -version` para comprobarlo).
- Terminal o un IDE (NetBeans, IntelliJ IDEA, Eclipse, VS Code).

## Estructura del proyecto

```
RegistroIntegrantes/
├── model/
│   └── Integrante.java              # Entidad (atributos encapsulados)
├── view/
│   └── FormularioIntegrante.java    # Interfaz gráfica Swing
├── controller/
│   └── IntegranteController.java    # Eventos, validaciones y coordinación
└── Main.java                        # Punto de entrada
```

## Arquitectura MVC

| Capa | Clases | Responsabilidad |
|---|---|---|
| Modelo | `Integrante` | Representa los datos de un integrante |
| Vista | `FormularioIntegrante` | Componentes Swing y acceso a los valores del formulario |
| Controlador | `IntegranteController` | Atiende el botón Registrar, valida, guarda en el `ArrayList` y coordina modelo y vista |

## Pasos de ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/TU_REPOSITORIO.git
   cd TU_REPOSITORIO
   ```
2. Compila desde la carpeta raíz del proyecto:
   ```bash
   javac Main.java model/*.java view/*.java controller/*.java
   ```
3. Ejecuta:
   ```bash
   java Main
   ```

También puedes abrir la carpeta en tu IDE y ejecutar la clase `Main`.

## Autor

- **Nombre:** _Valeria Usma Espitia_
- **Asignatura:** Programación Avanzada
