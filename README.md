Arquitetura de Software

Prof. Bernardo Copstein

Exercício: Arquitetura de 3 camadas


Implemente o sistema projetado a seguir utilizando a arquitetura em 3 camadas:
  
  Apresente a modelagem de classes, organizadas segundo uma arquitetura de três camadas, visando a implementação do sistema que segue:

  Especificação:
  O sistema de uma loja online mantém um catálogo de produtos e o controle de estoque. Sobre cada produto mantém-se o código, a descrição, o preço unitário de venda e a quantidade em estoque.
  O backend desse sistema deve oferecer endpoints para:
 
  Apresentar catálogo: listar código, descrição, preço de venda e quantidade em estoque de todos os produtos disponíveis para venda (estoque > 0)
  
  Efetuar uma venda: recebe o código do produto e a quantidade desejada. Retorna o custo da venda e dá baixa no estoque ou retorna o custo igual a -1 indicando que a venda não foi possível por falta de estoque. 
  Para o cálculo do custo final considere 10% de desconto para quantidades maiores que 10 unidades e 5% de imposto sobre o valor final
  
  Entrada no estoque: recebe código do produto e quantidade e atualiza o estoque. Se o produto ainda não existia no estoque cria uma entrada para ele. Caso contrário apenas atualiza a quantidade.
  
  Compras necessárias: retorna a lista dos códigos dos produtos que estão com o estoque abaixo do mínimo.
