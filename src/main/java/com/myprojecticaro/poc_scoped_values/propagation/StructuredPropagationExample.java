package com.myprojecticaro.poc_scoped_values.propagation;

import java.util.concurrent.StructuredTaskScope;

public class StructuredPropagationExample {

    public void execute() throws Exception {

        ContextLogger.info("StructuredPropagationExample: Parent task started");

        // Cria um StructuredTaskScope.
        //
        // Isso representa um ESCOPO ESTRUTURADO de concorrência.
        //
        // Conceitualmente:
        //
        // Parent Task
        //    ├── Child Task A
        //    └── Child Task B
        //
        // IMPORTANTÍSSIMO:
        //
        // O execution context atual
        // (incluindo ScopedValues)
        // será herdado pelas subtasks.
        //
        // O try-with-resources garante:
        // - fechamento automático
        // - cleanup do escopo
        // - finalização estruturada
        try (var scope = StructuredTaskScope.open()) {

            // Cria uma subtask concorrente.
            //
            // scope.fork():
            //
            // - cria uma task filha
            // - normalmente em uma Virtual Thread
            // - associada ao MESMO execution scope
            //
            // DIFERENÇA CRÍTICA:
            //
            // Isso NÃO é uma async task "solta".
            //
            // Ela pertence à hierarquia estruturada
            // do escopo atual.
            scope.fork(() -> {

                ContextLogger.info("Child task A");

                return null;
            });

            // Cria outra subtask concorrente.
            //
            // Esta task pode executar:
            // - em paralelo
            // - em outra Virtual Thread
            // - em outro carrier thread
            //
            // Mesmo assim o contexto continuará disponível.
            scope.fork(() -> {

                ContextLogger.info("Child task B");

                return null;
            });

            // Aguarda TODAS as subtasks terminarem.
            // Sem isso o metodo poderia continuar antes das child tasks completarem.
            //
            // join() sincroniza
            scope.join();
        }

        ContextLogger.info("StructuredPropagationExample: Parent task completed");
    }
}