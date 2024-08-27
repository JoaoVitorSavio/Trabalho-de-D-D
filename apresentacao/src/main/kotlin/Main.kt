fun main() {
    println("Vamos escolher uma ação? \nDigite 1 para criar um personagem")
    val opcao = readLine()

    val personagem1 = PersonagemBase()
    val distribuirPontos = 27

    when (opcao) {
        "1" -> {
            println("Vamos distribuir os pontos para cada atributo")
            val personagemComAtributosIniciais = distribuirPontos(personagem1, distribuirPontos)

            println(personagemComAtributosIniciais)

            println("Escolha uma raça dentre as de baixo e digite seu nome: \n1 - Anão \n2 - Elfo \n3 - Anão da Montanha \n4 - Draconato \n5 - Humano \n6 - Meio-Orc \n7 - Halfling \n8 - Gnomo da Floresta \n9 - Halfling Robusto \n10 - Gnomo das Rochas \n11 - Alto Elfo \n12 - Gnomo \n13 - Tiefling \n14 - Anão da Colina \n15 - Elfo da Floresta \n16 - Meio Elfo \n17 - Drow \n18 - Halfling Pés Leves")
            var racaEscolhida = readLine() ?: ""

            if (racaEscolhida.isEmpty()) {
                println("Nenhuma raça foi escolhida. Usando padrão sem bônus.")
                racaEscolhida = "padrao"
            } else {
                println("Raça escolhida: $racaEscolhida")
            }

            val personagemComRaca = aplicarBonusRaca(personagemComAtributosIniciais, racaEscolhida)

            val personagemCriado = aplicarConstVida(personagemComRaca)

            println("Distribuição final dos atributos com bônus de raça realizado. Seu personagem de raça $racaEscolhida ficou assim: $personagemCriado")
        }

        else -> println("Digite 1 para criar um personagem")
    }
}

fun distribuirPontos(personagem1: PersonagemBase, distribuirPontos: Int): PersonagemBase {
    var pontosRestantess = distribuirPontos

    println("Você quer gastar seus pontos restantes em qual atributo? \n Digite 1 para força \n Digite 2 para destreza \n Digite 3 para inteligencia \n Digite 4 para constituicao \n Digite 5 para sabedoria \n Digite 6 para carisma \n Digite outra coisa para sair")
    val opcao2 = readLine()
    when (opcao2) {
        "1" -> {
            pontosRestantess = adicionarPontos("força", personagem1.forca, pontosRestantess)
            personagem1.forca += (distribuirPontos - pontosRestantess) // Atualiza a força
            return distribuirPontos(personagem1, pontosRestantess)
        }

        "2" -> {
            pontosRestantess = adicionarPontos("destreza", personagem1.destreza, pontosRestantess)
            personagem1.destreza += (distribuirPontos - pontosRestantess) // Atualiza a destreza
            return distribuirPontos(personagem1, pontosRestantess)
        }

        "3" -> {
            pontosRestantess = adicionarPontos("inteligência", personagem1.inteligencia, pontosRestantess)
            personagem1.inteligencia += (distribuirPontos - pontosRestantess) // Atualiza a inteligência
            return distribuirPontos(personagem1, pontosRestantess)
        }

        "4" -> {
            pontosRestantess = adicionarPontos("constituição", personagem1.constituicao, pontosRestantess)
            personagem1.constituicao += (distribuirPontos - pontosRestantess) // Atualiza a constituição
            return distribuirPontos(personagem1, pontosRestantess)
        }

        "5" -> {
            pontosRestantess = adicionarPontos("sabedoria", personagem1.sabedoria, pontosRestantess)
            personagem1.sabedoria += (distribuirPontos - pontosRestantess) // Atualiza a sabedoria
            return distribuirPontos(personagem1, pontosRestantess)
        }

        "6" -> {
            pontosRestantess = adicionarPontos("carisma", personagem1.carisma, pontosRestantess)
            personagem1.carisma += (distribuirPontos - pontosRestantess) // Atualiza o carisma
            return distribuirPontos(personagem1, pontosRestantess)
        }

        else -> {
            println("Você não escolheu nenhum atributo. Deseja continuar distribuindo ou sair? \n Digite 1 para distribuir novamente \n Digite 2 para sair")
            val opcao3 = readLine()
            when (opcao3) {
                "1" -> {
                    return distribuirPontos(personagem1, pontosRestantess)
                }
                "2" -> {
                    return personagem1
                }
            }
        }
    }

    return personagem1
}

fun adicionarPontos(nomeAtributo: String, valorAtual: Int, pontosRestantes: Int): Int {
    var pontosRestantesAtualizados = pontosRestantes
    println("Você tem $pontosRestantesAtualizados pontos restantes.")
    print("Quantos pontos deseja adicionar em $nomeAtributo? \n(Lembrando que você já começa com 8 em cada atributo e não pode ultrapassar 15 em nenhuma categoria, e a partir do nível 13 são gastos 2 pontos por nível em atributo)\n")

    val pontos = readLine()?.toIntOrNull()

    if (pontos == null) {
        println("Entrada inválida. Seus pontos estão vazios.")
        return adicionarPontos(nomeAtributo, valorAtual, pontosRestantesAtualizados)
    }

    var custoPontos = 0
    for (i in 1..pontos) {
        if (valorAtual + i > 13) {
            custoPontos += 2
        } else {
            custoPontos += 1
        }
    }

    // Verificar se o jogador pode gastar esses pontos
    if (custoPontos > pontosRestantesAtualizados) {
        println("Você não tem pontos suficientes. Tente novamente.")
        return adicionarPontos(nomeAtributo, valorAtual, pontosRestantesAtualizados)
    } else if (valorAtual + pontos > 15) {
        println("O máximo de pontos permitidos em cada atributo é 15. Tente novamente.")
        return adicionarPontos(nomeAtributo, valorAtual, pontosRestantesAtualizados)
    } else {
        println("Pontos inseridos no atributo $nomeAtributo.")
        pontosRestantesAtualizados -= custoPontos
    }

    return pontosRestantesAtualizados
}

