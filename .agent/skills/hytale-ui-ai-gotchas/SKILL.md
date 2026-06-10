---
name: Hytale Custom UI vs CSS & Common AI Hallucinations
description: Explains the fundamental differences between Hytale UI and standard web CSS, common AI hallucinations, and frequent syntax errors.
---

# Hytale Custom UI vs. CSS (Gotchas & Hallucinations)

Hytale Custom UI uses a markup format (`.ui`) that looks loosely like JSON or CSS, but its layout engine works fundamentally differently from HTML/CSS. **As an AI agent, you must not mix CSS paradigms into Hytale UI.**

## 1. Hytale UI is NOT HTML/CSS

| Conceito Web / CSS | Equivalente no Hytale UI |
| :--- | :--- |
| `<div>` | `Group`, `Panel`, `DecoratedContainer` |
| `<p>`, `<span>`, `<h1>` | `Label` |
| `display: flex; flex-direction: row;` | `LayoutMode: Left;` |
| `display: flex; flex-direction: column;` | `LayoutMode: Top;` |
| `justify-content: center; align-items: center;` | `LayoutMode: CenterMiddle;` ou `LayoutMode: MiddleCenter;` |
| `width: 100%; height: 100%;` | `Anchor: (Full: 0);` |
| `margin-top: 10px;` | No Hytale, espaçamentos entre filhos empilhados usam a borda oposta no `Anchor` (ex: `Anchor: (Bottom: 10)` no elemento acima). |
| `padding: 10px 20px;` | `Padding: (Horizontal: 20, Vertical: 10);` |

## 2. Common AI Hallucinations (Alucinações de IAs)

Como a sintaxe do Hytale UI é parecida com CSS (usa chaves `{}` e dois pontos `:`), IAs frequentemente alucinam injetando código CSS dentro do Hytale UI. **Isto causa crash na leitura do arquivo**.

### ❌ O que NÃO fazer (Erros Comuns de IA)
1. **Usar Porcentagens**: `Anchor: (Width: 100%);`
   - O parser do jogo tentará ler um número inteiro ou Float. Ao achar o símbolo `%`, ele retornará o erro: `Expected ,, found %`.
   - **Correção**: Use `Anchor: (Full: 0);` ou flexibilize o elemento pai com `FlexWeight`.
2. **Propriedades Kebab-Case**: `font-size: 14;` ou `background-color: #fff;`
   - Hytale usa **PascalCase**. O correto é `FontSize: 14;` e `Background: #FFFFFF;`.
3. **Propriedade Text em Botões**: `Button { Text: "Clique"; }`
   - IAs adoram dar propriedades `Text` para botões, porém um nó do tipo `Button` não suporta isso nativamente. Gera o erro `Unknown property Text on node of type Button`.
   - **Correção**: Coloque um filho `Label` dentro do botão: `Button { Label { Text: "Clique"; } }`.
4. **Uso de Propriedade `Margin`**: `Margin: (Right: 20);`
   - IAs de web dev costumam inferir a existência de `Margin` para espaçamento entre botões/elementos, o que causa `Unknown property Margin`.
   - **Correção**: O espaçamento em listas (LayoutMode `Left`, `Top`, etc) é feito usando a borda oposta no próprio **Anchor**. Ex: `Anchor: (Width: 140, Height: 40, Right: 20);`.
5. **Valores CSS como "auto"**: `Width: auto;`
   - O Hytale não entende `"auto"`. Deixe o Width sem declaração se o LayoutMode lidar com isso, ou use `FlexWeight: 1`.
6. **Variáveis CSS**: `var(--cor-primaria)`
   - O Hytale usa **Expressões Nomeadas** com `@`. Exemplo: `@CorPrimaria = #FF0000;` e depois usa como `Background: @CorPrimaria;`.
7. **Nós Inexistentes (ex: `ScrollArea`)**: `ScrollArea { ... }`
   - IAs podem alucinar e criar nós focados em funcionalidade web como `ScrollArea`, `Div`, ou `View`. Isso vai causar o erro `Unknown node type: ScrollArea` no cliente Hytale.
   - **Correção**: O Hytale não possui um nó próprio para scroll. Para criar uma área rolável, use um nó `Group` padrão, mas aplique o `LayoutMode: TopScrolling` (ou `LeftScrolling`). O Hytale se encarregará de adicionar a barra de rolagem (`ScrollbarStyle`) automaticamente!
8. **HorizontalAlignment (Left/Right vs Start/End)**: `HorizontalAlignment: Right;`
   - IAs costumam usar propriedades CSS para alinhamento (como `Right` ou `Left`). No Hytale UI, os valores do enum de `LabelAlignment` são baseados em layout direcional.
   - **Correção**: Sempre utilize `Start` (ao invés de Left), `Center` (para meio) e `End` (ao invés de Right). Do contrário, gerará o erro `Could not resolve expression for property HorizontalAlignment`.

## 3. Erros Básicos Frequentes de Sintaxe

- **Esquecer o `#` para IDs ou `@` para Expressões**: Se você tentar dar ID a um grupo fazendo `Group MeuGrupo {}` ao invés de `Group #MeuGrupo {}`, o parser falhará.
- **Ordem de Declaração das Expressões**: Uma Expressão Nomeada (`@Variavel = Valor`) **deve** ser declarada no topo do bloco, antes das propriedades comuns (como `Anchor`, `LayoutMode`, etc) e antes dos elementos filhos.
- **Quebras de Linha em Strings (`\n`)**: O parser só aceita escapes como `\\` ou `\"`. Tentar usar `\n` dentro de uma string vai gerar o erro `\ must be followed by \ or "`. **Solução**: Crie dois `Label` separados dentro de um `Group` com `LayoutMode: MiddleCenter` ou `Top` (para empilhamento vertical).
- **Sintaxe de Cores (Alpha)**: Embora `#rrggbbaa` funcione, a documentação recomenda a notação `#rrggbb(a.a)` para transparências, por exemplo: `#000000(0.5)` para 50% de transparência.
- **Strings vs Translation Keys**: Strings literais usam aspas `"Meu Texto"`. Chaves de tradução usam o prefixo `%` sem aspas, ex: `Text: %ui.login.button;`.

> [!WARNING]
> Sempre lembre: O parser de Custom UI do Hytale é extremamente rigoroso. Erros de vírgula, esquecer o ponto-e-vírgula `;` no final das declarações de propriedades, ou usar CSS inválido fará a tela inteira não carregar no jogo.

### 4. Overlapping Elements no Editor (Flex Shrink vs Absoluto)

Se os elementos de uma lista horizontal (como botões num Footer) ficarem "enfiados um dentro do outro" ou encavalados no Hytale UI Studio, evite usar `LayoutMode: Left` acompanhado de `Padding` forçando larguras exatas para preencher o contêiner inteiro. 
- **Motivo:** O Studio usa React/CSS. Se o espaço interno livre (devido ao padding) for milimetricamente menor que o conteúdo (devido a uma borda `1px` padrão no editor), o comportamento CSS fará os itens encolherem ou sobreporem o conteúdo vizinho se não puderem encolher.
- **Correção:** Prefira sempre usar **`LayoutMode: CenterMiddle`** ou semelhantes no contêiner para empilhar e centralizar automaticamente. Use `Anchor: (Right: X)` no primeiro elemento para aplicar o espaçamento entre eles, sem depender de `Padding` extremo no contêiner pai para empurrá-los.
