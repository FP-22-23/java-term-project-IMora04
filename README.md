# Second Term Project. Fundamentals of programming (Course  22/23)
Author: Ignacio Mora Pérez.  uvus: ignmorper1

This dataset consists on the information registered from different accidents that caused deaths or serious injuries in Catalonia. Data was taken between 2010 and 2020 by the [Catalan Traffic Service](https://interior.gencat.cat/es/serveis/localitzador_de_comissaries/transit/).
The dataset was initially structured in 58 different columns, but for the sake of the project's simplicity, it has been reduced to 15 columns.

## Project folder structure

* **/src**: Contains the Java packages that make up the project.
  * **fp.domain**: This package contains the main type class, `Accident.java`, its container type, `Accidents.java`, and its factory, `FactoryAccident.java`
  * **fp.domain.test**: Inside the previous package, it contains the test for the types in *fp.domain*: `TestAccident.java`, `TestAccidents.java` and `TestFactory.java`
  * **fp.common**: It contains the auxiliary types used by `Accident.java` properties: `Climate.java` (Enum) and `Victims.java` (Class)
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
  * *info*, of type *List\<String\>*, gettable. It is a list with extra information about the accident, taken from the rows *Type*, *Subtype*, *Subzone*, *Moment*, and *Wind* (In that specific order).

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

### Factory - FactoryAccident

This class is a factory of the main type Accident, which is used to create objects of the type Accidents by reading CSV files. It does so with the following methods:

- *parseLine(String line)*: Given a String (Usually, a line of the CSV file read), it returns an object of the type Accident, by using the 1st constructor defined in `Accident.java`.
- *readFile(String route)*: Given the route of the CSV file as a string, it converts the information contained into a List of Accidents. It uses the previous method for each line, and then stores every Accident into the List.
- *readFileContainer(String route)*: Using the previous method, this reader converts the information contained in the CSV into individual Accidents, then into a List of Accidents, and then into a container type Accidents.

### Container type - Accidents

This is a container type that stores objects of type Accident.

It has just one **basic property**: *accidents*, of type List\<Accident\>, gettable. Is the list of the accidents stored.

**Constructors**:

* **C1**: The first constructor creates an empty Accidents object.
* **C2**: The second constructor receives a collection of accidents as parameter, and returns an Accidents object containing all the elements in the collection received.
* **C3**: The third constructor now receives a stream of accidents, and returns an Accidents object containing all the elements in that stream.

**Equality criterion**: Two Accidents objects are equal to each other if they contain the same accidents. Repetition or position stored is not taken into account.

**Basic methods**:
* *List\<Accident\> getAccidentList()*: Returns the list of accidents.
* *int getNumberAccidents()*: Returns the size of the list of accidents.
* *void addAccident(Accident a)*: Adds the accident *a* to the list of accidents.
* *void addAccidents(Collection\<Accident\> c)*: Adds all the elements contained in *c* to the list of accidents.
* *void removeAccident(Accident a)*: Removes the accident *a* of the list, if it is contained in it.

A secondary method, *Accidents getSublist(int a, int b)*, is also defined in this class. It will be used to simplify the output of the tests.

**Sequential methods**:
* *boolean allInYear(int y)*: Returns *true* if all the accidents in the list have happened in the year *y*. If not, it returns *false*.
* *boolean anyInYear(int y)*: Similar to the previous method. Returns true if any Accident has occurred int the year specified.
* *double avgVictims()*: Returns the average of the total number of victims of all the accidents in the object.
* *int countWithDeaths()*: Counts the number of accidents with deaths involved.
* *List\<Accident\> filterByYear(int y)*: Creates a new list of accidents containing only the accidents that have happened in the year *y*.
* *Map\<String, List\<Accident\>\> groupByLocation()*: This method groups every accident by its corresponding location. The map returned associates the location to the list of accidents that happened there.
* *Map\<Climate, Integer\> countByClimate()*: This map associates the different types of climate with the number of accidents that have happened with that climate.
  
**Stream methods**:
* *boolean allInYearStream(int y)*: Returns *true* if all the accidents in the list have happened in the year *y*, just like the *allInYear* method.
* *boolean anyInYearStream(int y)*: As in *anyInYear*, returns *true* if any accident occurred in year *y*.
* *double avgVictimsStream()*: Exactly the same functionality as the *avgVictims()* method.
* *int countWithDeathsStream()*: Work just as *countWithDeaths* method.
* *List\<Accident\> filterByYearStream(int y)*: As the method *filterByYear*, it creates a list of accidents containing only the accidents that have happened in the year *y*.
* *Integer maxSpeedOnClimate(Climate c)*: This method receives a Climate as a parameter, and returns the value of the maximum speed at which an accident occurred with that climate.
* *SortedSet\<Accident\> sortedVictimsWithEscapists()*: This method returns a SortedSet with the Accidents in which there were escapists, sorted by the number of total victims (from higher to lower).
* *Map\<String, List\<Accident\>\> groupByLocationStream()*: This method works in the same way as *groupByLocation*. It groups accidents by its location.
* *Map\<Climate, Integer\> countByClimateStream()*: Works in the same way as *countByClimate*. Returns a Map in which keys are all different climates, and values are the number of accidents that happened during that climate.
* *Map\<LocalDate, Victims\> accidentMostVictimsByDate()*: It returns a Map that associates every date with the maximum number of victims among all the accidents that occurred at that date (or null, if there were not).
* *Map\<String, List\<Integer\>\> groupSpeedsByType()*: This method takes the first element of the list *info* to group values of speed of accidents according to the type of accident it was.
* *SortedMap\<Climate, List\<Accident\>\> groupByClimateEarliest(int n)*: This method returns a SortedMap, in which keys are the different Climates for which accidents have happened, and values are lists with the first *n* accidents that occurred with that climate, sorted by earliest. If n is greater than the number of accidents with that corresponding climate, all those accidents will be inside the list.
* *String getLocationMostVictims()*: It uses the previous method *groupByLocationStream()* to get a map with accidents grouped by their location. Then, it operates over it to get the location where the accident with the most number of victims has happened.

### Secondary types - Victims

This auxiliary type is used as a property of each accident. It represents the number of victims in the accident. It also includes three basic **properties** (all of them of type Integer, and gettable):

* *deaths*, that indicates the number of mortal victims of the accident.
* *serious_inj*, that indicates the number of people that ended up with serious injuries during the accident.
* *slight_inj*, that also indicates the number of people that ended up with slight injuries.

Combining this three basic properties, a fourth **derived property** is created, named *totalVictims*. It is no more than the sum of the basic properties. It indicates the total number of people that were physically affected by the accident.

*Victims* type also includes a **constructor** receiving one parameter for each basic property of the type.

The **restriction** included in this type states that every property must be greater or equal to zero.

The **equality criterion** followed by this type indicates that, in order to be equal, two objects must have the same number of deahts and seriously/slightly injured victims.

For later use in `Accidents.java`, this type implements the interface **Comparable\<Victims\>**, so that different objects of the same type can be compared. The first value taken into account is the value of the total victims. If two objects have the same total victims, the "greater" will be the one with the greatest number of deaths.

When represented as a **String**, every property is shown in the same format as in type *Accident*.

### Secondary types - Climate

*Climate* is an Enum type that is also used as a basic property of *Accident*. It can take the following values:

`GOOD_WEATHER`, `HAIL`, `HEAVY_RAIN`, `LIGHT_RAIN`, `SNOWING` and `UNSPECIFIED`.
