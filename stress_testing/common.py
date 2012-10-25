import csv
import time


TEST_CASES = [pref * (10 ** zeroes) for zeroes in [2, 3, 4, 5, 6]
        for pref in [1, 5]]

LABELS = ["correct", "incorrect"]  # should be of length == 2


def transform_cost(cost):
    dictt = {}
    for c1, c2, cost_ in cost:
        el = dictt.get(c1, {})
        el[c2] = cost_
        dictt[c1] = el
    return dictt.items()


def load_costs(path):
    with open(path + "/costs") as F:
        cost = csv.reader(F, delimiter='\t')
        dictt = {}
        for c1, c2, cost_ in cost:
            el = dictt.get(c1, {})
            el[c2] = cost_
            dictt[c1] = el

    return [{'name':c, 'prior':1., 'misclassification_cost':d}
            for c, d in dictt.iteritems()]


def load_correct(path):
    correct = csv.reader(open(path + "/correct"), delimiter='\t')
    correct = [x[-2:] for x in correct]
    return [{"objectName": s, "correctCategory": v} for s, v in correct]


def load_input(path):
    inputt = csv.reader(open(path + "/input"), delimiter='\t')
    return [{'workerName':wn, 'objectName':on, 'categoryName':cn}
            for wn, on, cn in inputt]


def load_all(path):
    r = [list(csv.reader(open(path + s), delimiter='\t'))
            for s in ['/correct', '/costs', '/input']]
    r[0] = [x[-2:] for x in r[0]]
    return r


def save_csv(path, data):
    with open(path, 'w') as F:
        writer = csv.writer(F, delimiter='\t')
        writer.writerows(data)


def get_tc():
    from troia_client import TroiaClient
    return TroiaClient("http://localhost:8080/GetAnotherLabel/rest/", None)


def prepare_troia_client(jid, folder):
    tc = get_tc()
    correct, costs, inputt = load_all(folder)
    tc.reset(jid)
    costs = transform_cost(costs)
    tc.load_categories(costs, jid)
    tc.load_gold_labels(correct, jid)
    return tc, inputt


def timeit(f, *args, **kwargs):
    start = time.time()
    f(*args, **kwargs)
    return time.time() - start
