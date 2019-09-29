<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/Wyciag">
	<html>
	<body>
	
	<table border="1" style="background-color: #7AC0D3;">
	
	<table border="1" style="background-color: #70A9CB;">
	
		<tr bgcolor="#B0B0B0">
		<th>Naglowek</th>
		<th>Bank</th>
		<th>Podmiot</th>
		<th>AdresPodmiotu</th>

		
		
		
		
		</tr>
		
		<tr>
			<th>
				<p>kod Systemowy: <xsl:value-of select="Naglowek/KodFormularza/@kodSystemowy"/></p>
				<p>wersja Schemy: <xsl:value-of select="Naglowek/KodFormularza/@wersjaSchemy"/></p>
				<p>Kod Formularza: <xsl:value-of select="Naglowek/KodFormularza"/></p>
				<p>Wariant Formularza: <xsl:value-of select="Naglowek/WariantFormularza"/></p>
				<p>Cel Zlozenia: <xsl:value-of select="Naglowek/CelZlozenia"/></p>
				<p>Data Wytworzenia JPK: <xsl:value-of select="Naglowek/DataWytworzeniaJPK"/></p>
				<p>Data Od: <xsl:value-of select="Naglowek/DataOd"/></p>
				<p>Data Do: <xsl:value-of select="Naglowek/DataDo"/></p>
				<p>Domyslny Kod Waluty: <xsl:value-of select="Naglowek/DomyslnyKodWaluty"/></p>
				<p>Kod Urzedu: <xsl:value-of select="Naglowek/KodUrzedu"/></p>
			</th>	
			<th>
				<p>Nazwa Banku: <xsl:value-of select="Naglowek/Bank/NazwaBanku"/></p>
				<p>Adres Banku: <xsl:value-of select="Naglowek/Bank/AdresBanku"/></p>
				<p>Strona Internetowa: <xsl:value-of select="Naglowek/Bank/StronaInternetowa"/></p>
				<p>Infolinia: <xsl:value-of select="Naglowek/Bank/Infolinia"/></p>
					
				<table border="1" style="background-color: #A2C4BC;">	
				<tr bgcolor="#B0B0B0">
						
					<th>Saldo</th>
				</tr>
				<th>
					<p>Saldo Poczatkowe: <xsl:value-of select="Naglowek/Saldo/SaldoPoczatkowe"/></p>
					<p>Saldo Koncowe: <xsl:value-of select="Naglowek/Saldo/SaldoKoncowe"/></p>
				</th>
				</table>
				
			</th>	

			
			<th>
				<p>Numer Rachunku: <xsl:value-of select="Podmiot/NumerRachunku"/></p>
				<p>Numer IBAN: <xsl:value-of select="Podmiot/NumerIBAN"/></p>
				<p>Rodzaj Rachunku: <xsl:value-of select="Podmiot/RodzajRachunku"/></p>
				<p>Waluta Rachunku: <xsl:value-of select="Podmiot/WalutaRachunku"/></p>
				
				<table border="1" style="background-color: #A2C4BC;">	
				<tr bgcolor="#B0B0B0">
						
					<th>Identyfikator Podmiotu</th>
				</tr>
				<th>

				<p>Imie: <xsl:value-of select="Podmiot/IdentyfikatorPodmiotu/Imie"/></p>
				<p>Nazwisko: <xsl:value-of select="Podmiot/IdentyfikatorPodmiotu/Nazwisko"/></p>
				<p>Data Urodzenia: <xsl:value-of select="Podmiot/IdentyfikatorPodmiotu/DataUrodzenia"/></p>
				<p>Pesel: <xsl:value-of select="Podmiot/IdentyfikatorPodmiotu/Pesel"/></p>
					
					
				</th>
				</table>
				
				
			</th>

			<th>
				<p>Kod Kraju: <xsl:value-of select="Podmiot/AdresPodmiotu/KodKraju"/></p>
				<p>Wojewodztwo: <xsl:value-of select="Podmiot/AdresPodmiotu/Wojewodztwo"/></p>
				<p>Powiat: <xsl:value-of select="Podmiot/AdresPodmiotu/Powiat"/></p>
				<p>Gmina: <xsl:value-of select="Podmiot/AdresPodmiotu/Gmina"/></p>
				<p>Ulica: <xsl:value-of select="Podmiot/AdresPodmiotu/Ulica"/></p>
				<p>Nr Domu: <xsl:value-of select="Podmiot/AdresPodmiotu/NrDomu"/></p>
				<p>Nr Lokalu: <xsl:value-of select="Podmiot/AdresPodmiotu/NrLokalu"/></p>
				<p>Miejscowosc: <xsl:value-of select="Podmiot/AdresPodmiotu/Miejscowosc"/></p>
				<p>Kod Pocztowy: <xsl:value-of select="Podmiot/AdresPodmiotu/KodPocztowy"/></p>
				<p>Poczta: <xsl:value-of select="Podmiot/AdresPodmiotu/Poczta"/></p>
			</th>
		</tr>
  
	</table>
	
	

    <table border="1" style="background-color: #A6ACC1;">
	
	<tr bgcolor="#42B8FE">
		<th>
			Operacje:
		</th>
	</tr>
		<table border="1" style="background-color: #A6ACC1;">
      <tr bgcolor="#42B8FE">
        <th>Identyfikator Operacji</th>
		<th>id</th>
		<th>Data Operacji</th>
		<th>Godzina Operacji</th>
		<th>Data Waluty</th>
		<th>Typ Platnosci</th>
		<th>Waluta</th>
		<th>Nazwa Podmiotu</th>
		<th>Nr Rachunku Przeciwstawnego</th>
		<th>Dane Adresowe</th>
		<th>Tytul</th>
		<th>Typ Operacji</th>
		<th>Kwota Operacji</th>
		<th>Saldo</th>
		
      </tr>
	  
      <xsl:for-each select="Operacje/Operacja">
	  
	  
      <tr>
			<td><xsl:value-of select="@IdentyfikatorOperacji"/></td>
			<td><xsl:value-of select="@id"/></td>
			<td><xsl:value-of select="DataOperacji"/></td>
			<td><xsl:value-of select="GodzinaOperacji"/></td>
			<td><xsl:value-of select="DataWaluty"/></td>
			<td><xsl:value-of select="TypPlatnosci"/></td>
			<td><xsl:value-of select="Waluta"/></td>
			<td><xsl:value-of select="OpisOperacji/NazwaPodmiotu"/></td>
			<td><xsl:value-of select="OpisOperacji/NrRachunkuPrzeciwstawnego"/></td>
			<td><xsl:value-of select="OpisOperacji/DaneAdresowe"/></td>
			<td><xsl:value-of select="OpisOperacji/Tytul"/></td>
			<td><xsl:value-of select="OpisOperacji/TypOperacji"/></td>
			<td><xsl:value-of select="KwotaOperacji"/></td>
			<td><xsl:value-of select="Saldo"/></td>

      </tr>
	  
	  
	  
      </xsl:for-each>
	  
	  
	  
	</table>
	
	<div id = "bilans">
		BILANS = <xsl:value-of select="sum(//KwotaOperacji)"/>
	</div>
	
    </table>
	</table>
	
	
	
	
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>

