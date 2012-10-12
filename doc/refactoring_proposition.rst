Troia refactoring proposition
=============================

Tried to keep order of importance of changes.

Design abstraction for crowdsourcing quality algorithm
------------------------------------------------------
We should define interface or base class for such algorithms.
That way we could finally make GAL and MajorityVote equal in terms of capability.
This is the part that I'm currently working - designing this.

Separating service from GAL related code
----------------------------------------
Currently code that serializes core objects to JSON is directly in core classes.
We should make GAL code unaware of Service existence.
That way we can use different ways of serialization seamlessly.
Often in REST services we are able to specify expected output format like JSON, XML etc.


Design general Service and its subclasses for specific algorithms
-------------------------------------------------------------
This would allow us to minimize code duplication.


Minor things
------------

Core components
~~~~~~~~~~~~~~~
We should create core classes like Label/Label, Item (Object is reserved..), make their methods for comparison and hashing and use them!
Avoid using Strings that represent them - just use that objects.
This will make code usage easier.

Useful things
~~~~~~~~~~~~~
Also we should avoid passing Map - if they have some semantic than we should create proper class for this.
We should have classes like:

- ElementWithProbability<T> - pair of <T, Double>
- ProbabilityDistribution<T> - collection of ElementWithProbability<T> which gives such iteration. Similar to Map, but when object is not in it it should return 0.
- ConfusionMatrix<T> - BTW many operations put there should be taken outside it ... Its API is so huge...
- CostMatrix<T> - already I have added this

Separate repo for core things
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Separate repository with core/useful stuff like math, workers etc. In all repository there is implemented Worker, Label, ProbabilityDistribution, CostMatrix, ConfusionMatrix etc..
Like **crowdsourcing_utils**

Comments
~~~~~~~~
We need to have some comments when we use not clear naming conventions.
For example in:

- CostMatrix - from/to it is not obvious what true value and what is predicted.
- Datum - one line of comment would make it clear what it represents


Fix simulations of inheritance
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
There are some places in which we use Enum type and switch command to acquire polymorphism.
Like in:

- AbstractDawidSkene with WorkerCostMethod
- Worker normalize

