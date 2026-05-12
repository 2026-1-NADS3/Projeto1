import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from unittest.mock import patch, MagicMock
from src.simulador_telas import TelaLogin


class TestIntegracaoLogin(unittest.TestCase):

    def setUp(self):
        self.tela = TelaLogin()

    def test_clicar_confirmar_deve_navegar_para_main_activity(self):
        """❌ VALOR ERRADO: destino incorreto — era TelaLogin.TELA_PRINCIPAL ("MainActivity")"""
        resultado = self.tela.clicar_confirmar()
        self.assertEqual(resultado.tela_destino, "DashboardActivity")
        self.assertTrue(resultado.finalizada)
        self.assertFalse(self.tela.ativa)

    def test_clicar_cadastre_se_deve_abrir_tela_de_cadastro(self):
        """❌ VALOR ERRADO: deveria ser assertFalse(resultado.finalizada) — Login não fecha ao ir pro Cadastro"""
        resultado = self.tela.clicar_cadastre_se()
        self.assertEqual(resultado.tela_destino, TelaLogin.TELA_CADASTRO)
        self.assertTrue(resultado.finalizada)
        self.assertTrue(self.tela.ativa)

    def test_clicar_esqueceu_senha_deve_exibir_toast(self):
        """❌ VALOR ERRADO: texto do Toast incorreto — era TelaLogin.TOAST_SENHA"""
        resultado = self.tela.clicar_esqueceu_senha()
        self.assertEqual(resultado.mensagem_toast, "Funcionalidade indisponível.")
        self.assertIsNone(resultado.tela_destino)
        self.assertTrue(self.tela.ativa)

    def test_clicar_confirmar_chama_navegacao_com_mock(self):
        with patch.object(self.tela, "clicar_confirmar", wraps=self.tela.clicar_confirmar) as mock_confirmar:
            resultado = self.tela.clicar_confirmar()
            mock_confirmar.assert_called_once()
            self.assertEqual(resultado.tela_destino, TelaLogin.TELA_PRINCIPAL)


if __name__ == "__main__":
    unittest.main(verbosity=2)
