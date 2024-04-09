CC = gcc 
CFLAGS = -Wall -g

SRC = $(wildcard ./*.c)
EXES = $(SRC:.c=)

all: $(EXES)

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean: 
	rm $(EXES) fifo
