# Experimental concept - Inicere

Lightweight, practical library for working with TOML files.
Goal: Lightweight work multiple files using simple (with event handler)

<details open >
  <summary><h2>
    Table of Contents
  </h2></summary>

1. [Start](#getting-started)
2. [Examples](#examples)
3. [Develop](#develop)
4. [License](#license)

</details>

<hr>

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
    implementation("cf.shateq:inicere-core:(Version tag)")
}
```

</details>

## Examples

[Examples](/examples) project

<details>
<summary>Inicere instance</summary>

```java
class A {
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
</details>

<details>
<summary>Class serialization</summary>

```java
class B {
     void func() {
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

## Develop

Gradle guides, modules

## License

Licensed under MIT. Check [LICENSE](LICENSE) file for details.
