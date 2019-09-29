CC=g++
CFLAGS=-c
OBJDIR:=obj
SRCDIR:=src


LIBBASEDIR:=lib

LIBDIRS:=libswbmath
LIBS:= $(addprefix $(LIBBASEDIR)/, $(LIBDIRS))
LIBS:= $(addsuffix .a, $(LIBS))


.PHONY: all

all: runMake build install

runMake:
	cd libs/libswbmath && make
	
install: cw7_dom1

build: cw7_dom1


LDFLAGS= -L /obj -oswbmath.o


cw7_dom1: cw7_dom1.o sortowanie.o
	$(CC) $(OBJDIR)/cw7_dom1.o $(OBJDIR)/sortowanie.o $(OBJDIR)/oswbmath.o -o $(OBJDIR)/$@
	

cw7_dom1.o: $(SRCDIR)/cw7_dom1.cpp
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

sortowanie.o: $(SRCDIR)/sortowanie.cpp $(SRCDIR)/sortowanie.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@


	

.PHONY: clean

clean:
	rm -f *.o cw7_dom1
	rm -f $(OBJDIR)/*
	cd libs/libswbmath && make clean
