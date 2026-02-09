### Hexlet tests and linter status: ###

[![Actions Status](https://github.com/artmper/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/artmper/java-project-78/actions)

### SonarQube code analysis: ###
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=artmper_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=artmper_java-project-78) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=artmper_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=artmper_java-project-78) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=artmper_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=artmper_java-project-78) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=artmper_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=artmper_java-project-78)

# Data Validator

A simple Java library for validating different types of data.

## What it does

This library lets you validate strings, numbers, and maps with a fluent API. You can chain validation rules together to build complex validators.

## How to use it

First, create a `Validator` instance:

```java
import hexlet.code.Validator;

Validator v = new Validator();
```

### String validation

```java
var schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.minLength(5);

schema.isValid("what"); // false
schema.isValid("hello world"); // true

schema.contains("wh");

schema.isValid("what does the fox say"); // true
schema.isValid("hello world"); // false
```

### Number validation

```java
var schema = v.number();

schema.isValid(5); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10); // true

schema.positive();

schema.isValid(10); // true
schema.isValid(-10); // false
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

### Map validation

```java
var schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true

var data = new HashMap<String, String>();
data.put("key1", "value1");

schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data); // false

data.put("key2", "value2");

schema.isValid(data); // true
```

### Nested validation (shape)

You can also validate the structure of maps with nested schemas:

```java
var schema = v.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema.isValid(human3); // false (too short)
```

## Available validation methods

### StringSchema
- `required()` - makes string non-null and non-empty
- `minLength(int length)` - sets minimum length requirement
- `contains(String substring)` - checks if string contains substring

### NumberSchema
- `required()` - makes number non-null
- `positive()` - number must be greater than 0
- `range(int min, int max)` - number must be between min and max (inclusive)

### MapSchema
- `required()` - makes map non-null
- `sizeof(int size)` - sets exact size requirement
- `shape(Map<String, BaseSchema<String>> schemas)` - validates map values against provided schemas

## How it works

The library uses a predicate-based approach where each validation rule adds a check to the schema. When you call `isValid()`, it runs through all the checks you've added.

One thing to note: most validation methods allow `null` values to pass unless you explicitly call `required()` first. This is intentional to make the validators more flexible.

## Setup

Just add the source files to your project and make sure they're in the `hexlet.code` package structure:

```
src/main/java/hexlet/code/
├── Validator.java
└── schemas/
    ├── BaseSchema.java
    ├── StringSchema.java
    ├── NumberSchema.java
    └── MapSchema.java
```

## Testing

The project includes JUnit tests in the test directory. Run them with:

```bash
./gradlew test
```
