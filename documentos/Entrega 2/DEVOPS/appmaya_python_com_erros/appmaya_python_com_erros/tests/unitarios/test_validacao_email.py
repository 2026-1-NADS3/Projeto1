import sys
import os
sys.path.insert(0, os.path.join(os.path.dirname(__file__), "..", ".."))

import unittest
from src.validador_campos import email_valido


class TestValidacaoEmail(unittest.TestCase):

    def test_email_com_formato_correto_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(email_valido("paciente@clinicamaya.com.br"))

    def test_email_com_subdominio_deve_retornar_true(self):
        """❌ VALOR ERRADO: deveria ser assertTrue"""
        self.assertFalse(email_valido("maria@mail.clinica.com"))

    def test_email_sem_arroba_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(email_valido("pacienteclinicamaya.com"))

    def test_email_vazio_deve_retornar_false(self):
        """❌ VALOR ERRADO: deveria ser assertFalse"""
        self.assertTrue(email_valido(""))

    def test_email_nulo_deve_retornar_false(self):
        self.assertFalse(email_valido(None))

    def test_email_sem_dominio_com_ponto_deve_retornar_false(self):
        self.assertFalse(email_valido("paciente@clinicamaya"))


if __name__ == "__main__":
    unittest.main(verbosity=2)
