src = $(wildcard ./*.c)
obj = $(src:.c=.o)
exe = $(obj:.o=)

all: $(exe) 

%: %.o
	gcc -Wall $< -o $@

clean: 
	rm $(exe)
