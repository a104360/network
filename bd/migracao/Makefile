CC = javac

all: iap

iap: Iap.class
	 export CLASSPATH=mysql-connector-java-8.4.0.jar:. && java Iap root retr0.Root

Iap.class: Iap.java
	$(CC) $^

clean:
	rm -f ./*.class