fun aplicarBonusRaca(atributos: PersonagemBase, raca: String): PersonagemBase {
    when (raca.lowercase()) {
        "anao" -> {
            atributos.constituicao += 2
            println("Raça Anão escolhida. +2 Força, +2 Destreza aplicados.")
        }
        "elfo" -> {
            atributos.destreza += 2
            println("Raça Elfo escolhida. +2 Destreza, +1 Inteligência aplicados.")
        }
        "anao da montanha" -> {
            atributos.forca += 2
            println("Raça Anão da Montanha escolhida. +2 Constituição, +2 Força aplicados.")
        }
        "draconato" -> {
            atributos.forca += 2
            atributos.carisma += 1
            println("Raça Draconato escolhida. +2 Força, +1 Carisma aplicados.")
        }
        "humano" -> {
            atributos.forca += 1
            atributos.destreza += 1
            atributos.constituicao += 1
            atributos.inteligencia += 1
            atributos.sabedoria += 1
            atributos.carisma += 1
            println("Raça Humano escolhida. +1 Força, +1 Destreza aplicados.")
        }
        "meio-orc" -> {
            atributos.forca += 2
            atributos.constituicao += 1
            println("Raça Meio-Orc escolhida. +2 Força, +1 Constituição aplicados.")
        }
        "halfling" -> {
            atributos.destreza += 2
            println("Raça Halfling escolhida. +2 Destreza, +1 Carisma aplicados.")
        }
        "gnomo da floresta" -> {
            atributos.destreza += 1
            println("Raça Gnomo da Floresta escolhida. +2 Inteligência, +1 Destreza aplicados.")
        }
        "halfling robusto" -> {
            atributos.constituicao += 1
            println("Raça Halfling Robusto escolhida. +2 Destreza, +1 Constituição aplicados.")
        }
        "gnomo das rochas" -> {
            atributos.constituicao += 1
            println("Raça Gnomo das Rochas escolhida. +2 Inteligência, +1 Constituição aplicados.")
        }
        "alto elfo" -> {
            atributos.inteligencia += 1
            println("Raça Alto Elfo escolhida. +2 Inteligência, +1 Destreza aplicados.")
        }
        "gnomo" -> {
            atributos.inteligencia += 2
            println("Raça Gnomo escolhida. +2 Inteligência, +1 Constituição aplicados.")
        }
        "tiefling" -> {
            atributos.inteligencia += 1
            atributos.carisma += 2
            println("Raça Tiefling escolhida. +1 Inteligência, +2 Carisma aplicados.")
        }
        "anao da colina" -> {
            atributos.sabedoria += 1
            println("Raça Anão da Colina escolhida. +2 Constituição, +1 Sabedoria aplicados.")
        }
        "elfo da floresta" -> {
            atributos.sabedoria += 1
            println("Raça Elfo da Floresta escolhida. +2 Destreza, +1 Sabedoria aplicados.")
        }
        "meio elfo" -> {
            atributos.carisma += 2
            println("Raça Meio Elfo escolhida. +2 Carisma, +1 Destreza aplicados.")
        }
        "drow" -> {
            atributos.carisma += 1
            println("Raça Drow escolhida. +2 Destreza, +1 Carisma aplicados.")
        }
        "halfling pés leves" -> {
            atributos.carisma += 1
            println("Raça Halfling Pés Leves escolhida. +2 Destreza, +1 Carisma aplicados.")
        }
        else -> {
            println("Raça desconhecida ou padrão. Nenhum bônus aplicado. \n Digite 1 para escolher a raça novamente OU \n Digite 2 para sair")
            val opcao4 = readLine()
            when (opcao4) {
                "1" -> {
                    return aplicarBonusRaca(atributos, raca)
                }
                "2" -> {
                    return atributos
                }
            }

        }
    }
    return atributos
}

fun aplicarConstVida(personagem: PersonagemBase): PersonagemBase {
    var modConst = 0
    when (personagem.constituicao) {
        1 -> modConst -= 5
        2, 3 -> modConst -= 4
        4, 5 -> modConst -= 3
        6, 7 -> modConst -= 2
        8, 9 -> modConst -= 1
        10, 11 -> modConst = 0
        12, 13 -> modConst += 1
        14, 15 -> modConst += 2
        16, 17 -> modConst += 3
        18, 19 -> modConst += 4
        20, 21 -> modConst += 5
        22, 23 -> modConst += 6
        24, 25 -> modConst += 7
        26, 27 -> modConst += 8
        28, 29 -> modConst += 9
        30 -> modConst += 10
        else -> println("Não foi possível atribuir os pontos de vida corretamente.")
    }
    personagem.pontosDeVida += modConst
    return personagem
}

class PersonagemBase(
    var forca: Int = 8,
    var destreza: Int = 8,
    var constituicao: Int = 8,
    var inteligencia: Int = 8,
    var sabedoria: Int = 8,
    var carisma: Int = 8,
    var pontosDeVida: Int = 10
) {
    override fun toString(): String {
        return "Atributos(forca=$forca, destreza=$destreza, constituicao=$constituicao, inteligencia=$inteligencia, sabedoria=$sabedoria, carisma=$carisma, pontosDeVida=$pontosDeVida)"
    }
}