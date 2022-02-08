JC = javac
JFILES = APIFinance.java Main.java PickShareFunctional.java PickShareImperative.java ShareInfo.java Shares.java ShareUtil.java

all:
	$(JC) $(JFILES)

# Run the parallel functional benchmarking test.
run-parallel: all
	java Main --parallel

# Run the sequential functional benchmarking test.
run-sequential: all
	java Main

# Run the imperative benchmarking test.
run-imperative: all
	java Main --imperative

# Run all benchmarking tests.
run-all: all
	java Main --all

clean:
	rm -rf *.class
