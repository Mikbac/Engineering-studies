{
default(parisize, 120000000);
setrand(getwalltime());

data1 = read("KA-pub.out");

print("p= ", p);
print("q= ", q);
print("g= ", g);
print("y= ", y);

data2 = read("rs.out");

print("r= ", r);
print("s= ", s);

/*M = read("M");*/
H = read("Mh.out");

print("H= ", H);

/*-1-*/
print("krok-1");

if((r<0 || r>q) || (s<0 || s>y),
print("NIE");
return;
);

/*-2-*/
print("krok-2");

u1 = lift(Mod(1/s * H, q));
print("u1= ", u1);

/*-3-*/
print("krok-3");

u2 =  lift(Mod(r*1/s, q));
print("u2= ", u2);

/*-4-*/
print("krok-4");

v = Mod(Mod(g*y, p)^(u1*u2), q);

/*-5-*/
print("krok-5");
/*
print("v= ", v);
print("r= ", r);*/

if(v == r,
print("TAK");
return;
);

print("NIE");
return;
}