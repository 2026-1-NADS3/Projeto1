import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from src.validador_campos import nome_valido


class TestValidacaoNome(unittest.TestCase):

    def test_nome_completo_valido_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(nome_valido("Maria Fernanda"))

    def test_nome_simples_curto_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(nome_valido("Ana"))

    def test_nome_vazio_deve_retornar_false(self):
        self.assertFalse(nome_valido(""))

    def test_nome_nulo_deve_retornar_false(self):
        self.assertFalse(nome_valido(None))

    def test_nome_com_numeros_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(nome_valido("Maria123"))

    def test_nome_com_dois_caracteres_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(nome_valido("Li"))

    def test_nome_com_simbolos_deve_retornar_false(self):
        self.assertFalse(nome_valido("Maria@Fernanda!"))


if __name__ == "__main__":
    unittest.main(verbosity=2)
