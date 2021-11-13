# ALTO Parser

This project is a parser for [ALTO (Analyzed Layout and Text Object)](https://de.wikipedia.org/wiki/Analyzed_Layout_and_Text_Object). It transform ALTO input in Version 3 (3.0 and 3.1) into a Java object structure. Further ALTO versions can easily be added given that this project is based on generated classes from the ALTO xsd-schema.

As the generated classes are based on the official xsd-schema (found [here](https://github.com/altoxml/schema)), this project supports every element that is present in the official specification.

For usage examples, take a look at the test cases.
