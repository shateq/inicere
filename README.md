# ⚠ Experimental - 33.3% working here.

# **Inicere**
Lightweight, practical library for working with TOML files.

<details>
<summary>Library destinations.</summary>
Lightweight implementation to work on multiple files using simple operations (with event handler) built on top of the `java.nio package`.
</details>

<hr>

### Still using JSON?
Why jsnen if you can get into TOML?!

- https://github.com/toml-lang/toml/

## Getting started
- Explore javadocs: https://shateq.github.io/inicere/
- Check out [Wiki](https://github.com/shateq/inicere/wiki)

<details>
<summary><strong>Add as a dependency</strong></summary>

**Gradle**
```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation("cf.shateq:Inicere:(Version tag)")
}
```

**Maven**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>cf.shateq</groupId>
    <artifactId>Inicere</artifactId>
    <version>(Version Tag)</version>
</dependency>
```
</details>

### Then
Create a new Inicere object or use the builder. At this point you can call yourself a library user.

```java
public class AnyName {
    @Element("object")
    public boolean good = false;

    public void initialize() {
        AnyName object = new AnyName();
        // Key-value store
        Inicere inicere = new Inicere(Path.of("fileName.toml")); // Simple way
        
        inicere.set("some.key", 3.14);
        inicere.get("some.key"); // 3.14

        // Reflection, requires you to bind an object
        Inicere binding = new Inicere.Phi()
                .setFile(Path.of("file-name.toml"))
                .setObject(object)
                .build(); // Hard way
        
        binding.set("object", true);
        object.good; // true
    }
}
```

**I want more!**

<details>
<summary>You have been warned.</summary>

```java
public class Main {
    public static void main(String[] args) {
        Inicere paths = new Inicere(Path.of("file-name.toml"));
        paths.bind(new DefaultFile());
        paths.bound().key$to$integer; // 2

        paths.set("key.to.integer", 4);
        paths.bound().key$to$integer; // 4

        Inicere sections = new Inicere(new AsAFile());
        
        sections.set("version", "1.0.1");
        sections.get("version"); // "1.0.1"
    }

    @DataSection
    class DefaultFile {
       public boolean bool = false;
       public int key$to$integer = 2;
       // This field won't be processed
       transient String weatherOutside = "appealing";
    }

    @DataSection("as-a-file.toml")
    class AsAFile {
        String version = "1.0.0";
        double pi = 3.14;
    }
}

```
</details>
<hr>

### **License**
Project is licensed under MIT. Check `LICENSE` file for details.
