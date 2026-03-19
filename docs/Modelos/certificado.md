# Modelo: **Certificado**

Você pode instanciar um certificado, utilizado em métodos em que o mesmo é necessário.

```java
import ai.intima.Modelos.Certificado;
import java.nio.file.Path;

// Usando java.nio.file.Path:
Certificado certificado = new Certificado(
    Path.of("caminho/do/certificado"),
    "senha-do-certificado"
);

// Ou usando String diretamente:
Certificado certificado = new Certificado(
    "caminho/do/certificado",
    "senha-do-certificado"
);
```

[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
