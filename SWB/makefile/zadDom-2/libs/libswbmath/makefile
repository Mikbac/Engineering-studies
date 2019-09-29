CC=g++
CFLAGS=-c -Wall
OBJDIR:=obj
NEWOBJDIR:=../../obj
	
all: build

build: max.o min.o avg.o
	ar rvs $(NEWOBJDIR)/oswbmath.o $(OBJDIR)/max.o $(OBJDIR)/min.o $(OBJDIR)/avg.o


max.o: max.cpp max.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

min.o: min.cpp min.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

avg.o: avg.cpp avg.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@



.PHONY: clean

clean:
	rm -f *.a
	rm -f *.o cw7_dom1
	rm -f $(OBJDIR)/*
	rm -f $(NEWOBJDIR)/*

