CC=g++
CFLAGS=-c -Wall
SRCDIR:=src
OBJDIR:=obj
LIBDIR:=lib
LIBSRCDIR:=lib/src
LIBOBJDIR:=lib/obj



LIBBASEDIR:=lib

LIBDIRS:=currencyLib
LIBS:= $(addprefix $(LIBBASEDIR)/, $(LIBDIRS))
LIBS:= $(addsuffix .a, $(LIBS))



all: lib main


main: main.o
	$(CC) $(OBJDIR)/main.o $(LIBDIR)/currencyLib.o -o $(OBJDIR)/$@

main.o: $(SRCDIR)/main.cpp
	$(CC) $(CFLAGS) $< -o $(OBJDIR)/$@





lib: EURtoPLN.o PLNtoEUR.o USDtoPLN.o PLNtoUSD.o
	ar rvs $(LIBDIR)/currencyLib.o $(LIBOBJDIR)/EURtoPLN.o $(LIBOBJDIR)/PLNtoEUR.o $(LIBOBJDIR)/USDtoPLN.o $(LIBOBJDIR)/PLNtoUSD.o

EURtoPLN.o: $(LIBSRCDIR)/EURtoPLN.cpp $(LIBSRCDIR)/EURtoPLN.hpp
	$(CC) $(CFLAGS) $< -o $(LIBOBJDIR)/$@

PLNtoEUR.o: $(LIBSRCDIR)/PLNtoEUR.cpp $(LIBSRCDIR)/PLNtoEUR.hpp
	$(CC) $(CFLAGS) $< -o $(LIBOBJDIR)/$@

USDtoPLN.o: $(LIBSRCDIR)/USDtoPLN.cpp $(LIBSRCDIR)/USDtoPLN.hpp
	$(CC) $(CFLAGS) $< -o $(LIBOBJDIR)/$@

PLNtoUSD.o: $(LIBSRCDIR)/PLNtoUSD.cpp $(LIBSRCDIR)/PLNtoUSD.hpp
	$(CC) $(CFLAGS) $< -o $(LIBOBJDIR)/$@



.PHONY: clean

clean:
	rm -rf ./wynik
	rm -rf ./obj/*
	rm -rf ./out/*.o
	rm -rf ./lib/*.o
	rm -rf ./lib/obj/*.o
	