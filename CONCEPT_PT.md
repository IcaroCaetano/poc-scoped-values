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


### 🚀 Isso permite nested scopes

````
ScopedValue.where(USER, "icaro")
.run(() -> {

        System.out.println(USER.get());

        ScopedValue.where(USER, "maria")
            .run(() -> {

                System.out.println(USER.get());

            });

        System.out.println(USER.get());

    });
````

Saida: 

````
icaro
maria
icaro
````

### 🧠 O que isso resolve?

ThreadLocal antigo

Você fazia:

````
USER.set("icaro");
````

👉 alterando estado global da thread.

ScopedValue

Você faz:

````
ScopedValue.where(USER, "icaro")
````

👉 criando um binding contextual IMUTÁVEL e TEMPORÁRIO.


