# 🛠️ Taller de Nivelación: Java + Maven

## 🎯 Objetivo
Este taller tiene como propósito nivelar conocimientos fundamentales en Java y Maven antes de iniciar el curso formal. Aprenderás a estructurar proyectos, compilar desde consola, empaquetar en `.jar`, y usar Maven para la gestión del ciclo de vida de un proyecto Java.

---

## 👨‍💻 Conceptos básicos de Programación Orientada a Objetos (POO) en Java

Java es un lenguaje orientado a objetos, lo que significa que se basa en la creación y manipulación de objetos que representan entidades del mundo real.

### 🔹 Clases y Objetos
- **Clase**: Es un molde o plantilla que describe las características y comportamientos comunes de un tipo de objeto.
- **Objeto**: Es una instancia concreta de una clase.

```java
public class Estudiante {
    String nombre;
    int edad;

    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}
```

```java
Estudiante e = new Estudiante();
e.nombre = "Juan";
e.edad = 20;
e.saludar();
```

### 🔹 Encapsulamiento
Protege los datos internos de una clase, permitiendo acceder a ellos solo a través de métodos públicos.

```java
public class Persona {
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

### 🔹 Herencia
Permite que una clase herede atributos y métodos de otra clase.

```java
public class Empleado extends Persona {
    private double salario;

    public double getSalario() {
        return salario;
    }
}
```

---

### 🔹 Polimorfismo
Permite que un objeto adopte múltiples formas, como cuando una clase sobrescribe un método de su superclase.

---

## ⚙️ Herramientas del lenguaje Java

### 🔸 `javac`
Es el **compilador de Java**, que transforma archivos `.java` en archivos `.class` (bytecode que puede interpretar la máquina virtual de Java).

```bash
javac MiClase.java
```

### 🔸 `java`
Es el **intérprete de la JVM (Java Virtual Machine)** que ejecuta archivos `.class`.

```bash
java MiClase
```

### 🔸 `.jar` (Java ARchive)
Es un formato comprimido que contiene múltiples `.class`, imágenes, recursos y el archivo `MANIFEST.MF`. Permite distribuir aplicaciones Java.

```bash
jar cfm MiApp.jar META-INF/MANIFEST.MF -C bin .
java -jar MiApp.jar
```

## ⚙️ Configuración de Entorno: JAVA_HOME y MAVEN_HOME

### 🔧 Paso 1: ¿Qué son?

- `JAVA_HOME`: variable de entorno que apunta a la carpeta donde está instalado Java (por ejemplo, `C:\Program Files\Java\jdk-17`)
- `MAVEN_HOME`: variable que apunta a la carpeta donde se instaló Maven (por ejemplo, `C:\apache-maven-3.9.0`)

---

### 🪟 Windows

#### 1. Buscar "Variables de entorno" en el Panel de Control o buscador.
#### 2. Crear o editar las variables:

- **JAVA_HOME** → `C:\Program Files\Java\jdk-17`
- **MAVEN_HOME** → `C:\apache-maven-3.9.0`

#### 3. Editar la variable `Path` y agregar:

- `%JAVA_HOME%\bin`
- `%MAVEN_HOME%\bin`

---

### 🐧 Linux / macOS

Editar `~/.bashrc` o `~/.zshrc` y agregar:

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export MAVEN_HOME=/opt/apache-maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

Luego, recarga el archivo:

```bash
source ~/.bashrc
```

---

### ✅ Validar instalación

#### Verificar que Java esté disponible:

Abre una terminal con tecla Windows --> cmd

```bash
java -version
javac -version
```

Debe mostrar una versión como: `javac 17.0.8`

#### Verificar Maven:

```bash
mvn -version
```

Debe mostrar:

```
Apache Maven 3.9.X
Java version: 17.X
```

Si todo eso funciona correctamente, ya estás listo para compilar tus proyectos.

---

## 📁 Parte 1: Fundamentos de un Proyecto Java

### Estructura recomendada

```
MiPrimerProyecto/
├── src/
│   └── main/
│       └── java/
│           └── co/
│               └── edu/
│                   └── unisabana/
│                       ├── Main.java
│                       └── modelo/
│                           └── Estudiante.java
├── bin/
└── META-INF/
    └── MANIFEST.MF
```

### Compilar desde consola

```bash
javac -d bin src/main/java/co/edu/unisabana/modelo/Estudiante.java src/main/java/co/edu/unisabana/Main.java
```

### Ejecutar

```bash
java -cp bin co.edu.unisabana.Main
```

---

## 📦 Crear archivo `.jar` ejecutable

1. Crea la carpeta `META-INF` y dentro el archivo `MANIFEST.MF`:

```
Manifest-Version: 1.0
Main-Class: co.edu.unisabana.Main
```

> Asegúrate de dejar una línea en blanco al final del archivo.

Si esto no funciona prueba con los siguientes comando en CMD:

```bash
mkdir META-INF 2>nul
(
echo Manifest-Version: 1.0
echo Main-Class: co.edu.unisabana.Main
echo.
) > META-INF\MANIFEST.MF
```

2. Crear el `.jar`:

```bash
jar cfm MiApp.jar META-INF\MANIFEST.MF -C bin .
```

> Nota: usa \ en Windows. Si tu ruta tiene espacios, pon comillas:

```bash
jar cfm MiApp.jar "META-INF\MANIFEST.MF" -C bin .
```

3. Verifica que el JAR sí contiene tu Main-Class

```bash
jar xf MiApp.jar META-INF/MANIFEST.MF
type META-INF\MANIFEST.MF
```

Deberías ver:

```
Manifest-Version: 1.0
Main-Class: co.edu.unisabana.Main
```

> (Con una línea en blanco al final.)

4. Ejecutar:

```bash
java -jar MiApp.jar
```

---

## ⚙️ Parte 2: Introducción a Maven

### ¿Qué es Maven?

Maven es una herramienta de construcción y gestión de proyectos que:
- Compila, prueba, empaqueta y despliega código.
- Administra dependencias externas automáticamente.
- Estandariza la estructura del proyecto.

### Estructura Maven

```
MiProyectoMaven/
├── pom.xml
└── src/
    ├── main/java/...
    └── test/java/...
