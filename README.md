# ⚠ Experimental - proof of concept ⚠
# Someday it'll be a thing, small but still a thing, refer to master branch.

# **Inicere**
Lightweight, practical library for working with TOML files.

<details>
<summary>Library destinations.</summary>
Lightweight implementation to work on multiple files using simple operations (with event handler) built on top of the `java.nio package`.
</details>
<hr>

### Still using JSON?
Why jsnon if you can get into TOML?!

- https://github.com/toml-lang/toml/

## Getting started
- Explore javadocs: https://shateq.github.io/inicere/

<details>
<summary>Add as a dependency, using Gradle</summary>

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    modImplementation("cf.shateq:Inicere:(Version tag)")
    include("cf.shateq:Inicere:(Version tag)")
}
```
</details>

### Then, create a new Inicere object or use the builder. And I guess you can use the library now.
```java
public class AnyName {
    @Element("object")
    public boolean good = false;

    public void initalize() {
        AnyName object = new AnyName();

        Inicere inicere = new Inicere(Path.of("fileName.toml")); // Simple way

        // Key-value store
        inicere.set("some.key", 3.14);
        System.out.println(inicere.get("some.key")); // 3.14

        Inicere config = Inicere.Phi.path("file-name.toml")
        .defaultAction(a -> System.out.println(a.getKey()))
        .subscription(a -> a.pass())
        .bind(object)
        .build(); // Hard way

        // Reflection
        config.set("object", true);
        System.out.println(object.good) // true
    }
}
```

### *I want more!*

<details>
<summary>You have been warned.</summary>

```java
public class Main {
    public static void main(String[] args) {
        Inicere i = new Inicere(Path.of("file-name.toml"));
        i.bind(new DefaultFile());
        i.bound.key$to$integer; // 2

        i.set("key.to.integer", 4);
        i.bound.key$to$integer // 4

        Inicere sections = new Inicere(AsAFile.class);
        sections.set("version", "1.0.1");

        sections.get("versioon") // "1.0.1"
    }

    @DataSection
    class DefaultFile {
       public boolean bool = false;

       public int key$to$integer = 2;
    }

    @DataSection("as-a-file.toml")
    class AsAFile {
        String version = "1.0.0"

        double pi = 3.14
    }
}
```
</details>
<hr>

### **License**
Project is licensed under MIT. Check `LICENSE` file for details.
