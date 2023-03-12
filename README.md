# First Term Project. Fundamentals of programming (Course  22/23)
Author: Ignacio Mora Pérez.  uvus: ignmorper1

This dataset consists on the information registered from different accidents that caused deaths or serious injuries in Catalonia. Data was taken between 2010 and 2020 by the [Catalan Traffic Service](https://interior.gencat.cat/es/serveis/localitzador_de_comissaries/transit/).
The dataset was initially structured in 58 different columns, but for the sake of the project's simplicity, it has been reduced to 15 columns.

## Project folder structure

* **/src**: Contains the Java packages that make up the project.
  * **fp.domain**: This package contains the main type class, Accident.java
  * **fp.domain.test**: Inside the previous package, it contains the test for the main type, TestAccident.java
  * **fp.common**: It contains the auxiliary types used by Accident.java properties: Climate.java (Enum) and Victims.java (Class)
* **/data**: Contains the project dataset.
   * **Accidents_catalonia.csv**: CSV file with the data of the accidents.
    
## *Dataset* structure

The original dataset can be found [here](https://www.kaggle.com/datasets/jordigarciacastillon/road-traffic-injuries-deaths-catalonia-201020). It is made up of 15 columns, with the following description:

* **Date**: type date, indicates the date in which the accident occurred.
* **Municipality**: type string, contains the name of the municipality where the accident occurred.
* **Region**: type string, also contains the name of the region where the accident occurred.
* **City**: type string, contains the name of the city where the accident occurred.
* **Deaths**: type integer, indicates the number of deaths that took place during the accident.
* **Seriously injured**: type integer, indicates how many people was seriously injured.
* **Slightly injured**:type integer, indicates how many people was slightly injured.
* **Speed**: type integer, indicates the maximum speed allowed in the track in which the accident took place. It can also take value "NA", that could mean unspecified speed limit.
* **Escapists**: type boolean, contains information about if there were any escapists that left after the accident
* **Climate**: type string, indicates the weather during the accident. It can take values: Good weather, Hail, Heavy/Light rain, Snowing, or Unspecified.
* **Subtype**: type string, contains information about how/when/where the accident happened.
* **Subzone**: type string, indicates if the accident happened in Urban zone, Road or Crossing (Spanish Travesía).
* **Wind**: type string, indicates the strength of wind during the accident.
* **Moment**: type string, contains the moment of the day in which the accident happened.
* **Type**: type string, indicates the type of accident.

## Implemented types:

### Main type - Accident

This type is defined in the file  `Accident.java`. Each object of the type Accident represent a single accident. They have the following **properties**:

* **Basic properties:**
  * *date*, of type *LocalDate*, gettable. Indicates the accident's date.
  * *location*, of type *String*, gettable. Takes values from columns *Municipality*, *Region* and *City*, and creates a longer String containing the full location of the accident, in the format "*municipality*, *region*, *city*"
  * *victims*, of type *Victims*, gettable. Takes the information from columns *Deaths*, *Seriously injured* and *Slightly injured* to create a new type, defined in [Victims.java](#secondary-types---victims)
  * *speed*, of type *Integer*, gettable. Indicates the maximum speed allowed in the road where the accident happened.
  * *escapist*, of type *Boolean*, gettable. Stores *true* if the value of the column *Escapists* is "Yes", and *false* if the value is "No". If the value is "Unspecified", it stores null.
  * *climate*, of type *Climate*, gettable. Specifies the weather during the accident
  * *info*, of type *List<String>*, gettable. It is a list with extra information about the accident, taken from the rows *Type*, *Subtype*, *Subzone*, *Wind*, and *Moment*.

* **Derived properties:**
  * *deathsProportion*, of type *Double*. Derivated from the basic property *Victims*, it uses methods from Victims.java to get the proportion of deaths with respect to the total number of victims. This value will always be between 0 and 1.

**Constructors**:

* **C1**: The first constructor receives a value for each basic property of the type.
* **C2**: This second constructor will receive just a String, and will split it into the different properties of the type. The string contains values for each basic property.

**Restrictions**:

* **R1**: The accident's date must be lower or equal than today's date.
* **R2**: The maximum speed of the road cannot be negative.
* **R3**: String must have 15 values (Each one corresponding to one of the columns of the CSV file)

**Equality criterion**: An accident is equal to other accident if, and only if, every basic property of both of them is equal.

**Natural order criterion**: Accidents will be order by date. If both accidents have the same date, they will be ordered by location.

**String representation**: If we want to represent an accident as a string, every property (basic and derived) will be expressed in the format "*propertyName*=*propertyValue*"

### Secondary types - Victims

This auxiliary type is used as a property of each accident. It represents the number of victims in the accident. It also includes three basic **properties** (all of them of type Integer, and gettable):

* *deaths*, that indicates the number of mortal victims of the accident.
* *serious_inj*, that indicates the number of people that ended up with serious injuries during the accident.
* *slight_inj*, that also indicates the number of people that ended up with slight injuries.

Combining this three basic properties, a fourth **derived property** is created, named *totalVictims*. It is no more than the sum of the basic properties. It indicates the total number of people that were physically affected by the accident.

*Victims* type also includes a **constructor** receiving one parameter for each basic property of the type.

The **restriction** included in this type states that every property must be greater or equal to zero.

The **equality criterion** followed by this type indicates that, in order to be equal, two objects must have the same number of deahts and seriously/slightly injured victims.

This type does not follow any natural order criterion (**not comparable**).

When represented as a **String**, every property is shown in the same format as in type *Accident*

### Secondary types - Climate

*Climate* is an Enum type that is also used as a basic property of *Accident*. It can take the following values:

`GOOD_WEATHER`, `HAIL`, `HEAVY_RAIN`, `LIGHT_RAIN`, `SNOWING` and `UNSPECIFIED`.