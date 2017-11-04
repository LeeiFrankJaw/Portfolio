#!/usr/bin/env python
import asyncio
import json
import re
import threading
from os import listdir
from signal import SIGINT, SIGTERM
from subprocess import PIPE, Popen
from threading import Lock, Thread

p = re.compile(r'time=(.*) ms')

lock = Lock()


def run_in_thread(config):
    proc = Popen(['ping', '-c', '1', config['server']], stdout=PIPE)
    proc.wait()
    process_result(config, proc)


def process_result(config, proc):
    line = proc.stdout.readline().decode()
    while line:
        m = p.search(line)
        if m:
            latency = float(m.group(1))
            print(f'{config["server"]}\t{latency} ms')
            lock.acquire()
            config['latency'] = latency
            lock.release()
        line = proc.stdout.readline().decode()


def load(fname):
    with open(fname) as f:
        res = json.load(f)
    res['fname'] = fname
    return res


configs = [load(fname) for fname in listdir() if fname.endswith('.json')]

[Thread(target=run_in_thread, args=(config,)).start()
 for config in configs]

main_thread = threading.main_thread()

[t.join() for t in threading.enumerate() if t is not main_thread]

configs.sort(key=lambda x: x['latency'])

fastest = configs[0]

proc = Popen(['ss-local', '-v', '-c', fastest['fname']])

loop = asyncio.get_event_loop()

for signum in (SIGINT, SIGTERM):
    loop.add_signal_handler(signum, loop.stop)

try:
    loop.run_forever()
finally:
    loop.close()
