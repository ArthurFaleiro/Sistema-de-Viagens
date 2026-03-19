1. Sistema de Gerenciamento de Viagens ✈️
Linguagem: Java (JDK 17+)

Paradigma: Orientação a Objetos Puro

Este sistema foi projetado para gerenciar a complexidade de um roteiro turístico, utilizando associações entre classes para calcular orçamentos e organizar cronogramas.

📂 Estrutura de Pastas
Plaintext
Sistema-de-Viagens/
├── Acomodacao.java      # Atributos de hotel/estadia
├── Atividade.java       # Detalhes de passeios e horários
├── ItemBagagem.java     # Gestão de peso e tipo de itens
├── Orcamento.java       # Lógica de cálculo total da viagem
├── Usuario.java         # Dados do viajante e autenticação
├── Viagem.java          # Classe principal (Agregadora)
└── SistemaViagens.java  # Ponto de entrada (Main)

🧬 Estrutura de Classes e Lógica
Classe Viagem: Atua como o "Hub". Ela possui uma lista de Atividade e uma instância de Acomodacao.

Classe Orcamento: Recebe dados de todas as outras classes para gerar o valor final, aplicando taxas ou descontos.

Atributos Chave:

Usuario: Nome, CPF, Passaporte.

Acomodacao: Check-in, Check-out, Valor Diária.


---
Desenvolvido por [Arthur Faleiro](https://github.com/ArthurFaleiro)
