1 I suppose that we have unlimited amount of categories.
Based on this I decided move from Enum to simple Set<String>.
2 According to description of task MyMovie might have additional metadata.
I decided to choose Map<String, String> for this purpose.
Because metadata for each movie could be different.
For example:
-duration: 3 hs;
-manufacture: Sony.

3 MainSolution starts with creation of collection of MyMovies.
I`m creating MyMovie with categories and metadata, and store it into repository.
4 repository
For now it`s just in memory storage (Java.Collections). In the future I might switch repository to DB.
I decided to use "template Pattern" to specify logic of checking whether MyMovie matches categories.
    protected abstract boolean isSearchable(MyMovie movie, Set<String> searchCategories);
5 MyCustomer
Has String name and shopCard.
MyCustomer can find MyMovie by title and rent it by particular amount of days.
To do it MyCustomer creates MyRental object and put rental to ShopCard.
6 To process shopCard of MyCustomer I use PriceProcessor.
7 exporter
I decided to take "factory Pattern" to switch between different exporters.
Also, it`s good to have new type of exporter in the future (pdf, etc.)
8 UnitTest
I have covered CustomerExporter with JUnit.
Decided not to increase test coverage as we are going to use TestNG way of testing.
9 For HTML exporter I used template that is located in resources folder of the application