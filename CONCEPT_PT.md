# Conceitos

## 🧠 O que o .where() faz?

👉 Ele cria um binding contextual IMUTÁVEL e TEMPORÁRIO (Dentro do Run).

````
ScopedValue.where(USER, "icaro");

ScopedValue.where(USER, "icaro")
    .run(() -> {

        System.out.println(USER.get());

        });
````

onde: 

#### Durante o .run()

````
USER → "icaro"
````

#### Após sair

````
USER → sem binding
````


automaticamente.
