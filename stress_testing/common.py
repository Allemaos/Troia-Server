import csv


TEST_CASES = [pref * (10 ** zeroes) for pref in [1, 5]
        for zeroes in [2, 3, 4, 5]]

LABELS = ["correct", "incorrect"]  # should be of length == 2


def load_costs(path):
    cost = csv.reader(open(path + "/costs"), delimiter='\t')
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
