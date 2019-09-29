{
default(parisize, 120000000);
k = 250;


setrand(getwalltime());

print("<---------------------------------->");
print("<--------------START--------------->");
print("<---------------------------------->");


print("<---------------------------------->");
print("<--------------Krok-1--------------->");
print("<---------------------------------->");

p = randomprime(2^k);

print("<---------------------------------->");
print("<--------------Krok-2--------------->");
print("<---------------------------------->");





												/*-1-*/
												print("krok-1");


													while(Mod(p, 4) != Mod(3, 4),

														p = randomprime(2^k);

													);


												/*-2-*/
												print("krok-2");

													a = lift(Mod(randomprime(2^k), p));
													b = lift(Mod(randomprime(2^k), p));


												/*-3-*/
												print("krok-3");

													delE = (4 * (a^3)) + (27 * (b^2));
													/*delE = (4 * a^3) + (27 * b^2);*/

												/*-4-*/
												print("krok-4");

													while(Mod(delE, p) == Mod(0, p), 

													/*-skok-do-2-*/

														a = lift(Mod(randomprime(2^k), p));
														b = lift(Mod(randomprime(2^k), p));


														delE = (4 * (a^3)) + (27 * (b^2));
														/*delE = (4 * a^3) + (27 * b^2);*/

													);


												/*-5-*/
												print("krok-5");


													print("a=", a);
													print("b=", b);
													print("p=", p);


print("<---------------------------------->");
print("<--------------Krok-3--------------->");
print("<---------------------------------->");



											/*-1-*/
											print("krok-1");

												x = lift(Mod(randomprime(2^k), p));


											/*-2-*/
											print("krok-2");

												f = lift(Mod((x^(3) + a * x + b), p));


											/*-3-*/
											print("krok-3");

												while(Mod(f,p)^((p-1)/2) == Mod(-1, p),

													/*-skok-do-1-*/

													x = lift(Mod(randomprime(2^k), p));

													f = lift(Mod((x^(3) + a * x + b), p));

												);

												print("f = ", f);


											/*-4-*/
											print("krok-4");
											print("b=", b);
											print("f=", f);


												y = lift(Mod(f, p)^((p+1)/4));


											/*-5-*/
											print("krok-5");

											print("x=", x);
											print("y=", y);

											/*
											write("punkt.out", "x=", x);
											write("punkt.out", "y=", y);
											*/

											/*-Test-*/
											print("----->----<-----");
											print("----->Test<-----");
											print("----->----<-----");

											print("lift(Mod(y^2, p)) = ", lift(Mod(y^2, p)));
											print("lift(Mod(x^3 + a * x + b, p)) = ", lift(Mod(x^3 + a * x + b, p)));


											if(Mod(y^2, p) == Mod(x^3 + a * x + b, p),

											print("True");

											);
											
											Px = x;
											Py = y;


print("<---------------------------------->");
print("<--------------Krok-4--------------->");
print("<---------------------------------->");

Xa = randomprime(2^k);

while(Xa > p-p^(1/2),

Xa = randomprime(2^k);

);


print("<---------------------------------->");
print("<--------------Krok-5--------------->");
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

									PX = Px;
									PY = Py;

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
											
											
											
				Qx = RX;
				Qy = RY;

print("<---------------------------------->");
print("<--------------Krok-6--------------->");
print("<---------------------------------->");


	print("Ea=", a);
	print("Eb=", b);
	print("Ep=", p);
	write("kA-taj.out", "Ea=", a);
	write("kA-taj.out", "Eb=", b);
	write("kA-taj.out", "Ep=", p);
	
	print("p=", p);
	write("kA-taj.out", "p=", p);
	
	print("Px=", Px);
	print("Py=", Py);
	write("kA-taj.out", "Px=", Px);
	write("kA-taj.out", "Py=", Py);
	
	print("Xa=", Xa);
	write("kA-taj.out", "Xa=", Xa);

	

	
print("<---------------------------------->");
print("<--------------Krok-7--------------->");
print("<---------------------------------->");

	
	
	print("Ea=", a);
	print("Eb=", b);
	print("Ep=", p);
	write("kA-pub.out", "Ea=", a);
	write("kA-pub.out", "Eb=", b);
	write("kA-pub.out", "Ep=", p);
	
	print("p=", p);
	write("kA-pub.out", "p=", p);
	
	print("Px=", Px);
	print("Py=", Py);
	write("kA-pub.out", "Px=", Px);
	write("kA-pub.out", "Py=", Py);
	
	print("Qx=", Qx);
	print("Qy=", Qy);
	write("KA-pub.out", "Qx=", Qx);
	write("KA-pub.out", "Qy=", Qy);	



}
