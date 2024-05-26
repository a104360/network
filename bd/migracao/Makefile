CC = javac
ENTIDADES = $(wildcard tabelas/*.java)
CLASSES = $(ENTIDADES:tabelas/.java=tabelas/.class)


all: iap

iap: Iap.class
	 export CLASSPATH=mysql-connector-java-8.4.0.jar:. && java Iap root retr0.Root

Iap.class: Iap.java $(CLASSES)
	$(CC) $^

tabelas/%.class: tabelas/%.java 
	$(CC) -d tabelas $<

clean:
	rm -f ./*.class