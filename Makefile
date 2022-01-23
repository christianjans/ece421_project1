JC = javac

all:
	$(JC) *.java

run: all
	java Main

run-parallel: all
	java Main --parallel

clean:
	rm -rf *.class
