#!/usr/bin/env python

from __future__ import print_function

import logging
import sched
import sys
import time
from datetime import datetime

if sys.version_info.major == 3:
    from subprocess import run  # type: ignore
else:
    from subprocess import call  # type: ignore
    from collections import namedtuple
    CompletedProcess = namedtuple('CompletedProcess', ['returncode'])

    def run(*args, **kwargs):
        return CompletedProcess(call(*args, **kwargs))


def get_logger():
    level = logging.INFO
    logger = logging.getLogger(__name__)
    logger.setLevel(level)
    ch = logging.StreamHandler()
    ch.setLevel(level)
    formatter = logging.Formatter('%(asctime)s:{}'.format(
        logging.BASIC_FORMAT))
    ch.setFormatter(formatter)
    logger.addHandler(ch)
    return logger


logger = get_logger()

# def print_time():
#     print('From print_time: {}'.format(datetime.now()))

# cnt = 0

# def print_time_and_raise():
#     global cnt
#     cnt += 1
#     print(cnt)
#     if cnt >= 5 and cnt % 2 == 0:
#         raise Exception('can you not be interrupted by me?')
#     print_time()


def repeat_every(time_diff, action, argument):
    s = sched.scheduler(time.time, time.sleep)

    def repeater(*args):
        try:
            action(*args)
        except Exception:
            logger.exception('{} raised the following exception.'.format(
                action.__name__))
        finally:
            s.enter(time_diff, 1, repeater, args)

    s.enter(0, 1, repeater, argument)
    s.run()


def main():
    t1 = datetime.now()
    logger.info('job started')
    update = True
    if run(['pgrep', 'mbsync']).returncode == 0:
        logger.info('there was already an instance of mbsync running')
        update = False
    if run(['pgrep', '-f', 'mu server']).returncode == 0:
        logger.info('there was already an instance of mu server running')
        update = False
    if update:
        run(['mbsync', '-a', '-q'])
        if run(['pgrep', '-f', 'mu server']).returncode == 0:
            run(['emacsclient', '-e', '(mu4e-update-index)'])
        else:
            run(['mu', 'index', '-q'])
    t2 = datetime.now()
    logger.info('job finished')
    logger.info('duration: {}'.format(t2 - t1))


if __name__ == '__main__':
    time.sleep(60)
    sys.exit(repeat_every(60 * 30, main, ()))
