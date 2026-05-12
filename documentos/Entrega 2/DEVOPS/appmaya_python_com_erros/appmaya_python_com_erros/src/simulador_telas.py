"""
simulador_telas.py
==================
Simulação em Python das Activities Android do AppMaya3GL.

Como os testes de integração com Espresso dependem do emulador Android,
esta camada recria a lógica de navegação e estado de cada tela usando
classes Python puras. Os testes de integração usam unittest.mock para
interceptar as transições — o equivalente ao ActivityScenario do Espresso.

Classes simuladas:
  - TelaLogin       → activityLogin.java
  - TelaAgendamento → AgendamentoActivity.java
"""

from dataclasses import dataclass, field
from typing import Optional


# ─── Estrutura de navegação ─────────────────────────────────────────────────

@dataclass
class ResultadoNavegacao:
    """
    Representa o resultado de uma ação de navegação.
    Equivalente ao que o Espresso verifica após ActivityScenario.launch().
    """
    tela_destino: Optional[str]   # Nome da Activity aberta (None = não navegou)
    finalizada: bool = False      # True = finish() foi chamado (volta para tela anterior)
    mensagem_toast: Optional[str] = None  # Texto do Toast exibido


# ─── TelaLogin ──────────────────────────────────────────────────────────────

class TelaLogin:
    """
    Simula a activityLogin.java.

    Reproduz a lógica dos três listeners de clique:
      - btn_confirmar_login   → navega para MainActivity
      - tv_ir_para_cadastro   → navega para activityCadastro
      - tv_esqueceu_senha     → exibe Toast "Recuperação de senha em breve!"
    """

    TELA_PRINCIPAL = "MainActivity"
    TELA_CADASTRO  = "activityCadastro"
    TOAST_SENHA    = "Recuperação de senha em breve!"

    def __init__(self):
        self._ativa = True

    @property
    def ativa(self) -> bool:
        """Indica se a Activity ainda está em execução (não foi finalizada)."""
        return self._ativa

    def clicar_confirmar(self) -> ResultadoNavegacao:
        """
        Simula o clique no botão CONFIRMAR.

        Java original:
            Intent intent = new Intent(activityLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
        """
        self._ativa = False
        return ResultadoNavegacao(tela_destino=self.TELA_PRINCIPAL, finalizada=True)

    def clicar_cadastre_se(self) -> ResultadoNavegacao:
        """
        Simula o clique no link 'Cadastre-se'.

        Java original:
            Intent intent = new Intent(activityLogin.this, activityCadastro.class);
            startActivity(intent);
        """
        return ResultadoNavegacao(tela_destino=self.TELA_CADASTRO)

    def clicar_esqueceu_senha(self) -> ResultadoNavegacao:
        """
        Simula o clique no link 'Esqueceu a senha?'.

        Java original:
            Toast.makeText(activityLogin.this,
                "Recuperação de senha em breve!", Toast.LENGTH_SHORT).show();
        """
        return ResultadoNavegacao(
            tela_destino=None,
            mensagem_toast=self.TOAST_SENHA
        )


# ─── TelaAgendamento ────────────────────────────────────────────────────────

class TelaAgendamento:
    """
    Simula a AgendamentoActivity.java.

    Reproduz a lógica dos dois listeners de clique:
      - btn_confirmar_agendamento → exibe Toast de sucesso e chama finish()
      - btn_voltar                → chama finish()
    """

    TOAST_SUCESSO  = "Agendamento realizado com sucesso!"

    def __init__(self):
        self._ativa = True
        # Elementos visuais presentes na tela (espelham os findViewById)
        self.elementos_visiveis = {
            "btn_confirmar_agendamento": True,
            "btn_voltar": True,
        }

    @property
    def ativa(self) -> bool:
        return self._ativa

    def elemento_visivel(self, id_elemento: str) -> bool:
        """
        Verifica se um elemento da tela está visível.
        Equivalente ao ViewMatchers.isDisplayed() do Espresso.
        """
        return self.elementos_visiveis.get(id_elemento, False)

    def clicar_confirmar(self) -> ResultadoNavegacao:
        """
        Simula o clique em CONFIRMAR AGENDAMENTO.

        Java original:
            Toast.makeText(AgendamentoActivity.this,
                "Agendamento realizado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        """
        self._ativa = False
        return ResultadoNavegacao(
            tela_destino=None,
            finalizada=True,
            mensagem_toast=self.TOAST_SUCESSO
        )

    def clicar_voltar(self) -> ResultadoNavegacao:
        """
        Simula o clique no botão VOLTAR.

        Java original:
            finish();
        """
        self._ativa = False
        return ResultadoNavegacao(tela_destino=None, finalizada=True)
