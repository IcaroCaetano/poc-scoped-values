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


## 🧠 O que o .run() faz?

executa um bloco de código dentro do escopo contextual criado pelo .where().

#### 1️ .where()

Prepara o binding:

USER → "icaro"

#### 2️ .run()

Ativa esse binding DURANTE a execução do lambda.


##  public static final ScopedValue<T> CONTEXT = ScopedValue.newInstance();

### 📌 Conceitualmente

Você está criando algo equivalente a:

"um identificador lógico de contexto para bindings futuros"

o estado é:

CONTEXT
↓
sem binding

O identificador somente tera valor quando for realizado o binding contextual dentro do .where