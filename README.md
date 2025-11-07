# Dashboard Populacional - JavaFX, JDBC e Multithread

Sistema completo para análise de dados populacionais com interface gráfica, persistência em banco de dados e processamento multithread.

## 🚀 Tecnologias

- **Java 17**
- **JavaFX** - Interface gráfica
- **MySQL** - Banco de dados
- **JDBC** - Persistência
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades Implementadas

### Parte 1 - Modelagem e Estruturas de Dados ✅
- ✅ **Linguagem**: Variáveis, seletores, loops, organização em pacotes
- ✅ **Estruturas de Dados**: Leitura CSV, ArrayList, HashMap, ordenação
- ✅ **Orientação a Objetos**: 17 classes, encapsulamento completo
- ✅ **Herança**: Classe abstrata `Regiao` → `Estado`, `Municipio`
- ✅ **Polimorfismo**: Interface `Indicador` → `DensidadeDemografica`, `CrescimentoPopulacional`
- ✅ **Exceções**: `ArquivoInvalidoException` customizada

### Parte 2 - Interface, Persistência e Multithread ✅
- ✅ **JavaFX**: Tela inicial, carregamento de dados, dashboards
- ✅ **Gráficos**: BarChart (Top 5 estados) + PieChart (distribuição)
- ✅ **JDBC**: Persistência MySQL com padrão DAO
- ✅ **Multithread**: 2 threads paralelas para gráficos
- ✅ **Tratamento de Erros**: 3+ tipos de exceções com Alerts

## 🗄️ Banco de Dados

```sql
CREATE DATABASE dashboard_populacional;

CREATE TABLE estado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  sigla CHAR(2) UNIQUE
);

CREATE TABLE municipio (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  populacao INT,
  ano INT,
  id_estado INT REFERENCES estado(id)
);
```

## ⚙️ Configuração

1. **Instalar MySQL**
2. **Executar script SQL** (src/main/resources/database.sql)
3. **Configurar credenciais** em `ConnectionFactory.java`:
   ```java
   private static final String PASSWORD = "sua_senha_mysql";
   ```
4. **Compilar projeto**:
   ```bash
   mvn clean compile
   ```

## 🎯 Execução

```bash
# Executar aplicação
mvn javafx:run

# OU executar classe principal
java -cp target/classes com.example.poo2.javafx.DashboardApp
```

## 📁 Estrutura do Projeto

```
src/main/java/com/example/poo2/
├── dao/              # Persistência JDBC (ConnectionFactory, EstadoDAO, MunicipioDAO)
├── modelo/           # Classes de domínio (Regiao, Estado, Municipio)
├── indicadores/      # Polimorfismo (Indicador, DensidadeDemografica, CrescimentoPopulacional)
├── javafx/           # Interface gráfica (5 controllers)
├── leitor/           # Leitura de arquivos CSV
└── excecoes/         # Exceções customizadas
```

## 🔧 Como Usar

1. **Executar aplicação** → Tela inicial aparece
2. **Carregar Dados** → Selecionar arquivo CSV (dados_completos.csv)
3. **Abrir Dashboard** → Visualizar gráficos em tempo real
4. **Observar Multithread** → Gráficos carregam simultaneamente

## 📊 Formato do Arquivo CSV

```csv
Nome,Codigo,Populacao,Area,Ano
São Paulo,SP001,12396372,1521.11,2023
Rio de Janeiro,RJ001,6747815,1200.27,2023
Belo Horizonte,MG001,2530701,331.40,2023
```

## 🎨 Recursos Técnicos

### Multithread
- **BarChart**: Thread separada para Top 5 estados
- **PieChart**: Thread paralela para distribuição completa
- **Interface Responsiva**: ProgressIndicators durante carregamento

### Validação e Tratamento de Erros
- **SQLException**: Falha de conexão com banco
- **ArquivoInvalidoException**: Formato incorreto de arquivo
- **IllegalArgumentException**: População negativa, ano inválido
- **Alerts JavaFX**: Mensagens amigáveis ao usuário

### Padrões Implementados
- **DAO Pattern**: Separação de persistência
- **MVC Pattern**: Controllers JavaFX
- **Factory Pattern**: ConnectionFactory
- **Strategy Pattern**: Indicadores polimórficos

## 📈 Dashboards

### BarChart Horizontal
- **Top 5 estados** por população
- **Nomes visíveis** no eixo Y
- **Valores em milhões** no eixo X
- **Ordenação automática**

### PieChart
- **Todos os estados** com distribuição percentual
- **Legenda detalhada** com valores
- **Cores automáticas**

## ✅ Conformidade Acadêmica

Projeto 100% conforme aos requisitos:
- ✅ **Linguagem de Programação** completa
- ✅ **Estruturas de Dados** adequadas
- ✅ **Orientação a Objetos** (17 classes)
- ✅ **Herança e Polimorfismo** implementados
- ✅ **JavaFX** com gráficos interativos
- ✅ **JDBC** com MySQL
- ✅ **Multithread** funcional
- ✅ **Tratamento de Exceções** robusto