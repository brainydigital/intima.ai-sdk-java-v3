# Modelo: **Parte Vinculada**

Você pode instanciar uma parte vinculada, utilizada em métodos em que a mesma é necessária.

```java
import ai.intima.Modelos.ParteVinculada;

// Sem participação:
ParteVinculada parteVinculada = new ParteVinculada("nome-parte-vinculada");

// Com participação (opcional, depende do tribunal):
ParteVinculada parteVinculada = new ParteVinculada(
    "nome-parte-vinculada",
    "participacao" // Opcional (depende do tribunal)
);
```

[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
