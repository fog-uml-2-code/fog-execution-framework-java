# fog-execution-framework-java

*fog-execution-framework-java* is a Java framework for Fog/IoT applications, used by the [FogUML2Code](https://github.com/fog-uml-2-code/fog-uml-2-code) code generation project.

This framework provides:
* Automatic constraint validation using [@PreCondition](./activity-realization/src/main/java/pusztai/thomas/architecture/fog/validation/PreCondition.java) and [@Invariant](activity-realization/src/main/java/pusztai/thomas/architecture/fog/validation/Invariant.java).
* Execution of business processes defined as UML activities and serialized using FogUML2Code.


## Installation

To be able to use this library in a FogUML2Code generated project, you must first install it to your local Maven repository:
1. Open a terminal in the root folder of fog-execution-framework-java.
2. Run `mvn install`
