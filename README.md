# Dashboard Populacional - JavaFX, JDBC e Multithread

Sistema completo para análise de dados populacionais com interface gráfica, persistência em banco de dados e processamento multithread.

## 🚀 Tecnologias

- **Java 17**
- **JavaFX** - Interface gráfica
- **MySQL** - Banco de dados
- **JDBC** - Persistência
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

### Parte 1 - Modelagem
- ✅ Leitura e validação de arquivos CSV
- ✅ Estruturas de dados (ArrayList, HashMap)
- ✅ Herança: `Regiao` → `Estado`, `Municipio`
- ✅ Polimorfismo: Interface `Indicador`
- ✅ Exceções customizadas

### Parte 2 - Interface e Persistência
- ✅ Interface JavaFX com FXML
- ✅ Gráficos: BarChart e PieChart
- ✅ Persistência JDBC (MySQL)
- ✅ Multithread para carregamento
- ✅ Tratamento de exceções

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
3. **Configurar credenciais** em `ConnectionFactory.java`
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
├── dao/              # Persistência JDBC
├── modelo/           # Classes de domínio
├── indicadores/      # Polimorfismo
├── javafx/           # Interface gráfica
├── leitor/           # Leitura de arquivos
└── excecoes/         # Exceções customizadas
```

## 🔧 Como Usar

1. **Executar aplicação**
2. **Carregar Dados**: Selecionar arquivo CSV
3. **Abrir Dashboard**: Visualizar gráficos
4. **Dados carregados em threads paralelas**

## 📊 Formato do Arquivo CSV

```
Nome,Codigo,Populacao,Area,Ano
São Paulo,SP001,12000000,1521.11,2023
Rio de Janeiro,RJ001,6700000,1200.27,2023
```

## 🎨 Recursos Implementados

- **Multithread**: Gráficos carregam simultaneamente
- **Interface Responsiva**: ProgressIndicators
- **Tratamento de Erros**: Alerts informativos
- **Validação de Dados**: Exceções customizadas
- **Persistência Robusta**: Padrão DAO