"""
validador_campos.py
===================
Funções utilitárias de validação de campos de formulário.
Equivalente Python da classe Java ValidadorCampos.java

Regras espelhadas diretamente do original Android:
  - email_valido()  → emailValido(String email)
  - senha_valida()  → senhaValida(String senha)
  - nome_valido()   → nomeValido(String nome)
"""

import re


def email_valido(email: str | None) -> bool:
    """
    Valida se um e-mail possui formato básico aceitável.

    Regras (mesmas do Java original):
      - Não nulo e não vazio
      - Contém '@' e o '@' não é o primeiro caractere
      - O domínio após o '@' contém pelo menos um ponto

    Args:
        email: String do e-mail a ser validado.

    Returns:
        True se o e-mail for válido, False caso contrário.

    Exemplos:
        >>> email_valido("paciente@clinicamaya.com.br")
        True
        >>> email_valido("semdominio@")
        False
        >>> email_valido(None)
        False
    """
    if not email or not email.strip():
        return False

    trimmed = email.strip()
    at_index = trimmed.find("@")

    # '@' precisa existir e não pode ser o primeiro caractere
    if at_index <= 0:
        return False

    dominio = trimmed[at_index + 1:]
    return "." in dominio


def senha_valida(senha: str | None) -> bool:
    """
    Valida se uma senha atende os requisitos mínimos de segurança.

    Regras (mesmas do Java original):
      - Não nula e não vazia
      - Mínimo de 6 caracteres

    Args:
        senha: String da senha a ser validada.

    Returns:
        True se a senha for válida, False caso contrário.

    Exemplos:
        >>> senha_valida("abc123")
        True
        >>> senha_valida("123")
        False
        >>> senha_valida(None)
        False
    """
    if not senha:
        return False

    return len(senha) >= 6


def nome_valido(nome: str | None) -> bool:
    """
    Valida se um nome de paciente é aceitável.

    Regras (mesmas do Java original):
      - Não nulo e não vazio após strip
      - Mínimo de 3 caracteres
      - Apenas letras (incluindo acentuadas) e espaços

    Args:
        nome: String do nome a ser validado.

    Returns:
        True se o nome for válido, False caso contrário.

    Exemplos:
        >>> nome_valido("Maria Fernanda")
        True
        >>> nome_valido("Li")
        False
        >>> nome_valido("Maria123")
        False
    """
    if not nome or not nome.strip():
        return False

    trimmed = nome.strip()

    if len(trimmed) < 3:
        return False

    # Apenas letras (a-z, A-Z e caracteres acentuados) e espaços
    return bool(re.fullmatch(r"[a-zA-ZÀ-ú ]+", trimmed))
