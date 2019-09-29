{
default(parisize, 120000000);
setrand(getwalltime());

k = 250;

print("<---------------------------------->");
print("<--------------START--------------->");
print("<---------------------------------->");
data = read("kA-taj.out");

	print("Ea=", a);
	print("Eb=", b);
	print("Ep=", p);

	
	print("p=", p);

	
	print("Px=", Px);
	print("Py=", Py);

	
	print("Xa=", Xa);




data = read("C1C2.out");

	print("C1x=", C1x);
	print("C1y=", C1y);
	
	print("C2x=", C2x);
	print("C2y=", C2y);



print("<---------------------------------->");
print("<--------------Krok-1--------------->");
print("<---------------------------------->");

							n = Xa;


							nBin = binary(n);
							print("nBin = ", nBin);
								/*print("nBin[1] = ", nBin[1]);
								print("nBin[2] = ", nBin[2]);
								print("nBin[6] = ", nBin[6]);*/
								
							position = length(nBin);

							/*-krok-1-*/
							print("krok-1");

							PX = C1x;
							PY = C1y;

							QX = PX;
							QY = PY;

							if( nBin[position] == 0,

							RX = QX;
							RY = QY;

							);

							if( nBin[position] == 1,

							RX = PX;
							RY = PY;

							);

							position = position - 1;

							/*-krok-2-*/

							while(position != 0,

							print("<---------------------------------->");


							print("position = ", position);



													m = Mod(((3 * QX^2 + a)/(2*QY)),p);

													x3 = Mod((m^2 - 2*QX), p);
													y3 = Mod((m * (QX - x3) - QY), p);

														print("x3=", lift(x3));
														print("y3=", lift(y3));



																			print("----->----<-----");
																			print("----->Test<-----");
																			print("----->----<-----");

																			print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
																			print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
																			
																		if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

																		print("True");

																		);




													QX = x3;
													QY = y3;



							print("wartosc bin ------->" , nBin[position]);
							print("bin ------->" , nBin);
							if(nBin[position] == 1,


							x1 = RX;
							y1 = RY;

							x2 = QX;
							y2 = QY;



													print("Przypadek-1");
													if(x1 != x2,
														print("wybrano ----> Przypadek-1");
														
													m = Mod(((y2-y1)/(x2-x1)), p);
													x3 = Mod((m^2 - x1 - x2),p);
													y3 = Mod((m*(x1-x3)-y1),p);

														x3 = lift(x3);
														y3 = lift(y3);

														print("x3=", x3);
														print("y3=", y3);
														
														
																					print("----->----<-----");
																					print("----->Test<-----");
																					print("----->----<-----");

																					print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
																					print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
																					
																				if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

																				print("True");

																				);
														
														
													RX = x3;
													RY = y3;


													);

													/*Przypadek-2-*/
													print("Przypadek-2");
													if(x1 == x2 && y1 == y2 && y1 != 0,
														print("wybrano ----> Przypadek-2");
													m = Mod(((3 * x1^2 + a)/(2*y1)),p);

													x3 = Mod((m^2 - 2*x1), p);
													y3 = Mod((m * (x1 - x3) - y1), p);

														print("x3=", lift(x3));
														print("y3=", lift(y3));
														
														
																					print("----->----<-----");
																					print("----->Test<-----");
																					print("----->----<-----");

																					print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
																					print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
																					
																				if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

																				print("True");
																				
																				

																				);	
													
														RX = x3;
														RY = y3;
														

													);

													/*Przypadek-3-*/
													print("Przypadek-3");
													if(x1 == x2 && y1 != y2,
														print("wybrano ----> Przypadek-3");

														print("None");
														
														RX = 0;
														RY = 0;

													);

													/*Przypadek-4-*/
													print("Przypadek-4");
													if(x1 == x2 && y1 == y2 && y1 == 0,
														print("wybrano ----> Przypadek-4");

														print("None");
														
														RX = 0;
														RY = 0;

													);



							);
														





							position = position - 1;
							);

							print("<---------------------------------->");
							print("<--------------WYNIKI-------------->");
							print("<---------------------------------->");
							print("--->RX = ", RX);
							print("--->RY = ", RY);
							print("<---------------------------------->");
							print("<---------------------------------->");
							print("<---------------------------------->");

							print("----->----<-----");
							print("----->Test<-----");
							print("--->Wynikow<---");
							print("----->----<-----");

							print("lift(Mod(RY^2, p)) = ", lift(Mod(RY^2, p)));
							print("lift(Mod(RX^3 + a * RX + b, p)) = ", lift(Mod(RX^3 + a * RX + b, p)));


							if(Mod(RY^2, p) == Mod(RX^3 + a * RX + b, p),

							print("True");

							);



		Rx = RX;
		Ry = RY;

		print("Rx = ", Rx);
		print("Ry = ", Ry);



print("<---------------------------------->");
print("<--------------Krok-2--------------->");
print("<---------------------------------->");

Sx = -Rx;
Sy = -Ry;

print("<---------------------------------->");
print("<--------------Krok-3--------------->");
print("<---------------------------------->");



								x1 = C2x;
								y1 = C2y;
							
								x2 = Sx;
								y2 = Sy;
							
							print("a=", a);
							print("b=", b);
							print("p=", p);	


						/*Przypadek-1-*/
						print("Przypadek-1");
						if(x1 != x2,
							print("wybrano ----> Przypadek-1");
							
						m = Mod(((y2-y1)/(x2-x1)), p);
						x3 = Mod((m^2 - x1 - x2),p);
						y3 = Mod((m*(x1-x3)-y1),p);

							x3 = lift(x3);
							y3 = lift(y3);

							print("x3=", x3);
							print("y3=", y3);
							
							
							print("----->----<-----");
							print("----->Test<-----");
							print("----->----<-----");

							print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
							print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
							
						if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

						print("True");
						
						PMx = x3;
						PMy = y3;

						);
							

						);

						/*Przypadek-2-*/
						print("Przypadek-2");
						if(x1 == x2 && y1 == y2 && y1 != 0,
							print("wybrano ----> Przypadek-2");
						m = Mod(((3 * x1^2 + a)/(2*y1)),p);

						x3 = Mod((m^2 - 2*x1), p);
						y3 = Mod((m * (x1 - x3) - y1), p);

							print("x3=", lift(x3));
							print("y3=", lift(y3));
							
							
							print("----->----<-----");
							print("----->Test<-----");
							print("----->----<-----");

							print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
							print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
							
						if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

						print("True");
						
						PMx = x3;
						PMy = y3;

						);	

							

						);

						/*Przypadek-3-*/
						print("Przypadek-3");
						if(x1 == x2 && y1 != y2,
							print("wybrano ----> Przypadek-3");

							print("None");
							
							PMx = 0;
							PMy = 0;

						);

						/*Przypadek-4-*/
						print("Przypadek-4");
						if(x1 == x2 && y1 == y2 && y1 == 0,
							print("wybrano ----> Przypadek-4");

							PMx = 0;
							PMy = 0;

						);

print("----->----<-----");
print("----->Wynik<-----");
print("----->----<-----");


print("----->PMx = ", PMx);
print("----->PMy = ", PMy);


print("----->----<-----");
print("----->----<-----");
print("----->----<-----");
}