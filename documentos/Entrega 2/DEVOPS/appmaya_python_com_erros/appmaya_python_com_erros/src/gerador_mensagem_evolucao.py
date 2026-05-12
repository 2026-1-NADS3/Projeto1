"""
gerador_mensagem_evolucao.py
============================
Funções para geração e validação da mensagem de compartilhamento de evolução.
Equivalente Python da classe Java GeradorMensagemEvolucao.java

Funções espelhadas diretamente do original Android:
  - gerar_mensagem()         → gerarMensagem(double, double, int)
  - mensagem_contem_dados()  → mensagemContemDados(String)
"""


def gerar_mensagem(nivel_dor_inicial: float, nivel_dor_final: float, percentual_alivio: int) -> str:
    """
    Gera a mensagem de evolução do paciente para compartilhamento.

    A lógica e o texto são idênticos ao Intent ACTION_SEND da activityEvolucao.java,
    extraídos para permitir teste isolado sem dependência do Android.

    Args:
        nivel_dor_inicial:  Nível de dor registrado no início do tratamento (ex: 8.5).
        nivel_dor_final:    Nível de dor registrado ao final do tratamento (ex: 2.0).
        percentual_alivio:  Percentual de alívio obtido (ex: 95).

    Returns:
        String formatada pronta para compartilhamento.

    Exemplos:
        >>> msg = gerar_mensagem(8.5, 2.0, 95)
        >>> "Clínica Maya" in msg
        True
        >>> "95%" in msg
        True
    """
    return (
        "Confira minha evolução na Clínica Maya RPG!\n"
        f"📉 Nível de Dor: de {nivel_dor_inicial} para {nivel_dor_final}\n"
        f"📈 Alívio Lombar: {percentual_alivio}%\n"
        "Estou muito feliz com os resultados!"
    )


def mensagem_contem_dados(mensagem: str | None) -> bool:
    """
    Verifica se uma mensagem de evolução contém os dados essenciais esperados.

    Valida que a mensagem não está vazia e possui as três âncoras de conteúdo
    obrigatórias definidas na classe Java original.

    Args:
        mensagem: String da mensagem a ser verificada.

    Returns:
        True se a mensagem contiver todos os dados essenciais, False caso contrário.

    Exemplos:
        >>> mensagem_contem_dados(gerar_mensagem(8.5, 2.0, 95))
        True
        >>> mensagem_contem_dados("")
        False
        >>> mensagem_contem_dados(None)
        False
    """
    if not mensagem:
        return False

    return (
        "Clínica Maya" in mensagem
        and "Nível de Dor" in mensagem
        and "Alívio Lombar" in mensagem
    )
