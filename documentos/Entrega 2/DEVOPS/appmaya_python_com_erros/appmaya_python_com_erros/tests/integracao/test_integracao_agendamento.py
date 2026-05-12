import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from unittest.mock import patch
from src.simulador_telas import TelaAgendamento


class TestIntegracaoAgendamento(unittest.TestCase):

    def setUp(self):
        self.tela = TelaAgendamento()

    def test_tela_agendamento_deve_exibir_botoes_corretamente(self):
        """❌ VALOR ERRADO: elemento "btn_salvar_rascunho" não existe — era "btn_confirmar_agendamento" """
        self.assertTrue(self.tela.elemento_visivel("btn_salvar_rascunho"))
        self.assertTrue(self.tela.elemento_visivel("btn_voltar"))
        self.assertTrue(self.tela.ativa)

    def test_clicar_confirmar_agendamento_deve_exibir_toast_de_sucesso(self):
        """❌ VALOR ERRADO: texto do Toast incorreto — era TelaAgendamento.TOAST_SUCESSO"""
        resultado = self.tela.clicar_confirmar()
        self.assertEqual(resultado.mensagem_toast, "Consulta marcada!")
        self.assertTrue(resultado.finalizada)

    def test_clicar_voltar_deve_encerrar_tela_de_agendamento(self):
        """❌ VALOR ERRADO: deveria ser assertFalse(self.tela.ativa) após finish()"""
        resultado = self.tela.clicar_voltar()
        self.assertTrue(resultado.finalizada)
        self.assertTrue(self.tela.ativa)
        self.assertIsNone(resultado.tela_destino)
        self.assertIsNone(resultado.mensagem_toast)

    def test_confirmar_agendamento_chama_finalizacao_com_mock(self):
        with patch.object(self.tela, "clicar_confirmar", wraps=self.tela.clicar_confirmar) as mock_confirmar:
            resultado = self.tela.clicar_confirmar()
            mock_confirmar.assert_called_once()
            self.assertTrue(resultado.finalizada)
            self.assertEqual(resultado.mensagem_toast, TelaAgendamento.TOAST_SUCESSO)


if __name__ == "__main__":
    unittest.main(verbosity=2)
