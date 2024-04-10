CC = gcc 
CFLAGS = -Wall -g

SRC = $(wildcard ./*.c)
EXES = $(SRC:.c=)

all: $(EXES)

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean: $(fifos) 
	rm $(EXES) $(fifos)

fifos: tmp/*
	rm tmp/*
