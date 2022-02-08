# ECE 421 - Project 1

## Running instructions

Note that in order to avoid overlapping delay between the benchmarking test,
each benchmarking test waits for one minute after it has completed its testing.

Run all the benchmarking tests.
```sh
$ make run-all
```

Run just the sequential (non-concurrent) functional benchmarking test.
```sh
$ make run-sequential
```

Run just the parallel (concurrent) functional benchmarking test.
```sh
$ make run-parallel
```

Run just the imperative benchmarking test.
```sh
$ make run-imperative
```

### Run the tests

The tests require JUnit to be installed. In VSCode, one can install the Java
coding pack and extension pack. This allows VSCode to download JUnit and
identify where it is on your system.

Once this is complete, VSCode should show a `Run Test` label above the class
name in the `TestPickShareFunctional.java` and `TestPickShareImperative.java`
files. Click this label to run the tests.

The tests take a minute or two to run (due to the API restriction), but they
should all pass.

## Private link to the report

https://docs.google.com/document/d/1CsQg9MFQt-QFoCQZkKGNxPCNsR3nC8dI7YpGRyfnCrc/edit?usp=sharing

The report can be seen in the submission of this assignment.
