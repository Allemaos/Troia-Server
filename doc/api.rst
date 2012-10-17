Troia API proposal
==================

Current version of REST API is very cluttered and not structured well.
Here we propose version that will hopefully turn out to be better and easier to extend in future.

Let URL stand for entry point.

Items and their JSON representations

- Job - represented with its ID - String
- Item - aka Object - represented with its String id. In dict represented with: "item_id": IID
- Worker - represented with its String id. In dict represented with: "worker_id": WID
- GoldItem -

General points
--------------

- API versioning

Main entry
----------

URL/api/v1/


Management
----------

Entry
~~~~~
URL/api/v1/management

New job
~~~~~~~
/new_job (PUT?)

parameters:

- kind/type - Classification/Regression, algorithm - {galc, gal, mv} (or multi algorithms - list)
- optional - job_id - if not given it returns random one
- returns - job_id of created job

/delete_job (DELETE)
/reset_job (PATCH)

NOTE: I think there is some good pattern for such *resource* managing

Jobs entry
----------
All requests related to job should start with:

URL/api/v1/jobs/{job_id}/


/setUp

Depends on project kind.
For example parameters:

- misclassificationCostMatrix - json list of dictionaries of form:

    {"trueValue": x, "predictedValue": y, "errorCost": z}

- goldLabels - json list with dictionaries of form:

    {"item_id": ABC, "trueValue": x}


/addGoldLabel(s):
    {"item_id": oid, "trueValue"}
/addVote


/loadMisclassificationCostsMatrix


GENERAL

URL/api/v1
  |
  |--> /management
  |       |
  |       |--> /job (new, reset, delete)
  |
  |--> /stats # ??? worth thinking? like number of jobs, requests per second, cpu usage etc.
  |
  |--> /jobs/  # without job_id it should list all jobs?
  |--> /jobs/{job_id}
          |
          |--> /items/{item_id}  # returns assigned label to this object
          |--> /workers/{worker_id}  # returns all votes from this worker
          |--> /goldItems/{item_id}
          |--> /validationItems/{item_id}
          |--> /votes/ (GET - single, POST - many, DELETE)
          |--> /missclassificationCostMatrix
          |
          |--> /setMisclassificationCostMatrix
          |--> /addGoldItem(s)
          |--> /addValidationItem(s)
          |--> /addVote(s)
          |--> /quality


URL/api/v1
  |--> /jobs/  # list, new, reset, delete
  |--> /jobs/{job_id}
          |--> /stats/  # ??? worth thinking? number of requests per second
          |
          |--> /items/{item_id}  # returns assigned label to this object
          |--> /workers/{worker_id}  # returns all votes from this worker, quality
          |--> /goldItems/{item_id}
          |--> /validationItems/{item_id}
          |--> /votes/ (GET - single, POST - many, DELETE)
          |
          |--> /missclassificationCostMatrix
          |
          |--> /quality
          |--> /predictions
                  |-->