import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from src.validador_campos import senha_valida


class TestValidacaoSenha(unittest.TestCase):

    def test_senha_com_seis_caracteres_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(senha_valida("abc123"))

    def test_senha_longa_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(senha_valida("M@yaClinica2024!"))

    def test_senha_curta_demais_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(senha_valida("123"))

    def test_senha_vazia_deve_retornar_false(self):
        self.assertFalse(senha_valida(""))

    def test_senha_nula_deve_retornar_false(self):
        self.assertFalse(senha_valida(None))

    def test_senha_com_cinco_caracteres_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(senha_valida("abcd1"))


if __name__ == "__main__":
    unittest.main(verbosity=2)
