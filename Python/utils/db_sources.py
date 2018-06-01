import MySQLdb
from MySQLdb import InterfaceError, OperationalError

from decorators import Singleton

prod_cred = {
    'host': 'www.example.com',
    'user': 'your_username',
    'passwd': 'your_password',
    'db': 'your_database',
    'use_unicode': True,
    'charset': 'utf8mb4',
}


class BaseDb(object):

    # def __init__(self):
    #     self._conn = self._connect()

    def _connect(self):
        return MySQLdb.connect(**self._credential)

    def __del__(self):
        if hasattr(self, '_conn'):
            self._conn.close()

    def _check_conn(self):
        try:
            with self._conn.cursor() as cursor:
                cursor.execute('select 1')
        except (InterfaceError, OperationalError, AttributeError):
            self._conn = self._connect()

    @property
    def conn(self):
        self._check_conn()
        return self._conn

    def cursor(self):
        return self.conn.cursor()

    def commit(self):
        return self._conn.commit()

    def rollback(self):
        return self._conn.rollback()


# @Singleton
class _ProdDb(BaseDb):

    def __init__(self):
        self._credential = prod_cred
        super(_ProdDb, self).__init__()


ProdDb = Singleton(_ProdDb)
