<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">




<xsl:template match="/grupa">


			<ListaObecnosci>
				<xsl:attribute name="data">
					<xsl:value-of  select="current-date()"/>
				</xsl:attribute>
	
				<xsl:for-each select="student">
				
					<Student>
						<xsl:attribute name="nr_indeksu">
							<xsl:value-of select="@nr_indeks"/>
						</xsl:attribute>
						
						<Imie_Nazwisko>
							<xsl:value-of select="personalne/imie"/>
							<xsl:text> </xsl:text>
							<xsl:value-of select="personalne/nazwisko"/>
						</Imie_Nazwisko>

						<Studia>
							<Kierunek><xsl:value-of select="uczelniane/kierunek"/></Kierunek>
							<Wydzial><xsl:value-of select="uczelniane/wydzial"/></Wydzial>
							<Rok><xsl:value-of select="uczelniane/rok"/></Rok>
						</Studia>
						
						<Plec><xsl:value-of select="personalne/plec"/></Plec>
					
						<Ulubione>
							<Film></Film>
							<Jezyk></Jezyk>
							<Potrawa><xsl:value-of select="inne/ulubiona_potrawa"/></Potrawa>
							<Napoj><xsl:value-of select="inne/ulubiony_napoj"/></Napoj>
						</Ulubione>
						
						<Studenci_grupa>
							<xsl:value-of select="count(//student)"/>
						</Studenci_grupa>
						
					</Student>
			
				</xsl:for-each>


			</ListaObecnosci>
			
		

</xsl:template>

</xsl:stylesheet>