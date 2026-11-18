# Modelo: **Petição**

Você pode instanciar uma petição, utilizada em métodos em que a mesma é necessária:

```java
import ai.intima.Modelos.Peticao;

Peticao peticao = new Peticao(
    "caminho/da/peticao.pdf",
    "32",        // Tipo do documento. Opcional (depende do tribunal), pode ser null
    "descricao"  // Opcional, pode ser null
);

Peticao semTipo = new Peticao("caminho/da/outra-peticao.pdf");
```

[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
