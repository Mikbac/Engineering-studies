CC=g++
CFLAGS=-c
OBJDIR:=obj
SRCDIR:=src

.PHONY: all

all: build install

install: cw7_dom1

build: cw7_dom1

cw7_dom1: cw7_dom1.o sortowanie.o max.o min.o avg.o
	$(CC) $(OBJDIR)/cw7_dom1.o $(OBJDIR)/sortowanie.o $(OBJDIR)/max.o $(OBJDIR)/min.o $(OBJDIR)/avg.o -o $(OBJDIR)/$@

cw7_dom1.o: $(SRCDIR)/cw7_dom1.cpp
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

sortowanie.o: $(SRCDIR)/sortowanie.cpp $(SRCDIR)/sortowanie.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

max.o: $(SRCDIR)/max.cpp $(SRCDIR)/max.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

min.o: $(SRCDIR)/min.cpp $(SRCDIR)/min.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

avg.o: $(SRCDIR)/avg.cpp $(SRCDIR)/avg.h
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@

.PHONY: clean

clean:
	rm -f *.o cw7_dom1
	rm -f $(OBJDIR)/*

