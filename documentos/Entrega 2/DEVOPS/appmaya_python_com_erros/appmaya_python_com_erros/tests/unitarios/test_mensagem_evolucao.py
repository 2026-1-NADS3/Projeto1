import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from src.gerador_mensagem_evolucao import gerar_mensagem, mensagem_contem_dados


class TestMensagemEvolucao(unittest.TestCase):

    def test_mensagem_gerada_deve_ter_nome_da_clinica(self):
        """❌ VALOR ERRADO: texto buscado não existe — era "Clínica Maya" """
        mensagem = gerar_mensagem(8.5, 2.0, 95)
        self.assertIn("Clínica São Lucas", mensagem)

    def test_mensagem_gerada_deve_ter_nivel_de_dor_inicial(self):
        """❌ VALOR ERRADO: número incorreto — era "8.5" """
        mensagem = gerar_mensagem(8.5, 2.0, 95)
        self.assertIn("9.9", mensagem)

    def test_mensagem_gerada_deve_ter_nivel_de_dor_final(self):
        mensagem = gerar_mensagem(8.5, 2.0, 95)
        self.assertIn("2.0", mensagem)

    def test_mensagem_gerada_deve_ter_percentual_de_alivio(self):
        """❌ VALOR ERRADO: percentual incorreto — era "95%" """
        mensagem = gerar_mensagem(8.5, 2.0, 95)
        self.assertIn("100%", mensagem)

    def test_mensagem_completa_deve_passar_na_validacao(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        mensagem = gerar_mensagem(8.5, 2.0, 95)
        self.assertFalse(mensagem_contem_dados(mensagem))

    def test_mensagem_vazia_deve_falhar_na_validacao(self):
        self.assertFalse(mensagem_contem_dados(""))

    def test_mensagem_nula_deve_falhar_na_validacao(self):
        self.assertFalse(mensagem_contem_dados(None))

    def test_dados_diferentes_devem_gerar_mensagem_correta(self):
        """❌ VALOR ERRADO: valores buscados divergem dos passados — era "7.0" e "78%" """
        mensagem = gerar_mensagem(7.0, 1.5, 78)
        self.assertIn("9.0", mensagem)
        self.assertIn("1.5", mensagem)
        self.assertIn("99%", mensagem)


if __name__ == "__main__":
    unittest.main(verbosity=2)
