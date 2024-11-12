enum class Nivel { INICIANTE, INTERMEDIARIO, AVANCADO }

enum class Progresso { INSCRITO, EM_ANDAMENTO, CONCLUIDO }

class Usuario(val nome: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    private val inscritos = mutableMapOf<Usuario, Progresso>()
    
    fun matricular(usuario: Usuario) {
        if (inscritos.containsKey(usuario)) {
            println("Usuário ${usuario.nome} já está matriculado na formação $nome.")
        } else {
            inscritos[usuario] = Progresso.INSCRITO
            println("Usuário ${usuario.nome} matriculado com sucesso na formação $nome!")
        }
    }
    
    fun atualizarProgresso(usuario: Usuario, progresso: Progresso) {
        if (inscritos.containsKey(usuario)) {
            inscritos[usuario] = progresso
            println("Progresso de ${usuario.nome} atualizado para $progresso na formação $nome.")
        } else {
            println("Usuário ${usuario.nome} não está matriculado na formação.")
        }
    }
    
    fun exibirProgresso(usuario: Usuario) {
        val progresso = inscritos[usuario]
        if (progresso != null) {
            println("Progresso de ${usuario.nome} na formação $nome: $progresso")
        } else {
            println("Usuário ${usuario.nome} não está matriculado nesta formação.")
        }
    }
    
    fun listarInscritos() {
        println("Lista de inscritos na formação $nome:")
        inscritos.forEach { (usuario, progresso) ->
            println("- ${usuario.nome}: $progresso")
        }
    }
    
    fun exibirDetalhes() {
        println("Formação: $nome (Nível: $nivel)")
        println("Conteúdos:")
        conteudos.forEach { conteudo ->
            println("- ${conteudo.nome} (Duração: ${conteudo.duracao} minutos)")
        }
        println("Total de inscritos: ${inscritos.size}")
    }
}

fun main() {
    // Criação de alguns conteúdos educacionais
    val kotlinBasics = ConteudoEducacional("Introdução ao Kotlin", 120)
    val oopConcepts = ConteudoEducacional("Conceitos de OOP", 180)
    val advancedKotlin = ConteudoEducacional("Kotlin Avançado", 240)

    // Criação de uma formação com lista de conteúdos e nível
    val formacaoKotlin = Formacao("Formação Desenvolvedor Kotlin", Nivel.INTERMEDIARIO, listOf(kotlinBasics, oopConcepts, advancedKotlin))

    // Criação de usuários
    val usuario1 = Usuario("Ana")
    val usuario2 = Usuario("Bruno")
    val usuario3 = Usuario("Carlos")

    // Matriculando usuários na formação
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario3)

    // Atualizando e exibindo o progresso dos alunos
    formacaoKotlin.atualizarProgresso(usuario1, Progresso.EM_ANDAMENTO)
    formacaoKotlin.atualizarProgresso(usuario2, Progresso.CONCLUIDO)
    
    formacaoKotlin.exibirProgresso(usuario1)
    formacaoKotlin.exibirProgresso(usuario2)
    formacaoKotlin.exibirProgresso(usuario3)
    
    // Listando inscritos e detalhes da formação
    formacaoKotlin.listarInscritos()
    formacaoKotlin.exibirDetalhes()
}
