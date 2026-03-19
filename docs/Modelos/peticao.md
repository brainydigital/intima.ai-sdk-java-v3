# Modelo: **Petição**

Você pode instanciar uma petição, utilizada em métodos em que a mesma é necessária:

```java
import ai.intima.Modelos.Peticao;
import java.nio.file.Path;

// Usando java.nio.file.Path:
Peticao peticao = new Peticao(
    Path.of("caminho/da/peticao"),
    32,          // Tipo do documento. Opcional (depende do tribunal)
    "descricao"  // Opcional
);

// Ou usando String diretamente:
Peticao peticao = new Peticao(
    "caminho/da/peticao",
    32,          // Tipo do documento. Opcional (depende do tribunal)
    "descricao"  // Opcional
);
```

[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
