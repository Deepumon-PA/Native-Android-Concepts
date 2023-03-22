package com.deepu.clearnArchitectureNoteApp

//Types of tests

//1. 70% Unit Tests: tests business logic, single components (Classes, functions...etc) (Smallest, Fastest, Covers majority of test cases) , just need JVM
// Instrumented Unit Tests : need Android components like context, ui tests, fragments, activities...etc (they need an emulator to run)

//2. 20% Integration Tests: Tests the behaviour of multiple components working together (eg: toggling ui (composable interacting with viewModel))

//3. 10% End to End Tests: Quite big tests, It simulates user behaviour, (Adding and editing notes working, tapping button ..etc)

//Test Directory: Contain Unit Tests

//Android Test Directory: Instrumented Unit Tests : need Android components like context,

//JUnit: is the testing framework for java and kotlin