usage:
	@echo "Read README.md for directions"
encode: Hill.class Matrix.class
	@java Hill encode $(ARGS)
decode: Hill.class Matrix.class
	@java Hill decode $(ARGS)
bruteforce: Hill.class Matrix.class
	@java Hill bruteforce $(ARGS)
known-plaintext: Hill.class Matrix.class
	@java Hill known-plaintext $(ARGS)
Hill.class: Hill.java
	@javac Hill.java
Matrix.class: Matrix.java
	@javac Matrix.java
clean:
	@rm *.class
	@echo "Removed class files."