```

### ¿Qué es `pom.xml`?

Define el proyecto, metadatos, dependencias, plugins y fases del ciclo de vida.

---

## 🌐 Repositorio Central

Maven usa el repositorio central (https://repo.maven.apache.org) para descargar librerías automáticamente. También crea un repositorio local en `~/.m2/repository`.

---

## 🔁 Ciclo de Vida de Maven

| Fase      | Propósito                            |
|-----------|--------------------------------------|
| validate  | Verifica la estructura del proyecto  |
| compile   | Compila el código fuente             |
| test      | Ejecuta pruebas                      |
| package   | Empaqueta en `.jar` o `.war`         |
| install   | Instala en el repositorio local      |
| deploy    | Publica en un repositorio remoto     |

---

# 📦 Guía: Creación de un proyecto Maven y repositorio

Este documento explica cómo **crear un proyecto Maven desde cero**, configurarlo, ejecutarlo y luego **subirlo a un repositorio GitHub** como guía de buenas prácticas.

---

## 1. 🔧 Requisitos previos
Asegúrate de tener instalado en tu máquina:
- **Java JDK** (versión 8 o superior) → `java -version`
- **Maven** (Apache Maven 3.x o superior) → `mvn -version`
- **Git** → `git --version`
- Una cuenta en [GitHub](https://github.com/)

---

## 2. 📂 Crear un proyecto Maven
Ejecuta en la terminal:

```sh
mvn archetype:generate   -DgroupId=com.ejemplo.app   -DartifactId=MiProyectoMaven   -DarchetypeArtifactId=maven-archetype-quickstart   -DinteractiveMode=false
```

Esto generará la estructura:
```
MiProyectoMaven/
 ├── pom.xml
 ├── src/
 │   ├── main/java/com/ejemplo/app/App.java
 │   └── test/java/com/ejemplo/app/AppTest.java
```

---

## 3. ⚙️ Archivo `pom.xml` comentado
Ejemplo básico con plugin para ejecutar el proyecto:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!-- Identificador de la organización o paquete base -->
  <groupId>com.ejemplo.app</groupId>
  <!-- Nombre del proyecto -->
  <artifactId>MiProyectoMaven</artifactId>
  <!-- Versión del proyecto -->
  <version>1.0-SNAPSHOT</version>

  <properties>
    <!-- Configuración del compilador Java -->
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <!-- Dependencia para pruebas unitarias con JUnit -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Plugin para ejecutar con mvn exec:java -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
          <mainClass>com.ejemplo.app.App</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

---

## 4. 🏗️ Compilar y ejecutar
- Compilar:
```sh
mvn compile
```

- Ejecutar pruebas:
```sh
mvn test
```

- Empaquetar `.jar`:
```sh
mvn package
```

- Ejecutar con `java -cp`:
```sh
java -cp target/MiProyectoMaven-1.0-SNAPSHOT.jar com.ejemplo.app.App
```

- Ejecutar con `mvn exec:java` (dos opciones):

a) Con plugin configurado en el `pom.xml`:
```sh
mvn exec:java
```

b) Sin plugin, indicando el `mainClass` manualmente:
```sh
mvn exec:java -Dexec.mainClass="com.ejemplo.app.App"
```

Con argumentos:
```sh
mvn exec:java -Dexec.mainClass="com.ejemplo.app.App" -Dexec.args="arg1 arg2"
```

---

## 5. 🚀 Crear repositorio Git

1. Inicializar Git en el proyecto:
```sh
cd MiProyectoMaven
git init
```

2. Crear un archivo `.gitignore` (ejemplo para Maven):
```
target/
*.class
*.log
*.idea/
*.iml
*.classpath
*.project
.settings/
```
Guárdalo como `.gitignore` en la raíz del proyecto.

3. Hacer primer commit:
```sh
git add .
git commit -m "Inicialización del proyecto Maven"
```

4. Crear un repositorio en GitHub desde la web.

5. Conectar el proyecto local con el remoto:
```sh
git remote add origin https://github.com/USUARIO/MiProyectoMaven.git
git branch -M main
git push -u origin main
```

---

## ✅ Resumen de flujo Maven + Git
1. Crear proyecto → `mvn archetype:generate`  
2. Compilar → `mvn compile`  
3. Probar → `mvn test`  
4. Empaquetar → `mvn package`  
5. Ejecutar → `mvn exec:java -Dexec.mainClass="com.ejemplo.app.App"`  
6. Subir a GitHub → `git push`  

---

Con esto ya tendrás un **proyecto Maven configurado, ejecutable y versionado en GitHub** como guía de referencia.

---

## 📚 Recursos

- [Documentación oficial de Maven](https://maven.apache.org/)
- [Repositorio Central Maven](https://search.maven.org/)
- [Guía rápida de Maven](https://maven.apache.org/guides/getting-started/)


---
