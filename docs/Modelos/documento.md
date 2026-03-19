# Modelo: **Documento**

Você pode instanciar um documento, utilizado em métodos em que o mesmo é necessário:

```java
import ai.intima.Modelos.Documento;
import java.nio.file.Path;

// Usando java.nio.file.Path:
Documento documento = new Documento(
    Path.of("caminho/do/documento"),
    1,           // Ordem (primeiro, segundo, terceiro...) de processamento/envio
    32,          // Tipo do documento. Opcional (depende do tribunal)
    "descricao"  // Opcional
);

// Ou usando String diretamente:
Documento documento = new Documento(
    "caminho/do/documento",
    1,           // Ordem (primeiro, segundo, terceiro...) de processamento/envio
    32,          // Tipo do documento. Opcional (depende do tribunal)
    "descricao"  // Opcional
);
```

[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
