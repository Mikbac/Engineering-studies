#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <unistd.h>


#include "../lib/src/EURtoPLN.hpp"
#include "../lib/src/PLNtoEUR.hpp"
#include "../lib/src/USDtoPLN.hpp"
#include "../lib/src/PLNtoUSD.hpp"


using namespace std;

int main( int argc, char* argv[] )
{
	
	const char* nvalue = "World" ;
    int tvalue = 1 ;

    int c ;
    while( ( c = getopt (argc, argv, "b:s:") ) != -1 )
    {
        switch(c)
        {
            case 's':
                if(optarg) nvalue = optarg;
                break;
            case 'b':
                if(optarg) tvalue = std::atoi(optarg) ;
                break;
        }
    }

    for( int i = 0 ; i < tvalue; ++i ){
		std::cout <<"----------------------------------\n";
	  std::cout << nvalue << " po raz: " << i+1 <<"!\n";
	  
		printExchangeRateEURtoPLN(12.99+i);
		printExchangeRatePLNtoEUR(12.99+i);
		printExchangeRateUSDtoPLN(12.99+i);
		printExchangeRatePLNtoUSD(12.99+i);
	  
    }
	
	
	
    
	system("rm -rf ./wynik");
    system("mkdir wyniki");
	system("touch wyniki/plik.txt");
    system("echo \"program zostal uruchomiony poprawnie\" > wyniki/plik.txt");
	system("ls");
	
//  system("main > wyniki/plik.txt");

    return 0;
}
