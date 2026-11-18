# Modelo: **Documento**

Você pode instanciar um documento, utilizado em métodos em que o mesmo é necessário:

```java
import ai.intima.Modelos.Documento;

Documento documento = new Documento(
    "caminho/do/documento.pdf",
    1,           // Ordem (primeiro, segundo, terceiro...) de processamento/envio
    "32",        // Tipo do documento. Opcional (depende do tribunal), pode ser null
    "descricao"  // Opcional, pode ser null
);

Documento semTipo = new Documento("caminho/do/outro-documento.pdf", 2);
```

[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
