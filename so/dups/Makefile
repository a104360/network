src = $(wildcard ./*.c)
obj = $(src:.c=.o)
exe = $(obj:.o=)

all: $(exe) $(obj) 

%.o: %.c
	gcc -Wall -g -c $< -o $@

%: %.o
	gcc -g -Wall $< -o $@

clean: 
	rm $(exe) $(obj)
