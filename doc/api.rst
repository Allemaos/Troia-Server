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


NEW

Entry: URL/api/v1/

We need to enable some authorization - without that this service is useless for many companies

We assume that you are authorized when you make requests

GET /ping <- should return system status: its time and whether it can connect to DB/storage


GET /jobs/ <- lists all your jobs
GET /jobs/id:/ <- returns base info about job - when it was created etc and links to more data?

GET /jobs/id:/status/

GET /jobs/id:/costMatrix

GET /jobs/id:/labels/ <- list all labels
GET /jobs/id:/labels/label_id:/ <- some stats about this label like how much votes use it?

GET /jobs/id:/workers/ <- list all workers that voted in this job
GET /jobs/id:/workers/worker_id:/ <- some stats about worker

GET /jobs/id:/items/ <- list all items for which we have votes (all items are included: gold, validation etc)
GET /jobs/id:/items/item_id:/ <- all votes that we were given to this item and possibly algorithms decision about label for this item?

GET /jobs/id:/goldItems/ <- similar to items
GET /jobs/id:/goldItems/item_id:/ <- similar to items
GET /jobs/id:/validationItems/ <- similar to items
GET /jobs/id:/validationItems/item_id:/ <- similar to items

GET /jobs/id:/votes/ <- list all votes

GET /jobs/id:/prediction/algorithm:/items/ <- lists all object with their predicted labels
GET /jobs/id:/prediction/algorithm:/items/item_id:/ <- return more detailed info like labels probability distribution etc. Can be specific to given algorithm
GET /jobs/id:/prediction/algorithm:/items/item_id:/estimatedCost <- calculates estimated cost, takes method as argument

GET /jobs/id:/prediction/algorithm:/workers/worker_id:/ <- returns worker quality related data


POST /jobs/ <- create job with some random id and return this id similar to next one
POST /jobs/id:/ <- create new job with given id and with specified parameters
POST /jobs/id:/reset

POST /jobs/id:/costMatrix <- sends cost matrix
POST /jobs/id:/votes/ <- add votes to system - we have only version that adds multiple votes - possibly only one

POST /jobs/id:/prediction/algorithm:/calculate <- starts calculation for this project, takes parameters specific to used algorithm like number of iterations etc
POST /jobs/id:/prediction/algorithm:/


DELETE /jobs/id:/ <- deletes job

NOTE: Do we want to be able to remove some votes?
